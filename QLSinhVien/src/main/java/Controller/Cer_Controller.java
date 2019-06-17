package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Connection.ConnectionDB;
import Model.Certificate;

@Controller
public class Cer_Controller {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	// show
	@RequestMapping(value = "/cer", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Certificate> sv() {
		ArrayList<Certificate> list = new ArrayList<Certificate>();
		String sql = "SELECT  Diem.mamh,sinhvien.MASV,sinhvien.TenSV,MonHoc.TenMon,Diem.Diem_1,Diem.Diem_2 from sinhvien  join Diem on sinhvien.MaSV = Diem.MaSV  join MonHoc on Diem.MaMH = MonHoc.MaMH order by sinhvien.masv";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Certificate(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getFloat(5), rs.getFloat(6)));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// Sửa
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public void editt(@RequestParam("id") int id, @RequestParam("idsub") String idsub,
			@RequestParam("diem1") double diem1, @RequestParam("diem2") double diem2) {
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("update diem set diem_1 = '" + diem1 + "',diem_2 = '" + diem2 + "' where masv = '" + id
					+ "' and mamh = '" + idsub + "'");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Tìm kiếm
	@RequestMapping(value = "/timkiem/{infor}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Certificate> timkiem(@PathVariable("infor") String infor) throws Exception {
		ArrayList<Certificate> sv = new ArrayList<Certificate>();
		conn = getConnection();
		pstmt = conn.prepareStatement(
				"SELECT  Diem.mamh,sinhvien.MASV,sinhvien.TenSV,MonHoc.TenMon,Diem.Diem_1,Diem.Diem_2 "
						+ "from sinhvien  join Diem on sinhvien.MaSV = Diem.MaSV  join MonHoc on Diem.MaMH = "
						+ "MonHoc.MaMH where sinhvien.MASV like '%" + infor + "%' or SinhVien.TenSV like '%" + infor + "%'");
		rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				sv.add(new Certificate(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5),
						rs.getFloat(6)));
			}
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sv;
	}

	// Xóa
	@RequestMapping(value = "/del/{id}/{idsub}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void DeleteID(@PathVariable("id") int id, @PathVariable("idsub") int idsub) {
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("DELETE FROM diem WHERE masv=" + id + " and mamh = '" + idsub + "'");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// thêm sinh viên
	@RequestMapping(value = "/insSV", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void insertSV(@RequestParam("name") String name, @RequestParam("age") int age) {
		String ids = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(masv) from sinhvien");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String s = rs.getString(1);
				ids = Integer.parseInt(s) + 1 + "";
			}
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into sinhvien values('" + ids + "',N'" + name + "','" + age + "')");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// thêm điểm

	@RequestMapping(value = "/insScore", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void insertScore(@RequestParam("id") int id, @RequestParam("idsub") int idsub,
			@RequestParam("diem1") float diem1, @RequestParam("diem2") float diem2) {

		int a = 0, b = 0, c = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select MaSV from sinhvien");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (id == rs.getInt(1)) {
					a = 1;
					break;
				}

			}
			pstmt = conn.prepareStatement("select Mamh from monhoc");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (idsub == rs.getInt(1)) {
					b = 1;
					break;
				}

			}
			pstmt = conn.prepareStatement("select masv,Mamh from diem");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (id == rs.getInt(1) && idsub == rs.getInt(2)) {
					c = 1;
					break;
				}

			}
			if (a == 1 && b == 1 && c == 0) {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("insert into diem values(" + id + "," + idsub + "," + diem1 + "," + diem2 + ")");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
* Cung cap class CertificateController co nhung phuong thuc thuc hien viec ket noi database,
* 
* hien thi bang điem,them sua xoa va tim kiem thong tin co trong bang 
* 
* de file jsp tuong ung bat du lieu ma cac lop tra ve
* 
* de xu ly va xuat ra screen.
* 
* @author HaiKM
* @data 19/6/2019
*   
*/
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
public class CertificateController {
	              
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
//bbbbb
	/**
	 * phuong thuc nay duoc su dung de tra ve connect voi database
	 * 
	 * @return connect tra ve 1 hoac 0 (1 la thang cong, 0 la that bai)
	 */
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connect = ConnectionDB.getInstance().getConnection();
		return connect;
	}

	/**
	 * khi ajax goi toi /certificate với method GET và typedata: json thi phuong
	 * thuc nay se duoc su dung de tra ve 1 danh sach sau do ben jsp se lay danh
	 * sach do va hien thi len screen.
	 * 
	 * @return listScore Tra ve danh sach tim duoc trong database.
	 */
	@RequestMapping(value = "/certificate", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Certificate> Score() {
		ArrayList<Certificate> listScore = new ArrayList<Certificate>();
		String sql = "SELECT  Diem.mamh,sinhvien.MASV,sinhvien.TenSV,MonHoc.TenMon,Diem.Diem_1,Diem.Diem_2"
				+ " from sinhvien  join Diem on sinhvien.MaSV = Diem.MaSV  join MonHoc on Diem.MaMH = MonHoc.MaMH "
				+ "order by sinhvien.masv";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listScore.add(new Certificate(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),
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
		return listScore;
	}

	/**
	 * khi ajax goi toi /edit với method post thi phuong thuc nay se duoc su dung de
	 * giao tiep voi database va thuc hien cau lenh sql voi nhung du lieu lay duoc
	 * tu jsp.
	 * 
	 * @param idSV
	 *            Day la tham so dau tien cua phuong thuc edit
	 * @param idSub
	 *            Day la tham so thu hai cua phuong thuc edit
	 * @param diem_1
	 *            Day la tham so thu ba cua phuong thuc edit
	 * @param diem_2
	 *            Day la tham so cuoi cung cua phuong thuc edit tat ca tham so tren
	 *            deu dc RequestParam lay tu jsp
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public void edit(@RequestParam("id") int idSV, @RequestParam("idsub") String idSubject,
			@RequestParam("diem1") double diem_1, @RequestParam("diem2") double diem_2) {
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("update diem set diem_1 = '" + diem_1 + "',diem_2 = '" + diem_2 + "' where masv = '" + idSV
					+ "' and mamh = '" + idSubject + "'");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * khi ajax goi toi /certificate với method GET và typedata: json thi phuong
	 * thuc nay se duoc su dung de tra ve 1 danh sach tim duoc trong database sau do
	 * ben jsp se lay danh sach do va hien thi len screen.
	 * 
	 * @param infor
	 *            Day la tham so duy nhat cua phuong thuc timkiem, gia tri cua tham
	 *            so nay duoc requestParam lay tu jsp
	 * @return list Tra ve danh sach tim duoc trong database.
	 */
	@RequestMapping(value = "/timkiem/{infor}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Certificate> timkiem(@PathVariable("infor") String infor) throws Exception {
		ArrayList<Certificate> list = new ArrayList<Certificate>();
		conn = getConnection();
		pstmt = conn.prepareStatement(
				"SELECT  Diem.mamh,sinhvien.MASV,sinhvien.TenSV,MonHoc.TenMon,Diem.Diem_1,Diem.Diem_2 "
						+ "from sinhvien  join Diem on sinhvien.MaSV = Diem.MaSV  join MonHoc on Diem.MaMH = "
						+ "MonHoc.MaMH where sinhvien.MASV like '%" + infor + "%' or SinhVien.TenSV like N'%" + infor
						+ "%'");
		rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				list.add(new Certificate(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getFloat(5), rs.getFloat(6)));
			}
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	/**
	 * khi ajax goi toi /del/?/? với method post typadata: json thi phuong thuc nay
	 * se duoc su dung de giao tiep voi database va thuc hien cau lenh sql voi nhung
	 * du lieu lay duoc tu jsp.
	 * 
	 * @param idSV
	 *            Day la tham so dau tien cua phuong thuc DeleteID
	 * @param idSub
	 *            Day la tham so thu hai cua phuong thuc DeleteID tat ca tham so
	 *            tren deu dc RequestParam lay tu jsp
	 */
	@RequestMapping(value = "/del/{id}/{idsub}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void DeleteID(@PathVariable("id") int idSV, @PathVariable("idsub") int idSub) {
		try {
			conn = getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("DELETE FROM diem WHERE masv=" + idSV + " and mamh = '" + idSub + "'");
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * khi ajax goi toi insertScore? với method post typadata: jso thi phuong thu
	 * nay se duoc su dung de giao tiep voi databas dau tien la kiem tra xem sinh
	 * vien va mon hoc da ton tai trong bang sinh vien va mon hoc chua tiep theo
	 * kiem tra xem sinh vien va mon hoc do da có trong bang diem chua neu sinh vien
	 * va mon hoc da ton tai trong bang sinh vien,mon hoc va ko ton tai trong bang
	 * diem thia thuc hien cau lenh sql voi nhung du lieu lay duoc tu jsp
	 * 
	 * @param idS
	 *            Day la tham so dau tien cua phuong thucinsertScore
	 * @param idSu
	 *            Day la tham so thu hai cua phuong thucinsertScore
	 * @param diem_1
	 *            Day la tham so thu ba cua phuong thuc insertScore
	 * @param diem_2
	 *            Day la tham so cuoi cung cua phuong thuc insertScore tat ca tha so
	 *            tren deu dc RequestParam lay tu js
	 */
	@RequestMapping(value = "/insertScore", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void insertScore(@RequestParam("id") int idSV, @RequestParam("idsub") int idSub,
			@RequestParam("diem1") float diem_1, @RequestParam("diem2") float diem_2) {

		int a = 0, b = 0, c = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select MaSV from sinhvien");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (idSV == rs.getInt(1)) {
					a = 1;
					break;
				}

			}
			pstmt = conn.prepareStatement("select Mamh from monhoc");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (idSub == rs.getInt(1)) {
					b = 1;
					break;
				}

			}
			pstmt = conn.prepareStatement("select masv,Mamh from diem");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (idSV == rs.getInt(1) && idSub == rs.getInt(2)) {
					c = 1;
					break;
				}

			}
			if (a == 1 && b == 1 && c == 0) {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("insert into diem values(" + idSV + "," + idSub + "," + diem_1 + "," + diem_2 + ")");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

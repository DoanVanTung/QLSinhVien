/**
 *SubjectController thuc hien cac chuc nang cua phan dang ky mon hoc va hien thi ket qua dang ky
 *Dang ky mon hoc:gom lay thong tin mon hoc theo ky hoc,them-sua-xoa-timkiem-dangky  
 *Ket Qua dang ky:gom chuc nang hien thi ket qua dang ky cua sinh vien do va huy dang ky
 *
 *@author Quang Huy
 *@date:19/6/2019 
 *
 */

package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Connection.ConnectionDB;
import Golobal.test;
import Model.Subject;

@Controller
public class SubjectController {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	/**
	 * Lay tat ca du lieu cua bang Mon hoc
	 * Chon ky hoc,nhan nut chon se hien ra ket qua la cac mon hoc cua ky hoc do tren trang Subject.jsp
	 * @param semesterId(ky hoc)
	 * @return listSubject(danh sach mon hoc)
	 */
	@RequestMapping(value = "/Subject/{semesterId}", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Subject> getAllSubject(@PathVariable("semesterId") String semesterId, HttpSession session) {
		ArrayList<Subject> listSubject = new ArrayList<Subject>();

		String sql = "SELECT * from MonHoc where IdKH ='" + semesterId + "' ORDER BY MaMH DESC";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listSubject.add(new Subject(rs.getInt(1), rs.getInt(2), rs.getString(3)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSubject;
	}

	/**
	 * 
	 * Lay ra ten cua sinh vien vua dang nhap tren trang KQDK.jsp
	 * @param 
	 * @return studentName-(ten sinh vien)
	 */
	@RequestMapping(value = "/getName", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public String getNameStudent(HttpSession session) {

		String studentName = "";
		String sql = "SELECT * from SinhVien where MaSV ='" + test.getUser() + "'";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentName = rs.getString(2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentName;
	}

	/**
	 * Khi nhan nut "Edit",thong tin mon hoc do se duoc day len popup tren trang Subject.jsp
	 * Nut "Update" se cap nhap thong tin vua duoc sua vao csdl va chay lai
	 * @param subjectId(ma sv)-semesterId(ky hoc)-subjectName(ten mon hoc)
	 * @return
	 */
	@RequestMapping(value = "/EditSubject", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void updateStudent(@RequestParam("subjectId") Integer subjectId,
			@RequestParam("semesterId") Integer semesterId, @RequestParam("subjectName") String subjectName) {

		String sql = "update MonHoc set IdKH='" + semesterId + "',TenMon=N'" + subjectName + "' where MaMH='"
				+ subjectId + "'";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Khi nhan nut "Delete",thong tin mon hoc do se duoc xoa va chay lai  tren trang Subject.jsp
	 * @param subjectId(ma sv)-semesterId(ky hoc)-subjectName(ten mon hoc)
	 * @return
	 */
	@RequestMapping(value = "/DeleteSubject/{subjectId}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteStudent(@PathVariable("subjectId") Integer subjectId) {

		String sql = "delete from MonHoc where MaMH='" + subjectId + "'";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Khi nhan nut "Dang Ky",neu dung se tra ra thong bao dung va nguoc lai tren trang Subject.jsp
	 * Neu sv do da dk thi se ko hien thong bao da dk va nguoc lai
	 * @param subjectId(ma mon hoc)
	 * @return check-(check=1:cho dki    check=0:ko cho dki)
	 */
	@RequestMapping(value = "/DKSubject/{subjectId}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public int dkSubject(@PathVariable("subjectId") Integer subjectId, ModelMap mn) {
		ArrayList<Subject> list = new ArrayList<Subject>();
		String sql1 = "SELECT * from DangKyMH where MaSV ='" + test.getUser() + "' ";
		int check = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Subject(rs.getInt(2)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "insert into DangKyMH values('" + test.getUser() + "','" + subjectId + "')";

		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(subjectId) == false) {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					check = 1;
					break;
				} else {
					check = 0;
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	/**
	 * Khi nhan nut "Them",se hien len  popup cho phep nhap hoc ky va ten mon hoc tren trang Subject.jsp
	 * ma mon hoc se duoc sinh tu dong
	 * nut "Add" tren popup se them thong tin mon hoc do vao csdl 
	 * @param subjectId(ma sv)-semesterId(ky hoc)-subjectName(ten mon hoc)
	 * 
	 */
	@RequestMapping(value = "/addSubject", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void addStudent(@RequestParam("semesterId") Integer semesterId,
			@RequestParam("subjectName") String subjectName) {
		String sql = "select * from MonHoc ";

		String subjectId = "";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String s = rs.getString(1);

				subjectId = Integer.parseInt(s) + 1 + "";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "insert into MonHoc values('" + subjectId + "','" + semesterId + "',N'" + subjectName + "')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Khi nhap ten sinh vien can tim vao the input,nhan nut Search se hien thi sinh vien do duoi bang thong tin sv tren trang Subject.jsp
	 * @param searchSubject
	 * @return lstSubject(thong tin mon hoc can tim)
	 */
	@RequestMapping(value = "/Search/{searchSubject}", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Subject> searchStudent(@PathVariable("searchSubject") String searchSubject) {

		String sql = "SELECT * from MonHoc where TenMon=N'" + searchSubject + "' ";

		List<Subject> lstSubject = new ArrayList<Subject>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lstSubject.add(new Subject(rs.getInt(1), rs.getInt(2), rs.getString(3)));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return (ArrayList<Subject>) lstSubject;
	}

	/**
	 * khi nhan vao "Huy Dk" se xoa bo mon hoc ma sinh vien do dang ky khoi bang va csdl
	 * @param subjectId(ma mon hoc)
	 *
	 */
	@RequestMapping(value = "/HuyDangKy/{subjectId}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteDk(@PathVariable("subjectId") String subjectId) throws ClassNotFoundException, SQLException {

		String sql = "delete from dangkymh where mamh='" + subjectId + "' and masv='" + test.getUser() + "'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * khi nhan vao danh muc "Tra cuu mon moc" se hien thi 1 bang chua danh sach mon hoc ma sinh vien do da dang ky
	 * @return listSubject(danh sach mon hoc ma sinh vien do dang ky)
	 */
	@RequestMapping(value = "/Kqdk", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Subject> getAllKqdk(HttpSession session) throws ClassNotFoundException, SQLException {

		ArrayList<Subject> listSubject = new ArrayList<Subject>();

		String sql = "select d.MaMH,m.TenMon\r\n" + "from DangKyMH d,MonHoc m\r\n" + "where d.MaMH=m.MaMH and d.MaSV='"
				+ test.getUser() + "'";
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			listSubject.add(new Subject(rs.getInt(1), rs.getString(2)));

		}
		return listSubject;
	}
}

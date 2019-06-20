package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
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
import Golobal.test;;

@Controller
@RequestMapping("/")
public class HomeController {
	/* Khai bao connectDB */
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	/* Mo trang Sinhvien */
	@RequestMapping("/Sv")
	public String sv() {
		return "Sinhvien";
	}
	/* Mo trang KetQua_DkyHoc */
	@RequestMapping("KetQua_DkyHoc")
	public String KQDK() {
		return "KQDK";
	}
	/* Mo trang BangDiem */
	@RequestMapping("bangdiem")
	public String tranScript() {
		return "BangDiem";
	}

	/* Mo trang Subject */
	@RequestMapping("SubjectQ")
	public String subject() {
		return "Subject";
	}

	/* Mo trang Login */
	@RequestMapping()
	public String login() {
		return "Login";
	}

	/* Khi click link back o trang dang ky se tro ve tran login */
	@RequestMapping("/Login")
	public String login1() {
		return "Login";
	}

	/**
	 * Khi đang nhap thanh con se mo trang TrangChu.
	 * 
	 * @author Tung.
	 * @param String $account.
	 * @param String $password.
	 * @return String login or index.
	 * @date 6/19/2019
	 */
	@RequestMapping("TrangChu")
	public String login(HttpServletRequest request, ModelMap model, @RequestParam("TaiKhoan") int id_User,
			HttpSession session) throws ClassNotFoundException, SQLException {
		int d = 0;
		String user = request.getParameter("TaiKhoan");
		String pass = request.getParameter("MatKhau");
		
		String sql = "select * from DangNhap";
		session.setAttribute("id_User", user);
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				if (user.trim().equals(rs.getString(1)) && pass.trim().equals(rs.getString(2))) {
					System.out.println();
					session.setAttribute("user", user);
					test.setUser(user);
					d = 1;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (d == 1) {
			return "index";
		} else {
			return "Login";
		}
	}

	/* Mo trang chu khi clich vao link back tu trank doi mat khau */
	@RequestMapping("/TrangChu1")
	public String trangChu1() {
		return "index";
	}

	/* Mo trang dang ki */
	@RequestMapping("dangky")
	public String dangKy() {
		return "Signup";
	}

	/**
	 * Khi dang ki nhan duoc bien String account chua thong tin dang ki bao gom tai
	 * khoan va mat khau .
	 * @author Tung.
	 * @param String $account.
	 * @return Integer data , data = 1 => dang ki thanh cong, data = 2 => dang ki
	 *         that bai
	 * @date 6/19/2019
	 */
	@RequestMapping(value = "/inser/{insert}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public int insert(@PathVariable("insert") String insert, ModelMap moMap)
			throws ClassNotFoundException, SQLException {
		int d = 0;
		int tg = 0;
		System.out.println("da vao ham insert");
		String[] arr = insert.split("_");
		String sql = "select * from DangNhap";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		try {
			while (rs.next()) {
				String masv = rs.getString(1);
				if (arr[0].trim().equals(masv)) {
					d = 1;
					break;
				}
			}
			if (d == 0) {
				if (arr[1].trim().equals(arr[2].trim())) {
					pstmt = conn.prepareStatement("insert into DangNhap values('" + arr[0] + "',N'" + arr[1] + "')");
					pstmt.executeUpdate();
					tg = 1;
				} else {
					tg = 2;
				}

			} else {
				tg = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tg);
		return tg;
	}

	/* mo trang doi mat khau */
	@RequestMapping("DoiMK")
	public String doiMK() {
		return "EditMK";
	}

	/**
	 * Khi click button sua nhan duoc bien String editPass chua thong tin sau bao
	 * gom tai khoan va mat khau , mat khau moi .
	 * 
	 * @author Tung.
	 * @param String $editPass.
	 * @return Integer data , data = 1 => Edit thanh cong, data = 2 => Edit that
	 *         bai.
	 * @date 6/19/2019
	 */
	@RequestMapping(value = "/EditMK/{insert}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public int editMK(@PathVariable("insert") String insert, ModelMap moMap)
			throws ClassNotFoundException, SQLException {
		int d = 0;
		int tg = 0;
		String masv;
		String mk = null;
		String[] arr = insert.split("_");
		String sql = "select * from DangNhap";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		try {
			while (rs.next()) {
				masv = rs.getString(1);
				mk = rs.getString(2);
				if (arr[0].trim().equals(masv)) {
					d = 1;
					break;
				}
			}
			if (d == 1) {
				if (arr[2].trim().equals(arr[3].trim())) {
					if (arr[1].trim().equals(mk)) {
						pstmt = conn.prepareStatement(
								"update DangNhap set MatKhau='" + arr[2] + "' where TaiKhoan ='" + arr[0] + "'");
						pstmt.executeUpdate();
						tg = 1;
					}

				} else {
					tg = 2;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tg);
		return tg;
	}

}

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

import Connection.ConnectionDB;;


@Controller
@RequestMapping("/")
public class HomeController {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}
	
	@RequestMapping("/Sv")
	public String Sv() {
		return "Sinhvien";
	}
	@RequestMapping("/Score")
	public String Score() {
		return "Cer_Table";
	}
	@RequestMapping("SubjectQ")
	public String Subject() {
		return "Subject";
	}
	@RequestMapping()
	public String Login() {
		return "Login";
	}

	@RequestMapping("/Login")
	public String Login1() {
		return "Login";
	}

	@RequestMapping("TrangChu")
	public String login(HttpServletRequest request, ModelMap model, @RequestParam("TaiKhoan") int id_User,HttpSession session) throws ClassNotFoundException, SQLException {
		String user = request.getParameter("TaiKhoan");
		String pass = request.getParameter("MatKhau");
		String sql = "select * from DangNhap";
		session.setAttribute("id_User", user);
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int d = 0;
		try {
			while (rs.next()) {
				if (user.trim().equals(rs.getString(1)) && pass.trim().equals(rs.getString(2))) {
					System.out.println();
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
			model.addAttribute("mess", "Sai thÃ´ng Tin Ä�Äƒng Nháº­p");
			return "Login";
		}
	}

	@RequestMapping("/TrangChu1")
	public String TrangChu1() {
		return "index";
	}

	@RequestMapping("dangky")
	public String dangky() {
		return "Signup";
	}

	@RequestMapping(value = "/inser/{insert}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public int insert(@PathVariable("insert") String insert, ModelMap moMap) throws ClassNotFoundException, SQLException {
		System.out.println("da vao ham insert");
		String[] arr = insert.split("_");
		String sql = "select * from DangNhap";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int d = 0;
		int tg = 0;
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
					moMap.addAttribute("mess1", "Ä�Äƒng KÃ­ thÃ nh CÃ´ng");
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

	@RequestMapping("DoiMK")
	public String DoiMK() {
		return "EditMK";
	}

	@RequestMapping(value = "/EditMK/{insert}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public int EditMK(@PathVariable("insert") String insert, ModelMap moMap)
			throws ClassNotFoundException, SQLException {
		System.out.println("da vao ham insert");
		String[] arr = insert.split("_");
		String sql = "select * from DangNhap";

		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int d = 0;
		int tg = 0;
		String masv;
		String mk = null;
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

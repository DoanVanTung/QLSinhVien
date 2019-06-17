package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;


@Controller
public class Add_sv {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	@RequestMapping(value = "/addsv", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })

	public String abc(@RequestParam("maSV1") int maSV, @RequestParam("tenSV1") String tenSV,
			@RequestParam("age1") int age,ModelMap mn) throws ClassNotFoundException, SQLException {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		int check = 0;
		int id;
		conn = getConnection();
		pstmt = conn.prepareStatement("select * from SinhVien");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			String s = rs.getString(1);
			id = Integer.parseInt(s);
			lst.add(id);
		}
		for (int i = 0; i < lst.size(); i++) {
			if (maSV == lst.get(i)) {
				check = 2;
				break;
			} else {
				check = 1;
				continue;
			}
		}
		if (check == 2) {
			mn.put("abc", "mã sinh viên đã tồn tại");
			return "Bangdiem";
		} else {
			pstmt = conn.prepareStatement("insert into sinhvien values('" + maSV + "','" + tenSV + "','" + age + "')");
			pstmt.executeUpdate();
			return "Bangdiem";
		}

	}
}


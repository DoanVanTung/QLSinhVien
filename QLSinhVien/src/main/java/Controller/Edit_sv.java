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

@Controller
public class Edit_sv {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })

	public String abc(@RequestParam("maSV") int maSV, @RequestParam("tenSV") String tenSV,
			@RequestParam("age") int age) throws ClassNotFoundException, SQLException {
		
		conn = getConnection();
		pstmt = conn.prepareStatement(
				"update sinhvien set tensv='"+tenSV+"',age='"+age+"' where maSV ='"+maSV+"' ");
		pstmt.executeUpdate();
		return "Bangdiem";

	}
}


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
import Connection.*;

@Controller
public class Delete_sv {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	@RequestMapping(value = "/Delete_sv/{id_sinhvien}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String deleteStudent(@PathVariable("id_sinhvien") String empNo) throws ClassNotFoundException, SQLException {
		
		String sql="delete from sinhvien where maSV='" + empNo + "'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empNo;
	}

}
// nếu method POST thì return phải ntn ?
//contentType : la gì? tai sao phải dùng? dùng để làm gì?
//dataType  la gì? tai sao phải dùng? dùng để làm gì?


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

import Model.SinhVien;


@Controller
public class Seach_SV {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}

	@RequestMapping(value = "/seach/{aaa}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<SinhVien> abc(@PathVariable("aaa") String tenSV) throws ClassNotFoundException, SQLException {
		
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		conn = getConnection();
		pstmt = conn.prepareStatement(
				"select * from sinhvien where TenSV='"+tenSV+"'");
        rs = pstmt.executeQuery();
		while (rs.next()) {
            list.add(new SinhVien(rs.getInt(1),rs.getString(2),rs.getInt(3)));
  
}
		return list;


	}
}



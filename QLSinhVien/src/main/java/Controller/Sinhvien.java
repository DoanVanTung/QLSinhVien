package Controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Connection.ConnectionDB;
import Model.SinhVien;


@Controller
public class Sinhvien {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionDB.getInstance().getConnection();
        return con;
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET, headers="Accept=*/*",produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<SinhVien> sv() throws ClassNotFoundException, SQLException {
		
    	 ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien order by maSV ASC" ;
      
        	conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        	while (rs.next()) {
                list.add(new SinhVien(rs.getInt(1),rs.getString(2),rs.getInt(3)));
      
    }
			return list;
	}}
    
   





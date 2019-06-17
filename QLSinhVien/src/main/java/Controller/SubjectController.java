package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Connection.ConnectionDB;
import Model.Subject;

public class SubjectController {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}
	
	

	@RequestMapping(value = "/Subject/{aa}", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Subject> abc(@PathVariable("aa") String empNo) {
		ArrayList<Subject> list = new ArrayList<Subject>();
		String sql = "SELECT * from MonHoc where IdKH ='" + empNo + "' ORDER BY MaMH DESC";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Subject(rs.getInt(1), rs.getInt(2), rs.getString(3)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/EditSubject", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void updateStudent(@RequestParam("subjectId") Integer subjectId,
			@RequestParam("semesterId") Integer semesterId, @RequestParam("subjectName") String subjectName) {

		String sql = "update MonHoc set IdKH='" + semesterId + "',TenMon=N'" + subjectName + "' where MaMH='"+ subjectId + "'";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/DeleteSubject/{a}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteStudent(@PathVariable("a") Integer id) {

		String sql = "delete from MonHoc where MaMH='" + id + "'";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/addSubject", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public void addStudent(@RequestParam("semesterId") Integer semesterId, @RequestParam("subjectName") String subjectName) {
		String sql = "select * from MonHoc ";

		String id = "";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String s = rs.getString(1);

				id = Integer.parseInt(s) + 1 + "";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "insert into MonHoc values('" + id + "','" + semesterId + "',N'" + subjectName + "')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/Search/{searchSubject}", method = RequestMethod.GET, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<Subject>
	searchStudent(@PathVariable("searchSubject") String searchSubject) {

	  String sql = "SELECT * from MonHoc where TenMon=N'"+searchSubject+"' ";
	  
	  List<Subject> lststudentID=new ArrayList<Subject>();
	  try {
		  conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			lststudentID.add(new Subject(rs.getInt(1), rs.getInt(2), rs.getString(3)));
           
            
			}	
			
             
			
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	  return (ArrayList<Subject>) lststudentID;
}
}

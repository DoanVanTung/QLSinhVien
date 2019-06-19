package Controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import Connection.*;
import Model.SinhVien;

@Controller
public class SinhvienController {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = ConnectionDB.getInstance().getConnection();
		return con;
	}
	
	
	/**
	 * @funtion: lay thong tin tat ca sinh vien
	 * @author nguyenkhai
	 * @date 19/6/2019
	 * @param 
	 * @return : listSv chua thong tin toan bo sinh vien
	 * @example: 
	 *
	 */
	@RequestMapping(value = "/tableSinhvien", method = RequestMethod.GET, headers="Accept=*/*",produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<SinhVien> sv(HttpSession session) throws ClassNotFoundException, SQLException {
		
    	ArrayList<SinhVien> listSv = new ArrayList<SinhVien>();
		String sql = "select * from SinhVien order by maSV ASC" ;
		
        	   try {
        		   conn = getConnection();
                   pstmt = conn.prepareStatement(sql);
                   rs = pstmt.executeQuery();
                   
                   while (rs.next()) {
                       listSv.add(new SinhVien(rs.getInt(1),rs.getString(2),rs.getInt(3)));
                   }
                } catch (SQLException | ClassNotFoundException e) {
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
			return listSv;
	}
	
	
	/**
	 * @funtion: xoa sinh vien
	 * @author nguyenkhai
	 * @date 19/6/2019
	 * @param idSinhvien chua maSinhvien muon xoa. duoc gan vao button trong file jsp
	 * @return : listSv chua danh sach sinh vien sau khi da xoa
	 * @example: 
	 *
	 */
	@RequestMapping(value = "/deleteSinhvien/{idSinhvien}", method = RequestMethod.POST, headers = "Accept=*/*", produces = {
			MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String deleteStudent(@PathVariable("idSinhvien") String idSinhvien) throws ClassNotFoundException, SQLException {
		
		String sql="delete from sinhvien where maSV='" + idSinhvien + "'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
	}
		return idSinhvien;

}
	
	
	/**
	 * @funtion: them sinh vien
	 * @author nguyenkhai
	 * @date 19/6/2019
	 * @param idSinhvien moi = max(idSinhvien)+1
	 * @return : listSv chua danh sach sinh vien sau khi da xoa
	 * @example: 
	 *
	 */
	@RequestMapping(value = "/addSinhvien", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })

	public String addSinhvien(@RequestParam("tenSinhvien1") String tenSinhvien,
			@RequestParam("tuoi1") int tuoi,ModelMap maps) throws ClassNotFoundException, SQLException {
		ArrayList<Integer> listSv = new ArrayList<Integer>();
		int idSinhvien = 0;
				
		 try {
			 	conn = getConnection();
				pstmt = conn.prepareStatement("select max(masv) from sinhvien");
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String s = rs.getString(1);
					idSinhvien = Integer.parseInt(s)+ 1;
					listSv.add(idSinhvien);
				}
				
					pstmt = conn.prepareStatement("insert into sinhvien values('" + idSinhvien + "','" + tenSinhvien + "','" + tuoi + "')");
					pstmt.executeUpdate();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
          } finally {
              try {
                  if (pstmt != null) {
                      pstmt.close();
                  }
                  if (conn != null) {
                      conn.close();
                  }
              } catch (final SQLException e) {
                  e.printStackTrace();
              }
          }
		return "Sinhvien";
	}
	
	
	/**
	 * @funtion: sua thong tin sinh vien
	 * @author nguyenkhai
	 * @date 19/6/2019
	 * @param idSinhvien,tenSinhvien,tuoi duoc lay tu trang jsp. idSinhvien khong duoc phep sua
	 * @return : tra ve trang sinh vien chua thong tin sinh vien sau khi sua
	 * @example: 
	 *
	 */
	@RequestMapping(value = "/editSinhvien", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })

	public String abc(@RequestParam("maSinhvien") int maSinhvien, @RequestParam("tenSinhvien") String tenSinhvien,
			@RequestParam("tuoi") int tuoi) throws ClassNotFoundException, SQLException {
		

		 try {
			 	conn = getConnection();
				pstmt = conn.prepareStatement("update sinhvien set tensv='"+tenSinhvien+"',age='"+tuoi+"' where maSV ='"+maSinhvien+"' ");
				pstmt.executeUpdate();
          } catch (SQLException | ClassNotFoundException e) {
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
		return "Sinhvien";

	}
	
	
	/**
	 * @funtion: tim kiem sinh vien theo ten hoac ma sinh vien 
	 * @author nguyenkhai
	 * @date 19/6/2019
	 * @param searchSV= tenSSinhvien or maSinhvien tu fom tim kiem jsp
	 * @return : tra ve sinh vien neu co, nguoc lai se tra ve message
	 * @example: nhap 1 se tra ve sinh vien co maSinhvien=1 || nhap " g " se tra ve tat ca sinh vien co ten chua chu " g "
	 *
	 */
	@RequestMapping(value = "/seachSinhvien/{tenSinhvien}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ArrayList<SinhVien> abc(@PathVariable("searchSV") String searchSV) throws ClassNotFoundException, SQLException {
		
		ArrayList<SinhVien> listSv = new ArrayList<SinhVien>();
		
		 try {
			 	conn = getConnection();
				pstmt = conn.prepareStatement("SELECT * from sinhvien  where MASV like '%" + searchSV + "%' or TenSV like N'%" + searchSV+ "%'");
		        rs = pstmt.executeQuery();
		        
				while (rs.next()) {
		            listSv.add(new SinhVien(rs.getInt(1),rs.getString(2),rs.getInt(3)));
				}
  		  
          } catch (SQLException | ClassNotFoundException e) {
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
		
		return listSv;


	}

}

package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.activation.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//update 부분
public class db_update {
     Connection con = null;
     PreparedStatement ps = null;
     String result = null;
     
     public db_update(BasicDataSource dataSource) {
    	try {
    	    this.con = dataSource.getConnection();	
    	}
    	catch (Exception e) {
    		System.out.println("DB 연결 오류 발생!!");
    	}
     }
     
     
     //패스워드 변경
     public String passloss(String ridx, String rid, String pass) {
    	 try {
    		 String sql = "update reserve set rpass=? where ridx=? and rid=?";
    		 this.ps = this.con.prepareStatement(sql);
    		 this.ps.setString(1, pass);
    		 this.ps.setString(2, ridx);
    		 this.ps.setString(3, rid);
    		 int success = this.ps.executeUpdate();
    		 if(success > 0) {
    			 result  = "ok";
    		 }
    		 else {
    			 result = "no";
    		 }
    		 this.ps.close();
    		 this.con.close();
    	 }
    	 catch(Exception e) {
    		 System.out.println("SQL 문법 오류!");
    	 }
    	 return this.result;
     }


     public void approveAdmin(int midx) {
		 try {
			 String sql = "update admin set mcheck = 'Y' where midx = ?";
			 this.ps = this.con.prepareStatement(sql);
			 this.ps.setInt(1, midx);
			 int success = this.ps.executeUpdate();
			 
    		 this.ps.close();
    		 this.con.close();
    	 }
    	 catch(Exception e) {
    		 System.out.println("SQL 문법 오류!");
    	 }
	}
     
     public void disapproveAdmin(int midx) {
		 try {
			 String sql = "update admin set mcheck = 'N' where midx = ?";
			 this.ps = this.con.prepareStatement(sql);
			 this.ps.setInt(1, midx);
			 int success = this.ps.executeUpdate();
			 
    		 this.ps.close();
    		 this.con.close();
    	 }
    	 catch(Exception e) {
    		 System.out.println("SQL 문법 오류!");
    	 }
	}


     public void plusMcount(int midx) {
		 try {
			 String sql = "update admin set mcount = mcount + 1 where midx = ?";
			 this.ps = this.con.prepareStatement(sql);
			 this.ps.setInt(1, midx);
			 int success = this.ps.executeUpdate();
			 
    		 this.ps.close();
    		 this.con.close();
    	 }
    	 catch(Exception e) {
    		 System.out.println("SQL 문법 오류!");
    	 }
		
	}
     
     
     public void updateSiteinfo(member_dto member) throws Exception {
		 try {
			 String sql = "update siteinfo set stitle = ?, semail1 = ?, spoint1 = ?, sreward = ? , slevel = ? , scompany = ? , scompany_number = ? , sname = ? , stel = ? ,"+
					 		" snumber1 = ? , snumber2 = ? , snumber3 = ? , saddress = ? , sname2 = ? , semail2 = ? , sbank = ? , snumber4 = ? , scard = ? , shp = ? ,"+
					 		" sgift = ? ,  spoint2 = ? ,  spoint3 = ? , sreceipt = ?  where sidx = ?";
			 this.ps = this.con.prepareStatement(sql);
			 this.ps.setString(1, member.getStitle());
			 this.ps.setString(2, member.getSemail1());
			 this.ps.setString(3, member.getSpoint1());
			 this.ps.setInt(4, member.getSreward());
			 this.ps.setInt(5, member.getSlevel());
			 this.ps.setString(6, member.getScompany());
			 this.ps.setString(7, member.getScompany_number());
			 this.ps.setString(8, member.getSname());
			 this.ps.setString(9, member.getStel());
			 this.ps.setString(10, member.getSnumber1());
			 this.ps.setString(11, member.getSnumber2());
			 this.ps.setString(12, member.getSnumber3());
			 this.ps.setString(13, member.getSaddress());
			 this.ps.setString(14, member.getSname2());
			 this.ps.setString(15, member.getSemail2());
			 this.ps.setString(16, member.getSbank());
			 this.ps.setString(17, member.getSnumber4());
			 this.ps.setString(18, member.getScard());
			 this.ps.setString(19, member.getShp());
			 this.ps.setString(20, member.getSgift());
			 this.ps.setInt(21, member.getSpoint2());
			 this.ps.setInt(22, member.getSpoint3());
			 this.ps.setString(23, member.getSreceipt());
			 this.ps.setInt(24, member.getSidx());

			 
			 int success = this.ps.executeUpdate();
			 
    		 this.ps.close();
    		 this.con.close();
    	 }
    	 catch(Exception e) {
    		 System.out.println("SQL 문법 오류!");
    		 throw e;
    	 }
		
	}
	
	
	
	
	
}

package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class db_insert {

	Connection con = null;
	PreparedStatement ps = null;
	int result = 0; 

	public db_insert(BasicDataSource dataSource) {
		try {
			this.con = dataSource.getConnection();
		} catch (Exception e) {
			System.out.println("Database �젒�냽 �삤瑜� 諛쒖깮!!");
		}
	}


	public int user_insert(String password, login_dto jd) {
		System.out.println(password);
		String abc = jd.getMhp().replaceAll(",", "");

		System.out.println(jd.getMcheck());

		try {
			String sql = "insert into admin values ('0',?,?,?,?,?,?,?,'N',now(),0)";
			this.ps = this.con.prepareStatement(sql);
			// getter
			this.ps.setString(1, jd.getMid());
			this.ps.setString(2, password);
			this.ps.setString(3, jd.getMname());
			this.ps.setString(4, jd.getMemail());
			this.ps.setString(5, abc);
			this.ps.setString(6, jd.getMselect1());
			this.ps.setString(7, jd.getMselect2());
			this.result = this.ps.executeUpdate();

			this.ps.close();
			this.con.close();
		} catch (Exception e) {
			System.out.println("Database 臾몃쾿 �삤瑜�!!" + e);
		}

		return this.result;

	}

	public void saveSiteInfo(String stitle, String semail1, String spoint1, int sreward, int slevel, String scompany,
			String scompany_number, String sname, String stel, String snumber1, String snumber2, String snumber3,
			String saddress, String sname2, String semail2, String sbank, String snumber4, String scard, String shp,
			String sgift, int spoint2, int spoint3, String sreceipt) {
		try {
			// SQL 쿼리 작성
			String sql = "INSERT INTO siteinfo (stitle, semail1, spoint1, sreward, slevel, scompany, scompany_number, sname, stel, snumber1, snumber2, snumber3, saddress, sname2, semail2, sbank, snumber4, scard, shp, sgift, spoint2, spoint3, sreceipt) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// PreparedStatement 생성
			PreparedStatement pstmt = con.prepareStatement(sql);

			// 파라미터 설정
			pstmt.setString(1, stitle);
			pstmt.setString(2, semail1);
			pstmt.setString(3, spoint1);
			pstmt.setInt(4, sreward);
			pstmt.setInt(5, slevel);
			pstmt.setString(6, scompany);
			pstmt.setString(7, scompany_number);
			pstmt.setString(8, sname);
			pstmt.setString(9, stel);
			pstmt.setString(10, snumber1);
			pstmt.setString(11, snumber2);
			pstmt.setString(12, snumber3);
			pstmt.setString(13, saddress);
			pstmt.setString(14, sname2);
			pstmt.setString(15, semail2);
			pstmt.setString(16, sbank);
			pstmt.setString(17, snumber4);
			pstmt.setString(18, scard);
			pstmt.setString(19, shp);
			pstmt.setString(20, sgift);
			pstmt.setInt(21, spoint2);
			pstmt.setInt(22, spoint3);
			pstmt.setString(23, sreceipt);

			// SQL 실행
			pstmt.executeUpdate();

			// 자원 해제
			pstmt.close();
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void saveSiteInfoUseMeberDto(member_dto mdto) {
		try {
			// SQL 쿼리 작성
			String sql = "INSERT INTO siteinfo (stitle, semail1, spoint1, sreward, slevel, scompany, scompany_number, sname, stel, snumber1, snumber2, snumber3, saddress, sname2, semail2, sbank, snumber4, scard, shp, sgift, spoint2, spoint3, sreceipt) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			// PreparedStatement 생성
			PreparedStatement pstmt = con.prepareStatement(sql);

			// 파라미터 설정
			pstmt.setString(1, mdto.getStitle());
			pstmt.setString(2, mdto.getSemail1());
			pstmt.setString(3, mdto.getSpoint1());
			pstmt.setInt(4, mdto.getSreward());
			pstmt.setInt(5, mdto.getSlevel());
			pstmt.setString(6, mdto.getScompany());
			pstmt.setString(7, mdto.getScompany_number());
			pstmt.setString(8, mdto.getSname());
			pstmt.setString(9, mdto.getStel());
			pstmt.setString(10, mdto.getSnumber1());
			pstmt.setString(11, mdto.getSnumber2());
			pstmt.setString(12, mdto.getSnumber3());
			pstmt.setString(13, mdto.getSaddress());
			pstmt.setString(14, mdto.getSname2());
			pstmt.setString(15, mdto.getSemail2());
			pstmt.setString(16, mdto.getSbank());
			pstmt.setString(17, mdto.getSnumber4());
			pstmt.setString(18, mdto.getScard());
			pstmt.setString(19, mdto.getShp());
			pstmt.setString(20, mdto.getSgift());
			pstmt.setInt(21, mdto.getSpoint2());
			pstmt.setInt(22, mdto.getSpoint3());
			pstmt.setString(23, mdto.getSreceipt());

			// SQL 실행
			pstmt.executeUpdate();

			// 자원 해제
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void admin_notice_write(NoticeDto request) {
		try {
			// SQL 쿼리 작성
			String sql = "INSERT INTO noticewrite (mcheck, mtitle, mname, mfile, mtext, mdate, file_name, mviews) "
					+ "VALUES (?, ?, ?, ?, ?, now(), ?, ?)";

			// PreparedStatement 생성
			PreparedStatement pstmt = con.prepareStatement(sql);

			// 파라미터 설정
			pstmt.setBoolean(1, request.isMcheck());
			pstmt.setString(2, request.getMtitle());
			pstmt.setString(3, request.getMname());
			pstmt.setString(4, request.getFilePath());
			pstmt.setString(5, request.getMtext());
			pstmt.setString(6, request.getMfile());
			pstmt.setInt(7, 0);
			
			

			// SQL 실행
			pstmt.executeUpdate();

			// 자원 해제
			pstmt.close();
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

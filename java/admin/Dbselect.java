package admin;
//select 전용 class

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

public class Dbselect {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String result = null; // 결과값 return 사용

	public Dbselect(BasicDataSource dataSource) {
		try {
			this.con = dataSource.getConnection();
		} catch (Exception e) {
			System.out.println("Database 접속 오류!!");
		}
	}



	// 사용자 정보 확인 (가입자명, 이메일)
	public String findpw(String mname, String memail) {
		try {
			String sql = "select midx,mid from admin where mname=? and memail=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, mname);
			this.ps.setString(2, memail);
			this.rs = this.ps.executeQuery();
			while (this.rs.next()) {
				this.result = this.rs.getString("midx") + "," + this.rs.getString("mid");
			}
			this.rs.close();
			this.ps.close();
			this.con.close();
		} catch (Exception e) {
			System.out.println("SQL 문법 오류!");
		}
		return this.result;

	}

	// 회원 가입 여부 체크
	public String usercheck(String rid, String rpass) {

		security sc = new security(rpass);
		String pass = sc.md5_se();
		try {
			String sql = "select * from reserve where rid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, rid);
			this.rs = this.ps.executeQuery();

			while (this.rs.next()) {
				if (rid.equals(this.rs.getString("rid"))) {
					if (pass.equals(this.rs.getString("rpass"))) {
						this.result = this.rs.getString("rid") + "," + this.rs.getString("rname");
					}
				}
			}
			this.rs.close();
			this.ps.close();
			this.con.close();
		} catch (Exception e) {
			System.out.println("SQL 문법 오류 발생!!");
		}
		return this.result;
	}

	public AdminVo getAdmin(String mid) throws SQLException {
		try {
			String sql = "select * from admin where mid = ?";

			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, mid);
			this.rs = this.ps.executeQuery();

			AdminVo adminVo = new AdminVo();
			this.rs.next();
			adminVo.setMidx(rs.getInt("midx"));
			adminVo.setMid(rs.getString("mid"));
			adminVo.setMpass(rs.getString("mpass"));
			adminVo.setMname(rs.getString("mname"));
			adminVo.setMemail(rs.getString("memail"));
			adminVo.setMhp(rs.getString("mhp"));
			adminVo.setMselect1(rs.getString("mselect1"));
			adminVo.setMselect2(rs.getString("mselect2"));
			adminVo.setMcheck(rs.getString("mcheck"));
			adminVo.setMdate(rs.getString("mdate"));
			adminVo.setMcount(rs.getInt("mcount"));

			return adminVo;

		} catch (Exception e) {
			System.out.println("SQL 문법 오류 발생!!");
			throw e;

		} finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}
	
	
	public AdminVo getAdmin(int midx) throws SQLException {
		try {
			String sql = "select * from admin where midx = ?";

			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, midx);
			this.rs = this.ps.executeQuery();

			AdminVo adminVo = new AdminVo();
			this.rs.next();
			adminVo.setMidx(rs.getInt("midx"));
			adminVo.setMid(rs.getString("mid"));
			adminVo.setMpass(rs.getString("mpass"));
			adminVo.setMname(rs.getString("mname"));
			adminVo.setMemail(rs.getString("memail"));
			adminVo.setMhp(rs.getString("mhp"));
			adminVo.setMselect1(rs.getString("mselect1"));
			adminVo.setMselect2(rs.getString("mselect2"));
			adminVo.setMcheck(rs.getString("mcheck"));
			adminVo.setMdate(rs.getString("mdate"));
			adminVo.setMcount(rs.getInt("mcount"));

			return adminVo;

		} catch (Exception e) {
			System.out.println("SQL 문법 오류 발생!!");
			throw e;

		} finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}

	public List<AdminVo> getAdminAll() throws SQLException {

		try {
			String sql = "select * from admin order by midx desc";

			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();

			List<AdminVo> adminVoList = new ArrayList<>();

			while (this.rs.next()) {
				AdminVo adminVo = new AdminVo();
				adminVo.setMidx(rs.getInt("midx"));
				adminVo.setMid(rs.getString("mid"));
				adminVo.setMpass(rs.getString("mpass"));
				adminVo.setMname(rs.getString("mname"));
				adminVo.setMemail(rs.getString("memail"));
				adminVo.setMhp(rs.getString("mhp"));
				adminVo.setMselect1(rs.getString("mselect1"));
				adminVo.setMselect2(rs.getString("mselect2"));
				adminVo.setMcheck(rs.getString("mcheck"));
				adminVo.setMdate(rs.getString("mdate"));
				adminVo.setMcount(rs.getInt("mcount"));
				adminVoList.add(adminVo);
			}

			return adminVoList;

		} catch (Exception e) {
			System.out.println("SQL 문법 오류 발생!!");
			throw e;

		} finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}

	public member_dto getsiteinfo() throws SQLException {
		try {
			String sql = "SELECT * FROM siteinfo limit 1";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();

			member_dto member_dto = new member_dto();
			if (this.rs.next()) {
				member_dto.setSidx(rs.getInt("sidx"));
				member_dto.setStitle(rs.getString("stitle"));
				member_dto.setSemail1(rs.getString("semail1"));
				member_dto.setSpoint1(rs.getString("spoint1"));
				member_dto.setSreward(rs.getInt("sreward"));
				member_dto.setSlevel(rs.getInt("slevel"));
				member_dto.setScompany(rs.getString("scompany"));
				member_dto.setScompany_number(rs.getString("scompany_number"));
				member_dto.setSname(rs.getString("sname"));
				member_dto.setStel(rs.getString("stel"));
				member_dto.setSnumber1(rs.getString("snumber1"));
				member_dto.setSnumber2(rs.getString("snumber2"));
				member_dto.setSnumber3(rs.getString("snumber3"));
				member_dto.setSaddress(rs.getString("saddress"));
				member_dto.setSname2(rs.getString("sname2"));
				member_dto.setSbank(rs.getString("sbank"));
				member_dto.setSnumber4(rs.getString("snumber4"));
				member_dto.setScard(rs.getString("scard"));
				member_dto.setShp(rs.getString("shp"));
				member_dto.setSgift(rs.getString("sgift"));
				member_dto.setSpoint2(rs.getInt("spoint2"));
				member_dto.setSpoint3(rs.getInt("spoint3"));
				member_dto.setSreceipt(rs.getString("sreceipt"));
				member_dto.setSdate(rs.getString("sdate"));
				member_dto.setSemail2(rs.getString("semail2"));

			} else {
				return null;
			}

			return member_dto;
		} catch (Exception e) {
			System.out.println("SQL 문법 오류 발생!!");
			throw e;
		} finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}



	public member_dto getFooter() throws SQLException {
		try {
			String sql = "SELECT scompany,scompany_number,sname,stel,snumber1,snumber2,snumber3,saddress,sname2,semail2 FROM siteinfo limit 1";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();

			member_dto member_dto = new member_dto();
			if (this.rs.next()) {
				member_dto.setScompany(rs.getString("scompany"));
				member_dto.setScompany_number(rs.getString("scompany_number"));
				member_dto.setSname(rs.getString("sname"));
				member_dto.setStel(rs.getString("stel"));
				member_dto.setSnumber1(rs.getString("snumber1"));
				member_dto.setSnumber2(rs.getString("snumber2"));
				member_dto.setSnumber3(rs.getString("snumber3"));
				member_dto.setSaddress(rs.getString("saddress"));
				member_dto.setSname2(rs.getString("sname2"));
				member_dto.setSemail2(rs.getString("semail2"));
			} else {
				return null;
			}

			return member_dto;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQL 문법 오류 발생!!");
			throw e;
		} finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}

	public boolean idcheck(String mid) {
		try {

			String sql = "select count(*) as ctn from admin where mid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, mid);
			this.rs = this.ps.executeQuery();

			rs.next();

			boolean isDuplicated = rs.getInt("ctn") != 0;
			ps.close();
			con.close();

			return isDuplicated;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

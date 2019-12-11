package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.db.DbClose;
import com.db.DbConn;
import com.entity.SalesMan;

/**
 * 数据库SalesMan表操作
 */
public final class SalesManDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 1.前台收银登陆
	 * 
	 * @param sName 用户名
	 * @return ArrayList<SalesMan> sPassWord,sId
	 */
	public ArrayList<SalesMan> checkstandLog(String sName) {
		// TODO Auto-generated method stub
		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "select sId,sPassWord from salesMan where Sname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String spassWord = rs.getString("spassword");
				int sId = rs.getInt("sId");
				SalesMan salesMan = new SalesMan(sId, spassWord);
				salesManInfo.add(salesMan);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesManInfo;
	}

	/**
	 * 2.添加售货员
	 * 
	 * @param sName 用户名
	 * @return boolean
	 */
	public boolean addSalesMan(SalesMan sName) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "insert into salesan(sName,spassWord) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName.getSName());
			pstmt.setString(2, sName.getSPassWord());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}

	/**
	 * 3.更改售货员信息
	 * 
	 * @param key   要更改项
	 * @param sName 用户名
	 * @return boolean
	 */
	public boolean updateSalesMan(int key, SalesMan saName) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		switch (key) {
		case 1:// 3.1 更改售货员姓名
			String sqlName = "update salesMan set sName=? where sId=?";
			try {
				pstmt = conn.prepareStatement(sqlName);
				pstmt.setString(1, saName.getSName());
				pstmt.setInt(2, saName.getSId());
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					bool = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DbClose.addClose(pstmt, conn);
			}
			break;
		case 2:// 3.2 更改售货员密码
			String sqlprice = "update salesMan set sPassWord=? where sId=?";
			try {
				pstmt = conn.prepareStatement(sqlprice);
				pstmt.setString(1, saName.getSName());
				pstmt.setInt(2, saName.getSId());
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					bool = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				DbClose.addClose(pstmt, conn);
			}
			break;

		default:
			break;
		}
		return bool;
	}

	/**
	 * 4.删除售货员
	 * 
	 * @param sName 用户名
	 * @return boolean
	 */
	public boolean deleteSalesMan(String sName) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "delete from salesMan where sName=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}

	/**
	 * 5.模糊查询售货员
	 * 
	 * @param sName 用户名
	 * @return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> querySalesMan(String sName) {
		// TODO Auto-generated method stub
		ArrayList<SalesMan> salesMansList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		sName = "%" + sName + "%";
		// 从用户处获取的字符串加上 % 符号，来达到模糊查询的目的.
		// 字符串 的连接还有更优秀的方式，待优化代码！
		String sql = "select * from salesMan where sNAme like ?";
		// 居然不能直接跟 % .只能用连接字符串的方式
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sId = rs.getInt("sid");
				String sname = rs.getString(2);
				String sPassWord = rs.getString(3);
				SalesMan salesMan = new SalesMan(sId, sname, sPassWord);
				salesMansList.add(salesMan);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesMansList;
	}

	/**
	 * 6.显示所有售货员
	 * 
	 * @return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> displaySalesMan() {
		// TODO Auto-generated method stub
		ArrayList<SalesMan> salesMansList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "Select * from saleaMan";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sId = rs.getInt(1);
				String sname = rs.getString(2);
				String sPassWord = rs.getString(3);
				SalesMan salesMan = new SalesMan(sId, sname, sPassWord);
				salesMansList.add(salesMan);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesMansList;
	}

}

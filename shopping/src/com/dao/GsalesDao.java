package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.DbClose;
import com.db.DbConn;
import com.entity.Gsales;

/**
 * 数据库gSales表操作
 */
public final class GsalesDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 1.当天卖出的商品
	 * 
	 * @return ArrayList<Gsales> 商品信息,包括 allSum (单种商品的销售总和)
	 */
	public ArrayList<Gsales> dailyGsales() {
		// TODO Auto-generated method stub
		ArrayList<Gsales> Gsaleslist = new ArrayList<Gsales>();
		conn = DbConn.getconn();
		// 售卖时间=当前时间 trunc(sdate) =trunc(sysdate) 单位：天
		// sql语句解释见files/sql/java_sql.sql
		String sql = "select gname,gprice,gnum,allSum from goods," + "(select gid as salsaid,sum(snum) as allSum "
				+ "from gsales where trunc(sdate)=trunc(sysdate) " + "group by gid) where gid=salesid";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String gName = rs.getString(1);
				double gPrice = rs.getDouble(2);
				int gNum = rs.getInt(3);
				int allSum = rs.getInt("allSum");
				Gsales gsales = new Gsales(gName, gPrice, gNum, allSum);
				Gsaleslist.add(gsales);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return Gsaleslist;
	}

	/**
	 * 2.购物结算-向sales表中插入商品数据！
	 * 
	 * @param gSales 售卖商品对象
	 * @return boolean
	 */
	public boolean shoppingSettlement(Gsales gSales) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "insert into gsales(gId,sId,snum) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gSales.getgId());
			pstmt.setInt(2, gSales.getsId());
			pstmt.setInt(3, gSales.getsNum());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}

}

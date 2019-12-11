package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.db.DbClose;
import com.db.DbConn;
import com.entity.SalesMan;

/**
 * ���ݿ�SalesMan�����
 */
public final class SalesManDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 1.ǰ̨������½
	 * 
	 * @param sName �û���
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
	 * 2.����ۻ�Ա
	 * 
	 * @param sName �û���
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
	 * 3.�����ۻ�Ա��Ϣ
	 * 
	 * @param key   Ҫ������
	 * @param sName �û���
	 * @return boolean
	 */
	public boolean updateSalesMan(int key, SalesMan saName) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		switch (key) {
		case 1:// 3.1 �����ۻ�Ա����
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
		case 2:// 3.2 �����ۻ�Ա����
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
	 * 4.ɾ���ۻ�Ա
	 * 
	 * @param sName �û���
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
	 * 5.ģ����ѯ�ۻ�Ա
	 * 
	 * @param sName �û���
	 * @return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> querySalesMan(String sName) {
		// TODO Auto-generated method stub
		ArrayList<SalesMan> salesMansList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		sName = "%" + sName + "%";
		// ���û�����ȡ���ַ������� % ���ţ����ﵽģ����ѯ��Ŀ��.
		// �ַ��� �����ӻ��и�����ķ�ʽ�����Ż����룡
		String sql = "select * from salesMan where sNAme like ?";
		// ��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
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
	 * 6.��ʾ�����ۻ�Ա
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

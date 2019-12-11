package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.DbClose;
import com.db.DbConn;
import com.entity.Goods;
import com.tools.ScannerChoice;

/**
 * ���ݿ�goods�����
 */
public class GoodsDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 1.�����Ʒ�����ݿ�goods��
	 * 
	 * @param goods ��Ʒ����
	 * @return boolean
	 */
	public boolean addGoods(Goods goods) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "insert into Goods(gname,gprice,gnum) values(?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGname());
			pstmt.setDouble(2, goods.getGprice());
			pstmt.setInt(3, goods.getGnum());
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}

	/**
	 * 2.������Ʒ��Ϣ�����ݿ�goods��
	 * 
	 * @param key   ѡ��Ҫ������Ʒ��Ϣ
	 * @param goods ��Ʒ����
	 * @return boolean
	 */
	public boolean updateGoods(int key, Goods goods) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		switch (key) {
		case 1:// key=1,������Ʒ����
			String sqlName = "update goods set gname=? where gid=?";

			try {
				pstmt = conn.prepareStatement(sqlName);
				pstmt.setString(1, goods.getGname());
				pstmt.setInt(2, goods.getGid());
				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					bool = true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbClose.addClose(pstmt, conn);
			}
			break;
		case 2:// key=2,������Ʒ�۸�
			String sqlPrice = "update goods set gprice=? where gid=?";
			try {
				pstmt = conn.prepareStatement(sqlPrice);
				pstmt.setDouble(1, goods.getGprice());
				pstmt.setInt(2, goods.getGid());

				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.addClose(pstmt, conn);
			}
			break;
		case 3:// key=3,������Ʒ����
			String sqlNum = "update goods set gnum=? where gid=?";
			try {
				pstmt = conn.prepareStatement(sqlNum);
				pstmt.setInt(1, goods.getGnum());
				pstmt.setInt(2, goods.getGid());

				int rs = pstmt.executeUpdate();
				if (rs > 0) {
					bool = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
	 * 3.�����ݿ�goods����-�h����Ʒ
	 * 
	 * @param gid ��Ʒ���
	 * @return boolean
	 */
	public boolean deleteGoods(int gid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "delete from goods where gid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.addClose(pstmt, conn);
		}

		return bool;
	}

	/**
	 * 4.��ѯ��Ʒ��Ϣ
	 * 
	 * @param key ��ѯ��ʽ
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> queryGoods(int key) {
		// TODO Auto-generated method stub
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConn.getconn();
		switch (key) {
		case 1:// key=1��Ʒ ���� �����ѯ
			String sqlGnum = "select * from goods order by gnum asc";
			try {
				pstmt = conn.prepareStatement(sqlGnum);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int gid = rs.getInt("gid");
					String gname = rs.getString(2);
					double gprice = rs.getDouble(3);
					int gnum = rs.getInt(4);

					Goods goods = new Goods(gid, gname, gprice, gnum);
					goodsList.add(goods);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pstmt, rs, conn);
			}
			break;
		case 2:// key=2��Ʒ �۸� �����ѯ
			String sqlGprice = "select * from goods order by gprice asc";
			try {
				pstmt = conn.prepareStatement(sqlGprice);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int gid = rs.getInt("gid");
					String gname = rs.getString(2);
					double gprice = rs.getDouble(3);
					int gnum = rs.getInt(4);

					Goods goods = new Goods(gid, gname, gprice, gnum);
					goodsList.add(goods);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pstmt, rs, conn);
			}
			break;
		case 3:// key=3��Ʒ �ؼ��� ��ѯ
			String nameGet = ScannerChoice.ScannerInfoString();
			String sqlGname = "select * from goods where gname like '%'||?||'%'";
			try {
				pstmt = conn.prepareStatement(sqlGname);
				pstmt.setString(1, nameGet);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int gid = rs.getInt("gid");
					String gname = rs.getString(2);
					double gprice = rs.getDouble(3);
					int gnum = rs.getInt(4);

					Goods goods = new Goods(gid, gname, gprice, gnum);
					goodsList.add(goods);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbClose.queryClose(pstmt, rs, conn);
			}
			break;
		default:
			break;
		}
		return goodsList;
	}

	/**
	 * 5.��ʾ������Ʒ��Ϣ
	 * 
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> displayGoods() {
		// TODO Auto-generated method stub
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConn.getconn();
		String sql = "select * from goods";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int gid = rs.getInt("gid");
				String gname = rs.getString(2);
				double gprice = rs.getDouble("gprice");// ˫����+������,Ҳ�������ֱ�ʾ.
				int gnum = rs.getInt(4);

				Goods goods = new Goods(gid, gname, gprice, gnum);// ����Goods���󣬲���ֵ.
				goodsList.add(goods); // �����Ϣ����̬������.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return goodsList;
	}

}

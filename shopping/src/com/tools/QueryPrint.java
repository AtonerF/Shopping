package com.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.GoodsDao;
import com.db.DbClose;
import com.db.DbConn;
import com.entity.Goods;
import com.entity.SalesMan;

/**
 * ��ѯ&&��ӡ ��������(�����Ż����ܻ�ɾ)
 */
public class QueryPrint {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * ģ����ѯ�����в�ѯ��Ϣ����С����
	 * 
	 * @param oper ������
	 * @return ��ѯ������Ϣ��gid,�������ֵ����-1��������ѯ�쳣��
	 */
	public static int quert(String oper) {
		// TODO Auto-generated method stub
		int gid = -1;
		String shopping = ScannerChoice.ScannerInfoString();// ���̻�ȡ��Ʒ����
		ArrayList<Goods> goodslist = new QueryPrint().queryGoodsKey(-1, shopping);
		// ���ݼ��̻�ȡ����Ʒ�����{�� ��ȷ��ѯ�������_���Ñ���Ҫ����������
		if (goodslist == null || goodslist.size() <= 0) {
			System.err.println("\t�������޴���Ʒ ����");
			// �{��ѡ����һ������
			ScannerChoice.choicesalesManNext(oper);
		} else// �鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������

		{
			Goods goods = goodslist.get(0);
			System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
			System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
			System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t"
					+ goods.getGnum());
			if (goods.getGnum() == 0) {
				System.out.println("\t\t����Ʒ���ۿ�");
			} else if (goods.getGnum() < 10) {
				System.out.println("\t\t����Ʒ�Ѳ���10��");
			} else {
				System.out.println("\t\t-");
			}
			gid = goods.getGid(); // ����Ʒ��ŷ��ظ�������
		}
		return gid;
	}

	/**
	 * ģ����ѯ����С����
	 * 
	 * @return int ����Ʒ��������ֻ��һ��ʱ������Ʒgid�ţ���Ʒ���ۿ�ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
	 * 
	 */
	public static int querySettlement() {
		int gid = -1;
		ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);// �{�� �ؼ��ֲ�ѯ����
		if (goodsSettlement == null || goodsSettlement.size() <= 0) {
			System.err.println("\t�������޴���Ʒ ����\n");
			gid = -3;
		} else // �鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������
		{
			System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
			System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
			for (int i = 0; i < goodsSettlement.size(); i++) {
				Goods goods = goodsSettlement.get(i);
				if (goods.getGnum() > 0) {
					System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice()
							+ "\t\t" + goods.getGnum());
					if (goods.getGnum() == 0) {
						System.out.println("\t\t����Ʒ���ۿ�");
					} else if (goods.getGnum() < 10) {
						System.out.println("\t\t����Ʒ�Ѳ���10��");
					} else {
						System.out.println("\t\t-");
					}
					if (goodsSettlement.size() == 1) {
						gid = goods.getGid();// ����Ʒ��ŷ��ظ�������
					} else {
						gid = -2;
					}
				}
			}
		}
		return gid;
	}

	/**
	 * ������Ʒ gid or gName��ѯ��Ʒ
	 * 
	 * @param ��Ʒid,��Ʒ����
	 * @return ��Ʒ��Ϣ
	 */
	public ArrayList<Goods> queryGoodsKey(int gId, String gName) {
		// TODO Auto-generated method stub
		ArrayList<Goods> goodsLixt = new ArrayList<Goods>();
		conn = DbConn.getconn();
		String sql = "select * from Goods where gid=? or gnum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gId);
			pstmt.setString(2, gName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int gid = rs.getInt("gid");
				String gname = rs.getString(2);
				double gprice = rs.getDouble(3);
				int gnum = rs.getInt(4);

				Goods goods = new Goods(gid, gname, gprice, gnum);
				goodsLixt.add(goods);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return goodsLixt;
	}

	/**
	 * ��ȷ��ѯ�ۻ�Ա��Ϣ
	 * 
	 * @param �ۻ�Ա����
	 * @return
	 */
	public ArrayList<SalesMan> queSalesMan(String sName) {
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "select * from SalesMan where sName";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sId = rs.getInt("sid");
				String sname = rs.getString(2);
				String sPassWord = rs.getString(3);

				SalesMan salesMan = new SalesMan(sId, sname, sPassWord);
				salesManList.add(salesMan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesManList;

	}

}

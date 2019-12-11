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
 * 查询&&打印 函数工具(后期优化可能会删)
 */
public class QueryPrint {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 模糊查询并陈列查询信息函数小工具
	 * 
	 * @param oper 调用者
	 * @return 查询到的信息的gid,如果返回值等于-1，则代表查询异常。
	 */
	public static int quert(String oper) {
		// TODO Auto-generated method stub
		int gid = -1;
		String shopping = ScannerChoice.ScannerInfoString();// 键盘获取商品名字
		ArrayList<Goods> goodslist = new QueryPrint().queryGoodsKey(-1, shopping);
		// 根据键盘获取的商品名字調用 精确查询函数，確定用戶所要操作的数据
		if (goodslist == null || goodslist.size() <= 0) {
			System.err.println("\t！！查无此商品 ！！");
			// 調用选择下一步函数
			ScannerChoice.choicesalesManNext(oper);
		} else// 查到有此商品，实现进行 更改商品 信息操作！

		{
			Goods goods = goodslist.get(0);
			System.out.println("\t\t\t\t\t商品列表\n\n");
			System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
			System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t"
					+ goods.getGnum());
			if (goods.getGnum() == 0) {
				System.out.println("\t\t该商品已售空");
			} else if (goods.getGnum() < 10) {
				System.out.println("\t\t该商品已不足10件");
			} else {
				System.out.println("\t\t-");
			}
			gid = goods.getGid(); // 将商品编号返回给调用者
		}
		return gid;
	}

	/**
	 * 模糊查询函数小工具
	 * 
	 * @return int 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
	 * 
	 */
	public static int querySettlement() {
		int gid = -1;
		ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);// 調用 关键字查询函数
		if (goodsSettlement == null || goodsSettlement.size() <= 0) {
			System.err.println("\t！！查无此商品 ！！\n");
			gid = -3;
		} else // 查到有此商品，实现进行 更改商品 信息操作！
		{
			System.out.println("\t\t\t\t\t商品列表\n\n");
			System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
			for (int i = 0; i < goodsSettlement.size(); i++) {
				Goods goods = goodsSettlement.get(i);
				if (goods.getGnum() > 0) {
					System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t" + goods.getGprice()
							+ "\t\t" + goods.getGnum());
					if (goods.getGnum() == 0) {
						System.out.println("\t\t该商品已售空");
					} else if (goods.getGnum() < 10) {
						System.out.println("\t\t该商品已不足10件");
					} else {
						System.out.println("\t\t-");
					}
					if (goodsSettlement.size() == 1) {
						gid = goods.getGid();// 将商品编号返回给调用者
					} else {
						gid = -2;
					}
				}
			}
		}
		return gid;
	}

	/**
	 * 根据商品 gid or gName查询商品
	 * 
	 * @param 商品id,商品名称
	 * @return 商品信息
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
	 * 精确查询售货员信息
	 * 
	 * @param 售货员名字
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

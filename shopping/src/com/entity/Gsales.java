package com.entity;

/**
 * Gsales g购物结算实体类
 * 
 * @author
 */
public class Gsales {

	private int gId;
	private int sId;
	private int sNum;

	private String gName;
	private double gPrice;
	private int gNum;
	private int allSnum; // 单种商品销量总和

	/**
	 * 购物结算
	 * 
	 * @param gId,sId,sNum
	 */
	public Gsales(int gId, int sId, int sNum) {
		this.gId = gId;
		this.sId = sId;
		this.sNum = sNum;
	}

	/**
	 * 展现商品列表
	 * 
	 * @param gName,gPrice,gNum,allSnum
	 */
	public Gsales(String gName, double gPrice, int gNum, int allSnum) {
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
		this.allSnum = allSnum;
	}
	// 共有set、get

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public int getsNum() {
		return sNum;
	}

	public void setsNum(int sNum) {
		this.sNum = sNum;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public double getgPrice() {
		return gPrice;
	}

	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}

	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	public int getAllSnum() {
		return allSnum;
	}

	public void setAllSnum(int allSnum) {
		this.allSnum = allSnum;
	}

}

package com.page;

import java.util.ArrayList;

import com.dao.SalesManDao;
import com.entity.SalesMan;
import com.tools.QueryPrint;
import com.tools.ScannerChoice;

/**
 * 操作售货员界面
 */
public final class SalesManPage extends ScannerChoice {
	/**
	 * 1.添加售货员界面 已实现！
	 */
	public static void addSalesManPage() {
		// TODO Auto-generated method stub
		System.out.println("\t正在执行添加售货员操作\n");
		System.out.println("\n添加售货员-姓名");
		String sName = ScannerInfoString();
		System.out.println("\n添加售货员-密码");
		String sPssswd = ScannerInfoString();
		SalesMan salesMan = new SalesMan(sName, sPssswd);
		boolean bool = new SalesManDao().addSalesMan(salesMan);
		if (bool) {
			System.out.println("\n\t!您已成功添加售货员到数据库!");
		} else {
			System.out.println("添加售货员失败");
		}
		choicesalesManNext("addSalesMan");
	}

	/**
	 * 2.更改售货员界面
	 */
	public static void updateSalesManPage() {
		// TODO Auto-generated method stub
		System.out.println("\t正在执行更改售货员操作\n");
		System.out.println("请输入想要更改的售货员名字");
		String sName = ScannerInfoString();

		// 调用精确查找售货员函数
		ArrayList<SalesMan> salesManList = new QueryPrint().queSalesMan(sName);
		if (salesManList.size() <= 0) {
			System.err.println("\t！！查无此人！！");
			choicesalesManNext("updateSalesMan");
		} else {
			// 显示将要更改的售货员信息
			System.out.println("\t\t\t售货员信息\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

			SalesMan salesMan = salesManList.get(0); // 上面的精确查找中，只能返回一组数值。无需遍历！
			System.out.println(
					"\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
			System.out.println();

			// 选择更改售货员内容
			System.out.println("\n--------请选择您要更改的内容\n");
			System.out.println("\t1.更改售货员-姓名");
			System.out.println("\t2.更改售货员-密码");
			do {
				String choice = ScannerInfoString();
				String regex = "[0-2]";
				if (choice.matches(regex)) {
					int info = Integer.parseInt(choice);
					switch (info) {
					case 0:
						MainPage.salesManManagementPage();
						break;
					case 1:
						System.out.println("更改售货员-新姓名");
						String sNewName = ScannerInfoString();
						SalesMan salesManName = new SalesMan(salesMan.getSId(), sNewName, null);
						boolean boolsName = new SalesManDao().updateSalesMan(1, salesManName);
						if (boolsName) {
							System.out.println("\n\t！！成功更新售货员名字至数据库！！\n");
						} else {
							System.err.println("\n\t！！更新售货员名字失敗！！");
						}
						choicesalesManNext("updateSalesMan");
						break;
					case 2:
						System.out.println("更改售货员-新密码");
						String sNewPasswd = ScannerInfoString();

						SalesMan salesManPasswd = new SalesMan(salesMan.getSId(), null, sNewPasswd);
						boolean boolsPasswd = new SalesManDao().updateSalesMan(2, salesManPasswd);

						if (boolsPasswd) {
							System.out.println("\n\t！！成功更新售货员密码至数据库！！\n");
						} else {
							System.err.println("\n\t！！更新售货员密码失敗！！");
						}
						choicesalesManNext("updateSalesMan");
						break;
					default:
						break;
					}
				}
				System.out.println("\t!输入有误!");
				System.out.println("\n请选择选项.或者按 0 返回上一级菜单.");
			} while (true);
		}
	}

	/**
	 * 3.删除售货员界面
	 */
	public static void deleteSalesManPage() {
		// TODO Auto-generated method stub
		System.out.println("\t正在执行 删除售货员 操作\n");
		System.out.println("请输入想要删除的售货员名字");
		String sName = ScannerInfoString();

		// 调用精确查找售货员函数
		ArrayList<SalesMan> salesManList = new QueryPrint().queSalesMan(sName);
		if (salesManList.size() <= 0) {
			System.err.println("\t！！查无此人！！");
			choicesalesManNext("deleteSalesMan");
		} else {
			// 显示将要删除的售货员信息
			System.out.println("\t\t\t删除售货员信息\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			for (int i = 0, length = salesManList.size(); i < length; i++) {
				SalesMan salesMan = salesManList.get(i);
				System.out.println(
						"\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
				System.out.println();
			}
			// 确认是否真的删除！
			do {
				System.out.println("\n确认删除该售货员：Y/N");
				String choice = ScannerInfoString();
				if ("y".equals(choice) || "Y".equals(choice)) {
					// 进行刪除-数据库操作
					boolean boolDeleteSalsMan = new SalesManDao().deleteSalesMan(sName);// 調用刪除功能
					if (boolDeleteSalsMan) {
						System.err.println("\t！！已成功刪除该售货员！！\n");
					} else {
						System.err.println("\t！！刪除该售货员失敗！！");
					}
					choicesalesManNext("deleteGoods");
				} else if ("N".equals(choice) || "n".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				System.err.println("\t!!输入有误,请重新输入!!");
			} while (true);
		}
	}

	/**
	 * 4.查询售货员界面 已实现！
	 */
	public static void querySalesManPage() {
		// TODO Auto-generated method stub
		System.out.println("\t\t  正在执行查询售货员操作\n");
		System.out.println("要查询的售货员关键字");
		String sName = ScannerInfoString();
		ArrayList<SalesMan> salesManList = new SalesManDao().querySalesMan(sName);
		if (salesManList.size() <= 0) {
			System.err.println("\t！没有人员符合查询条件！");
		} else {
			System.out.println("\t\t\t所有售货员列表\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			for (int i = 0, length = salesManList.size(); i < length; i++) {
				SalesMan salesMan = salesManList.get(i);
				System.out.println(
						"\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
				System.out.println();
			}
		}
		choicesalesManNext("querySalesMan"); // param：调用者
	}

	/**
	 * 5.显示所有售货员界面
	 */
	public static void displaySalesManPage() {
		// TODO Auto-generated method stub
		ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
		if (salesManList.size() <= 0) {
			System.err.println("\t！！售货员列表为空！！");
			MainPage.salesManManagementPage();
		} else {
			System.out.println("\t\t\t所有售货员列表\n\n");
			System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
			for (int i = 0, length = salesManList.size(); i < length; i++) {
				SalesMan salesMan = salesManList.get(i);
				System.out.println(
						"\t" + salesMan.getSId() + "\t\t\t" + salesMan.getSName() + "\t\t\t" + salesMan.getSPassWord());
				System.out.println();
			}
			do {
				System.out.println("\n\n输入 0 返回上一级菜单");
				String choice = ScannerInfoString();
				if ("0".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				System.err.print("\t输入有误！");
			} while (true);
		}
	}

}

package com.page;

import java.util.ArrayList;

import com.dao.GsalesDao;
import com.entity.Gsales;
import com.tools.ScannerChoice;

/**
 * ����������Ʒ�б����
 */
public final class GsalesPage {

	public static void dailySaleGoodsPage() {
		// TODO Auto-generated method stub
		System.out.println("\t����ִ���г������۳���Ʒ�б����\n");
		ArrayList<Gsales> GsalesList = new GsalesDao().dailyGsales();// �����۳���Ʒ���鼯
		if (GsalesList.size() <= 0) {
			System.err.println("\t������������Ʒ�۳�����");
			MainPage.commodityManagementPage();
		} else {
			System.out.println("\t\t\t\t�����۳���Ʒ�б�\n");
			System.out.println("\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t����\t\t��ע\n");
			for (int i = 0, length = GsalesList.size(); i < length; i++) {
				// ��ȡ�۳���Ʒ��gname,gprice,gnum, allSum (������Ʒ�������ܺ�)
				Gsales gSales = GsalesList.get(i);
				System.out.print("\t" + gSales.getgName() + "\t\t" + gSales.getgPrice() + " $\t\t" + gSales.getgNum()
						+ "\t\t" + gSales.getAllSnum());
				int gNum = gSales.getgNum();
				if (gNum == 0) {

					System.out.println("\t\t����Ʒ���ۿ�");
				} else if (gNum < 10) {
					System.out.println("\t\t����Ʒ�Ѳ���10��");
				} else {
					System.out.println("\t\t-");
				}
				System.out.println("\t");
			}
			do {
				System.out.println("\n\n���� 0 ������һ���˵�");
				String choice = ScannerChoice.ScannerInfoString();
				if ("0".equals(choice)) {
					MainPage.salesManManagementPage();
				}
				MainPage.commodityManagementPage();
			} while (true);
		}
	}
}

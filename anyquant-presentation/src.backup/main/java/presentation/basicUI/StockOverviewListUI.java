package presentation.basicUI;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.Accordion;
import presentation.tableUI.SortComponent;
import presentation.tableUI.TableModel;
import vo.ShareVO;

public class StockOverviewListUI extends JLayeredPane {
	
	private static final long serialVersionUID = -522002009353842922L;
	/**
	 * 表格起始位置
	 */
	private Point p = new Point(Start.distance, 2 * Start.min_close);
	/**
	 * tablepane放置表格 operationpane
	 */
	private JPanel tablepane, operationpane;

	public StockOverviewListUI(MainUIService m) {
		this.setLayout(null);
		this.setBounds(0, 4 * Start.min_close, Start.width - Start.sideWidth,
				Start.hight - Start.min_close - 4 * Start.min_close);
		this.setOpaque(false);

		tablepane = new JPanel();
		tablepane.setLayout(null);
		tablepane.setSize(Start.width - Start.sideWidth, Start.hight - Start.min_close - 4 * Start.min_close);
		this.add(tablepane, 0);
		tablepane.setBackground(Start.color);
		this.moveToBack(tablepane);

		operationpane = new JPanel();
		operationpane.setLayout(null);
		operationpane.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		operationpane.setOpaque(false);
		this.add(operationpane, new Integer(100));

		// 初始化数�?
		String[] title = { "代码", "名称", "�?盘价", "�?高价", "�?低价", "收盘�?", "成交�?" };
		int[] width = new int[7];
		for (int i = 0; i < 7; i++) {
			if (i == 0)
				width[i] = (int) Math.floor((Start.table_normal_width_7 * (1.0 / 7)));
			else if (i == 1)
				width[i] = (int) Math.floor((Start.table_normal_width_7 * (1.0 / 7)));
			else
				width[i] = (int) Math.floor((Start.table_normal_width_7 * (1.0 / 7)));
		}
		GetShareBLService share = new Share("open+close+high+low+volume");
		ArrayList<ShareVO> list = share.getInfoList("sh");

		// 绘制表格
		TableModel tableModel = new TableModel(tablepane, p, "shareOverview", m, list);
		tableModel.init(title, width, Start.table_height, list);
		// 过滤
		new Accordion(operationpane, this, tableModel, width, title, 1,null);
		// 排序
		String[] options = { "�?近日�?", "�?盘价排行", "收盘价排�?", "�?高价排行", "�?低价排行", "成交量排�?" };
		new SortComponent(operationpane, tableModel, width, title,options);
		this.updateUI();
	}
}

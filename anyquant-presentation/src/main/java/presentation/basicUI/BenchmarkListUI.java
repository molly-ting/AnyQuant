package presentation.basicUI;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import blService.getInfoBLService.GetBenchmarkBLService;
import businessLogic.getInfoBL.Benchmark;
import businessLogic.sortBL.Sort;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.Accordion;
import presentation.tableUI.SortComponent;
import presentation.tableUI.TableModel;
import vo.ShareVO;
import vo.StrategyVO;

public class BenchmarkListUI extends JLayeredPane {

	private static final long serialVersionUID = -6658000176092803534L;
	/**
	 * 起始位置
	 */
	private Point p = new Point(Start.distance, 2 * Start.min_close);
	/**
	 * tablepane放置表格 operationpane
	 */
	private JPanel tablepane, operationpane;

	public BenchmarkListUI(MainUIService m) {
		this.setLayout(null);
		this.setBounds(0, 4 * Start.min_close, Start.width - Start.sideWidth,
				Start.hight - Start.min_close - 4 * Start.min_close);
		this.setOpaque(false);

		tablepane = new JPanel();
		tablepane.setLayout(null);
		tablepane.setSize(Start.width - Start.sideWidth, Start.hight - Start.min_close - 4 * Start.min_close);
		tablepane.setBackground(Start.color);
		this.add(tablepane, 0);
		this.moveToBack(tablepane);

		operationpane = new JPanel();
		operationpane.setLayout(null);
		operationpane.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		operationpane.setOpaque(false);
		this.add(operationpane, new Integer(100));

		// 初始化数据
		String[] title = { "日期", "开盘价", "最高价", "最低价", "收盘价", "成交量" };
		int[] width = new int[6];
		for (int i = 0; i < 6; i++) {
			if (i == 0)
				width[i] = (int) Math.floor((Start.table_normal_width_6 * (3.0 / 14)));
			else if (i == 5)
				width[i] = (int) Math.floor((Start.table_normal_width_6 * (3.0 / 14)));
			else
				width[i] = (int) Math.floor((Start.table_normal_width_6 * (1.0 / 7)));
		}
		GetBenchmarkBLService benchmark = new Benchmark();
		ArrayList<ShareVO> list = benchmark.getList("open+close+high+low+volume");
		Sort sort = new Sort();
		ArrayList<ShareVO> sortList = sort.sort(new StrategyVO("date"), list);

		// 绘制表格
		TableModel tableModel = new TableModel(tablepane, p, "benchmark", m, sortList);
		tableModel.init(title, width, Start.table_height, list);

		// 过滤
		new Accordion(operationpane, this, tableModel, width, title, 0, "sh000300");
		// 排序
		String[] options = { "最近日期", "开盘价排行", "收盘价排行", "最高价排行", "最低价排行", "成交量排行" };
		new SortComponent(operationpane, tableModel, width, title, options);
		this.updateUI();
	}

}

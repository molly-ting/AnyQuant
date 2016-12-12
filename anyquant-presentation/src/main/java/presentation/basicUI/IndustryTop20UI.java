package presentation.basicUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import blService.IndustriesBLService.GetTopIndustriesBLService;
import businessLogic.industriesBL.GetTopIndustries50;
import crawler.init.BasicCrawlController;
import dataService.industryService.GetTopIndustries;
import dto.IndustryInfoTop50;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.FXpanelBar;
import presentation.tableUI.FxIndustryBar;
import presentation.tableUI.TableModel;
import vo.IndustryInfoTop50VO;
import vo.ShareVO;

public class IndustryTop20UI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel tablepane, notfoundpane;
	private FxIndustryBar barpane;
	private MainUIService m;
	private Point p = new Point(0, 0);

	public IndustryTop20UI(MainUIService m) {
		this.setLayout(null);
		this.setBounds(0, 2 * Start.min_close, Start.width - Start.sideWidth, Start.hight - 3 * Start.min_close);
		this.setBackground(Start.color);
		this.m = m;

		tablepane = new JPanel();
		tablepane.setLayout(null);
		tablepane.setBounds(Start.distance, Start.distance, Start.top_20_width,
				Start.hight - 3 * Start.min_close - Start.distance);
		tablepane.setBackground(Start.color);
		this.add(tablepane);

		String[] title = { "行业名称", "平均股价", "成交量", "涨跌幅度" };
		int[] width = new int[4];
		for (int i = 0; i < 4; i++) {
			width[i] = Start.table_normal_width_4;
		}

		// 从逻辑层获取行业分类数据
		GetTopIndustriesBLService service = new GetTopIndustries50();
		ArrayList<IndustryInfoTop50VO> list = service.geTop50s();

		// 绘制表格
		TableModel tableModel = new TableModel(tablepane, p, "industry", m, null);
		tableModel.init(title, width, Start.table_height, list);

		int graphheight = Start.hight - 3 * Start.min_close - Start.distance;
//		if (list == null) {
//			notfoundpane = new JPanel();
//			notfoundpane.setLayout(null);
//			notfoundpane.setBounds(Start.distance + Start.top_20_width + Start.gap, Start.distance,
//					Start.width - Start.sideWidth - Start.top_20_width - 2 * Start.distance - Start.gap, graphheight);
//			JLabel tip = new JLabel("没 有 统 计 数 据", JLabel.CENTER);
//			tip.setFont(new Font(Start.font_name, Font.PLAIN, Start.font66));
//			tip.setForeground(Color.LIGHT_GRAY);
//			tip.setBounds(0, 0, notfoundpane.getWidth(), notfoundpane.getHeight());
//			notfoundpane.add(tip);
//			this.add(notfoundpane);
//		} else {
//			for (IndustryInfoTop50VO vo : list) {
//				vo.setTotalvolume(vo.getTotalvolume() / (double) (1000 ));
//			}
			
			barpane = new FxIndustryBar(list, "industrytop", "行业","成交量（万手）",
					Start.width - Start.sideWidth - Start.top_20_width - 2 * Start.distance - Start.gap, graphheight);
			barpane.setBounds(Start.distance + Start.top_20_width + Start.gap, Start.distance,
					Start.width - Start.sideWidth - Start.top_20_width - 2 * Start.distance - Start.gap, graphheight);
			this.add(barpane);
//		}

		this.updateUI();
	}

}

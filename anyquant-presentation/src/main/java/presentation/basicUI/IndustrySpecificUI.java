package presentation.basicUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import blService.IndustriesBLService.GetCertainIndustryInfoBLService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import businessLogic.industriesBL.GetCertainIndustryInfo;
import businessLogic.industriesBL.GetTopIndustries50;
import presentation.listener.BoardListener;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.FXpanelLine;
import presentation.tableUI.MyLabel;
import presentation.tableUI.TableModel;
import vo.AverageVO;
import vo.IndustryInfoTop50VO;
import vo.ShareVO;
import vo.SpecificIndustryVO;

public class IndustrySpecificUI extends JPanel {

	private static final long serialVersionUID = -3315689097329243404L;
	private Point p = new Point(0, 0);
	private MyLabel back;
	private MainUIService m;
	private JPanel tablepane;
	private FXpanelLine linepane;

	public IndustrySpecificUI(MainUIService m, String name) {
		this.setLayout(null);
		this.setBounds(0, 0, Start.width - Start.sideWidth, Start.hight - Start.min_close);
		this.setBackground(Start.color);
		this.m = m;

		JLabel label = new JLabel();
		label.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		label.setOpaque(true);
		label.setBackground(Start.dark_color);
		this.add(label);

		JLabel title = new JLabel(name);
		title.setFont(new Font(Start.font_name, Font.PLAIN, Start.font30));
		title.setForeground(Color.WHITE);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBounds((int) (Start.min_close * 1.5), 0, Start.width - 2 * Start.sideWidth,
				(int) (Start.min_close * 1.5));
		label.add(title);

		back = new MyLabel("back", (int) (Start.min_close * 1.5), (int) (Start.min_close * 1.5));
		back.setBounds(0, 0, (int) (Start.min_close * 1.5), (int) (Start.min_close * 1.5));
		back.addMouseListener(new Listener());
		label.add(back);

		tablepane = new JPanel();
		tablepane.setLayout(null);
		tablepane.setBounds(Start.distance, 2 * Start.min_close + Start.distance, Start.specific_width,
				Start.hight - 3 * Start.min_close - Start.distance);
		tablepane.setBackground(Start.color);
		this.add(tablepane);

		String[] tableheader = { "代码", "名称", "开盘价", "收盘价", "成交量" };
		int[] width = new int[5];
		for (int i = 0; i < 5; i++) {
			width[i] = Start.table_normal_width_5;
		}

		GetCertainIndustryInfoBLService certainindustry = new GetCertainIndustryInfo();
		SpecificIndustryVO sharelist = certainindustry.getCertainIndustryInfo(name);

		TableModel tableModel = new TableModel(tablepane, p, "industrydetail-" + name, m, null);
		tableModel.init(tableheader, width, Start.table_height, sharelist.getInfoList());

		ArrayList<AverageVO> datalist = sharelist.getAverageLine();

		int x = Start.distance + Start.specific_width + Start.gap;
		int y = 6 * Start.min_close + Start.distance + Start.gap;
		int graphwidth = Start.width - Start.sideWidth - 2 * Start.distance - Start.specific_width - Start.gap;
		int graphheight = Start.hight - Start.distance - y;

		GetTopIndustries50 all = new GetTopIndustries50();
		ArrayList<IndustryInfoTop50VO> industryList = all.getAll();
		GetShareBLService share = new Share();
		ArrayList<ShareVO> testList = share.getSpecifiedInfo("sh600000", "open");

		if (industryList != null && testList != null) {
			JLabel ave = new JLabel();
			ave.setBounds(x + graphwidth - 4 * Start.min_close + Start.gap, 2 * Start.min_close + Start.distance,
					4 * Start.min_close - Start.gap, 4 * Start.min_close - Start.gap);
			ave.setOpaque(true);
			ave.setBackground(new Color(200, 60, 0));
			ave.addMouseListener(new BoardListener());
			this.add(ave);

			JLabel note = new JLabel();
			note.setBounds(Start.gap, 3 * Start.min_close + Start.gap, 4 * Start.min_close - Start.gap,
					Start.min_close - Start.gap);
			note.setForeground(Color.WHITE);
			note.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			note.setText("总成交量");
			ave.add(note);

			int wide = graphwidth - 4 * Start.min_close;
			JLabel volume = new JLabel();
			volume.setBounds(x, 2 * Start.min_close + Start.distance, wide, 4 * Start.min_close - Start.gap);
			volume.setOpaque(true);
			volume.setBackground(new Color(100, 0, 0));
			volume.addMouseListener(new BoardListener());
			this.add(volume);

			int height = Start.min_close;

			JLabel t1 = new JLabel();
			t1.setBounds(Start.gap, 0, wide / 2 - Start.gap, height);
			t1.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			t1.setForeground(Color.WHITE);
			t1.setText(name);
			volume.add(t1);

			JLabel t = new JLabel();
			t.setBounds(Start.gap, height, wide / 2 - Start.gap, height);
			t.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			t.setForeground(Color.WHITE);
			t.setText("总成交额");
			volume.add(t);

			JLabel a = new JLabel();
			a.setBounds(Start.gap, 2 * height, wide / 2 - Start.gap, height);
			a.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			a.setForeground(Color.WHITE);
			a.setText("均价");
			volume.add(a);

			JLabel top = new JLabel();
			top.setBounds(Start.gap, Start.min_close / 2, 4 * Start.min_close, 2 * height);
			top.setFont(new Font(Start.font_name, Font.BOLD, Start.font30));
			top.setForeground(Color.WHITE);
			ave.add(top);

			JLabel middle = new JLabel();
			middle.setBounds(wide / 2, height, wide / 2, height);
			middle.setFont(new Font(Start.font_name, Font.PLAIN, Start.font21));
			middle.setForeground(Color.WHITE);
			volume.add(middle);

			JLabel bottom = new JLabel();
			bottom.setBounds(wide / 2, 2 * height, wide / 2, height);
			bottom.setFont(new Font(Start.font_name, Font.PLAIN, Start.font21));
			bottom.setForeground(Color.WHITE);
			volume.add(bottom);

			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String d = format.format(date);
			JLabel time = new JLabel();
			time.setBounds(Start.gap, 3 * Start.min_close + Start.gap, wide / 2, height - Start.gap);
			time.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			time.setForeground(Color.LIGHT_GRAY);
			time.setText(d);
			volume.add(time);

			top.setText("--");
			middle.setText("--");
			bottom.setText("--");

			for (IndustryInfoTop50VO vo : industryList) {
				if (vo.getName().equals(name)) {
					String tip = "万";
					double value = vo.getTotalvolume() * 100;
					if (value >= 10000) {
						value /= 10000;
						tip = "亿";
					}
					String result = "";
					if (value >= 1000) {
						DecimalFormat df = new DecimalFormat("#,###.0");
						result = df.format(value) + tip;
					} else {
						DecimalFormat df = new DecimalFormat("#,###.00");
						result = df.format(value) + tip;
					}
					top.setText(result);
					middle.setText(vo.getTotalmoney() + "亿元");
					if (!vo.getPercent().startsWith("-"))
						bottom.setText(vo.getAveragePrice() + "(" + "+" + vo.getPercent() + ")");
					else
						bottom.setText(vo.getAveragePrice() + "(" + vo.getPercent() + ")");
				}
			}
		}
		this.repaint();

		linepane = new FXpanelLine(datalist, name, "平均股价", graphwidth, graphheight);
		linepane.setBounds(x, y, graphwidth, graphheight);
		this.add(linepane);
		// }

	}

	// private void get_average_max_and_min(ArrayList<AverageVO> list) {
	// max = -100;
	// min = 1000000;
	//
	// for (AverageVO vo : list) {
	// if (vo.getValue() > max) {
	// max = vo.getValue();
	// }
	// if (vo.getValue() < min) {
	// min = vo.getValue();
	// }
	// }
	// }

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == back) {
				m.jumpToIndustryAllUI();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == back) {
				back.changeIcon(1);
				back.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == back) {
				back.changeIcon(0);
				back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}
}

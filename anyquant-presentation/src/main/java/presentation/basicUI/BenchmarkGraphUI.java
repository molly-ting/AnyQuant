package presentation.basicUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import blService.getInfoBLService.GetBenchmarkBLService;
import blService.statisticBLService.KLineBLService;
import businessLogic.getInfoBL.Benchmark;
import businessLogic.statisticsBL.ATRMark;
import businessLogic.statisticsBL.AverageLine;
import businessLogic.statisticsBL.BenchMarkWeeklyK;
import presentation.main.Start;
import presentation.tableUI.FXpanelBar;
import presentation.tableUI.FXpanelKLine;
import presentation.tableUI.FXpanelKLineWeekly;
import presentation.tableUI.FXpanelLine;
import presentation.tableUI.FXpaneldoubleChart;
import presentation.tableUI.FxpanelPie;
import presentation.tableUI.MetroMarks;
import vo.ATRVO;
import vo.AverageVO;
import vo.ShareVO;

public class BenchmarkGraphUI extends JPanel {
	private static final long serialVersionUID = -1853299651044636879L;
	private JLabel line, bar, kline, left, right, timetype;
	private CardLayout card;
	private JPanel container, charttime, volumepane, chartpane;
	private FXpanelLine sumlinepane;
	// private FXpaneldoubleChart doublechartpane;
	private FXpanelBar barpane;
	private FXpanelKLine klinepane;
	private FXpanelKLineWeekly klineweeklypane;
	private String startdate, enddate;
	private int index, interval;
	private String[] options = { "一月", "三月", "六月", "一年", "周K", "月K" };
	private ArrayList<ShareVO> klinedatalist, sumlinedatalist, bardatalist;
	private ArrayList<AverageVO> averagedatalist;
	// private ArrayList<ATRVO> atrdatalist;
	private GetBenchmarkBLService benchbl;
	private AverageLine averagebl;
	// private ATRMark atrbl;
	private KLineBLService klinebl;
	private Color color1 = new Color(255, 127, 36);
	private Color color2 = new Color(55, 55, 55);
	private Color color3 = Start.dark_color;

	public BenchmarkGraphUI() {
		this.setLayout(null);
		this.setBounds(0, 4 * Start.min_close, Start.width - Start.sideWidth, Start.hight - 5 * Start.min_close);
		this.setBackground(Start.color);

		// 使用默认时区和语言环境获得一个日历
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DAY_OF_MONTH, +1);
		enddate = format.format(cal.getTime());
		// 取当前日期的前30天
		cal.add(Calendar.DAY_OF_MONTH, -31);
		startdate = format.format(cal.getTime());

		benchbl = new Benchmark();
		averagebl = new AverageLine();
		// atrbl = new ATRMark();
		klinebl = new BenchMarkWeeklyK();

		container = new JPanel();
		container.setBounds(0, 0, Start.graph_len, Start.graph_hei);
		container.setBounds(Start.distance, Start.distance, Start.graph_len, Start.graph_hei);
		container.setBackground(Start.color);
		card = new CardLayout();
		container.setLayout(card);
		this.add(container);

		Font labelfont = new Font(Start.font_name, Font.BOLD, Start.font18);
		Listener lis = new Listener();
		index = 0;
		interval = 30 - 8;

		JLabel base = new JLabel();
		base.setBounds(Start.distance, Start.distance + Start.graph_hei, Start.graph_len, Start.choose_bar_hei);
		base.setOpaque(true);
		base.setBackground(color2);
		this.add(base);

		bar = new JLabel("收盘价+成交量");
		bar.setBounds(Start.choose_button_width, 0, Start.choose_button_width * 3 / 2, Start.choose_bar_hei);
		bar.setFont(labelfont);
		bar.setHorizontalAlignment(SwingConstants.CENTER);
		bar.setForeground(new Color(250, 250, 250));
		bar.addMouseListener(lis);
		bar.setOpaque(true);
		bar.setBackground(color2);
		bar.setBorder(null);
		base.add(bar);

		kline = new JLabel("k线图");
		kline.setBounds(0, 0, Start.choose_button_width, Start.choose_bar_hei);
		kline.setFont(labelfont);
		kline.setHorizontalAlignment(SwingConstants.CENTER);
		kline.setForeground(color1);
		kline.addMouseListener(lis);
		kline.setOpaque(true);
		kline.setBackground(color3);
		kline.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, color1));
		base.add(kline);

		charttime = new JPanel();
		charttime.setLayout(null);
		charttime.setBounds(Start.graph_len - Start.choose_button_width, 0, Start.choose_button_width,
				Start.choose_bar_hei);
		charttime.setOpaque(false);
		base.add(charttime);

		left = new JLabel();
		left.setIcon(new ImageIcon("picture/left.png"));
		left.setBounds(0, 0, Start.choose_button_width / 4, Start.choose_bar_hei);
		left.addMouseListener(lis);
		charttime.add(left);

		right = new JLabel();
		right.setIcon(new ImageIcon("picture/right.png"));
		right.setBounds(Start.choose_button_width / 2 + Start.choose_button_width / 4, 0, Start.choose_button_width / 4,
				Start.choose_bar_hei);
		right.addMouseListener(lis);
		charttime.add(right);

		timetype = new JLabel(options[index]);
		timetype.setFont(labelfont);
		timetype.setForeground(Color.WHITE);
		timetype.setHorizontalAlignment(SwingConstants.CENTER);
		timetype.setBounds(Start.choose_button_width / 4, 0, Start.choose_button_width / 2, Start.choose_bar_hei);
		charttime.add(timetype);

		volumepane = new JPanel();
		volumepane.setLayout(null);
		volumepane.setBounds(0, 0, Start.graph_len, Start.graph_hei);
		volumepane.setBackground(Start.color);
		container.add("bar", volumepane);

		sumlinedatalist = benchbl.getList("close+volume", startdate, enddate);
		sumlinepane = new FXpanelLine(sumlinedatalist, "沪深300指数", "收盘价（点）", Start.graph_len, Start.graph_hei / 2);
		sumlinepane.setBounds(0, 0, Start.graph_len, Start.graph_hei / 2);
		volumepane.add(sumlinepane);

		bardatalist = benchbl.getList("volume", startdate, enddate);
		barpane = new FXpanelBar(bardatalist, "share", "沪深300指数", "成交量（亿）", Start.graph_len, Start.graph_hei / 2);
		barpane.setBounds(0, Start.graph_hei / 2, Start.graph_len, Start.graph_hei / 2);
		volumepane.add(barpane);

		chartpane = new JPanel();
		chartpane.setLayout(null);
		chartpane.setBounds(0, 0, Start.graph_len, Start.graph_hei);
		container.add("kline", chartpane);

		averagedatalist = averagebl.getAverageLine(interval, enddate, 10);
		klinedatalist = benchbl.getList("open+close+high+low", startdate, enddate);
		klinepane = new FXpanelKLine(klinedatalist, averagedatalist, Start.graph_len, Start.graph_hei);
		klinepane.setBounds(0, 0, Start.graph_len, Start.graph_hei);
		chartpane.add(klinepane);
		card.show(container, "kline");

		new MetroMarks(this, "sh000300");

		this.updateUI();
	}

	private void updatechartdata(int field, int offset) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(field, offset);
		startdate = format.format(cal.getTime());
		getchartdata();
		// updateUI();
	}

	private void getchartdata() {
		if (volumepane.isVisible()) {
			sumlinedatalist = benchbl.getList("close+volume", startdate, enddate);
			bardatalist = benchbl.getList("volume", startdate, enddate);
			sumlinepane.setdatalist(sumlinedatalist);
			barpane.setdatalist(bardatalist);

		} else if (chartpane.isVisible()) {

			if (klineweeklypane != null) {
				klineweeklypane.setVisible(false);
			}

			averagedatalist = averagebl.getAverageLine(interval, enddate, 10);
			klinedatalist = benchbl.getList("open+close+high+low", startdate, enddate);
			klinepane.setdatalist(klinedatalist, averagedatalist);
			klinepane.setVisible(true);
		}

	}

	private void choosetime(int index) {
		if (index == 0) {
			interval = 22;
			updatechartdata(Calendar.MONTH, -1);
		} else if (index == 1) {
			interval = 66;
			updatechartdata(Calendar.MONTH, -3);
		} else if (index == 2) {
			interval = 126;
			updatechartdata(Calendar.MONTH, -6);
		} else if (index == 3) {
			interval = 360 - 108;
			updatechartdata(Calendar.YEAR, -1);
		} else if (index == 4) {
			klinedatalist = klinebl.getWeeklyKLineData();
			showklineweeklypane();

		} else if (index == 5) {
			klinedatalist = klinebl.getMonthlyKLineData();
			showklineweeklypane();
		}
	}

	private void showklineweeklypane() {
		klinepane.setVisible(false);
		if (klineweeklypane == null) {
			klineweeklypane = new FXpanelKLineWeekly(klinedatalist, Start.graph_len, Start.graph_hei);
			klineweeklypane.setBounds(0, 0, Start.graph_len, Start.graph_hei);
			chartpane.add(klineweeklypane);
		} else {
			klineweeklypane.setdatalist(klinedatalist);
			klineweeklypane.setVisible(true);
		}
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == bar) {
				bar.setOpaque(true);
				bar.setBackground(color3);
				bar.setForeground(color1);
				bar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, color1));
				kline.setBorder(null);
				kline.setBackground(color2);
				kline.setForeground(new Color(250, 250, 250));
				index = 0;
				timetype.setText(options[index]);
				choosetime(index);
				card.show(container, "bar");

			} else if (e.getSource() == kline) {
				kline.setOpaque(true);
				kline.setBackground(color3);
				kline.setForeground(color1);
				kline.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, color1));
				bar.setBorder(null);
				bar.setBackground(color2);
				bar.setForeground(new Color(250, 250, 250));
				index = 0;
				timetype.setText(options[index]);
				choosetime(index);
				card.show(container, "kline");

			} else if (e.getSource() == left) {
				if (index > 0) {
					index--;
					timetype.setText(options[index]);
					choosetime(index);
				}

			} else if (e.getSource() == right) {
				int maxindex = 3;
				if (chartpane.isVisible()) {
					maxindex = 5;
				}
				if (index < maxindex) {
					index++;
					timetype.setText(options[index]);
					choosetime(index);
				}
			}
			updateUI();
			// repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == bar) {
				bar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				if (bar.getBorder() == null)
					bar.setForeground(color1);
			} else if (e.getSource() == kline) {
				kline.setCursor(new Cursor(Cursor.HAND_CURSOR));
				if (kline.getBorder() == null)
					kline.setForeground(color1);
			} else if (e.getSource() == left) {
				left.setOpaque(true);
				left.setBackground(color3);
			} else if (e.getSource() == right) {
				right.setOpaque(true);
				right.setBackground(color3);
			}
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == bar) {
				bar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				if (bar.getBorder() == null)
					bar.setForeground(new Color(250, 250, 250));
			} else if (e.getSource() == kline) {
				kline.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				if (kline.getBorder() == null)
					kline.setForeground(new Color(250, 250, 250));
			} else if (e.getSource() == left) {
				left.setBackground(color2);
			} else if (e.getSource() == right) {
				right.setBackground(color2);
			}
			repaint();
		}

	}
}

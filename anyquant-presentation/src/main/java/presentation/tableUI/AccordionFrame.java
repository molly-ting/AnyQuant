package presentation.tableUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import blService.filterInfoBLService.FilterBLService;
import blService.getInfoBLService.GetBenchmarkBLService;
import blService.getInfoBLService.GetShareBLService;
import businessLogic.filterInfoBL.Check;
import businessLogic.filterInfoBL.Filter;
import businessLogic.getInfoBL.Benchmark;
import businessLogic.getInfoBL.Share;
import presentation.listener.EndDateListener;
import presentation.listener.MaxTextListener;
import presentation.listener.MinTextListener;
import presentation.listener.StartDateListener;
import presentation.main.Start;
import vo.ShareVO;
import vo.StrategyDateVO;
import vo.StrategyNumVO;
import vo.StrategyVO;

/**
 * 
 * 过滤面板
 *
 */
public class AccordionFrame extends JPanel {
	private static final long serialVersionUID = 7890187393910560428L;
	private JPanel filterinfopane, layer;
	private JLabel title;
	private MyLabel confirm, cancel, close, infoclose;
	private TableModel tableModel;
	private JTextField[][] fields;
	private JLabel[][] tips;
	private JLabel tip;
	private int[] width;
	private String[] tableheader;
	private Check check;
	private MinTextListener minListener = new MinTextListener();
	private MaxTextListener maxListener = new MaxTextListener();
	private Color color = new Color(240, 240, 240);
	private String id;

	public AccordionFrame(JLabel title, JLayeredPane panel, JPanel layer, TableModel tModel, int[] width,
			String[] tableheader, String id) {
		this.setLayout(null);
		this.setSize(Start.accordion_width, Start.accordion_height);

		Point point = layer.getLocation();
		point.x = title.getX();
		point.y += title.getY() + title.getHeight();
		this.setLocation(point);
		Listener lis = new Listener();
		check = new Check();

		this.title = title;
		this.tableheader = tableheader;
		this.width = width;
		tableModel = tModel;
		this.layer = layer;
		this.id = id;

		this.setBackground(new Color(55, 55, 55, 245));
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}
		});
		init();
		confirm.addMouseListener(lis);
		cancel.addMouseListener(lis);
		close.addMouseListener(lis);

		panel.add(this, new Integer(200));
		panel.moveToFront(this);

	}

	private void init() {
		JLabel[] labels = new JLabel[6];
		fields = new JTextField[6][2];
		tips = new JLabel[6][2];
		FocListener foclis = new FocListener();
		String[] labelnames = { "开盘价（元）", "收盘价（元）", "最高价（元）", "最低价（元）", "成交量（股）" };
		Font labelfont = new Font(Start.font_name, Font.BOLD, Start.font23);
		Font labelfont1 = new Font(Start.font_name, Font.PLAIN, Start.font21);
		Font textfont = new Font(Start.font_name, Font.PLAIN, Start.font20);
		Font textfont1 = new Font(Start.font_name, Font.PLAIN, Start.font18);

		int h = Start.line_height / 4 * 3;
		int tip_h = Start.line_height / 2;
		int err_h = Start.line_height / 3;
		int tipLen = Start.tip_len * 2;
		int len1 = Start.tip_len / 6;
		int len2 = len1 + Start.tip_len;
		int len3 = Start.textfield_len - h;
		int len4 = 2 * len1 + Start.textfield_len;
		int d1 = Start.line_height / 5;
		int d2 = d1 + 2 * Start.line_height;
		int d3 = d2 + tip_h;
		int d4 = d3 + h;
		int gap = h + tip_h + err_h;

		labels[0] = new JLabel("Start");
		labels[0].setForeground(Color.LIGHT_GRAY);
		labels[0].setBounds(len1, d1, Start.tip_len, h);
		labels[0].setFont(labelfont);
		this.add(labels[0]);

		labels[1] = new JLabel("End");
		labels[1].setForeground(Color.LIGHT_GRAY);
		labels[1].setBounds(len1, d1 + Start.line_height, Start.tip_len, h);
		labels[1].setFont(labelfont);
		this.add(labels[1]);

		tip = new JLabel();
		tip.setBounds(len2 + Start.textfield_len, d1 + Start.line_height, Start.textfield_len, h);
		tip.setFont(textfont1);
		tip.setOpaque(false);
		tip.setForeground(new Color(238, 118, 33));
		tip.setText("*日期选反");
		this.add(tip);
		tip.setVisible(false);

		fields[0][0] = new RoundJTextField(3);
		fields[0][0].setBounds(len2, d1, Start.textfield_len, h);
		fields[0][0].setFont(textfont);
		fields[0][0].setBorder(null);
		fields[0][0].setBackground(color);
		fields[0][0].addMouseListener(new StartDateListener());
		fields[0][0].addFocusListener(foclis);
		this.add(fields[0][0]);

		fields[0][1] = new RoundJTextField(3);
		fields[0][1].setBounds(len2, d1 + Start.line_height, Start.textfield_len, h);
		fields[0][1].setFont(textfont);
		fields[0][1].setBorder(null);
		fields[0][1].setBackground(color);
		fields[0][1].addMouseListener(new EndDateListener());
		fields[0][1].addFocusListener(foclis);
		this.add(fields[0][1]);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前30天
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String startDate = format.format(cal.getTime());
		fields[0][0].setText(startDate);

		fields[0][0].setEditable(false);
		DateChooser datechooser1 = new DateChooser("yyyy-MM-dd", fields[0][0], h, h);
		datechooser1.setBounds(len3, 0, h, h);
		fields[0][0].add(datechooser1);

		cal = Calendar.getInstance();
		String endDate = format.format(cal.getTime());
		fields[0][1].setText(endDate);

		fields[0][1].setEditable(false);
		DateChooser datechooser2 = new DateChooser("yyyy-MM-dd", fields[0][1], h, h);
		datechooser2.setBounds(len3, 0, h, h);
		fields[0][1].add(datechooser2);

		for (int i = 1; i < 6; i++) {
			labels[i] = new JLabel(labelnames[i - 1]);
			labels[i].setForeground(Color.LIGHT_GRAY);
			labels[i].setBounds(len1, d2 + (i - 1) * gap, tipLen, tip_h);
			labels[i].setFont(labelfont1);
			labels[i].setOpaque(false);
			this.add(labels[i]);

			fields[i][0] = new RoundJTextField(3);
			fields[i][0].setBounds(len1, d3 + (i - 1) * gap, Start.textfield_len, h);
			fields[i][0].setText("最小值");
			fields[i][0].setFont(textfont);
			fields[i][0].setForeground(Color.GRAY);
			fields[i][0].setBackground(color);
			fields[i][0].setBorder(BorderFactory.createMatteBorder(0, 0, 0, Start.round, color));
			fields[i][0].addMouseListener(minListener);
			fields[i][0].addFocusListener(foclis);
			this.add(fields[i][0]);

			tips[i][0] = new JLabel("*只能填数字");
			tips[i][0].setBounds(len1, d4 + (i - 1) * gap, Start.textfield_len, err_h);
			tips[i][0].setFont(textfont1);
			tips[i][0].setForeground(new Color(238, 118, 33));
			this.add(tips[i][0]);
			tips[i][0].setVisible(false);

			fields[i][1] = new RoundJTextField(3);
			fields[i][1].setBounds(len4, d3 + (i - 1) * gap, Start.textfield_len, h);
			fields[i][1].setText("最大值");
			fields[i][1].setFont(textfont);
			fields[i][1].setForeground(Color.GRAY);
			fields[i][1].setBackground(color);
			fields[i][1].setBorder(BorderFactory.createMatteBorder(0, Start.round, 0, 0, color));
			fields[i][1].addMouseListener(maxListener);
			fields[i][1].addFocusListener(foclis);
			this.add(fields[i][1]);

			tips[i][1] = new JLabel("*只能填数字");
			tips[i][1].setBounds(len4, d4 + (i - 1) * gap, Start.textfield_len, err_h);
			tips[i][1].setFont(textfont1);
			tips[i][1].setForeground(new Color(238, 118, 33));
			this.add(tips[i][1]);
			tips[i][1].setVisible(false);

		}

		int height = Start.accordion_height / 2 + (d4 + 4 * gap) / 2 - Start.button_len / 2;
		int width = Start.accordion_width / 4;
		int half = Start.button_len / 2;

		confirm = new MyLabel("panelok", Start.button_len, Start.button_len);
		confirm.setBounds(width - half, height, Start.button_len, Start.button_len);
		this.add(confirm);

		cancel = new MyLabel("panelback", Start.button_len, Start.button_len);
		cancel.setBounds(2 * width - half, height, Start.button_len, Start.button_len);
		this.add(cancel);

		close = new MyLabel("panelclose", Start.button_len, Start.button_len);
		close.setBounds(3 * width - half, height, Start.button_len, Start.button_len);
		this.add(close);
	}

	private void filter() {
		FilterBLService filterbl = new Filter();
		ArrayList<ShareVO> shareinit = tableModel.getinitialdata();
		String info = "";

		String start = fields[0][0].getText();
		String end = fields[0][1].getText();
		StrategyDateVO strategy = new StrategyDateVO("date", start, end);
		info += "日期：" + start + "—" + end;
		ArrayList<ShareVO> result = filterbl.filter(strategy, shareinit);
		String[] head = { "", "开盘价", "收盘价", "最高价", "最低价", "成交量" };
		String[] straname = { "open", "close", "high", "low", "volume" };

		for (int i = 1; i < 6; i++) {
			String starttemp = fields[i][0].getText();
			String endtemp = fields[i][1].getText();
			double startd = 0;
			double endd = 0;
			boolean startnull = false;
			boolean endnull = false;

			if (starttemp.equals("最小值") || starttemp.equals("")) {
				startnull = true;
				startd = new Double(Double.MIN_VALUE);
			} else {
				startd = Double.parseDouble(starttemp);
			}

			if (endtemp.equals("最大值") || endtemp.equals("")) {
				endnull = true;
				endd = new Double(Double.MAX_VALUE);
			} else {
				endd = Double.parseDouble(endtemp);
			}

			if ((!startnull) && endnull) {
				info += " > " + head[i] + "：>" + starttemp;
			} else if (startnull && (!endnull)) {
				info += " > " + head[i] + "：<" + endtemp;
			} else if ((!startnull) && (!endnull)) {
				info += " > " + head[i] + "：" + starttemp + "—" + endtemp;
			}

			StrategyVO numvo = new StrategyNumVO(straname[i - 1], startd, endd);
			ArrayList<ShareVO> resulttemp = filterbl.filter(numvo, result);
			result = resulttemp;
		}

		tableModel.init(tableheader, width, Start.table_height, result);
		if (filterinfopane != null && filterinfopane.isVisible()) {
			layer.remove(filterinfopane);
		}
		addInfopane(info);
	}

	private void addInfopane(String info) {
		Point point = title.getLocation();
		int len = info.length();

		JLabel filterinfo = new JLabel(info);
		filterinfo.setFont(new Font(Start.font_name, Font.PLAIN, Start.font16));
		filterinfo.setVerticalAlignment(SwingConstants.CENTER);
		int max = Start.width - Start.sideWidth - Start.sort_width - 2 * Start.distance - (int) (Start.min_close * 2.5)
				- 30;
		len = len * 10 + 5;
		int length = Math.min(len, max);
		filterinfo.setBounds(1, 0, length, (int) (title.getHeight() * 0.8));

		infoclose = new MyLabel("infoclose", (int) (title.getHeight() * 0.8) - 2, (int) (title.getHeight() * 0.8) - 2);
		infoclose.setBounds(length + 1, 1, (int) (title.getHeight() * 0.8) - 2, (int) (title.getHeight() * 0.8) - 2);
		infoclose.addMouseListener(new Listener());

		filterinfopane = new JPanel();
		filterinfopane.setLayout(null);
		filterinfopane.setBackground(new Color(240, 240, 240));
		filterinfopane.setBounds(2 * point.x + title.getWidth(), point.y + (int) (title.getHeight() * 0.1),
				length + (int) (title.getHeight() * 0.8), (int) (title.getHeight() * 0.8));
		filterinfopane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		filterinfopane.add(filterinfo);
		filterinfopane.add(infoclose);
		layer.add(filterinfopane);
		layer.repaint();
	}

	private void clear() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前30天
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String startDate = format.format(cal.getTime());
		fields[0][0].setText(startDate);

		cal = Calendar.getInstance();
		String endDate = format.format(cal.getTime());
		fields[0][1].setText(endDate);
		if (filterinfopane != null && filterinfopane.isVisible()) {
			layer.remove(filterinfopane);
			layer.repaint();
		}

		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					fields[i][j].setText("最小值");
					fields[i][j].addMouseListener(minListener);
				} else {
					fields[i][j].setText("最大值");
					fields[i][j].addMouseListener(maxListener);
				}
				fields[i][j].setForeground(Color.GRAY);
				tips[i][j].setVisible(false);
			}
		}
	}

	private class FocListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			/**
			 * 日期提示
			 */
			String s1 = fields[0][0].getText();
			String s2 = fields[0][1].getText();
			if (s1.compareTo(s2) > 0) {
				tip.setVisible(true);
			} else {
				tip.setVisible(false);
			}

			for (int i = 1; i < 6; i++) {
				for (int j = 0; j < 2; j++) {
					if (e.getSource() == fields[i][j]) {
						if (tips[i][j].isVisible())
							tips[i][j].setVisible(false);
						fields[i][j].setForeground(Color.BLACK);
						String text = fields[i][j].getText();
						if (text.equals("最大值") || text.equals("最小值")) {
							fields[i][j].setText("");
						}
						fields[i][j].setBackground(color);
						if (j == 0) {
							fields[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, Start.round, color));
							fields[i][j].removeMouseListener(minListener);
						} else {
							fields[i][j].setBorder(BorderFactory.createMatteBorder(0, Start.round, 0, 0, color));
							fields[i][j].removeMouseListener(maxListener);
						}
					}
				}
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			/**
			 * 日期提示
			 */
			String s1 = fields[0][0].getText();
			String s2 = fields[0][1].getText();
			if (s1.compareTo(s2) > 0) {
				tip.setVisible(true);
			} else {
				tip.setVisible(false);
			}

			for (int i = 1; i < 6; i++) {
				for (int j = 0; j < 2; j++) {
					if (e.getSource() == fields[i][j]) {
						String text = fields[i][j].getText();
						if (!(text.equals("") || text.equals("最小值") || text.equals("最大值"))) {
							if (!check.checkNumber(fields[i][j].getText())) {
								tips[i][j].setVisible(true);
							}
						} else {
							fields[i][j].setForeground(Color.GRAY);
							if (j == 0)
								fields[i][j].setText("最小值");
							else
								fields[i][j].setText("最大值");
						}
						if (j == 0) {
							fields[i][j].addMouseListener(minListener);
						} else {
							fields[i][j].addMouseListener(maxListener);
						}
					}
				}
			}
		}

	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == confirm) {
				boolean correct = true;
				String s1 = fields[0][0].getText();
				String s2 = fields[0][1].getText();
				if (s1.compareTo(s2) > 0) {
					tip.setVisible(true);
					correct = false;
				} else {
					tip.setVisible(false);
				}
				for (int i = 1; i < fields.length; i++) {
					for (int j = 0; j < fields[i].length; j++) {
						String text = fields[i][j].getText();
						if (!(text.equals("最小值") || text.equals("最大值") || text.equals(""))) {
							if (!check.checkNumber(fields[i][j].getText())) {
								tips[i][j].setVisible(true);
								correct = false;
							} else {
								tips[i][j].setVisible(false);
							}
						} else {
							tips[i][j].setVisible(false);
						}
					}
				}
				if (correct) {
					filter();
					setVisible(false);
				}
			} else if (e.getSource() == cancel) {
				clear();
			} else if (e.getSource() == close) {
				clear();
				setVisible(false);
			} else if (e.getSource() == infoclose) {
				filterinfopane.setVisible(false);
				ArrayList<ShareVO> list = tableModel.getinitialdata();
				if (list == null) {
					if (id.equals("sh000300")) {
						GetBenchmarkBLService benchmark = new Benchmark();
						list = benchmark.getList("open+close+high+low+volume");
					} else {
						GetShareBLService share = new Share();
						list = share.getSpecifiedInfo(id, "open+close+high+low+volume");
					}
					tableModel.setinitialdata(list);
				}
				tableModel.init(tableheader, width, Start.table_height, list);
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
			if (e.getSource() == confirm) {
				confirm.changeIcon(1);
				confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (e.getSource() == cancel) {
				cancel.changeIcon(1);
				cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (e.getSource() == close) {
				close.changeIcon(1);
				close.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (e.getSource() == infoclose) {
				infoclose.changeIcon(1);
				infoclose.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == confirm) {
				confirm.changeIcon(0);
				confirm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (e.getSource() == cancel) {
				cancel.changeIcon(0);
				cancel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (e.getSource() == close) {
				close.changeIcon(0);
				close.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (e.getSource() == infoclose) {
				infoclose.changeIcon(0);
				infoclose.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}
}

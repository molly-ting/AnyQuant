package presentation.tableUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
 
import presentation.listener.TableMouseListener;
import presentation.listener.ShareOverviewMouseListener;
import presentation.main.MainUIService;
import presentation.main.Start;
import vo.ShareVO;
import vo.IndustryInfoTop50VO;

/**
 * 可�?�用的表�?
 * 
 * @author 云奎
 *
 */
public class TableModel {

	/**
	 * 字体
	 */
	private Font titlefont = new Font(Start.font_name, Font.PLAIN, Start.font21);
	private Font contentfont = new Font(Start.font_name, Font.PLAIN, Start.font20);
	/**
	 * panel是放置表格的容器，table是绘制表格的
	 */
	private JPanel panel, table;
	/**
	 * panel的大小和位置
	 */
	private Rectangle bounds;
	/**
	 * 表格的起始位�?
	 */
	private Point p;
	/**
	 * 每两个标题之间的空格
	 */
	private int gap = Start.gap;
	/**
	 * 对表格添加的监听
	 */
	private MouseListener mouseListener;
	/**
	 * 股票列表
	 */
	private static ArrayList<ShareVO> shareinit = null;
	private static ArrayList<ShareVO> list = null;
	// private static ArrayList list = null;
	private String type = "";

	/**
	 * 初始化TableModel
	 * 
	 * @param panel
	 *            表格的容�?
	 * @param p
	 *            表格的起始位�?
	 * @param type
	 *            表格类型
	 * @param m
	 *            界面跳转
	 * @param shareinit
	 *            股票列表
	 */
	@SuppressWarnings("static-access")
	public TableModel(JPanel panel, Point p, String type, MainUIService m, ArrayList<ShareVO> shareinit) {
		this.panel = panel;
		this.bounds = panel.getBounds();
		this.shareinit = shareinit;
		this.p = p;
		this.type = type;
		decideListener(type, m);
	}

	/**
	 * 决定不同表格添加的不同监�?
	 * 
	 * @param type
	 *            表格类型
	 * @param m
	 *            界面跳转
	 */
	private void decideListener(String type, MainUIService m) {
		mouseListener = new TableMouseListener(m,type);
//		if (type.equals("shareOverview")) {
//			mouseListener = new ShareOverviewMouseListener(m);
//		} else {
//			mouseListener = new TableMouseListener();
//		}
	}

	public ArrayList<ShareVO> getinitialdata() {
		return shareinit;
	}

	@SuppressWarnings("static-access")
	public void setinitialdata(ArrayList<ShareVO> shareinit) {
		this.shareinit = shareinit;
	}

	public ArrayList<ShareVO> getList() {
		return list;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void init(String[] title, int[] width, int height, @SuppressWarnings("rawtypes") ArrayList list) {
		// if(type.equals("industry"))
		this.list = list;
		panel.removeAll();
		if (list == null) {
			JLabel tip = new JLabel("�?  �?  �?  �?  �?", JLabel.CENTER);

			tip.setFont(new Font(Start.font_name, Font.PLAIN, Start.font66));
			tip.setForeground(Color.LIGHT_GRAY);

			tip.setBounds(bounds);
			panel.setBounds(bounds);
			panel.add(tip);
		} else {
			int wide = 0;
			int i;
			for (i = 0; i < width.length; i++) {
				wide += width[i];
			}
			wide += (i - 1) * gap;
			// 初始化表�?
			int h = initTitle(title, width, height);

			// 初始化表�?
			table = new JPanel();
			table.setLayout(null);
			table.setBounds(p.x, p.y + h, wide, bounds.height);
			table.setOpaque(false);
			// 添加scrollpane
			JScrollPane scroll = new JScrollPane(table);
			scroll.setBounds(p.x, p.y + h, wide + Start.scroll_width, bounds.height - p.y - 1 - h);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setBorder(null);
			scroll.setOpaque(false);
			scroll.getViewport().setOpaque(false);
			// 重定义样�?
			MyScrollPane render = new MyScrollPane();
			scroll.getVerticalScrollBar().setUI(render);
			render.setscrollbar();
			panel.add(scroll);
			// 计算表格高度
			int y = 0;
			int num = 0;
			for (Object share : list) {
				// 表格的一�?
				JLabel line = new JLabel();
				line.setOpaque(true);
				if (num % 2 == 0) {
					line.setBackground(new Color(70, 70, 70));
				} else {
					line.setBackground(new Color(85, 0, 0));
				}

				line.setBounds(0, y, wide + Start.scroll_width, height + 2);

				if (type.equals("industry")) {
					line.setName(((IndustryInfoTop50VO) share).getName());				
				}else{
					line.setName(((ShareVO) share).getID());
				}
				line.addMouseListener(mouseListener);
				table.add(line);
				// 表格每行具体内容
				initTable(title, width, height, share, line);
				y += height + 2;
				num++;
			}
			table.setPreferredSize(new Dimension(wide, y));
		}
		panel.updateUI();
	}

	/**
	 * 
	 * @param title
	 *            名称
	 * @param width
	 *            每一列宽�?
	 * @param height
	 *            行的高度
	 * @return 表头高度
	 */
	private int initTitle(String[] title, int[] width, int height) {
		/**
		 * 表头高度
		 */
		int h = (int) (height * 4 / 5);

		int wide = 0;
		for (int i = 0; i < title.length; i++) {
			JLabel t = new JLabel(title[i], JLabel.LEFT);

			t.setFont(titlefont);

			if (i < 2)
				t.setHorizontalAlignment(JLabel.LEFT);
			else if (i == title.length - 1)
				t.setHorizontalAlignment(JLabel.CENTER);
			else
				t.setHorizontalAlignment(JLabel.RIGHT);
			if (i == title.length - 1) {
				t.setBounds((int) p.getX() + wide, (int) p.getY(), width[i] + Start.scroll_width, h);
			} else {
				t.setBounds((int) p.getX() + wide, (int) p.getY(), width[i], h);
			}
			wide += width[i] + gap;
			t.setOpaque(true);
			t.setForeground(Color.WHITE);
			t.setBackground(new Color(55, 55, 55));
			panel.add(t);
		}

		return h;
	}

	/**
	 * 初始化每�?�?
	 * 
	 * @param title
	 *            名称
	 * @param width
	 *            每一列宽�?
	 * @param height
	 *            行的高度
	 * @param share
	 *            股票属�??
	 * @param line
	 *            每一行的底层容器
	 */
	private void initTable(String[] title, int[] width, int height, Object share, JLabel line) {
		int wide = 0;
		for (int i = 0; i < title.length; i++) {
			JLabel area = new JLabel();
			if (i < 2)
				area.setHorizontalAlignment(JLabel.LEFT);
			else if (i == title.length - 1)
				area.setHorizontalAlignment(JLabel.CENTER);
			else
				area.setHorizontalAlignment(JLabel.RIGHT);
			area.setBounds(wide, 1, width[i], height);
			wide += width[i] + gap + 1;
			String text = "";
			if (type.equals("industry")) {
				text = getValue(title[i], (IndustryInfoTop50VO) share);
			} else {
				text = getValue(title[i], (ShareVO) share);
			}
			// String text = getValue(title[i], share);
			area.setText(text);
			area.setForeground(Color.WHITE);
			area.setFont(contentfont);
			area.setOpaque(false);
			line.add(area);
		}
	}

	private String getValue(String type, IndustryInfoTop50VO info) {

		switch (type) {
		case "行业名称":
			return info.getName();
		case "平均股价":
			return info.getAveragePrice();
		case "成交�?":
			return info.getTotalvolume()+"万手";
		case "涨跌幅度":
			return info.getPercent();
		}
		return null;
	}

	/**
	 * 
	 * @param strategy
	 *            b的属�?
	 * @param b
	 *            要过滤的对象
	 * @return b的属性�??
	 */
	private String getValue(String type, ShareVO s) {
		double value;
		switch (type) {
		case "日期":
			return s.getDate();
		case "代码":
			return s.getID();
		case "�?盘价":
			value = s.getOpen();
			break;
		case "收盘�?":
			value = s.getClose();
			break;
		case "�?高价":
			value = s.getHigh();
			break;
		case "�?低价":
			value = s.getLow();
			break;
		case "成交�?":
			value = s.getVolume();
			break;
		case "换手�?":
			value = s.getTurnover();
			break;
		case "后复权价":
			value = s.getAdj_price();
			break;
		case "市净�?":
			value = s.getPb();
			break;
		case "市盈�?":
			value = s.getPe();
			break;
		case "名称":
			return s.getName();
		default:
			value = 0;
			break;
		}

		if (value == 0.0)
			return "停牌";
		
		String add = "";

		if (value > 10000) {
			value /= 10000;
			add = "�?";
		}
		if (value > 10000) {
			value /= 10000;
			add = "�?";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value) + add;
	}
}

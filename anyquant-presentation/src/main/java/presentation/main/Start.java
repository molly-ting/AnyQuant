package presentation.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import blService.IndustriesBLService.GetTopIndustriesBLService;
import blService.newsBLService.GetNewsBLService;
import businessLogic.getInfoBL.Share;
import businessLogic.industriesBL.GetTopIndustries50;
import businessLogic.newsBL.GetNews;
import data.getShare.ShareGetter;
import dataService.getShareDataService.GetShareService;
import presentation.basicUI.StartUI;
import presentation.listener.LoadingListener;
import sql.DBUtility;

public class Start {
	private static JFrame frame;
	private static JLabel tip;
	/**
	 * 获取屏幕的宽和高，设置frame的宽高比为0.618
	 * 
	 * 屏幕宽
	 */
	static int w = (int) (Toolkit.getDefaultToolkit().getScreenSize().width);
	/**
	 * 屏幕高
	 */
	static int h = (int) (Toolkit.getDefaultToolkit().getScreenSize().height);
	/**
	 * 高和宽的比
	 */
	static double rate = 0.618;
	/**
	 * frame宽
	 */
	public static final int width = (int) (w * 0.77);
	/**
	 * frame高
	 */
	public static final int hight = (int) (width * rate);
	/**
	 * loading的宽
	 */
	public static final int loading_width = (int) (w * 0.32);
	/**
	 * loading的高
	 */
	public static final int loading_height = loading_width * 2 / 3;
	/**
	 * 边条宽
	 */
	public static final int sideWidth = (int) (width * 0.13);
	/**
	 * 顶部条的高度
	 */
	public static final int min_close = (int) (hight * 0.045);
	/**
	 * 关闭按钮的宽度
	 */
	public static final int close_width = (int) (width * 0.04);
	/**
	 * 搜索框的宽度
	 */
	public static final int search_textfield_len = (int) (width * 0.2);
	/**
	 * 搜索框的高度
	 */
	public static final int search_textfield_hei = (int) (width * 0.036);
	/**
	 * 滚动条高度
	 */
	public static final int scroll_width = (int) (width * 0.014);
	/**
	 * 排序条宽度
	 */
	public static final int sort_width = (int) (width * 0.16);
	/**
	 * 排序条高度
	 */
	public static final int sort_height = (int) (width * 0.039);
	/**
	 * 表格与边框的距离
	 */
	public static final int distance = (int) (width * 0.011);
	/**
	 * 板块间的间隙
	 */
	public static final int gap = (int) (width * 0.003);
	/**
	 * 6列表格的合适宽度
	 */
	public static final int table_normal_width_6 = width - sideWidth - scroll_width - 2 * distance - 5 * gap;
	/**
	 * 7列表格的合适宽度
	 */
	public static final int table_normal_width_7 = width - sideWidth - scroll_width - 2 * distance - 5 * gap;
	/**
	 * 表格每一行的高度
	 */
	public static final int table_height = (int) (width * 0.038);
	/**
	 * 过滤框的宽
	 */
	public static final int accordion_width = (int) (width * 0.29);
	/**
	 * 过滤框的高
	 */
	public static final int accordion_height = hight - (int) (min_close * 6.5);
	/**
	 * 过滤框底部高度
	 */
	public static final int botton = (int) (accordion_height * 0.15);
	/**
	 * 过滤按钮的边长
	 */
	public static final int button_len = (int) (accordion_height * 0.085);
	/**
	 * 过滤框的行距
	 */
	public static final int line_height = (accordion_height - botton) / 10;
	/**
	 * 过滤框提示的长度
	 */
	public static final int tip_len = (int) (accordion_width * 0.16);
	/**
	 * 过滤框输入框的长度
	 */
	public static final int textfield_len = (int) (accordion_width * 0.45);
	/**
	 * 字体20号
	 */
	public static final int font20 = (int) (width * 0.014);
	/**
	 * 字体21号
	 */
	public static final int font21 = (int) (width * 0.0145);
	/**
	 * 字体23号
	 */
	public static final int font23 = (int) (width * 0.016);
	/**
	 * 字体18号
	 */
	public static final int font18 = (int) (width * 0.0125);
	/**
	 * 字体16号
	 */
	public static final int font16 = (int) (width * 0.011);
	/**
	 * 字体30号
	 */
	public static final int font30 = (int) (width * 0.018);
	/**
	 * 字体35号
	 */
	public static final int font35 = (int) (width * 0.021);
	/**
	 * 字体45号
	 */
	public static final int font45 = (int) (width * 0.028);
	/**
	 * 字体53号
	 */
	public static final int font53 = (int) (width * 0.032);
	/**
	 * 字体60号
	 */
	public static final int font60 = (int) (width * 0.042);
	/**
	 * 字体66号
	 */
	public static final int font66 = (int) (width * 0.045);
	/**
	 * 字体名称
	 */
	public static final String font_name = "微软雅黑";
	/**
	 * 圆角的值
	 */
	public static final int round = (int) (width * 0.009);
	/**
	 * 指标面板的基本大小
	 */
	public static final int mark_len = (hight - 5 * min_close - 2 * distance - 4 * gap) / 4;
	/**
	 * 图表宽度
	 */
	public static final int graph_len = width - sideWidth - 3 * mark_len - 3 * gap - 2 * distance;
	/**
	 * 图表高度
	 */
	public static final int graph_hei = hight - 6 * min_close - min_close / 2 - gap - 2 * distance;
	/**
	 * 图表选择条的高度
	 */
	public static final int choose_bar_hei = min_close + min_close / 2;
	/**
	 * 图表按钮的宽度
	 */
	public static final int choose_button_width = graph_len / 7;
	/**
	 * top20表格宽度
	 */
	public static final int top_20_width = (width - sideWidth - 2 * distance - gap) * 3 / 7;
	/**
	 * 
	 */
	public static final int specific_width = (width - sideWidth - 2 * distance - gap) * 4 / 7;
	/**
	 * 
	 */
	public static final int table_normal_width_4 = (top_20_width - scroll_width - 3 * gap) / 4;
	/**
	 * 
	 */
	public static final int table_normal_width_5 = (specific_width - scroll_width - 4 * gap) / 5;
	/**
	 * 底部条的高度
	 */
	public static final int botton_bar = (int) (hight * 0.09);
	/**
	 * 4个字按钮的宽度
	 */
	public static final int letter_button_len_4 = (int) (2.7 * min_close);
	/**
	 * 2个字按钮的宽度
	 */
	public static final int letter_button_len_2 = 2 * min_close;
	/**
	 * 字符标识的高度
	 */
	public static final int letter_button = min_close + min_close / 3;
	/**
	 * 背景色
	 */
	public static final Color color = new Color(90, 90, 90);
	/**
	 * 背景色1
	 */
	public static final Color color1 = new Color(75, 75, 75);
	/**
	 * 背景色2
	 */
	public static final Color dark_color = new Color(65, 65, 65);
	/**
	 * 背景色3
	 */
	public static final Color darker_color = new Color(60, 60, 60);
	/**
	 * 行业界面每行高度
	 */
	public static final int industry_line_height = (hight - 3 * min_close) / 16;

	/**
	 * 启动类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// System.out.println(loading_width);
			showAnimation();// 先给出一个loading

			// 初始化股票数据
			tip.setText("正在加载股票数据");
			new Share();

			tip.setText("正在更新数据");
			DBUtility dbUtility = new DBUtility();
			Iterator map;
			GetShareService service1 = new ShareGetter("sh");
			map = service1.getSHShare();
			int num = 0;
			while (map.hasNext()) {
				Entry entry = (Entry) map.next();
				String id = (String) entry.getKey();
				id = "sh" + id;
				dbUtility.updateData(id);
				num++;
				if (num % 100 == 0)
					tip.setText("正在更新数据" + num / 2500.0 * 100 + "%");
			}
			GetShareService service2 = new ShareGetter("sz");
			map = service2.getSZShare();
			while (map.hasNext()) {
				Entry entry = (Entry) map.next();
				String id = (String) entry.getKey();
				id = "sz" + id;
				dbUtility.updateData(id);
				num++;
				if (num % 100 == 0)
					tip.setText("正在更新数据" + num / 2500.0 * 100 + "%");
			}

			dbUtility.updateData("sh000300");

			// 提前获得行业列表
			tip.setText("正在加载行业数据");
			GetTopIndustriesBLService service = new GetTopIndustries50();
			if (service.geTop50s() == null) {
				tip.setText("数据加载失败（请检查网络连接）");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 多展示一秒。。。
				int count = 0;
				while (count < 5) {
					Share share = new Share();
					if (share.getInfoList("sh") != null)
						break;
					count++;
				}
			}

			tip.setText("正在加载行业数据");
			service.geTop50s();
			tip.setText("正在加载新闻");
			GetNewsBLService newsService = new GetNews();
			newsService.getImageNewsList();

			closeAnimation();

			new StartUI();
		} catch (Exception e) {
			e.printStackTrace();
			tip.setText("Something went wrong");
			System.out.println("Something went wrong");
		}

	}

	private static void closeAnimation() {
		frame.dispose();
	}

	private static void showAnimation() {
		frame = new JFrame();
		frame.setSize(loading_width, loading_height);
		MyBackgroundPanel p = new MyBackgroundPanel();
		p.setSize(loading_width, loading_height);
		frame.getContentPane().add(p);

		p.setLayout(null);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		tip = new JLabel();
		tip.setBounds(0, loading_height - loading_width / 10, loading_width, loading_width / 10);
		tip.setForeground(Color.LIGHT_GRAY);
		tip.setFont(new Font(font_name, Font.PLAIN, font18));
		p.add(tip);

		JLabel close = new JLabel();
		close.setBounds(loading_width - 2 * loading_width / 30, loading_width / 30, loading_width / 30,
				loading_width / 30);
		ImageIcon image = new ImageIcon("picture/delete.png");
		image.setImage(image.getImage().getScaledInstance(loading_width / 30, loading_width / 30, Image.SCALE_SMOOTH));
		close.setIcon(image);
		close.addMouseListener(new LoadingListener());
		p.add(close);

		JLabel label = new JLabel();
		label.setSize(loading_width, loading_width / 10);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setIcon(new ImageIcon("picture/abbc.gif"));
		label.setLocation(0, loading_height * 3 / 5);
		p.add(label);
		frame.repaint();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // 多展示一秒。。。
	}

}

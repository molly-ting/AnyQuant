package presentation.marks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import blService.newsBLService.GetNewsBLService;
import businessLogic.newsBL.GetNews;
import presentation.listener.BoardListener;
import presentation.listener.URLListener;
import presentation.main.Start;
import presentation.tableUI.BoardScrollPane;
import presentation.tableUI.MyScrollPane;
import vo.ImageNewsVO;
import vo.ShareVO;

/**
 * 大盘或股票指标 显示当天收盘价和最近5天的最高价和最低价
 * 
 * @author 云奎
 *
 */
public class Board extends JLabel {
	private static final long serialVersionUID = -940503162241527136L;
	Timer timer;
	int length = 2 * Start.mark_len + Start.gap;
	int line = length / 30;
	int flow = 0;
	JLabel news, up, layer;

	public Board(String id, double close, ArrayList<ShareVO> list) {
		int length = 2 * Start.mark_len + Start.gap;
		this.setSize(new Dimension(length, length));
		this.setOpaque(true);
		this.setBackground(new Color(20, 20, 60));
		this.addMouseListener(new BoardListener());

		layer = new JLabel();
		layer.setLayout(null);
		layer.setBounds(0, 0, length, 2 * length);
		this.add(layer);
		init(layer, id, close, list);
	}

	private void init(JLabel layer, String id, double close, ArrayList<ShareVO> list) {
		/**
		 * 缓冲长度
		 */
		int length = Start.gap;
		int height = (int) (Start.mark_len / 3.6);
		int len = (2 * Start.mark_len + Start.gap - 2 * length) / 4;
		int gap = height / 3;
		int tip_len = (int) (height / 5.0 * 3);

		JLabel title1 = new JLabel();
		title1.setBounds(2 * length, 0, Start.mark_len, height);
		title1.setText("价格");
		title1.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
		title1.setForeground(new Color(255, 255, 255));
		layer.add(title1);

		JLabel title2 = new JLabel();
		title2.setBounds(2 * length, height, 2 * Start.mark_len - length, height * 2);
		title2.setText(trans(close));
		title2.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font66));
		title2.setForeground(Color.WHITE);
		layer.add(title2);

		JLabel title3 = new JLabel();
		title3.setBounds(2 * length, 3 * height, 2 * Start.mark_len - length, tip_len);
		title3.setText("价格范围");
		title3.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font20));
		title3.setForeground(Color.WHITE);
		layer.add(title3);

		for (int i = 0; i < 4; i++) {
			ShareVO share = list.get(list.size() - i - 1);
			JLabel title = new JLabel();
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setBounds(length + i * len, 3 * height + tip_len + gap, len, tip_len);
			String date = share.getDate();
			title.setText(date.substring(5));
			title.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			title.setForeground(new Color(255, 255, 255));
			layer.add(title);

			JLabel high = new JLabel();
			high.setHorizontalAlignment(JLabel.CENTER);
			high.setBounds(length + i * len, 3 * height + 2 * tip_len + gap, len, tip_len);
			high.setText(transfer(share.getHigh()));
			high.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			high.setForeground(new Color(238, 44, 44));
			layer.add(high);

			JLabel low = new JLabel();
			low.setHorizontalAlignment(JLabel.CENTER);
			low.setBounds(length + i * len, 3 * height + 3 * tip_len + gap, len, tip_len);
			low.setText(transfer(share.getLow()));
			low.setFont(new Font(Start.font_name, Font.PLAIN, Start.font20));
			low.setForeground(new Color(0, 205, 0));
			layer.add(low);
		}

		news = new JLabel();
		news.setBounds(2 * length, 2 * Start.mark_len - height, 2 * Start.mark_len - length, height);
		news.setText("新闻");
		news.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font23));
		news.setForeground(Color.WHITE);
		news.addMouseListener(new Listener());
		layer.add(news);

		up = new JLabel();
		up.setBounds(2 * length, 2 * Start.mark_len + Start.gap, 2 * Start.mark_len - length, height);
		up.setText("指标");
		up.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font23));
		up.setForeground(Color.WHITE);
		up.addMouseListener(new Listener());
		layer.add(up);

		GetNewsBLService newsService = new GetNews();
		ArrayList<ImageNewsVO> newsList = newsService.getImageNewsList();

		if (newsList != null) {

			JLabel table = new JLabel();
			int base = (2 * Start.mark_len + Start.gap - height) / 2;
			table.setPreferredSize(
					new Dimension(2 * Start.mark_len + Start.gap - Start.scroll_width / 3, newsList.size() * base));

			JScrollPane scroll = new JScrollPane(table);
			scroll.setBounds(0, 2 * Start.mark_len + Start.gap + height, 2 * Start.mark_len + Start.gap,
					2 * Start.mark_len + Start.gap - height);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setBorder(null);
			scroll.setOpaque(false);
			scroll.getViewport().setOpaque(false);
			// 重定义样式
			BoardScrollPane render = new BoardScrollPane();
			scroll.getVerticalScrollBar().setUI(render);
			render.setscrollbar();
			layer.add(scroll);
			init(table, base, newsList);
		} else {
			JLabel table = new JLabel();
			table.setBounds(0, 2 * Start.mark_len + Start.gap + height, 2 * Start.mark_len + Start.gap - Start.scroll_width / 3,
					2 * Start.mark_len + Start.gap - height);
			table.setForeground(Color.GRAY);
			table.setFont(new Font(Start.font_name, Font.PLAIN, Start.font35));
			table.setText("没有新闻");
			layer.add(table);
		}

	}

	private void init(JLabel table, int base, ArrayList<ImageNewsVO> newsList) {
		int i = 0;
		int len = base - Start.gap;

		for (ImageNewsVO imageVO : newsList) {
			JLabel label = new JLabel();
			label.setBounds(0, i * base, length - Start.scroll_width / 3, len);
			ImageIcon image = imageVO.getImageIcon();
			if (image != null) {
				image.setImage(
						image.getImage().getScaledInstance(length - Start.scroll_width / 4, len, Image.SCALE_SMOOTH));
				label.setIcon(image);
			}
			label.setName(imageVO.getURL());
			label.addMouseListener(new URLListener());

			JTextArea content = new JTextArea();
			content.setBounds(0, len * 2 / 3, length - Start.scroll_width / 3, len / 3);
			content.setEditable(false);
			content.setBorder(null);
			content.setOpaque(false);
			content.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
			content.setForeground(Color.WHITE);
			content.setText(imageVO.getContent());
			content.setLineWrap(true);
			label.add(content);
			table.add(label);

			i++;
		}
	}

	private String trans(double value) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(value);
	}

	private String transfer(double value) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(value);
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == news) {
				flow = 0;
				timer = new Timer();
				timer.schedule(new FlowUP(), 20, 20);
			} else if (e.getSource() == up) {
				flow = length;
				timer = new Timer();
				timer.schedule(new FlowDown(), 20, 20);
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel news = (JLabel) e.getSource();
			news.setForeground(Color.GRAY);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel news = (JLabel) e.getSource();
			news.setForeground(Color.WHITE);

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class FlowUP extends TimerTask {

		@Override
		public void run() {
			if (flow == length)
				timer.cancel();
			else {
				if (flow + line > length && flow < length)
					flow = length;
				else
					flow += line;
				layer.setLocation(0, -flow);
			}
		}
	}

	private class FlowDown extends TimerTask {

		@Override
		public void run() {
			if (flow == 0)
				timer.cancel();
			else {
				if (flow - line < 0 && flow > 0)
					flow = 0;
				else
					flow -= line;
				layer.setLocation(0, -flow);
			}
		}
	}
}

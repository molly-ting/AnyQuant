package presentation.basicUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.MyLabel;
import presentation.tableUI.SearchTextField;

/**
 * 股票具体信息界面
 * 
 * 
 *
 */
public class StockUI extends JPanel {
	/**
	 * 标号
	 */
	private static final long serialVersionUID = -6154864987451050959L;
	/**
	 * tabspane选择表格还是图表
	 */
	private JPanel tabspane;
	/**
	 * listpane表格界面
	 */
	private StockListUI listpane;
	private CardLayout card;
	private JPanel container;
	/**
	 * table表格按钮 graph图表按钮
	 */
	private JLabel table, graph;
	private MyLabel back;
	private StockGraphUI graphpane;
	private MainUIService main;
	private boolean tableisclicked, graphisclicked = false;
	private String before;

	public StockUI(MainUIService m, String id,String before) {
		this.setLayout(null);
		this.setSize(Start.width - Start.sideWidth, Start.hight - Start.min_close);
		this.setOpaque(false);
		main = m;
		this.before = before;

		container = new JPanel();
		container.setBounds(0, 4 * Start.min_close, Start.width - Start.sideWidth,
				Start.hight - Start.min_close - 4 * Start.min_close);
		card = new CardLayout();
		container.setLayout(card);
		this.add(container);

		inittabspane(id);
		listpane = new StockListUI(m, id);
		container.add("table", listpane);
		card.show(container, "table");

		graphpane = new StockGraphUI(id);
		container.add("graph", graphpane);

		back = new MyLabel("back", (int) (Start.min_close * 1.5), (int) (Start.min_close * 1.5));
		back.setBounds(0, 0, (int) (Start.min_close * 1.5), (int) (Start.min_close * 1.5));
		back.addMouseListener(new Listener());
		this.add(back);
		// 搜索框
		JPanel title = new JPanel();
		title.setLayout(null);
		title.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		title.setBackground(Start.darker_color);
		this.add(title);

		GetShareBLService share = new Share();
		String name = share.getShareName(id);

		JLabel title1 = new JLabel();
		title1.setBounds((int) (Start.min_close * 1.5), 0,
				Start.width - Start.sideWidth - (int) (Start.min_close * 1.5) - Start.distance - Start.textfield_len,
				(int) (Start.min_close * 1.5));
		title1.setFont(new Font(Start.font_name, Font.PLAIN, Start.font30));
		title1.setText(id + " " + name);
		title1.setForeground(new Color(255, 255, 255));
		title.add(title1);

		new SearchTextField(m, title);
		this.updateUI();
	}

	/**
	 * 初始化列表和图表的panel
	 */
	private void inittabspane(String id) {
		Font labelfont = new Font(Start.font_name, Font.BOLD, Start.font20);
		Listener lis = new Listener();

		tabspane = new JPanel();
		tabspane.setLayout(null);
		tabspane.setBounds(0, 2 * Start.min_close, Start.width - Start.sideWidth, 2 * Start.min_close);
		tabspane.setBackground(Start.dark_color);
		this.add(tabspane);

		tableisclicked = true;
		table = new JLabel("列表");
		table.setBounds(Start.distance, Start.min_close * 2 - Start.letter_button, Start.letter_button_len_2,
				Start.letter_button);
		table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
		table.setHorizontalAlignment(SwingConstants.CENTER);
		table.setForeground(new Color(0, 191, 255));
		table.setOpaque(false);
		table.setFont(labelfont);
		table.addMouseListener(lis);
		tabspane.add(table);

		graph = new JLabel("图表");
		graph.setBounds(Start.distance + Start.letter_button_len_2 + Start.gap,
				Start.min_close * 2 - Start.letter_button, Start.letter_button_len_2, Start.letter_button);
		// graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new
		// Color(240, 240, 240)));
		graph.setHorizontalAlignment(SwingConstants.CENTER);
		graph.setForeground(new Color(240, 240, 240));
		graph.setOpaque(false);
		graph.setFont(labelfont);
		graph.addMouseListener(lis);
		tabspane.add(graph);
	}

	private class Listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == back) {

				if(before.equals("shareOverview")){
				main.jumpToShareOverviewUI();
				}else{
					main.jumpToIndustrySpecificUI(before);
				}
				

			} else if (e.getSource() == table) {
				tableisclicked = true;
				graphisclicked = false;
				table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
				table.setForeground(new Color(0, 191, 255));
				graph.setBorder(null);
				graph.setForeground(new Color(240, 240, 240));
				card.show(container, "table");

			} else if (e.getSource() == graph) {
				tableisclicked = false;
				graphisclicked = true;
				graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
				graph.setForeground(new Color(0, 191, 255));
				table.setBorder(null);
				table.setForeground(new Color(240, 240, 240));
				card.show(container, "graph");

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
			} else if (e.getSource() == table) {
				table.setForeground(new Color(0, 191, 255));
				table.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (e.getSource() == graph) {
				graph.setForeground(new Color(0, 191, 255));
				graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == back) {
				back.changeIcon(0);
				back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (e.getSource() == table) {

				if (!tableisclicked) {
					table.setForeground(new Color(240, 240, 240));
				}
				table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			} else if (e.getSource() == graph) {

				if (!graphisclicked) {
					graph.setForeground(new Color(240, 240, 240));
				}
				graph.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
		}

	}
}

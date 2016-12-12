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

import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.SearchTextField;

/**
 * 所有股票列表
 * 
 * @author 云奎
 *
 */
public class StockOverviewUI extends JPanel {

	/**
	 * 标记
	 */
	private static final long serialVersionUID = 374460338551815318L;
	/**
	 * tabspane选择表格还是图表
	 */
	private JPanel tabspane;
	/**
	 * listpane表格界面
	 */
	private StockOverviewListUI listpane;
//	private CardLayout card;
//	private JPanel container;
	/**
	 * table表格按钮 graph图表按钮
	 */
	private JLabel table;	
//	private JLabel graph;
	//private StockOverviewGraphUI graphpane;
	
//	private boolean tableisclicked,graphisclicked = false;

	public StockOverviewUI(MainUIService m) {
		this.setLayout(null);
		this.setSize(Start.width - Start.sideWidth, Start.hight - Start.min_close);
		this.setOpaque(false);

//		container = new JPanel();
//		container.setBounds(0,4 * Start.min_close,Start.width - Start.sideWidth, Start.hight - Start.min_close-4 * Start.min_close);
//		card = new CardLayout();
//		container.setLayout(card);
//		this.add(container);

		inittabspane();
		listpane = new StockOverviewListUI(m);	
		this.add(listpane);
//		container.add("table",listpane);
//		card.show(container, "table");

//		graphpane = new StockOverviewGraphUI();
//		container.add("graph",graphpane);
		
		// 搜索框
		JPanel title = new JPanel();
		title.setLayout(null);
		title.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		title.setBackground(Start.darker_color);
		this.add(title);
		
		JLabel title1 = new JLabel();
		title1.setBounds(Start.distance, 0, Start.width - Start.sideWidth - Start.distance, 2 * Start.min_close);
		title1.setFont(new Font(Start.font_name, Font.PLAIN, Start.font30));
		title1.setText("股票列表");
		title1.setForeground(new Color(255, 255, 255));
		title.add(title1);
		
		new SearchTextField(m, title);
		this.updateUI();
	}

	/**
	 * 初始化列表和图表的panel
	 */
	private void inittabspane() {
		Font labelfont = new Font(Start.font_name, Font.BOLD, Start.font20);
//		Listener lis = new Listener();

		tabspane = new JPanel();
		tabspane.setLayout(null);
		tabspane.setBounds(0, 2 * Start.min_close, Start.width - Start.sideWidth, 2 * Start.min_close);
		tabspane.setBackground(Start.dark_color);
		this.add(tabspane);

//		tableisclicked = true;
		table = new JLabel("列表");
		table.setBounds(Start.distance, Start.min_close * 2 - Start.letter_button, Start.letter_button_len_2,
				Start.letter_button);
		table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
		table.setHorizontalAlignment(SwingConstants.CENTER);
		table.setForeground(new Color(0, 191, 255));
		table.setOpaque(false);
		table.setFont(labelfont);
		tabspane.add(table);
	}

//	private class Listener implements MouseListener {
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if (e.getSource() == table) {
//				tableisclicked = true;
//				graphisclicked = false;
//				table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
//				table.setForeground(new Color(0, 191, 255));		
//				graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240,240,240)));
//				graph.setForeground(new Color(240,240,240));
//				card.show(container, "table");
//
//			} else if (e.getSource() == graph) {
//				tableisclicked = false;
//				graphisclicked = true;
//				graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
//				graph.setForeground(new Color(0, 191, 255));	
//				table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240,240,240)));
//				table.setForeground(new Color(240,240,240));
//				card.show(container, "graph");
//			}
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if (e.getSource() == table) {
//
//				table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
//				table.setForeground(new Color(0, 191, 255));
//				table.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//			} else if (e.getSource() == graph) {
//
//				graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
//				graph.setForeground(new Color(0, 191, 255));
//				graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//			}
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if (e.getSource() == table) {
//
//				if(!tableisclicked){
//					table.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240,240,240)));
//					table.setForeground(new Color(240,240,240));	
//				}
//				table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//
//			} else if (e.getSource() == graph) {
//
//				if(!graphisclicked){
//					graph.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240,240,240)));
//					graph.setForeground(new Color(240,240,240));
//				}
//				graph.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//
//			}
//		}
//
//	}
}

package presentation.basicUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.listener.MouseEventListener;
import presentation.main.MainUI;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.MyLabel;

public class StartUI extends JFrame {

	/**
	 * 
	 * /**这是界面的起始点，startUI是frame，menu这个panel是左侧的菜单栏
	 * bar这个panel是顶上的“AnyQuant”、“最小化”、“关闭”，重写了Windows自带的功能
	 * container是cardlayout的容器，用来存放各种panel，如：股票、大盘、行业分类等等。
	 * 此外，mainUIservice是cardlayout集中跳转的接口
	 */
	private static final long serialVersionUID = 87900012735341313L;
	private CardLayout card;
	private JPanel container, menu, bar;
	private MyLabel minimize, close, stockl, tapel, industry;
	private MainUIService main;
	private boolean stockisclicked, tapeisclicked, indusisclicked;
	private float count = 1.0f;

	public StartUI() {
		this.setLayout(null);
		this.setSize(new Dimension(Start.width, Start.hight));
		this.setUndecorated(true);
		this.setBackground(null);
		// this.setOpacity(0.9f);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		JListener listen = new JListener();
		MouseEventListener mouseEventListener = new MouseEventListener(this);

		Font titleFont = new Font(Start.font_name, Font.PLAIN, Start.font20);
		stockisclicked = false;
		tapeisclicked = true;
		indusisclicked = false;

		container = new JPanel();
		container.setBounds(Start.sideWidth, Start.min_close, Start.width - Start.sideWidth,
				Start.hight - Start.min_close);
		card = new CardLayout();
		container.setLayout(card);
		container.setBackground(Start.color);
		this.add(container);
		main = new MainUI(card, container);

		bar = new JPanel();
		bar.setBounds(0, 0, Start.width, Start.min_close);
		bar.setBackground(new Color(19, 19, 19));
		bar.setLayout(null);
		// bar.setOpaque(false);
		bar.addMouseListener(mouseEventListener);
		bar.addMouseMotionListener(mouseEventListener);
		this.add(bar);

		JLabel title = new JLabel();
		title.setFont(titleFont);
		title.setBounds(Start.sideWidth / 8, 1, 3 * Start.close_width, Start.min_close);
		title.setForeground(new Color(255, 255, 255));
		title.setText("AnyQuant");
		bar.add(title);

		minimize = new MyLabel("miniicon", Start.close_width, Start.min_close);
		minimize.setBounds(Start.width - 2 * Start.close_width - 1, 1, Start.close_width, Start.min_close);
		minimize.addMouseListener(listen);
		bar.add(minimize);

		close = new MyLabel("closeicon", Start.close_width, Start.min_close);
		close.setBounds(Start.width - Start.close_width - 1, 1, Start.close_width, Start.min_close);
		close.addMouseListener(listen);
		bar.add(close);

		menu = new JPanel();
		menu.setBounds(0, Start.min_close, Start.sideWidth, Start.hight - Start.min_close);
		menu.setBackground(new Color(44, 44, 44));
		menu.setLayout(null);
		this.add(menu);

		stockl = new MyLabel("stock", Start.sideWidth, (int) (Start.sideWidth / 2.5));
		stockl.setBounds(0, Start.sideWidth / 2, Start.sideWidth, (int) (Start.sideWidth / 2.5));
		stockl.addMouseListener(listen);
		menu.add(stockl);

		tapel = new MyLabel("tape", Start.sideWidth, (int) (Start.sideWidth / 2.5));
		tapel.setBounds(0, Start.sideWidth / 2 + (int) (Start.sideWidth / 2.5), Start.sideWidth,
				(int) (Start.sideWidth / 2.5));
		tapel.addMouseListener(listen);
		menu.add(tapel);

		industry = new MyLabel("trade", Start.sideWidth, (int) (Start.sideWidth / 2.5));
		industry.setBounds(0, Start.sideWidth / 2 + 2 * (int) (Start.sideWidth / 2.5), Start.sideWidth,
				(int) (Start.sideWidth / 2.5));
		industry.addMouseListener(listen);
		menu.add(industry);

		this.repaint();

		main.jumpToBenchmarkUI();
		container.updateUI();

	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == tapel) {
				tapeisclicked = true;
				stockisclicked = false;
				indusisclicked = false;

				tapel.changeIcon(2);
				stockl.changeIcon(0);
				industry.changeIcon(0);

				main.jumpToBenchmarkUI();
			} else if (e.getSource() == stockl) {
				tapeisclicked = false;
				stockisclicked = true;
				indusisclicked = false;

				tapel.changeIcon(0);
				stockl.changeIcon(2);
				industry.changeIcon(0);

				main.jumpToShareOverviewUI();

			} else if (e.getSource() == industry) {
				tapeisclicked = false;
				stockisclicked = false;
				indusisclicked = true;

				tapel.changeIcon(0);
				stockl.changeIcon(0);
				industry.changeIcon(2);

				main.jumpToIndustryUI();

			} else if (e.getSource() == close) {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				if (ge.getDefaultScreenDevice()
						.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)
						&& getGraphicsConfiguration().isTranslucencyCapable()) {
					Timer time = new Timer();
					time.schedule(new MyTask(), 60, 20);
				} else {
					System.exit(0);
				}
			} else if (e.getSource() == minimize) {
				setExtendedState(JFrame.ICONIFIED);
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == tapel) {
				if (tapeisclicked) {
					tapel.changeIcon(3);
				} else {
					tapel.changeIcon(1);
				}
				tapel.setCursor(new Cursor(Cursor.HAND_CURSOR));

			} else if (e.getSource() == stockl) {

				if (stockisclicked) {
					stockl.changeIcon(3);
				} else {
					stockl.changeIcon(1);
				}
				stockl.setCursor(new Cursor(Cursor.HAND_CURSOR));

			} else if (e.getSource() == industry) {

				if (indusisclicked) {
					industry.changeIcon(3);
				} else {
					industry.changeIcon(1);
				}
				industry.setCursor(new Cursor(Cursor.HAND_CURSOR));

			} else if (e.getSource() == minimize) {
				minimize.changeIcon(1);
			} else if (e.getSource() == close) {
				close.changeIcon(1);
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == tapel) {

				if (tapeisclicked) {
					tapel.changeIcon(2);
				} else {
					tapel.changeIcon(0);
				}
				tapel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			} else if (e.getSource() == stockl) {
				if (stockisclicked) {
					stockl.changeIcon(2);
				} else {
					stockl.changeIcon(0);
				}
				stockl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			} else if (e.getSource() == industry) {

				if (indusisclicked) {
					industry.changeIcon(2);
				} else {
					industry.changeIcon(0);
				}
				industry.setCursor(new Cursor(Cursor.HAND_CURSOR));

			} else if (e.getSource() == minimize) {
				minimize.changeIcon(0);
			} else if (e.getSource() == close) {
				close.changeIcon(0);
			}
		}
	}

	private class MyTask extends TimerTask {

		@Override
		public void run() {
			if (count <= 0.01)
				System.exit(0);
			else {
				setOpacity(count);
				count -= 0.05;
			}
		}
	}
}

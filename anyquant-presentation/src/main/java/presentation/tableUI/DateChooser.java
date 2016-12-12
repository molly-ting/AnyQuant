package presentation.tableUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DateChooser extends JPanel {
	private static final long serialVersionUID = -4780209095587123655L;
	private Date initDate;
	private Calendar now = Calendar.getInstance();
	private Calendar select;
	private JPanel monthPanel;// 月历
	private JP1 jp1;// 四块面板,组成
	private JP3 jp3;
	private Font font = new Font("幼圆", Font.PLAIN, 15);
	private final LabelManager lm = new LabelManager();
	private JLabel showDate; // ,toSelect;
	private SimpleDateFormat sdf;
	private boolean isShow = false;
	private Popup pop;
	private JTextField text;
	private int panelLength = 210;
	private int width, height;

	/**
	 * Creates a new instance of DateChooser
	 */
	public DateChooser(String format, JTextField textfield, int width, int height) {

		this(new Date(), format, width, height);
		text = textfield;
	}

	public DateChooser(Date date, String format, int width, int height) {
		this.width = width;
		this.height = height;
		this.setOpaque(false);
		initDate = date;
		sdf = new SimpleDateFormat(format);
		select = Calendar.getInstance();
		select.setTime(initDate);
		initPanel();
		initLabel();
	}

	/**
	 * 是否允许用户选择
	 */
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		showDate.setEnabled(b);
	}

	/**
	 * 得到当前选择框的日期
	 */
	public Date getDate() {
		return select.getTime();
	}

	// 根据初始化的日期,初始化面板
	private void initPanel() {
		monthPanel = new JPanel(new BorderLayout());
		monthPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		JPanel up = new JPanel(new BorderLayout());
		up.setBackground(Color.WHITE);
		up.add(jp1 = new JP1(), BorderLayout.NORTH);
		up.add(new JP2(), BorderLayout.CENTER);
		monthPanel.add(jp3 = new JP3(), BorderLayout.CENTER);
		monthPanel.add(up, BorderLayout.NORTH);
		this.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {

			}

			public void ancestorRemoved(AncestorEvent event) {

			}

			// 只要祖先组件一移动,马上就让popup消失
			public void ancestorMoved(AncestorEvent event) {
				hidePanel();
			}
		});
	}

	// 初始化标签
	private void initLabel() { // showDate是显示的文字，应改为图标(sdf.format(initDate))
		this.setSize(40, 40);
		this.setLayout(null);
		showDate = new JLabel();
		ImageIcon c = new ImageIcon("picture/calendar.png");
		c.setImage(c.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		showDate.setIcon(c);
		showDate.setBounds(0, 0, this.getWidth(), this.getHeight());
		showDate.setOpaque(false);
		showDate.setRequestFocusEnabled(true);
		showDate.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				showDate.requestFocusInWindow();
			}
		});
		this.add(showDate);
		showDate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}

			public void mouseExited(MouseEvent me) {
				if (showDate.isEnabled()) {
					showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}

			public void mousePressed(MouseEvent me) {
				if (showDate.isEnabled()) {
					if (isShow) {
						hidePanel();
					} else {
						showPanel(showDate);
					}
				}
			}

			public void mouseReleased(MouseEvent me) {
				if (showDate.isEnabled()) {
					// showDate.setForeground(Color.BLACK);
				}
			}
		});
		showDate.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				hidePanel();
			}

			public void focusGained(FocusEvent e) {

			}
		});
	}

	// 根据新的日期刷新
	private void refresh() {
		jp1.updateDate();
		jp3.updateDate();
		SwingUtilities.updateComponentTreeUI(this);
	}

	// 提交日期
	private void commit() {
		System.out.println("选中的日期是：" + sdf.format(select.getTime()));
		text.setText(sdf.format(select.getTime()));
		hidePanel();
	}

	// 隐藏日期选择面板
	private void hidePanel() {
		if (pop != null) {
			isShow = false;
			pop.hide();
			pop = null;
		}
	}

	// 显示日期选择面板
	private void showPanel(Component owner) {
		if (pop != null) {
			pop.hide();
		}
		Point show = new Point(0, showDate.getHeight());
		SwingUtilities.convertPointToScreen(show, showDate);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = show.x;
		int y = show.y;
		if (x < 0) {
			x = 0;
		}
		if (x > size.width - panelLength) {
			x = size.width - panelLength;
		}
		if (y < size.height - 170) {
		} else {
			y -= 188;
		}
		pop = PopupFactory.getSharedInstance().getPopup(owner, monthPanel, x, y);
		pop.show();
		isShow = true;
	}

	/**
	 * 最上面的面板用来显示月份的增减
	 */
	private class JP1 extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7310613064289180357L;
		JLabel yearleft, yearright, monthleft, monthright, center, centercontainer;

		public JP1() {
			super(new BorderLayout());
			// this.setBackground(new Color(160,185,215));
			this.setBackground(Color.ORANGE);
			initJP1();
		}

		private void initJP1() {
			yearleft = new JLabel("  -", JLabel.CENTER);
			yearleft.setToolTipText("上一年");
			yearright = new JLabel("+  ", JLabel.CENTER);
			yearright.setToolTipText("下一年");
			yearleft.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
			yearright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

			monthleft = new JLabel("<", JLabel.RIGHT);
			monthleft.setToolTipText("上一月");
			monthright = new JLabel(">", JLabel.LEFT);
			monthright.setToolTipText("下一月");
			monthleft.setBorder(BorderFactory.createEmptyBorder(2, 30, 0, 0));
			monthright.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 30));

			centercontainer = new JLabel("", JLabel.CENTER);
			centercontainer.setLayout(new BorderLayout());
			center = new JLabel("", JLabel.CENTER);

			centercontainer.add(monthleft, BorderLayout.WEST);
			centercontainer.add(center, BorderLayout.CENTER);
			centercontainer.add(monthright, BorderLayout.EAST);

			yearleft.setFont(font);
			yearright.setFont(font);
			monthleft.setFont(font);
			monthright.setFont(font);

			this.add(yearleft, BorderLayout.WEST);
			this.add(centercontainer, BorderLayout.CENTER);
			this.add(yearright, BorderLayout.EAST);
			this.setPreferredSize(new Dimension(panelLength, 30));

			updateDate();

			yearleft.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					yearleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
					yearleft.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					yearleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					yearleft.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.YEAR, -1);
					yearleft.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					yearleft.setForeground(Color.BLACK);
				}
			});
			yearright.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					yearright.setCursor(new Cursor(Cursor.HAND_CURSOR));
					yearright.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					yearright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					yearright.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.YEAR, 1);
					yearright.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					yearright.setForeground(Color.BLACK);
				}
			});
			monthleft.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					monthleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
					monthleft.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					monthleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					monthleft.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, -1);
					monthleft.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					monthleft.setForeground(Color.BLACK);
				}
			});
			monthright.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					monthright.setCursor(new Cursor(Cursor.HAND_CURSOR));
					monthright.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent me) {
					monthright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					monthright.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent me) {
					select.add(Calendar.MONTH, 1);
					monthright.setForeground(Color.WHITE);
					refresh();
				}

				public void mouseReleased(MouseEvent me) {
					monthright.setForeground(Color.BLACK);
				}
			});
		}

		private void updateDate() {
			Font font2 = new Font("幼圆", Font.PLAIN, 15);
			center.setFont(font2);
			center.setText(select.get(Calendar.YEAR) + "年" + (select.get(Calendar.MONTH) + 1) + "月");
		}
	}

	private class JP2 extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2973304835364716117L;

		public JP2() {
			this.setPreferredSize(new Dimension(panelLength, 30));
			this.setBackground(Color.RED);
		}

		protected void paintComponent(Graphics g) {

			g.setFont(font);
			g.drawString("日  一  二  三  四  五  六", 5, 20);
			g.drawLine(0, 25, getWidth(), 25);

		}
	}

	private class JP3 extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7298383418090843429L;

		public JP3() {
			super(new GridLayout(6, 7));
			this.setPreferredSize(new Dimension(panelLength, 150));
			this.setBackground(Color.WHITE);
			initJP3();
		}

		private void initJP3() {
			updateDate();
		}

		public void updateDate() {
			this.removeAll();
			lm.clear();
			Date temp = select.getTime();
			Calendar select = Calendar.getInstance();
			select.setTime(temp);
			select.set(Calendar.DAY_OF_MONTH, 1);
			int index = select.get(Calendar.DAY_OF_WEEK);
			int sum = (index == 1 ? 8 : index);
			select.add(Calendar.DAY_OF_MONTH, 0 - sum);
			for (int i = 0; i < 42; i++) {
				select.add(Calendar.DAY_OF_MONTH, 1);
				lm.addLabel(new MyLabel(select.get(Calendar.YEAR), select.get(Calendar.MONTH),
						select.get(Calendar.DAY_OF_MONTH)));
			}
			for (MyLabel my : lm.getLabels()) {
				this.add(my);
			}
			select.setTime(temp);
		}
	}

	private class MyLabel extends JLabel implements Comparator<MyLabel>, MouseListener, MouseMotionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1131463792254061802L;
		private int year, month, day;
		private boolean isSelected;

		public MyLabel(int year, int month, int day) {
			super("" + day, JLabel.CENTER);
			this.year = year;
			this.day = day;
			this.month = month;
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.setFont(font);
			this.setOpaque(true);
			if (month == select.get(Calendar.MONTH)) {
				this.setForeground(Color.BLACK);
			} else {
				this.setForeground(Color.LIGHT_GRAY);
			}
			if (day == select.get(Calendar.DAY_OF_MONTH)) {
				// this.setBackground(new Color(160,185,215));
				this.setBackground(Color.ORANGE);
			} else {
				this.setBackground(Color.WHITE);
			}
		}

		public boolean getIsSelected() {
			return isSelected;
		}

		public void setSelected(boolean b, boolean isDrag) {
			isSelected = b;
			if (b && !isDrag) {
				int temp = select.get(Calendar.MONTH);
				select.set(year, month, day);
				if (temp == month) {
					SwingUtilities.updateComponentTreeUI(jp3);
				} else {
					refresh();
				}
			}
			this.repaint();
		}

		protected void paintComponent(Graphics g) {
			setBackground(Color.WHITE);
			if (day == select.get(Calendar.DAY_OF_MONTH) && month == select.get(Calendar.MONTH)) {
				// 如果当前日期是选择日期,则高亮显示
				g.fillRect(0, 0, getWidth(), getHeight());
				setBackground(Color.ORANGE);
			}
			if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH)
					&& day == now.get(Calendar.DAY_OF_MONTH)) {
				// 如果日期和当前日期一样,则用红框
				Graphics2D gd = (Graphics2D) g;
				gd.setColor(Color.RED);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			if (isSelected) {// 如果被选中了就画出一个虚线框出来
				Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f,
						new float[] { 2.0f, 2.0f }, 1.0f);
				Graphics2D gd = (Graphics2D) g;
				gd.setStroke(s);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			super.paintComponent(g);
		}

		public boolean contains(Point p) {
			return this.getBounds().contains(p);
		}

		private void update() {
			repaint();
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			isSelected = true;
			update();
		}

		public void mouseReleased(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, false);
			commit();
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mouseDragged(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), jp3);
			lm.setSelect(p, true);
			// System.out.println("aaaaaaa");
		}

		public void mouseMoved(MouseEvent e) {
		}

		public int compare(MyLabel o1, MyLabel o2) {
			Calendar c1 = Calendar.getInstance();
			c1.set(o1.year, o2.month, o1.day);
			Calendar c2 = Calendar.getInstance();
			c2.set(o2.year, o2.month, o2.day);
			return c1.compareTo(c2);
		}
	}

	private class LabelManager {
		private List<MyLabel> list;

		public LabelManager() {
			list = new ArrayList<MyLabel>();
		}

		public List<MyLabel> getLabels() {
			return list;
		}

		public void addLabel(MyLabel my) {
			list.add(my);
		}

		public void clear() {
			list.clear();
		}

		@SuppressWarnings("unused")
		public void setSelect(MyLabel my, boolean b) {
			for (MyLabel m : list) {
				if (m.equals(my)) {
					m.setSelected(true, b);
				} else {
					m.setSelected(false, b);
				}
			}
		}

		public void setSelect(Point p, boolean b) {
			// 如果是拖动,则要优化一下,以提高效率
			if (b) {
				// 表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和
				// 将要显示的标签找到了就可以了
				boolean findPrevious = false, findNext = false;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						findNext = true;
						if (m.getIsSelected()) {
							findPrevious = true;
						} else {
							m.setSelected(true, b);
						}
					} else if (m.getIsSelected()) {
						findPrevious = true;
						m.setSelected(false, b);
					}
					if (findPrevious && findNext) {
						return;
					}
				}
			} else {
				MyLabel temp = null;
				for (MyLabel m : list) {
					if (m.contains(p)) {
						temp = m;
					} else if (m.getIsSelected()) {
						m.setSelected(false, b);
					}
				}
				if (temp != null) {
					temp.setSelected(true, b);
				}
			}
		}
	}
}

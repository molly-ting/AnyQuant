package presentation.basicUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import data.GetAllIndustries;
import dataService.industryService.GetAllIndustriesService;
import presentation.main.MainUIService;
import presentation.main.Start;
import presentation.tableUI.IndustryLabel;
import presentation.tableUI.MyScrollPane;

public class IndustryAllUI extends JPanel {

	private static final long serialVersionUID = -7396167631419255918L;
	private int number;
	private MainUIService m;
	private Color background = new Color(255, 255, 255);
	private Color color1 = new Color(205, 16, 118);
	private Color color2 = new Color(238, 44, 44);
	private Color color3 = new Color(255, 127, 0);
	private Color color4 = new Color(72, 118, 255);

	public IndustryAllUI(MainUIService m) {
		this.setLayout(null);
		this.setBounds(0, 2 * Start.min_close, Start.width - Start.sideWidth, Start.hight - 3 * Start.min_close);

		JPanel layer = new JPanel();
		layer.setLayout(null);
		layer.setBounds(0, 0, Start.width - Start.sideWidth - Start.scroll_width, Start.hight - 3 * Start.min_close);
		layer.setBackground(Color.GRAY);
		JScrollPane scroll = new JScrollPane(layer);
		scroll.setBounds(0, 0, Start.width - Start.sideWidth, Start.hight - 3 * Start.min_close);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		// 重定义样式
		MyScrollPane render = new MyScrollPane();
		scroll.getVerticalScrollBar().setUI(render);
		render.setscrollbar();
		this.add(scroll);

		Font labelfont = new Font(Start.font_name, Font.PLAIN, Start.font21);
		JListener lis = new JListener();
		this.m = m;

		GetAllIndustriesService service = new GetAllIndustries();
		ArrayList<String> names = service.getIndustryNames();
		number = names.size();

		IndustryLabel[] labels = new IndustryLabel[number];
		LinkedList<Integer> row = new LinkedList<Integer>();
		LinkedList<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();

		int x = 2 * Start.industry_line_height;
		int y = Start.industry_line_height;
		int count = 2;
		int perfer = 16;

		for (int i = 0; i < number; i++) {
			if(names.get(i).equals("酒店即餐饮"))
				continue;
			labels[i] = new IndustryLabel();
			labels[i].setText(names.get(i));
			labels[i].setFont(labelfont);
			int len = names.get(i).length() * Start.font35;
			labels[i].setSize(new Dimension(len, Start.industry_line_height));
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setForeground(background);
			labels[i].addMouseListener(lis);
			
			if ((x + len > Start.width - Start.sideWidth - 3 * Start.industry_line_height - Start.scroll_width)) {
				x = 2 * Start.industry_line_height;
				y = y + 2 * Start.industry_line_height;
				count += 2;
				list.add(row);
				row = new LinkedList<Integer>();
			}
			row.add(i);

			if ((count + 1) > perfer) {
				perfer += 2;
			}
			labels[i].setLocation(x, y);
			x = x + len + Start.industry_line_height;
		}
		list.add(row);
		int size = list.size() - 1;

		int row_r1 = (color1.getRed() - color3.getRed()) / size;
		int row_g1 = (color1.getGreen() - color3.getGreen()) / size;
		int row_b1 = (color1.getBlue() - color3.getBlue()) / size;

		int row_r2 = (color4.getRed() - color2.getRed()) / size;
		int row_g2 = (color4.getGreen() - color2.getGreen()) / size;
		int row_b2 = (color4.getBlue() - color2.getBlue()) / size;

		int line_r = 0;
		int line_g = 0;
		int line_b = 0;
		for (int i = 0; i < list.size(); i++) {
			LinkedList<Integer> column = list.get(i);
			int len1 = column.size() - 1;
			if (len1 > 0) {
				line_r = (color2.getRed() + row_r2 * i - color3.getRed() - row_r1 * i) / len1;
				line_g = (color2.getGreen() + row_g2 * i - color3.getGreen() - row_g1 * i) / len1;
				line_b = (color2.getBlue() + row_b2 * i - color3.getBlue() - row_b1 * i) / len1;
			} else {
				line_r = 0;
				line_g = 0;
				line_b = 0;
			}

			int tmp_r = color3.getRed() + row_r1 * i;
			int tmp_g = color3.getGreen() + row_g1 * i;
			int tmp_b = color3.getBlue() + row_b1 * i;
			for (int j = 0; j <= len1; j++) {
				int index = column.get(j);
				labels[index].setBackground(new Color(tmp_r + line_r * j, tmp_g + line_g * j, tmp_b + line_b * j));
				layer.add(labels[index]);
			}
		}
		layer.setPreferredSize(
				new Dimension(Start.width - Start.sideWidth - Start.scroll_width, perfer * Start.industry_line_height));
		layer.updateUI();
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			m.jumpToIndustrySpecificUI(label.getText());
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			IndustryLabel label = (IndustryLabel) e.getSource();
			Color c = label.getForeground();
			label.setForeground(label.getBackground());
			label.setBackground(c);
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			IndustryLabel label = (IndustryLabel) e.getSource();
			Color c = label.getForeground();
			label.setForeground(label.getBackground());
			label.setBackground(c);
			label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

	}
}

package presentation.basicUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presentation.main.MainUIService;
import presentation.main.Start;

public class IndustryUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel container, optionpane;
	private JLabel top20, all;
	private boolean top20isclicked, allisclicked = false;
	private CardLayout card;
	private IndustryTop20UI top20pane;
	private IndustryAllUI allpane;

	public IndustryUI(MainUIService m) {
		this.setLayout(null);
		this.setSize(Start.width - Start.sideWidth, Start.hight - Start.min_close);

		initoptionpane();

		card = new CardLayout();
		container = new JPanel();
		container.setLayout(card);
		container.setBounds(0, 2 * Start.min_close, Start.width - Start.sideWidth, Start.hight - 3 * Start.min_close);
		this.add(container);

		top20pane = new IndustryTop20UI(m);
		container.add("top20", top20pane);
		card.show(container, "top20");

		allpane = new IndustryAllUI(m);
		container.add("all", allpane);
	}

	private void initoptionpane() {
		Font labelfont = new Font(Start.font_name, Font.BOLD, Start.font20);
		JListener lis = new JListener();

		optionpane = new JPanel();
		optionpane.setLayout(null);
		optionpane.setBackground(Start.dark_color);
		optionpane.setBounds(0, 0, Start.width - Start.sideWidth, 2 * Start.min_close);
		// optionpane.setOpaque(false);
		this.add(optionpane);

		top20isclicked = true;
		top20 = new JLabel("TOP20");
		top20.setBounds(Start.distance, Start.min_close * 2 - Start.letter_button, Start.letter_button_len_4,
				Start.letter_button);
		top20.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
		top20.setHorizontalAlignment(JLabel.CENTER);
		top20.setForeground(new Color(0, 191, 255));
		top20.setOpaque(false);
		top20.setFont(labelfont);
		top20.addMouseListener(lis);
		optionpane.add(top20);

		all = new JLabel("全部行业");
		all.setBounds(Start.distance + Start.letter_button_len_4 + Start.gap, Start.min_close * 2 - Start.letter_button,
				Start.letter_button_len_4, Start.letter_button);
		all.setHorizontalAlignment(JLabel.CENTER);
		all.setForeground(new Color(240, 240, 240));
		all.setOpaque(false);
		all.setFont(labelfont);
		all.addMouseListener(lis);
		optionpane.add(all);

	}

	private void jumpToTop20() {
		top20isclicked = true;
		allisclicked = false;
		top20.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
		top20.setForeground(new Color(0, 191, 255));
		all.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(240, 240, 240)));
		all.setForeground(new Color(240, 240, 240));
		card.show(container, "top20");
	}

	public void jumpToAll() {
		top20isclicked = false;
		allisclicked = true;
		all.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
		all.setForeground(new Color(0, 191, 255));
		top20.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(240, 240, 240)));
		top20.setForeground(new Color(240, 240, 240));
		card.show(container, "all");
	}

	private class JListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == top20) {
				top20.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
				all.setBorder(null);
				jumpToTop20();

			} else if (e.getSource() == all) {
				all.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 191, 255)));
				top20.setBorder(null);
				jumpToAll();

			}
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
			// TODO Auto-generated method stub
			if (e.getSource() == top20) {
				top20.setForeground(new Color(0, 191, 255));
				top20.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (e.getSource() == all) {
				all.setForeground(new Color(0, 191, 255));
				all.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == top20) {

				if (!top20isclicked) {
					top20.setForeground(new Color(240, 240, 240));
				}
				top20.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			} else if (e.getSource() == all) {

				if (!allisclicked) {
					all.setForeground(new Color(240, 240, 240));
				}
				all.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
		}

	}
}

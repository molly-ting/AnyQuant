package presentation.tableUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import presentation.main.Start;

/**
 * 
 * 过滤组件
 *
 */
public class Accordion {
	private MyLabel title;
	String id;

	public Accordion(JPanel panel, JLayeredPane lp, TableModel tableModel, int[] width, String[] tableheader,
			int type,String id) {
		title = new MyLabel("filter", (int) (Start.min_close * 2.5), Start.min_close);
		title.setBounds(0, (int) (Start.min_close * 0.5), (int) (Start.min_close * 2.5), Start.min_close);
		panel.add(title);
		Listener listener = new Listener(lp, panel, tableModel, width, tableheader, type);
		title.addMouseListener(listener);
		
		this.id = id;
	}

	private class Listener implements MouseListener {

		JPanel panel;
		JLayeredPane lp;
		TableModel tableModel;
		int[] width;
		String[] tableheader;
		JPanel accorf = null;
		int type;

		public Listener(JLayeredPane lp, JPanel panel, TableModel tableModel, int[] width, String[] tableheader,
				int type) {
			this.width = width;
			this.tableheader = tableheader;
			this.panel = panel;
			this.lp = lp;
			this.tableModel = tableModel;
			this.type = type;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (accorf == null) {
				title.changeIcon(2);
				if (type == 0)
					accorf = new AccordionFrame(title, lp, panel, tableModel, width, tableheader, id);
				else
					accorf = new AccordionFrameOverView(title, lp, panel, tableModel, width, tableheader);
				accorf.requestFocus();
				accorf.setVisible(true);
			} else {
				if (accorf.isVisible()) {
					accorf.setVisible(false);
					title.changeIcon(0);
				} else {
					title.changeIcon(2);
					accorf.setVisible(true);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// title.setCursor(new Cursor(Cursor.HAND_CURSOR));
			if (accorf != null && (!accorf.isVisible())) {
				title.changeIcon(1);
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// title.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			if (accorf != null && accorf.isVisible()) {
				title.changeIcon(2);
			} else {
				title.changeIcon(0);
			}

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}
}

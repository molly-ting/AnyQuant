package presentation.combobox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboPopup;

public class MyComboPopup extends BasicComboPopup {

	private static final long serialVersionUID = -7003078392614562562L;

	@SuppressWarnings("rawtypes")
	public MyComboPopup(JComboBox combo) {
		super(combo);

		setBorder(new LineBorder(new Color(0, 0, 0, 0), 2, true) {
			private static final long serialVersionUID = 6641930367784507052L;

			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				int offs = 1;
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Shape inner = new Rectangle2D.Float(x + offs - 1, y, width - offs, height);
				g2d.setColor(new Color(10, 10, 10));
				g2d.fill(inner);
				g2d.draw(inner);
			}
		});
	}

	protected JScrollPane createScroller() {
		JScrollPane scroll = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		MyComboBoxScroll sp = new MyComboBoxScroll();
		scroll.getVerticalScrollBar().setUI(sp);
		sp.setscrollbar();
		return scroll;
	}

}

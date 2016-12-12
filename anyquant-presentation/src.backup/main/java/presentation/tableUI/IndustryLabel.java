package presentation.tableUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

import presentation.main.Start;

public class IndustryLabel extends JLabel {

	private static final long serialVersionUID = -4132119142870043478L;
	private Shape shape;

	public IndustryLabel() {
		setOpaque(false); // As suggested by @AVD in comment.
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Start.round, Start.round);
		super.paintComponent(g);
	}

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, Start.round, Start.round);
		}
		return shape.contains(x, y);
	}
}

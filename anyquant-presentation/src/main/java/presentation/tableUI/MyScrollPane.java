package presentation.tableUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import presentation.main.Start;

/**
 * 重绘scrollpane
 * @author 云奎
 *
 */
public class MyScrollPane extends BasicScrollBarUI {

	public JButton getincrButton() {
		return incrButton;
	}

	public JButton getdecrButton() {
		return decrButton;
	}

	public JScrollBar getscrollbar() {
		return scrollbar;
	}

	public void setscrollbar() {
		Dimension d = scrollbar.getSize();
		Point p = scrollbar.getLocation();
		
		if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			int axis_x = p.x + d.width - Start.scroll_width;
			scrollbar.setLocation(axis_x, p.y);
			scrollbar.setPreferredSize(new Dimension(Start.scroll_width, d.height));
		} else if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			int axis_y = p.y + d.height - Start.scroll_width;
			scrollbar.setLocation(p.x,axis_y);
			scrollbar.setPreferredSize(new Dimension(d.width, Start.scroll_width));
		}
		
		scrollbar.setOpaque(false);
	}

	@Override
	public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		int th = thumbBounds.height;
		int tw = thumbBounds.width;
		g.translate(thumbBounds.x, thumbBounds.y);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = null;
		Color color = new Color(115, 115, 115);
		if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			tw = Start.scroll_width;
			gp = new GradientPaint(0, 0, color, tw, 0, color);
		} else if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			th = Start.scroll_width;
			gp = new GradientPaint(0, 0, color, 0, th, color);
		}
		int thumbx = thumbBounds.x;
		int thumby = thumbBounds.y;
		this.setThumbBounds(thumbx, thumby, tw, th);
		g2.setPaint(gp);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.addRenderingHints(rh);
		// 半透明
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.9f));
		g2.fillRect(0, 0, tw, th);
		g2.setColor(color);

	}

	@Override
	public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		int th = trackBounds.height;
		int tw = trackBounds.width;
		g.setColor(new Color(150,150,150));
		g.fillRect(trackBounds.x, trackBounds.y, tw, th);
	}

	@Override
	protected void paintDecreaseHighlight(Graphics g) {
		g.setColor(new Color(0, 134, 139));
		int x = this.getTrackBounds().x;
		int y = this.getTrackBounds().y;
		int w = 0, h = 0;
		if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			w = this.getThumbBounds().width;
			h = this.getThumbBounds().y - y;

		}
		if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			w = this.getThumbBounds().x - x;
			h = this.getThumbBounds().height;
		}
		g.fillRect(x, y, w, h);
	}

	@Override
	protected void paintIncreaseHighlight(Graphics g) {
		g.setColor(new Color(0, 134, 139, 100));
		int x = this.getThumbBounds().x;
		int y = this.getThumbBounds().y;
		int w = this.getTrackBounds().width;
		int h = this.getTrackBounds().height;
		g.fillRect(x, y, w, h);
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(0,0));
		button.setVisible(false);
		return button;
	}

	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(0,0));
		button.setVisible(false);
		return button;
	}
}

package presentation.main;

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
			int axis_x = p.x + d.width - 14;
			scrollbar.setLocation(axis_x, p.y);
			scrollbar.setPreferredSize(new Dimension(12, d.height));
		} else if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			int axis_y = p.y + d.height - 14;
			scrollbar.setLocation(p.x,axis_y);
			scrollbar.setPreferredSize(new Dimension(d.width, 12));
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
		Color color = new Color(122, 139, 139);
		if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			tw = 13;
			gp = new GradientPaint(0, 0, color, tw, 0, color);
		} else if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			th = 13;
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
				0.7f));
		g2.fillRect(0, 0, tw, th);
		g2.setColor(color);

	}

	@Override
	public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		/*
		 * Graphics2D g2 = (Graphics2D) g; GradientPaint gp = null; Color color
		 * = Color.BLUE; int h = trackBounds.height;
		 * //trackBounds.setLocation(20, 0); //trackBounds.setSize(20, h); int w
		 * = trackBounds.width;
		 * 
		 * if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) { gp =
		 * new GradientPaint(0, 0, color, w, 0, color); } if
		 * (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) { gp = new
		 * GradientPaint(0, 0, color, 0, h, color); } g2.setPaint(gp);
		 * //AlphaComposite ac =
		 * AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
		 * //g2.setComposite(ac); g2.fillRect(trackBounds.x+20, trackBounds.y,
		 * w,h); g2.setColor(color); g2.drawRect(trackBounds.x+20,
		 * trackBounds.y, w - 1, h - 1);
		 */
		/*if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT)
			this.paintDecreaseHighlight(g);
		if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT)
			this.paintIncreaseHighlight(g);*/
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
		//Insets insets = scrollbar.getInsets();
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
		button.setSize(0, 0);
		button.setVisible(false);
		return button;
		/*
		 * {// 重绘按钮的三角标记 /*public void paintTriangle(Graphics g, int x, int y,
		 * int size, int direction, boolean isEnabled) { Graphics2D g2 =
		 * (Graphics2D) g; GradientPaint gp = null; Image arrowImg = null;
		 * switch (this.getDirection()) { case BasicArrowButton.SOUTH: gp = new
		 * GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new
		 * Color(207, 190, 164)); arrowImg = ImageLoader.get("south.gif");
		 * break; case BasicArrowButton.EAST: gp = new GradientPaint(0, 0, new
		 * Color(242, 222, 198), 0, getHeight(), new Color(207, 190, 164));
		 * arrowImg = ImageLoader.get("east.gif"); break; } g2.setPaint(gp);
		 * g2.fillRect(0, 0, getWidth(), getHeight()); g2.setColor(frameColor);
		 * g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		 * g2.drawImage(arrowImg, (getWidth() - 2) / 2 - 5, (getHeight() - 2) /
		 * 2 - 5, 13, 13, null); } };
		 */
	}

	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton();
		button.setSize(0, 0);
		button.setVisible(false);
		return button;
		/*
		 * {public void paintTriangle(Graphics g, int x, int y, int size, int
		 * direction, boolean isEnabled) { Graphics2D g2 = (Graphics2D) g;
		 * GradientPaint gp = null; Image arrowImg = null; switch
		 * (this.getDirection()) { case BasicArrowButton.NORTH: gp = new
		 * GradientPaint(0, 0, new Color(242, 222, 198), getWidth(), 0, new
		 * Color(207, 190, 164)); arrowImg = ImageLoader.get("north.gif");
		 * break; case BasicArrowButton.WEST: gp = new GradientPaint(0, 0, new
		 * Color(242, 222, 198), 0, getHeight(), new Color(207, 190, 164));
		 * arrowImg = ImageLoader.get("west.gif"); break; } g2.setPaint(gp);
		 * g2.fillRect(0, 0, getWidth(), getHeight()); g2.setColor(frameColor);
		 * g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		 * g2.drawImage(arrowImg, (getWidth() - 2) / 2 - 5, (getHeight() - 2) /
		 * 2 - 5, 13, 13, null); } };
		 */
	}
}

package presentation.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

/**
 * 窗体拖动
 * @author 云奎
 *
 */
public class MouseEventListener implements MouseInputListener {
	JFrame frame;
	Point origin;

	public MouseEventListener(JFrame f) {
		this.frame = f;
		origin = new Point();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * 记录鼠标按下时的点
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		origin.x = e.getX();
		origin.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * 鼠标在标题栏拖拽时，设置窗口的坐标位置 窗口新的坐标位置 = 移动前坐标位置+（鼠标指针当前坐标-鼠标按下时指针的位置）
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = frame.getLocation();
		int x = e.getX() - origin.x;
		int y = e.getY() - origin.y;
		frame.setLocation(p.x + x, p.y + y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}

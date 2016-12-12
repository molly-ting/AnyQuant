package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import presentation.main.MainUIService;

/**
 * 股票列表信息的表格的监听
 * 
 * @author 云奎
 *
 */
public class ShareOverviewMouseListener implements MouseListener {

	MainUIService m;

	public ShareOverviewMouseListener(MainUIService m) {
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel line = (JLabel) e.getSource();
		line.setBackground(Color.GRAY);
		String id = line.getName();
		m.jumpToStockInfoUI(id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel line = (JLabel) e.getSource();
		line.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 3));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel line = (JLabel) e.getSource();
		line.setBorder(null);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

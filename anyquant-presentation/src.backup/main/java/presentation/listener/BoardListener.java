package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BoardListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel board = (JLabel) e.getSource();
		board.setBorder(BorderFactory.createLineBorder(new Color(200,200,200,180), 2));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel board = (JLabel) e.getSource();
		board.setBorder(null);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

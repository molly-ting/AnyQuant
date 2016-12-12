package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import presentation.tableUI.RoundJTextField;

public class StartDateListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		RoundJTextField text = (RoundJTextField) e.getSource();
		text.setBackground(new Color(0, 245, 127));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		RoundJTextField text = (RoundJTextField) e.getSource();
		text.setBackground(new Color(240, 240, 240));
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

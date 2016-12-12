package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import presentation.main.Start;
import presentation.tableUI.RoundJTextField;

public class MaxTextListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		RoundJTextField text = (RoundJTextField) e.getSource();
		text.setBackground(new Color(255, 220, 255));
		text.setBorder(BorderFactory.createMatteBorder(0, Start.round, 0, 0, new Color(255, 220, 255)));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		RoundJTextField text = (RoundJTextField) e.getSource();
		text.setBackground(new Color(240, 240, 240));
		text.setBorder(BorderFactory.createMatteBorder(0, Start.round, 0, 0, new Color(240, 240, 240)));
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

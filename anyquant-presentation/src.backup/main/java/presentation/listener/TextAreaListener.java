package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class TextAreaListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JTextArea txt = (JTextArea) e.getSource();
		txt.setBorder(BorderFactory.createLineBorder(new Color(200,200,200,180), 2));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JTextArea txt = (JTextArea) e.getSource();
		txt.setBorder(null);
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

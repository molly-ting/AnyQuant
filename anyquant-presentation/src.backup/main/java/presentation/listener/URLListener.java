package presentation.listener;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class URLListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(label.getName()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		label.setBorder(BorderFactory.createLineBorder(new Color(200,200,200,160), 2));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		label.setBorder(null);
		
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

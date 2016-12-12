package presentation.listener;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.main.MainUIService;
import presentation.main.Start;

/**
 * 搜索按钮监听
 * @author 云奎
 *
 */
public class SearchButtonListener implements MouseListener {

	private MainUIService m;
	private JTextField txtInput;

	public SearchButtonListener(MainUIService m, JTextField txtInput) {
		this.m = m;
		this.txtInput = txtInput;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String id = txtInput.getText();
		m.jumpToStockInfoUI(id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		ImageIcon image = new ImageIcon("picture/search2.png");
		image.setImage(image.getImage().getScaledInstance(Start.search_textfield_hei, Start.search_textfield_hei,
				Image.SCALE_SMOOTH));
		button.setIcon(image);
		button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel button = (JLabel) e.getSource();
		ImageIcon image = new ImageIcon("picture/search1.png");
		image.setImage(image.getImage().getScaledInstance(Start.search_textfield_hei, Start.search_textfield_hei,
				Image.SCALE_SMOOTH));
		button.setIcon(image);
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

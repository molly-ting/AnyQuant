package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

/**
 * 搜索框鼠标监听
 * @author 云奎
 *
 */
public class SearchTextFieldListener implements MouseListener {

	private boolean isAdded = true;
	public SearchTextFieldListener(){
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField txtInput = (JTextField) e.getSource();
		txtInput.setBackground(Color.WHITE);
		txtInput.removeMouseListener(this);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JTextField txtInput = (JTextField) e.getSource();
		txtInput.setBackground(new Color(140, 140, 140));
		txtInput.setFocusable(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JTextField txtInput = (JTextField) e.getSource();
		txtInput.setBackground(new Color(170, 170, 170));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public boolean getState(){
		return isAdded;
	}
	
	public void setState(boolean add){
		isAdded = add;
	}
}

package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import presentation.main.Start;

/**
 * 用于让搜索框的失去焦点
 * @author 云奎
 *
 */
public class PanelMouseListener implements MouseListener {

	SearchTextFieldListener listener;
	SearchTextFocusListener fListener;
	JTextField txtInput;

	public PanelMouseListener(SearchTextFieldListener listener,SearchTextFocusListener fListener, JTextField txtInput) {
		this.listener = listener;
		this.txtInput = txtInput;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!listener.getState()) {
			if (e.getXOnScreen() < txtInput.getX()
					|| e.getXOnScreen() > (txtInput.getX() + Start.search_textfield_len)
					|| e.getYOnScreen() > txtInput.getY()
							|| e.getYOnScreen() < (txtInput.getY() + Start.search_textfield_hei)) {
				txtInput.setBackground(new Color(170, 170, 170));
				txtInput.addMouseListener(listener);
				txtInput.setText("股票代码");
				txtInput.setFocusable(false);
				//panel.updateUI();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

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

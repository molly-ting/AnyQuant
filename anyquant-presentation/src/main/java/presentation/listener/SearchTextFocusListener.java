package presentation.listener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * 搜索框焦点监听
 * @author 云奎
 *
 */
public class SearchTextFocusListener implements FocusListener{

	SearchTextFieldListener listener;
	
	public SearchTextFocusListener(SearchTextFieldListener listener){
		this.listener = listener;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		JTextField txtInput = (JTextField) e.getSource();
		txtInput.setText("");
		txtInput.setBackground(Color.WHITE);
		listener.setState(false);
		//txtInput.removeMouseListener(listener);
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txtInput = (JTextField) e.getSource();
		txtInput.setBackground(new Color(170, 170, 170));
		txtInput.addMouseListener(listener);
		txtInput.setFocusable(false);
	}

}

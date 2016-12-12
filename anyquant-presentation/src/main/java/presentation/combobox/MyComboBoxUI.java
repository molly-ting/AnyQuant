package presentation.combobox;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

public class MyComboBoxUI extends BasicComboBoxUI {

	private JButton arrow;

	public MyComboBoxUI() {
		super();
	}

	protected JButton createArrowButton() {
		arrow = new JButton();
		//arrow.setIcon(new ImageIcon("picture/search1.png"));
		arrow.setRolloverEnabled(true);
		arrow.setBorder(null);
		arrow.setOpaque(true);
		arrow.setContentAreaFilled(false);
		return arrow;
	}

	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
		super.paintCurrentValue(g, bounds, hasFocus);
	}

	protected ComboPopup createPopup() {
		return new MyComboPopup(comboBox);
	}

}

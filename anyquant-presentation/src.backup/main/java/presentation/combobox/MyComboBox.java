package presentation.combobox;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

import presentation.main.Start;

@SuppressWarnings("rawtypes")
public class MyComboBox extends JComboBox {
	private static final long serialVersionUID = 5446635498115166151L;

	public MyComboBox() {
		super();
		init();
	}

	@SuppressWarnings("unchecked")
	public MyComboBox(ComboBoxModel model) {
		super(model);
		init();
	}

	@SuppressWarnings("unchecked")
	public MyComboBox(Object[] items) {
		super(items);
		init();
	}

	@SuppressWarnings("unchecked")
	public MyComboBox(Vector<?> items) {
		super(items);
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		this.setOpaque(false);
		this.setFont(new Font(Start.font_name,Font.PLAIN,Start.font21));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setPreferredSize(new Dimension(120, 33));
		this.setMaximumRowCount(6);
		this.setUI(new MyComboBoxUI());
		this.setRenderer(new MyComboBoxRender());
	}
}

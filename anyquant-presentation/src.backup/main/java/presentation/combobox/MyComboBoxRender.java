package presentation.combobox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import presentation.main.Start;

@SuppressWarnings("rawtypes")
public class MyComboBoxRender extends JLabel implements ListCellRenderer {
	private static final long serialVersionUID = 4340414652462894053L;
	private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	public MyComboBoxRender() {
		super();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultCellRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		renderer.setText(value.toString());
		renderer.setPreferredSize(new Dimension(Start.sort_width - Start.scroll_width, Start.line_height / 5 * 4));
		renderer.setFont(new Font(Start.font_name, Font.PLAIN, Start.font21));
		Color background;
		Color select;

		background = new Color(45, 45, 45);
		select = new Color(10, 10, 10);

		if (isSelected) {
			renderer.setForeground(new Color(240,248,255));
			renderer.setBackground(select);
			// renderer.setBorder(BorderFactory.createLineBorder(new Color(100,
			// 0, 0),3));
		} else {
			renderer.setForeground(Color.WHITE);
			renderer.setBackground(background);
			// renderer.setBorder(null);
		}

		list.setBackground(background);
		list.setSelectionBackground(background);
		list.setBorder(null);

		return renderer;
	}
}

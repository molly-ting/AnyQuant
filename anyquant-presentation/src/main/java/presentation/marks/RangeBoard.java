package presentation.marks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import presentation.listener.BoardListener;
import presentation.main.Start;

public class RangeBoard extends JLabel {

	private static final long serialVersionUID = 52417186644504231L;

	public RangeBoard(double value) {
		this.setSize(new Dimension(Start.mark_len, Start.mark_len));
		this.setOpaque(true);
		if (value > 0)
			this.setBackground(new Color(184, 27, 27));
		else
			this.setBackground(new Color(51, 153, 51));
		this.addMouseListener(new BoardListener());
		init(value);
	}

	private void init(double value) {
		/**
		 * 缓冲长度
		 */
		int length = 2 * Start.gap;
		int height = Start.mark_len / 4;

		JLabel title1 = new JLabel();
		title1.setBounds(length, height / 2, 3 * Start.mark_len / 2, 2 * height);
		title1.setText(trans(value) + "%");
		title1.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font45));
		title1.setForeground(new Color(255, 255, 255));
		this.add(title1);

		JLabel title3 = new JLabel();
		title3.setBounds(length, 3 * height, Start.mark_len, height);
		title3.setText("涨跌幅");
		title3.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		title3.setForeground(new Color(255, 255, 255));
		this.add(title3);
	}

	private String trans(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}
}

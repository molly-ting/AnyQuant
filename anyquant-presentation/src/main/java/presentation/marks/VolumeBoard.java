package presentation.marks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import presentation.listener.BoardListener;
import presentation.main.Start;

public class VolumeBoard extends JLabel {

	private static final long serialVersionUID = -1185394237997096467L;

	public VolumeBoard(double volume) {
		this.setSize(new Dimension(Start.mark_len, Start.mark_len));
		this.setOpaque(true);
		this.setBackground(new Color(27, 161, 226));
		this.addMouseListener(new BoardListener());
		init(volume);
	}

	private void init(double volume) {
		/**
		 * 缓冲长度
		 */
		int length = 2 * Start.gap;
		int height = Start.mark_len / 4;

		JLabel title1 = new JLabel();
		title1.setBounds(length, height / 2, Start.mark_len - length, 2 * height);
		title1.setText(trans(volume));
		title1.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font35));
		title1.setForeground(new Color(255, 255, 255));
		this.add(title1);

		JLabel title2 = new JLabel();
		title2.setBounds(length, 3 * height, Start.mark_len - length, height);
		title2.setText("成交量");
		title2.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		title2.setForeground(new Color(255, 255, 255));
		this.add(title2);
	}

	private String trans(double value) {
		String tip = "万";
		if (value >= 10000) {
			value /= 10000;
		}
		if (value >= 10000) {
			value /= 10000;
			tip = "亿";
		}
		String result = "";
		if (value >= 1000) {
			DecimalFormat df = new DecimalFormat("#,###.0");
			result = df.format(value) + tip;
		} else {
			DecimalFormat df = new DecimalFormat("#,###.00");
			result = df.format(value) + tip;
		}
		if (value == 0)
			return "0";
		return result;
	}
}

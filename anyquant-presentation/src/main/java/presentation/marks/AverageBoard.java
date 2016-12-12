package presentation.marks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import presentation.listener.BoardListener;
import presentation.main.Start;

public class AverageBoard extends JLabel {

	private static final long serialVersionUID = -1861835493592035183L;

	public AverageBoard(double average) {
		this.setSize(new Dimension(Start.mark_len, Start.mark_len));
		this.setOpaque(true);
		this.setBackground(new Color(240, 150, 9));
		this.addMouseListener(new BoardListener());
		init(average);
	}

	private void init(double average) {
		/**
		 * 缓冲长度
		 */
		int length = 2 * Start.gap;
		int height = Start.mark_len / 4;
		JLabel title1 = new JLabel();
		title1.setBounds(length, height / 2, Start.mark_len, 2 * height);
		title1.setText(trans(average));
		title1.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font35));
		title1.setForeground(new Color(255, 255, 255));
		this.add(title1);

		JLabel title2 = new JLabel();
		title2.setBounds(length, 3 * height, Start.mark_len, height);
		title2.setText("10日移动平均数");
		title2.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		title2.setForeground(new Color(255, 255, 255));
		this.add(title2);
	}

	private String trans(double value) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(value);
	}

}

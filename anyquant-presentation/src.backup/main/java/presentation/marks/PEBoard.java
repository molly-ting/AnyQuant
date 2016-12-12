package presentation.marks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import presentation.listener.BoardListener;
import presentation.main.Start;

/**
 * 市净�?
 * 
 * @author 云奎
 *
 */
public class PEBoard extends JLabel {

	private static final long serialVersionUID = -5502621322135735098L;

	public PEBoard(double pe, boolean isPE) {
		this.setSize(new Dimension(2 * Start.mark_len + Start.gap, Start.mark_len));
		this.setOpaque(true);
		this.setBackground(new Color(255, 0, 151));
		this.addMouseListener(new BoardListener());
		init(pe, isPE);
	}

	private void init(double pe, boolean isPE) {
		/**
		 * 缓冲长度
		 */
		int length = 2 * Start.gap;
		int height = Start.mark_len / 4;
		JLabel title1 = new JLabel();
		title1.setBounds(length, height / 2, 2 * Start.mark_len, 2 * height);
		if (pe > -1001)
			title1.setText(trans(pe));
		else
			title1.setText("--");
		title1.setFont(new Font(Start.font_name, Font.TRUETYPE_FONT, Start.font53));
		title1.setForeground(new Color(255, 255, 255));
		this.add(title1);

		JLabel title2 = new JLabel();
		title2.setBounds(length, 3 * height, Start.mark_len / 2, height);
		if (isPE)
			title2.setText("市盈�?");
		else
			title2.setText("乖离�?");
		title2.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		title2.setForeground(new Color(255, 255, 255));
		this.add(title2);

		JLabel title3 = new JLabel();
		title3.setHorizontalAlignment(JLabel.RIGHT);
		title3.setBounds(Start.mark_len / 2 + length, 3 * height, Start.mark_len * 3 / 2 - length, height);
		if (isPE)
			title3.setText(estimate(pe));
		else
			title3.setText(envaluate(pe));
		title3.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		title3.setForeground(new Color(255, 255, 255));
		this.add(title3);
	}

	private String trans(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}

	private String estimate(double pe) {
		if (pe < 0 && pe > -1000)
			return "亏损";
		else if (pe >= 0 && pe < 25)
			return "价�?�被低估";
		else if (pe >= 25 && pe < 40) {
			return "正常价�??";
		} else if (pe >= 40 && pe < 60) {
			return "价�?�被高估";
		} else if (pe >= 60) {
			return "价�?�泡�?";
		} else {
			return "";
		}
	}

	private String envaluate(double pe) {
		if (pe <= -10)
			return "可能反弹";
		else if (pe >= 8)
			return "建议观望";
		else
			return "正常波动";
	}
}

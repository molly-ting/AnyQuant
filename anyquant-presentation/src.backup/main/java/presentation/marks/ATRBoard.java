package presentation.marks;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import presentation.listener.BoardListener;
import presentation.listener.TextAreaListener;
import presentation.main.Start;

public class ATRBoard extends JLabel {

	private static final long serialVersionUID = 2035274512048279491L;

	public ATRBoard(double average, double close, double tip, double p, boolean isShare) {
		this.setSize(new Dimension(Start.mark_len, Start.mark_len));
		this.setOpaque(true);
		this.setBackground(Start.dark_color);
		this.addMouseListener(new BoardListener());
		init(average, close, tip, p, isShare);
	}

	private void init(double average, double close, double tip, double p, boolean isShare) {
		/**
		 * 缓冲长度
		 */
		JTextArea analyse = new JTextArea();
		analyse.setBounds(0, 0, Start.mark_len, Start.mark_len);
		analyse.setOpaque(false);
		analyse.setEditable(false);
		analyse.setBorder(null);
		analyse.setLineWrap(true);
		analyse.setForeground(Color.WHITE);
		analyse.setFont(new Font(Start.font_name, Font.PLAIN, Start.font18));
		analyse.addMouseListener(new TextAreaListener());
		this.add(analyse);

		String result = "";
		if (average > close) {
			result += "·均线位于价格之上，看跌\n";
		} else {
			result += "·均线位于价格之下，看涨\n";
		}

		if (isShare) {
			if (tip < 0 && tip > -1000)
				result += "·股票亏损\n";
			else if (tip >= 0 && tip < 25)
				result += "·股票价�?�被低估\n";
			else if (tip >= 25 && tip < 40) {
				result += "·股票价�?�正常�??";
			} else if (tip >= 40 && tip < 60) {
				result += "·股票价�?�被高估\n";
			} else if (tip >= 60) {
				result += "·股票价�?�泡沫\n";
			} else {
				result += "\n";
			}
			if (p <= 6) {
				result += "·市净率较低，股票有潜力，建议买入\n";
			} else if (p > 6 && p < 12) {
				result += "·市净率正常，建议少量买入或观望\n";
			} else if (p > 12) {
				result += "·市净率较高，建议离场\n";
			}
		} else {
			result += "\n";
			if (tip <= -10)
				result += "·大盘走势可能反弹";
			else if (tip >= 8)
				result += "·建议离场观望";
			else
				result += "·大盘正常波动，行情较稳定";
		}

		analyse.setText(result);
	}
}

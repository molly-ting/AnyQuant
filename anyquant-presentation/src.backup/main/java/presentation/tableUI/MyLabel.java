package presentation.tableUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
	
	private static final long serialVersionUID = -8697248764566799041L;
	ImageIcon[] icons;

	public MyLabel(String str, int width, int height) {

		if (str.equals("miniicon") || str.equals("closeicon") || str.equals("back")  || str.equals("panelback") || str.equals("panelclose")
				|| str.equals("panelok")|| str.equals("infoclose")) {
			icons = new ImageIcon[2];

			for (int i = 0; i < 2; i++) {
				icons[i] = new ImageIcon("picture/" + str + i + ".png");
				icons[i].setImage(icons[i].getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			}
			this.setIcon(icons[0]);

		} else if (str.equals("filter")) {
			icons = new ImageIcon[3];

			for (int i = 0; i < 3; i++) {
				icons[i] = new ImageIcon("picture/" + str + i + ".png");
				icons[i].setImage(icons[i].getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			}
			this.setIcon(icons[0]);
		} else {
			icons = new ImageIcon[4];

			for (int i = 0; i < 4; i++) {
				icons[i] = new ImageIcon("picture/" + str + i + ".png");
				icons[i].setImage(icons[i].getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			}

			if (str.equals("tape")) {
				this.setIcon(icons[2]);
			} else {
				this.setIcon(icons[0]);
			}
		}

	}

	public void changeIcon(int i) {
		this.setIcon(icons[i]);
	}
}

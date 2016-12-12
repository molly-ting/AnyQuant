package presentation.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyBackgroundPanel extends JPanel{
	private Image image = null;  
	  
    public MyBackgroundPanel() {  
    	image=new ImageIcon("picture/backk.png").getImage();
    }  
  
    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  
}

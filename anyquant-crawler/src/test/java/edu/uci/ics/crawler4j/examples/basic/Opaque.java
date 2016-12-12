package edu.uci.ics.crawler4j.examples.basic;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Opaque {

	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(350, 10, 1000, 800);                                    /** * parameter main panel */
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
//		frame.setOpacity(0.0f);
		frame.setBackground(new Color(0,0,0,0));
		
		
		JLabel label=new JLabel("asdad");
		
		label.setSize(200,200);
		frame.add(label);
		
		
		frame.setVisible(true);
		
		
	}
}

package presentation.listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import presentation.main.MainUIService;

/**
 * 股票详细信息和大盘信息的表格的监听
 * @author 云奎
 *
 */
public class TableMouseListener implements MouseListener{
	
	private MainUIService m;
	private String type;
	
	public TableMouseListener(MainUIService m,String type) {
		this.m = m;
		this.type = type;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(type.equals("shareOverview")){
			JLabel line = (JLabel) e.getSource();
			line.setBackground(Color.GRAY);
			String id = line.getName();
			m.jumpToStockInfoUI(id,type);
			
		}else if(type.equals("industry")){
			JLabel line = (JLabel) e.getSource();
			line.setBackground(Color.GRAY);
			String name = line.getName();
			m.jumpToIndustrySpecificUI(name);
			
		}else if(type.contains("industrydetail")){
			JLabel line = (JLabel) e.getSource();
			line.setBackground(Color.GRAY);
			String id = line.getName();
			int index = type.indexOf("-");
			type = type.substring(index+1);
			m.jumpToStockInfoUI(id,type);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel line = (JLabel) e.getSource();
		line.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 3));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel line = (JLabel) e.getSource();
		line.setBorder(null);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

package presentation.tableUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.combobox.MyComboBox;
import presentation.main.Start;

public class ChartTimeChooser {
	
	private MyComboBox cbInput;
	private String[] options;
	private boolean click = false;
	
	public ChartTimeChooser(JPanel pane,String[] options){
		this.options = options;
		
		JTextField sorttf = new JTextField();
		sorttf.setLayout(new BorderLayout());
		sorttf.setFont(new Font(Start.font_name, Font.PLAIN, Start.font21));
		sorttf.setText(options[0]);
		sorttf.setBorder(null);
		sorttf.setEditable(false);
		sorttf.setForeground(Color.LIGHT_GRAY);
		sorttf.setBackground(new Color(60, 60, 60));
		sorttf.setBounds(Start.width - Start.sideWidth - Start.sort_width - Start.distance,
				(int) (Start.min_close * 0.3), Start.sort_width, Start.sort_height);
		sorttf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		pane.add(sorttf);

		JLabel button = new JLabel();
		button.setPreferredSize(new Dimension(Start.sort_height / 2, Start.sort_height));
		button.setIcon(new ImageIcon("picture/sort.png"));
		button.setOpaque(false);
		sorttf.add(button, BorderLayout.EAST);
		
		Listener listener = new Listener(sorttf);
		sorttf.addMouseListener(listener);
		setupAutoComplete(sorttf);
		button.addMouseListener(listener);

		CListener cListener = new CListener(sorttf);
		pane.addMouseListener(cListener);
	}
	
	/**
	 * 搜索框下面的提示栏
	 * 
	 * @param txtInput
	 */
	public void setupAutoComplete(JTextField txtInput) {
		
		cbInput = new MyComboBox(options) {
			private static final long serialVersionUID = 4834548239247255419L;

			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};

		cbInput.setSelectedItem(null);
		cbInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbInput.getSelectedItem() != null) {
					txtInput.setBackground(new Color(60, 60, 60));
					txtInput.setText(" " + cbInput.getSelectedItem().toString());
					click = false;
					
				}
			}
		});

		txtInput.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (cbInput.isPopupVisible()) {
						e.setKeyCode(KeyEvent.VK_ENTER);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_DOWN) {
					e.setSource(cbInput);
					cbInput.dispatchEvent(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						txtInput.setBackground(new Color(60, 60, 60));
						txtInput.setText(" " + cbInput.getSelectedItem().toString());
						cbInput.setPopupVisible(false);
						click = false;
						
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					txtInput.setBackground(new Color(60, 60, 60));
					click = false;
					cbInput.setPopupVisible(false);
				}
			}
		});
		txtInput.add(cbInput, BorderLayout.SOUTH);
	}

	private class Listener implements MouseListener {

		JTextField txtInput;

		public Listener(JTextField txtInput) {
			this.txtInput = txtInput;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!click) {
				txtInput.setBackground(new Color(45, 45, 45));
				cbInput.setPopupVisible(true);
				click = true;
			} else {
				txtInput.setBackground(new Color(60, 60, 60));
				click = false;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (!click) {
				txtInput.setBackground(new Color(45, 45, 45));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (!click) {
				txtInput.setBackground(new Color(60, 60, 60));
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	private class CListener implements MouseListener {

		JTextField txtInput;

		public CListener(JTextField txtInput) {
			this.txtInput = txtInput;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			txtInput.setBackground(new Color(65, 65, 65));
			click = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
}

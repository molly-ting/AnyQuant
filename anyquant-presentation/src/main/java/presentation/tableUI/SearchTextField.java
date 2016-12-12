package presentation.tableUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import blService.getInfoBLService.GetShareBLService;
import businessLogic.getInfoBL.Share;
import presentation.combobox.MyComboBox;
import presentation.listener.PanelMouseListener;
import presentation.listener.SearchButtonListener;
import presentation.listener.SearchTextFieldListener;
import presentation.listener.SearchTextFocusListener;
import presentation.main.MainUIService;
import presentation.main.Start;

/**
 * 
 * 搜索框
 *
 */
public class SearchTextField {

	/**
	 * 输入用的textfield
	 */
	JTextField txtInput = new JTextField();
	private Color color1 = new Color(170, 170, 170);
	private MainUIService m;

	public SearchTextField(MainUIService m, JPanel title) {
		this.m = m;
		// 定义textfield样式
		txtInput.setFont(new Font(Start.font_name, Font.PLAIN, Start.font23));
		txtInput.setText("股票代码");
		setupAutoComplete(txtInput);
		txtInput.setColumns(30);
		txtInput.setBounds(Start.width - Start.sideWidth - Start.search_textfield_len - Start.distance,
				Start.min_close - Start.search_textfield_hei / 2, Start.search_textfield_len,
				Start.search_textfield_hei);
		txtInput.setBorder(null);
		txtInput.setBackground(color1);
		title.add(txtInput, new Integer(100));
		// 添加鼠标监听
		SearchTextFieldListener listener = new SearchTextFieldListener();
		txtInput.addMouseListener(listener);
		// 添加焦点监听
		SearchTextFocusListener fListener = new SearchTextFocusListener(listener);
		txtInput.addFocusListener(fListener);
		// 给panel添加鼠标监听
		PanelMouseListener pListener = new PanelMouseListener(listener, fListener, txtInput);
		title.addMouseListener(pListener);
		// 搜索按钮
		JLabel searchButton = new JLabel();
		ImageIcon image = new ImageIcon("picture/search1.png");
		image.setImage(image.getImage().getScaledInstance(Start.search_textfield_hei, Start.search_textfield_hei,
				Image.SCALE_SMOOTH));
		searchButton.setIcon(image);
		searchButton.setSize(Start.search_textfield_hei, Start.search_textfield_hei);
		SearchButtonListener sListener = new SearchButtonListener(m, txtInput);
		searchButton.addMouseListener(sListener);
		txtInput.add(searchButton, BorderLayout.EAST);
	}

	/**
	 * 
	 * @return 获得输入的股票代码
	 */
	public String getID() {
		return txtInput.getText();
	}

	private boolean isAdjusting(JComboBox<String> cbInput) {
		if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
			return (Boolean) cbInput.getClientProperty("is_adjusting");
		}
		return false;
	}

	private void setAdjusting(JComboBox<String> cbInput, boolean adjusting) {
		cbInput.putClientProperty("is_adjusting", adjusting);
	}

	/**
	 * 搜索框下面的提示栏
	 * 
	 * @param txtInput
	 */
	@SuppressWarnings("unchecked")
	public void setupAutoComplete(JTextField txtInput) {
		MyComboBox cbInput = new MyComboBox() {
			private static final long serialVersionUID = 4834548239247255419L;

			public Dimension getPreferredSize() {
				return new Dimension(super.getPreferredSize().width, 0);
			}
		};
		setAdjusting(cbInput, false);

		cbInput.setSelectedItem(null);
		cbInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isAdjusting(cbInput)) {
					if (cbInput.getSelectedItem() != null) {
						txtInput.setSelectionColor(color1);
						String id = cbInput.getSelectedItem().toString();
						txtInput.setText(id);
						m.jumpToStockInfoUI(id);
					}
				}
			}
		});

		txtInput.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				setAdjusting(cbInput, true);
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
						if (cbInput.getSelectedItem() != null) {
							txtInput.setSelectionColor(color1);
							cbInput.setPopupVisible(false);
							String id = cbInput.getSelectedItem().toString();
							txtInput.setText(id);
							m.jumpToStockInfoUI(id);
						} else {
							txtInput.setSelectionColor(color1);
							cbInput.setPopupVisible(false);
							String id = txtInput.getText();
							m.jumpToStockInfoUI(id);
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					txtInput.setSelectionColor(color1);
					cbInput.setPopupVisible(false);
				}
				setAdjusting(cbInput, false);
			}
		});
		txtInput.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			public void changedUpdate(DocumentEvent e) {
				updateList();
			}

			private void updateList() {
				setAdjusting(cbInput, true);
				cbInput.removeAllItems();
				String input = txtInput.getText();
				if (!input.equals("")) {
					GetShareBLService share = new Share();
					ListIterator<String> list = share.getNameList(input);
					while (list.hasNext()) {
						cbInput.addItem(list.next());
					}
				}
				cbInput.setPopupVisible(cbInput.getItemCount() > 0);
				setAdjusting(cbInput, false);
			}
		});
		txtInput.setLayout(new BorderLayout());
		txtInput.add(cbInput, BorderLayout.SOUTH);
	}

}

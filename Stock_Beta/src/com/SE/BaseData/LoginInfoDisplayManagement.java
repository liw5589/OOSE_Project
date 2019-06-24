package com.SE.BaseData;
import com.SE.main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class LoginInfoDisplayManagement extends JPanel {
	private Main mainFrame;
	private JTextField userCodeInputText;
	private JButton loginButton;
	private JButton cancelButton;
	private MainPanel mainPanel;
	/**
	 * Create the panel.
	 */
	public LoginInfoDisplayManagement(Main mainFrame,SettingInfo settingInfo) {
		
		setLayout(null);
		
		this.mainFrame = mainFrame;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		loginButton = new JButton("\uC811\uC18D\uD558\uAE30");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userCode = userCodeInputText.getText();
				 mainPanel.setLoginCode(userCode);
				LoginInfoManagement mgmt = new LoginInfoManagement();
				try {
					boolean loginState=mgmt.login(userCode);
					if(loginState == true)
					{
						mainFrame.change();
						mainFrame.setSize(500,350);
					}
					else {
						JOptionPane.showMessageDialog(null, "사용자 코드가 잘못 되었습니다.", "error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		loginButton.setBounds(246, 235, 91, 53);
		add(loginButton);
		
		cancelButton = new JButton("\uC885\uB8CC\uD558\uAE30");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setBounds(345, 235, 91, 53);
		add(cancelButton);
		
		userCodeInputText = new JTextField();
		userCodeInputText.setFont(new Font("굴림", Font.PLAIN, 20));
		userCodeInputText.setBounds(166, 97, 270, 34);
		add(userCodeInputText);
		userCodeInputText.setColumns(10);
		
		JLabel userCodeLabel = new JLabel("\uC0AC\uC6A9\uC790 \uCF54\uB4DC : ");
		userCodeLabel.setFont(new Font("굴림", Font.BOLD, 20));
		
		userCodeLabel.setBounds(14, 61, 162, 103);
		add(userCodeLabel);
	}
	public void setLoginCode(MainPanel mainPanel )
	{
		this.mainPanel = mainPanel;
	}


	
}

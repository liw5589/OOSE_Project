package com.SE.BaseData;
import com.SE.main.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

public class BaseDataDisplayManagement extends JFrame {

	/**
	 * Create the panel.
	 */
	private JPanel contentPane;
	private JTextField userCodeText;
	private JTextField userNameText;
	private JTextField phoneNumText;
	private JComboBox accessRightBox;
	private JTable table;
	private JScrollPane scrollpane;
	private JTextField lookupTextField;
	private DefaultTableModel model;
	private String[] accessRightList = {"관리자","약사","근로장학생"}; 
	
	private JTextField discountRateText;
	private JTextField fileSaveRouteText;
	private JPanel panel;
	private String[] fontList = {"12","14","18","20","22","24"};
	private String[] themaList = {"white","blue","red","yellow","lightGray","simple"};
	private SettingInfo settingInfo;

	private String loginCode;
	public BaseDataDisplayManagement(SettingInfo settingInfo) {
		this.settingInfo = settingInfo;
		printBaseDataManagementDisplay();
	}
	private void printBaseDataManagementDisplay()
	{
		//frame 생성
		System.out.println(loginCode);
		
		
		setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(700,700);
		setBounds(100, 100, 646, 480);
		
	
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		//User등록 패널 생성
		JPanel UserInsertPanel = new JPanel();
		
		
		JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uB4F1\uB85D");
		lblNewLabel.setBounds(239, 46, 86, 23);
		UserInsertPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("\uC0AC\uC6A9\uC790 \uCF54\uB4DC");
		label.setBounds(64, 114, 67, 15);
		UserInsertPanel.add(label);
		
		userCodeText = new JTextField("AD");
		userCodeText.setBounds(156, 108, 231, 21);
		UserInsertPanel.add(userCodeText);
		userCodeText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setBounds(64, 156, 67, 15);
		UserInsertPanel.add(lblNewLabel_1);
		
		userNameText = new JTextField();
		userNameText.setBounds(156, 153, 231, 21);
		UserInsertPanel.add(userNameText);
		userNameText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setBounds(64, 195, 50, 15);
		UserInsertPanel.add(lblNewLabel_2);
		
		phoneNumText = new JTextField();
		phoneNumText.setBounds(156, 192, 231, 21);
		UserInsertPanel.add(phoneNumText);
		phoneNumText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uC811\uADFC\uAD8C\uD55C");
		lblNewLabel_3.setBounds(64, 230, 50, 15);
		UserInsertPanel.add(lblNewLabel_3);
		
		accessRightBox = new JComboBox(accessRightList);
		accessRightBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accessRight = (String)accessRightBox.getSelectedItem();
				String code="";
				switch(accessRight)
				{
					case "관리자":
						code = "AD";break;
					
					case "약사":
						code = "MD";break;
					case "근로장학생":
						code = "WS";break;
				}
				userCodeText.setText(code);
			}
		});
		accessRightBox.setBounds(156, 226, 85, 23);
		UserInsertPanel.add(accessRightBox);
		
		JButton enrollButton = new JButton("\uB4F1\uB85D");
		enrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userCode = userCodeText.getText();
				String userName = userNameText.getText();
				String phoneNum = phoneNumText.getText();
				String accessRight = (String)accessRightBox.getSelectedItem();
				UserInfoManagement mngt = new UserInfoManagement();
				try {
					mngt.insertUser(userCode, userName, phoneNum, accessRight);
					userCodeText.setText("");
					userNameText.setText("");
					phoneNumText.setText("");
					accessRightBox.setSelectedItem("관리자");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		enrollButton.setBounds(341, 332, 91, 23);
		UserInsertPanel.add(enrollButton);
		
		JButton exitButton = new JButton("\uC885\uB8CC");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		exitButton.setBounds(449, 332, 91, 23);
		UserInsertPanel.add(exitButton);
		tabbedPane.addTab("사용자 등록", null, UserInsertPanel, null);
		UserInsertPanel.setLayout(null);
		
		//User조회/수정패널 생성
		JPanel UserLookupModifyPanel = new JPanel();
		
		String header[] = {"사용자 코드","이름","전화번호","접근 권한"};
		model = new DefaultTableModel(header,0);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		scrollpane = new JScrollPane(table);
		scrollpane.setLocation(45, 79);
		scrollpane.setSize(546,301);
		setBounds(100, 100, 646, 448);
		UserLookupModifyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		table.setBounds(40, 100, 373, 205);
		UserLookupModifyPanel.add(scrollpane);
		
		UserLookupModifyPanel.setLayout(null);
		
		JLabel title = new JLabel("\uC0AC\uC6A9\uC790 \uAD00\uB9AC");
		title.setBounds(279, 10, 79, 29);
		UserLookupModifyPanel.add(title);
		
		JButton deleteButton = new JButton("\uC0AD\uC81C");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
				{
					return;
				}
				else
				{
					int selectRow = table.getSelectedRow(); //행을 위치를 가져옴 
					String firstColumm=(String)table.getValueAt(selectRow, 0); //행의 위치와 칼럼 위치 
					UserInfoManagement mngt = new UserInfoManagement();
					int check=JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까??.", "confirm", JOptionPane.YES_NO_OPTION);
					if(check == JOptionPane.CLOSED_OPTION){
						
					}
					else if(check == JOptionPane.YES_OPTION) {
						mngt.deleteUser(firstColumm);
						model.removeRow(table.getSelectedRow());
						
					}
					else {}
				}
			
			}
		});
		deleteButton.setBounds(43, 34, 91, 35);
		UserLookupModifyPanel.add(deleteButton);
		
		JButton modifyButton = new JButton("\uC218\uC815");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
				{
					return;
				}
				else
				{
					int selectRow = table.getSelectedRow();
					String userCode=(String)table.getValueAt(selectRow, 0);
					String userName = (String)table.getValueAt(selectRow, 1);
					String phoneNum = (String)table.getValueAt(selectRow, 2);
					String accessRight = (String)table.getValueAt(selectRow, 3);
					printUserModifyDisplay(userCode,userName,phoneNum,accessRight);
					
				}
			}
		});
		modifyButton.setBounds(146, 34, 91, 35);
		UserLookupModifyPanel.add(modifyButton);
		
		lookupTextField = new JTextField();
		lookupTextField.setBounds(356, 34, 132, 35);
		UserLookupModifyPanel.add(lookupTextField);
		lookupTextField.setColumns(10);
		
		JButton lookupButton = new JButton("\uC870\uD68C");
		lookupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lookupUserName = lookupTextField.getText();
				
				UserInfoManagement mngt = new UserInfoManagement();
				
				try {
					UserInfo []userInfo=mngt.lookupUser(lookupUserName);
					String inputStr[] = new String[4];
					while(model.getRowCount()!=0) {
						model.removeRow(0);
					}
					for(int i=0;i<userInfo.length-1;i++)
					{	if(userInfo[i].getUserCode() == "")break;
						inputStr[0] = userInfo[i].getUserCode();
						inputStr[1] = userInfo[i].getUserName();
						inputStr[2] = userInfo[i].getPhoneNum();
						inputStr[3] = userInfo[i].getAccessRight();
						model.addRow(inputStr);
					
					}
				
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				
			}
		});
		lookupButton.setBounds(500, 34, 91, 36);
		UserLookupModifyPanel.add(lookupButton);
		tabbedPane.addTab("사용자 조회/수정", null, UserLookupModifyPanel, null);
		//설정관리 패널 
		JPanel SettingInfoDisplayManagementPanel = new JPanel();
		SettingInfoDisplayManagementPanel.setLayout(null);
		
		SettingInfoDisplayManagementPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			JLabel settingTitle = new JLabel("\uAE30\uBCF8 \uC124\uC815 \uAD00\uB9AC");
			settingTitle.setBounds(173, 23, 84, 33);
			SettingInfoDisplayManagementPanel.add(settingTitle);
			JLabel settinglblNewLabel = new JLabel("\uD560\uC778\uC728");
			settinglblNewLabel.setBounds(56, 82, 67, 45);
			settinglblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
			SettingInfoDisplayManagementPanel.add(settinglblNewLabel);
			
			JLabel settinglblNewLabel_2 = new JLabel("\uD3F0\uD2B8");
			settinglblNewLabel_2.setBounds(73, 137, 50, 15);
			settinglblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 16));
			SettingInfoDisplayManagementPanel.add(settinglblNewLabel_2);
			
			
			JLabel settinglblNewLabel_3 = new JLabel("\uD14C\uB9C8");
			settinglblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 16));
			settinglblNewLabel_3.setBounds(73, 184, 50, 15);
			SettingInfoDisplayManagementPanel.add(settinglblNewLabel_3);
			
			discountRateText = new JTextField();
			discountRateText.setBounds(148, 90, 67, 32);
			SettingInfoDisplayManagementPanel.add(discountRateText);
			discountRateText.setColumns(10);
			
			JComboBox fontComboBox = new JComboBox(fontList);
			fontComboBox.setBounds(148, 132, 67, 33);
			fontComboBox.setFont((new Font("굴림", Font.PLAIN, 16)));
			SettingInfoDisplayManagementPanel.add(fontComboBox);
			
			JComboBox themaComboBox = new JComboBox(themaList);
			themaComboBox.setFont(new Font("굴림", Font.PLAIN, 16));
			themaComboBox.setBounds(148, 175, 67, 33);
			SettingInfoDisplayManagementPanel.add(themaComboBox);
			
			JButton okButton = new JButton("확인");
			okButton.setFont(new Font("굴림", Font.PLAIN, 16));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					   String discountRate = discountRateText.getText();
		               String fontSize = (String)fontComboBox.getSelectedItem();
		               String thema = (String)themaComboBox.getSelectedItem();
		               if(discountRate.equals("")||fontSize.equals("")||thema.equals(""))
		               {
		                  JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.", "error", JOptionPane.ERROR_MESSAGE);
		                  return;
		               }
		               
					SettingInfoManagement mngt = new SettingInfoManagement();
					mngt.setupSetting(Integer.parseInt(fontSize),thema,Integer.parseInt(discountRate));
				}
			});
			okButton.setBounds(327, 234, 111, 45);
			SettingInfoDisplayManagementPanel.add(okButton);
			
		
			
		
		tabbedPane.addTab("설정 관리", null, SettingInfoDisplayManagementPanel, null);
	}
	private void printUserModifyDisplay(String userCode,String userName,String phoneNum,String accessRight)
	{
		 JPanel contentPane;
		 JTextField userCodeText;
		 JTextField userNameText;
		 JTextField phoneNumText;
		 JComboBox accessRightBox;
		JFrame modifyFrame = new JFrame();
		
		modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modifyFrame.setVisible(true);
		modifyFrame.setSize(600,600);
		setBounds(100, 100, 566, 428);
		contentPane = new JPanel();
	
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modifyFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uB4F1\uB85D");
		lblNewLabel.setText("사용자 수정");
		lblNewLabel.setBounds(239, 46, 86, 23);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\uC0AC\uC6A9\uC790 \uCF54\uB4DC");
		label.setBounds(64, 114, 67, 15);
		contentPane.add(label);
		
		userCodeText = new JTextField(userCode);
		userCodeText.setBounds(156, 108, 231, 21);
		userCodeText.setEnabled(false);
		contentPane.add(userCodeText);
		userCodeText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setBounds(64, 156, 67, 15);
		contentPane.add(lblNewLabel_1);
		
		userNameText = new JTextField(userName);
		userNameText.setBounds(156, 153, 231, 21);
		contentPane.add(userNameText);
		userNameText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setBounds(64, 195, 50, 15);
		contentPane.add(lblNewLabel_2);
		
		phoneNumText = new JTextField(phoneNum);
		phoneNumText.setBounds(156, 192, 231, 21);
		contentPane.add(phoneNumText);
		phoneNumText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uC811\uADFC\uAD8C\uD55C");
		lblNewLabel_3.setBounds(64, 230, 50, 15);
		contentPane.add(lblNewLabel_3);
		
		accessRightBox = new JComboBox(accessRightList);
		accessRightBox.setEnabled(false);

		accessRightBox.setSelectedItem(accessRight);
		accessRightBox.setBounds(156, 226, 85, 23);
		contentPane.add(accessRightBox);
		
		JButton enrollButton = new JButton("\uB4F1\uB85D");
		enrollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userCode = userCodeText.getText();
				String userName = userNameText.getText();
				String phoneNum = phoneNumText.getText();
				String accessRight = (String)accessRightBox.getSelectedItem();
				UserInfoManagement mngt = new UserInfoManagement();
				mngt.modifyUser(userCode, userName, phoneNum, accessRight);
				modifyFrame.setVisible(false);
				modifyFrame.dispose();
			}
			
		});
		

		enrollButton.setBounds(346, 358, 91, 23);
		contentPane.add(enrollButton);
	
		JButton exitButton = new JButton("\uC885\uB8CC");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyFrame.setVisible(false);
				modifyFrame.dispose();
			}
		});
		exitButton.setBounds(449, 358, 91, 23);
		contentPane.add(exitButton);
	}
	
	
}


package com.SE.main;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.SE.BaseData.BaseDataDisplayManagement;
import com.SE.BaseData.SettingInfo;
import com.SE.SalesPerfomance.SalesPerformanceManagementDisplay;
import com.SE.Sell.sellInfoManagementDisplay;
import com.SE.Supplier.SupplierInfoManagementDisplay;
import com.SE.Warehousing.WarehousingInfoManagementDisplay;
import com.SE.customer.CustomerInfoManagementDisplay;
import com.SE.product.ProductInfoDisplay;
import com.SE.stock.StockInfoManagementDisplay;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private MainPanel mainPanel;
	private String loginCode;
	private SettingInfo settingInfo=null;

	
	public MainPanel(Main mainFrame,SettingInfo settingInfo) {
	
		this.settingInfo = settingInfo;
		
		
		JButton Btn_ProductMGMT = new JButton("\uC0C1\uD488 \uAD00\uB9AC");
		Btn_ProductMGMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInfoDisplay frame = new ProductInfoDisplay();
				frame.setVisible(true);
			}
		});
		setLayout(null);
		Btn_ProductMGMT.setBounds(26, 101, 98, 37);
		add(Btn_ProductMGMT);
		
		JButton Btn_WarehousingMGMT = new JButton("\uC785\uACE0 \uAD00\uB9AC");
		Btn_WarehousingMGMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarehousingInfoManagementDisplay warehousing = new WarehousingInfoManagementDisplay();
				warehousing.setVisible(true);
				
			}
		});
		Btn_WarehousingMGMT.setBounds(175, 166, 98, 37);
		add(Btn_WarehousingMGMT);
		
		JButton button = new JButton("\uC7AC\uACE0 \uAD00\uB9AC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockInfoManagementDisplay frame = new StockInfoManagementDisplay();
				frame.setVisible(true);
			}
		});
		button.setBounds(343, 223, 93, 37);
		add(button);
		
		JButton button_1 = new JButton("\uD310\uB9E4 \uAD00\uB9AC");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellInfoManagementDisplay sell = new sellInfoManagementDisplay(settingInfo);
				sell.setVisible(true);
			}
		});
		button_1.setBounds(180, 223, 93, 37);
		add(button_1);
		
		JButton button_2 = new JButton("\uACE0\uAC1D \uAD00\uB9AC");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfoManagementDisplay frame = new CustomerInfoManagementDisplay();
				frame.setBounds(300, 100, 750, 440);
				frame.setVisible(true);
			}
		});
		button_2.setBounds(31, 223, 93, 37);
		add(button_2);
		
		JButton button_3 = new JButton("\uB9E4\uCD9C \uAD00\uB9AC");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accessRight=accessControl(loginCode);
	               if(accessRight.equals("AD")) {
				SalesPerformanceManagementDisplay spmd = new SalesPerformanceManagementDisplay();
				spmd.setVisible(true);
	               }
	               else {
	            	   JOptionPane.showMessageDialog(null, "접근할 수 있는 권한이 아닙니다.", "error", JOptionPane.ERROR_MESSAGE);
	               }
			}
		});
		button_3.setBounds(31, 166, 93, 37);
		add(button_3);
		
		JButton button_4 = new JButton("\uAC70\uB798\uCC98 \uAD00\uB9AC");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierInfoManagementDisplay page = new SupplierInfoManagementDisplay();
				
				page.setVisible(true);
			}
		});
		button_4.setBounds(329, 101, 107, 37);
		add(button_4);
		
		JButton button_5 = new JButton("\uAE30\uCD08\uC790\uB8CC \uAD00\uB9AC");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String accessRight=accessControl(loginCode);
	               if(accessRight.equals("AD")) {
	                  BaseDataDisplayManagement page = new BaseDataDisplayManagement(settingInfo);
	                  page.setVisible(true);
	            }
	               else {
	                  JOptionPane.showMessageDialog(null, "접근할 수 있는 권한이 아닙니다.", "error", JOptionPane.ERROR_MESSAGE);
	               }
	         
				
			}
		});
		button_5.setBounds(316, 166, 121, 37);
		add(button_5);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(0, 272, 450, 27);
		add(btnNewButton);
		
		JLabel label = new JLabel("\uC18C\uACF5 \uC57D\uAD6D \uAD00\uB9AC \uC2DC\uC2A4\uD15C V.1.1.2");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setBounds(37, 12, 399, 85);
		add(label);
	}
	
	public void setLoginCode(String loginCode) {
		this.loginCode=loginCode;
	}
	public String accessControl(String userCode)
	{
		String accessRight = userCode.substring(0,2);
		switch(accessRight)
		{
		case"AD": return "AD";
		case "ad": return "AD";
		case "MD": return "MD";
		case "md": return "MD";
		case "WS": return "WS";
		case "ws": return "WS";
		}
		return "";
	}
}

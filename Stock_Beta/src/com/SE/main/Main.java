package com.SE.main;

import com.SE.SalesPerfomance.SalesPerformanceManagementDisplay;
import com.SE.Sell.*;
import com.SE.Supplier.SupplierInfoManagementDisplay;
import com.SE.Warehousing.*;
import com.SE.customer.*;
import com.SE.product.*;
import com.SE.stock.*;
import com.SE.BaseData.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	public LoginInfoDisplayManagement loginPanel = null;
	public MainPanel mainPanel = null;
	private JPanel contentPane;
	 
	public void change()
	{
		getContentPane().removeAll();
		getContentPane().add(mainPanel);
		revalidate();
		repaint();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main mainFrame = new Main();
					SettingInfo settingInfo=mainFrame.loadSetting("settingInfo.txt");
					
				
					mainFrame.setTitle("약국 관리 시스템");
					
					mainFrame.loginPanel = new LoginInfoDisplayManagement(mainFrame,settingInfo);
					mainFrame.mainPanel = new MainPanel(mainFrame,settingInfo);
					mainFrame.loginPanel.setLoginCode(mainFrame.mainPanel);
					mainFrame.add(mainFrame.loginPanel);
					
					mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					mainFrame.setSize(520,400);
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		
	}

	public SettingInfo loadSetting(String fileName)
	{
		
		File file  = new File(fileName);
		SettingInfo settingInfo = new SettingInfo();
		try {
			Scanner sc = new Scanner(file);
			
			int font=Integer.parseInt(sc.nextLine());
			String thema =sc.nextLine(); 
			int discountRate = Integer.parseInt(sc.nextLine()); 
			 settingInfo.setDiscountRate(discountRate);
			 settingInfo.setFont(font);
			 settingInfo.setThema(thema);
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return settingInfo;
	}
}

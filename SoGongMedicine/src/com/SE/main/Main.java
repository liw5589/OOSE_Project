package com.SE.main;

import com.SE.SalesPerfomance.SalesPerformanceManagementDisplay;
import com.SE.Sell.*;
import com.SE.Supplier.SupplierInfoManagementDisplay;
import com.SE.Warehousing.*;
import com.SE.customer.*;
import com.SE.product.*;
import com.SE.stock.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Btn_ProductMGMT = new JButton("\uC0C1\uD488 \uAD00\uB9AC");
		Btn_ProductMGMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInfoDisplay frame = new ProductInfoDisplay();
				frame.setVisible(true);
			}
		});
		Btn_ProductMGMT.setBounds(14, 129, 116, 41);
		contentPane.add(Btn_ProductMGMT);
		
		JButton Btn_WarehousingMGMT = new JButton("\uC785\uACE0 \uAD00\uB9AC");
		Btn_WarehousingMGMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarehousingInfoManagementDisplay warehousing = new WarehousingInfoManagementDisplay();
				warehousing.setVisible(true);
			}
		});
		Btn_WarehousingMGMT.setBounds(166, 129, 116, 41);
		contentPane.add(Btn_WarehousingMGMT);
		
		JButton button = new JButton("\uC7AC\uACE0 \uAD00\uB9AC");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockInfoManagementDisplay frame = new StockInfoManagementDisplay();
				frame.setVisible(true);
			}
		});
		button.setBounds(328, 129, 116, 41);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\uD310\uB9E4 \uAD00\uB9AC");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellInfoManagementDisplay sell = new sellInfoManagementDisplay();
				sell.setVisible(true);
			}
		});
		button_1.setBounds(14, 201, 116, 41);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\uACE0\uAC1D \uAD00\uB9AC");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfoManagementDisplay frame = new CustomerInfoManagementDisplay();
				frame.setBounds(300, 100, 750, 440);
				frame.setVisible(true);
			}
		});
		button_2.setBounds(328, 201, 116, 41);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\uB9E4\uCD9C \uAD00\uB9AC");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesPerformanceManagementDisplay spmd = new SalesPerformanceManagementDisplay();
				spmd.setVisible(true);
			}
		});
		button_3.setBounds(166, 201, 116, 41);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\uAC70\uB798\uCC98 \uAD00\uB9AC");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierInfoManagementDisplay page = new SupplierInfoManagementDisplay();
				page.setVisible(true);
			}
		});
		button_4.setBounds(14, 279, 116, 41);
		contentPane.add(button_4);
		
		JLabel lblNewLabel = new JLabel("\uC0DD\uD65C\uD611\uB3D9\uC870\uD569 \uC18C\uACF5 \uC57D\uAD6D");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(24, 28, 420, 51);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uCC3D \uB2EB\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(166, 279, 278, 41);
		contentPane.add(btnNewButton);
	}
}

package com.SE.stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StockInfoManagementDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField productCodeField;
	private JTextField approStockField;
	private StockInfoManagement simgmt;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField CodeField;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_3;


	/**
	 * Create the frame.
	 */
	public StockInfoManagementDisplay() {
		simgmt = new StockInfoManagement();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 901, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("적정재고설정", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\uC801\uC815\uC7AC\uACE0\uC124\uC815");
		label.setFont(new Font("굴림", Font.BOLD, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(318, 29, 248, 44);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uCF54\uB4DC :");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(32, 147, 147, 70);
		panel.add(lblNewLabel);
		
		productCodeField = new JTextField();
		productCodeField.setFont(new Font("굴림", Font.PLAIN, 20));
		productCodeField.setBounds(193, 159, 628, 47);
		panel.add(productCodeField);
		productCodeField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uC801\uC815\uC7AC\uACE0\uB7C9 :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(32, 269, 147, 70);
		panel.add(label_1);
		
		approStockField = new JTextField();
		approStockField.setFont(new Font("굴림", Font.PLAIN, 20));
		approStockField.setColumns(10);
		approStockField.setBounds(193, 281, 628, 47);
		panel.add(approStockField);
		
		/*적정재고량 설정 버튼 액션 퍼폼드*/
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simgmt.settingApproStock(approStockField.getText(),productCodeField.getText());
				JOptionPane.showMessageDialog(null, "적정재고량이 수정되었습니다.");
			}
		});
		btnOk.setFont(new Font("굴림", Font.BOLD, 20));
		btnOk.setBounds(546, 456, 140, 44);
		panel.add(btnOk);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancle.setFont(new Font("굴림", Font.BOLD, 20));
		btnCancle.setBounds(714, 456, 140, 44);
		panel.add(btnCancle);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("전체 재고 조회", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("\uC804\uCCB4 \uC7AC\uACE0 \uC870\uD68C");
		label_2.setBounds(14, 21, 250, 35);
		label_2.setFont(new Font("굴림", Font.BOLD, 30));
		panel_1.add(label_2);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("굴림", Font.BOLD, 20));
		btnCancel.setBounds(714, 12, 140, 44);
		panel_1.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 85, 840, 415);
		panel_1.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("굴림", Font.PLAIN, 17));
		scrollPane.setViewportView(table_1);
		
		Vector<Vector<String>> checkNull = new Vector<Vector<String>>();
		
		Vector<String> header = new Vector<String>();
		header.add("상품코드");
		header.add("상품이름");
		header.add("상품수량");
		header.add("적정재고량");
		header.add("유통기한");
		
		Vector<Vector<String>> content = new Vector<Vector<String>>();
		Vector<String> stockinfo = new Vector<String>();
		
		content.add(stockinfo);
		DefaultTableModel model = new DefaultTableModel(content,header);
		table_1.setModel(model);
		table_1.setRowHeight(50);
		JButton btnLookup = new JButton("LookUp");
		/*전체재고조회 액션 퍼폼드*/
		btnLookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<String>> stockInfos;
				stockInfos = simgmt.lookupTotalStock();
				while(model.getRowCount() != 0) {
					model.removeRow(0);
				}
				
				for(int i = 0; i < stockInfos.size(); i++) {
					model.addRow(stockInfos.get(i));
				}
			}
		});
		btnLookup.setFont(new Font("굴림", Font.BOLD, 20));
		btnLookup.setBounds(560, 12, 140, 44);
		panel_1.add(btnLookup);
		
		
		Vector<Vector<String>> content2 = new Vector<Vector<String>>();
		DefaultTableModel model1 = new DefaultTableModel(content2,header);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("상품별 재고 조회", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("\uC0C1\uD488\uBCC4 \uC7AC\uACE0 \uC870\uD68C");
		label_3.setBounds(14, 21, 275, 35);
		label_3.setFont(new Font("굴림", Font.BOLD, 30));
		panel_2.add(label_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 91, 840, 409);
		panel_2.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setFont(new Font("굴림", Font.PLAIN, 17));
		table_2.setRowHeight(50);
		scrollPane_1.setViewportView(table_2);
		table_2.setModel(model1);
		
		JButton button = new JButton("LookUp");
		button.addActionListener(new ActionListener() {
			/*코드별 재고조회 액션 퍼폼드*/
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<String>> stockInfos;
				stockInfos = simgmt.lookupStockAsProductCode(CodeField.getText());
				if(stockInfos.equals(checkNull)) {
					JOptionPane.showMessageDialog(null,"등록되어 있지 않은 상품코드입니다. 다시 한번 확인해 주세요");
				}
				while(model1.getRowCount() != 0) {
					model1.removeRow(0);
				}
				
				for(int i = 0; i < stockInfos.size(); i++) {
					model1.addRow(stockInfos.get(i));
				}
			}
		});
		button.setFont(new Font("굴림", Font.BOLD, 20));
		button.setBounds(560, 20, 140, 44);
		panel_2.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("굴림", Font.BOLD, 20));
		button_1.setBounds(714, 20, 140, 44);
		panel_2.add(button_1);
		
		CodeField = new JTextField();
		CodeField.setFont(new Font("굴림", Font.PLAIN, 20));
		CodeField.setBounds(416, 23, 130, 43);
		panel_2.add(CodeField);
		CodeField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uD488\uCF54\uB4DC : ");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(294, 21, 108, 43);
		panel_2.add(lblNewLabel_1);
		
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("유통기한별 재고조회", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label_6 = new JLabel("~");
		label_6.setFont(new Font("굴림", Font.BOLD, 22));
		label_6.setBounds(295, 80, 62, 18);
		panel_3.add(label_6);
		
		JLabel label_4 = new JLabel("\uC720\uD1B5\uAE30\uD55C\uBCC4 \uC7AC\uACE0 \uC870\uD68C");
		label_4.setBounds(24, 12, 312, 35);
		label_4.setFont(new Font("굴림", Font.BOLD, 30));
		panel_3.add(label_4);
		
		
		
		JButton button_3 = new JButton("Cancel");
		button_3.setFont(new Font("굴림", Font.BOLD, 20));
		button_3.setBounds(714, 12, 140, 44);
		panel_3.add(button_3);
		
		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(157, 69, 130, 43);
		panel_3.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("굴림", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(329, 69, 130, 43);
		panel_3.add(textField_1);
		
		JLabel label_5 = new JLabel("\uC720\uD1B5\uAE30\uD55C : ");
		label_5.setFont(new Font("굴림", Font.BOLD, 22));
		label_5.setBounds(24, 69, 119, 43);
		panel_3.add(label_5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 126, 830, 374);
		panel_3.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.setFont(new Font("굴림", Font.PLAIN, 17));
		scrollPane_2.setViewportView(table_3);
		
		Vector<Vector<String>> content3 = new Vector<Vector<String>>();
		DefaultTableModel model2 = new DefaultTableModel(content3,header);
		table_3.setModel(model2);
		table_3.setRowHeight(50);

		JButton button_2 = new JButton("LookUp");
		button_2.addActionListener(new ActionListener() {
			/*유통기한 별 재고조회 액션 퍼폼드*/
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<String>> stockInfos;
				
				stockInfos = simgmt.lookupStockAsExpirationDate(textField.getText(),textField_1.getText());
				String regExp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";
				if(!(textField.getText().matches(regExp))) {
					JOptionPane.showMessageDialog(null,"유통기한 시작일자를 잘못입력하셧습니다. 다시 한번 확인해 주세요 날짜 형식 : yyyy-mm-dd");
				}
				else if(!(textField_1.getText().matches(regExp))) {
					JOptionPane.showMessageDialog(null,"유통기한 마지막 일자를 잘못입력하셧습니다. 다시 한번 확인해 주세요 날짜 형식 : yyyy-mm-dd");
				}
				
				else if(stockInfos.equals(checkNull)) {
					JOptionPane.showMessageDialog(null,"날짜에 해당하는 상품이 없습니다.");
				}
				
				else {
				while(model2.getRowCount() != 0) {
					model2.removeRow(0);
				}
				
				for(int i = 0; i < stockInfos.size(); i++) {
					model2.addRow(stockInfos.get(i));
				}
				}
			
			}
		});
		button_2.setFont(new Font("굴림", Font.BOLD, 20));
		button_2.setBounds(564, 11, 140, 44);
		panel_3.add(button_2);
	}
}

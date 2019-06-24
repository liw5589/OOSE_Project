package com.SE.customer;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : CustomerInfoManagementDisplay.java
//  @ Date : 2019-05-31
//  @ Author : 
//
//

public class CustomerInfoManagementDisplay extends JFrame {

	private CustomerInfoManagement cim;

	private JTable table;
	private JTextField lookupTextField;

	private JTextField insertCodeTextField;
	private JTextField insertNameTextField;
	private JTextField insertPhoneNumTextField;
	private JTextField insertBirthdayTextField;
	private JTextField insertAddressTextField;

	private JTextField modifyCodeTextField;
	private JTextField modifyNameTextField;
	private JTextField modifyPhoneNumTextField;
	private JTextField modifyBirthdayTextField;
	private JTextField modifyAddressTextField;

	private JComboBox comboBox;

	Vector<Vector<String>> data;
	DefaultTableModel model;



	public void printModifyDisplay() {
		JFrame frame = new JFrame();
		JPanel contentPane;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(500, 100, 500, 440);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_3 = new JLabel("�ּ� ����");
		label_3.setFont(new Font("����", Font.PLAIN, 18));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(30, 274, 156, 36);
		contentPane.add(label_3);

		JLabel label = new JLabel("�������");
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(30, 224, 156, 36);
		contentPane.add(label);

		JLabel label_1 = new JLabel("��ȭ ��ȣ");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(30, 171, 156, 36);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uC774\uB984");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(35, 120, 151, 36);
		contentPane.add(label_2);

		JLabel label_4 = new JLabel("�й�(������ �ڵ�)");
		label_4.setFont(new Font("����", Font.PLAIN, 18));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(39, 66, 151, 36);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("���� ���� ����");
		label_5.setFont(new Font("����", Font.PLAIN, 24));
		label_5.setBounds(17, 15, 169, 36);
		contentPane.add(label_5);

		modifyCodeTextField = new JTextField();
		modifyCodeTextField.setBounds(223, 71, 207, 27);
		int s = table.getSelectedRow();
		String cC = (String) table.getValueAt(s, 0);
		modifyCodeTextField.setText(cC);
		modifyCodeTextField.setEnabled(false);
		contentPane.add(modifyCodeTextField);
		modifyCodeTextField.setColumns(10);

		modifyNameTextField = new JTextField();
		modifyNameTextField.setColumns(10);
		modifyNameTextField.setBounds(223, 125, 207, 27);
		contentPane.add(modifyNameTextField);

		modifyPhoneNumTextField = new JTextField();
		modifyPhoneNumTextField.setColumns(10);
		modifyPhoneNumTextField.setBounds(223, 176, 207, 27);
		contentPane.add(modifyPhoneNumTextField);

		modifyBirthdayTextField = new JTextField();
		modifyBirthdayTextField.setColumns(10);
		modifyBirthdayTextField.setBounds(223, 229, 207, 27);
		contentPane.add(modifyBirthdayTextField);

		modifyAddressTextField = new JTextField();
		modifyAddressTextField.setColumns(10);
		modifyAddressTextField.setBounds(223, 280, 207, 27);
		contentPane.add(modifyAddressTextField);

		JButton modifyButton = new JButton("����");
		modifyButton.setBounds(266, 340, 72, 29);
		contentPane.add(modifyButton);

		JButton modifyButton1 = new JButton("���");
		modifyButton1.setBounds(355, 340, 75, 29);
		contentPane.add(modifyButton1);

		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfo c = new CustomerInfo();
				
				c.setCustomerCode(modifyCodeTextField.getText());
				c.setCustomerName(modifyNameTextField.getText());
				c.setPhoneNum(modifyPhoneNumTextField.getText());
				c.setAddress(modifyAddressTextField.getText());
				c.setBirthday(modifyBirthdayTextField.getText());

				boolean result = cim.modifyCustomerInfo(c);
				if (result) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
					if (comboBox.getSelectedItem().toString() == "����")
						printLookupDisplay();
					else if (comboBox.getSelectedItem().toString() == "���ɰ���")
						printInterestedLookupDisplay();
				} else {
					JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�. �ٽ� �õ����ּ���");
				}

			}
		});

		modifyButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}

	public void printInsertDisplay() {
		JFrame frame = new JFrame();
		JPanel contentPane;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(500, 100, 500, 440);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_3 = new JLabel("�ּ� ����");
		label_3.setFont(new Font("����", Font.PLAIN, 18));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(30, 274, 156, 36);
		contentPane.add(label_3);

		JLabel label = new JLabel("�������");
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(30, 224, 156, 36);
		contentPane.add(label);

		JLabel label_1 = new JLabel("��ȭ ��ȣ");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(30, 171, 156, 36);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uC774\uB984");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(35, 120, 151, 36);
		contentPane.add(label_2);

		JLabel label_4 = new JLabel("�й�(������ �ڵ�)");
		label_4.setFont(new Font("����", Font.PLAIN, 18));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(39, 66, 151, 36);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("���� ���� ���");
		label_5.setFont(new Font("����", Font.PLAIN, 24));
		label_5.setBounds(17, 15, 169, 36);
		contentPane.add(label_5);

		insertCodeTextField = new JTextField();
		insertCodeTextField.setBounds(223, 71, 207, 27);
		contentPane.add(insertCodeTextField);
		insertCodeTextField.setColumns(10);

		insertNameTextField = new JTextField();
		insertNameTextField.setColumns(10);
		insertNameTextField.setBounds(223, 125, 207, 27);
		contentPane.add(insertNameTextField);

		insertPhoneNumTextField = new JTextField();
		insertPhoneNumTextField.setColumns(10);
		insertPhoneNumTextField.setBounds(223, 176, 207, 27);
		contentPane.add(insertPhoneNumTextField);

		insertBirthdayTextField = new JTextField("yyyy-mm-dd");
		insertBirthdayTextField.setColumns(10);
		insertBirthdayTextField.setBounds(223, 229, 207, 27);
		contentPane.add(insertBirthdayTextField);

		insertAddressTextField = new JTextField();
		insertAddressTextField.setColumns(10);
		insertAddressTextField.setBounds(223, 280, 207, 27);
		contentPane.add(insertAddressTextField);

		JButton insertButton = new JButton("���");
		insertButton.setBounds(266, 340, 72, 29);
		contentPane.add(insertButton);

		JButton insertButton1 = new JButton("���");
		insertButton1.setBounds(355, 340, 75, 29);
		contentPane.add(insertButton1);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfo c = new CustomerInfo();
				c.setCustomerCode(insertCodeTextField.getText());
				c.setCustomerName(insertNameTextField.getText());
				c.setPhoneNum(insertPhoneNumTextField.getText());
				c.setAddress(insertAddressTextField.getText());
				c.setBirthday(insertBirthdayTextField.getText());

				boolean result = cim.insertCustomerInfo(c);

				if (result) {
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");
					if (comboBox.getSelectedItem().toString() == "����")
						printLookupDisplay();
					else if (comboBox.getSelectedItem().toString() == "���ɰ���")
						printInterestedLookupDisplay();

				} else {
					JOptionPane.showMessageDialog(null, "�ߺ��� �ڵ��Դϴ�. �ٽ� �õ����ּ���");
				}

			}
		});

		insertButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}

	void printLookupDisplay() {

		List<CustomerInfo> customerInfo;
		try {
			if (lookupTextField.getText().length() != 0)
				customerInfo = cim.lookupCustomerInfo(lookupTextField.getText());
			else
				customerInfo = cim.lookupCustomerInfo("");

			data.clear();

			for (int i = 0; i < customerInfo.size(); i++) {
				Vector<String> row = new Vector<>();

				row.add(customerInfo.get(i).getCustomerCode());
				row.add(customerInfo.get(i).getCustomerName());
				row.add(customerInfo.get(i).getPhoneNum());
				row.add(customerInfo.get(i).getAddress());
				row.add(customerInfo.get(i).getBirthDay());

				data.add(row);
			}

			model.fireTableDataChanged();
		} catch (Exception e11) {
			System.out.println("�������� : " + e11.getMessage());
		}
	}

	void printInterestedLookupDisplay() {

		List<CustomerInfo> customerInfo;
		try {
			if (lookupTextField.getText().length() != 0)
				customerInfo = cim.interestedLookupCustomerInfo(lookupTextField.getText());
			else
				customerInfo = cim.interestedLookupCustomerInfo("");

			data.clear();

			for (int i = 0; i < customerInfo.size(); i++) {
				Vector<String> row = new Vector<>();

				row.add(customerInfo.get(i).getCustomerCode());
				row.add(customerInfo.get(i).getCustomerName());
				row.add(customerInfo.get(i).getPhoneNum());
				row.add(customerInfo.get(i).getAddress());
				row.add(customerInfo.get(i).getBirthDay());

				data.add(row);
			}

			model.fireTableDataChanged();
		} catch (Exception e11) {
			System.out.println("�������� : " + e11.getMessage());
		}
	}

	public CustomerInfoManagementDisplay() {

		cim = new CustomerInfoManagement();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Vector<String> colNames = new Vector<>();
		colNames.add("�й�(������ �ڵ�)");
		colNames.add("�̸�");
		colNames.add("��ȭ��ȣ");
		colNames.add("�������");
		colNames.add("�ּ� ����");

		data = new Vector<>();

		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(59, 87, 635, 230);
		getContentPane().add(scrollPane_1);

		model = new DefaultTableModel(data, colNames);

		table = new JTable(model);
		scrollPane_1.setViewportView(table);

		JButton btnNewButton = new JButton("���");
		btnNewButton.setBounds(367, 342, 90, 47);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("����");
		button.setBounds(487, 342, 90, 47);
		getContentPane().add(button);

		JButton button_1 = new JButton("����");
		button_1.setBounds(604, 342, 90, 47);
		getContentPane().add(button_1);

		lookupTextField = new JTextField();
		lookupTextField.setBounds(459, 39, 166, 27);
		lookupTextField.setToolTipText("�й�(������ �ڵ�) �Է�");
		getContentPane().add(lookupTextField);
		lookupTextField.setColumns(10);

		JButton btnNewButton_1 = new JButton("��ȸ");
		btnNewButton_1.setBounds(624, 39, 69, 27);
		getContentPane().add(btnNewButton_1);

		String[] combo = { "����", "���ɰ���" };
		comboBox = new JComboBox(combo);
		comboBox.setBounds(379, 39, 80, 27);
		getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("�й�(������ �ڵ�)");
		lblNewLabel.setBounds(490, 15, 146, 21);
		getContentPane().add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					printInsertDisplay();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}

				printModifyDisplay();
			}
		});

		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "�����ϰ����ϴ� ���� �������ּ���");
					return;
				}
				int delete = JOptionPane.showConfirmDialog(null, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
				if (delete != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "������ ����Ͽ����ϴ�.");
					return;
				}
				int selectedRow = table.getSelectedRow();
				String customerCode = (String) table.getValueAt(selectedRow, 0);
				boolean result = cim.deleteCustomerInfo(customerCode);

				if (result) {
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
					if (comboBox.getSelectedItem().toString() == "����")
						printLookupDisplay();
					else if (comboBox.getSelectedItem().toString() == "���ɰ���")
						printInterestedLookupDisplay();

				} else {
					JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�. �ٽ� �õ����ּ���");
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString() == "����")
					printLookupDisplay();
				else if (comboBox.getSelectedItem().toString() == "���ɰ���")
					printInterestedLookupDisplay();
			}
		});

	}

}
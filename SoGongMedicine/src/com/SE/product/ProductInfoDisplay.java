package com.SE.product;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


public class ProductInfoDisplay extends JFrame {

	private ProductInfoManagement pim = new ProductInfoManagement();
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInfoDisplay frame = new ProductInfoDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//수정 대화상자
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JTextField textField5;
	JTextField textField6;

	
	public ProductInfoDisplay() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		//첫번째 탭
		tabbedPane.addTab("상품조회", null, panel, null);
		
		panel.setLayout(null);

		String[] aa = { "상품코드", "의약품분류", "제품명", "제조사", "주의수준", "소매가격" };
		// String[][] b = { { "a1", "a2", "a3" }, { "b1", "b2", "b3" }, { "c1", "c2",
		// "c3" } };
		// 1. 모델과 데이터를 연결
		DefaultTableModel model2 = new DefaultTableModel(null, aa);
		// 2. Model을 매개변수로 설정, new JTable(b,a)도 가능하지만
		// 삽입 삭제를 하기 위해 해당 방법을 사용합니다
		JTable table2 = new JTable(model2);
		table2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setAutoResizeMode(HEIGHT);
		table2.setLocation(47, 54);
		// 3. 결과적으로는 JScrollPane를 추가합니다.
		JScrollPane sc2 = new JScrollPane(table2);
		sc2.setSize(531, 247);
		sc2.setLocation(30, 30);
		panel.add(sc2);
		
		
		
		JButton button2 = new JButton("조회");
		button2.setBounds(350, 322, 70, 23);
		panel.add(button2);
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//읽어서 테이블에 내용 추가
				pim.lookupProduct();	
				DefaultTableModel m2 = (DefaultTableModel) table2.getModel();
				Vector<ProductInfo> v2 = (Vector<ProductInfo>) pim.getV();
				
				m2.setRowCount(0);
				for (int i = 0; i < v2.size(); i++) {
					String[] rowData = { v2.elementAt(i).getProductCode(), v2.elementAt(i).getProductClassification(),
							v2.elementAt(i).getProductName(), v2.elementAt(i).getProductManufacturer(),
							Integer.toString(v2.elementAt(i).getAttentionLevel()),
							Integer.toString(v2.elementAt(i).getProductRetailPrice()) };
					m2.addRow(rowData);
				}
				// 추가를 마치고 데이터 갱신을 알립니다.
				table2.updateUI();
				
			}
		});
		
		JButton button3 = new JButton("삭제");
		button3.setBounds(440, 322, 70, 23);
		panel.add(button3);
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "삭제하고자하는 행을 선택해주세요");
					return;
				}
				int delete = JOptionPane.showConfirmDialog(null,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
				if (delete != JOptionPane.OK_OPTION){
					JOptionPane.showMessageDialog(null, "삭제를 취소하였습니다.");
					return;
	            }
				int selectedRow = table2.getSelectedRow();
				String productCode = (String)table2.getValueAt(selectedRow, 0);
				boolean result = pim.deleteProduct(productCode);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					button2.doClick();					
				}
				else {
					JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다. 다시 시도해주세요");
				}
			}
		});
		
		//수정
		JButton button4 = new JButton("수정");
		button4.setBounds(530, 322, 70, 23);
		panel.add(button4);
		
	
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table2.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "수정하고자하는 행을 선택해주세요");
					return;
				}
				int selectedRow = table2.getSelectedRow();
				String productCode = (String)table2.getValueAt(selectedRow, 0);
				ProductInfo ProductInfo = pim.lookupProductInfo(productCode);
						
				printProductModifyDisplay();
				textField1.setText(ProductInfo.getProductCode());
				textField2.setText(ProductInfo.getProductClassification());
				textField3.setText(ProductInfo.getProductName());
				textField4.setText(ProductInfo.getProductManufacturer());
				textField5.setText(Integer.toString(ProductInfo.getAttentionLevel()));
				textField6.setText(Integer.toString(ProductInfo.getProductRetailPrice()));

			}
		});
		
		
		
		//두번째 탭
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("상품등록", null, panel_1, null);
		panel_1.setLayout(null);

		String[] a = { "상품코드", "의약품분류", "제품명", "제조사", "주의수준", "소매가격" };
		// String[][] b = { { "a1", "a2", "a3" }, { "b1", "b2", "b3" }, { "c1", "c2",
		// "c3" } };
		// 1. 모델과 데이터를 연결
		DefaultTableModel model = new DefaultTableModel(null, a);
		// 2. Model을 매개변수로 설정, new JTable(b,a)도 가능하지만
		// 삽입 삭제를 하기 위해 해당 방법을 사용합니다
		JTable table = new JTable(model);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(HEIGHT);
		table.setLocation(47, 54);
		// 3. 결과적으로는 JScrollPane를 추가합니다.
		JScrollPane sc = new JScrollPane(table);
		sc.setSize(531, 247);
		sc.setLocation(30, 30);
		panel_1.add(sc);

		// 추가를 마치고 데이터 갱신을 알립니다.
		table.updateUI();
		
		JButton button = new JButton("파일찾기");
		button.setBounds(363, 322, 97, 23);
		panel_1.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				// For Directory
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().toString());

					String filePath = fileChooser.getSelectedFile().getAbsolutePath(); // 파일경로
					// 테이블에 데이터 추가하기
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					Vector<ProductInfo> v = (Vector<ProductInfo>) pim.readProductList(filePath);
					
					for (int i = 0; i < v.size(); i++) {
						String[] rowData = { v.elementAt(i).getProductCode(), v.elementAt(i).getProductClassification(),
								v.elementAt(i).getProductName(), v.elementAt(i).getProductManufacturer(),
								Integer.toString(v.elementAt(i).getAttentionLevel()),
								Integer.toString(v.elementAt(i).getProductRetailPrice()) };

						m.addRow(rowData);
					}
					
					// 추가를 마치고 데이터 갱신을 알립니다.
					table.updateUI();
				}
			}
		});

		JButton button_1 = new JButton("등록확정");
		button_1.setBounds(475, 322, 97, 23);
		panel_1.add(button_1);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbm = new DBManager();
				Vector<ProductInfo> v = pim.getV();
				for (int i = 0; i < v.size(); i++) {
	
				dbm.insertDBProduct(
						v.elementAt(i).getProductCode(), v.elementAt(i).getProductClassification(), v.elementAt(i).getProductName(),
						v.elementAt(i).getProductManufacturer(),
						Integer.toString(v.elementAt(i).getAttentionLevel()), v.elementAt(i).getProductRetailPrice()
						);
				}
				JOptionPane.showMessageDialog(null, "등록되었습니다.");


	
				pim.resetVector();
				
			}
		});
		
	}
	private void printProductModifyDisplay() {
		JFrame modifyFrame = new JFrame();
				
		modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		modifyFrame.setBounds(300, 100, 405, 440);
		modifyFrame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		modifyFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("상품정보수정");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(138, 10, 112, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("상품코드");
		lblNewLabel1.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel1.setBounds(28, 48, 77, 25);
		contentPane.add(lblNewLabel1);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(109, 48, 245, 25);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(109, 83, 245, 25);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(109, 118, 245, 25);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(109, 153, 245, 25);
		contentPane.add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(109, 188, 245, 25);
		contentPane.add(textField5);
		
		textField6 = new JTextField();
		textField6.setColumns(10);
		textField6.setBounds(109, 223, 245, 25);
		contentPane.add(textField6);
		
		JLabel label1 = new JLabel("의약품분류");
		label1.setFont(new Font("굴림", Font.BOLD, 13));
		label1.setBounds(28, 83, 77, 25);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("상품이름");
		label2.setFont(new Font("굴림", Font.BOLD, 13));
		label2.setBounds(28, 118, 77, 25);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("제조사");
		label3.setFont(new Font("굴림", Font.BOLD, 13));
		label3.setBounds(28, 153, 77, 25);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("주의수준");
		label4.setFont(new Font("굴림", Font.BOLD, 13));
		label4.setBounds(28, 188, 77, 25);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("소매가격");
		label5.setFont(new Font("굴림", Font.BOLD, 13));
		label5.setBounds(28, 223, 77, 25);
		contentPane.add(label5);
		
		
		JButton btnNewButton = new JButton("취소");
		btnNewButton.setBounds(294, 366, 60, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				modifyFrame.setVisible(false);
				modifyFrame.dispose();
			}
		});
		
		JButton button = new JButton("수정");
		button.setBounds(227, 366, 60, 25);
		contentPane.add(button);
		// 수정버튼클릭
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductInfo ProductInfo = new ProductInfo();
				ProductInfo.setProductCode(textField1.getText());
				ProductInfo.setProductClassification(textField2.getText());
				ProductInfo.setProductName(textField3.getText());
				ProductInfo.setProductManufacturer(textField4.getText());
				ProductInfo.setAttentionLevel(Integer.parseInt(textField5.getText()));
				ProductInfo.setProductRetailPrice(Integer.parseInt(textField5.getText()));

				boolean result = pim.modifyProduct(ProductInfo);
				
				if(result) {
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "수정에 실패하였습니다. 입력정보를 확인해주세요");
				}
			}
		});
		
	}
}

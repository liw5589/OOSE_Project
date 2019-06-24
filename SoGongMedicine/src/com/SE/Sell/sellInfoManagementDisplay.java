//  @ Project : 소공 약국
//  @ File Name : sellInfoManagementDisplay.java
//  @ Date : 2019-06-07
//  @ Author : 이수향

package com.SE.Sell;

import java.awt.BorderLayout;


import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;

public class sellInfoManagementDisplay extends JFrame {

	public static final double DISCOUNT = 0.05;

	private JTable sellInfoTable;
	private JTable lookupSellRecordTable;
	private JTextField dateTextField;
	private JTextField sellCodeTextField;
	private JTextField Selling_productCode_textField;
	private JTextField Selling_productQty_textField;
	private JLabel Selling_totalPrice_labe;
	private JLabel Selling_totalPrice_view;
	JButton sellingComplete_btn;

	// 상품판매에 사용될 변수
	private int totalPrice;
	static ArrayList<proudctSellRecordInfo> productInfo = new ArrayList<proudctSellRecordInfo>();
	static sellRecordInfo sellInfo;
	static int totalLevel;
	
	// 판매 완료를 위한 부분
	private JPanel successSellPanel;
	private JTextField customerInfoTextField;
	private JButton successSellBtn;
	private JCheckBox noCustomerInfoCheck;
	private JButton customerInfoLBtn;
	private JLabel TotalResultLabelView;
	private JProgressBar interestedCusPrograss;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// 영수증 출력을 위한 부분
	private JPanel receiptOutputPanel;
	static String receiptSellCode;
	JLabel receiptDateTextField;
	JTextArea recieptProductNameLabel;
	JTextArea recieptProductQtyLabel;
	JTextArea recieptProductPriceLabel;
	JLabel receiptSellPriceLabel;

	/*------------------------ 탭 메뉴 시작 ------------------------*/

	public sellInfoManagementDisplay() {
		setTitle("\uC0C1\uD488\uD310\uB9E4\uAD00\uB9AC");

		setBounds(300, 50, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPanel, BorderLayout.CENTER);

		/*---------------상품판매에 관한 부분 패널 시작---------------*/

		JPanel productSell = new JPanel();
		tabbedPanel.addTab("상품판매", null, productSell, null);
		productSell.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 127, 700, 292);
		productSell.add(scrollPane_1);

		// 테이블 생성 부분
		sellInfoTable = new JTable();
		sellInfoTable.setRowHeight(25);
		scrollPane_1.setViewportView(sellInfoTable);

		// 컬럼 제목 설정
		Vector<String> sellingHeader = new Vector<String>();
		sellingHeader.add("상품 코드");
		sellingHeader.add("상품명");
		sellingHeader.add("수량");
		sellingHeader.add("가격");

		Vector<Vector<String>> content1 = new Vector<Vector<String>>();

		DefaultTableModel model = new DefaultTableModel(content1, sellingHeader);
		sellInfoTable.setModel(model);

		JLabel label2 = new JLabel("\uD310\uB9E4\uAD00\uB9AC");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("맑은고딕", Font.BOLD, 20));
		label2.setBounds(37, 23, 99, 46);
		productSell.add(label2);

		JLabel label_1 = new JLabel("\uC0C1\uD488 \uD310\uB9E4");
		label_1.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		label_1.setBounds(135, 34, 62, 28);
		productSell.add(label_1);

		Selling_productCode_textField = new JTextField();
		Selling_productCode_textField.setBounds(127, 85, 191, 21);
		productSell.add(Selling_productCode_textField);
		Selling_productCode_textField.setColumns(10);

		JLabel Selling_productCode_label = new JLabel("\uC0C1\uD488 \uCF54\uB4DC");
		Selling_productCode_label.setBounds(50, 88, 65, 15);
		productSell.add(Selling_productCode_label);

		JLabel Selling_productQty_label = new JLabel("\uC218\uB7C9");
		Selling_productQty_label.setBounds(356, 88, 65, 15);
		productSell.add(Selling_productQty_label);

		Selling_productQty_textField = new JTextField();
		Selling_productQty_textField.setText("1");
		Selling_productQty_textField.setColumns(10);
		Selling_productQty_textField.setBounds(396, 85, 41, 21);
		productSell.add(Selling_productQty_textField);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(50, 429, 700, 43);
		productSell.add(panel);
		panel.setLayout(null);

		Selling_totalPrice_labe = new JLabel("\uCD1D \uD569\uACC4 \uAE08\uC561");
		Selling_totalPrice_labe.setBounds(453, 15, 86, 18);
		Selling_totalPrice_labe.setFont(new Font("맑은고딕", Font.BOLD, 15));
		panel.add(Selling_totalPrice_labe);

		// 총 가격 노출 될 부분
		Selling_totalPrice_view = new JLabel();
		Selling_totalPrice_view.setFont(new Font("맑은고딕", Font.PLAIN, 14));
		Selling_totalPrice_view.setBounds(551, 17, 126, 15);
		panel.add(Selling_totalPrice_view);

		sellInfo = new sellRecordInfo();

		// 상품 추가 버튼을 누르면 테이블에 구매한 상품 정보가 표시된다
		JButton Selling_Plus_btn = new JButton("\uCD94\uAC00");
		Selling_Plus_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 상품코드, 상품명, 수량, 가격
				String productCode = Selling_productCode_textField.getText();
				int productQty = Integer.parseInt((String) Selling_productQty_textField.getText());

				// 컨트롤
				sellInfoManagementControl control = new sellInfoManagementControl();
				proudctSellRecordInfo temp = control.getProductInfo(productCode);

				if (temp == null) {
					JOptionPane.showMessageDialog(null, "상품이 존재하지 않습니다! 상품 코드 확인!");
					return;
				} else {
					int price = temp.getProductPrice();
					String productName = temp.getProductName();

					// 제품 정보 셋팅
					temp.setQty(productQty);
					temp.setProductCode(productCode);
					temp.setTotalprice(price * productQty);
					temp.setProductName(productName);

					// 가격 셋팅
					totalPrice += price * productQty;
					sellInfo.setTotalPrice(totalPrice);

					// 테이블에 컬럼 추가
					Vector<String> userRow = new Vector<String>();

					userRow.addElement(temp.getProductCode());
					userRow.addElement(temp.getProductName());
					userRow.addElement(Integer.toString(temp.getQty()));
					userRow.addElement(Integer.toString(temp.getProductPrice()));

					model.addRow(userRow);

					// 가격 보여주기
					Selling_totalPrice_view.setText(Integer.toString(totalPrice));

					// 판매 제품 추가
					productInfo.add(temp);
				}

			}
		});

		Selling_Plus_btn.setBounds(478, 84, 62, 23);
		productSell.add(Selling_Plus_btn);

		// 판매완료를 위한 최종적인 부분
		sellingComplete_btn = new JButton("\uD310\uB9E4\uC644\uB8CC");
		sellingComplete_btn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {

				int count = sellInfoTable.getModel().getRowCount();
						
				if(count == 0)
				{ 
					JOptionPane.showMessageDialog(null, "판매 할 제품 정보를 입력 해 주세요!");
					return;
				} else {
					sellSuccessDisplay();
				}
				
				int totalPrice = sellInfo.getTotalPrice();

				TotalResultLabelView.setText(Integer.toString(totalPrice));
			}
		});

		sellingComplete_btn.setFont(new Font("맑은고딕", Font.BOLD, 15));
		sellingComplete_btn.setBounds(639, 482, 111, 34);
		productSell.add(sellingComplete_btn);

		/*---------------상품판매에 관한 부분 패널 끝---------------*/

		/*---------------판매이력조회에 관한 부분 패널---------------*/

		JPanel lookupSellRecord = new JPanel();
		tabbedPanel.addTab("판매이력조회", null, lookupSellRecord, null);
		lookupSellRecord.setLayout(null);

		JScrollPane lookUpTable = new JScrollPane();
		lookUpTable.setBounds(49, 170, 700, 305);
		lookupSellRecord.add(lookUpTable);

		lookupSellRecordTable = new JTable();
		lookupSellRecordTable.setRowHeight(25);
		lookUpTable.setViewportView(lookupSellRecordTable);

		Vector<String> lookUpHeader = new Vector<String>();
		Vector<Vector<String>> lookUpContent = new Vector<Vector<String>>();

		lookUpHeader.add("구매날짜");
		lookUpHeader.add("판매코드");
		lookUpHeader.add("결제방법");
		lookUpHeader.add("구매제품");
		lookUpHeader.add("총 판매가격");
		lookUpHeader.add("구매자");

		DefaultTableModel lookupModel = new DefaultTableModel(lookUpContent, lookUpHeader);
		lookupSellRecordTable.setModel(lookupModel);

		JLabel label3 = new JLabel("\uD310\uB9E4\uC774\uB825\uC870\uD68C");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("맑은고딕", Font.BOLD, 20));
		label3.setBounds(130, 10, 523, 50);
		lookupSellRecord.add(label3);

		// 조회버튼 클릭시
		JButton lookupBtn = new JButton("\uC870\uD68C\uD558\uAE30");
		lookupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// 컨트롤 클래스 선언
				sellInfoManagementControl control = new sellInfoManagementControl();

				// 조회를 위한 변수 구성
				ArrayList<sellRecordInfo> sellInfoList = new ArrayList<sellRecordInfo>();
				sellRecordInfo lookupSellInfo = new sellRecordInfo();
				String sellDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";

				// 조회
				String date = dateTextField.getText();
			

				// 기존데이터 제거
				while (lookupModel.getRowCount() != 0) {
					lookupModel.removeRow(0);
				}

				// 테이블에 컬럼 추가
				Vector<String> userRow = new Vector<String>();

				// 조회를 위한 분기문
				if (sellCodeTextField.getText().equals("") && dateTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "검색 조건을 입력해주세요");
					return;
				}

				// 제품명 조회
				else if (!sellCodeTextField.getText().equals("") && dateTextField.getText().equals("")) {

					lookupSellInfo = control.getLookupSellRecorde(sellCodeTextField.getText());

					if (lookupSellInfo == null) {
						JOptionPane.showMessageDialog(null, "조회할 수 없는 정보입니다!(바코드만 입력가능)");
						return;

					} else {
						// 판매코드에 대한 조회기록
						userRow.addElement(lookupSellInfo.getSellDate());
						userRow.addElement(lookupSellInfo.getSellCode());

						// 결제 방법 0:현금, 1:카드
						if (lookupSellInfo.getPaymentMethod() == 0) {
							userRow.addElement("현금");
						} else {
							userRow.addElement("카드");
						}

						userRow.addElement(lookupSellInfo.getSellProductList().toString());
						userRow.addElement(Integer.toString(lookupSellInfo.getTotalPrice()));
						userRow.addElement(lookupSellInfo.getCustomerName());

						lookupModel.addRow(userRow);
					}

				}

				// 날짜 형식 확인
				else if (!date.matches(sellDateFormat)) {
					JOptionPane.showMessageDialog(null, "날짜형식을 확인해주세요!(yyyy-mm-dd)");
					return;
				}

				// 날짜별 조회
				else if (sellCodeTextField.getText().equals("") && !dateTextField.getText().equals("")) {

					sellInfoList = control.getSellDateLookupRecorde(dateTextField.getText());

					if (sellInfoList == null) {
						JOptionPane.showMessageDialog(null, "현재 거래된 정보가 존재하지 않습니다!");
						return;
					}

					for (int i = 0; i < sellInfoList.size(); i++) {

						String sellDateInfo = sellInfoList.get(i).getSellDate();
						String sellCodeInfo = sellInfoList.get(i).getSellCode();
						String paymentMethodInfo;
						String sellInfoListInfo = sellInfoList.get(i).getSellProductList().toString();
						String totalPriceInfo = Integer.toString(sellInfoList.get(i).getTotalPrice());
						String customerName = sellInfoList.get(i).getCustomerName();

						// 결제 방법 0:현금, 1:카드
						if (sellInfoList.get(i).getPaymentMethod() == 0) {
							paymentMethodInfo = "현금";
						} else {
							paymentMethodInfo = "카드";
						}

						Object[] data = { sellDateInfo, sellCodeInfo, paymentMethodInfo, sellInfoListInfo,
								totalPriceInfo, customerName };

						lookupModel.addRow(data);
					}

				}
			}
		});

		lookupBtn.setFont(new Font("맑은고딕", Font.BOLD, 15));
		lookupBtn.setBounds(550, 70, 111, 80);
		lookupSellRecord.add(lookupBtn);

		dateTextField = new JTextField();
		dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dateTextField.setColumns(10);
		dateTextField.setBounds(223, 116, 297, 34);
		lookupSellRecord.add(dateTextField);

		sellCodeTextField = new JTextField();
		sellCodeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		sellCodeTextField.setColumns(10);
		sellCodeTextField.setBounds(223, 70, 297, 34);
		lookupSellRecord.add(sellCodeTextField);

		JLabel sellCodeInputLabel = new JLabel("\uD310\uB9E4\uCF54\uB4DC\uC785\uB825");
		sellCodeInputLabel.setFont(new Font("맑은고딕", Font.BOLD, 14));
		sellCodeInputLabel.setBounds(110, 70, 101, 34);
		lookupSellRecord.add(sellCodeInputLabel);

		JLabel dataInputLabel = new JLabel("\uB0A0\uC9DC\uC785\uB825");
		dataInputLabel.setFont(new Font("맑은고딕", Font.BOLD, 14));
		dataInputLabel.setBounds(138, 116, 58, 34);
		lookupSellRecord.add(dataInputLabel);

		// 영수증 출력 버튼
		JButton receiptBtn = new JButton("\uC601\uC218\uC99D \uCD9C\uB825");
		receiptBtn.addActionListener(new ActionListener() {
			// 영수증 출력하기
			public void actionPerformed(ActionEvent e) {
				
				// 영수증 출력을 위한 부분
				if (lookupSellRecordTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "영수증을 출력하기 위한 거래를 선택해주세요!");
					return;
				}

				// 출력할 것인지 묻는 다이얼로그
				int cancel = JOptionPane.showConfirmDialog(null, "이 거래에 대한 영수증을 출력하시겠습니까?", "취소", JOptionPane.YES_NO_OPTION);

				if (cancel != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "해당 거래에 대한 영수증 출력이 취소되었습니다!");
					return;
				} else {
					receiptOutputDisplay();
				}

			}
		});
		receiptBtn.setFont(new Font("맑은고딕", Font.BOLD, 13));
		receiptBtn.setBounds(632, 485, 117, 36);
		lookupSellRecord.add(receiptBtn);

		// 판매 취소 버튼
		JButton cancelBtn = new JButton("\uD310\uB9E4\uCDE8\uC18C");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 컨트롤 클래스 선언
				sellInfoManagementControl control = new sellInfoManagementControl();

				if (lookupSellRecordTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "삭제하고자하는 행을 선택해주세요");
					return;
				}

				// 삭제할 것인지 묻는 다이얼로그
				int delete = JOptionPane.showConfirmDialog(null, "정말 취소하시겠습니까?", "취소", JOptionPane.YES_NO_OPTION);

				if (delete != JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "판매취소 요청이 취소되었습니다!");
					return;
				}

				// 선택된 레코드
				int selectedRow = lookupSellRecordTable.getSelectedRow();
				String sellCode = (String) lookupSellRecordTable.getValueAt(selectedRow, 1);
				int result = control.sellProudctcancel(sellCode);

				if (result == 1) {
					if (delete != JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "판매취소되었습니다!");
						return;
					}
				}

			}
		});

		cancelBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		cancelBtn.setBounds(504, 485, 117, 36);
		lookupSellRecord.add(cancelBtn);

		// 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm1 = sellInfoTable.getColumnModel();
		TableColumnModel tcm2 = lookupSellRecordTable.getColumnModel();

		// 전체 열 정렬
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		}
		// 전체 열 정렬
		for (int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(dtcr);
		}

		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //프로그램종료
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose(); //현재창만 닫는다.
	}

	/*------------------------ 탭 메뉴 끝 ------------------------*/

	/*------------------------ 최종 판매완료 창 ------------------------*/

	public void sellSuccessDisplay() {

		JFrame sellSuccessDisplay = new JFrame();

		sellSuccessDisplay.setTitle(
				"\uC0C1\uD488\uD310\uB9E4-\uACE0\uAC1D\uC815\uBCF4 \uD655\uC778 \uBC0F \uD310\uB9E4\uC644\uB8CC");
		sellSuccessDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sellSuccessDisplay.setBounds(100, 100, 699, 400);
		sellSuccessDisplay.setVisible(true);

		successSellPanel = new JPanel();
		successSellPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sellSuccessDisplay.setContentPane(successSellPanel);
		successSellPanel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 69, 661, 15);
		successSellPanel.add(separator);

		JLabel customerInfoLabel = new JLabel("\uACE0\uAC1D \uC815\uBCF4 \uD655\uC778");
		customerInfoLabel.setFont(new Font("나눔고딕", Font.BOLD, 14));
		customerInfoLabel.setBounds(43, 112, 96, 15);
		successSellPanel.add(customerInfoLabel);

		customerInfoTextField = new JTextField();

		// 고객 이름 받아오는 텍스트 필드
		customerInfoTextField.setBounds(159, 106, 262, 27);
		successSellPanel.add(customerInfoTextField);
		customerInfoTextField.setColumns(10);

		// 고객 이름 입력 시 버튼으로 정보 확인
		customerInfoLBtn = new JButton("\uD655\uC778");
		customerInfoLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sellInfoManagementControl control = new sellInfoManagementControl();

				String name = customerInfoTextField.getText();
				String returnCustomerCode = control.customerCheck(name);
				
				//위험도 가져오기
				int level = control.getInterestedCustomer(name);
				System.out.println("이것은레벨이다다다다다"+level);
				
				totalLevel = level;
				
				if(level >= 80) {
					JOptionPane.showMessageDialog(null, "반드시 복약지도를 해야하는 대상입니다!");
					return;
				}
				
				interestedCusPrograss.setValue(level);
				
				if (returnCustomerCode == "비회원") {
					JOptionPane.showMessageDialog(null, "해당 가입자를 찾을 수 없습니다!");
					String temp = control.getNoCustomerCode();
					sellInfo.setCustomerCode(temp);
					returnCustomerCode = " ";

				} else {
					// 할인율을 적용 해 봅시다
					int totalPrice = sellInfo.getTotalPrice();
					double totalTemp = DISCOUNT * totalPrice;

					totalPrice = (int) (totalPrice - totalTemp);
					sellInfo.setTotalPrice(totalPrice);
					sellInfo.setCustomerCode(returnCustomerCode);

					TotalResultLabelView.setText(Integer.toString(totalPrice));
				}

			}
		});

		customerInfoLBtn.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		customerInfoLBtn.setBounds(444, 105, 91, 28);
		successSellPanel.add(customerInfoLBtn);

		// 비고객이면 체크하도록 하기
		noCustomerInfoCheck = new JCheckBox("\uC678\uBD80\uACE0\uAC1D");
		noCustomerInfoCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				sellInfoManagementControl control = new sellInfoManagementControl();

				String returnCode = control.getNoCustomerCode();

				if (e.getStateChange() == ItemEvent.SELECTED) {

					int result = JOptionPane.showConfirmDialog(null, "고객 아님이 체크됩니다!", "Confirm",
							JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.CLOSED_OPTION) {
					} else if (result == JOptionPane.YES_OPTION) {
						sellInfo.setCustomerCode(returnCode);
					}
				}

			}

		});

		JCheckBox paymentCheckboxCard = new JCheckBox("\uCE74\uB4DC");
		paymentCheckboxCard.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sellInfo.setPaymentMethod(1);
			}
		});
		buttonGroup.add(paymentCheckboxCard);
		paymentCheckboxCard.setBounds(38, 230, 62, 23);
		successSellPanel.add(paymentCheckboxCard);
		
		JCheckBox paymentCheckboxCash = new JCheckBox("\uD604\uAE08");
		paymentCheckboxCash.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sellInfo.setPaymentMethod(1);
			}
		});
		
		buttonGroup.add(paymentCheckboxCash);
		paymentCheckboxCash.setBounds(104, 230, 62, 23);
		successSellPanel.add(paymentCheckboxCash);
		
		noCustomerInfoCheck.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		noCustomerInfoCheck.setBounds(566, 108, 91, 23);
		successSellPanel.add(noCustomerInfoCheck);

		JLabel successSellTitleLabel = new JLabel("\uC0C1\uD488\uD310\uB9E4");
		successSellTitleLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		successSellTitleLabel.setBounds(27, 27, 96, 33);
		successSellPanel.add(successSellTitleLabel);

		interestedCusPrograss = new JProgressBar();
		interestedCusPrograss.setToolTipText("");
		interestedCusPrograss.setValue(0);
		interestedCusPrograss.setBounds(43, 183, 593, 27);
		successSellPanel.add(interestedCusPrograss);

		JLabel interestedCusLabel = new JLabel("\uACE0\uAC1D \uC704\uD5D8 \uC218\uC900");
		interestedCusLabel.setFont(new Font("나눔고딕", Font.BOLD, 14));
		interestedCusLabel.setBounds(43, 158, 96, 15);
		successSellPanel.add(interestedCusLabel);

		JLabel TotalResultLabel = new JLabel("\uCD1D \uAC00\uACA9");
		TotalResultLabel.setFont(new Font("나눔고딕", Font.BOLD, 16));
		TotalResultLabel.setBounds(491, 241, 50, 27);
		successSellPanel.add(TotalResultLabel);

		// 최종 가격 보여주는 라벨
		TotalResultLabelView = new JLabel("");
		TotalResultLabelView.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		TotalResultLabelView.setBounds(549, 243, 87, 23);
		successSellPanel.add(TotalResultLabelView);

		// 최종적으로 판매완료를 하는 버튼
		successSellBtn = new JButton("\uD310\uB9E4\uC644\uB8CC");
		successSellBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "판매완료했습니다!", "정보", JOptionPane.INFORMATION_MESSAGE);
				sellInfoManagementControl control = new sellInfoManagementControl();
				control.sellProduct(productInfo, sellInfo);
				control.setAttentionCustomer(totalLevel, sellInfo.getCustomerCode());
				
				for(int i = 0; i < productInfo.size(); i++) {
					control.decreaseStockQty(productInfo.get(i).getQty(), productInfo.get(i).getProductCode());
				}
				
				sellSuccessDisplay.setVisible(false);
			}
		});

		successSellBtn.setBounds(272, 308, 139, 41);
		successSellPanel.add(successSellBtn);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 283, 661, 15);
		successSellPanel.add(separator_1);
	}

	
	/*------------------------ 영수증 출력을 위한 창 ------------------------*/
	
	public void receiptOutputDisplay() {
	
		JFrame receiptOutputDisplay = new JFrame();
		receiptOutputDisplay.setVisible(true);

		receiptOutputDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		receiptOutputDisplay.setBounds(100, 100, 448, 568);
		receiptOutputPanel = new JPanel();
		receiptOutputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		receiptOutputDisplay.setContentPane(receiptOutputPanel);
		receiptOutputPanel.setLayout(null);

		JLabel receiptTitleLabel = new JLabel("\uC601\uC218\uC99D");
		receiptTitleLabel.setFont(new Font("굴림", Font.BOLD, 24));
		receiptTitleLabel.setBounds(166, 27, 79, 51);
		receiptOutputPanel.add(receiptTitleLabel);

		JLabel basic_label1 = new JLabel("\uC0C1\uD638 : \uC18C\uACF5\uC57D\uAD6D");
		basic_label1.setFont(new Font("굴림", Font.PLAIN, 12));
		basic_label1.setBounds(30, 86, 118, 18);
		receiptOutputPanel.add(basic_label1);

		JLabel basic_label2 = new JLabel("\uC0AC\uC5C5\uC790\uBC88\uD638 : SW-TWO-PEOPLE-09");
		basic_label2.setFont(new Font("굴림", Font.PLAIN, 12));
		basic_label2.setBounds(30, 107, 268, 18);
		receiptOutputPanel.add(basic_label2);

		JLabel basic_label3 = new JLabel("\uC804\uD654\uBC88\uD638 : 054-478-7114");
		basic_label3.setFont(new Font("굴림", Font.PLAIN, 12));
		basic_label3.setBounds(30, 127, 268, 18);
		receiptOutputPanel.add(basic_label3);

		JLabel basic_label4 = new JLabel(
				"\uC8FC\uC18C : (39253) \uACBD\uBD81 \uAD6C\uBBF8\uC2DC \uAD6C\uBBF8\uB300\uB85C 350-27 (\uC2E0\uD3C9\uB3D9\uCEA0\uD37C\uC2A4)");
		basic_label4.setFont(new Font("굴림", Font.PLAIN, 12));
		basic_label4.setBounds(30, 147, 321, 18);
		receiptOutputPanel.add(basic_label4);

		receiptDateTextField = new JLabel("date");
		receiptDateTextField.setFont(new Font("굴림", Font.PLAIN, 12));
		receiptDateTextField.setBounds(30, 196, 321, 18);
		receiptOutputPanel.add(receiptDateTextField);

		JLabel label = new JLabel("================================================================");
		label.setBounds(24, 224, 410, 15);
		receiptOutputPanel.add(label);

		JLabel label_1 = new JLabel("================================================================");
		label_1.setBounds(24, 256, 410, 15);
		receiptOutputPanel.add(label_1);

		JLabel label_2 = new JLabel("\uC0C1\uD488\uBA85");
		label_2.setFont(new Font("굴림", Font.PLAIN, 12));
		label_2.setBounds(81, 238, 48, 18);
		receiptOutputPanel.add(label_2);

		JLabel label_3 = new JLabel("\uC218\uB7C9");
		label_3.setFont(new Font("굴림", Font.PLAIN, 12));
		label_3.setBounds(218, 239, 48, 18);
		receiptOutputPanel.add(label_3);

		JLabel label_4 = new JLabel("\uAC00\uACA9");
		label_4.setFont(new Font("굴림", Font.PLAIN, 12));
		label_4.setBounds(328, 238, 48, 18);
		receiptOutputPanel.add(label_4);

		JLabel label_5 = new JLabel("================================================================");
		label_5.setBounds(24, 455, 410, 15);
		receiptOutputPanel.add(label_5);

		JLabel receiptSellPriceLabelTitle = new JLabel("\uD310\uB9E4\uAE08\uC561");
		receiptSellPriceLabelTitle.setFont(new Font("굴림", Font.BOLD, 15));
		receiptSellPriceLabelTitle.setBounds(251, 480, 79, 31);
		receiptOutputPanel.add(receiptSellPriceLabelTitle);

		//최종 가격 나타내는 부분
		receiptSellPriceLabel = new JLabel();
		receiptSellPriceLabel.setBounds(328, 488, 79, 15);
		receiptOutputPanel.add(receiptSellPriceLabel);

		// 제품 이름 나타내는 곳
		recieptProductNameLabel = new JTextArea();
		recieptProductNameLabel.setEditable(false);
		recieptProductNameLabel.setLineWrap(true);
		recieptProductNameLabel.setOpaque(false);
		recieptProductNameLabel.setBounds(24, 281, 163, 164);
		receiptOutputPanel.add(recieptProductNameLabel);

		//수량 나타내는 곳
		recieptProductQtyLabel = new JTextArea();
		recieptProductQtyLabel.setEditable(false);
		recieptProductQtyLabel.setLineWrap(true);
		recieptProductQtyLabel.setOpaque(false);
		recieptProductQtyLabel.setBounds(207, 282, 70, 164);
		receiptOutputPanel.add(recieptProductQtyLabel);

		//가격 나타내는 곳
		recieptProductPriceLabel = new JTextArea();
		recieptProductPriceLabel.setEditable(false);
		recieptProductPriceLabel.setLineWrap(true);
		recieptProductPriceLabel.setOpaque(false);
		recieptProductPriceLabel.setBounds(305, 281, 95, 164);
		receiptOutputPanel.add(recieptProductPriceLabel);	
		
		//영수증 출력을 위한 부분
		selectedColum();
		ArrayList<StringBuilder> proudctInfoList = new ArrayList<StringBuilder>();
		sellInfoManagementControl control = new sellInfoManagementControl();
		proudctInfoList = control.requestOutputReceipt(receiptSellCode);
		
		receiptDateTextField.setText(proudctInfoList.get(0).toString());
		recieptProductNameLabel.setText(proudctInfoList.get(2).toString());
		recieptProductQtyLabel.setText(proudctInfoList.get(3).toString());
		recieptProductPriceLabel.setText(proudctInfoList.get(4).toString());	
		receiptSellPriceLabel.setText(proudctInfoList.get(1).toString());

	}

	// 컬럼 선택 후 판매코드 리턴
	public void selectedColum() {
		
		

		// 선택된 레코드
		int selectedRow = lookupSellRecordTable.getSelectedRow();
		receiptSellCode = (String) lookupSellRecordTable.getValueAt(selectedRow, 1);		
		
	}


}

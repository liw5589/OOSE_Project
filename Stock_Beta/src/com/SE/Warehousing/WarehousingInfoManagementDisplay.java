package com.SE.Warehousing;
//  @ Project : 소공 약국
//  @ File Name : WarehousingInfoManagementDisplay.java
//  @ Date : 2019-05-20
//  @ Author : 신충섭

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.SE.BaseData.SettingInfo;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class WarehousingInfoManagementDisplay extends JFrame {
   private JTable table1;
   private JTable table2;
   private JTextField txtDate;
   private JTextField txtProduct;
   private JTextArea txtrA;
   private WarehousingInfoManagement wimg;
   //private SettingInfo settingInfo=null;
   Vector<Vector<String>> warehousingInfos;
   
   // modifyFrame
   JTextField textField1;
   JTextField textField2;
   JTextField textField3;
   JTextField textField4;
   JTextField textField5;
   JTextField textField6;
   JTextField textField7;
   JTextField textField8;
   JTextField textField9;
   public WarehousingInfoManagementDisplay() {
      wimg = new WarehousingInfoManagement();
      
      setBounds(300, 50, 800, 600);
      getContentPane().setLayout(new BorderLayout(0, 0));
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

      getContentPane().add(tabbedPane, BorderLayout.CENTER);

      JPanel panel1 = new JPanel();
      tabbedPane.addTab("\uC785\uACE0\uC815\uBCF4\uB4F1\uB85D", null, panel1, null);
      panel1.setLayout(null);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(50, 80, 700, 383);
      panel1.add(scrollPane_1);

      table1 = new JTable(); // 이 테이블이 출력하는정보 : 식재료신청자 , 입금내역 , 총 계산된 단가(식재료 신청자명단조회)
      table1.setRowHeight(25);
      scrollPane_1.setViewportView(table1);

      Vector<String> header = new Vector<String>();
      header.add("제품코드");
      header.add("입고일");
      header.add("단위");
      header.add("수량");
      header.add("단가");
      header.add("입고상태");
      header.add("거래처");
      header.add("입고자코드");

      Vector<Vector<String>> content1 = new Vector<Vector<String>>();
      Vector<String> warehousingInfo = new Vector<String>();

      content1.add(warehousingInfo);
      DefaultTableModel model = new DefaultTableModel(content1, header);
      table1.setModel(model);

      JLabel label2 = new JLabel("\uC785\uACE0\uC815\uBCF4\uB4F1\uB85D");
      label2.setHorizontalAlignment(SwingConstants.CENTER);
      label2.setFont(new Font("굴림", Font.BOLD, 20));
      label2.setBounds(126, 10, 523, 50);
      panel1.add(label2);

      JButton button2 = new JButton("\uB4F1\uB85D\uD558\uAE30");
      button2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {         
            Vector<Vector> infos = new Vector<Vector>();
            infos = model.getDataVector();
            if(infos.get(0).get(0)==null) {
               JOptionPane.showMessageDialog(null, "등록정보를 확인해주세요");
               return;
            }
            boolean result = wimg.insertWarehousings(infos);
            
            if(result) {
               JOptionPane.showMessageDialog(null, "등록되었습니다.");         
            }
            else {
               JOptionPane.showMessageDialog(null, "등록에 실패하였습니다. 다시 확인해주세요");
            }
         }
      });
      button2.setFont(new Font("굴림", Font.BOLD, 15));
      button2.setBounds(639, 482, 111, 34);
      panel1.add(button2);

      JButton button4 = new JButton("\uD589\uCD94\uAC00");
      button4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Vector<String> info = new Vector<String>();
            model.addRow(info);

         }
      });
      button4.setFont(new Font("굴림", Font.BOLD, 15));
      button4.setBounds(516, 482, 111, 34);
      panel1.add(button4);

      JPanel panel2 = new JPanel();
      tabbedPane.addTab("\uC785\uACE0\uC815\uBCF4\uC870\uD68C.\uC218\uC815", null, panel2, null);
      panel2.setLayout(null);

      JScrollPane scrollPane2 = new JScrollPane();
      scrollPane2.setBounds(49, 170, 700, 305);
      panel2.add(scrollPane2);

      table2 = new JTable();
      table2.setRowHeight(25);
      scrollPane2.setViewportView(table2);
      Vector<String> header2 = new Vector<String>();
      Vector<Vector<String>> content2 = new Vector<Vector<String>>();

      header2.add("입고코드");
      header2.add("제품명");
      header2.add("입고일");
      header2.add("단위");
      header2.add("수량");
      header2.add("단가");
      header2.add("입고상태");
      header2.add("거래처");
      header2.add("입고담당자");

      DefaultTableModel model2 = new DefaultTableModel(content2, header2);
      table2.setModel(model2);

      JLabel label3 = new JLabel("\uC785\uACE0\uC815\uBCF4\uC870\uD68C.\uC218\uC815");
      label3.setHorizontalAlignment(SwingConstants.CENTER);
      label3.setFont(new Font("굴림", Font.BOLD, 20));
      label3.setBounds(130, 10, 523, 50);
      panel2.add(label3);

      // 조회버튼 클릭시
      JButton button3 = new JButton("\uC870\uD68C\uD558\uAE30");
      button3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            String regExp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$";
            String str = txtDate.getText();

            
            if (txtProduct.getText().equals("") && txtDate.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "검색 조건을 입력해주세요");
               return;
            }
            // 제품명 조회
            else if (!txtProduct.getText().equals("") && txtDate.getText().equals("")) {
               warehousingInfos = wimg.lookupWarehousingsAsProductName(txtProduct.getText());
            }
            // 날짜 형식 확인
            else if (!str.matches(regExp)) {
               JOptionPane.showMessageDialog(null, "날짜형식을 확인해주세요(yyyy-mm-dd)");
               return;
            }
            // 날짜별 조회
            else if (txtProduct.getText().equals("") && !txtDate.getText().equals("")) {
               warehousingInfos = wimg.lookupWarehousingsAsWarehousingDate(txtDate.getText());
            }
            // 제품명, 날짜별 조회
            else {
               warehousingInfos = wimg.lookupWarehousings(txtProduct.getText(), txtDate.getText());
            }
            // 기존데이터 제거
            while (model2.getRowCount() != 0) {
               model2.removeRow(0);
            }
            // 조회데이터 삽입
            for (int i = 0; i < warehousingInfos.size(); i++) {
               model2.addRow(warehousingInfos.get(i));
            }
         }
      });
      button3.setFont(new Font("굴림", Font.BOLD, 15));
      button3.setBounds(550, 70, 111, 80);
      panel2.add(button3);

      txtDate = new JTextField();
      txtDate.setText("\uB0A0\uC9DC\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694(ex.2019-5-25)");
      txtDate.setHorizontalAlignment(SwingConstants.CENTER);
      txtDate.setColumns(10);
      txtDate.setBounds(223, 116, 297, 34);
      panel2.add(txtDate);

      txtProduct = new JTextField();
      txtProduct.setText("\uC81C\uD488\uBA85\uC744 \uC785\uB825\uD574\uC8FC\uC138\uC694");
      txtProduct.setHorizontalAlignment(SwingConstants.CENTER);
      txtProduct.setColumns(10);
      txtProduct.setBounds(223, 70, 297, 34);
      panel2.add(txtProduct);

      JLabel lblNewLabel = new JLabel("\uC81C\uD488\uBA85");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
      lblNewLabel.setBounds(140, 70, 56, 34);
      panel2.add(lblNewLabel);

      JLabel label = new JLabel("\uC785\uACE0\uC77C");
      label.setFont(new Font("굴림", Font.BOLD, 14));
      label.setBounds(140, 116, 56, 34);
      panel2.add(label);

      JButton btnNewButton = new JButton("\uC218\uC815");
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 13));
      // 수정 클릭시
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if(table2.getSelectedRow()==-1) {
               JOptionPane.showMessageDialog(null, "수정하고자하는 행을 선택해주세요");
               return;
            }
            int selectedRow = table2.getSelectedRow();
            String warehousingCode = (String)table2.getValueAt(selectedRow, 0);
            WarehousingInfo warehousingInfo = wimg.lookupWarehousingsAsWarehousingCode(warehousingCode);
            
            printWarehousingModifyDisplay();
            textField1.setText(warehousingInfo.getWarehousingCode());
            textField2.setText(warehousingInfo.getProductCode());
            textField3.setText(warehousingInfo.getWarehousingDate());
            textField4.setText(warehousingInfo.getUnit());
            textField5.setText(warehousingInfo.getQty()+"");
            textField6.setText(warehousingInfo.getUnitPrice()+"");
            textField7.setText(warehousingInfo.getWarehousingState());
            textField8.setText(warehousingInfo.getSupplierName());
            textField9.setText(warehousingInfo.getUserCode());
         }
      });
      btnNewButton.setBounds(501, 486, 74, 36);
      panel2.add(btnNewButton);

      JButton button = new JButton("\uC0AD\uC81C");
      button.addActionListener(new ActionListener() {
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
            String warehousingCode = (String)table2.getValueAt(selectedRow, 0);
            boolean result = wimg.deleteWarehousing(warehousingCode);
            
            if(result) {
               JOptionPane.showMessageDialog(null, "삭제되었습니다.");
               button3.doClick();               
            }
            else {
               JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다. 다시 시도해주세요");
            }
         }
      });
      button.setFont(new Font("굴림", Font.BOLD, 13));
      button.setBounds(587, 486, 74, 36);
      panel2.add(button);
      
      // 확인증 출력버튼
      JButton button1 = new JButton("\uCD9C\uB825");
      button1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            printWarehousingProof();
         }
      });
      button1.setFont(new Font("굴림", Font.BOLD, 13));
      button1.setBounds(675, 485, 74, 36);
      panel2.add(button1);

      // 셀 가운데 정렬
      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
      TableColumnModel tcm1 = table1.getColumnModel();
      TableColumnModel tcm2 = table2.getColumnModel();
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

   public static void main(String[] args) {
      new WarehousingInfoManagementDisplay();
   }
   private void printWarehousingModifyDisplay() {
      JFrame modifyFrame = new JFrame();
      JPanel contentPane;
      modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      modifyFrame.setBounds(300, 100, 405, 440);
      modifyFrame.setVisible(true);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      modifyFrame.setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("<\uC785\uACE0\uC815\uBCF4\uC218\uC815>");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel.setBounds(138, 10, 112, 30);
      contentPane.add(lblNewLabel);
      
      JLabel lblNewLabel1 = new JLabel("\uC785\uACE0\uCF54\uB4DC");
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
      
      textField7 = new JTextField();
      textField7.setColumns(10);
      textField7.setBounds(109, 258, 245, 25);
      contentPane.add(textField7);
      
      textField8 = new JTextField();
      textField8.setColumns(10);
      textField8.setBounds(109, 293, 245, 25);
      contentPane.add(textField8);
      
      textField9 = new JTextField();
      textField9.setColumns(10);
      textField9.setBounds(109, 328, 245, 25);
      contentPane.add(textField9);
      
      JLabel label1 = new JLabel("\uC81C\uD488\uCF54\uB4DC");
      label1.setFont(new Font("굴림", Font.BOLD, 13));
      label1.setBounds(28, 83, 77, 25);
      contentPane.add(label1);
      
      JLabel label2 = new JLabel("\uC785\uACE0\uC77C");
      label2.setFont(new Font("굴림", Font.BOLD, 13));
      label2.setBounds(28, 118, 77, 25);
      contentPane.add(label2);
      
      JLabel label3 = new JLabel("\uB2E8\uC704");
      label3.setFont(new Font("굴림", Font.BOLD, 13));
      label3.setBounds(28, 153, 77, 25);
      contentPane.add(label3);
      
      JLabel label4 = new JLabel("\uC218\uB7C9");
      label4.setFont(new Font("굴림", Font.BOLD, 13));
      label4.setBounds(28, 188, 77, 25);
      contentPane.add(label4);
      
      JLabel label5 = new JLabel("\uB2E8\uAC00");
      label5.setFont(new Font("굴림", Font.BOLD, 13));
      label5.setBounds(28, 223, 77, 25);
      contentPane.add(label5);
      
      JLabel label6 = new JLabel("\uC785\uACE0\uC0C1\uD0DC");
      label6.setFont(new Font("굴림", Font.BOLD, 13));
      label6.setBounds(28, 258, 77, 25);
      contentPane.add(label6);
      
      JLabel label7 = new JLabel("\uAC70\uB798\uCC98");
      label7.setFont(new Font("굴림", Font.BOLD, 13));
      label7.setBounds(28, 293, 77, 25);
      contentPane.add(label7);
      
      JLabel label8 = new JLabel("\uC785\uACE0\uC790\uCF54\uB4DC");
      label8.setFont(new Font("굴림", Font.BOLD, 13));
      label8.setBounds(28, 328, 77, 25);
      contentPane.add(label8);
      
      JButton btnNewButton = new JButton("\uCDE8\uC18C");
      btnNewButton.setBounds(294, 366, 60, 25);
      contentPane.add(btnNewButton);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
//            modifyFrame.setVisible(false);
            modifyFrame.dispose();
         }
      });
      
      JButton button = new JButton("\uC218\uC815");
      button.setBounds(227, 366, 60, 25);
      contentPane.add(button);
      // 수정버튼클릭
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            boolean result = false;
            try {
            WarehousingInfo warehousingInfo = new WarehousingInfo();
            warehousingInfo.setWarehousingCode(textField1.getText());
            warehousingInfo.setProductCode(textField2.getText());
            warehousingInfo.setWarehousingDate(textField3.getText());
            warehousingInfo.setUnit(textField4.getText());
            warehousingInfo.setQty(Integer.parseInt(textField5.getText()));
            warehousingInfo.setUnitPrice(Integer.parseInt(textField6.getText()));
            warehousingInfo.setWarehousingState(textField7.getText());
            warehousingInfo.setSupplierName(textField8.getText());
            warehousingInfo.setUserCode(textField9.getText());
            result = wimg.modifyWarehousing(warehousingInfo);
            
            if(result) {
               JOptionPane.showMessageDialog(null, "수정되었습니다.");
            }
            else {
               JOptionPane.showMessageDialog(null, "수정에 실패하였습니다. 입력정보를 확인해주세요");
            }
            }
            catch(Exception exception) {
               JOptionPane.showMessageDialog(null, "수정에 실패하였습니다. 입력정보를 확인해주세요");
            }
            
         }
      });
   }
   private void printWarehousingProof() {
      JFrame modifyFrame = new JFrame();
      JPanel contentPane;
      modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      modifyFrame.setBounds(100, 100, 1000, 440);
      modifyFrame.setVisible(true);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      modifyFrame.setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("<\uC785\uACE0\uD655\uC778\uC99D>");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
      lblNewLabel.setBounds(414, 10, 112, 30);
      contentPane.add(lblNewLabel);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 38, 960, 353);
      contentPane.add(scrollPane);
      
      txtrA = new JTextArea();
      scrollPane.setViewportView(txtrA);
      txtrA.setEditable(false);

      txtrA.append("입고코드");
      txtrA.append("\t");
      txtrA.append("\t");
      txtrA.append("제품명");
      txtrA.append("\t");
      txtrA.append("입고일");
      txtrA.append("\t");
      txtrA.append("\t");
      txtrA.append("단위");
      txtrA.append("\t");
      txtrA.append("수량");
      txtrA.append("\t");
      txtrA.append("단가");
      txtrA.append("\t");
      txtrA.append("입고상태");
      txtrA.append("\t");
      txtrA.append("거래처");
      txtrA.append("\t");
      txtrA.append("입고담당자");
      txtrA.append("\n");

      for(int i=0; i<warehousingInfos.size(); i++) {
         Vector<String> warehousingInfo = warehousingInfos.get(i);
         txtrA.append(warehousingInfo.get(0));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(1));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(2));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(3));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(4));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(5));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(6));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(7));
         txtrA.append("\t");
         txtrA.append(warehousingInfo.get(8));
         txtrA.append("\n");
      }
   }
	
}

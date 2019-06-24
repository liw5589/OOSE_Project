package com.SE.BaseData;
import java.sql.SQLException;

import javax.swing.JOptionPane;



class LoginInfoManagement {

	private boolean loginCheck(String userCode) throws ClassNotFoundException, SQLException {
		
		DBManager db = new DBManager();
		int temp = db.login(userCode);
		if(temp == 1)
			return true;
		else {
			return false;
		}
		
	}
	public boolean login(String userCode) throws ClassNotFoundException, SQLException {
		
		boolean loginState = loginCheck(userCode);
		
		if(loginState == true) {
			return true;
		}
		else {
			return false;
		}
	}

};


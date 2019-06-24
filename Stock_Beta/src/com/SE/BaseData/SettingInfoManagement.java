package com.SE.BaseData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SettingInfoManagement {

	public void setupSetting(int fontSize,String thema,int discountRate)
	{ 
		String fileName = "settingInfo.txt";
		try {
			PrintWriter out  = new PrintWriter(fileName);
			out.println(fontSize);
			out.println(thema);
			out.println(discountRate);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

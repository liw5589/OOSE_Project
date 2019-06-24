package com.SE.main;

public class test {

	
	public static void main(String[] args) {
		String date = "2019-05-05 00:00:00";
		int year = Integer.parseInt(date.substring(0,4)) + 3;
		String mmdd = date.substring(5,10);
		String full =  year + "-"+ mmdd;
		System.out.println(full);
		
	}
}

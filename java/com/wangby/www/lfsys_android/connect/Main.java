package com.wangby.www.lfsys_android.connect;

public class Main {
	public static void main(String[] args) {
		User user = new User();
		user.setStuNum(1514010505);
		user.setPassword("3.1415926123456");
		System.out.println(Function.login(user));
		System.out.println(Function.showFound());
		System.out.println(Function.showLost());
		System.out.println(Function.showArgument(1));
		System.out.println(Function.showClue(1));
		
	}
}

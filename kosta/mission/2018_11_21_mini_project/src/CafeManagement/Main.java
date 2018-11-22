package CafeManagement;

import Info.InfoManager;
import User.Admin;
import User.Guest;
import User.Login;
import User.User;

public class Main {

	public static void DataInit(){			
		InfoManager.getInst().readUser(System.getProperty("user.dir")+"\\userInfo.csv");
		InfoManager.getInst().readMenu(System.getProperty("user.dir")+"\\menu.csv");
	}
	
	public static void main(String[] args) {

		Manager manager = new Manager();

		DataInit();					// 데이터 초기화
	
		User user = new Admin();
		manager.start(user);
	}

}

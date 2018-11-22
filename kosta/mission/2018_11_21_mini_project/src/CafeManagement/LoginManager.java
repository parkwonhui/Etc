package CafeManagement;

import Info.InfoManager;
import ScannerManager.ScannerManager;
import User.Guest;
import User.User;

public class LoginManager {
	
	public LoginManager() {}
	
	public void login() {
		Manager manager = new Manager();
		User user = null;
		while(true) {
		
			if(null == user)
				user = new Guest();
			
			manager.start(user);

			user = loginInput(user);
		}
	}
	
	public User loginInput(User ori) {
		try {
			System.out.println("로그인 id 입력");
			String id = ScannerManager.ReadString();
			System.out.println("로그인 pass 입력");
			String pass = ScannerManager.ReadString();
			
			User user = InfoManager.getInst().searchUser(id);
			if(null == user) {
				System.out.println("유저를 찾을 수 없습니다");
				return ori;
			}
			
			if(true == user.isPassEquals(pass)) {
				System.out.println("로그인 성공!");
				return user;
			}else {
				System.out.println("로그인 실패!");
			}
			
		} catch (Exception e) {
			System.out.println("잘못입력 하셨습니다");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ori;		
	}

}

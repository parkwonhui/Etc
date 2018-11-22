package User;

import Menu.Menu;
import ScannerManager.ScannerManager;
import User.User.INPUT_TYPE;

public class Admin extends User{
	
	public Admin(){}

	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[관리자모드/매장관리] 1.메뉴추가 2.메뉴수정 3.메뉴삭제 4.회원검색 5.재고관리 6.로그아웃");
		int value = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		switch(value){
			case 1 : return User.INPUT_TYPE.ADMIN_MENU_ADD;
			case 2 : return User.INPUT_TYPE.ADMIN_MENU_MODIFY;
			case 3 : return User.INPUT_TYPE.ADMIN_MENU_DELETE;
			case 4 : return User.INPUT_TYPE.ADMIN_MENU_SEARCH;
			case 5 : return User.INPUT_TYPE.ADMIN_MENU_COUNT;
			case 6 : return User.INPUT_TYPE.LOGOUT;
			default : return null;
		}
	}

	@Override
	public boolean login() {
		return false;
	}

	@Override
	public boolean logout() {
		return true;	
	}


	@Override
	public Menu menuChoice(int menutype){

		return null;
	}
	
	@Override
	public void myMenuPrint() {
	}

	@Override
	public void myMenuModify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuModify() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuCount() {
		// TODO Auto-generated method stub
		
	}

}

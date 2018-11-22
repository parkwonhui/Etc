package User;

import CafeManagement.Manager;
import Menu.Menu;
import ScannerManager.ScannerManager;

public class Guest extends User{

	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[손님모드/메뉴] 1.시즌메뉴 2.음료 3.디저트 4.로그인");
		int value = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		switch(value){
			case 1 : return User.INPUT_TYPE.MENU_COFFEE;
			case 2 : return User.INPUT_TYPE.MENU_SEASON;
			case 3 : return User.INPUT_TYPE.MENU_DESSERT;
			case 4 : return User.INPUT_TYPE.LOGIN;
			default : return null;
		}
	}

	@Override
	public boolean login() {
		return true;
	}

	@Override
	public boolean logout() {
		return false;	
	}

	@Override
	public Menu menuChoice(int menutype){
		// TODO: 시간될 때 분류별로 체크해서 출력하는 코드 추가할 것
		if(Menu.MENUTYPE_COFFEE == menutype)
			System.out.println("[커피] 0.아메리카노 1.카페라떼 2.카페모카");
		else if(Menu.MENUTYPE_SEASON == menutype)
			System.out.println("[시즌] 3.시즌");	
		else if(Menu.MENUTYPE_DESSERT == menutype)
			System.out.println("[디저트] 4.치즈케이크 5.모카케이크 6.마카롱");
		else
			return null;
	
		int index = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
	
		Menu findMenu = Manager.menuList.get(index);
		return findMenu;
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

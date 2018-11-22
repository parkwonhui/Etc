package User;

import CafeManagement.Manager;
import Menu.Menu;
import ScannerManager.ScannerManager;
import User.User.INPUT_TYPE;

public class Login extends User {
	
	// 마이메뉴
	private Menu myMenu;		// set,get 함수 추가할 것!!
	
	public Login(Menu menu){
		myMenu = menu;
	}
	
	public void SetMyMenu(Menu menu){
		myMenu = menu;	
	}
	
	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[유저모드/메뉴] 1.시즌메뉴 2.음료 3.디저트 4.마이메뉴 5.마이메뉴 수정 6.로그아웃");
		int value = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		switch(value){
			case 1 : return User.INPUT_TYPE.MENU_COFFEE;
			case 2 : return User.INPUT_TYPE.MENU_SEASON;
			case 3 : return User.INPUT_TYPE.MENU_DESSERT;
			case 4 : return User.INPUT_TYPE.MY_MENU;
			case 5 : return User.INPUT_TYPE.MY_MENU_MODIFY;
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
		// TODO: 시간될 때 분류별로 체크해서 출력하는 코드 추가할 것
		// 재고 출력 
		if(Menu.MENUTYPE_COFFEE == menutype)
			System.out.println("[커피] 0.아메리카노 1.카페라떼 2.카페모카");
		else if(Menu.MENUTYPE_SEASON== menutype)
			System.out.println("[시즌] 3.시즌");	
		else if(Menu.MENUTYPE_SEASON== menutype)
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
		System.out.println("myMenu 이름:"+myMenu.getName()+"\n가격:"+myMenu.getPrice()+"\n타입:"+myMenu.getType());
	}

	@Override
	public void myMenuModify() {
		System.out.println("1.에스프레소 2. 아메리카노 3.카페라떼 4.카푸치노"
				+ "5.카페모카 6.카라멜마끼야또 7.산타푸치노 8.루돌프라페");
		ScannerManager.sc.nextInt();
		
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

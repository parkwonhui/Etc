package User;

import java.util.ArrayList;
import java.util.Map;

import CafeManagement.Manager;
import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import ScannerManager.ScannerManager;
import User.User.INPUT_TYPE;

public class Login extends User {
	
	// 마이메뉴
	private Menu myMenu;
	
	public Login(){
		myMenu = null;
	}
	
	public void SetMyMenu(Menu menu){
		myMenu = menu;	
	}
	
	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[유저모드/메뉴] 1.시즌메뉴 2.음료 3.디저트 4.마이메뉴 5.마이메뉴 수정 6.결제 7.로그아웃");
		int value = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		switch(value){
			case 1 : return User.INPUT_TYPE.MENU_COFFEE;
			case 2 : return User.INPUT_TYPE.MENU_SEASON;
			case 3 : return User.INPUT_TYPE.MENU_DESSERT;
			case 4 : return User.INPUT_TYPE.MY_MENU;
			case 5 : return User.INPUT_TYPE.MY_MENU_MODIFY;
			case 6 : return User.INPUT_TYPE.MENU_PAY;
			case 7 : return User.INPUT_TYPE.LOGOUT;
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
		Menu fineMenu = null;
		int choiceCategory = inputMenu(menutype);
		if(-1 == choiceCategory){
			return null;
		}

		Menu findMenu = Manager.menuList.get(choiceCategory);
		Menu newMenu = null;
		// 생성 정리해야할듯..(팩토리매니저만들까?)
		if(Menu.MENUTYPE_COFFEE == findMenu.getType()||
				Menu.MENUTYPE_SEASON == findMenu.getType()){
			newMenu = new Coffee(findMenu.getIndex(), findMenu.getName(), findMenu.getStockNum(), findMenu.getPrice(), findMenu.getType());
			addOption((Coffee)newMenu);
	
		}else if(Menu.MENUTYPE_DESSERT == findMenu.getType()){
			Dessert findMenutoDessert = (Dessert)findMenu;
			newMenu = new Dessert(findMenu.getIndex(), findMenu.getName(), findMenu.getStockNum(), findMenu.getPrice(), findMenu.getType(), findMenutoDessert.getDiscount());
		}
		
		return newMenu;
	}
	
	public int inputMenu(int menutype){
		// TODO: 시간될 때 분류별로 체크해서 출력하는 코드 추가할 것
		if(Menu.MENUTYPE_COFFEE == menutype)
			System.out.println("[커피] 0.아메리카노 1.카페라떼 2.카페모카");
		else if(Menu.MENUTYPE_SEASON == menutype)
			System.out.println("[시즌] 3.시즌");	
		else if(Menu.MENUTYPE_DESSERT == menutype)
			System.out.println("[디저트] 4.치즈케이크 5.모카케이크 6.마카롱");
		else
			return -1;
	
		int index = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		
		return index;
	}
	
	@Override
	public void myMenuPrint() {
		if(null == myMenu)
			System.out.println("myMenu가 없습니다");
		else
			System.out.println("myMenu 이름:"+myMenu.getName()+"\n가격:"+myMenu.getPrice());
	}

	@Override
	public void myMenuModify() {
		System.out.println("MyMenu를 선택하세요(숫자입력)");
		Map<Integer, Menu> menuList = Manager.menuList;
		for(int i = 0; i < Manager.menuList.size(); ++i){
			Menu menu = menuList.get(i);
			if(menu instanceof Coffee){
				System.out.println(menu.getIndex()+"."+menu.getName());
			}
		}
		
		int index = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		
		Menu newMyMenu = menuList.get(index);
		if(null == newMyMenu){
			System.out.println("잘못된 index 입니다");
		}else{
			System.out.println("새로운 MyMenu"+newMyMenu.getName()+"를 등록했습니다");
			SetMyMenu(newMyMenu);
		}
	}

	@Override
	public void adminMenuAdd() {}
	@Override
	public void adminMenuModify() {}
	@Override
	public void adminMenuDelete() {}
	@Override
	public void adminMenuSearch() {}
	@Override
	public void adminMenuCount() {}

	public void addOption(Coffee coffee){
		System.out.println("[옵션추가]옵션추가를 원하시면 1 원치 않으면 0을 눌러주세요");
		boolean bSizeup 		= false; 	// 사이즈업
		boolean bAddShot	 	= false; 	// 샷추가
		boolean bWhippedCream	= false;	// 휘핑추가
		boolean bSyrup			= false;	// 시럽

		System.out.println("[옵션추가] 사이즈 업?(0:No 1:Yes)");
		bSizeup = (1 == ScannerManager.sc.nextInt() ? true:false);
		ScannerManager.sc.nextLine();

		System.out.println("[옵션추가] 샷추가?(0:No 1:Yes)");
		bAddShot = (1 == ScannerManager.sc.nextInt() ? true:false);
		ScannerManager.sc.nextLine();

		System.out.println("[옵션추가] 휘핑추가?(0:No 1:Yes)");
		bWhippedCream = (1 == ScannerManager.sc.nextInt() ? true:false);
		ScannerManager.sc.nextLine();

		System.out.println("[옵션추가] 시럽추가?(0:No 1:Yes)");
		bSyrup = (1 == ScannerManager.sc.nextInt() ? true:false);
		ScannerManager.sc.nextLine();
		
		coffee.setOption(bSizeup, bAddShot, bWhippedCream, bSyrup);
	}
}

package User;

import CafeManagement.Manager;
import Info.InfoManager;
import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import ScannerManager.ScannerManager;

public class Guest extends User{

	public Guest(){}
	
	@Override
	public INPUT_TYPE mainMenu()  throws Exception{
		System.out.println("[손님모드/메뉴] 1.음료 2.시즌메뉴 3.디저트 4.계산 5.로그인");
		int value = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		switch(value){
			case 1 : return User.INPUT_TYPE.MENU_COFFEE;
			case 2 : return User.INPUT_TYPE.MENU_SEASON;
			case 3 : return User.INPUT_TYPE.MENU_DESSERT;
			case 4 : return User.INPUT_TYPE.MENU_PAY;
			case 5 : return User.INPUT_TYPE.LOGIN;			
			default : return null;
		}
	}

	@Override
	public boolean login() throws Exception {
		return true;
	}

	@Override
	public boolean logout()  throws Exception{
		return false;	
	}

	@Override
	public Menu menuChoice(int menutype) throws Exception{
		Menu fineMenu = null;
		int choiceCategory = inputMenu(menutype);
		if(-1 == choiceCategory){
			return null;
		}

		//Menu findMenu = Manager.menuList.get(choiceCategory);
		Menu findMenu = InfoManager.getInst().searchMenu(choiceCategory);
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
	
	@Override
	public void myMenuPrint()  throws Exception{}
	@Override
	public void myMenuModify()  throws Exception{}
	@Override
	public void adminMenuAdd()  throws Exception{}
	@Override
	public void adminMenuModify() throws Exception {}
	@Override
	public void adminMenuDelete()  throws Exception{}
	@Override
	public void adminUserSearch() throws Exception {}
	@Override
	public void adminMenuCount()  throws Exception{}
	
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
		
		// 범위 체크
		if(Menu.MENUTYPE_COFFEE == menutype && index >= 3) return -1;
		else if(Menu.MENUTYPE_SEASON == menutype && index != 3 ) return -1;
		else if(Menu.MENUTYPE_DESSERT == menutype && (index < 4 || index > 6 )) return -1;
		
		return index;
	}
	
	public void addOption(Coffee coffee) throws Exception{
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

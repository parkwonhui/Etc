package User;

import CafeManagement.Manager;
import Info.InfoManager;
import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import ScannerManager.ScannerManager;
import User.User.INPUT_TYPE;

public class Admin extends User{
	
	public Admin(){}

	@Override
	public INPUT_TYPE mainMenu() throws Exception{
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
	public boolean login() throws Exception{
		return false;
	}

	@Override
	public boolean logout() throws Exception{
		return true;	
	}


	@Override
	public Menu menuChoice(int menutype){return null;}
	@Override
	public void myMenuPrint() throws Exception{}
	@Override
	public void myMenuModify() throws Exception{}

	@Override
	public void adminMenuAdd() throws Exception{
		 System.out.println("메뉴 이름 :");
		 String name = ScannerManager.sc.nextLine();
		 System.out.println("메뉴 가격 :");
		 int price = ScannerManager.sc.nextInt();
		 ScannerManager.sc.nextInt();
		 System.out.println("메뉴 할인(90%할인일 시 90):");
		 int discount = ScannerManager.sc.nextInt();
		 ScannerManager.sc.nextLine();
		 System.out.println("메뉴 타입 (0:음료 1:시즌 2:디저트):");
		 int type = ScannerManager.sc.nextInt();
		 
		 int index =  InfoManager.getInst().getMenuDataSize() - 1;
		 Menu newMenu;
		 if(Menu.MENUTYPE_COFFEE == type || Menu.MENUTYPE_SEASON == type)
			 newMenu = new Coffee(index, name, 10, price, type);
		 else if(Menu.MENUTYPE_DESSERT == type)
			 newMenu = new Dessert(index, name, 10, price, type, 90);

		 
	}

	@Override
	public void adminMenuModify() throws Exception{
		InfoManager.getInst().allPrint();
		System.out.println("수정할 메뉴 index를 입력해주세요");
		int index = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();

		Menu menu = InfoManager.getInst().searchMenu(index);
		
		System.out.println("이름 입력");
		String name = ScannerManager.sc.nextLine();
		System.out.println("가격 입력");
		int price  = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		int discount = 100;
		if(menu instanceof Dessert){
			System.out.println("할인율 입력");
			discount  = ScannerManager.sc.nextInt();
			ScannerManager.sc.nextLine();
		}
		int type  = ScannerManager.sc.nextInt();
		ScannerManager.sc.nextLine();
		
		//menu.modifyMenuInfo(String name, int price, int type)
	
	}

	@Override
	public void adminMenuDelete() throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adminMenuSearch() throws Exception{		
	}

	@Override
	public void adminMenuCount() throws Exception{
		InfoManager.getInst().PrintStock();
	}

}

package User;

import java.util.ArrayList;
import java.util.Map;

import CafeManagement.Manager;
import Info.InfoManager;
import Info.UserInfo;
import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import ScannerManager.ScannerManager;
import User.User.INPUT_TYPE;

public class Login extends User {
	
	// 마이메뉴
	private Menu myMenu;
	private UserInfo userInfo;
	
	
	public Login(UserInfo user){
		myMenu = null;
		this.userInfo = user;
	}
	
	public void SetMyMenu(Menu menu) throws Exception{
		myMenu = menu;	
	}
	
	@Override
	public INPUT_TYPE mainMenu() throws Exception {
		System.out.println("EDIE Coffee에 방문하신 "+userInfo.getId()+" 님을 환영합니다");
		System.out.println("[유저모드/메뉴] 1.음료 2.시즌메뉴 3.디저트 4.마이메뉴 5.마이메뉴 수정 6.결제 7.로그아웃");
		System.out.println("===============================================");
		int value = ScannerManager.ReadInt();
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
	public boolean login() throws Exception {
		return false;
	}

	@Override
	public boolean logout() throws Exception {
		return true;	
	}
	public void viewMenu() throws Exception{}
	
	@Override	
	public Menu menuChoice(int menutype) throws Exception{
		Menu fineMenu = null;
		int choiceCategory = inputMenu(menutype);
		if(-1 == choiceCategory){
			return null;
		}
		
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
	
	public int inputMenu(int menutype) throws Exception{
		// TODO: 시간될 때 분류별로 체크해서 출력하는 코드 추가할 것
		if(Menu.MENUTYPE_COFFEE == menutype)
			InfoManager.getInst().allCoffeePrint();
		else if(Menu.MENUTYPE_SEASON == menutype)
			InfoManager.getInst().allSeasonPrint();
		else if(Menu.MENUTYPE_DESSERT == menutype)
			InfoManager.getInst().allDessertPrint();
		else
			return -1;
	
		int index = ScannerManager.ReadInt();
		
		// 입력 범위 체크
		if(false == checkInputMenu(index, menutype))
			index = -1;

		
		return index;
	}
	
	@Override
	public void myMenuPrint() throws Exception{
		if(null == myMenu)
			System.out.println("myMenu가 없습니다");
		else
			System.out.println("myMenu 이름:"+myMenu.getName()+"\n가격:"+myMenu.getPrice());
	}

	@Override
	public void myMenuModify() throws Exception{
		System.out.println("MyMenu를 선택하세요(숫자입력)");
		int size = InfoManager.getInst().getMenuDataSize();
		for(int i = 0; i < size; ++i){
			Menu menu = InfoManager.getInst().searchMenu(i);
			if(menu instanceof Coffee){
				System.out.println(menu.getIndex()+"."+menu.getName());
			}
		}
		
		int index = ScannerManager.ReadInt();
		
		Menu newMyMenu = InfoManager.getInst().searchMenu(index);
		if(null == newMyMenu)
			throw new Exception("없는 메뉴를 선택하였습니다");
		
		if(Menu.MENUTYPE_COFFEE != newMyMenu.getType())
			throw new Exception("음료가 아닙니다");	
		
		System.out.println("새로운 MyMenu"+newMyMenu.getName()+"를 등록했습니다");
		SetMyMenu(newMyMenu);
	
	}

	@Override
	public void adminMenuAdd()  throws Exception{}
	@Override
	public void adminMenuModify()  throws Exception{}
	@Override
	public void adminMenuDelete() throws Exception {}
	@Override
	public void adminUserSearch()  throws Exception{}
	@Override
	public void adminMenuCount()  throws Exception{}

	public void addOption(Coffee coffee) throws Exception{
		boolean bSizeup 		= false; 	// 사이즈업
		boolean bAddShot	 	= false; 	// 샷추가
		boolean bWhippedCream	= false;	// 휘핑추가
		boolean bSyrup			= false;	// 시럽

		System.out.print("[옵션추가] 사이즈 업?(0:No 1:Yes)");
		bSizeup = (1 == ScannerManager.ReadInt() ? true:false);

		System.out.print("[옵션추가] 샷추가?(0:No 1:Yes)");
		bAddShot = (1 == ScannerManager.ReadInt() ? true:false);

		System.out.print("[옵션추가] 휘핑추가?(0:No 1:Yes)");
		bWhippedCream = (1 == ScannerManager.ReadInt() ? true:false);
		
		System.out.print("[옵션추가] 시럽추가?(0:No 1:Yes)");
		bSyrup = (1 == ScannerManager.ReadInt() ? true:false);
		
		coffee.setOption(bSizeup, bAddShot, bWhippedCream, bSyrup);
	}
	
	public boolean isPassEquals(final String pass) {
		if(true == pass.equals(userInfo.getPass()))
			return true;

		return false;
	}
	
	public void printInfo(){
		System.out.print("id:"+userInfo.getId());
		
		if(null != myMenu)
			System.out.println(" myMenu:"+myMenu.getName()+"("+myMenu.getPrice()+")");
		else
			System.out.println(" myMenu가 없습니다");
	}
	
	public boolean checkInputMenu(final int index, final int menuType) throws Exception{
		Menu menu = InfoManager.getInst().searchMenu(index);
		if(menuType != menu.getType())
			return false;
		
		return true;
	}
	
	public String getId(){
		return userInfo.getId();
	}
	
	public String getPass(){
		return userInfo.getPass();
	}	
	
	public void join() throws Exception{}
}

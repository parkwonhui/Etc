package User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		System.out.println("EDIE Coffee에 방문하신 손님을 환영합니다");		
		System.out.println("1.음료 2.시즌메뉴 3.디저트 4.계산 5.로그인 6.회원가입");
		System.out.println("====================================");
		int value = ScannerManager.ReadInt();
		switch(value){
			case 1 : return User.INPUT_TYPE.MENU_COFFEE;
			case 2 : return User.INPUT_TYPE.MENU_SEASON;
			case 3 : return User.INPUT_TYPE.MENU_DESSERT;
			case 4 : return User.INPUT_TYPE.MENU_PAY;
			case 5 : return User.INPUT_TYPE.LOGIN;		
			case 6 : return User.INPUT_TYPE.JOIN;
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
		int choiceCategory = inputMenu(menutype);
		if(-1 == choiceCategory){
			return null;
		}

		Menu findMenu = InfoManager.getInst().searchMenu(choiceCategory);
		if(null == findMenu)
			return null;
		
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
	public void viewMenu() throws Exception{}

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
	
	public void addOption(Coffee coffee) throws Exception{
		boolean bSizeup 		= false; 	// 사이즈업
		boolean bAddShot	 	= false; 	// 샷추가
		boolean bWhippedCream	= false;	// 휘핑추가
		boolean bSyrup			= false;	// 시럽

		System.out.println("[옵션추가] 사이즈 업?(0:No 1:Yes)");
		bSizeup = (1 == ScannerManager.ReadInt() ? true:false);
		System.out.println("[옵션추가] 샷추가?(0:No 1:Yes)");
		bAddShot = (1 == ScannerManager.ReadInt() ? true:false);
		System.out.println("[옵션추가] 휘핑추가?(0:No 1:Yes)");
		bWhippedCream = (1 == ScannerManager.ReadInt() ? true:false);
		System.out.println("[옵션추가] 시럽추가?(0:No 1:Yes)");
		bSyrup = (1 == ScannerManager.ReadInt() ? true:false);
		
		coffee.setOption(bSizeup, bAddShot, bWhippedCream, bSyrup);
	}

	// guest는 비밀번호와 상관없다
	public boolean isPassEquals(String pass) {
		return false;
	}
	
	public boolean checkInputMenu(final int index, final int menuType) throws Exception{
		Menu menu = InfoManager.getInst().searchMenu(index);
		if(null == menu)
			return false;
		
		if(menuType != menu.getType())
			return false;
		
		return true;
	}

	public void join() throws Exception{
		Pattern idPattern = Pattern.compile("^[a-zA-Z]{1}[\\w]{4,9}$");
		Pattern passPattern = Pattern.compile("^[\\w]{4,10}$");
		
		System.out.println("회원가입 id, pass를 입력해주세요.\n - id,pass는 영어대소문자숫자 4~10자리 입니다\n - 첫글자는 영어대소문자로 시작합니다");
		System.out.print("[회원가입] id 입력:");
		String id = ScannerManager.ReadString();
		System.out.print("[회원가입] pass 입력:");
		String pass = ScannerManager.ReadString();

		Matcher idMatcher = idPattern.matcher(id);
		Matcher passMatcher = passPattern.matcher(pass);
		if(false == idMatcher.find())
			throw new Exception("[system]id를 잘못 입력하였습니다\n 메인화면으로 돌아갑니다");
			
		if(false == passMatcher.find())
			throw new Exception("[system]pass를 잘못 입력하였습니다\n 메인화면으로 돌아갑니다");		
		
		if(false == InfoManager.getInst().addUserInfo(id, pass, User.TYPE_LOGIN))
			throw new Exception("[system] 이미 같은 아이디가 있습니다 메인화면으로 돌아갑니다");
		
		// 메뉴추가 시 파일 저장
		InfoManager.getInst().saveUser();

		System.out.println(" 축하합니다 회원가입 되었습니다");
	}
}

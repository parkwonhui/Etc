package CafeManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Info.InfoManager;
import Menu.Coffee;
import Menu.Menu;
import Menu.NonCoffee;
import User.User;
import User.User.INPUT_TYPE;
import User.User.LOGIN_STATE;
import Menu.Dessert;
import Save.SaveManager;
import ScannerManager.ScannerManager;

public class Manager {
	
	// 장바구니
	private SaveManager saveManager;
	private int	totalMoney;		// 총 판매 금액
	
	public Manager() {
		saveManager = new SaveManager();
		totalMoney = 0;
	}
	
	// 메뉴입력을 받음, 로그인 성공 시 로그인 클래스에서 유저를 생성하고 넘겨줌
	public LOGIN_STATE start(final User user) {		
	
		while (true) {
			try {
				INPUT_TYPE inputType = user.mainMenu(); // 메인메뉴를 보여줌과 동시에 사용자
														// 입력을 받는 함수
				if (true == isLogout(user, inputType)) // 로그아웃일 때 빼져나온다
					return LOGIN_STATE.LOGOUT;

				if(true == exec(user, inputType))		 // 로그인 일 때만 true 그 외 false
					return LOGIN_STATE.LOGIN;
				
			} catch (Exception e) {
				System.out.println("잘못된 값을 입력하였습니다");
			}
		}
		
		//return err..
		//return User.LOGIN_STATE.LOGOUT;
	}
	
	public boolean isLogout(final User user, final INPUT_TYPE inputype) throws Exception {
		if(INPUT_TYPE.LOGOUT != inputype)
			return false;
		
		return user.logout();
	}
	
	// 여기선 전부 false를 리턴해야한다. return값이 true면 while문을 빠져나가기 때문이다
	public boolean exec(final User user, final INPUT_TYPE inputType) throws Exception  {
		try{
		switch(inputType) {
		case LOGIN :
			return user.login();					// info 정보 참조해서 로그인한다
		case MENU_COFFEE :
		case MENU_SEASON :
		case MENU_DESSERT :

			int menutype = Menu.MENUTYPE_COFFEE;
			if(User.INPUT_TYPE.MENU_SEASON ==inputType) menutype = Menu.MENUTYPE_SEASON;
			if(User.INPUT_TYPE.MENU_DESSERT == inputType) menutype = Menu.MENUTYPE_DESSERT;
			
			Menu menu =user.menuChoice(menutype);	// 커피메뉴(논커피도 있지만 일단 네임을 이렇게 만든다)
			if(null == menu){
				throw new Exception("메뉴 번호를 잘못 입력하였습니다");
			}
			
			// 메뉴 갯수 체크
			if(0 >= menu.getStockNum()){
				System.out.println("[System] 재고가 남지 않았습니다");
				return false;				
			}
			
			// 장바구니 최대 개수 체크
			if(true == saveManager.isFull()){
				System.out.println("[System] 최대 담을 수 있는 개수"+saveManager.MAX_SAVE+"를 넘었습니다");
				return false;
			}
			// 구매성공이므로

			// 금액을 올려주고
			addTotalMoney(menu.getPrice());
			
			Menu findMenu = InfoManager.getInst().searchMenu(menu.getIndex());
			if(null == findMenu) {
				return false;
			}
			
			// 수량을 뺀다
			findMenu.minusOneStockNum();

			// 장바구니에 넣는다
			saveManager.saveMenu(menu);
			System.out.println("[Success]구매완료");
			saveManager.allPrint();
			break;
		case MENU_PAY:
			saveManager.calAllMenu();   // 전체 계산
			saveManager.deleteAllMenu();// 출력 후 초기화

			break;
		case MY_MENU :
			user.myMenuPrint();				// 선택하고 장바구니까지 가지
			break;
		case MY_MENU_MODIFY :	
			user.myMenuModify();			// 메뉴 수정 입력 완료 후 다시 카테고리로 돌아가기
			break;
		case ADMIN_MENU_ADD :
			user.adminMenuAdd();			// 관리자의 카페 메뉴 추가
			break;
		case ADMIN_MENU_MODIFY :
			user.adminMenuModify();
			break;
		case ADMIN_MENU_DELETE :
			user.adminMenuDelete();
			break;
		case ADMIN_MENU_SEARCH :
			user.adminUserSearch();
			break;
		case ADMIN_MENU_COUNT :
			user.adminMenuCount();
			break;
		case MENU_VIEW:
			user.viewMenu();
			System.out.println("총 매출:"+getTotalMoney());
			break;
		case JOIN:
			user.join();
			break;
		default:
			break;
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			//System.out.println("잘못된 값을 입력하였습니다");
		}
		
		return false;
	}
	
	private void addTotalMoney(int money){
		totalMoney+=money;
	}
	
	private int getTotalMoney(){
		return totalMoney;
	}
	
	
}

package CafeManagement;

import java.util.ArrayList;
import java.util.List;

import Menu.Coffee;
import Menu.Menu;
import Menu.NonCoffee;
import User.User;
import User.User.INPUT_TYPE;
import Menu.Dessert;

public class Manager {
	
	// info가 있다고 치고.. 임시 메뉴를 만든다
	List<Menu> menuList = new ArrayList<Menu>();
	
	User user;
	
	public Manager() {
		menuList.add(new Coffee(0, "아메리카노", 10, 1000, false));
		menuList.add(new Coffee(1, "카페라떼", 10, 1500, false));
		menuList.add(new Coffee(2, "카라멜마끼아또", 10, 3000, false));
		menuList.add(new Coffee(3, "바닐라프라페자바칩", 10, 4500, true));

		menuList.add(new Coffee(4, "홍차", 10, 3000, false));
		menuList.add(new Coffee(5, "오렌지쥬스", 10, 3000, true));
		
		menuList.add(new Coffee(6, "케이크", 10, 5000, false));
		menuList.add(new Coffee(7, "샌드위치", 10, 3000, false));
		menuList.add(new Coffee(6, "마카롱", 10, 2000, false));
	}
	
	// 메뉴입력을 받음, 로그인 성공 시 로그인 클래스에서 유저를 생성하고 넘겨줌
	public void start(final User user) {		
		
		while(true) {
			
			INPUT_TYPE inputType = user.mainMenu();			// 메인메뉴를 보여줌과 동시에 사용자 입력을 받는 함수 
			
			if(true == isLogout(user, inputType))			// 로그인일 때 빼져나온다
				break;
			
			exec(user, inputType);  								// 로그인 외에 메뉴실행
			
			
		}
	}
	
	public boolean isLogout(final User user, final INPUT_TYPE inputype) {
		if(INPUT_TYPE.LOGOUT != inputype)
			return false;
		
		return user.logout();
	}
	
	public boolean exec(final User user, final INPUT_TYPE inputType) {
		// 메뉴가 너무 많아..
		switch(inputType) {
		case LOGOUT :
			return user.login();			// info 정보 참조해서 로그인한다
		case MY_MENU :
			user.myMenuPrint();				// 그냥 출력하고 다시 메뉴로 돌아오기
			break;
		case MY_MENU_MODIFY :	
			user.myMenuModify();			// 메뉴 수정 입력 완료 후 다시 카테고리로 돌아가기
			break;
		case ADMIN_MENU_ADD :
			user.adminMenuAdd();			// 관리자의 카페 메뉴 추가
			break;
		case ADMIN_MENU_MODIFY :
			break;
		case ADMIN_MENU_DELETE :
			break;
		case ADMIN_MENU_SEARCH :
			break;
		case ADMIN_MENU_COUNT :
			break;
		}

		
		return false;
	}
}

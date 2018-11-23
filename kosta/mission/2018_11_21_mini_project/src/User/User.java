package User;

import Menu.Menu;

public abstract  class User {
	
	public enum INPUT_TYPE{
		
		MAIN_CATEGORY, 		// 메뉴고르기, 매장관리
		LOGIN,				// Login
		LOGOUT,				// Logout
		MENU_COFFEE,		// 커피
		MENU_SEASON,		// 시즌
		MENU_DESSERT,		// 디저트
		MENU_CHOICE,		// 안씀
		MENU_VIEW,			// 관리자만 보는 것(전체 메뉴보기)
		MENU_PAY,			// 메뉴 선택완료. 계산
		MY_MENU,			// 마이메뉴
		MY_MENU_MODIFY,		// 마이메뉴 수정
		ADMIN_MENU_ADD,		// 메뉴 추가
		ADMIN_MENU_MODIFY,	// 메뉴 수정
		ADMIN_MENU_DELETE,	// 메뉴삭제
		ADMIN_MENU_SEARCH,	// 회원검색
		ADMIN_MENU_COUNT,	// 재고보기
		JOIN,				// 회원가입
	};
	
	public enum LOGIN_STATE{
		LOGIN,				// LoginLogout 함수에서 쓰이는 로그인, 로그아웃 결과
		LOGOUT,
	};

	public static final int TYPE_LOGIN = 0;
	public static final int TYPE_ADMIN = 1;

	
	public abstract INPUT_TYPE mainMenu() throws Exception;
	public abstract boolean login() throws Exception;
	public abstract boolean logout() throws Exception;					// 결과값으로 로그아웃되면 manager roop를 벗어난다
	public abstract Menu menuChoice(int menutype) throws Exception;		// 메뉴 선택(3개 메뉴 한방에 출력하자)	 
	public abstract void viewMenu() throws Exception;					// 메뉴 전체 출력	
	public abstract void myMenuPrint() throws Exception;
	public abstract void myMenuModify() throws Exception;
	public abstract void adminMenuAdd() throws Exception;
	public abstract void adminMenuModify() throws Exception;
	public abstract void adminMenuDelete() throws Exception;
	public abstract void adminUserSearch() throws Exception;
	public abstract void adminMenuCount() throws Exception;
	public abstract void join() throws Exception;						// 회원가입
	
	
	public abstract boolean isPassEquals(String pass);
}

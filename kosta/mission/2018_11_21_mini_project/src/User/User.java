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
		MENU_CHOICE,		// 메뉴 고르기 이거 없애고..각자 메뉴를 주자(현재 쓰지 않음 이따 지우자)
		MENU_PAY,			// 메뉴 선택완료. 계산
		MY_MENU,			// 마이메뉴
		MY_MENU_MODIFY,		// 마이메뉴 수정
		ADMIN_MENU_ADD,		// 메뉴 추가
		ADMIN_MENU_MODIFY,	// 메뉴 수정
		ADMIN_MENU_DELETE,	// 메뉴삭제
		ADMIN_MENU_SEARCH,	// 회원검색
		ADMIN_MENU_COUNT,	// 재고보기
	};
	
	public enum LOGIN_STATE{
		LOGIN,				// LoginLogout 함수에서 쓰이는 로그인, 로그아웃 결과
		LOGOUT,
	};
	
	public abstract INPUT_TYPE mainMenu();
	public abstract boolean login();
	public abstract boolean logout();					// 결과값으로 로그아웃되면 manager roop를 벗어난다
	public abstract Menu menuChoice(int menutype);		// 메뉴 선택(3개 메뉴 한방에 출력하자)	 
	public abstract void myMenuPrint();
	public abstract void myMenuModify();
	public abstract void adminMenuAdd();
	public abstract void adminMenuModify();
	public abstract void adminMenuDelete();
	public abstract void adminMenuSearch();
	public abstract void adminMenuCount();
	
		
		// guest-메뉴고르기 ( 0 )
		// guest-결제
		// guest-로그인( x )
		// user-메뉴고르기 ( 0 )
		// user-결제화면
		// user-마이메뉴
		// user-마이메뉴등록
		// user-로그아웃 ( x )
		// admin-매장관리화면 ( 0 )
		// admin-메뉴추가
		// admin-메뉴수정
		// admin-메뉴삭제
		// admin-회원검색
		// admin-재고보기
		// admin-Logout( x )
		
		// 분류
		// 메뉴고르기
		// Logint/Logout
		// 마이메뉴
		// 마이메뉴 수정
		// 메뉴 추가
		// 메뉴 수정
		// 메뉴삭제
		// 회원검색
		// 재고보기
		// 메뉴에 따라서 그냥 처리하는걸로 하자 오 함수에서 어케어케되는데?/왜냐면 static이니까...글구 user랑 guest는 결과만 주면될듯 리턴값으로?
		
		
			// 메뉴고르기
		// Logint/Logout
		// 마이메뉴
		// 마이메뉴 수정
		// 메뉴 추가
		// 메뉴 수정
		// 메뉴삭제
		// 회원검색
		// 재고보기



}

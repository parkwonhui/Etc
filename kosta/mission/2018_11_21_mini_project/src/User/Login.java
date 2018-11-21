package User;

public class Login extends User {
	
	// 마이메뉴
	private Menu myMenu;		// set,get 함수 추가할 것!!
	
	
	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[메장관리] 1.시즌메뉴 2.음료 3.디저트 4.마이메뉴 5.마이메뉴 수정 6.로그아웃");

		return null;
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
	public void myMenuPrint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void myMenuModify() {
		// TODO Auto-generated method stub
		
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

package User;

public class Guest extends User{

	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[매장관리] 1.시즌메뉴 2.음료 3.디저트 4.로그인");

		return null;
	}

	@Override
	public LOGIN_STATE loginLogout() {
		// TODO Auto-generated method stub
		return null;
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

package User;

public class Admin extends User{

	@Override
	public INPUT_TYPE mainMenu() {
		System.out.println("[메장관리] 1.메뉴추가 2.메뉴수정 3.메뉴삭제 4.회원검색 5.재고관리 6.로그아웃");
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

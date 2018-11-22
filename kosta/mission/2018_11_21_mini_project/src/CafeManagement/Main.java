package CafeManagement;

import User.Guest;
import User.User;

public class Main {

	public static void main(String[] args) {

		Manager manager = new Manager();
		User user = new Guest();
		manager.start(user);
	}

}

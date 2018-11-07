package kosta.bank;

public class Customer {
	private String ID;
	private String name;
	private Account account;
	
	public Customer(String ID, String name, long balance) {
		super();
		this.ID = ID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getId() {
		return ID;
	}
}

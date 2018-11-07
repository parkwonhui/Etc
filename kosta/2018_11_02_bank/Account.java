package kosta.bank;

public class Account {

	private String ID;
	private long balance;
	
	public Account(String ID, long balance)
	{
		this.ID = ID;
		this.balance = balance;
	}
	
	public void deposit(long amt)
	{		
		balance+= amt;
	}
	
	public boolean withdraw(long amt)
	{
		if(balance < amt)
		{
			return false;
		}
		
		balance -= amt;
		return true;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public long getBalance()
	{
		return balance;
	}
	
}

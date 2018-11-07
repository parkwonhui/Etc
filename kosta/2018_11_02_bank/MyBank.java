package kosta.bank;

public class MyBank {

	private Customer[] customer;
	private int customersNum;
	
	public MyBank()
	{
		customer = new Customer[3];
		customersNum = 0;
	}
	
	public void addCustomer(String ID, String name, long balance)
	{
		if(customersNum >= 3)
		{
			System.out.println("고객정보가 많아 생성할 수 없습니다");
			return;
		}
		
		Account account = new Account(ID, balance);
		customer[customersNum] = new Customer(ID, name, balance);	
		customer[customersNum].setAccount(account);
		++customersNum;
	}
	
	public int getCustomerNum()
	{
		return customersNum;
	}
	
	public Customer getCustomer(String ID)
	{
		for(int i = 0; i < customersNum; ++i)
		{
			if(customer[i].getName().equals(ID))
			{
				return customer[i];
			}
		}
		
		return null;
	}
	
	public Customer[] getAllCustomers()
	{		
		Customer[] customerTemp = new Customer[customersNum];
		System.arraycopy(customer, 0, customerTemp, 0, customersNum);	
		return customerTemp;
	}
	
	
}

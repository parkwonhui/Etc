package kosta.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BankSystem {
	private MyBank myBank;		//MyBank 
	
	public BankSystem(){	
		myBank = new MyBank();
		showMenu();
	}
	
	public void showMenu(){		//show method �����
		String  menu = null;			
		String id = null;
		String name = null;
		long balance = 0;
		
		do{
		
			System.out.println("****�޴��� �Է��ϼ���****");
			System.out.println("1. �����");
			System.out.println("2. ������(1��)");
			System.out.println("3. ����ü����");
			System.out.println("4. ���������");
			System.out.println("5. �������Ա�");
			System.out.println("***������***");
			System.out.println("***************");
			System.out.print(">>");			
			
			menu = readFromKeyboard();			
		
			if(menu.equals("1")){				//�����
				
				System.out.print("ID�� �Է��ϼ���: ");
				id = readFromKeyboard();
				
				System.out.print("�̸��� �Է��ϼ���: ");
				name = readFromKeyboard();
				
				System.out.print("�ܰ� �Է��ϼ���: ");
				balance = Long.parseLong(readFromKeyboard());
				
				myBank.addCustomer(id, name, balance);
				
			}
			else if(menu.equals("2"))
			{
				System.out.println("ã�� �� ������ �Է����ּ���(1��)");
				id = readFromKeyboard();
				Customer customer = myBank.getCustomer(id);
				if(null == customer)
				{
					System.out.println("ID�� ã�� �� �����ϴ�");
				}
				else
				{
					System.out.println("id:"+customer.getId()+" name:"+customer.getName()+" balance:"+customer.getAccount().getBalance());
				}
			}
			else if(menu.equals("3"))
			{
				System.out.println("��ü �� ���� ���");
				Customer[] arrCustomer = myBank.getAllCustomers();
				for(Customer customer:arrCustomer)
				{
					System.out.println("id:"+customer.getId()+" name:"+customer.getName()+" balance:"+customer.getAccount().getBalance());
				}
			}
			else if(menu.equals("4"))
			{
				System.out.println("���� ID�� �Է��ϼ���:");
				String customerID = readFromKeyboard();
				Customer customer = myBank.getCustomer(customerID);
				if(null == customer)
				{
					System.out.println("ID�� ã�� �� �����ϴ�");
				}
				else
				{
					System.out.println("����Ͻ� �ݾ��� �Է��ϼ���:");
					long money = Long.parseLong(readFromKeyboard());
					if(false == customer.getAccount().withdraw(money))
					{
						System.out.println("�ܾ��� �����մϴ�");
					}
				}
			}
			else if(menu.equals("5"))
			{				
				System.out.println("���� ID�� �Է��ϼ���:");
				String customerID = readFromKeyboard();
				Customer customer = myBank.getCustomer(customerID);
	
				if(null == customer)
				{
					System.out.println("ID�� ã�� �� �����ϴ�");
				}
				else
				{
					System.out.println("�Ա��Ͻ� �ݾ��� �Է��ϼ���:");
					long money = Long.parseLong(readFromKeyboard());
					customer.getAccount().deposit(money);
				}
			}
			
			
		}while(!menu.equals("q"));
	}
	


	public String readFromKeyboard(){
		String input = null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return input;
	}
	
	public static void main(String[] args) {
		BankSystem bank = new BankSystem();

	}

}











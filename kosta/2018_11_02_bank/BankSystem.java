package kosta.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BankSystem {
	private MyBank myBank;		//MyBank 
	
	public BankSystem(){	
		myBank = new MyBank();
		showMenu();
	}
	
	public void showMenu(){		//show method 만들기
		String  menu = null;			
		String id = null;
		String name = null;
		long balance = 0;
		
		do{
		
			System.out.println("****메뉴를 입력하세요****");
			System.out.println("1. 고객등록");
			System.out.println("2. 고객보기(1명)");
			System.out.println("3. 고객전체보기");
			System.out.println("4. 고객예금출금");
			System.out.println("5. 고객예금입금");
			System.out.println("***끝내기***");
			System.out.println("***************");
			System.out.print(">>");			
			
			menu = readFromKeyboard();			
		
			if(menu.equals("1")){				//고객등록
				
				System.out.print("ID를 입력하세요: ");
				id = readFromKeyboard();
				
				System.out.print("이름을 입력하세요: ");
				name = readFromKeyboard();
				
				System.out.print("잔고를 입력하세요: ");
				balance = Long.parseLong(readFromKeyboard());
				
				myBank.addCustomer(id, name, balance);
				
			}
			else if(menu.equals("2"))
			{
				System.out.println("찾을 고객 정보를 입력해주세요(1명)");
				id = readFromKeyboard();
				Customer customer = myBank.getCustomer(id);
				if(null == customer)
				{
					System.out.println("ID를 찾을 수 없습니다");
				}
				else
				{
					System.out.println("id:"+customer.getId()+" name:"+customer.getName()+" balance:"+customer.getAccount().getBalance());
				}
			}
			else if(menu.equals("3"))
			{
				System.out.println("전체 고객 정보 출력");
				Customer[] arrCustomer = myBank.getAllCustomers();
				for(Customer customer:arrCustomer)
				{
					System.out.println("id:"+customer.getId()+" name:"+customer.getName()+" balance:"+customer.getAccount().getBalance());
				}
			}
			else if(menu.equals("4"))
			{
				System.out.println("고객의 ID를 입력하세요:");
				String customerID = readFromKeyboard();
				Customer customer = myBank.getCustomer(customerID);
				if(null == customer)
				{
					System.out.println("ID를 찾을 수 없습니다");
				}
				else
				{
					System.out.println("출금하실 금액을 입력하세요:");
					long money = Long.parseLong(readFromKeyboard());
					if(false == customer.getAccount().withdraw(money))
					{
						System.out.println("잔액이 모질합니다");
					}
				}
			}
			else if(menu.equals("5"))
			{				
				System.out.println("고객의 ID를 입력하세요:");
				String customerID = readFromKeyboard();
				Customer customer = myBank.getCustomer(customerID);
	
				if(null == customer)
				{
					System.out.println("ID를 찾을 수 없습니다");
				}
				else
				{
					System.out.println("입금하실 금액을 입력하세요:");
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











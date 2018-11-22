package Save;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.sql.rowset.serial.SerialStruct;

import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import Menu.NonCoffee;

public class SaveManager {
	// 장바구니
	public final static int MAX_SAVE = 5;
	private ArrayList<Menu> saveList;
	
	public SaveManager(){
		saveList = new ArrayList<Menu>();
	}
	
	public boolean saveMenu(Menu menu){
		if(true == isFull())
			return false;
		
		saveList.add(menu);
		return true;
	}
	
	public int calSetMenu(){		// 세트 체크 개수를 리턴한다
		
		int drinkCount = 0;
		int dessertCount = 0;
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			if(menu instanceof Coffee) 			++drinkCount;
			else if(menu instanceof NonCoffee) 	++drinkCount;
			else if(menu instanceof Dessert) 	++dessertCount;
		}
		
		if(drinkCount >= dessertCount)
			return dessertCount;
		
		return drinkCount;
	}

	public void allPrint(){
		System.out.println("******* 구매리스트 ********");		
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			System.out.println(i+":"+" "+menu.getName()+" 가격:"+menu.getPrice());
		}
	}
	
	public void calAllMenu(){
		System.out.println("******* 담은 메뉴 ********");
		int total = getCoffeeMoney()+getSeasonMoney()+getDessertMoney();
		System.out.println("**** 총가격 :"+total+"**** ");		
	}
	
	private int getCoffeeMoney(){
		int allPrice = 0;
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			if(false == (menu instanceof Coffee))
				continue;
			
			allPrice += menu.getPrice();
			int optionValue = addOptionMoney((Coffee)menu);
			allPrice += optionValue;
			
			System.out.println("["+(i+1)+"]이름:"+menu.getName()+" 옵션추가:"+optionValue+": 가격:"+menu.getPrice());
		}
		return allPrice;
	}
	
	private int getSeasonMoney(){
		int allPrice = 0;
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			if(false == (menu instanceof NonCoffee))
				continue;
			
			allPrice += menu.getPrice();

			System.out.println("["+(i+1)+"]이름:"+menu.getName()+": 가격:"+menu.getPrice());
		}
		return allPrice;
	}
	
	private int getDessertMoney(){
		int allPrice = 0;
		int setMenuCount = calSetMenu();
		int setCount = 0;
		int resultMoney = 0;			// 결과금액
		int setDiscount = 0;			// 할인금액
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			if(false == (menu instanceof Dessert))
				continue;
			
			int price = menu.getPrice();
			if(setCount < setMenuCount){				// 할인금액 적용
				Dessert dessert = (Dessert)menu;
				resultMoney = (int)(price*dessert.getDiscount()*0.01);
				System.out.println("price:"+price+"resultMoney:"+resultMoney);
				setDiscount = (int) (price - resultMoney);
				price += resultMoney;
				++setCount;
			}
			
			allPrice += price;

			System.out.println("["+(i+1)+"]이름:"+menu.getName()+": 가격:"+menu.getPrice()+" 세트할인금액:"+setDiscount);
		}
		return allPrice;
	}

	private int addOptionMoney(Coffee coffee){
		int optionMoney = 0;
		final int option = 200;		// option은 200원씩
		if(true == coffee.isbAddShot())		optionMoney+=option;
		if(true == coffee.isbSizeup())		optionMoney+=option;
		if(true == coffee.isbSyrup())		optionMoney+=option;
		if(true == coffee.isbWhippedCream())optionMoney+=option;
		
		return optionMoney;
	}
	
	public int getSize(){
		return saveList.size();
	}
	
	public boolean isFull(){
		if(saveList.size() >= MAX_SAVE)
			return true;
		
		return false;
	}
	
	public void deleteAllMenu(){
		saveList.clear();
	}
}

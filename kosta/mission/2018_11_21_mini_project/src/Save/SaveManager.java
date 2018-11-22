package Save;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import Menu.Menu;

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
	
	public void allSavePrint(){
		System.out.println("******* 담은 메뉴 ********");
		int allPrice = 0;
		for(int i =0; i < saveList.size(); ++i){
			Menu menu = saveList.get(i);
			allPrice += menu.getPrice();
			System.out.println("["+(i+1)+"]이름:"+menu.getName()+": 가격:"+menu.getPrice());
		}
		System.out.println("**** 총가격 :"+allPrice+"**** ");		
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
		int count = saveList.size();
		for(int i = 0; i < count; ++i)
			saveList.remove(i);
		
	}
	
}

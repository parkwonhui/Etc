package Info;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;

public class InfoManager {

	HashMap<String, UserInfo> userinfo = new HashMap<>();
	HashMap<Integer, Menu> menuinfo = new HashMap<>();

	private static InfoManager instance;

	private InfoManager() {}

	public static InfoManager getInst() {
		if (instance == null) {
			instance = new InfoManager();
		}
		return instance;
	}

	public void addUserInfo(String id, String pass) {
		UserInfo user = new UserInfo(id, pass);
		userinfo.put(id, user);
	}

	public void addMenu(int index, String name, int price, int discount, int type) {
		Menu menu = null;
		if(Menu.MENUTYPE_COFFEE  == type)
			menu = new Coffee(index, name, 10, price, type);			// 개수는 생성 때 임의로 10으로 설정
		else
			menu = new Dessert(index, name, 10, price, type, discount);
		
		if(null != menu)
			menuinfo.put(index, menu);
	}

	public UserInfo searchUser(String id) {
		if (false == userinfo.containsKey(id))
			return null;

		return userinfo.get(id);
	}

	public Menu searchMenu(int id) {
		if (false == menuinfo.containsKey(id))
			return null;

		return menuinfo.get(id);
	}
	
	public boolean deleteMenu(int id) {
		if(false == menuinfo.containsKey(id)) {
			System.out.println("메뉴를 찾을 수 없습니다");
			return false;
		}
		
		menuinfo.remove(id);
		return true;
	}
	
	public int getUserDataSize(){
		return userinfo.size();
	}
	
	public int getMenuDataSize(){
		return menuinfo.size();
	}

	// 재고출력
	public void PrintStock() {
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			System.out.println(menu.getIndex() + ": " + menu.getName() + " 재고:" + menu.getStockNum());	
		}
	}

	/*public void printCoffee(){
		int size = menuinfo.size();
		for (int i = 0; i < size; ++i) {
			Menu menu = menuinfo.get(i);
			if(menu instanceof Coffee)
				continue;

			System.out.println(i + ": " + menu.getName() + " 재고:" + menu.getStockNum());
		}
	}

	public void printDessert(){
		int size = menuinfo.size();
		for (int i = 0; i < size; ++i) {
			Menu menu = menuinfo.get(i);
			if(menu instanceof Dessert)
				continue;
			
			System.out.println(i + ": " + menu.getName() + " 재고:" + menu.getStockNum());
		}
	}*/
	
	public void allPrint(){
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			System.out.println(menu.getIndex() + ": " + menu.getName() + " 재고:" + menu.getStockNum());		
		}
	}

	public void readUser(String pass) {
		BufferedReader br = null;

		try {
			br = Files.newBufferedReader(Paths.get(pass));
			Charset.forName("UTF-8");
			String line = "";
			
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				tmpList = Arrays.asList(array);
				addUserInfo(array[1], array[2]);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			} // catch

		} // finally

	}// function end

	public void readMenu(String pass) {
		List<List<String>> ret = new ArrayList<List<String>>();
		BufferedReader br = null;

		try {
			br = Files.newBufferedReader(Paths.get(pass));
			Charset.forName("UTF-8");
			String line = "";

			line = br.readLine();	// 첫째줄 읽는다
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				tmpList = Arrays.asList(array);
				int index = Integer.parseInt(array[0]);
				int price = Integer.parseInt(array[2]);
				int discount = Integer.parseInt(array[3]);
				int type = Integer.parseInt(array[4]);
				addMenu(index, array[1], price, discount, type);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}

		}

	}
}

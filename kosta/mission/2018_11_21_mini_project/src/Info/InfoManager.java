package Info;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

import Menu.Coffee;
import Menu.Dessert;
import Menu.Menu;
import User.Admin;
import User.Login;
import User.User;

public class InfoManager {

	HashMap<String, User> userinfo = new HashMap<>();
	HashMap<Integer, Menu> menuinfo = new HashMap<>();

	private static InfoManager instance;

	private InfoManager() {}

	public static InfoManager getInst() {
		if (instance == null) {
			instance = new InfoManager();
		}
		return instance;
	}

	public boolean addUserInfo(String id, String pass, int type) {
		if(null != searchUser(id)){
			return false;
		}
		
		UserInfo user = new UserInfo(id, pass);
		if(User.TYPE_ADMIN == type)
			userinfo.put(id, new Admin(user));
		else
			userinfo.put(id, new Login(user));
		
		return true;
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

	public User searchUser(String id) {
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
	
	public void allPrint(){
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			System.out.println(menu.getIndex() + ": " + menu.getName() + " 가격:" + menu.getPrice());		
		}
	}

	// 메뉴 출력
	public void allCoffeePrint(){
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			if(null == menu)
				continue;
			
			if(menu.MENUTYPE_COFFEE != menu.getType())
				continue;
			
			System.out.print(menu.getIndex() + ": " + menu.getName() + " 가격:" + menu.getPrice());	
			
			if(0 >= menu.getStockNum())
				System.out.println(" !수량없음 ");

			else
				System.out.println();
		}
	}
	
	public void allSeasonPrint(){
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			if(menu.MENUTYPE_SEASON != menu.getType())
				continue;
			
			System.out.print(menu.getIndex() + ": " + menu.getName() + " 가격:" + menu.getPrice());		
			if(0 >= menu.getStockNum())
				System.out.println(" !수량없음 ");

			else
				System.out.println();

		}
	}
	
	public void allDessertPrint(){
		Set set = menuinfo.entrySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>)iter.next();
			Menu menu = temp.getValue();
			if(menu.MENUTYPE_DESSERT != menu.getType())
				continue;
			
			System.out.print(menu.getIndex() + ": " + menu.getName() + " 가격:" + menu.getPrice());		
			
			if(0 >= menu.getStockNum())
				System.out.println(" !수량없음 ");

			else
				System.out.println();
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
				int type = Integer.parseInt(array[3]);
				addUserInfo(array[1], array[2], type);

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
	
	public void saveUser(){
		 saveUserInfotoCSV(System.getProperty("user.dir")+"\\userInfo.csv");
	}

	public void saveUserInfotoCSV(String path) {
		BufferedWriter bufWriter = null;
		try {
			bufWriter = Files.newBufferedWriter(Paths.get(path));
			bufWriter.write("Index,ID,PW,Type");
			bufWriter.newLine();
			int index = 0;
			Set set = userinfo.entrySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, User> temp = (Map.Entry<String, User>) iter.next();
				User user = temp.getValue();
				if (user instanceof Login) {
					
					Login login = (Login) user;
					String id = login.getId();
					String pass = login.getPass();
					int type = User.TYPE_LOGIN;
					bufWriter.write(index+","+id+","+pass+","+type);
					bufWriter.newLine();
					++index;
				} else if (user instanceof Admin) {
					
					Admin admin = (Admin) user;
					String id = admin.getId();
					String pass = admin.getPass();
					int type = User.TYPE_ADMIN;
					bufWriter.write(index+","+id+","+pass+","+type);
					bufWriter.newLine();
					++index;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bufWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveMenu(){
		 saveMenuInfotoCSV(System.getProperty("user.dir")+"\\menu.csv");
	}

	public void saveMenuInfotoCSV(String path) {
		BufferedWriter bufWriter = null;
		try {
			bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			bufWriter.write("Index,Name,Price,Discount,Type");
			bufWriter.newLine();
			Set set = menuinfo.entrySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				Map.Entry<Integer, Menu> temp = (Map.Entry<Integer, Menu>) iter.next();
				Menu menu = temp.getValue();
				if(null == menu)
					continue;
				
				int 	index 	= menu.getIndex();
				String 	name 	= menu.getName();
				int 	price 	= menu.getPrice();
				int 	type 	= menu.getType();
				int 	disccount = 100;
				if (menu instanceof Dessert) {
					Dessert dessert = (Dessert)menu;
					disccount = dessert.getDiscount();

				}
				bufWriter.write(index+","+name+","+price+","+disccount+","+type);
				bufWriter.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

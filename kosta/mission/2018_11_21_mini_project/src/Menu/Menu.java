package Menu;

public class Menu{

	public final static int MENUTYPE_COFFEE = 0;
	public final static int MENUTYPE_SEASON = 1;
	public final static int MENUTYPE_DESSERT = 2;

	private int index;				// 메뉴 인덱스
	private String name;			// 메뉴 이름
	private int stockNum;			// 재고개수
	private int price;				// 가격
	private int type;				// 카테고리
	
	public Menu() {}
	public Menu(int index, String name, int inventoryCount, int price, int type) {
		super();
		this.index = index;
		this.name = name;
		this.stockNum = inventoryCount;
		this.price = price;
		this.type = type;
	}
	
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}
	public int getStockNum() {
		return stockNum;
	}
	public int getPrice() {
		return price;
	}
	public int getType() {
		return type;
	}
	
	public boolean minusOneStockNum(){
		if(0 >= stockNum)
			return false;
		
		--stockNum;
		return true;
	}
}

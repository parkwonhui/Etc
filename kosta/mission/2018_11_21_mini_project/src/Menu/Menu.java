package Menu;

public class Menu{
	private int index;				// 메뉴 인덱스
	private String name;			// 메뉴 이름
	private int inventoryCount;		// 재고개수
	private int price;				// 가격
	private boolean season;			// 시즌메뉴 여부
	
	public Menu() {}
	public Menu(int index, String name, int inventoryCount, int price, boolean season) {
		super();
		this.index = index;
		this.name = name;
		this.inventoryCount = inventoryCount;
		this.price = price;
		this.season = season;
	}
	
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}
	public int getInventoryCount() {
		return inventoryCount;
	}
	public int getPrice() {
		return price;
	}
	public boolean getSeason() {
		return season;
	}
}

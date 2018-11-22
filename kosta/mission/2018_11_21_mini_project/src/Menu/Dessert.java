package Menu;

public class Dessert extends Menu implements DiscountPercent{

	int discountPercent;				// 디저트는 할인율이 존재한다

	public Dessert(int index, String name, int inventoryCount, int price, int type, int discountPercent) {
		super(index, name, inventoryCount, price, type);
		this.discountPercent = discountPercent;
	}

	public int getDiscount() {	// 할인율
		return discountPercent;
	}
	
	public void setDiscount(int disscount) {	// 할인율
		if(0 > disscount)
			return;
		
		if(100 < disscount)
			return;
		
		discountPercent = disscount;		
	}
}

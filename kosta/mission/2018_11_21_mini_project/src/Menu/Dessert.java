package Menu;

public class Dessert extends Menu implements DiscountPercent{

	int discountPercent;				// 디저트는 할인율이 존재한다

	public Dessert(int index, String name, int inventoryCount, int price, int discountPercent, boolean season) {
		super(index, name, inventoryCount, price, season);
		this.discountPercent = discountPercent;
	}

	public int getDiscount() {	// 할인율
		return discountPercent;
	}

}

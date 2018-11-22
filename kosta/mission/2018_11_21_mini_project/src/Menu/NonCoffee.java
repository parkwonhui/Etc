package Menu;

public class NonCoffee extends Menu implements DiscountPercent {

	public NonCoffee() {}
	public NonCoffee(int index, String name, int inventoryCount, int price, int type) {
		super(index, name, inventoryCount, price, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getDiscount() {
		return 100;
	}

}

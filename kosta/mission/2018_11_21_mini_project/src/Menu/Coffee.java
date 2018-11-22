package Menu;

public class Coffee extends Menu implements DiscountPercent{

	boolean bSizeup; 		// 사이즈업
	boolean bAddShot; 		// 샷추가
	boolean bWhippedCream;	// 휘핑추가
	boolean bSyrup;			// 시럽

	public Coffee() {}

	public Coffee(int index, String name, int inventoryCount, int price, int type) {
		super(index, name, inventoryCount, price, type);
		this.bSizeup = false;
		this.bAddShot = false;
		this.bWhippedCream = false;
		this.bSyrup = false;
	}

	public int getDiscount() {	// 할인율
		return 100;
	}


	public boolean isbSizeup() {
		return bSizeup;
	}

	public boolean isbAddShot() {
		return bAddShot;
	}

	public boolean isbWhippedCream() {
		return bWhippedCream;
	}

	public boolean isbSyrup() {
		return bSyrup;
	}

	public void setOption(boolean bSizeup, boolean bAddShot, boolean bWhippedCream, boolean bSyrup){
		this.bWhippedCream = bWhippedCream;
		this.bSyrup = bSyrup;
		this.bAddShot = bAddShot;
		this.bSizeup = bSizeup;
	}
}

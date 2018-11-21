package Menu;

public class Coffee extends Menu implements DiscountPercent{

	boolean bSizeup; 		// 사이즈업
	boolean bAddShot; 		// 샷추가
	boolean bWhippedCream;	// 휘핑추가
	boolean bSyrup;			// 시럽

	public Coffee() {}

	public Coffee(int index, String name, int inventoryCount, int price, boolean season) {
		super(index, name, inventoryCount, price, season);
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


	public void setbSizeup(boolean bSizeup) {
		this.bSizeup = bSizeup;
	}


	public boolean isbAddShot() {
		return bAddShot;
	}


	public void setbAddShot(boolean bAddShot) {
		this.bAddShot = bAddShot;
	}


	public boolean isbWhippedCream() {
		return bWhippedCream;
	}


	public void setbWhippedCream(boolean bWhippedCream) {
		this.bWhippedCream = bWhippedCream;
	}


	public boolean isbSyrup() {
		return bSyrup;
	}


	public void setbSyrup(boolean bSyrup) {
		this.bSyrup = bSyrup;
	}
}

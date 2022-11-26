package diagram.q05_shopping;

public class Item {

	public String category;
	public String itemCode;
	public String itemName;
	public int itemPrice;

	
	public Item() {
		super();
	}
	public Item(String category, String itemCode, String itemName, int itemPrice) {
		super();
		this.category = category;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
	
}

package diagram.q05_shopping;

import java.util.List;

public class Cart {

	private List<Item> items;
	public Cart(List<Item> items) {
		this.items = items;
	}
	public Cart() {}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public void addItem(Item item) {
		this.items.add(item);
	}
	
}

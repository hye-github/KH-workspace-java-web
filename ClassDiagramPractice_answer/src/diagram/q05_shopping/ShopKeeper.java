package diagram.q05_shopping;

public class ShopKeeper {
	private Cart cart;

	public int makeBillPaper() {
		int sum = 0;
		for(Item i : cart.getItems()) {
			sum += i.getItemPrice();
		}
		return sum;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void calculate(Calculator calc) {
		calc.calculate();
	}
	
}

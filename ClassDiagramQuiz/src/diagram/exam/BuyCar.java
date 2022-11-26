package diagram.exam;

public class BuyCar {
	
	private Car carType;
	
	private Driver bestDriver;

	public BuyCar(Driver d) {
		this.bestDriver = d;
		this.carType = new Benz();
	}
}

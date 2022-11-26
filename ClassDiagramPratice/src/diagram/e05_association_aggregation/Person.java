package diagram.e05_association_aggregation;

public class Person {

	private Shoes shoes;
	private Watch watch;
	
	public Person() {
		super();
	}

	public Person(Shoes shoes, Watch watch) {
		super();
		this.shoes = shoes;
		this.watch = watch;
	}

	public Shoes getShoes() {
		return shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public Watch getWatch() {
		return watch;
	}

	public void setWatch(Watch watch) {
		this.watch = watch;
	}
	
	
	
}

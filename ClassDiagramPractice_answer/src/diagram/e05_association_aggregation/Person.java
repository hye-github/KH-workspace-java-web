package diagram.e05_association_aggregation;

import java.util.List;

public class Person {

	private Shoes shoes;
	private List<Watch> watch;
	
	public Person(Shoes shoes, Watch watch) {
		super();
		this.shoes = shoes;
	}
	
	

	public Person(Shoes shoes, List<Watch> watch) {
		this.shoes = shoes;
		this.watch = watch;
	}



	public Shoes getShoes() {
		return shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public List<Watch> getWatch() {
		return watch;
	}

	public void setWatch(List<Watch> watch) {
		this.watch = watch;
	}


	
	
	
	
	
	
	
}

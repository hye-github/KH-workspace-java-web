package diagram.e06_association_composition;

public class Person {
	private Eyes e = new Eyes();
	private Heart h = new Heart();
	
	public Person() {
		super();
	}
	public Person(Eyes e, Heart h) {
		this.e = e;
		this.h = h;
	}
	public Eyes getE() {
		return e;
	}
	public void setE(Eyes e) {
		this.e = e;
	}
	public Heart getH() {
		return h;
	}
	public void setH(Heart h) {
		this.h = h;
	}
	
	
	
}

package diagram.e02_generalization;

public class Member {

	private int id;
	private String name;
	private double point;
	
	// 게터 세터 알트 쉬프트 ㄴ

	
	public Member() {
		super();
	}

	public Member(int id, String name, double point) {
		super();
		this.id = id;
		this.name = name;
		this.point = point; 	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	
}

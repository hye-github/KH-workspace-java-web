package diagram.e01_basic;

abstract public class Notation {
	
	public int _public;
	private String _private;
	protected double _protected;
	static int _default;
	// Member Field
	
	public Notation() {}
	// Constructor
	
	abstract public void _publicF(int a);
	private void _privateF(double d) {}
	protected void _protectedF(int a, double b) {}
	void _defaultF() {}
	// Member Method
	
	
	
	
}


//- public : +
//- private : -
//- protected : #
//- default : ~
//- static : 밑줄
//- abstract(추상 메서드) : italic체
//
//Association : 한 클래스와 다른 클래스가 연관 관계가 있을 때 사용
//Composition : 한 클래스가 다른 클래스에 완전히 종속되는 관계일 때 사용
//Aggregation : 한 클래스가 다른 클래스를 포함하는 관계일 때 사용
//Generalization : 일반적인 요소와 더 구체적인 요소의 관계일 때 사용
//
//## Association 연관관계 ——————→
//
//- 객체를 멤버 객체(필드)로 사용
//- 클래스 내에서 멤버변수로 레퍼런스 가는 경우
//- 의존하는 곳으로 화살표
//- 실선에 열린 삼각형은 클래스간의 의존관계를 의미한다.
//- 한 클래스와 다른 클래스가 연관 관계가 있을 때 사용
//
//## Dependency 의존관계 ------------→
//
//- 클래스를 파라미터로 사용
//- 메소드 내에서 일시적으로 인스턴스 만드는 경우
//- 의존하는 곳으로 화살표
//- 점선에 열린 삼각형은 클래스간의 의존관계를 의미한다.
//
//## Generalization 일반화
//
//- 닫힌 삼각헤드에 실선 (클래스 간의 상속관계)
//- 방향은 자식 → 부모쪽으로
//- 일반적인 요소와 더 구체적인 요소의 관계일 때 사용
//- **extends** CLASS
//
//## Realization 실체화
//
//- **interface** CLASS
//- **implements** CLASS **@Override**
//
//## Aggregation 집합 ◇-------------→
//
//- 라이프 사이클이 따로 놀 때
//- 한 클래스가 다른 클래스를 포함하는 관계일 때 사용
//- 문제는 있지만 존재할 수 있는 경우
//
//## Composition 합성/구성 ◆---------→
//
//- 라이프 사이클이 같이 돌 때
//- 한 클래스가 다른 클래스에 완전히 종속되는 관계일 때 사용
//- 해당 객체가 없어졌을 때 같이 사라지는 경우
//
//## Capability 능력
//
//- 0..1 이라는 건 하나도 가지지않거나, 하나를 가지거나 라는 뜻임
//- 만약에 리스트화하면 하나도 가지지않거나, 무한대(허락하는한)로 가질 수 있다.
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

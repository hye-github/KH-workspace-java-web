package test_221228;

public abstract class SmartPhone implements MultiMedia, Telephone, Camera, Navigation {

	private Telecom telecom;
	
	public SmartPhone(Telecom telecom) {
		this.telecom = telecom;
	}

//	public Telecom getTelecom() {
//		return telecom;
//	}
//
//	public void setTelecom(Telecom telecom) {
//		this.telecom = telecom;
//	}
	
}

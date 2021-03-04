package polymophism02;

public class SamsungTV implements TV {
	public SamsungTV() {
		System.out.println(">> SamsungTV 객체 생성");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV - 전원 ON");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - 전원 OFF");
	}
	@Override
	public void volumUp() {
		System.out.println("SamsungTV - 소리 크게");
	}
	@Override
	public void volumDown() {
		System.out.println("SamsungTV - 소리 작게");
	}
	//----------------------------------------------
	
	public void initMethod() {
		System.out.println("SamsungTV-initMethod() 실행~~");
	}
	public void destroyMethod() {
		System.out.println("SamsungTV-destroyMethod() 실행~~");
	}
}

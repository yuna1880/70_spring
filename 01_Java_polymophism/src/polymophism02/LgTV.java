package polymophism02;

public class LgTV implements TV{
	
	@Override
	public void powerOn() {
		System.out.println("LgTV - 전원 ON");	
	}
	@Override
	public void powerOff() {
		System.out.println("LgTV - 전원 OFF");
		
	}
	@Override
	public void volumUp() {
		System.out.println("LgTV - 소리크게~~~");
		
	}
	@Override
	public void volumDown() {
		System.out.println("LgTV - 소리작게~~~");
		
	}
}

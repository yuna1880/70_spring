package polymophism03;

public class SamsungTV implements TV {
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
}

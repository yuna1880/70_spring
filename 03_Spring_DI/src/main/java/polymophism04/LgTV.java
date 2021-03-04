package polymophism04;

public class LgTV implements TV{
	private Speaker speaker;
	private int price;
	
	public LgTV() {
		System.out.println(">> LgTV 객체 생성");
	}
	
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
		speaker.volumUp();
	}
	@Override
	public void volumDown() {
		System.out.println("LgTV - 소리작게~~~");
		speaker.volumDown();
		
	}
	public void initMethod() {
		System.out.println("LgTV-initMethod() 실행~~");
	}
	public void destroyMethod() {
		System.out.println("LgTV-destroyMethod() 실행~~");
	}
	//======================================================

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

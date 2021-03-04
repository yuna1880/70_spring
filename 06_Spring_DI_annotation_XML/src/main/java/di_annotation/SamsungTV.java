package di_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component : 객체 생성
//@Component() // samsungTV 명칭으로 객체가 생성된다. (첫글자 소문자)

@Component("tv") // tv라는 이름으로 객체 생성
public class SamsungTV implements TV {
	
	//@Autowired와 @Qualifier는 같이 쓰여야 한다. 데이터 타입 -> 명칭이 sony인것 찾아서 주입!
	@Autowired //동일한 데이터 타입을 찾아서 주입해준다.
	@Qualifier("apple")
	private Speaker speaker;
	private int price;
	private int width;
	private int height;
	
	public SamsungTV() {
		System.out.println(">> SamsungTV 객체 생성");
	}
	public SamsungTV(Speaker speaker) {
		System.out.println(">> SamsungTV(speaker)객체 생성");
		this.speaker = speaker;
	}
	
	public SamsungTV(Speaker speaker, int price) {
		System.out.println(">> SamsungTV(speaker,price)객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	
	public SamsungTV(Speaker speaker, int width, int height) {
		System.out.println(">> SamsungTV(speaker,width,height)객체 생성");
		this.speaker = speaker;
		this.width = width;
		this.height = height;
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
		speaker.volumUp();
	}
	@Override
	public void volumDown() {
		System.out.println("SamsungTV - 소리 작게");
		speaker.volumDown();
	}
	//----------------------------------------------
	
	public void initMethod() {
		System.out.println("SamsungTV-initMethod() 실행~~");
	}
	public void destroyMethod() {
		System.out.println("SamsungTV-destroyMethod() 실행~~");
	}
	@Override
	public String toString() {
		return "SamsungTV [speaker=" + speaker + ", price=" + price + ", width=" + width + ", height=" + height + "]";
	}
	
	//==================getter&setter===============================
	
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	//=================================================
	
	
}

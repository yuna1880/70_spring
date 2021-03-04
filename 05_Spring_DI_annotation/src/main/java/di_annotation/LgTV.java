package di_annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//이름 쓰지 않으면, lgTV 라는 이름으로 객체 생성(클래스명 첫글자 소문자로..)
@Component("tv2")
public class LgTV implements TV {
	
	//@Autowired와 @Qualifier가 결합된 어노테이션!
	@Resource(name="apple")
	private Speaker speaker;
	private int price;
	
	//(실습) LgTV객체 생성하고, 스피커도 주입해서 TV사용.
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

package di_annotation;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {
	
	public AppleSpeaker() {
		System.out.println("---->> AppleSpeaker 생성");
	}
	@Override
	public void volumUp() {
		System.out.println("---->> AppleSpeaker 소리 크게");
	}
	@Override
	public void volumDown() {
		System.out.println("---->> AppleSpeaker 소리 작게");
	}
}

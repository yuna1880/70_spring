package polymophism02;

public class TVUser {

	public static void main(String[] args) {
		
		//삼성tv사용
		//SamsungTV tv = new SamsungTV();
		
		//LG TV로 변경
		//LgTV tv = new LgTV();
		//TV tv = new SamsungTV();
		
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.volumUp();
		tv.volumDown();
		tv.powerOff();
	}
}

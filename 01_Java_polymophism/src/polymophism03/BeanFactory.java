package polymophism03;

public class BeanFactory {
	//Factory 패턴을 적용해서 결합도를 낮춤
	//TV를 만들어주는 공장역활 클래스의 메소드
	//이름을 전달받아서 거기에 맞는 객체를 생성해준다.
	public Object getBean(String beanName) {
		Object bean = null;
		if ("samsung".equals(beanName)) {
			bean = new SamsungTV();			
		} else if ("lg".equals(beanName)) {
			bean = new LgTV();	
		}
		return bean;
	}
}

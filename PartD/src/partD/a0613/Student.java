package partD.a0613;

/**
 *	builder 패턴이 적용된 dto 클래스
	//1) 빌더 패턴을 사용하면 좋은점.
	//2) 내부 클래스의 분석
 *
 */
public class Student {
	private String stuno;
	private String name;
	private int age;
	private String address;
	
	public String getStuno() {
		return stuno;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	
	private Student(StudentBuilder builder) {
		this.stuno=builder.stuno;
		this.name=builder.name;
		this.age=builder.age;
		this.address=builder.address;
	}
	
	public static StudentBuilder builder() {
		return new StudentBuilder();
	}
	
	//내부클래스 - Builder 
	public static class StudentBuilder{
		private String stuno;
		private String name;
		private int age;
		private String address;
		
		/*
		 * public StudentBuilder(String stuno) { this.stuno=stuno; }
		 */
		
		public StudentBuilder stuno(String stuno) {
			this.stuno = stuno;
			return this;
		}
		public StudentBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public StudentBuilder age(int age) {
			this.age = age;
			return this;
		}
		public StudentBuilder address(String address) {
			this.address = address;
			return this;
		}
		
		public Student build() {
			return new Student(this);
		}
		
	}

	@Override
	public String toString() {
		return "Student [stuno=" + stuno + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
	
	
	
}
// 클라이언트 프로그램으로부터 팩토리 클래스로 많은 파라미터를 넘겨줄 때 타입, 순서 등에 대한 관리가 어려워져 에러가 발생할 확률이 높아집니다.
//경우에 따라 필요 없는 파라미터들에 대해서 팩토리 클래스에 일일이 null 값을 넘겨줘야 합니다.
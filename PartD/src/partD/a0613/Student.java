package partD.a0613;

/**
 *  중첩클래스( 또는 중첩 인터페이스) 중첩을 nested(내포된)라고 합니다.
 *  ㄴ 클래스 안의 클래스 : 외부클래스, 내부클래스
 *  ㄴ 내부클래스는 1) 클래스의 멤버입니다. 기존 멤버는 필드 , 메소드 있었습니다.
 *  		ㄴ내부클래스는 (a)인스턴스 멤버 또는 (b)static 멤버 둘다 가능합니다.
 *  ㄴ 내부클래스는 2) 메소드 내부에서 정의될 수 있습니다. 이런 경우는 지역변수처럼 쓰입니다.(사용빈도 거의 없음)
 *  
 *
 */
 


//	builder 패턴이 적용된 dto 클래스
 //1) 빌더 패턴을 사용하면 좋은점.
 //2) 내부 클래스의 StudentBuilder 분석
 
public class Student {
	private String stuno;
	private String name;
	private int age;
	private String address;
	private String email;
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String stuno, String name, int age, String address, String email) {
		super();
		this.stuno = stuno;
		this.name = name;
		this.age = age;
		this.address = address;
		this.email = email;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getEmail() {
		return email;
	}
	//생성자
	private Student(StudentBuilder builder) {	//인자 StudentBuilder 타입
		this.stuno=builder.stuno;
		this.name=builder.name;
		this.age=builder.age;
		this.address=builder.address;
		this.email=builder.email;
	}	//내부클래스의 (111번째줄)빌더가 저장한 초기화 값들을 외부클래스 필드에게 전달
	
	public static StudentBuilder builder() {	//외부클래스의 static 메소드 - 클래스이름.메소드()
		return new StudentBuilder();
	}	//외부클래스 static 메소드가 접근하려면 내부클래스도 static 이어야 합니다.
	
	//내부클래스 - Builder -Student 클래스 안에서 정의된 StudentBuilder 클래스
	//			-Student 클래스의 멤버입니다. 필드,메소드와 같은 클래스의 구성요소.
	//			-내부 클래스는 static 클래스가 가능합니다.
	public static class StudentBuilder{
		private String stuno;
		private String name;
		private int age;
		private String address;
		private String email;
		
		/*
		 * public StudentBuilder(String stuno) { this.stuno=stuno; }
		 */
		
		//내부클래스 기본생성자
		public StudentBuilder() {
			super();
			System.out.println("나는 static 내부 클래스 생성자입니다.");
		}
		
		//내부클래스 인스턴스 메소드
		public StudentBuilder stuno(String stuno) {
			this.stuno = stuno;
			return this;
		}
		public StudentBuilder name(String name) {
			this.name = name;	//내부클래스 name 필드 setter
			return this; // StudentBuilder 객체
		}
		
		public StudentBuilder age(int age) {
			this.age = age;
			return this;
		}
		public StudentBuilder address(String address) {
			this.address = address;
			return this;
		}
		
		public StudentBuilder emaill(String email) {
			this.email = email;
			return this;
		}
		
		public Student build() {
			return new Student(this);	//this는 자신의 StudentBuilder 객체 입니다.
		}
		
	}//StudentBuilder 클래스 구현

	@Override
	public String toString() {
		return "Student [stuno=" + stuno + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
	
	
	
}
// 클라이언트 프로그램으로부터 팩토리 클래스로 많은 파라미터를 넘겨줄 때 타입, 순서 등에 대한 관리가 어려워져 에러가 발생할 확률이 높아집니다.
//경우에 따라 필요 없는 파라미터들에 대해서 팩토리 클래스에 일일이 null 값을 넘겨줘야 합니다.
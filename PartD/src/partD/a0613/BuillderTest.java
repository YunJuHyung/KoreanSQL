package partD.a0613;

public class BuillderTest {

	
	//메서드 체이닝 또는 플루언트 인터페이스(Fluent Interface)
		public static void main(String[] args) {
			//객체 생성과 초기화 방법
			//1) 기본생성자
			Student s1 = new Student();
			s1.setStuno("2023019");
			s1.setName("김일구");
			s1.setEmail("kkk@naver.com");
			s1.setAge(33);
			s1.setAddress("서울시");
			
			
			//2)커스텀 생성자
			Student s2 = new Student("2023019", "김일팔", 25, "kkkk@naver.com", "서울시");
			//	커스텀 생성자의 단점 : 일부 필드만 초기화 시킬때도 null 또는 0 의미 없는 값을 부여해야합니다.
			Student s3 = new Student("2023017", "김일치", 0, null, null);
			
			//3) 1과2)의 장점으로 만들어진 빌더 패턴
			Student stu = Student.builder()		//builder() 메소드 리턴타입 StudentBuilder
				.name("김땡오")		//builder() 메소드 리턴타입 StudentBuilder
				.age(22)			//builder() 메소드 리턴타입 StudentBuilder
				.build();			//builder() 메소드 리턴타입 Student 아마 111번째 줄
			//Builder 클래스가 필드초기화를 담당하고 마지막에 build 메소드에서 해당 객체를 리턴합니다.
			//Student 클래스에서 builder()메소드를 사용한다
			System.out.println(stu);
			
		}

}

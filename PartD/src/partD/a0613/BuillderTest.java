package partD.a0613;

public class BuillderTest {

		
		public static void main(String[] args) {
			
			Student stu = Student.builder()
					.name("김땡오")
					.age(22)
					.build();
			
			System.out.println(stu);
			
		}

}

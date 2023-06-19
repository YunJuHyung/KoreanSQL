package jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import koreait.jdbc.day06.SingletonClass;

//테스트케이스
class SingletonClassTest {

 @Test
 void SingletonClasstest() {
     SingletonClass s1 = SingletonClass.getInstance();
     SingletonClass s2 = SingletonClass.getInstance();

     assertEquals(s1, s2);
 }
}


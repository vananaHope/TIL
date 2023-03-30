package javaexp.a01_begin;

public class A02_PrintExp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
- 다중행 주석
1. 출력 처리의 여러가지 형식
	1) 자바는 기본적으로 System.out.println("출력문자열");
		명령을 통해서 줄바꿈과 함께 문자열을 출력한다.
		sysout+ctrl+space : 자동 출력문 생성
 */
		System.out.println("안녕하세요");
		System.out.println("안녕하세요");
		System.out.println("안녕하세요");
//	2) 줄바꾸지 않는 출력 : System.out.print()
		System.out.print("홍길동,");
		System.out.print("김길동,");
		System.out.println("신길동!");
		System.out.println();
//		ex) 국어 70
//		    영어 80
//          수학 90
//		국어, 70, 영어, 80, 수학, 90 
//		각각 System.out.println("국어")에 넣어 위형식으로 출력되게 하세요
//		각각 System.out.println(70)에 넣어 위형식으로 출력되게 하세요
//		각각 System.out.println("영어")에 넣어 위형식으로 출력되게 하세요
		System.out.print("국어 ");
		System.out.println(70);
		System.out.print("영어 ");
		System.out.println(80);
		System.out.print("수학 ");
		System.out.println(90);
	
		
		
//	ctrl+/ : 단일주석생성
//		변경2
	}

}

package javaexp.a01_begin;

public class A04_varBasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 숫자형 데이터 선언과 할당
		// 형식: 유형 이름 = 데이터 
		int num01 = 25;
		var num02 = 30;
		System.out.println(num01);
		System.out.println(num02);
		System.out.println(num01+num02);
		int point01 = 70;
		// + : 숫자형태을 합산해주지만, 문자열과 함께 
		// 사용하면 연결해주는 역할을 한다.
		System.out.println("점수:"+point01);
		// ex) 국어, 영어, 수학 점수를 영문명으로 변수를
		// 선언하여, 항목별로 출력하고,
		// 총계도 출력하세요.
		int kor = 80;
		var eng = 90;
		int math = 95;
		System.out.println("국어:"+kor);
		System.out.println("영어:"+eng);
		System.out.println("수학:"+math);
		System.out.print("합산:");
		System.out.println(kor+eng+math);
		// ex) 사과가격, 바나나가격, 딸기가격을 영문변수로
		// 선언하고, 가격데이터를 할당한 후, 항목별로 출력하고,
		// 총비용을 출력하세요.
		int apple = 1000;
		var banaPrice= 4000;
		var strawPrice = 12000;
		System.out.println("사과:"+apple);
		System.out.println("바나나:"+banaPrice);
		System.out.println("딸기:"+strawPrice);
		System.out.print("총계:");
		System.out.println(apple+banaPrice+strawPrice);
		System.out.println("총계:"+
				(apple+banaPrice+strawPrice));
		var tot = apple+banaPrice+strawPrice;
		System.out.println("총계:"+tot);
		
		
		
		
		
		
		
		
		
	}

}

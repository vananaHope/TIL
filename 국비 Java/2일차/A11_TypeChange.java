package javaexp.a01_begin;

public class A11_TypeChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*		
		# 형변환
		1. 데이터 타입을 다른 타입으로 변환하는 것을 말한다.
			1) 기본 데이터 타입끼리
				작 <==> 큰, 실수 <==> 정수
			2) 객체형끼리 변경
				- 문자열 데이터와 숫자변경
					"301" vs 301
					"301"+"101" vs 301+101
					"301101"       402
					ps) 실무형태
					모든 데이터는 기본적으로 문자열형태로만
					전송하고 전달받을 수 있다.
					내장된 객체에서 지원하는 기능메서드를 통해서
					전환이 가능하다.
					 	
					 
					
					
				- 상속관계에서 형변환(객체이후 진행)
				
				
				
		2. 종류
			1) 자동형(묵시적) 형변환 :
				작은유형이 큰유형으로 할당할 때(promotion)
			2) 강제형(명시적) 형변환 :
				큰유형을 작유형으로 할당할 때,
				명시적으로 형변환 표현(casting)
		*/
		byte num01 = 30;
		int num02 = 230;
		// 주의 : 데이터를 오른쪽에서 =(대입연산자)에 의해서
		// 	왼쪽으로 할당된다.
		//  순수데이터로 할당할 수 있것이 아니고, 그것을
		//  포함하는 용기(유형)기준으로 할당한다.		
//		num02 = num01; // 작은 용기에 있는 데이터를 큰 용기에
		// 있는 데이터로 할당, 묵시적(자동) 형변환이 일어난다.
//		num01 = num02; (x)
		num01 = (byte)num02; 
		// 큰 용기에 이는 데이터를 작은 용기에
		// 있는 데이터로 할당,
		// 1. 데이터 할당 불가(자동형변환이 불가)
		// 2. 큰 용기에 있는 데이터를 작은 용기에 할당할려면
		//    casting작업이 필요한다.
		//    (byte) 해당 틀을 지정하여 처리
		// 주의) 해당 범위 안에 있으면 정상적으로 할당하지만,
		//   해당 범위를 초과하는데 casting하는 경우
		//   정수의 경우에는 bit단위로 잘려지기에 원치않는 경과를
		//   확인할 수 있다.
		System.out.println(num01);
		/*
		3. 정수 vs 실수 변환
		*/
		// 1) 정수 ==> 실수(promotion)
		// 		20 ==> 20.0
		// 2) 실수 ==> 정수(casting)
		//      3.14 ==> 3
		int num03 = 20;
		double num04 = num03;
		System.out.println(num04);
		double num05 = 3.14;
		int num06 = (int)num05; // 실수 정수 casting
		System.out.println(num06);
		/*
		# 주의(자바에만 있음)- 연산자 할때, 한번 더 언급
		1. 자바는 정수/정수형은 정수형 처리된다.
			소숫점이하를 처리해주지 않는다.
			==> 소숫점이하를 처리하기 위해서는 나눗셈의
			데이터중 적어도 하나는 실수형으로 형변환하여 
			처리하여야 한다.(강제형변환-casting)
			cf) casting을 무조건 큰데이터를 작은 데이터에
			할당하는 것이 아니라, 데이터유형의 크기/종류에 
			상관없이 강제 형변환시 사용한다. 
		 * */
		int num07 = 10;
		int num08 = 3;
		System.out.println(num07/num08);
		System.out.println(num07/(double)num08);
		System.out.println((double)num07/num08);
		System.out.println((double)num07/(double)num08);
		// ex) 25와 7을 정수로 변수로 선언하여,
		//     소숫점이하가 나오지 않는 경우와
		//     소숫점이하가 나오게 하는 경우를 처리하여 출력하세요.
		int num09 = 25;
		int num10 = 7;
		System.out.println(num09/num10);
		System.out.println(num09/(double)num10);
		System.out.println((double)num09/num10);
		System.out.println((double)num09/(double)num10);
		
		
		
		
		
		
		
		
		
		
				
		
		
		
	}

}

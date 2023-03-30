package javaexp.a01_begin;

public class A12_StringTypeChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		문자열을 정수형 변환
		    내장된 Integer객체의 parseInt()를
		    메서드를 통해서 정수형문자열을 숫자형으로
		    변환해준다.
		    ex) "25.7"(X) "이십오"(X) "30"(O) 
			int num01 = Integer.pareInt("25")
		문자열을 실수형 변환
		 	내장된 Double객체의 parseDouble()을
		 	메서드를 통해서 실수형문자열을 실수형으로
		 	변환해준다.
		 	ex) "25"(O) "이십오점사"(X) "3.14" (O)
		 	double num02 = Double.parseDouble("3.14");
		 * */
		int num01 = Integer.parseInt("25");
		System.out.println(num01+25);
		String num02Str = "30.15"; 
		// 반드시 정수형 문자열이어야 한다.
		// int num02 = Integer.parseInt(num02Str);
		// 실행에러(컴파일까지되지만 실행시 에러발생)
		// System.out.println(num02+20);
		double num02 = Double.parseDouble(num02Str);
		System.out.println(num02+30.5);
		// 실수형변환에서 정수형문자열을 전환이 된다.
		System.out.println(Double.parseDouble("30")+0.7);
		// ex1) 문자열 "70"과 "20"을 정수형으로 형변환하여,
		//     합산된 결과를 출력하세요.
		// ex2) 문자열 "30.7"과 "40.2"를 실수형으로 형변환하여
		//     합산된 결과를 출력하세요.
		String str01 = "70";
		String str02 = "20";
		int num03 = Integer.parseInt(str01);
		int num04 = Integer.parseInt(str02);
		System.out.print(str01+" + "+str02+ " = ");
		System.out.println(num03+num04);
		String str03= "30.7";
		String str04= "40.2";
		double num05 = Double.parseDouble(str03);
		double num06 = Double.parseDouble(str04);
		System.out.print(str03+" + "+str04+ " = ");
		System.out.println(num05+num06);
		
		
	}

}

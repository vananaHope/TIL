# Java 예외
## Error VS Exception
* Error는 프로그램이 동작하는 환경에서 문제가 발생하여 프로그램이 정상적으로 동작하지 않는 것을 말한다.
* Exception는 직접 짠 코드가 예상과는 다른 상황에 직면했을 때를 말한다.

## 예외의 처리 
* 예외가 발생한 것 같은 부분은 try catch문을 통해 격리시킨다.
* try에는 예외가 의심되는 부분이 들어가고 catch 문에는 예외가 발생했을 때 대신 처리되는 부분이 들어간다.
* catch의 매개변수 = Exception 데이터 타입 + 문자 ( e를 넣는 것이 일반적 )

<img src="https://user-images.githubusercontent.com/125250099/224469340-aa122701-9896-4595-aa91-35eac7ef5990.png" width="50%">

* try catch문을 두 개 합친 경우
```
public class ExceptionApp {

	public static void main(String[] args) {
		int[] scores = {10, 20, 30};
		System.out.println(1);
		try {
			System.out.println(scores[3]);
			System.out.println(2/0);  ← X, 실행되지 않음
		} catch(ArithmeticException e) { // 0으로 나눴을 때의 에러가 발생하면 에러처리
			System.out.println("잘못된 계산입니다.");
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("값이 존재하지 않습니다.");
		}
		System.out.println(3);

	}

}
```

* try문을 쭉 진행하다가 한번 예외가 발생하면     
  그 아래의 문장들은 실행이 되지 않고 바로 catch문으로 넘어간 후에 try catch문을 빠져나간다.
* "잘못된 계산입니다"는 출력되지 않고 "값이 존재하지 않습니다" 출력된다.

## 예외의 우선순위
* 위에서 사용한 ArithmeticException과 ArrayIndexOutOfBoundsException은 모두 Exception이라는 부모 클래스를 가지고 있다.
<p align="left">
  <img src="https://user-images.githubusercontent.com/125250099/224469621-51b315f9-ece4-43f4-9bee-522d163d4df8.png" width="40%">
  <img src="https://user-images.githubusercontent.com/125250099/224469624-b60d027c-ef46-480b-a88d-046da27de53f.png" width="40%">
</p>

* 예외로 Exception을 받게 되면 ArithmeticException와 ArrayIndexOutOfBoundsException를 따로 따로 받을 필요 없이 한 번에 받을 수 있다.


* ArithmeticExcpetion이 조건문으로 담겨있는 catch문이 Exception이 조건문으로 담겨있는 catch문보다 앞에 있는 경우를 가정해보자 
```
public class ExceptionApp {

	public static void main(String[] args) {
				
		int[] scores = {10, 20, 30};
		System.out.println(1);
		try {
			System.out.println(2);
//			System.out.println(scores[3]);
			System.out.println(3);
			System.out.println(2/0);
			System.out.println(4);
		} catch(ArithmeticException e) {
			System.out.println("잘못된 계산입니다.");
		} catch(Exception e) {
			System.out.println("오류가 발생했습니다.");
		}
		System.out.println(5);

	}

}
```
* 컴파일러는 코드를 위에서부터 순서대로 읽기 때문에 ArithmeticException 예외가 발생하는 경우      
  더 위에 있는 catch문이 동작해서 "잘못된 계산입니다."가 출력됩니다.
* catch 문의 순서 그 다음엔 상속 여부에 따라 우선순위가 결정된다.

## e의 비밀
* Exception e에서 Exception은 변수의 데이터형이 되고, e는 변수가 된다.
* e는 바꿔도 무방하지만 Exception은 데이터타입이므로 함부로 바꾸어선 안된다.
* 어떤 에러가 떴는 지 예외메시지가 아니라 콘솔창에 띄우고 싶다면 **e.getMessage()** 를 사용하면 된다.
* 예외처리가 되면서 예외 메시지를 확인하고 싶다면 **e.printStackTrace()** 를 사용하면 된다.

```
public class ExceptionApp {

	public static void main(String[] args) {
		
		int[] scores = {10, 20, 30};
		System.out.println(1);
		try {
			System.out.println(2);
//			System.out.println(scores[3]);
			System.out.println(3);
			System.out.println(2/0);
			System.out.println(4);
		} catch(ArithmeticException e) {
			System.out.println("잘못된 계산입니다."+e.getMessage());
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("오류가 발생했습니다");
		}
		System.out.println(5);

	}

}
```

## Checked Exception VS Unchecked Exception
* 


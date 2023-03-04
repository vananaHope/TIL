# Java Method
## 메서드 기본
* 메서드는 **자바에서 사용하는 함수**이다.
* 메서드는 서로 연관된 코드를 모아놓은 정리정돈 상자이다.
* System.output.println에서 println은 메서드
* Math.floor()는 입력값을 내림함 값으로 변환해주는 Math라는 클래스의 floor 메서드
* public static void main{} 역시 main이라는 이름을 가진 메서드이며 프로그램을 실행할 때 main을 찾아 실행하도록 약속이 되어있다.

## 메서드 기본 형식
* 메서드를 사용하지 않으면 여러 번 같은 코드를 입력해야 하고 재사용도 어렵다.

```
	public static void main(String[] args) {
		// 1억번 반복
		System.out.println("-");
		System.out.println("A");
		System.out.println("A");
		// 1억번 반복
		System.out.println("-");
		System.out.println("A");
		System.out.println("A");
		// 1억번 반복
		System.out.println("-");
		System.out.println("A");
		System.out.println("A");
		
 }
```

* main 메서드 위 쪽에 public static printTwoTimesA 라는 메서드를 생성하여 반복을 줄이고 재사용도 가능해진다.

```
public class WhyMethod {

	public static void printTwoTimesA() { 
		System.out.println("-");
		System.out.println("A");
		System.out.println("A");
	}
	
	public static void main(String[] args) {
  
    
		printTwoTimesA();
		// 1억번 반복!!
		printTwoTimesA();
		// 1억번 반복!!
		printTwoTimesA();
   }
   
}     
```

**리팩토링**
* 이클립스 > 우클릭 > Refactor > Extract Method 
* 이클립스가 자동으로 메서드를 생성해준다.

## 메서드 입력 값
* 메서드 기본 형식의 코드 처럼 짤 경우 A를 B로 바꿀 때 메서드의 이름과 내용을 모두 바꿔야 하는 불편함이 있다.
* 따라서 입력값에 따라 메서드가 다르게 동작하도록 바꿀 필요가 있다.

```
public static void printTwoTimes(String text, String delimiter) {
		System.out.println(delimiter);
		System.out.println(text);
		System.out.println(text);

```

* 위 코드처럼 printTwoTimes 소괄호 안에 입력값에 데이터 타입과 변수명을 입력해준다
* 그렇게 되면 메서드의 입력값에 따라 다른 값이 출력된다.

```
public class WhyMethod {
	public static void main(String[] args) {

		// 1억번 반복!!
		printTwoTimes("a", "-");
		// 1억번 반복!!
		printTwoTimes("a", "*");
		// 1억번 반복!!
		printTwoTimes("a", "&");
		// 1억번 반복!!
		printTwoTimes("b", "!");
		

	}

	public static void printTwoTimes(String text, String delimiter) {
		System.out.println(delimiter);
		System.out.println(text);
		System.out.println(text);
	}
	
}
```

**용어정리**
* 메서드에서 소괄호 안에 넣어야 하는 값을 매개변수(parameter)라고 부른다.
* 사용할 때 소괄호 안에 넣는 값을 인자(argument)라고 부른다.

## 메서드 출력
* (메서드의) return 값 = (그 메서드의) 실행결과
* return 값
  * 메서드의 실행결과
  * 메서드를 종료시킴 ( return 이후의 구문은 실행되지 않음 )
* 리턴값에 따라 메서드 앞의 데이터 타입을 변경해주어야 한다.

```
public static String a(){  // 데이터 타입은 String, 출력값은 a
    /...
    return a;
}


public static int one(){  // 데이터 타입은 int, 출력값은 1
    return 1;
}


public static void main(){  // void는 리턴값이 없는 메서드를 정의할 때 사용됨
    /...
}

```

## 메서드의 활용
* 메서드를 활용하지 않았을 경우

```
public class AccountingApp {

	public static void main(String[] args) {
		// 공급가액
		double valueOfSupply = 10000.0;
		// 부가가치세율
		double vatRate = 0.1;
		// 부가세
		double vat = valueOfSupply * vatRate;
		// 합계(공급가액 + 부가세)
		double total = valueOfSupply + vat;
		
		System.out.println("Value of supply : " + valueOfSupply);
		System.out.println("VAT : " + vat);
		System.out.println("Total : " + total);

	}
  // 복잡하지는 않지만 이러한 코드가 1억줄 이상이 된다면 이해하기 어렵다. 따라서 메서드를 활용해야 한다.
```

* 메서드를 활용한 경우

```
public class AccountingApp {
	// 공급가액
	public static double valueOfSupply = 10000.0;
	// 부가가치세율
	public static double vatRate = 0.1;
	
	public static double getVAT() {
		return valueOfSupply * vatRate;
	}
	
	public static double getTotal() {
		return valueOfSupply + getVAT();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Value of supply : " + valueOfSupply);
		System.out.println("VAT : " + getVAT());
		System.out.println("Total : " + getTotal());

	}

} 
// 메서드를 이용함으로써 코드를 단순화하고 메서드의 역할을 명확하게 전달할 수 있다. 또한, 가독성이 높아지고 유지보수에 용이해진다.
```

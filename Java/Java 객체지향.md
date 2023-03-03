# Java 객체지향
## 남의 클래스 & 남의 인스턴스
```
import java.io.FileWriter;
import java.io.IOException;

public class OthersOOP {

	public static void main(String[] args) throws IOException {
		
		// class : System, Math, FileWriter
		// instance : f1, f2
		
		// 1회용 작업인 경우에는 클래스에 소속된 메소드를 사용
		System.out.println(Math.PI);
		System.out.println(Math.floor(1.8));
		System.out.println(Math.ceil(1.8));
		
		// 긴 맥락을 가진 경우 클래스를 복제해서 사용 ( 인스턴스 )
		FileWriter f1 = new FileWriter("data.txt"); // data.txt라는 파일에 데이터를 저장하겠다는 상태를 가진 인스턴스가 f1
		f1.write("Hello");
		f1.write(" Java");
		
		
		FileWriter f2 = new FileWriter("data2.txt"); // f1과는 다른 복제본 : 데이터는 data2.txt에 저장된다
		f2.write("Hello");
		f2.write(" Java");
		f2.close();
		
	}

}
```
**참고 : FileWriter 클래스에서 f1.close를 하기 전까지는 f1.write를 통해 내용을 계속 추가할 수 있다.**

## 변수와 메서드
```
public class Practice {

	public static void main(String[] args) {
		
		// 1억번 반복
		System.out.println("----");
		System.out.println("a");
		System.out.println("a");

	}

}
```
↓ 반복되는 부분을 메서드화 시킴으로써 훨씬 간결하게 만들 수 있다.
```
public class Practice {

	public static void main(String[] args) {
		
		// 1억번 반복
		printA();

	}

	public static void printA() {
		System.out.println("----");
		System.out.println("a");
		System.out.println("a");
	}

}
```
↓ 하지만 구분자를 여러 개 써서 1억번 이상 출력해야 된다면 이 때는 변수를 따로 설정하여 값을 넣는 방식으로 간결하게 만들 수 있다.    
```
public class Practice {

	public static String delimiter = ""; 
  // 이 때, 메서드 안에서 정의한 변수는 그 메서드 안에서만 쓸 수 있기 때문에 MAIN 메서드 밖으로 꺼내어 
     정의함으로써 다른 메서드가 사용할 수 있도록 한다.
	
	public static void main(String[] args) {
		
		// 1억번 반복
		delimiter = "----";
		printA();
		
		delimiter = "****";
		printA();

	}

	public static void printA() {
		System.out.println(delimiter);
		System.out.println("a");
		System.out.println("a");
	}

}
```

## 클래스
* 클래스는 관련 있는 메서드와 변수를 모아 놓은 정리 상자

```
public class Practice {

	public static String delimiter = "";
	
	public static void main(String[] args) {
		
		// 1억번 반복
		delimiter = "----";
		printA();
		printB();
		
		delimiter = "****";
		printA();
		printB();

	}

	public static void printA() {
		System.out.println(delimiter);
		System.out.println("a");
		System.out.println("a");
	}
	public static void printB() {
		System.out.println(delimiter);
		System.out.println("b");
		System.out.println("b");
	}

}
```
↓ 새로운 클래스를 만들어 printA,printB 메서드와 delimiter 변수를 옮김으로써 더 간결하게 만들 수 있다.
```
class Print {
	public static String delimiter = "";
	public static void A() {
		System.out.println(delimiter);
		System.out.println("a");
		System.out.println("a");
	}
	public static void B() {
		System.out.println(delimiter);
		System.out.println("b");
		System.out.println("b");
	}
	
}

public class Practice {

	public static void main(String[] args) {
		
		// 1억번 반복
		Print.delimiter = "----"; // "새로 만든 클래스.메서드 or 변수" 를 통해 값을 불러온다.
		Print.A();
		Print.B();
		
		Print.delimiter = "****";
		Print.A();
		Print.B();

	}

}
```
↓ 하지만 하나의 java 파일 안에 모두 들어있기 때문에 클래스를 쪼개서 정리정돈 할 수 있다.
```
Practice.java // 이클립스의 Refactor → Move Type to New File을 쓰면 훨씬 쉽게 클래스를 분리시킬 수 있다.

public class Practice {

	public static void main(String[] args) {
		
		// 1억번 반복
		Print.delimiter = "----";
		Print.A();
		Print.B();
		
		Print.delimiter = "****";
		Print.A();
		Print.B();

	}

}
```
```
Print.java

class Print {
	public static String delimiter = "";
	public static void A() {
		System.out.println(delimiter);
		System.out.println("A");
		System.out.println("A");
	}
	public static void B() {
		System.out.println(delimiter);
		System.out.println("B");
		System.out.println("B");
	}

}
```

## 인스턴스
* 인스턴스는 **클래스의 복제본** 이라고 할 수 있다.
* 위에 클래스 부분에서 delimiter라는 변수를 활용해서 간결하게 만들었지만 delimiter 변수를 매번 다르게 선언해줘야 하는 불편함이 있다.
* 이를 해결하는 방법이 바로 **인스턴스**를 이용하는 것이다.

new를 이용하여 Print라는 데이터형(클래스)를 가진 인스턴스를 만들어 사용한다.
```
Practice.java

public class Practice {

	public static void main(String[] args) {
		Print p1 = new Print();
		p1.delimiter = "****";
		p1.A();
		p1.A();
		p1.B();
		p1.B();
		
		Print p2 = new Print();
		p2.delimiter = "----";
		p2.A();
		p2.A();
		p2.B();
		p2.B();
	}
}
```
```
Print.java // static이 있는 메서드는 그 메서드가 속한 클래스에서만 쓸 수 있기 때문에 인스턴스에서 불러오기 위해선 static을 지워야 한다.

class Print {
	public String delimiter = "";
	public void A() {
		System.out.println(delimiter);
		System.out.println("A");
		System.out.println("A");
	}
	public void B() {
		System.out.println(delimiter);
		System.out.println("B");
		System.out.println("B");
	}

}
```

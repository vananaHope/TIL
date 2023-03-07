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
## Static
* static이 붙은 메서드는 **클래스의 메서드**
* static이 붙지 않은 메서드는 **인스턴스의 메서드**
* 공유 폴더를 생각하면 훨씬 이해하기 쉽다.
	* Static : 공유 폴더의 내용물, 안의 내용물을 보고 사용할 수 있지만 해당 내용을 수정하면 해당 폴더를 공유하는 모든 사용자에게도 수정된 결과물이 보여짐
	* Static X ( Instance ) : 공유 폴더 내용물을 내 컴퓨터로 복사한 것, 따라서 수정해도 나만 볼 수 있고 나에게만 영향을 미침

<img src="https://user-images.githubusercontent.com/125250099/223329503-be6349ff-a499-4f01-8746-93d55cd456f6.png" width="50%">

```
class Foo{
    public static String classVar = "I class var";
    public String instanceVar = "I instance var";
    public static void classMethod() {
        System.out.println(classVar); // Ok
//      System.out.println(instanceVar); // Error
    }
    public void instanceMethod() {
        System.out.println(classVar); // Ok
        System.out.println(instanceVar); // Ok
    }
}
public class StaticApp {
 
    public static void main(String[] args) {
        System.out.println(Foo.classVar); // OK
//      System.out.println(Foo.instanceVar); // Error
        Foo.classMethod();
//      Foo.instanceMethod();
         
        Foo f1 = new Foo();
        Foo f2 = new Foo();
//      
        System.out.println(f1.classVar); // I class var
        System.out.println(f1.instanceVar); // I instance var
//      
        f1.classVar = "changed by f1";
        System.out.println(Foo.classVar); // changed by f1
        System.out.println(f2.classVar);  // changed by f1
//      
        f1.instanceVar = "changed by f1";
        System.out.println(f1.instanceVar); // changed by f1
        System.out.println(f2.instanceVar); // I instance var
    }
 
}
```
## 생성자와 this
### 생성자
* 자바는 인스턴스를 생성할 때 클래스명과 같은 이름의 메소드가 있다면 그 메소드를 호출한다.
	그리고 그 메소드를 **생성자(Constructor)** 라고 부른다.
* 생성자는 리턴 데이터 타입을 따로 지정하지 않아도 된다.
* 인스턴스를 생성하는 동시에 값을 입력하면 필수적인 과정을 빼먹지 않을 수 있고 이를 위해 생성자를 이용한다.

```
public class Practice {

	public static void main(String[] args) {
		
		Print2 pp = new Print2("----");
		
		pp.A();
		pp.B();
		
		
		Print2 pp2 = new Print2("####");
		
		pp2.A();
		pp2.B();

	}

}
```
```
class Print2 {
	public String delimiter = "";
	public Print2(String _delimiter --> Practice.java 인스턴스 생성할 때 입력한 매개변수가 들어옴) {
		delimiter = _delimiter; // 뒤에 배우는 THIS를 이용하면 _를 붙일 필요 없어짐
	}
	public void A() {
		System.out.println(delimiter);
		System.out.println("a");
		System.out.println("a");
	}
	public void B() {
		System.out.println(delimiter);
		System.out.println("b");
		System.out.println("b");
	}
	
}
```
### This
* this는 인스턴스의 이름을 가르키며 this.delimiter는 인스턴스의 변수을 의미한다.
```
class Print2 {
	public String delimiter = ""; ---> this.delimiter가 가르키는 것
	public Print2(String delimiter ---> Practice.java 인스턴스 생성할 때 입력한 매개변수가 들어옴, 밑에 delimiter가 가르키는 것) {
		this.delimiter = delimiter;
	}
	public void A() {
		System.out.println(delimiter);
		System.out.println("a");
		System.out.println("a");
	}
	public void B() {
		System.out.println(delimiter);
		System.out.println("b");
		System.out.println("b");
	}
	
}
```
## 클래스와 인스턴스의 활용
### 클래스 활용
```
public class AccountingApp {
	// 공급가액
	public static double valueOfSupply;
	// 부가가치세율
	public static double vatRate = 0.1;
	
	public static double getVAT() {
		return valueOfSupply * vatRate;
	}
	
	public static double getTotal() {
		return valueOfSupply + getVAT();
	}
	
	public static void main(String[] args) {
		valueOfSupply = 10000.0;
		System.out.println("Value of supply : " + valueOfSupply);
		System.out.println("VAT : " + getVAT());
		System.out.println("Total : " + getTotal());

	}

}
```
↓ 위의 예제 코드에서 관련 있는 메서드를 클래스로 모으면 훨씬 코드가 깔끔해진다.
```
class Accounting{
    public static double valueOfSupply;
    public static double vatRate = 0.1;
    public static double getVAT() {
        return valueOfSupply * vatRate;
    }
    public static double getTotal() {
        return valueOfSupply + getVAT();
    }
}
public class AccountingApp {
    public static void main(String[] args) {
        Accounting.valueOfSupply = 10000.0;
        System.out.println("Value of supply : " + Accounting.valueOfSupply);
        System.out.println("VAT : " + Accounting.getVAT());
        System.out.println("Total : " + Accounting.getTotal());
  
    }
}
```
### 인스턴스 활용
* 10000원일 때의 공급가액을 출력하고, 20000원일 때의 공급가액을 출력한 뒤에
  다시 10000원일 때의 부가가치세를 출력하고, 20000원일 때의 부가가치세를 출력하는 복잡한 상황을 생각해보자
* 위 클래스 활용 예제로는 한계가 있고 매번 새롭게 변수를 정의해야 한다.
* 이럴 때 인스턴스를 사용하여 인스턴스마다 고유의 상태를 지정해주면 훨씬 간결하고 효율적인 코드를 짤 수 있다.

```
class Accounting{
    public double valueOfSupply;
    public static double vatRate = 0.1;
    // 클래스와 인스턴스에 모두 적용되는 값이라면 static을 그대로 두는 것이 좋다, 왜냐하면 static이 붙은 값을 변경하면 클래스,인스턴스 상관없이 값이 모두 변경되기 때문
    public Accounting(double valueOfSupply) {
        this.valueOfSupply = valueOfSupply;
    }
    public double getVAT() {
        return valueOfSupply * vatRate;
    }
    public double getTotal() {
        return valueOfSupply + getVAT();
    }
}
public class AccountingApp {
    public static void main(String[] args) {
        Accounting a1 = new Accounting(10000.0);
        
        System.out.println("Value of supply : " + a1.valueOfSupply);
        System.out.println("VAT : " + a1.getVAT());
        System.out.println("Total : " + a1.getTotal());
  
    }
}
```



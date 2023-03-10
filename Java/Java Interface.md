# Java Interface
## 인터페이스
* 인터페이스는 클래스의 변수와 메서드 형태를 엄격하게 강제하는 요소이다.
* "interface + 인터페이스명"을 통해서 인터페이스를 정의한다.
* 구현하고자 하는 클래스 뒤에 implements와 인터페이스명을 적는다.
* 인터페이스를 따르지 않으면 컴파일이 되지 않는다. 따라서 implements가 붙은 클래스는 무조건 인터페이스 내용을 구현해야 한다.

```
interface Calculable{ // interface + 인터페이스명
	int sum(int v1, int v2);
}

class RealCal implements Calculable{ // 인터페이스가 규제하고 있는 형태를 엄수해야만 정상적으로 동작
	public int sum(int v1, int v2) {
		return v1+v2;
	}	
}

public class InterfaceApp {

	public static void main(String[] args) {
		RealCal c = new RealCal();
		System.out.println(c.sum(2, 1));

	}

}
```

## 인터페이스 형식
* 하나의 클래스에 여러 개의 인터페이스를 구현할 수 있다.

```
interface Calculable{
	double PI = 3.14; // 인터페이스에는 변수의 실제 값이 들어간다!
	int sum(int v1, int v2);
}

interface Printable{ // 인터페이스의 이름은 대문자로 시작, 형용사 형태의 이름
	void print();
}

class RealCal implements Calculable, Printable{ // 하나의 클래스에 여러 interface 구현 가능!
	public int sum(int v1, int v2) {
		return v1+v2;
	}

	public void print() {
		System.out.println("This is RealCal!!");
	}
}

public class InterfaceApp {

	public static void main(String[] args) {
		// 더하기 기능이 있는 클래스를 만들어주세요.
		RealCal c = new RealCal();
		System.out.println(c.sum(2, 1)); // 3
		System.out.println(c.PI); // 3.14
	}

}
```
* 인터페이스에는 메소드와 변수가 정의될 수 있는데 변수에는 값이 들어오고 메소드에는 실제 구현이 들어가지 않아서      
  인터페이스를 구현하는 클래스가 형태를 만족하는 메소드를 직접 구현해야 함
  
## 인터페이스와 다형성
* 다형성 : 인스턴스를 만들 때 하나의 클래스가 다양한 얼굴을 갖도록 하는 것
* RealCal 는 Calculable과 Printable이라는 interface를 기반으로 완성된 클래스
* 사용자가 Printable이라는 인터페이스에 속한 메소드만 사용하게 하고 싶다면 인스턴스의 데이터형을 인터페이스의 이름으로 지정하면 된다.     
  ex) Printable c = new RealCal();
* 이렇게 만들면 c라는 인스턴스에서는 Printable에서 선언한 print 메소드는 사용할 수 있지만, Calculable에서 선언한 PI 변수와 sum 메소드는 사용할 수 없다.

<img src="https://user-images.githubusercontent.com/125250099/224246488-31f33964-bc67-43e7-b365-a6a4c12520da.png" width="50%">

* 인스턴스의 데이터형으로 인터페이스를 지정하면 똑같은 인터페이스를 구현하고 있는 클래스라면 어떤 것이 new 뒤에 와도 상관이 없다(호환성을 보장한다)

```
interface Calculable{
	double PI = 3.14; // 인터페이스에는 변수의 실제 값이 들어간다!
	int sum(int v1, int v2);
}

interface Printable{ // 인터페이스의 이름은 대문자로 시작, 형용사 형태의 이름
	void print();
}

class RealCal implements Calculable, Printable{ // 하나의 클래스에 여러 interface 구현 가능!
	public int sum(int v1, int v2) {
		return v1+v2;
	}

	public void print() {
		System.out.println("This is RealCal!!");
	}
}

class DummyCal implements Calculable{ // 인터페이스가 규제하고 있는 형태를 엄수해야만 정상적으로 동작
	public int sum(int v1, int v2) {
		return 3;
	}
}

public class InterfaceApp {

	public static void main(String[] args) {
		// 더하기 기능이 있는 클래스를 만들어주세요.
		Printable c = new RealCal();
//		System.out.println(c.sum(2, 1)); // Error
		c.print();
//		System.out.println(c.PI); // Error
	}

}
```

# Java 상속
## 상속의 기본
* 계산기 클래스를 만드는 것을 가정해보자
```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}

}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu();
		
		System.out.println(cal1.sum(2, 1));

	}

}
```
* Calcu 클래스를 직접 변경할 수 없는 상황에서 마이너스 기능을 추가하는 상황을 가정해보자
```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}

}

class Calcu2 {
	public int sum(int v1, int v2) {
		return v1+v2;
	}
	public int minus(int v1, int v2) {
		return v1-v2;
	}
}

public class InheritanceReview {
	public static void main(String[] args) {
		
    Calcu cal1 = new Calcu();
		
    System.out.println(cal1.sum(2, 1));

	}

}
```
* ↑ 이런 식으로 sum이 중복되고 비효율적인 코딩이 된다. 따라서 이 때 **상속**을 사용하게 된다.
```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}

}

class Calcu2 extends Calcu {

}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu();
		System.out.println(cal1.sum(2, 1));
		
		Calcu2 cal2 = new Calcu2();
		System.out.println(cal2.sum(2,1));

	}

}
```
* ↑ Calcu2 뒤에 extends Calucu를 붙임으로써 Calcu2는 Calcu를 상속하게 된다.
* Calcu2는 Calcu클래스 내용을 따로 복사하지 않아도 그대로 사용할 수 있다.

### 정리 
* 상속은 어떤 클래스가 가지고 있는 변수와 메소드를 다른 클래스가 갖도록 하면서     
  재사용성을 높이고 유지보수의 편의성을 높이고 가독성을 높이고 코드 양을 줄일 수 있게 된다.
  
## 기능 개선과 발전
* 중요한 것은 자식 클래스가 부모 클래스가 가지고 있지 않은 새로운 기능을 추가할 것인가" 또는 "부모 클래스가 가지고 있는 기능을 덮어쓰기 할 것인가"가 된다.

```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}

}

class Calcu2 extends Calcu {
	public int sum(int v1, int v2) { // 부모 클래스 내용을 자식 클래스 내용으로 덮어씀 ( Overriding )
		System.out.println("Calcu2!!!");
		return v1+v2;
	}
	public int minus(int v1, int v2) { // 부모 클래스에 없는 기능 추가
		return v1-v2;
	}
	
}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu();
		System.out.println(cal1.sum(2, 1));
		
		Calcu2 cal2 = new Calcu2();
		System.out.println(cal2.sum(2,1));
		System.out.println(cal2.minus(2,1));

	}

}
```
* 이전과 달리 Calcu에 있는 sum을 실행하는 게 아니라 Calcu2에 있는 sum을 실행하게 된다. ( Overriding )

## 오버라이딩과 오버로딩
* 오버라이딩은 위에서 예제처럼 덮어씌우는 것을 말한다.
* **오버로딩**
  * 상속과 큰 관계가 없다
  * 같은 이름의 메서드를 만들어도 매개변수가 다르면 한 클래스 안에 여러 개의 같은 메서드를 만들 수 있다.

```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}
	public int sum(int v1, int v2, int v3) { // Overloading
		return v1+v2+v3;
	}

}

class Calcu2 extends Calcu {
	public int sum(int v1, int v2) { // 부모 클래스 내용을 자식 클래스 내용으로 덮어씀 ( Overriding )
		System.out.println("Calcu2!!!");
		return v1+v2;
	}
	public int minus(int v1, int v2) { // 부모 클래스에 없는 기능 추가
		return v1-v2;
	}
	
}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu();
		System.out.println(cal1.sum(2, 1, 1));
		
		Calcu2 cal2 = new Calcu2();
		System.out.println(cal2.sum(2,1));
		System.out.println(cal2.minus(2,1));

	}

}
```
## This와 Super
* This는 **자기자신** / Super는 **부모**를 의미한다.
* 기존의 Calcu2 
```
class Calcu2 extends Calcu {
	public int sum(int v1, int v2) { // 부모 클래스 내용을 자식 클래스 내용으로 덮어씀 ( Overriding )
		System.out.println("Calcu2!!!");
		return v1+v2;
	}
```  
* Calcu2!!만 추가로 출력되도록 하고 싶다면 System.out.println("Calcu2!!!"); 뒤에 Caclu의 sum 메서드를 가져오면 된다.
* 만약 Calcu의 sum이 복잡하다면 sum을 그대로 복사하는 데 무리가 따를 수 있다. 이 때 super를 사용한다.

```
class Calcu2 extends Calcu {
	public int sum(int v1, int v2) { // 부모 클래스 내용을 자식 클래스 내용으로 덮어씀 ( Overriding )
		System.out.println("Calcu2!!!");
		return super.sum(v1, v2);
	}
``` 
* ↑ Calcu2!! 출력하고 나서 반환하는 리턴값은 Calcu의 sum을 그대로 사용한다는 의미

```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}
	public int sum(int v1, int v2, int v3) {
		return this.sum(v1, v2)+v3;
	}

}
```
* ↑ 3개의 매개변수를 더하는 sum의 경우 2개의 매개변수를 더하는 sum에다가 v3를 추가로 더해주면 된다.    
  만약 2개의 매개변수를 더하는 첫 번째 sum을 사용하고 싶다면    
  자신 클래스를 의미하는 this를 붙여서 this.sum(v1, v2)에다가 v3를 더해준 값을 리턴값으로 넣어주면 된다.
  
```
class Calcu {
	public int sum(int v1, int v2) {
		return v1+v2;
	}
	public int sum(int v1, int v2, int v3) {
		return this.sum(v1, v2)+v3;
	}

}

class Calcu2 extends Calcu {
	public int sum(int v1, int v2) { // 부모 클래스 내용을 자식 클래스 내용으로 덮어씀 ( Overriding )
		System.out.println("Calcu2!!!");
		return super.sum(v1, v2);
	}
	public int minus(int v1, int v2) { // 부모 클래스에 없는 기능 추가
		return v1-v2;
	}
	
}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu();
		System.out.println(cal1.sum(2, 1, 1));
		
		Calcu2 cal2 = new Calcu2();
		System.out.println(cal2.sum(2,1));
		System.out.println(cal2.minus(2,1));

	}

}
```

## 상속과 생성자
* Calcu2가 Calu를 상속한 경우 ( Calcu 클래스의 생성자가 있음 )
  * 자바에서는 무조건 자식 클래스가 부모 클래스의 생성자를 불러오도록 강제하고 있다. 그렇지 않을 경우 에러가 발생한다.
  * super를 통해 부모 클래스의 생성자를 불러옴으로써 문제를 해결할 수 있다.
```
class Calcu {
	int v1; int v2;
	Calcu(int v1, int v2) {
		this.v1 = v1; this.v2 = v2;
	}
	public int sum() {
		return this.v1+v2;
	}
}

class Calcu2 extends Calcu {

	Calcu2(int v1, int v2) {
		super(v1, v2); // 부모 클래스의 생성자를 반드시 호출해야 함!
		System.out.println("Calcu2!!");
	}
	
}

public class InheritanceReview {

	public static void main(String[] args) {
		
		Calcu cal1 = new Calcu(2,1);
		System.out.println(cal1.sum());
		
		Calcu2 cal2 = new Calcu2(3,4);
		System.out.println(cal2.sum());

	}

}
```

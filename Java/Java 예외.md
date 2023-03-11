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
* Checked Exception는 예외처리가 되어있지 않으면 컴파일이 되지 않는 예외
  * Exception에서 RuntimeException을 제외한 것들
	
  
* Unchecked Exception는 예외처리가 되어있지 않아도 컴파일이 되는 예외
  * RuntimeException을 포함한 그 자식들

<img src="https://user-images.githubusercontent.com/125250099/224479008-73b7a0ed-058b-42c1-a500-562a8c11e5da.png" width="60%">

## Resource와 Finally
* 자바와 연결되는 외부 데이터는 DB,File,Network 등이 있는데 이러한 외부 데이터들을 **Resource** 라고 부른다.
* 이런 Resource들의 경우 붙잡고 놓는 것이 굉장히 중요하다.     
  어떤 Recource를 사용하다가 다른 Resource를 하게 될 경우 꼬이는 경우가 있기 때문이다.
  
```
import java.io.FileWriter;
import java.io.IOException;

public class Practice {

	public static void main(String[] args) {
		try {
			FileWriter f = new FileWriter("data1.txt");
			f.write("Hello");
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
}
```
* 위의 코드에서 만약 close 전에 예외가 발생한다면 f.close()는 실행이 되지 않고 catch로 넘어가게 된다.
* 이럴 때 사용하는 것이 **Finally** 이다.

```
import java.io.FileWriter;
import java.io.IOException;

public class Practice {

	public static void main(String[] args) {
		try {
			FileWriter f = new FileWriter("data1.txt");
			f.write("Hello");
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			f.close(); ---> 오류 발생
		}

	}
}	
```
* ↑ 하지만 finally 내에서 close를 하게 되면 FileWriter f가 선언된 try 메서드 안에 있지 않기 때문에 오류가 발생한다.
* 따라서 FileWriter f를 try 메서드 밖에서 선언해야 한다.

```
import java.io.FileWriter;
import java.io.IOException;

public class Practice {

	public static void main(String[] args) {
		FileWriter f;
		try {
			f = new FileWriter("data1.txt");
			f.write("Hello");
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			f.close(); ---> 그럼에도 오류가 다시 발생
		}

	}
}	
```
* ↑ try 문 밖에서 선언했음에도 오류가 발생한다.


* 그 이유는 f가 정상적으로 인스턴스로 할당되지 않은 경우 (try문의 1줄이 정상적으로 실행되지 않은 경우)    
  close할 f가 없기 때문에 발생할 수 있는 예외를 처리하지 않아서 발생하는 문제이다.
  
  
* 따라서 if 조건문을 활용해서 f에 데이터값이 있는 지 없는 지 판단해서 작동해도록 해야 한다.
  * 이 때도 역시 if문 안에서 close를 할 때 예외 상황이 발생할 수 있기 때문에 try catch를 통한 예외 처리를 다시 해야 한다.

```
import java.io.FileWriter;
import java.io.IOException;

public class Practice {

	public static void main(String[] args) {
		FileWriter f = null; // null은 값이 없다는 뜻
		try {
			f = new FileWriter("data1.txt");
			f.write("Hello");
			f.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(f != null) { // f가 null이 아니라면
				try {
					f.close();
				} catch(IOException e) {
					e.printStackTrace();
			} 
				
			}
			
		}

	}
}	
```

## Try with Resource
* try-with-resource는 리소스 작업을 포함하는 친구들의 예외처리를 할 수 있도록 도와주는 기능이다.
* FileWriter의 인스턴스 f는 close를 해야 작업이 완료되는데 이러한 close를 쓰는 것들을 위한 Autocloseable이 있다.


* 사용법은 try까지는 똑같지만 try에 매개변수가 들어간다. / 이 매개변수 자리에 close가 필요한 인스턴스 생성문을 넣는다
* 필요한 작업을 try문 안에 넣고 catch문을 통해 예외처리를 한다.
* close는 try문 내부에서 처리하기 때문에 close는 필요하지 않다.

```
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResource {

	public static void main(String[] args) {
		// try with resource statements
		try (FileWriter f = new FileWriter("data2.txt")) {
			f.write("Hello");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
```

## 예외 만들기
* 예외가 발생했을 때 그것을 바로 처리할 수도 있지만 사용하는 쪽으로 예외를 던질 수 있다.
  * 이러한 행위를 THROW를 넣음으로써 동작하도록 할 수 있다.
  * 이런 식으로 throw를 하다가 어느 순간에 try catch를 통해 예외 처리를 진행하면 된다.

<img src="https://user-images.githubusercontent.com/125250099/224483556-7e19cfe9-4444-4a8b-b13a-1169d507872e.png" width="60%">

```
import java.io.FileWriter;
import java.io.IOException;

public class ThrowException {
	public static void main(String[] args) throws IOException {
		FileWriter f = new FileWriter("data3.txt");
	}
}
```




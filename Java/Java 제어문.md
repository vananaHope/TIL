# Java 제어문
## Boolean Data Type
* True,False로 이루어진 데이터 타입으로 참과 거짓을 판별해낸다
* 반복문, 조건문과 함께 사용될 때 엄청난 잠재력을 가지고 있다.
* contains(a)를 실행했을 때 a가 문자열에 포함되면 true 아니면 false 값이 나온다.

## 비교 연산자
* 왼쪽, 오른쪽 값을 비교하여 그 결과가 무엇이냐에 따라 true,false를 나타내는 연산자를 말한다.

## 조건문
* if(true)일 경우 if문을 실행하고 false일 경우 else에 있는 것을 실행시키거나 if문을 빠져나간다.
* if문 안에 if를 써서 중첩시킬 수 있다.
* 논리연산자 &&를 통해서 조건을 동시에 만족시켜야 if문이 실행되도록 할 수 있다. 이를 통해 if문을 간결하게 만들 수 있다.
```
  if(조건1 && 조건2) {
            System.out.println(1);
        } else if(조건3) {
            System.out.println(0);
        } else {
            System.out.println(-1);
        }
        
   반복문 내부에 제어문 쓸 때 :
       if (조건)
        	break;
        
       if (조건)
        	continue;
```
## 문자의 비교 ==와 equals의 차이점
* 원시 데이터타입 = boolean,int,double,short,long,float,char
* 원시 데이터타입이 아닌 것 = String,Array,Date,File 등
* 기본적으로 원시 데이터 타입들은 같은 데이터라면 heap 안에서 같은 메모리 공간을 가리키게 된다.  
  int p1 = 1 ---> 같은 매모리 공간을 가리킴 (p1 == p2 는 true)  
  int p2 = 1  
  이렇게 되면 == 연사자를 사용하면 p1과 p2는 같다고 인식하게 됨.    
  p1 == p2 ---> true
  
* 하지만 String은 특혜를 받고있어서 primitive가 아님에도 같은 데이터면 같은 메모리 공간을 가리킨다. (원시 데이터 타입처럼 동작한다)  
  String o3 = "java2" ---> 같은 메모리 공간을 가리킴 (o3 == o4 는 true)    
  String o4 = "java2"

* 정리하면 원시 데이터 타입은 == 사용하면 된다. equals를 가지고있지도 않다.(오류남)  
  원시 데이터 타입이 아닌 객체들에겐 equals를 사용하는게 편하다.

* 같은 데이터를 heap 안에서 다른 메모리 공간에 할당하고 싶으면 new 연산자를 사용하면 된다.  
  String ex1 = new String ("java") ---> 각각의 메모리 공간을 할당받음 (ex1 == ex2 는 false)    
  String ex2 = new String ("java")
  
## 반복문
* While문
``` 
  int i = 0;

  while(i<10){         
       System.out.println("Coding Everybody"+i);
       i++;
}
```
* For문
```
for (int i = 0; i < 10; i++) {
            System.out.println("Coding Everybody " + i);
        }
```
**특정구간을 반복만 할 경우 For문이 좋다 왜냐하면 반복에 필요한 것들이 서로 떨어져 있지 않고 하나에 모두 들어가 있기 때문이다.    
대신 While문은 자유도가 높고 활용할 수 있는 범위가 넓다.**

## 배열
* 배열 생성을 할 때는 데이터타입 뒤에 [ ]를 붙인다.
```
String[] classGroup = { "진혁", "유빈", "철수", "이고잉" };
// 혹은

String[] classGroup = new String[4];
classGroup[0] = "진혁";
classGroup[1] = "유빈";
classGroup[2] = "철수";
classGroup[3] = "이고잉";

System.out.println(classGroup.length); // 4
```
* 배열+반복문을 활용하는 예시
```
String[] classGroup = { "진혁", "유빈", "철수", "이고잉" };

For( int i = 0; i < users.length; i++ ) {
    System.out.println( users[i] + "," )
}
```
```
String[] users = {"yubin","youngjae","hyunsoo"};
		
		for(int i = 0; i < users.length; i++ ) {
			if( i < users.length-1 ) {
				System.out.println(users[i]+",");
			} else if( i < users.length) {
				System.out.println(users[i]);
			}
		}
```
## 종합 응용
```
public static void main(String[] args) {
		
		String[][] users = {
				{"yujin","1111","Seoul"},
				{"hyunsoo","2222","Incheon"},
				{"roborto","4444","Ulsan"}
		};

		String inputId = args[0];
		String inputPass = args[1];
		String Region = args[2];
				
		boolean isLogined = false;
		for( int i = 0; i < users.length; i++ ) {
			String[] current = users[i];
			if(
					current[0].equals(inputId) && current[1].equals(inputPass) && 
					current[2].equals(Region)
					
					) { 
					isLogined = true;
					break;
			}
			
		}
		System.out.println("Hi");
		if(isLogined) {
			System.out.println("Master!!");
		} else {
			System.out.println("Who are you?");
		}
		
	}

```

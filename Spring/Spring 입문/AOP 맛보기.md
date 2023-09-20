# AOP
* AOP : Aspect Oriented Programming
* 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
![image](https://github.com/vananaHope/TIL/assets/125250099/4b3d736d-9ba6-4714-834d-1a7c983f1df3)

## AOP 적용
* 모든 메서드에 대해 시간 측정을 해야 되는 상황
* 메서드는 1000개 이상일 때 모든 메서드에 시간 측정 로직을 추가?
* 위와 같은 경우 공통 관심 사항(시간 측정 로직) 과 핵심 관심 사항 (비즈니스 로직) 섞여서 유지 보수가 어려움
* 이럴 때 AOP를 사용한다.

**시간 측정 AOP**
```
@Component
@Aspect
public class TimeTraceAop {

 @Around("execution(* hello.hellospring..*(..))")
 public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
     long start = System.currentTimeMillis();
     System.out.println("START: " + joinPoint.toString());
     try {
     return joinPoint.proceed();
     } finally {
       long finish = System.currentTimeMillis();
       long timeMs = finish - start;
       System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
     }
 }
}
```
* 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
* 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
* 핵심 관심 사항을 깔끔하게 유지할 수 있다.
* 변경이 필요하면 이 로직만 변경하면 된다.
* 원하는 적용 대상을 선택할 수 있다

**AOP 동작 방식 설명**
* AOP 적용 전

![image](https://github.com/vananaHope/TIL/assets/125250099/58262156-63ba-4e3d-8d53-86a990193b8d)

* AOP 적용 후

![image](https://github.com/vananaHope/TIL/assets/125250099/135db64b-20a5-48af-8f3b-9fd113b285d0)

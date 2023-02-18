# Section 3 HTTP 기본
* 김영한님의 모든 개발자를 위한 HTTP 지식 강의를 정리한 내용입니다.

## HTTP ( HyperText Transfer Protocol )
* 클라이언트 서버 구조
* 무상태 프로토콜(스테이스리스), 비연결성
* HTTP 메시지
* 단순하고 확장이 가능함

### 기반 프로토콜
* HTTP/1.1 , HTTP/2는 TCP 기반으로 동작한다.
* HTTP/3는 UDP 기반으로 동작한다.
* **HTTP/3가 UDP 기반인 이유는?** 
  * TCP는 3 way handshake부터 내부적으로 포함되는 정보나 데이터가 많아서 연결성은 보장되지만 속도가 떨어진다.   
    따라서 UDP를 애플리케이션 레벨에서 재설계해서 나온 것인 HTTP/3이다.

### 클라이언트 서버 구조
* Request Response 구조
* 클라이언트는 요청을 보내고 응답을 대기 / 서버는 요청에 대한 결과를 만들어 전송
*  **클라이언트와 서버를 분리시키는 이유는?**
   * 각자의 역할에 집중할 수 있다.
   * 클라이언트는 비즈니스 로직이나 데이터를 다룰 필요가 없고 UI에만 집중할 수 있다.
   * 서버는 비즈니스 로직과 데이터에만 집중하면 된다. ---> 트래픽이 폭증해도 클라이언트는 신경 쓰지 않고 서버에만 집중하면 된다.

### Stateful, Stateless
* Stateful ( 상태유지 )
  * 서버가 클라이언트의 상태를 유지한다.
  * 클라이언트와 서버가 송,수신을 진행하며 단계별로 진행하는데 서버에서 클라이언트가 이전에 제공한 내용을 계속해서 저장하고 있는 것 
  * **Stateful 방식을 잘 사용하지 않는 이유는?**
    * 상태를 저장하고 있는 서버에 문제가 생기면 다른 서버들은 해당 내용을 가지고 있지 않기 때문에 에러가 발생한다. 또한, 서버 확장이 어렵다.
    * 즉, 항상 같은 서버가 유지되어야 한다. 
     
* Stateless ( 무상태 )
  * 서버가 클라이언트의 상태를 유지하지 않는다.
  * 매번 요청할 때마다 클라이언트에서 모든 정보를 전송해야한다.
  
  * 스케일 아웃 - 수평 확장 유리
    * 서버 확장이 편리하다.
    * 서버 변경이 쉽다는 것은 서버를 어느 것을 선택해도 된다는 것.
    
  <img src = "https://user-images.githubusercontent.com/125250099/219844489-30f458fd-0262-48b9-b7c8-fca6115a51f2.png" width="60%">
   

 
* **정리**
  * Stateful : 중간에 서버가 변경되면 안된다. ( 변경된다면 미리 다른 서버에 상태를 전송해야 한다. )
  * Stateless : 중간에 서버 변경이 가능하다. ( 트래픽이 폭증해도 서버 증설이 쉽다. )

* **무상태의 실무한계**
  * 모든 것을 무상태로 설계 할 수는 없다.
  * 무상태 ---> 로그인이 필요없는 단순한 이벤트 페이지
  * 상태유지 ---> 로그인 
  * 로그인한 사용자의 경우를 상태를 유지해야한다.
  * 브라우저의 쿠키와 서버 섹션을 이용해 상태를 유지한다.
  * **상태 유지는 최소한으로만 사용한다.**
  * 전송해야하는 데이터가 너무 많다.

## 비연결성 ( Connectionless )
* 연결을 유지하는 모델
<img src ="https://user-images.githubusercontent.com/125250099/219845620-c982cebd-d6cb-4e65-93bf-45ae7033c149.png" width="50%"> 
<img src ="https://user-images.githubusercontent.com/125250099/219845674-ac03ac88-f36e-4a28-82d3-b6fa573ca698.png" width="50%">

* 연결을 유지하지 않는 모델
<img src="https://user-images.githubusercontent.com/125250099/219845774-06933398-875c-4403-9ef5-d05446110ce0.png" width="50%">

* HTTP 비연결성
  * HTTP는 기본적으로 연결을 유지하지 않는다
  * 1시간동안 수천명이 서비스를 이용해도 동시에 처리하는 요청은 수십개 이하이다.
  * 서버 자원을 효율적으로 사용할 수 있다.

* 비연결성의 한계와 극복
  * TCP/IP로 새로 연결을 맺어야 한다. ( 3way handshake 시간 추가 )
  * 웹 브라우저로 요청을 하면 HTML 뿐만아니라 JS,CSS 등 다양한 것을 다운로드한다.
  * 이러한 문제를 **HTTP 지속 연결**을 통해 해결한다.
  * HTTP/2,HTPP/3에서 지속 연결은 더 최적화 되었다.

* HTTP 초기 - 연결, 종료 낭비
<img src ="https://user-images.githubusercontent.com/125250099/219846069-b60970ce-db22-44a3-b28d-b27a6ba7325a.png" width="50%">

* HTTP 지속 연결
<img src ="https://user-images.githubusercontent.com/125250099/219846115-e9db4561-aae3-4500-8f42-b44426a13136.png" width="50%">
  
  * 연결이 바로 종료되는 것이 아니라 필요한 자원들을 모두 다운로드 한 뒤에 연결이 종료된다.

* **스테이스리스를 기억하자!!!**
  * 실무 상황에서 특정한 시간대에 발생하는 대용량 트래픽을 대응해야 하는 일이 생긴다.
  * EX) 선착순 이벤트, 명절 KTX 예약, 수강 신청 등

## HTTP 메시지
**HTTP 메시지 구조**

<img src="https://user-images.githubusercontent.com/125250099/219848023-62efa9b7-79f4-445c-9cfb-6ab2ec6f42a1.png" width="60%">
  
  * 공백라인은 필수적으로 넣어야한다.

HTTP 헤더
* header-field = field-name":" OWS field-value OWS
* OWS는 띄어쓰기 허용
* field-name은 대소문자 구분이 없다

HTTP 헤더 용도
* HTTP 전송에 필요한 모든 부가정보 ( 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보 등등. )
* 필요한 경우 임의의 헤더 추가 가능

HTTP 메시지 바디 용도
* 실제 전송할 데이터
* HTML문서, 이미지, 영상, JSON 등 byte로 표현할 수 있는 모든 데이터 전송이 가능하다.


**HTTP 요청 메시지**

<img src ="https://user-images.githubusercontent.com/125250099/219848179-c569a6a0-b45d-4e6f-ad34-954c06f49c1f.png" width="60%">
 
* 시작라인 ( 요청메시지 )
  * start-line = **request-line** / status-line
  * request-line = method SP request-target SP HTTP-Version CRLF
    * SP : 공백
    * CRLF : 엔터(개행)
* 메서드
  * GET,POST,PUT,DELETE 등
  * 서버가 수행해야 할 동작을 지정한다.
    * GET : 리소스 조회
    * POST : 요청 사항 처리
* 요청대상 ( /search?q=hello&hl=ko )
  * absolute-path[?query] ( 절대경로[?쿼리] )
  * 절대경로 = "/"로 시작하는 경로
* HTTP 버전

**HTTP 응답 메시지**

<img src ="https://user-images.githubusercontent.com/125250099/219849091-270932ec-14b7-4550-8c26-347a0714afdd.png" width="60%">

* 시작라인 ( 응답메시지 )
  * start-line = request-line / **status-line**
  * status-line = HTTP-Version SP status-code SP reason-phrase CRLF
  * HTTP 상태코드
    * 200 : 성공
    * 500 : 서버 내부 오류
    * 400 : 클라이언트 요청 오류
  * 이유문구 : 상태코드를 설명하는 짧은 설명 글





















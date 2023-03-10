# Section 6 HTTP 상태코드
## 클라이언트 상태 코드
* 1xx (Informational) : 요청이 수신되어 처리 중
* 2xx(Successful) : 요청 정상 처리
* 3xx(Redirection) : 요청을 완료하려면 추가 행동이 필요
* 4xx(Client Error) : 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음.
* 5xx(Server Error) : 서버 오류, 서버가 정상 요청을 처리하지 못함
### 만약 모르는 상태 코드를 받은 경우
* 상위 상태 코드로 해석해서 처리한다.
* 299 ---> 2xx(Successful)로 해석해서 처리  
  399 ---> 3xx(Redirection)으로 해석해서 처리
* 1xx는 거의 사용되지 않는다

## 2xx 성공
### 200 OK
* 요청을 성공적으로 처리해서 응답할 경우 200 상태코드를 반환한다.

<img src ="https://user-images.githubusercontent.com/125250099/222066694-6d686594-020c-4a80-babe-a6bbef367cb2.png" width="70%">

### 201 Created
* 요청을 성공적으로 처리해서 새로운 리소스가 생성된 경우
* 주로 POST일 떄 많이 사용됨

<img src ="https://user-images.githubusercontent.com/125250099/222067045-c5db6476-3071-4509-b369-2fdb265dac25.png" width="70%">

### 202 Accepted
* 요청은 접수되었지만 아직 처리되지 않은 경우
* 배치 처리 같은 곳에서 사용 EX) 요청 접수 후 1시간 뒤에 배치 프로세스를 처리

### 204 No Content
* 서버가 요청을 성공적으로 처리했지만 응답 본문에 보낼 데이터가 없을 때
* EX) 웹 편집기의 SAVE 버튼
* SAVE 버튼을 눌러서 요청이 와도 어떤 결과를 응답할 필요가 없다.

## 3xx 리다이렉션
* 요청을 완료하기 위해서 클라이언트의 추가적인 조치가 필요한 경우
* 웹 브라우저는 3xx 응답 결과에 Location 헤더가 있는 겨우 Location 위치로 자동 이동한다(리다이렉트)

<img src ="https://user-images.githubusercontent.com/125250099/222069381-378c653a-9d6d-47cd-a31c-3f961713350b.png" width="70%">

* 리다이렉션의 종류
  * 영구 리다이렉션 - 특정 리소스의 URI가 영구적으로 이동  
    예) /members → /users  
    예) /event → /new-event
  * 일시 리다이렉션 - 일시적인 변경  
    주문 완료 후 주문 내역 화면으로 이동  
    PRG: POST/Redirect/Get
  * 특수 리다이렉션  
    결과 대신 캐시를 사용

### 영구 리다이렉션 - 301, 308
* 리소스 URI가 영구적으로 이동
* 원래 URL를 사용하지 않음, 검색 엔진에서 변경 인지
* **301 - Moved Permanently** : 리다이렉트시 메서드가 GET으로 변경되고, 본문이 변경될 수 있음(MAY)

<img src ="https://user-images.githubusercontent.com/125250099/222070574-6d5aeb2a-a93a-48b0-a746-04ff03895874.png" width="70%">

* **308 - Permanent Redirect** : 리다이렉트시 요청 메서드와 본문을 유지한다. ( POST로 보내면 리다이렉트할 때도 POST로 보냄 )

<img src ="https://user-images.githubusercontent.com/125250099/222070996-2f49105e-b5b8-4ec8-900b-ede76f5a02b8.png" width="70%">

**실무에서는 경로가 바뀌어 리다이렉션 하는 경우 메서드를 유지할 필요가 거의 없다.     
왜냐하면 페이지가 바뀌면서 필요한 정보나 파라미터들이 변경되기 때문이다. 따라서 308보다 301를 주로 사용**

### 일시적인 리다이렉션 - 302, 307, 303
* 리소스의 URI가 일시적으로 변경됨
* 따라서 검색 엔진 등에서 URL을 변경하면 안됨
* **302 Found** : 리다이렉트시 메서드가 GET으로 변경되고, 본문이 변경될 수 있음(MAY)
* **307 Temporary Redirect** : 리다이렉트시 메서드가 변경되지 않고 유지됨(MUST NOT)
* **303 See Other** : 리다이렉트시 메서드가 GET으로 변경됨

### PRG ( POST/REDIRECT/GET )
* 일시적인 리다이렉션
* PRG를 사용하지 않을 경우 주문 후에 새로고침을 해서 다시 요청을 보내게 되면 중복 주문이 될 수 있다.
* **PRG 사용 전**

<img src ="https://user-images.githubusercontent.com/125250099/222075007-990fcca4-3b1a-432b-a61e-830d74b0a6dd.png" width="70%">

* **PRG 사용 후**

<img src ="https://user-images.githubusercontent.com/125250099/222075495-08d23883-39ac-4b4e-9dd7-f73ef409858a.png" width="70%">

* POST로 주문후에 새로 고침으로 인한 중복 주문을 클라이언트에서 방지할 수 있다. 
* POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트
* 새로고침해도 결과 화면을 GET으로 조회한다. 
* 중복 주문 대신 결과 화면만 GET으로 요청

### PRG 이후 리다이렉트
* URL 이 이미 POST → GET으로 리다이렉트 됨
* 새로고침해도 GET으로 결과 화면만 조회된다.

### 302, 307, 303 중 무엇을 사용해야 하는가?
* 307,303 사용이 권장되지만 이미 많은 애플리케이션에서 302를 디폴트로 사용하고 있다.
* 자동 리다이렉션시 메서드가 GET으로 변경되어도 괜찮다면 302를 사용한다.

### 기타 리다이렉션 - 300, 304
* 300 Multiple Choices : 사용하지 않음
* 304 Not Modified
  * 캐시를 목적으로 사용한다.
  * 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 로컬PC는 저장된 캐시를 그대로 재사용한다. ( 캐시로 리다이렉트한다. )
  * 304 응답은 응답 바디에 메세지를 포함해서는 안된다. ( 로컬 캐시를 재사용해야 하므로 )
  * 조건부 GET, HEAD 요청시 사용

## 4xx 클라이언트 오류
* 클라이언트의 잘못된 요청으로 인해 서버가 요청을 처리할 수 없음
* **오류의 원인이 클라이언트에 있음**
* 클라이언트가 이미 잘못된 요청과 데이터를 보내고 있기 때문에 재시도를 해도 계속 실패함

### 400 Bad Request
* 클라이언트가 잘못된 요청을 해서 서버가 처리할 수 없는 경우
* 요청 구문, 메시지 등의 오류
* 클라이언트는 내용을 다시 검토해서 보내야 됨
* EX) 요청 파라미터가 잘못되거나 API 스펙이 맞지 않는 경우

### 401 Unauthorized
* 클라이언트가 해당 리소스에 대한 인증이 필요함
* 인증(Authentication) 되지 않음
* 401 오류 발생시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명
* 참고
  * 인증(Authentication): 본인이 누구인지 확인(로그인)
  * 인가(Authorization): 권한 부여(Admin 권한처럼 특정 리소스에 접근할 수 있는 권한, 인증이 있어야 인가가 있다.)
  * 오류 메세지가 Unauthorized 이지만 인증 되지 않음

### 403 Forbidden
* 서버의 요청을 확인했지만 승인을 거부함
* 주로 인증이 되었지만 접근 권한이 없는 경우
* 일반 사용자가 관리자 등급에 접근하려고 할 때 발생

### 404 Not Found
* 요청 리소스를 찾을 수 없을 때 발생
* 클라이언트가 권한이 부족한 리소스에 접근 했을 때 해당 리소스를 클라이언트에게 숨기고 싶을 때

## 5xx 서버 오류
* 서버 문제에 오류가 발생한 경우
* 클라이언트가 실패했더라도 재시도하면 성공할 가능성이 있다.

### 500 Internal Server Error
* 서버 문제로 오류가 발생한 경우, 애매하다면 500 오류 사용
* **정말 서버에 문제가 있을 때만 사용해야 한다. 비즈니스 로직상의 문제가 있거나 나이가 안되거나 잔고가 부족한 것처럼   
  서버가 아닌 비즈니스 조건에 문제가 되는경우 500에러를 사용해서는 안된다.**
  
### 503 Service Unavailable
* 서버가 일시적인 과부하나 예정된 작업으로 인해 요청을 처리할 수 없음
* Retry-After 헤더 필드에 얼마 뒤에 복구되는 지 보낼 수 있음
* 대부분 예측 불가능한 에러이기 때문에 503 에러를 보는 것은 흔치 않다.


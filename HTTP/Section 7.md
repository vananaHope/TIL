# Section 7 HTTP 헤더1 - 일반 헤더
## HTTP 헤더 개요
* header-field = field-name":"OWS field-value OWS (OWS: 띄어쓰기 허용)
### 용도
* HTTP 전송에 필요한 모든 부가 정보를 포함한다.    
  ---> 메시지 바디의 내용, 바디의 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 정보 등
* 필요하다면 임의의 헤더를 추가할 수 있다.

### 과거 vs 현재
* Message Body - RFC2616(과거)

<img src ="https://user-images.githubusercontent.com/125250099/222342929-59ee05b5-29c9-49d0-adb0-336b739d9edb.png" width="70%">

* 메세지 본문(message body)은 엔티티 본문(entity body)를 전달하는데 사용한다. 
* 엔티티 본문은 요청이나 응답에서 전달할 실제 데이터를 의미한다.
* 엔티티 헤더는 엔티티 본문의 데이터를 해석할 수 있는 정보를 제공한다.     
⇒ 데이터 유형(html, json), 데이터 길이, 압축 정보 등

**BUT**
```
엔티티 헤더 스펙은 1999년 RFC2616 스펙에서 나온 스펙인데, 이 스펙은 2014년 RFC7230~7235가 등장하면서 폐기된다. 
그 이후 엔티티라는 표현은 표현이라는 용어로 대체된다. 
엔티티(Entity) → 표현(Representation)
Representation = representation Metadata + Representation Data
즉, 표현 메타데이터와 표현 데이터를 합쳐 표현이라 부른다. 
```

* Message Body - RFC7230(최신)

<img src ="https://user-images.githubusercontent.com/125250099/222343492-59ca8eb4-c4c3-4fad-af83-ed0e8bbbb9c1.png" width="70%">

* 메시지 본문을 통해 표현 데이터 전달
* 메세지 본문 = Payload
* 표현은 요청이나 응답에서 전달할 실제 데이터
* 표현 헤더는 표현 데이터를 해석할 수 있는 정보를 제공한다.    
⇒ 데이터 유형(html, json), 데이터 길이, 압축 정보 등
* 참고: 표현 헤더는 표현 메타데이터와, 페이로드 메시지를 구분해야하지만, 너무 복잡해지기에 생략

## 표현
<img src ="https://user-images.githubusercontent.com/125250099/222344945-be04dc74-c735-4161-bcea-ad1871786f9e.png" width="50%">

* 클라이언트와 서버 간의 송/수신을 진행할 때 리소스를 무엇으로 표현할 지 알려주고 표현한다.     
  ( html 혹은 json으로 이 리소스를 표현할거야 )
* Content-Type : 표현 데이터의 형식
* Content-Encoding : 표현 데이터의 압축 방식
* Content-Language : 표현 데이터의 자연 언어 
* Content-Length : 표현 데이터의 길이
* 표현 헤더는 요청,응답에 모두 사용된다.

### Content-Type
* 표현 데이터의 형식을 나타낸다
<img src ="https://user-images.githubusercontent.com/125250099/222345657-9bddfc7c-57dc-483f-91bb-c61b1c5b0cb6.png" width="50%">

* 미디어 타입, 문자 인코딩 등을 나타낸다
* EX) text/html; charset=UTF-8, application/json, image/png

### Content-Enconding
* 표현 데이터의 압축 방식을 설명
<img src ="https://user-images.githubusercontent.com/125250099/222346009-bd948022-3a41-4f5c-8107-ed731f3cf37d.png" width="50%">

* 데이터를 전달할 때 압축을 한 후 인코딩 헤더를 추가해서 전송
* 데이터를 읽는 쪽에서는 인코딩 헤더를 통해 압축을 해제
  * gzip
  * deflate
  * identity ( 압축을 하지 않음 )

### Content-Language
* 표현 데이터의 자연 언어를 표현
  * ko
  * en-US
  * en
* 해당 헤더를 통해 국가별로 맞는 언어로 응답을 받을 수 있다. ( 웹 사이트에서 지원한다는 가정하에서 )

### Content-Length
* 표현 데이터의 길이
* 바이트 단위
* Transfer-Encoding ( 전송 코딩 )을 사용할 때는 Content-Length를 적으면 안된다. ( 이미 포함되어 있기 때문 )

## 콘텐츠 협상
* 클라이언트가 선호하는 표현을 서버에 요청하는 것
  * Accept : 클라이언트가 선호하는 미디어 타입을 전달
  * Accept-Charset : 클라이언트가 선호하는 문자 인코딩을 전달
  * Accept-Encoding : 클라이언트가 선호하는 압축 인코딩
  * Accept-Language : 클라이언트가 선호하는 자연 언어
* 협상 헤더는 클라이언트 요청시에만 사용된다.

### 콘텐츠 협상 예시
* **Accept-Language 적용 전**
<img src="https://user-images.githubusercontent.com/125250099/222348340-a05bd1df-da48-4902-bf05-0b818ebb87cb.png" width="70%">

* 콘텐츠 협상을 적용하지 않은 경우 한국어가 아닌 서버의 기본언어로 설정된 영어로 응답을 받는다.

* **Accept-Language 적용 후**
<img src="https://user-images.githubusercontent.com/125250099/222348894-e31681b6-4028-4c20-98fe-9ce964d1133d.png" width="70%">

* 콘텐츠 협상을 적용하면 서버에서 한국어를 지원하기 때문에 한국어로 응답을 받을 수 있다.

* **Accept-Language 복잡한 예시**
<img src="https://user-images.githubusercontent.com/125250099/222349404-cbcaca40-56e7-4890-a65f-db27f783b72e.png" width="70%">

* 클라이언트에서는 한국어를 선호하기에 Accept-Language에 한국어를 요청했다.
* 하지만 서버에서는 한국어를 지원하지 않는상황이고 기본 언어는 독일어로 되어있다. 
* 클라이언트에서는 독일어는 너무 어렵기 때문에 한국어가 안되면 영어라도 나오길 바란다면?    
⇒ 우선순위를 사용해야 한다.

### 협상과 우선순위 (1)
```
GET /event
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
```
* Quality Value(q) 값을 사용한다.
* 0~1 사이에 있는 값을 이용하며 값이 클수록 우선순위가 높다.
* 생략된 경우에는 1로 취급하며 우선순위가 가장 높다.
* **우선순위 적용 후**
<img src="https://user-images.githubusercontent.com/125250099/222350097-14c485b5-4a26-415f-8a04-37791fbac91a.png" width="70%">

* 1순위인 한국어를 지원하지 않는다 
* 2순위인 영어를 지원한다.
* 따라서 서버에서는 우선순위에 있는 영어를 응답해준다.

### 협상과 우선순위 (2)
```
GET /event
Accept: text/*, text/plain, text/plain;format=flowed, */*
```
* 구체적일수록 우선순위가 높다.
* Accept: text/*, text/plain, text/plain;format=flowed, */*
  1. text/plain;format=flowed
  2. text/plain
  3. text/*
  4. */*

### 협상과 우선순위 (3)
* 구체적인 것을 기준으로 미디어 타입을 맞춘다.
* Accept: text/*;q=0.3, text/html;q=0.7, text/html;level=1,text/html;level2;q=0.4, */*;q=0.5
<img src="https://user-images.githubusercontent.com/125250099/222352846-ac8c2d44-ae64-4312-84f5-5b1327bde50c.png" width="30%">

* text/plain은 매칭되는 것은 없지만 text/*과는 매칭되기에 0.3으로 생각하면 된다. 

## 전송 방식
* 단순 전송
* 압축 전송
* 분할 전송
* 범위 전송

### 단순 전송 Content-Length
<img src="https://user-images.githubusercontent.com/125250099/222355026-e165f4b3-be37-49af-9556-1f203956634f.png" width="70%">

* 요청을 하면 응답을 할때 메세지 바디에 대한 Content-Length를 지정하는 것
* 메세지 바디의 길이를 다 알고있을 때 사용. 
* 한번에 요청하고 응답한다. 

### 압축 전송 Content-Encoding
<img src="https://user-images.githubusercontent.com/125250099/222355405-bf672fe9-3e5d-4637-a8e9-48fc87918d75.png" width="70%">

* 서버에서 gzip을 이용해 압축을 하여 전달하는 방식
* Content-Encoding 헤더를 넣어서 어떤 방식으로 압축했는 지 알려줘야 한다.

### 분할 전송 Transfer-Encoding
<img src="https://user-images.githubusercontent.com/125250099/222355756-31acd7c0-38e9-4046-9a00-47e701657ec7.png" width="70%">

* Transfer-Encoding : chunked 는 덩어리로 쪼개서 데이터를 전송한다는 뜻
* 서버에서 클라이언트로 보낼 때 메시지를 쪼개서 전송
* 이 때 Content-Length를 넣으면 안된다. ( 쪼개서 보내기 때문에 길이를 예측하기 어렵다. )

### 범위 전송 Range,Content-Range
<img src="https://user-images.githubusercontent.com/125250099/222364185-8d3a9d34-af05-4923-ab06-8fc3a581e79d.png" width="70%">

* 용량이 큰 데이터를 받을 때 중간에 전송이 끊겼을 경우 범위를 지정해서 요청하면 특정 범위부터 응답해서 속도를 높힐 수 있다.

## 일반 정보
### From 
* 유저 에이전트의 이메일 정보
* 일반적으로 거의 사용되지 않음
* 검색엔진에서 주로 사용하며 요청에서 사용된다.

### Referer
* 현재 웹 페이지의 이전 웹 페이지 주소
* A페이지에서 B페이지로 이동하면 B를 요청할 때 Referer: A를 포함해서 요청한다.
* Referer를 사용해서 유입 경로 분석이 가능하다.
* 요청(Request)에서 사용한다.
* 참고: referer는 단어 referrer의 오타지만 이미 너무 많은곳에서 referer로 사용하고 있기에 그냥 사용하고 있다. 

### User-Agent
```
Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36
```
* 클라이언트의 애플리케이션 정보 ( 웹 브라우저 정보 등 )
* 통계 정보를 뽑아낼 때 유용하게 사용 가능
* 어떤 브라우저에서 장애가 발생하는 지 파악하기가 용이함
* 요청에서 사용됨

### Server
* 요청을 처리하는 ORIGIN 서버의 소프트웨어 정보
**참고: HTTP요청을 보내면 여러 프록시 서버를 거치게되는데, 실제로 내 요청을 받고 응답을 해주는 엔드포인트 서버를 ORIGIN 서버라 한다.**
* Server: Apache/2.2.22(Debian)
* server: nginx
* 응답(Response)에서 사용한다.

### Date
* 메세지가 발생한 시간과 날짜
* Date: Fri, 09 Apr 2021 14:41:31 GMT
* 응답(Response)에서 사용한다.

## 특별한 정보
### Host
* 요청한 호스트 정보 ( 도메인 )
* 필수 헤더
* 하나의 서버가 여러 도메인을 처리할 때 사용
* 하나의 IP주소에 여러 도메인이 적용되어 있을 때

### Host가 없을 때 생기는 문제
<img src="https://user-images.githubusercontent.com/125250099/222372972-1ca6c836-dee8-42b7-9675-b7732c8135db.png" width=30%:>

* 가상 호스트를 사용해서 여러 도메인을 한 번에 처리하는 서버가 있어서 애플리케이션을 여러 개 구동할 수 있다.

<img src="https://user-images.githubusercontent.com/125250099/222373379-9cc84cac-a602-4cf5-832b-e668627ae964.png" width="70%">

* 호스트를 사용하지 않는 경우 클라이언트의 요청을 가상 호스트를 사용하는 서버 내 어떤 도메인으로 보내야 할 지 알 수가 없다.

### Host 추가를 통해 문제 해결
<img src="https://user-images.githubusercontent.com/125250099/222373872-203d89fa-a3de-45fc-8836-52937d37b5e4.png" width="70%">

* 헤더에 호스트 정보를 넣음으로써 어떤 도메인으로 가야 할 지를 알 수 있다.

### Location
* 페이지 리다이렉션
* 3xx 응답 결과를 받은 경우 응답 결과 안에 있는 Location 헤더를 통해 자동 리다이렉션을 진행한다.
* 201(Created): Location 값은 요청에 의해 생성된 리소스 URI이다
* 3xx(Redirection): Location값은 요청을 자동으로 리디렉션 하기 위한 대상 리소스를 가리킨다.

### Allow 
* 허용 가능한 HTTP 메서드
* 일반적으로 사용되지 않음
* 405 ( Method Not Allowed ) 응답을 통해 클라이언트에게 허용되는 메서드에 대해 알려주어야 한다.

### Retry-After 
* 유저 에이전트가 다음 요청까지 기다려야 하는 시간
* 503 ( Service Unavailable ) : 서비스가 언제까지 이용 불가능한지 알려줄 수 있다
* Retry-After: Fri, 31 Dec 1999 23:59:59 GMT(날짜 표기)
* Retry-After: 120(초단위 표기)

## 인증
### Authorization
* 클라이언트 인증 정보를 서버에 전달
* Authorization: BASIC xxxxxxxxxxxxxxxxxx
  * 인증 방식은 OAuth, OAuth2, SNS로그인 등 다양한데, 그런 방식별로 들어가야하는 값이 다르다. 
  * 인증 메커니즘과는 상관없이 헤더를 제공하는 것으로 인증과 관련된 값을 넣어주면 된다.
* WWW-Authenticate
  * 리소스 접근 시 필요한 인증 방법을 정의
  * 401 Unauthorized 응답과 함께 사용
  * WWW-Authenticate: Newauth realm="apps", type=1,title="Login to \"apps\"", Basic realm="simple"    
    ⇒ 위와같은 방식으로 어떻게 인증을 해야할지를 정의해 알려준다.
    
## 쿠키
* Set-Cookie : 서버에서 클라이언트로 쿠키 전달
* Cookie : 클라이언트가 서버에게 받은 쿠키를 저장, HTTP 요청시 서버에 전송

### 주 사용 목적
* 세션 관리(Session Management)    
⇒ 서버에 저장해야 할 로그인, 장바구니, 게임 스코어등의 정보 관리
* 개인화(Personalization)    
⇒ 사용자 선호, 테마 등의 세팅
* 트래킹(Tracking)    
⇒ 사용자 행동을 기록하고 분석하는 용도

### 쿠키를 사용하지 않았을 때
<img src="https://user-images.githubusercontent.com/125250099/222381477-2f30f019-ae5f-4fe6-8d8c-77973b654b42.png" width="70%">

* 클라이언트에서 POST로 로그인
* 로그인 후 다시 Welcome 페이지로 이동
* 하지만 로그인 한 사용자명이 표시되지 않음

<img src="https://user-images.githubusercontent.com/125250099/222382451-a69ffecf-012a-431d-8edb-2d5336bf7835.png" width="70%">

* HTTP는 무상태 프로토콜이기 때문에 다운로드가 끝나면 연결을 끊어버림
* 따라서 로그인 이후에 Welcome 페이지 요청을 보내도 서버는 로그인 한 유저의 요청인지 알 수가 없음    
  ---> **쿠키를 사용함으로써 이 문제를 해결할 수 있음**
  
### 쿠키 사용
<img src="https://user-images.githubusercontent.com/125250099/222383224-d07531e4-cbaa-4803-b570-fe67e2a3fd24.png" width="70%">

* 클라이언트에서 로그인을 요청하며 데이터를 보내면 서버에서는 Set-Cookie로 로그인 정보를 담아 응답한다. 
* 웹브라우저는 내장된 쿠키 저장소에 Set-Cookie에 있는 정보를 저장한다. 
* 그래서 로그인 이후 welcome 페이지에 접근하면 쿠키를 조회해서 쿠키값을 Cookie에 담아서 보낸다.

<img src="https://user-images.githubusercontent.com/125250099/222383482-8700eb5c-56d7-46a6-9542-4a393d7b04f4.png" width="70%">

### 쿠키 사용처 및 주의사항
```
set-cookie: sessionId=abcde1234; expires=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=.google.com; Secure
```
* 사용처
  * 로그인 세션 관리
  * 광고 트래킹

* 쿠키는 항상 서버에 전송된다.
  * 추가적인 네트워크 트래픽을 유발
  * 따라서 최소한의 정보만 담아야 한다. (세션id, 인증 토큰)
  * 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지 이용

* 주의사항
  * 보안과 관련된 민감한 정보는 포함되면 안된다. ( 주민번호, 신용카드번호 등 )

### 쿠키의 생명주기
* Set-Cookie: expires=Sat, 26-Dec-2020 04:39:21 GMT    
⇒ 만료일이 되면 쿠키는 삭제된다.
* Set-Cookie: max-age=3600(3600초)    
⇒ 0이나 음수로 지정할 경우 쿠키는 삭제된다
* 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시까지만 유지
* 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지

### 쿠키 도메인 지정
```
domain=example.org
```
* 명시 : 명시한 문서 기준의 도메인 + 서브 도메인 포함
  * domain=example.org를 지정해서 쿠키 생성
    * example.org는 물론이고
    * dev.example.org도 쿠키 접근

* 생략 : 현재 문서 기준 도메인만 적용
  * example.org에서 쿠키를 지정하고 도메인 지정 생략
    * example.org는 접근 가능
    * dev.example.org는 접근 불가능

### 쿠키 경로
* 작성한 경로를 포함한 하위 경로 페이지에서만 쿠키 접근이 가능하다. 
* 일반적으로 path=/ 루트로 지정한다
* 해당 도메인의 하위 경로 모두 쿠키를 사용하기를 바라기 때문    
예)    
⇒ path=/home 지정    
⇒ /home/level1 접근 가능     
⇒ /home/level1/level2 접근 가능    
⇒ /hello 불가능     

### 쿠키 보안
* Secure
  * 쿠키는 http, https 가리지 않고 전송한다.
  * Secure을 넣을 경우 https에만 쿠키를 전송한다.

* HttpOnly
  * XSS공격방지
  * 자바스크립트에서 접근 불가능
  * HTTP 전송에만 사용됨

* SameSite
  * XSRF공격방지
  * 요청 도메인과 쿠키의 도메인이 일치할 때만 쿠키를 전송함


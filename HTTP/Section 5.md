# Section 5 HTTP 메서드 활용

## 클라이언트에서 서버로 데이터 전송
* 쿼리 파라미터를 이용한 전송
  * GET에서 주로 사용되고 검색어나 정렬을 할 때 사용된다.
* 메시지 바디를 통한 전송
  * POST,PUT,PATCH에서 사용되며 회원 가입, 상품 주문과 같은 리소스 등록이나 변경에 사용된다.

## 클라이언트에서 서버로 데이터를 전송하는 4가지 상황
### 정적 데이터 조회
  * 이미지나 정적인 텍스트 문서
  * 쿼리 파라미터를 사용하지 않는다

<img src ="https://user-images.githubusercontent.com/125250099/220872823-c9ca5cf5-82fa-4765-8b52-40a77d42259f.png" width="70%">

### 동적인 데이터 조회
  * 검색, 게시판 목록에서 정렬 필터를 하는 경우
  * 쿼리 파라미터를 사용해야한다.
  * 조회는 GET를 사용하며 쿼리 파라미터를 통해 데이터 전달

<img src ="https://user-images.githubusercontent.com/125250099/220873095-3a49d2cd-24cb-4ca5-bce3-304afd469f09.png" width="70%">

### HTML Form을 통한 데이터 전송
  
  <img src ="https://user-images.githubusercontent.com/125250099/220879023-0ed36539-3c47-40bf-b4a6-41fa843f713a.png" width="70%">
  
  * 웹 브라우저는 submit 버튼을 누르면 Form 태그의 정보를 바탕으로 action에 작성된 경로로 해당 method 타입의 요청을 하게 되는데, Content-Type을 보면 x-www-form-urlencoded 라고 되어있다
  * 쿼리 파라미터와 비슷한 방식으로 메시지가 변환되어 전송된다. ---> username=kim&age=20
  * 한글 같은 경우 인코딩되어 넘어간다. ( urlencoded가 인코딩되었다는 뜻 )
  * GET은 조회에만 사용해야 하며 위와 같은 경우에는 GET를 사용하면 안된다. ( 리소스 변경과 관련 있기 때문 )
  
**HTML Form에서 이미지나 파일을 전송해야하는 경우에는 어떻게 해야 하는가?**
<img src ="https://user-images.githubusercontent.com/125250099/220881687-5e767841-3405-428b-a29f-9e8871091600.png" width="70%">

  * enctype에 multipart/form-data를 적어 파일이나 이미지를 전송한다는 것을 나타낸다
  * multipart/form-data 형식이면 웹 브라우저에서 임의의 경계선을 사용해서 파일과 이미지 등의 데이터를 나누어준다.
  * HTML Form은 GET,POST만 지원한다.

### HTML API를 통한 데이터 전송
  * 서버 간 통신 혹은 앱 클라이언트, 웹 클라이언트(AJAX) 등에서 사용된다.
  * 서버 to 서버
    * 백엔드 서버 간의 통신
  * 앱 클라이언트
    * 아이폰, 안드로이드
  * 웹 클라이언트
    * HTML Form 대신 자바스크립트를 활용한 AJAX 통신에 사용 ( React, Vuejs 등의 웹 클라이언트 통신 )
  * GET : 조회, 쿼리 파라미터를 통한 전송
  * PUT,PATCH,DELETE : 메시지 바디를 통해 데이터 전송

## HTTP API 설계 예시
* HTTP API - 컬렉션
  * POST 기반 등록
  * 회원 관리 API제공
* HTTP API - 스토어
  * PUT 기반 등록
  * 정적 컨텐츠 관리, 원격 파일 관리
* HTML FORM 사용  
  * 웹 페이지 회원 관리
  * GET, POST만 지원

### API 설계 - POST 기반 
* 회원 목록 /members → GET
⇒ 회원 정렬조건이나 검색 조건이 필요하면 쿼리 파라미터를 설계하면 된다.
* 회원 등록 /members → POST
* 회원 조회 /members/{id} → GET
⇒ 계층적 구조로 되어있어 컬렉션 안의 특정 아이디를 조회하기 쉽다. 
* 회원 수정 /members/{id} → PATCH, PUT, POST
⇒ PUT은 덮어쓰기고 PATCH는 부분적 업데이트이며 회원 정보 수정은 PATCH를 주로 이용한다.
하지만, 수정이 전부 다 변경해줘야 하는 경우(ex: 게시판 글 수정하기)에는 PUT을 쓰는게 좋을수도 있다.
* 회원 삭제 /members/{id} → DELETE

### POST 신규 등록 특징
* 클라이언트는 리소스 URI에 대해 전혀 알지 못한다.
  * 회원 등록 /members → POST
  * POST /members
* 서버가 클라이언트의 요청에 따라 URI를 생성하여 응답한다.
* 컬렉션 ( Collection )
  * 서버가 관리하는 리소스 디렉토리
  * 서버가 URI를 생성하며 관리한다.
  * /members가 서버가 관리하는 컬렉션

### API 설계 - PUT 기반
* 파일 목록 /files → GET
* 파일 조회 /files/{filename} → GET
* 파일 등록 /files/{filename} → PUT
* 파일 삭제 /files/{filename} → DELETE
* 파일 대량 등록 /files → POST

### PUT - 신규 등록 특징
* 클라이언트가 리소스의 URI를 알고 있어야 한다.
  * 파일 등록 /files/{filename} → PUT
  * PUT /files/**star.jpg**
* 클라이언트가 URI를 직접 지정하며 관리한다
* 스토어 ( Store )
  * 클라이언트가 관리하는 리소스 저장소
  * 클라이언트가 URI를 알고 관리한다.
  * /files 가 스토어에 해당

**POST,PUT 둘 중 가장 많이 사용되는 곳은?**
* 실무에서 POST를 대부분 사용하며 PUT은 파일 업로드와 같은 특수한 경우에만 사용된다.

### HTML Form 사용
* GET,POST만 사용할 수 있어 제약이 있다.
* AJAX와 같은 기술을 통해 제약을 해결할 수 있다.
* 순수한 HTML FORM에서는 GET,POST만 이용 가능

### HTML FORM 설계 
* 회원 목록 /members → GET
* 회원 등록 폼 /members/new → GET
⇒ 회원등록 버튼을 누르면 회원 등록 폼으로 이동
* 회원 등록 /members/new, /members → POST
⇒ /members/new 로 폼을 가져오는 경로와 회원 등록 경로를 동일하게 하는 방식과, 폼을 가져올때는 /new 를 통해 가져오지만 회원 등록은 /members 으로 하는 방식도 있다.
⇒ 전자(경로를 맞추는 상황)로 사용을하면 Validation 작업으로 새로고침되어 동일페이지에 가야하는경우 간단하게 이전(동일) 페이지로 이동할 수 있다.
* 회원 조회 /members/{id} → GET
* 회원 수정 폼 /members/{id}/edit → GET
* 회원 수정 /members/{id}/edit, /members/{id} → POST
* 회원 삭제 /members/{id}/delete → POST
⇒ 삭제인데 POST 메소드를 사용한다. HTML FORM에서는 GET, POST만 사용할 수 있다는 제약때문에 어쩔수 없이 URI 경로에 삭제하겠다는 행위를 작성함으로써 기능을 구현한다. 이런 URI를 컨트롤 URI라 한다.

**컨트롤 URI**
* GET,POST만 사용하기 때문에 HTML FORM은 제약이 있음
* 이를 해결하기 위해 동사로 된 리소스를 사용 EX) /MEMBERS/ID/DELETE,EDIT,NEW 등
* HTTP 메서드를 사용하기 애매한 경우에도 사용 ( HTTP API를 사용하는 경우에도 컨트롤 URI를 사용하며 실무에서 이러한 경우가 매우 많다. )

## 참고하면 좋은 URI 설계 개념
* 문서(document)
  * 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)  
    EX) /members/100, /file/star.jpg
* 컬렉션(collection)
  * 서버가 관리하는 리소스 디렉토리
  * 서버가 리소스의 URI를 생성하고 관리  
    EX) /members
* 스토어(store)
  * 클라이언트가 관리하는 자원 저장소
  * 클라이언트가 리소스의 URI를 알고 관리  
    EX) /files
* 컨트롤러(controller), 컨트롤 URI
  * 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
  * 동사를 사용한다.  
    EX) /members/{id}/delete


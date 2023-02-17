# Section 2 URI와 웹 브라우저 요청 흐름
* 김영한님의 모든 개발자를 위한 HTTP 지식 강의를 정리한 내용입니다.

## URL ( Uniform Resource Locator )
* 리소스의 위치를 지정
* scheme://[userinfo@]host[:port][/path][?query][#fragment]
  * scheme : 프로토콜을 주로 사용하며 생략이 가능하다.
  * userinfe : URL에 사용자 정보를 포함하여 인증하는 부분이지만 거의 사용되지 않는다.
  * host : 호스트명 / 도메인명, ip를 적는 부분이다.
  * port : 포트를 입력하며 http는 80, https는 443 포트를 사용한다. 일반적으로 생략한다.
  * path : 리소스의 경로를 입력하며 계층적 구조로 이루어져 있다. ex) /home/file.jpg
  * query : key=value 형태로 이루어져 있으며 ?로 시작하여 &로 추가할 수 있다. / query parameter , query string 등으로 불린다. / 문자 형태로 전송되며 숫자를 적어도 문자로 전송된다
  * fragment : http 내부 북마크에 이용되며 서버에 전송되는 정보가 아니다.

## 웹 브라우저 요청 흐름
1. DNS 조회 
2. HTTP 요청 메시지 생성
<img src = "https://user-images.githubusercontent.com/125250099/219564115-89850cfc-be2d-4f27-a014-3402f82dae55.png" width="80%">
3. HTTP 요청 메시지 전송
<img src = "https://user-images.githubusercontent.com/125250099/219565179-c8cfbdb4-fb24-429b-9ebf-7eba02df4279.png" width="80%">
4. HTTP 메세지가 포함된 TCP/IP 패킷을 생성
<img src = "https://user-images.githubusercontent.com/125250099/219565517-01d8bf58-5430-40c9-8131-1433e6d71e5d.png" width="80%">
5. 서버는 도착한 메세지를 해석해서 응답 메세지를 생성
<img src = "https://user-images.githubusercontent.com/125250099/219565904-a4383c77-bce2-4dac-8d4a-69e92e5cb91d.png" width="80%">
6. 클라이언트는 응답 메세지를 통해 HTML 렌더링을 진행

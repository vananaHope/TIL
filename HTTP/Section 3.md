# Section 3 HTTP 기본
* 김영한님의 모든 개발자를 위한 HTTP 지식 강의를 정리한 내용입니다.

## HTTP ( HyperText Transfer Protocol )
* 클라이언트 서버 구조
* 무상태 프로토콜(스테이스리스), 비연결성
* HTTP 메시지
* 단순하고 확장이 가능함

#### 기반 프로토콜
* HTTP/1.1 , HTTP/2는 TCP 기반으로 동작한다.
* HTTP/3는 UDP 기반으로 동작한다.
* **HTTP/3가 UDP 기반인 이유는?** 
  * TCP는 3 way handshake부터 내부적으로 포함되는 정보나 데이터가 많아서 연결성은 보장되지만 속도가 떨어진다.   
    따라서 UDP를 애플리케이션 레벨에서 재설계해서 나온 것인 HTTP/3이다.


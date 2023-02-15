# Section 1 인터넷 네트워크
* 김영한님의 모든 개발자를 위한 HTTP 지식 강의를 정리한 내용입니다.

## IP(인터넷 프로토콜)
* 인터넷 프로토콜의 역할
  * 지정한 IP 주소에 데이터 전달
  * 패킷(Packet)이라는 통신 단위로 데이터 전달

* IP 프로토콜의 한계 :패킷이 소실되거나 중간 노드 혹은 받는 서버의 문제가 생겨도 일단 보낸다는 문제점이 있다.
  * 비연결성 ( 받는 쪽의 서버가 어떤 상태인지 확인하지 않고 보냄 )
  * 비신뢰성 ( 순서가 바뀔 수 있기 때문에 신뢰하기 어려움 )
  * 프로그램의 구분 ( 어떤 프로그램이 패킷을 보냈는 지 구분이 불가능 )

* 이를 보완하기 위해 TCP UDP가 필요하다!

## TCP, UDP
* TCP
  ** 연결 지향 ( TCP 3way handshake, SYN과 ACK를 통해 서로 확인이 가능하다. ) **
  * 데이터 전달을 보증해주며 데이터 순서가 변경되지 않도록 도와준다.
  * 대부분 TCP를 사용하고 있다. 

* UDP
  * IP와 비슷하지만 PORT와 체크섬 기능을 추가로 제공한다.
  * PORT란? 같은 IP내에서 프로세스를 서로 구분하도록 도와준다. EX) IP = 아파트 / PORT = 동, 호수
  * 데이터 전달과 순서가 보증되지 않지만 빠른 전달이 가능하다.

## DNS ( 도메인 네임 시스템 )
* 전화번호부의 역할을 하며 기억하기 어려운 IP주소 대신 도메인을 이용하여 사용자가 쉽게 접속할 수 있도록 도와준다. EX) naver.com / google.com
* 도메인명을 IP주소로 변환시키는 역할
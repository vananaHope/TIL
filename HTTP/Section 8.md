# HTTP 헤더2 - 캐시와 조건부 요청
## 캐시 기본 동작
### 캐시가 없을 때 - 첫 번째 요청
<img src="https://user-images.githubusercontent.com/125250099/223025878-59f4e6c9-5656-455d-9370-7e9f5fc087c2.png" width="70%">

### 캐시가 없을 때 - 두 번째 요청
<img src="https://user-images.githubusercontent.com/125250099/223025961-16e2b414-b3a5-4b58-b277-c4f2b73d5787.png" width="70%">

* 데이터가 변경되지 않아도 새 데이터를 계속 다운 받는다.
* 추가 네트워크 트래픽이 발생
* 웹 브라우저 로딩 속도가 느려진다

### 캐시 적용 - 첫 번째 요청
<img src="https://user-images.githubusercontent.com/125250099/223026153-c7f4d415-2b5f-48af-8b70-9f3a82d5e4fc.png" width="70%">

* 헤더에 cache-control를 넣어서 유효시간을 설정해준다.
* 유효시간동안 브라우저에 캐시가 저장된다.
<img src="https://user-images.githubusercontent.com/125250099/223026334-f8e4daf7-dc95-487d-92a9-19bcf8ac8025.png" width="40%">

### 캐시 적용 - 두 번째 요청
<p align="left">
<img src="https://user-images.githubusercontent.com/125250099/223026501-36e853db-6c35-42c6-927d-df0b3a8a2319.png" width="30%">                                         <img src="https://user-images.githubusercontent.com/125250099/223026507-8e6b58af-3b09-4883-bcfe-cfac478807b0.png" width="30%">
</p>    

* 두 번째로 요청할 때 브라우저 캐시를 먼저 조회한다.
* 유효 시간이 지나지 않았다면 해당 캐시를 가져온다.

### 캐시 적용
* 캐시 유효 시간동안 네트워크를 사용하지 않아도 된다.
* 브라우저 로딩 속도가 빠르다.
* 비싼 네트워크 사용량을 줄일 수 있다.
* 사용자에게 빠른 경험을 제공한다.

### 유효시간이 지난 경우
* 특정 자료가 필요해 요청을 해야하는 경우 캐시를 조회할 때 유효시간이 지났지만, 변경이 없기때문에 해당 데이터를 써도 되는 상황이라면      
  **검증헤더와 조건부 요청**을 사용해야 한다.
  
## 검증 헤더와 조건부 요청 1  
### 캐시 시간 초과
* 캐시 만료후에도 서버에서 데이터를 변경하지 않은 경우 서버에서 동일한 데이터를 요청해서 응답받는 것은 비효율적이다.   
  이럴때는 저장해 둔 캐시를 재사용 할 수 있으면 좋은데, 어떻게 클라이언트의 데이터와 서버의 데이터가 동일하다는 것을 알 수있는지에 대해 알아야 한다.    
  그래서 **검증 헤더**가 들어가게 된다.
  
<img src="https://user-images.githubusercontent.com/125250099/223030158-6225bd0f-f227-4a3d-9833-2161a7fdb5c9.png" width="70%">

### 검증 헤더 - 첫 번째 요청
<img src="https://user-images.githubusercontent.com/125250099/223030188-a78f56e6-1652-48ef-ae2b-6bfab3c0c5d0.png" width="70%">

* 데이터가 마지막으로 수정된 날짜와 시간을 헤더에 포함해서 전송한다.   
  ⇒ Last-Modified: 2020년 11월 10일 10:00:00   
  ⇒ UTC 표기법으로 적어준다.     
* 응답 결과를 브라우저 캐시에 저장할 때 최종 수정일도 같이 저장된다.
<img src="https://user-images.githubusercontent.com/125250099/223030198-91f89a0f-f1d7-48d9-93fb-2684b35e45e7.png" width="30%">

### 검증 헤더 - 두 번째 요청 ( 캐시 시간 초과 )
<img src="https://user-images.githubusercontent.com/125250099/223030204-50cce023-c79b-46ca-abcd-a29e73422637.png" width="70%">

* 캐시 유효 시간이 지나서 서버에 다시 요청할 때 if-modified-since 라는 조건부 요청을 담아서 보낸다.
* 서버에 최종 수정일과 비교하여 변경되지 않았다면 응답 결과로 변경되지 않았음을 알려준다.
<img src="https://user-images.githubusercontent.com/125250099/223030733-41afed16-5d93-4564-adf9-6f956d6c9249.png" width="70%">

* 304 Not Modified 응답을 할 때는 HTTP BODY에 데이터가 있어서는 안된다.
* HTTP BODY가 빠졌기 때문에 0.1M에 해당하는 헤더만 전송된다.
* 클라이언트에서는 해당 응답을 받은 뒤 캐시를 갱신해주고 다시 일정시간(60초) 유효하게 된다.

### 정리
* 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면   
  304 Not Modified + 헤더 메타 정보만 응답한다. 
* 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보를 갱신한다.
* 클라이언트는 캐시에 저장되어 있는 데이터 재활용
* 결과적으로 네트워크 다운로드가 발생하지만 용량이 적은 헤더 정보만 다운로드받으면 된다.
* 매우 실용적인 해결책

## 검증 헤더와 조건부 요청 2
* 검증헤더란 캐시데이터와 서버 데이터가 같은지 검증하는 데이터로 Last-Modified, ETag가 있다.
* If-Modified-Since: Last-Modified 사용
* If-None-Matach: ETag 사용
* 조건이 만족하면 200 OK
* 조건이 만족하지 않으면 304 Not Modified

### If-Modified-Since 이후에 데이터가 수정이 되었다면?
* 데이터 미변경인 경우
  * 캐시 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 10:00:00
  * 304 Not Modified, 헤더 데이터만 전송(Body 없음)
  * 전송 용량 0.1M(헤더 0.1M, 바디 1.0M)
* 데이터가 변경된 경우
  * 캐시: 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 11:00:00
  * 200 OK, 모든 데이터 전송(Body 포함)
  * 전송 용량 1.1(헤더 0.1M, 바디 1.0M)

### Last-Modified, If-Modified-Since 의 단점
* 1초 미만(0.x초)단위로 캐시 조정이 불가능하다.
* 날짜 기반의 로직을 사용한다.
* 데이터를 수정해 날짜가 다르지만, 같은 데이터를 수정해 데이터 결과가 똑같은 경우
  * test.txt 파일의 내용을 A→B로 수정했지만, 다시 B→ A로 수정한 경우
* 서버에서 별도의 캐시 로직을 관리하고 싶은 경우
  * 스페이스나 주석처럼 크게 영향이 없는 변경에서 캐시를 유지하고 싶은 경우 

### ETag, If-None-Match
* 서버에서 캐시를 컨트롤하고 싶은 경우 ETag 를 사용
* ETag ( Entity Tag )
  * 캐시용 데이터에 임의의 고유 이름을 붙인다.
    * ETag: "v1.0", ETag: "a2jiodwjekjl3"
  * 데이터가 변경되면 이름을 바꿔서 저장한다.
    * ETag:"aaaa" → ETag:"bbbb"
  * ETag를 전송해서 같으면 유지하고 다르면 서버로부터 다시 받는다.

### ETag 활용 - 첫 번째 요청
<img src="https://user-images.githubusercontent.com/125250099/223040993-41a3012d-ac00-4603-8b28-17c4dd561230.png" width="70%">

* 헤더에 ETag를 포함해서 응답
* 브라우저 캐시에 ETag를 저장
<img src="https://user-images.githubusercontent.com/125250099/223041000-9b4bee74-f445-49d9-81d8-648e312c7c95.png" width="30%">

### ETag 활용 - 두 번째 요청 ( 캐시 시간 초과 )
<img src="https://user-images.githubusercontent.com/125250099/223041006-9ed88a51-d6fc-4630-b592-a4e7c6ca9bdf.png" width="70%">

* 캐시 유효 시간이 지나서 다시 보낼 때 if-none-match 헤더를 추가해서 서버에 보낸다.

<img src="https://user-images.githubusercontent.com/125250099/223041008-2c21f219-ab3b-43ce-95cb-ae6b34a0dd7a.png" width="70%">

* 서버에서 데이터가 변경되지 않았을 경우 ETag는 동일하다. 따라서 If-None-Match는 실패이다.
* 이 경우 서버에서는 304 Not Modified를 응답하며 이때 HTTP Body는 포함하지 않는다. 
* 브라우저 캐시에서는 응답 결과를 재사용하고 헤더 데이터를 갱신한다.

<img src="https://user-images.githubusercontent.com/125250099/223041055-686af903-089c-4a38-929f-796061ce28c9.png" width="30%">

### 정리
* ETag가 동일하면 유지하고 다르면 새로 서버로부터 받는다.
* 캐시 제어 로직을 서버에서 관리한다.
* 클라이언트는 캐시 로직을 알 필요가 없다.

## 캐시와 조건부 요청 헤더
### 캐시 제어 헤더 
* 캐시 지시어
  * Cache-control:max-age ( 캐시 유효 시간을 초 단위로 지정)
  * Cache-control:no-cache ( 데이터는 캐시로 저장해도 되지만 무조건 사용할 때 원(Origin)서버에 검증하고 사용 )
  * Cache-control:no-store ( 민감한 정보이기 때문에 데이터에 저장하면 안된다. / 메모리에서 사용하고 빨리 삭제해야 한다. )

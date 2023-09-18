# GitHub 시작
* https://github.com

## 가입하고 토큰 생성
* 회원가입 및 로그인
* Personal Access Token 생성
  * 우측 상단의 프로필 - Settings
  * Developer Settings
  * Personal access tokens - Generate new token
  * repo 및 원하는 기능에 체크, 기간 설정 뒤 Generate token
  * 토큰 안전한 곳에 보관해 둘 것
* 토큰 컴퓨터에 저장
  * Windows 자격 증명 관리자
  * Windows 자격 증명 선택
  * git:https://github.com 자격 정보 생성
  * 사용자명과 토큰 붙여넣기
 
## 원격 저장소 사용하기
* HTTP 프로토콜 사용

### GitHub 레포지토리 생성 후 복붙 명령어
```
git remote add origin (원격 저장소 주소)
```
* 로컬 Git 저장소에 원격 저장소로의 연결 추가
  * 원격 저장소 이름에 흔히 origin 사용 / 다른 것으로 수정 가능
* GitHub 권장 - 기본 브랜치명을 main으로
```
git branch -M main
```
* 로컬 저장소의 커밋 내역들 원격으로 push (업로드)
  * -u 또는 --set-upstream : 현재 브랜치와 명시된 원격 브랜치 기본 연결
```
git push -u origin main
```
* 원격 목록 살펴보기
```
git remote
```
* 원격 지우기 (로컬 프로젝트와의 연결만 없애는 것, 레포지토리는 삭제되지 않음)
```
git remote remove (origin 등 원격 이름)
```

### GitHub에서 프로젝트 다운
* 터미널이나 Git Bash에서 대상 폴더 이동 후
```
git clone (원격 저장소 주소)
```

# fetch VS pull
* fetch : 원격 저장소의 최신 커밋을 로컬로 가져오기만 함
* pull : 원격 저장소의 최신 커밋을 로컬로 가져와 merge 또는 rebase

## fetch한 내역 적용 전 살펴보기
* 원격 main 브랜치에 커밋 추가
  * git checkout origin/main으로 확인하기
* 원격의 변경사항 fetch
  * git checkout origin/main으로 확인
  * pull로 적용

## 원격의 새 브랜치 확인
* git checkout origin/브랜치명
* git switch -t origin/브랜치명

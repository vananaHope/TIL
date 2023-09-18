# Branch
![image](https://github.com/vananaHope/TIL/assets/125250099/fd702db7-976d-4fbe-8a2c-660c75d160a3)

## Branch : 분기된 가지(다른 차원)
* 프로젝트를 하나 이상의 모습으로 관리해야 할 때
  * 예) 실배포용, 테스트용, 새로운 시도용
* 여러 작업들이 각각 독립되어 진행될 때
  * 예) 신기능 1, 신기능 2, 코드 개선, 긴급 수정...
  * 각각의 차원에서 작업한 뒤 확정된 것을 메인 차원에 통합

## Branch 생성 / 이동 / 삭제
* 브랜치 생성
```
git branch add-coach
```
* 브랜치 목록 확인
```
git branch
```
* 브랜치 이동
```
git switch add-coach
```
* 브랜치 생성과 동시에 이동하기
```
git switch -c new-teams
```
* 브랜치 삭제하기
```
git branch -d (삭제할 브랜치명)
```
* 브랜치 강제 삭제
  * 지워질 브랜치에만 있는 내용의 커밋이 있을 경우 
```
git branch -D (강제삭제할 브랜치명)
```

## 브랜치 합치기
* **Merge** : 두 브랜치를 한 커밋에 이어붙인다.
  *  브랜치 사용내역을 남길 필요가 있을 때 적합한 방식입니다
* **Rebase** : 브랜치를 다른 브랜치에 이어붙입니다.
  * 한 줄로 깔끔히 정리된 내역을 유지하기 원할 때 적합합니다.
  * 이미 팀원과 공유된 커밋들에 대해서는 사용하지 않는 것이 좋습니다.

### Merge

![image](https://github.com/vananaHope/TIL/assets/125250099/0bee5acb-9741-4742-8951-72452eeecfbd)

```
git merge add-coach
```
* merge는 reset으로 되돌리기 가능
  * merge도 하나의 커밋
  * merge 하기 전 해당 브랜치의 마지막 시점으로
* 병합된 브랜치는 삭제

### Rebase

![image](https://github.com/vananaHope/TIL/assets/125250099/3c2ba8c1-f608-4b70-9aec-7680a0427839)
```
git rebase main
```
* main 브랜치로 이동 후 아래 명령어로 new-teams의 시점으로 fast-forward
```
git merge new-teams
```
* new-teams 브랜치 삭제

## 충돌 해결하기

### merge 충돌 해결하기
* 당장 충돌 해결 어려울 경우
```
git merge --abort
```
* 해결 가능 시 충돌 부분을 수정한 뒤 git add ., git commit 등으로 병합하여 충돌 해결

### rebase 충돌 해결하기
* 당장 충돌 해결 어려울 경우
```
git rebase --abort
```
* 해결 가능 시
  * 충돌 부분을 수정한 뒤 git add .
  * 아래 명령어로 rebase 계속하기
```
git rebase --continue
```
  * 충돌이 모두 해결될 때까지 반복

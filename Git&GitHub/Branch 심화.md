# Branch 심화

![image](https://github.com/vananaHope/TIL/assets/125250099/1dfa5a23-888a-4b5f-b3da-8bea36b6defc)

## 다른 브랜치의 원하는 커밋 가져오기
* cherry-pick 명령어 사용
```
git cherry-pick (커밋 해시)
```

## 다른 브랜치에서 파생된 브랜치 가져오기
* rebase --onto 옵션 사용
* fruit 브랜치에서 파생된 citrue 브랜치를 main 브랜치로 옮기기
```
git rebase --onto (도착 브랜치) (출발 브랜치) (이동할 브랜치)
```
* git rebase --onto main fruit citrus

## rebase --onto 되돌리기
![image](https://github.com/vananaHope/TIL/assets/125250099/32a88c57-b57d-478a-a629-b0224cb7bd7e)

* rebase --onto는 여러 동작들을 포함하고 있다.
* 따라서 rebase --onto로 영향 받은 모든 브랜치들에서 하나하나 리셋을 진행해야 한다.

### main 브랜치
main은 그리로 옮겨붙여진 citrus로 fast-forward된 것이 마지막 액션이므로
**reflog**의 기록상에서 그 이전 기록으로 **reset --hard**를 하면 된다.
lemon과 lime이 추가되기 전으로 돌아가게 된다.

### citrus 브랜치
**방법 1**

citrus 브랜치는 해당 브랜치가 옮겨지기 전 마지막 커밋인
**commit: Lime** 부분을 **reflog**에서 찾아 그리로 **reset --hard**하면 된다.

**방법 2**

다시 rebase --onto를 사용해서 citrus의 커밋들을 main으로부터 도로 fruit브랜치의 orange 부분으로 옮기는 것이다.

orange 커밋으로 checkout 한 다음 그곳에서 새 브랜치를 만들고 (temp라 가정)

```
git rebase --onto temp main citrus
```

위 명령어로 citrus의 두 커밋들을 해당 위치로 옮겨붙인 뒤 temp 브랜치는 삭제해주시면 된다.

## 다른 커밋들을 하나로 묶어서 가져오기
* merge --squash 옵션 사용

### root 브랜치의 마디들을 하나로 묶어 main 브랜치로 가져오기
```
git merge --squash (대상 브랜치)
```
* 변경사항들 스테이지 되어 있음
* git commit 후 메시지 입력

### 일반 merge와의 차이점
일반 merge와 merge --squash는 실행 후 코드 상태는 같지만 내역 면에서 큰 차이가 있다.

* 일반 merge : A와 B 두 브랜치를 한 곳으로 이어 붙임
* merge --squash : B 브랜치의 마디들을 복사해서 한 마디로 모아 A 브랜치에 붙임 (staged 상태로)

## Gitflow
* 협업을 위한 브랜칭 전략을 뜻함

![image](https://github.com/vananaHope/TIL/assets/125250099/2028cad3-eb06-4889-ab48-e8fc69190c30)

![image](https://github.com/vananaHope/TIL/assets/125250099/0c147129-ee46-4bf3-b2cc-c0d1da83de1a)


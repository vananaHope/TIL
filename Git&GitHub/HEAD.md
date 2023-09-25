# HEAD
![image](https://github.com/vananaHope/TIL/assets/125250099/83851be6-d1bf-48b7-8e9b-c16be462a32a)

## Git의 HEAD
* 현재 속한 브랜치의 가장 최신 커밋

**checkout으로 앞뒤 이동**
```
git checkout HEAD^
```
* ^ 또는 ~ 갯수만큼 이전으로 이동
  * git checkout HEAD^^^^, git checkout HEAD~5
* **커밋 해시를 사용해서도 이동 가능**
  * git checkout (커밋해시)
* git checkout - : 이동을 한 단계 되돌리기

## HEAD 사용하여 reset하기
```
git reset HEAD(원하는 단계) (옵션)
```

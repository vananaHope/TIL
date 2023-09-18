# Push와 Pull

## 원격으로 커밋 밀어올리기(push)
```
git push
```
* 이미 git push -u origin main으로 대상 원격 브랜치가 지정되었기 때문에 가능

## 원격의 커밋 당겨오기(pull)
```
git pull
```

## pull 할 것이 있을 때 push 할 경우
* merge 방식
```
git pull --no-rebase
```
* rebase 방식
```
git pull --rebase
```
* pull 진행한 뒤에 push 하기

## 로컬 내역 강제 push
```
git push --force
```

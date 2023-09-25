# Stash 활용하기
* 커밋하기 애매한 것들을 **Stash**를 활용하여 임시 저장해놓기

## Stash 명령어
* git stash 명령어로 임시 저장해놓기
```
git stash
```

* 원하는 시점, 브랜치에서 다시 적용
```
git stash pop
```

* 원하는 것만 stash 하기
```
git stash -p
```

* 메시지와 함께 stash
```
git stash -m 'Add Stash'
```

* stash 목록 보기
  * list 상의 번호로 apply, drop, pop 가능
  * git stash apply stash@{1}
```
git stash list
```

## 정리

![image](https://github.com/vananaHope/TIL/assets/125250099/b715d566-53ff-4410-bbc0-05d418335514)



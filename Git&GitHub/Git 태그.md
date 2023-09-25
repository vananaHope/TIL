# Git 태그
* 특정 시점을 키워드로 저장하고 싶을 때
* 커밋에 버전 정보를 붙이고자 할 때
  * [Semantic Versioning](https://semver.org/lang/ko/)

## 태그 달기

![image](https://github.com/vananaHope/TIL/assets/125250099/fcb94ed4-fbea-4e15-b1c2-477cab0f775a)

### 마지막 커밋에 태그 달기(lightweight)
```
git tag v2.0.0
```

### tag 확인
```
git tag
```

### 원하는 tag 태그 내용 확인
```
git show v2.0.0
```

### 태그 삭제
```
git tag -d v2.0.0
```

### 마지막 커밋에 태그 달기(annotated)
```
git tag -a v2.0.0
```
```
git tag v2.0.0 -m '버전 2'
```

### 원하는 커밋에 태그 달기
```
git tag (태그명) (커밋 해시) -m (메시지)
```

### 원하는 패턴으로 필터링
```
git tag -l 'v1.*'
```

### 원하는 버전으로 체크아웃
```
git checkout v1.2.1
```
* switch로 이전 브랜치 복귀

## 원격 태그와 릴리즈

### 특정 태그 원격에 올리기
```
git push (원격명) (태그명)
```

### 특정 태그 원격에서 삭제
```
git push --delete (원격명) (태그명)
```

### 로컬의 모든 태그 원격에 올리기
```
git push --tags
```

## GitHub의 release
* 다운가능한 배포판 만드는 기능

### release 만들기
* GitHub에서 태그 목록으로
* 원하는 태그에서 Create release
* 제목과 내용(마크다운) 입력 후 Publish release

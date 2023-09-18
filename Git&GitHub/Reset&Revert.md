# Reset & Revert
* Reset : 원하는 시점으로 돌아간 뒤 이후 내역들을 삭제
* Revert : 되돌리기 원하는 시점의 커밋을 거꾸로 실행

## Reset
* 커밋 내역 확인
```
git log
```
* reset으로 과거 돌아가기
```
git reset --hard (돌아갈 커밋 해시)
```
* 뒤에 커밋해시가 없을 경우 마지막 커밋을 가리킴
```
git reset --hard
```

## Revert
```
git revert (되돌릴 커밋 해시)
```
* 커밋하지 않고 revert 하기
  * 원하는 다른 작업을 추가한 다음에 함께 커밋
  * 취소하려면 git reset --hard
```
git revert --no-commit (되돌릴  커밋 해시)
```

# Add 와 Commit

## Add
### 변경사항 확인
```
git status
```
### 파일 하나 담기
```
git add tigers.yaml
```
### 모든 파일 담기
```
git add .
```
### modified된 파일 수정 부분을 단위별로 나누어서 담기 **(권장)**
```
git add -p
```
* 변경사항의 하나의 단위를 **hunk** 라고 한다.
* ?를 입력하면 각 명령어를 확인할 수 있다.
```
y - stage this hunk
n - do not stage this hunk
q - quit; do not stage this hunk or any of the remaining ones
a - stage this hunk and all later hunks in the file
d - do not stage this hunk or any of the later hunks in the file
g - select a hunk to go to
/ - search for a hunk matching the given regex
j - leave this hunk undecided, see next undecided hunk
J - leave this hunk undecided, see next hunk
k - leave this hunk undecided, see previous undecided hunk
K - leave this hunk undecided, see previous hunk
s - split the current hunk into smaller hunks
e - manually edit the current hunk
? - print help
```
* y를 누르면 해당 hunk를 스테이징에 추가하고 n을 누르면 추가하지 않고 다음 hunk를 보여준다 q를 입력하면 add과정을 종료한다
* git이 알아서 hunk의 단위를 나누어 주지만 원하는 것보다 그 단위가 크다면(hunk에 스테이징에 추가할 내용과 아닌 내용이 섞여 있는 경우) s를 입력하면 hunk를 더 잘게 쪼개준다.

## Commit
### git commit
* vi 입력 모드로 진입
  
|         작업        | Vi명령어 |                     상세                     |
|:-------------------:|:--------:|:--------------------------------------------:|
|      입력 시작      |     i    | 명령어 입력 모드에서 텍스트 입력 모드로 전환 |
|      입력 종료      |    ESC   | 텍스트 입력 모드에서 명령어 입력 모드로 전환 |
|    저장 없이 종료   |    :q    |                                              |
| 저장 없이 강제 종료 |    :q!   |           입력한 것이 있을 때 사용           |
|    저장하고 종료    |    :wq   |           입력한 것이 있을 때 사용           |
|     위로 스크롤     |     k    |        git log등에서 내역이 길 때 사용       |
|    아래로 스크롤    |     j    |        git log등에서 내역이 길 때 사용       |  

### commit 메시지와 함께 작성
* git add를 진행한 후에 commit -m
```
git commit -m "FIRST COMMIT"
```
### add와 커밋을 동시에 (untracked 파일이 없을 때 한정)
```
git commit -am "SECOND COMMIT"
```
### 변경사항 확인하고 커밋하기
```
git commit -v
```
* j, k로 스크롤하며 내용 확인
* git diff --staged와 비교


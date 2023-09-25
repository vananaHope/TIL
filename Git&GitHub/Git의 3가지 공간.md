# Git의 3가지 공간

![image](https://github.com/vananaHope/TIL/assets/125250099/04afae2e-570d-4046-aac7-3f56cbd3694e)

## Working Directory
* untracked : Add된 적 없는 파일, ignore된 파일
* tracked : Add된 적 있고 변경 내역이 있는 파일
* git add 명령어로 Staging Area로 이동

## Staging Area
* 커밋을 위한 준비 단계
  * 예시 : 작업을 위해 선택된 파일들
* git commit 명령어로 repository로 이동

## Repository
* .git directory 라고도 불림
* 커밋된 상태

## 3가지 공간 비유

![image](https://github.com/vananaHope/TIL/assets/125250099/3358b9a4-dabb-4665-ad04-70df9d1862ec)

## 파일 삭제와 이동
* git rm
```
git rm (파일명)
```
* git mv
```
git mv (원래 파일명) (변경할 파일명)
```

**파일을 staging area에서 working directory로 이동**
```
git restore --staged (파일명)
```
* --staged를 빼면 working directory에서도 제거

**reset의 3가지 옵션**
* --soft : repository에서 staging area로 이동
* --mixed **(default)** : repository에서 working directory로 이동
* --hard : 수정사항 완전히 삭제

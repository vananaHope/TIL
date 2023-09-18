# Git 설치와 세팅
* 얄팍한 코딩의 깃 끝장내기를 정리한 내용입니다.

## Git 설치
* https://git-scm.com/ 로 이동해서 Git을 다운로드합니다
* 설치과정에서 Git Bash 반드시 포함하여 설치
* 윈도우와 맥에서 엔터 방식 차이로 인한 오류 방지를 위해 아래 명령어 입력
  ```
  git config --global core.autocrlf true
  ```
## SourceTree 설치
* https://www.sourcetreeapp.com/ - Git을 GUI로 다룰 수 있도록 해주는 툴

## VS Code 설치 및 설정
* https://code.visualstudio.com/

### 기본 터미널을 Git Bash로 설정
* VS Code에서 Ctrl + Shift + P
* Select Default Profile 검색하여 선택
* Git Bash 선택
* 터미널에서 +로 새 창을 열어서 기본으로 Git Bash가 설정된 것 확인
* Git Bash를 C 드라이브에 설치해야 이 설정이 가능
  
## Git 최초 설정
* GitHub 계정과는 별개
* 터미널 프로그램에서 아래 명령어 실행
```
git config --global user.name "(본인 이름)"
git config --global user.email "(본인 이메일)"
```
* 기본 브랜치명 변경
```
git config --global init.defaultBranch main
```
* 아래 명령어 통해 .git 폴더(git 관리내역) 생성
```
git init
```

## Git 예외 설정
* .gitignore 파일을 통해서 배제할 요소들을 지정할 수 있음

### Git 관리에서 특정 파일/폴더를 배제해야 할 경우
* 포함할 필요가 없을 때
  * 자동으로 생성 또는 다운로드되는 파일들 (빌드 결과물, 라이브러리)
* 포함하지 말아야 할 때
  * 보안상 민감한 정보를 담은 파일

### .gitignore 형식
```
# 이렇게 #를 사용해서 주석

# 모든 file.c
file.c

# 최상위 폴더의 file.c
/file.c

# 모든 .c 확장자 파일
*.c

# .c 확장자지만 무시하지 않을 파일
!not_ignore_this.c

# logs란 이름의 파일 또는 폴더와 그 내용들
logs

# logs란 이름의 폴더와 그 내용들
logs/

# logs 폴더 바로 안의 debug.log와 .c 파일들
logs/debug.log
logs/*.c

# logs 폴더 바로 안, 또는 그 안의 다른 폴더(들) 안의 debug.log
logs/**/debug.log
```

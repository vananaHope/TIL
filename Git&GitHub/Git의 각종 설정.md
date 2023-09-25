# Git의 각종 설정
* global과 local 설정
  * config를 --global과 함께 지정하면 전역으로 설정됩니다.
  * --global 붙이지 않으면 local 설정

## 설정값 확인하기
* 현재 모든 설정값 확인
```
git config (global) --list
```

* 에디터에서 보기 (기본 : vi)
```
git config (global) -e
```

* 기본 에디터 수정
  * code 자리에 원하는 편집 프로그램의 .exe 파일 경로 연결
  * --wait : 에디터에서 수정하는 동안 CLI를 정지
  * git commit 등의 편집도 지정된 에디터에서 열게 됨
```
git config --global core.editor "code --wait"
```

* 위의 에디터 설정을 되돌리려면
  * git config --global -e로 편집기를 연 뒤 아래 부분을 삭제하고 저장
![image](https://github.com/vananaHope/TIL/assets/125250099/38c2c78f-8118-436d-9c99-bbba015dd7ba)

## 유용한 설정들
* 줄바꿈 호환 문제 해결
```
git config --global core.autocrlf (윈도우: true / 맥: input)
```
* pull 기본 전략 merge 또는 rebase로 설정
```
git config pull.rebase false
git config pull.rebase true
```
* 기본 브랜치명
```
git config --global init.defaultBranch main
```
* push시 로컬과 동일한 브랜치명으로
```
git config --global push.default current
```

## 단축키 설정
```
git config --global alias.(단축키) "명령어"
```
* 예시 : git config --global alias.cam "commit -am"

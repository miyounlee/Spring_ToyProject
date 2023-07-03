## ⚾️ 야구 관리 프로그램 토이 프로젝트
<br>

## 🛠 프로젝트에 사용한 기술
<img src="https://img.shields.io/badge/Java 11-FF160B?style=flat-square&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/JDBC-A9225C?style=flat-square&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=flat-square&logo=notion&logoColor=white">

<br><br>
## 👩🏻‍💻 팀원 역할
`이미연`
- DB 생성 및 DB 역할
- Stadium, Team 모델 생성
- Stadium, Team 서비스 생성
- Stadium,Team, PlayerByPosition DAO 생성
- PositionResp, TeamResp DTO 생성
- 콘솔출력 및 파싱구현

<br>

`양수현`
- DB 생성 및 DB 역할
- Player,OutPlayer 모델 생성
- Player,OutPlayer 서비스 생성
- Player,OutPlayer DAO 생성
- PlayerResp, OutPlayerResp DTO 생성

<br><br>
## 💻 데이터베이스
`데이터베이스 스키마/테이블 생성`
``` sql
create database baseball;
use baseball;

create table stadium(
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  name varchar(20) NOT NULL,
  created_at  Timestamp DEFAULT CURRENT_TIMESTAMP
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

create table team (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  stadium_id int ,
  name varchar(20) NOT NULL,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  foreign key (stadium_id) references stadium(id)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

create table player(
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  team_id int ,
  name varchar(20) NOT NULL,
  position varchar(10) NOT NULL,
  created_at  timestamp DEFAULT CURRENT_TIMESTAMP,
  foreign key (team_id) references team(id),
  unique(position, team_id)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

create table out_player(
  id  int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  player_id int ,
  reason varchar(100) NOT NULL,
  created_at  timestamp DEFAULT CURRENT_TIMESTAMP,
  foreign key (player_id) references player(id)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;
```
<br>

`야구장 입력 데이터`
``` sql
야구장등록?name=잠실
야구장등록?name=창원
야구장등록?name=광주
```
<br>

`팀 입력 데이터`
```sql
팀등록?stadiumId=1&name=LG
팀등록?stadiumId=2&name=NC
팀등록?stadiumId=3&name=KIA
```
<br>

`선수 입력 데이터`
```sql
LG (1) 
선수등록?teamId=1&name=오스틴&position=1루수
선수등록?teamId=1&name=서건창&position=2루수
선수등록?teamId=1&name=문보경&position=3루수
선수등록?teamId=1&name=박동원&position=포수
선수등록?teamId=1&name=켈리&position=투수
선수등록?teamId=1&name=오지환&position=유격수
선수등록?teamId=1&name=홍창기&position=좌익수
선수등록?teamId=1&name=박해민&position=중견수
선수등록?teamId=1&name=문성주&position=우익
NC (2)
선수등록?teamId=1&name=오영수&position=1루수
선수등록?teamId=1&name=박민우&position=2루수
선수등록?teamId=1&name=박석민&position=3루수
선수등록?teamId=1&name=박세혁&position=포수
선수등록?teamId=1&name=페디&position=투수
선수등록?teamId=1&name=김주원&position=유격수
선수등록?teamId=1&name=김성욱&position=좌익수
선수등록?teamId=1&name=마틴&position=중견수
선수등록?teamId=1&name=박건우&position=우익
KIA (3)
선수등록?teamId=1&name=변우혁&position=1루수
선수등록?teamId=1&name=김선빈&position=2루수
선수등록?teamId=1&name=류지혁&position=3루수
선수등록?teamId=1&name=한승택&position=포수
선수등록?teamId=1&name=앤더슨&position=투수
선수등록?teamId=1&name=박찬호&position=유격수
선수등록?teamId=1&name=이창진&position=좌익수
선수등록?teamId=1&name=소크라테스&position=중견수
선수등록?teamId=1&name=나성범&position=우익
```
<br>

`퇴출선수 입력 데이터`
```sql
퇴출등록?playerId=1&reason=군입대
퇴출등록?playerId=2&reason=은퇴
퇴출등록?playerId=3&reason=부상
```
<br>

## 💻구현 기능

### 야구장 등록 ```이미연```

````
요청 : 야구장등록?name=잠실야구장
응답 : 성공이라는 메시지를 출력한다.
````

### 모든 야구장 목록보기 ```이미연```

````
요청 : 야구장목록
응답 : 야구장 목록은 Model -> Stadium을 List에 담아서 출력한다.
````



### 팀 등록 ```이미연```

````
요청 : 팀등록?stadiumId=1&name=NC
응답 : 성공이라는 메시지를 출력한다.
````


### 전체 팀 목록 ```이미연```

````
요청 : 팀목록
응답 : 팀 목록은 Stadium 정보를 조인해서 출력해야 된다. TeamRespDTO가 필요하다.
````


### 선수 등록 ```양수현```

````
요청 : 선수등록?teamId=1&name=이대호&position=1루수
응답 : 성공이라는 메시지를 출력한다.
포지션은 아래와 같이 중복되지 않고 입력되어야 합니다.(같은 포지션에 두 명의 선수가 등록될 수 없습니다.)
player 테이블에 포지션 칼럼은 팀 별로 유일해야 합니다.(player 테이블의 team_id와 position은 다중 칼럼 유니크 제약조건이 필요)
````



### 팀별 선수 목록 ```양수현```

````
요청 : 선수목록?teamId=1
응답 : 선수 목록은 Model -> Player를 List에 담아서 출력한다. (team_id는 출력하지 않아도 된다)
````


### 선수 퇴출 등록 ```양수현```

````
요청 : 퇴출등록?playerId=1&reason=도박
응답 : 성공이라는 메시지를 출력합니다.
````

### 선수 퇴출 목록 ```양수현```

```
요청 : 퇴출목록
응답 : OutPlayerRespDTO에 담아서 출력합니다.
```

### 포지션별 팀 야구 선수 페이지 ```이미연```

````
요청 : 포지션별목록
응답 : PositionRespDto 에 값을 담아서 콘솔에 출력합니다.
````

## 내용
스프링부트로 게시판 만들어보기

## 개발환경
- IntelliJ IDEA Community Edition 2023.3.3
- Amazon Corretto 21
- mysql community server 8.0
- spring boot 3.1.8
- mybatis framework
- thymeleaf

## dependencies
- Spring Boot DevTools : 코드 수정시 자동으로 서버 재시작
- Spring Web : REST ful 서비스, MVC 패턴 지원
- MyBatis Framework : SQL 매핑 프레임워크. 데이터베이스 작업 간소화
- MySQL Driver : MySQL 데이터베이스 연결을 위한 JDBC 드라이버
- Thymeleaf : 동적 HTML 생성
- Lombok : 반복적인 코드(getter, setter 등) 자동 생성. 코드 간소화

## Library or Framework
a. Spring Data JPA : 데이터베이스 작업 간소화
  1. 반복적인 CRUD (Create, Read, Update, Delete) 연산을 자동화합니다.
  2. 메서드 이름만으로 복잡한 쿼리를 생성할 수 있습니다.
  3. 페이징과 정렬 기능을 쉽게 구현할 수 있습니다.

b. Thymeleaf : 웹 페이지 생성
  1. HTML을 그대로 사용하면서 동적 내용을 추가할 수 있습니다.
  2. 서버에서 렌더링된 페이지와 정적 프로토타입을 모두 잘 표시합니다.
  3. 다양한 템플릿 기능을 제공하여 동적 웹 페이지 생성을 용이하게 합니다.

c. Lombok : Java 코드 간소화
  1. 어노테이션을 사용하여 getter, setter, constructor 등의 보일러플레이트 코드를 자동 생성합니다.
  2. @Data, @Builder 등의 어노테이션으로 클래스 작성을 매우 간단하게 만듭니다.
  3. 코드의 가독성을 높이고 작성 시간을 단축시킵니다.

d. Gradle : 빌드 자동화 도구
  1. Groovy 또는 Kotlin DSL을 사용하여 빌드 스크립트를 작성합니다.
  2. 의존성 관리, 컴파일, 테스트, 패키징 등 프로젝트 빌드 전 과정을 자동화합니다.
  3. 플러그인 시스템을 통해 확장성이 뛰어나며, 빌드 속도가 빠릅니다.

e. MyBatis : 데이터베이스와 자바 애플리케이션 간의 데이터 매핑을 쉽게 할 수 있도록 도와주는 도구
  1. SQL과 자바 코드의 분리: SQL은 XML 파일이나 어노테이션으로 관리됩니다.
  2. 동적 SQL 지원: 조건에 따라 SQL을 동적으로 생성할 수 있습니다.
  3. 성능 최적화: Statement 캐싱, 지연 로딩 등의 기능을 제공합니다.

## table 정의
```sql
-- board_table
 drop table if exists board_table;
 create table board_table
 (
	id bigint primary key auto_increment,
    boardTitle varchar(50),
    boardWriter varchar(20),
    boardPass varchar(20),
    boardContents varchar(500),
    boardHits int default 0,
    createdAt datetime default now(), 
    fileAttached int default 0
);
-- board_file_table
drop table if exists board_file_table;
create table board_file_table
(
    id	bigint auto_increment primary key,
    originalFileName varchar(100),
    storedFileName varchar(100),
    boardId bigint,
    constraint fk_board_file foreign key(boardId) references board_table(id) on delete cascade
);
```

## 폴더와 파일 구조
- com.codingrecipe.board: 프로젝트의 기본 패키지
  - config
    - WebConfig: 설정 담당 / Spring MVC 설정, 인터셉터, 리소스 핸들러, CORS 설정 포함
  - controller
    - BoardController: 게시판 관련 HTTP 요청을 처리하는 컨트롤러
    - HomeController: 홈페이지 또는 메인 페이지 관련 요청을 처리하는 컨트롤러
  - dto
    - BoardDTO: 게시판 데이터를 전송하기 위한 객체
    - BoardFileDTO: 게시판의 파일 관련 데이터를 전송하기 위한 객체
  - repository
    - BoardRepository: 게시판 관련 데이터베이스 작업을 처리
  - service
    - BoardService: 게시판 관련 비즈니스 로직을 처리
  - BoardApplication: 애플리케이션 실행 진입점

# 스프링 DB 접근 기술
* h2 데이터베이스
* 순수 jdbc
* JdbcTemplate
* JPA
* Spring Data JPA

## h2 데이터베이스
* https://www.h2database.com/html/download-archive.html
* 다운로드 및 설치
* 실행 : h2.bat
* 데이터베이스 파일 생성 방법
  * jdbc:h2:~/test (최초 한번)
  * ~/test.mv.db 파일 생성 확인
  * 이후부터는 jdbc:h2:tcp://localhost/~/test 이렇게 접속

**h2 데이터베이스가 정상 작동하지 않을 때**
![image](https://github.com/vananaHope/TIL/assets/125250099/45d8b80b-5d13-4cc3-86b5-4a32aa6e8255)

## jdbc 환경 설정
* build.gradle 파일에 jdbc, h2 데이터베이스 관련 라이브러리 추가
```
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
runtimeOnly 'com.h2database:h2'
```
* 스프링 부트 데이터베이스 연결 설정 추가 (application properties)
```
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
```

## 스프링 통합 테스트
* @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.
* @Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 
테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지
않는다

## 스프링 JdbcTemplate
* 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분제거해준다. 하지만 SQL은 직접 작성해야 한다.
```
public class JdbcTemplateMemberRepository implements MemberRepository {
 private final JdbcTemplate jdbcTemplate;

 public JdbcTemplateMemberRepository(DataSource dataSource) {
   jdbcTemplate = new JdbcTemplate(dataSource);
 }
 @Override
 public Member save(Member member) {
     SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
     jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
     Map<String, Object> parameters = new HashMap<>();
     parameters.put("name", member.getName());
  
     Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // IDENTITY 전략의 ID 반환
     member.setId(key.longValue());
  
     return member;
 }
 @Override
 public Optional<Member> findById(Long id) {
    List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);

    return result.stream().findAny();
 }
 @Override
 public List<Member> findAll() {
    return jdbcTemplate.query("select * from member", memberRowMapper());
 }
 @Override
 public Optional<Member> findByName(String name) {
    List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);

    return result.stream().findAny();
 }
 private RowMapper<Member> memberRowMapper() {
     return (rs, rowNum) -> {
       Member member = new Member();
       member.setId(rs.getLong("id"));
       member.setName(rs.getString("name"));
       return member;
     };  
 }
}
```

**jdbcTemplate 활용하도록 스프링 설정 변경**
```
@Configuration
public class SpringConfig {
 private final DataSource dataSource;

 public SpringConfig(DataSource dataSource) {
     this.dataSource = dataSource;
 }
 @Bean
 public MemberService memberService() {
     return new MemberService(memberRepository());
 }
 @Bean
 public MemberRepository memberRepository() {
    // return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
     return new JdbcTemplateMemberRepository(dataSource);
 }
}
```

## JPA
* JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
* JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
* JPA를 사용하면 개발 생산성을 크게 높일 수 있다.

**build.gradle 파일에 JPA, h2 데이터베이스 관련 라이브러리 추가**
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // jdbc 포함되어 있음
    runtimeOnly 'com.h2database:h2'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
      exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
}
```

**스프링 부트에 JPA 설정 추가 (application properties)**
```
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
```
* show-sql : JPA가 생성하는 SQL을 출력한다.
* ddl-auto : JPA는 테이블을 자동으로 생성하는 기능을 제공하는데 none 를 사용하면 해당 기능을 끈다.
  * create를 할 경우 엔티티 정보를 바탕으로 테이블 자동 생성

### JPA 엔티티 매핑
```
@Entity
public class Member {

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 생성해주는 경우 IDENTITY 전략 쓰기
 private Long id;

 private String name;

 public Long getId() {
     return id;
 }
 public void setId(Long id) {
     this.id = id;
 }
 public String getName() {
     return name;
 }
 public void setName(String name) {
     this.name = name;
 }
}
```

### JPA 리포지토리
```
public class JpaMemberRepository implements MemberRepository {
 private final EntityManager em;  // 스프링에서 엔티티를 관리하는 역할을 수행하는 클래스

 public JpaMemberRepository(EntityManager em) {
     this.em = em;
 }

 public Member save(Member member) {
     em.persist(member);
     return member;
 }

 public Optional<Member> findById(Long id) {
     Member member = em.find(Member.class, id);
     return Optional.ofNullable(member);
 }
 public List<Member> findAll() {
     return em.createQuery("select m from Member m", Member.class)
               .getResultList();
 }
 public Optional<Member> findByName(String name) {
     List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                   .setParameter("name", name)
                   .getResultList();
     return result.stream().findAny();
 }
}
```

### 서비스에 트랜잭션 추가
```
import org.springframework.transaction.annotation.Transactional

@Transactional
public class MemberService {}
```
* org.springframework.transaction.annotation.Transactional
* 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고 메서드가 정상 종료되면 트랜잭션을 커밋한다.
* 만약 런타임에러가 뜨면 롤백을 실행한다.
* **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행되어야 한다.**
* **데이터 변경하는 메서드 단위 혹은 클래스 단위에 @Transactional을 삽입해야 한다.**

### JPA를 사용하도록 스프링 설정 변경
```
@Configuration
public class SpringConfig {

 private final DataSource dataSource;
 private final EntityManager em;

 public SpringConfig(DataSource dataSource, EntityManager em) {
     this.dataSource = dataSource;
     this.em = em;
 }

 @Bean
 public MemberService memberService() {
     return new MemberService(memberRepository());
 }

 @Bean
 public MemberRepository memberRepository() {
    // return new MemoryMemberRepository();
    // return new JdbcMemberRepository(dataSource);
    // return new JdbcTemplateMemberRepository(dataSource);
     return new JpaMemberRepository(em);
 }
}
```

## 스프링 데이터 JPA
* 인터페이스를 통한 기본적인 CRUD
* findByName() , findByEmail() 처럼 메서드 이름 만으로 조회 기능 제공
* 페이징 기능 자동 제공
* 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이다. 따라서 JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 한다.
* 앞의 JPA 설정 그대로 사용

### 스프링 데이터 JPA 회원 리포지토리
```
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
     Optional<Member> findByName(String name);
}
```
* save, findAll 등과 같은 단순 CRUD는 모두 제공하기 때문에 추가 X
* 제공하지 않는 것을 활용하는 경우에는 리포지토리에 추가
* findByNameAndId 처럼 JPA에서 제공하는 문법에 맞게 쓰면 알아서 SQL문을 작성해준다.

### 스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경
```
@Configuration
public class SpringConfig {

 private final MemberRepository memberRepository;

 public SpringConfig(MemberRepository memberRepository) {
     this.memberRepository = memberRepository;
 }
 @Bean
 public MemberService memberService() {
     return new MemberService(memberRepository);
 }
}
```
* 스프링 데이터 JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다.
![image](https://github.com/vananaHope/TIL/assets/125250099/ec695f2f-2419-4dcb-92b5-bcbf210cfadb)


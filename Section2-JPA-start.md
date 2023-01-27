# JPA 시작
## 프로젝트 생성
- H2 Database 설치 및 실행
- Maven 프로젝트 생성
  - pom.xml : JPA 하이버네이트, H2 데이터베이스 라이브러리 추가
  - persistence.xml : JPA 설정 파일
    - /META-INF/ 에 위치
    - persistence-unit name : 이름 지정
    - javax.persistence.* : JPA 표준 속성
      - dialect : 데이터베이스 방언
      - 특정 데이터베이스에서만 사용하는 SQL 문법과 함수 호환 가능
    - hibernate : 하이버네이트 전용 속성
    
~~~xml
<persistence-unit name="hello">
  <properties>
    <!-- 필수 속성 -->
    <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
    <property name="javax.persistence.jdbc.user" value="sa"/>
    <property name="javax.persistence.jdbc.password" value=""/>
    <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
    <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>

    <!-- 옵션 -->
    <property name="hibernate.show_sql" value="true"/>
    <property name="hibernate.format_sql" value="true"/>
    <property name="hibernate.use_sql_comments" value="true"/>
    <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
  </properties>
</persistence-unit>
~~~
<br>

## 애플리케이션 개발
![](https://velog.velcdn.com/images/psmin77/post/80e7f317-ef6b-42a4-8139-468548f80b15/image.png)
- 객체 엔티티
  - @Entity : JPA가 관리할 객체
  - @Id : 데이터베이스의 PK와 매핑
- EntityManagerFactory : 하나만 생성하여 전체 애플리케이션 공유
- EntityManager : 쓰레드 간 공유 불가
- JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

### JPQL
- SQL을 추상화한 객체 지향 쿼리 언어
- SQL과 유사한 SELECT, WHERE, GROUP BY 등 지원
- 테이블이 아닌 엔티티 객체를 대상으로 검색하는 객체 지향 쿼리
<br>

>
자바 ORM 표준 JPA 프로그래밍 기본편 - 김영한, 인프런


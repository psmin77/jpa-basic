# 객체지향 쿼리 언어
- JPQL
- JPA Criteria
- QueryDSL
- 네이티브 SQL
- JDBC 외

# JPQL
- 엔티티 객체를 대상으로 검색하는 JPA에서 SQL를 추상화한 객체지향 쿼리 언어
- 특정 데이터베이스 SQL에 의존하지 않음
- SQL 문법과 유사함
  - SELECT, FROM, WHERE, GROUP BY 등 제공
  - _(ex) select m from Member m where m.age > 18_

# 기본 문법과 기능
## 기본 문법
- 엔티티와 속성은 대소문자 구분
- 엔티티 이름으로 사용 (테이블 이름 X)
- 별칭 필수

## 파라미터 바인딩
- 이름 기준 (=:)
- 위치 기준 (권장X)

## 반환 타입
- TypeQuery : 반환 타입이 명확할 때 (특정 클래스, DTO 등)
- Query : 반환 타입이 명확하지 않을 때

## 결과 조회
- getResultList() : 결과가 하나 이상일 때, 리스트 반환
- getSingleResult() : 결과가 하나일 때, 단일 객체 반환

## 페이징 API
- setFirstResult(int startPosition) : 조회 시작 위치
- setMaxResult(int maxResult) : 조회할 데이터 수
~~~java
String jpql = "SELECT m FROM Member m ORDER BY m.name DESC"
List<Member> result = em.createQuery(jpql, Member.class)
			.setFirstResult(10)
                       	.setMaxResults(20)
                       	.getResultList();  
~~~
## 조인 (ON절)
- 조인 대상 필터링
- 연관관계 없는 엔티티 외부 조인

## 서브 쿼리
- EXISTS {ALL | ANY | SOME} (subquery) : 서브쿼리 결과가 존재하면 참
- IN (subquery) : 서브쿼리 결과 중 하나라도 같은 것이 있으면 참
- SELECT, WHERE, HAVING 절에서만 가능 (FROM 불가)

## 데이터 타입 표현
- 숫자 : 10L(Long), 10D(Double), 10F(Float)
- ENUM : jpabook.MemberType.Admin (패키지명 포함)
- 엔티티 타입 : TYPE(m) = Member (상속 관계에서 사용)

## 조건식 (CASE식)
- COALESCE : null이면 설정한 값 반환
- _(ex) select coalesce(m.username, '이름 없는 회원') from Member m_
- NULLIF : 설정한 값과 같으면 null 반환, 다르면 본래 값 반환
- _(ex) select NULLIF(m.username, '관리자') from Member m_

## 기본 함수
- CONCAT
- SUBSTRING
- TRIM
- LOWER, UPPER
- LENGTH
- LOCATE
- ABS, SQRT, MOD
- SIZE, INDEX    
<br>

# 경로 표현식
- .(점)으로 객체 그래프 탐색
- 상태 필드(state field) : 값을 저장하기 위한 필드
  - 경로 탐색의 끝
  - _(ex) select m.username, m.age from Member m_
- 연관 필드(association field) : 연관관계를 위한 필드
  - 묵시적 내부 조인 발생
  - 단일 값 연관 필드 : @ManyToOne, @OneToOne, 엔티티 대상
  - _(ex) select o.member from Order o_
  - 컬렉션 값 연관 필드 : @OneToMany, @ManyToMany, 컬렉션 대상
- 명시적 조인 사용 권장
<br>

# 페치 조인(fetch join)
- JPQL 성능 최적화를 위해 연관된 엔티티나 컬렉션을 SQL 한 번에 함께 조회하는 기능
- [JPQL] select m 
from Member m 
join fetch m.team
- [SQL] SELECT m.\*, t.\*
FROM member m 
INNER JOIN team t ON m.team_id = t.id

## 컬렉션 페치 조인 : 일대다 관계
- [JPQL] select t 
from Team t 
join fetch t.members 
where t.name = 'teamA'
- [SQL] SELECT t.\*, m.\* 
FROM team t 
INNER JOIN member m 
ON t.id = m.team_id 
WHERE t.name = 'teamA'

## DISTINCT
- SQL에 DISTINCT 추가
- 애플리케이션에서 엔티티 중복 제거

## 특징과 한계
- JPQL은 연관관계를 고려하지 않기 때문에 SELECT절에 지정한 엔티티만 조회
- 페치 조인을 사용할 때만 연관된 엔티티도 함께 조회(즉시 로딩)
- 페치 조인 대상에는 별칭 사용 불가
- 둘 이상의 컬렉션은 페치 조인 불가
- 컬렉션을 페치 조인하면 페이징 API 사용 불가
- 최적화가 필요한 경우 페치 조인 적용
- 객체 그래프를 유지할 때 사용하면 효과적
<br>

# 다형성 쿼리
## TYPE
- 조회 대상을 특정 자식으로 한정
- 자바의 타입 캐스팅과 유사
- 상속 구조에서 부모 타입을 특정 자식 타입으로 다룰 때 사용
- FROM, WHERE, SELECT 사용
<br>

# 엔티티 직접 사용
- JPQL에서 엔티티를 직접 사용하면, SQL에서 해당 엔티티의 기본 키 값을 사용함
- [JPQL] select count(m.id) from Member m
select count(m) from Member m 
- [SQL] SELECT count(m.id) AS cnt FROM member m

## 파라미터 전달
- _m = :member_
- _.setParameter("member", member)_

## 식별자 전달
- _m.id = :memberId_
- _.setParameter("memberId", memberId)_
<br>

# Named 쿼리(정적쿼리)
- 미리 정의해둔 쿼리에 이름을 부여하고 사용함
- 어노테이션 또는 XML에 정의
  - XML 우선권
  - _(ex) @NamedQuery(name="...", query="...")_
- 애플리케이션 로딩 시점에 쿼리를 검증, 초기화 후 재사용
<br>

# 벌크 연산
- 한 번의 쿼리로 여러 테이블 로우 변경
- executeUpdate() : 영향 받은(변경된) 엔티티의 수 반환
- UPDATE, DELETE 지원
- 주의점
  - 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리
  - 벌크 연산 먼저 실행 -> 영속성 컨텍스트 초기화
<br>

>
자바 ORM 표준 JPA 프로그래밍 - 김영한, 인프런


# JPA 소개
- 기존 SQL 중심의 개발 문제점
  - 객체를 테이블에 맞추어 모델링하여, 매핑 작업이 많아지고 복잡함
  - SQL문에 따라 탐색 범위 한정적 등
  
## JPA (Java Persistence API)
- 자바 ORM 기술 표준
  - ORM (Object-Relational Mapping) : 객체 관계 매핑
  - 객체와 데이터베이스 사이에서 매핑하는 기술
- 애플리케이션과 JDBC 사이에서 동작함
- JPA 2.1 표준 명세
  - 3가지 구현체(하이버네이트, EclipseLink, DataNucleus) 인터페이스의 모음
- JPA 장점
  - 객체 중심의 개발
  - 생산성 : 저장-persist, 조회-find, 수정-set, 삭제-remove
  - 유지보수 : 기존 필드 변경 시 모든 SQL 수정, JPA는 필드만 수정
  - 성능 최적화 : 동일성 보장(같은 엔티티 반환), 지연 로딩/즉시 로딩
<br>

>
자바 ORM 표준 JPA 프로그래밍 기본편 - 김영한, 인프런

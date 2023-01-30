# 객체와 테이블 매핑
## @Entity
 - JPA를 통해 테이블과 매핑할 클래스
 - 기본 생성자 필수 
 - final, enum, interface, inner 클래스 사용 불가
 - name 속성: 엔티티 이름 지정(기본 값은 클래스 이름)
## @Table
- 엔티티와 매핑할 테이블 지정
- name : 매핑할 테이블 이름
- catalog, schema, uniqueConstraints
<br>

# 데이터베이스 스키마 자동 생성
- 애플리케이션 실행 시점에 자동으로 DDL 생성
- 테이블 중심 -> 객체 중심
- 개발 서버에서만 사용, 운영 서버에서는 사용하지 말 것

## 속성
- create : 기존 테이블 삭제 후 다시 생성 (drop + create)
- create-drop : 종료 시점에 테이블 모두 DROP
- update : 변경 내용만 반영
- validate  : 엔티티와 테이블이 정상 매핑되었는지만 확인
- none : 사용하지 않음

## DDL 생성 기능
- 제약조건 추가(null, length 등)
- 유니크 제약조건 추가
<br>

# 필드와 컬럼 매핑
## 매핑 어노테이션
- @Column : 컬럼 매핑
  - name, nullable, unique,length 등
- @Temporal : 날짜 타입 매핑
- @Enumerated : enum 타입 매핑
  - EnumType.ORDINAL 사용하지 말 것
  - EnumType.STRING 권장
- @Lob : BLOB, CLOB 매핑
- @Transient : 특정 필드를 매핑하지 않음
<br>

# 기본키 매핑
## @Id
- 직접 할당

## @GeneratedValue
- 자동 생성
- IDENTITY : 데이터베이스에 위임, MYSQL
- SEQUENCE : 시퀀스 오브젝트 사용, ORACLE
  - @SequenceGenerator 
- TABLE : 키 생성용 테이블 사용
  - @TableGenerator
- AUTO : 방언에 따라 자동 지정, 기본 값

## 식별자 전략
- 기본 키 제약 조건 : null이 아닌 유일성, 변하지 않는 값
- 권장 : Long형 + 대체키 + 키 생성 전략
<br>

>
자바 ORM 표준 JPA 프로그래밍 기본편 - 김영한, 인프런

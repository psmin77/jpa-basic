# 상속관계 매핑
- 관계형 데이터베이스는 상속 관계가 없음
- 슈퍼-서브타입 논리 모델을 상속관계로 매핑
- @Inheritance(strategy=InheritanceType.XXX)
  - JOINED : 조인 전략
  - SINGLE_TABLE : 단일 테이블 전략
  - TABLE_PER_CLASS : 구현 클래스마다 테이블 전략
    - 권장하지 않음
- @DiscriminatorColumn(name="DTYPE")
- @DiscriminatorValue("XXX")

## 조인 전략
- 장점 
  - 테이블 정규화
  - 외래 키 참조 무결성 제약조건 활용 가능
  - 저장공간 효율화
- 단점
  - 조회 시 조인을 많이 사용함, 성능 저하
  - 조회 커리 복잡함
  - 데이터 저장 시 INSERT 2번 호출

## 싱글 테이블 전략
- 장점
  - 일반적으로 조회 성능이 빠름
  - 조회 쿼리가 단순함
- 단점
  - 자식 엔티티가 매핑한 컬럼은 null 허용
  - 하나의 테이블이 커질 수 있음
  - 상황에 따라서 조회 성능이 느려짐
<br>

# @MappedSuperclass
- 테이블과 관계 없이, 공통으로 사용하는 매핑 정보가 필요할 때 사용
  - 주로 등록일, 수정일, 등록자, 수정자와 같은 정보
- 상속 관계 매핑은 아님
- 엔티티나 테이블과 매핑하지 않음
- 조회나 검색 불가(em.find 불가)
- 추상 클래스 권장
<br>

>
자바 ORM 표준 JPA 프로그래밍 - 김영한, 인프런


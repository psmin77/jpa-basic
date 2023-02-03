# 프록시
- 프록시 객체는 실제 객체의 참조(target)를 보관
  - 프록시 객체를 호출하면 실제 객체의 메소드 호출 (프록시 객체 초기화)
- em.getReference() 
  - 데이터베이스 조회를 지연시키는 프록시(가짜) 객체 조회
- 프록시 특징
  - 실제 클래스를 상속 받아서 만들어짐
    - 타입 체크 시 주의 ( == 비교 실패, instance of 사용)
  - 사용자 입장에서는 실제 객체와 프록시 객체를 구분하지 않고 사용 가능
  - 처음 사용할 때 한 번만 초기화
  - 초기화되면 프록시 객체를 통해 실제 객체로 접근 가능
    - 프록시 객체가 실제 객체로 바뀌는 것은 아님
  - 영속성 컨텍스트에 실제 객체가 이미 있으면 getReference()로 호출해도 해당 객체 반환
- 프록시 확인
  - PersistenceUnitUtil.isLoaded(Object entity) : 초기화 여부 확인
  - entity.getClass().getName() : 프록시 클래스 확인
  - org.hibernate.Hibernate.initialize(entity) : 강제 초기화
<br>

# 즉시 로딩과 지연 로딩
## 지연 로딩
- FetchType.LAZY
  - @ManyToOne, @OneToOne은 지연 로딩으로 설정 필요함
  - 이외에는 기본 값이 지연 로딩
- 실제 사용하는 시점에 초기화 (DB 조회)
- 모든 연관관계에서 사용 권장
  
## 즉시 로딩
- FetchType.EAGER 
- 조인을 사용하여 함께 조회
- 사용 권장하지 않음
<br>

# 영속성 전이(CASCADE)
- 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 만들고 싶을 때
  - 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
- CascadeType.XXX
  - ALL : 모두 적용
  - PERSIST : 영속
  - REMOVE : 삭제
<br>

# 고아 객체
- 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제
  - CascadeType.REMOVE와 같은 동작
- orphanRemoval = true
- 참조하는 곳이 하나일 때만 사용해야 함
- @OneToOne, @OneToMany만 가능

## 활용
- CascadeType.ALL + orphanRemovel=true
  - 부모 엔티티를 통해서 자식 엔티티의 생명주기까지 관리할 수 있음
<br>

>
자바 ORM 표준 JPA 프로그래밍 - 김영한, 인프런


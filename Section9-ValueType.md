# 기본값 타입
## 데이터 타입 분류
- 엔티티 타입
  - @Entity로 정의하는 객체
  - 데이터가 변해도 식별자로 추적 가능
  - 생명 주기 관리, 공유
- 값 타입
  - int, Integer, String과 같이 자바 기본 타입이나 객체
  - 값만 있으므로 변경 시 추적 불가
  - 생명 주기를 엔티티에 의존
  - 공유하지 않고 복사해서 사용하거나, 불변 객체로 만드는 것이 안전
  
## 값 타입 분류
- 기본값 타입
  - 자바 기본 타입(int, double)
    - _값 타입은 절대 공유하면 안됨_
  - 래퍼 클래스(Integer, Long)
  - String
- 임베디드 타입(복합 값 타입, embedded type)
- 컬렉션 값 타입(collection value type)
<br>

# 임베디드 타입(복합 값 타입)
- 새로운 값 타입을 직접 정의할 수 있음
  - 여러 테이블에서 공통으로 사용되는 값들을 모아서 만들 수 있음
  - (ex) 임베디드 타입인 homeAddress(city, street, zipcode)를 Member, Delivery 등 여러 테이블에서 사용 가능
- 주로 기본 값 타입을 모아서 만들기 때문에 복합 값 타입이라고도 함

## 임베디드 타입 사용법
- @Embeddable : 값 타입 정의
- @Embedded : 값 타입 사용
- 기본 생성자 필수

## 임베디드 타입 장점
- 재사용
- 높은 응집도
- 값을 소유한 엔티티의 생명주기 의존

## @AttributeOverride
- 한 엔티티에서 같은 값 타입을 사용하면 컬럼명이 중복됨
- @AttributeOverrides, @AttributeOverride로 컬럼명 속성 재정의
<br>

# 값 타입과 불변 객체
## 객체 타입의 한계
- 자바 기본 타입에 값을 대입하면 복사함
- 객체 타입은 참조 값을 직접 대입하는 것을 막지 못함

## 불변 객체
- 생성 시점 이후 절대 값을 변경할 수 없는 객체
- 객체 타입을 수정할 수 없도록 불변 객체(immutable object)로 설계
- 생성자로만 값을 설정하고, 수정자(setter)는 만들지 않음
<br>

# 값 타입 비교
- 동일성 비교 (identity)
  - 인스턴스의 참조 값을 비교
  - == 사용
- 동등성 비교 (equivalence) 
  - 인스턴스의 값 타입을 비교
  - equals() 사용
<br>

# 값 타입 컬렉션
- 값 타입을 하나 이상 저장할 때 사용
  - @ElementCollection, @CollectionTable
- 컬렉션을 저장하기 위한 별도 테이블 필요
- 값 타입 컬렉션도 지연 로딩 사용
- 영속성 정의(Cascade) + 고아 객체 제거 기능을 필수로 가짐

## 제약사항
- 식별자 개념이 없기 때문에 변경 시 추적이 어려움
- 변경 사항이 발생하면, 연관된 모든 데이터를 삭제하고 현재 값으로 다시 저장함
  - 모든 컬럼을 묶어서 하나의 기본 키로 구성해야 함
- 실무에서는 상황에 따라 일대다 관계로 고려
<br>

>
자바 ORM 표준 JPA 프로그래밍 - 김영한, 인프런
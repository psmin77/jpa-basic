# 영속성 컨텍스트
- 엔티티를 영구 저장하는 환경 (persistenceContext)
- 엔티티 매니저를 통해 영속성 컨텍스트에 접근
  - EntityManager.persist(entity)
  - 엔티티 매니저 : 영속성 컨텍스트 (1:1)

## 엔티티 생명주기
- 비영속 (new/transient)
  - 객체를 생성한 상태, JPA와 관련 없는 상태
  - _Member member = new Member();_
- 영속 (managed)
  - 객체를 저장한 상태
  - _entityManager.persist(member);_
- 준영속 (detached)
  - 영속성 컨텍스트에서 분리한 상태
  - _entityManager.detach(member);_
- 삭제 (removed)
  - 객체를 삭제한 상태
  - _entityManager.remove(member);_
  
## 영속성 컨텍스트 이점
- 1차 캐시
  - 커밋 전까지는 1차 캐시에 저장 및 조회
  - 1차 캐시에 없는 경우 데이터베이스에서 조회 후 1차 캐시에 저장
- 동일성(identity) 보장
  - 동일성 비교 시 true
- 트랜잭션 기반 쓰기 지연(transactional write-behind)
  - 엔티티 매니저는 데이터 변경 시 트랜잭션 시작
  - 임시 저장소에 SQL문 저장
  - 트랜잭션 커밋하면 저장소 flush, 데이터베이스 반영
- 변경 감지(Dirty Checking)
  - 기존 엔티티와 스냅샷을 비교하여 flush/update 수행 
- 지연 로딩(Lazy Loading)

## 플러시
- 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
  - 영속성 컨텍스트를 비우지 않음
  - 변경 내용을 데이터베이스에 동기화
  - 트랜잭션 작업 단위 중요
- 변경 감지 > 수정된 엔티티 저장소에 등록 > 데이터베이스에 전송
- 플러시 방법
  - em.flush() : 직접 호출
  - 트랜잭션 커밋 : 자동 호출
  - JPQL 쿼리 실행 : 자동 호출
- 플러시 모드 옵션
  - FlushModeType.AUTO : 기본 값, 사용 권장
  - FlushModeType.COMMIT : 커밋 시에만 플러시

## 준영속 상태
- 영속 상태의 엔티티를 영속성 컨텍스트에서 분리(detached)
- 준영속 상태 만드는 방법
  - entityManager.detach(entity) : 특정 엔티티만 분리
  - entityManager.clear() : 영속성 컨텍스트 초기화
  - entityManager.close() : 영속성 컨텍스트 종료
<br>

>
자바 ORM 표준 JPA 프로그래밍 기본편 - 김영한, 인프런



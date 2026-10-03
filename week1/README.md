# Week 1. Spring Boot로 REST API 만들기

## 목표

Spring Boot를 이용해 간단한 게시글 관리 REST API를 구현합니다.

- Spring Boot 프로젝트 생성
- REST API 설계
- HTTP Method 사용
- Request / Response DTO 분리
- 적절한 HTTP Status Code 적용
- Validation
- 예외 처리
- Repository 계층 분리
- JDBC 적용

---

## 구현할 도메인

```text
Post
├─ id
├─ title
└─ content
```

---

## 필수 기능

- 게시글 등록
- 게시글 전체 조회
- 게시글 단건 조회
- 게시글 수정
- 게시글 삭제

---

## 기본 구현

처음에는 DB를 사용하지 않고 `List`, `Map` 등의 자료구조를 이용해 데이터를 메모리에 저장합니다.

```text
Controller
    ↓
Application / Service
    ↓
Domain
    ↓
Repository
    ↑
InMemoryPostRepository
```

다음 내용을 적용합니다.

- Request / Response DTO 분리
- `ResponseEntity` 사용
- 존재하지 않는 게시글 처리
- 적절한 HTTP Status Code 적용
- Validation
- Repository 계층 분리

---

## 고민해볼 것

- URI와 HTTP Method를 어떻게 설계할 것인가?
- Request DTO와 Response DTO를 왜 분리하는가?
- Domain 객체를 그대로 API 응답으로 반환해도 되는가?
- Validation은 어느 계층에서 담당해야 하는가?
- 존재하지 않는 게시글은 어디에서 판단해야 하는가?
- Controller, Service, Domain, Repository는 각각 어떤 책임을 가져야 하는가?
- 객체의 상태를 외부에서 꺼내 판단하기보다 객체에게 행동을 요청할 수 있는가?
- 메서드 이름만으로 객체의 의도와 책임이 드러나는가?

---

# Additional Challenge. JDBC 적용

기본 구현이 완료되면 In-Memory Repository를 JDBC 기반 Repository로 변경합니다.

Spring Data JPA나 Hibernate는 사용하지 않고 JDBC API를 이용해 직접 데이터베이스와 통신합니다.

### Before

```text
PostRepository
      ↑
InMemoryPostRepository
      ↓
Map<Long, Post>
```

### After

```text
PostRepository
      ↑
JdbcPostRepository
      ↓
JDBC
      ↓
Database
```

## 구현 요구사항

다음 기능을 JDBC로 구현합니다.

- 게시글 저장
- 게시글 전체 조회
- 게시글 단건 조회
- 게시글 수정
- 게시글 삭제

다음 JDBC API를 직접 사용합니다.

- `Connection`
- `PreparedStatement`
- `ResultSet`

---

## JDBC 적용 후 확인할 것

- 저장 방식이 변경됐을 때 기존 코드가 얼마나 변경되는가?
- Domain이 JDBC나 DB의 존재를 알아야 하는가?
- Repository 인터페이스를 둔 이유는 무엇인가?
- In-Memory와 JDBC 구현체를 교체할 수 있는가?
- 하나의 작업 단위를 어디까지로 볼 것인가?
- 트랜잭션이 필요하다면 어느 계층을 경계로 잡는 것이 자연스러운가?
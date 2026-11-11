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

## 현재 구현 범위

### 기술 스택

- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Spring Security Crypto `PasswordEncoder`
- Bean Validation

### 도메인

```text
User
├─ id
├─ name
└─ password

Post
├─ id
├─ title
├─ body
└─ author
```

- `Post`와 `User`의 id는 서비스 계층에서 UUID v7로 생성합니다.
- `Post`는 작성자인 `User`와 연관관계를 가집니다.
- `BaseEntity`를 통해 생성일과 수정일을 관리합니다.

### 게시글 기능

- 게시글 등록
- 게시글 전체 조회
- 게시글 단건 조회
- 게시글 수정
- 게시글 삭제
- 로그인한 사용자의 게시글만 조회

### 유저 기능

- 유저 등록
- 유저 전체 조회
- 유저 단건 조회
- 유저 수정
- 유저 삭제
- 현재 로그인한 유저 조회

### 인증 기능

- 세션 기반 로그인
- 세션 저장소는 인메모리 `Map` 사용
- 요청에서 세션 id를 추출하는 필터
- 세션 id의 유효성을 확인하는 인터셉터
- 로그인 유저를 컨트롤러 파라미터로 주입하는 아규먼트 리졸버

### 권한 정책

- 게시글 생성은 로그인한 사용자만 가능합니다.
- 게시글 수정과 삭제는 작성자만 가능합니다.
- 작성자가 아닌 사용자가 게시글을 수정하거나 삭제하면 `403 Forbidden`을 반환합니다.
- 인증되지 않은 사용자가 로그인이 필요한 API에 접근하면 `401 Unauthorized`를 반환합니다.

### API

#### Users

```text
POST   /users
GET    /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}

POST   /users/login
GET    /users/me
GET    /users/me/posts
```

#### Posts

```text
POST   /posts
GET    /posts
GET    /posts/{id}
PUT    /posts/{id}
DELETE /posts/{id}
```

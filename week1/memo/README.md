# 메모 CRUD API (1주차)

메모리(`LinkedHashMap`)에 메모를 저장하는 Spring Boot CRUD API입니다. DB를 쓰지 않으므로 서버를 재시작하면 메모가 모두 사라집니다.

## 환경

| 항목 | 버전 |
|---|---|
| 언어 | Java 17 |
| 프레임워크 | Spring Boot 4.1.1 (Spring Web MVC, Validation) |
| 빌드 도구 | Gradle 9.7.1 (Gradle Wrapper 포함) |

## 의존성 설치

Gradle Wrapper가 포함되어 있어 따로 설치할 것은 JDK 17뿐입니다. 아래 명령이 의존성을 내려받고 빌드합니다.

```bash
cd week1/memo
./gradlew build        # Windows: gradlew.bat build
```

## 서버 실행 · 종료

```bash
./gradlew bootRun      # Windows: gradlew.bat bootRun
```

- 종료: 실행 중인 터미널에서 `Ctrl + C` (IntelliJ에서는 ■ Stop 버튼)
- 포트: **8081** (`src/main/resources/application.properties`의 `server.port`)
- 기본 URL: **http://localhost:8081**

## API

| 작업 | 메서드 | 경로 | 성공 코드 | 성공 응답 |
|---|---|---|---|---|
| 생성 | POST | `/memos` | 201 | 생성한 메모 객체 |
| 전체 조회 | GET | `/memos` | 200 | 메모 객체 배열 (없으면 `[]`) |
| 단건 조회 | GET | `/memos/{memo_id}` | 200 | 해당 메모 객체 |
| 수정 | PUT | `/memos/{memo_id}` | 200 | 수정한 메모 객체 |
| 삭제 | DELETE | `/memos/{memo_id}` | 200 | `{"message": "삭제 완료", "id": 1}` |

요청 본문(생성·수정): `{"content": "메모 내용"}`

### 오류 처리

| 상황 | 응답 코드 |
|---|---|
| 없는 ID, 이미 삭제한 ID의 조회·수정·삭제 | 404 |
| `content` 누락, `null`, 빈 문자열, 공백만 있는 문자열 | 400 (`@NotBlank` + `@Valid`) |
| `content`가 숫자·불리언 등 문자열이 아닌 값 | 400 (`JacksonConfig`에서 문자열 자동 변환 차단) |
| ID 자리에 숫자가 아닌 값 (`/memos/abc`) | 400 |

- ID는 서버가 1부터 순서대로 발급하며, 삭제한 ID는 재사용하지 않습니다.

## 구조

```
com.example.demo
 ├─ controller/MemoController     요청·응답 (URL 매핑, 상태 코드)
 ├─ service/MemoService           로직 (없는 ID → 404)
 ├─ repository/MemoRepository     메모리 저장소 (Map + ID 카운터)
 ├─ entity/Memo                   저장되는 메모 (id, content)
 ├─ dto/MemoDto                   생성·수정 요청 본문
 ├─ dto/MemoDeleteResponse        삭제 응답 본문
 └─ config/JacksonConfig          숫자→문자열 자동 변환 차단
```

## 검증

과제의 필수 검증 시나리오 V01~V11을 로컬에서 curl로 요청을 보내 모두 확인했습니다.

## 선택 기능 · 미완성 사항

- 선택 기능: 없음
- 미완성 사항: 없음

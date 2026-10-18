# 메모 CRUD API (심화반 1주차)

메모리(배열)에 메모를 저장하는 CRUD API입니다. DB를 쓰지 않으므로 서버를 다시 시작하면 메모가 모두 사라집니다.

## 환경

| 항목 | 버전 |
| --- | --- |
| 언어 | TypeScript 6.0 |
| 런타임 | Node.js 24.17 (npm 11.13) |
| 프레임워크 | NestJS 12.1 (Express) |

## 설치 및 실행

```bash
# 의존성 설치
npm install

# 개발 모드 실행 (파일 변경 시 자동 재시작)
npm run start:dev

# 또는 빌드 후 실행
npm run build
npm run start:prod
```

- 포트: `3000` (환경변수 `PORT`로 변경 가능)
- 기본 URL: `http://localhost:3000`
- 종료: 실행 중인 터미널에서 `Ctrl + C`

## API

| 작업 | 메서드 | 경로 | 성공 코드 | 성공 응답 |
| --- | --- | --- | --- | --- |
| 생성 | POST | /memos | 201 | 생성한 메모 객체 |
| 전체 조회 | GET | /memos | 200 | 메모 객체 배열 |
| 단건 조회 | GET | /memos/{memo_id} | 200 | 해당 메모 객체 |
| 수정 | PUT | /memos/{memo_id} | 200 | 수정한 메모 객체 |
| 삭제 | DELETE | /memos/{memo_id} | 200 | `{ "message": "삭제 완료", "id": 1 }` |

### 오류 처리

| 상황 | 응답 코드 |
| --- | --- |
| 없는 ID 조회·수정·삭제 | 404 |
| `content` 누락, `null`, 숫자 등 문자열이 아닌 값, 빈 문자열, 공백만 있는 문자열 | 400 |
| ID 자리에 정수가 아닌 값 (`abc` 등) | 400 (`ParseIntPipe`) |
| 잘못된 JSON 본문 | 400 |

### 구현 메모

- ID는 1부터 발급하고 마지막 발급 번호만 증가시키므로, 메모를 삭제해도 ID가 재사용되지 않습니다.
- 요청 본문은 `CreateMemoDto`(`src/dto/create-memo.dto.ts`)와 전역 `ValidationPipe`(class-validator)로 검증합니다. 생성과 수정에 같은 DTO를 씁니다.
- `ValidationPipe`의 `whitelist` 옵션으로 DTO에 없는 필드(예: 클라이언트가 보낸 `id`)는 무시합니다.
- `content`는 공백 검사만 하고 저장할 때는 원본 그대로 저장합니다(앞뒤 공백 유지).

## 테스트

```bash
npm run test       # 단위 테스트
npm run test:e2e   # 과제의 검증 시나리오 V01 ~ V10
```

V11(서버 재시작 후 빈 목록)은 메모리 저장 방식이라 재시작하면 자동으로 만족합니다.

## 선택 기능 / 미완성 사항

- 없음

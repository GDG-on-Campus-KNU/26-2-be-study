# Week 1 - 메모 CRUD

메모를 생성(Create) / 조회(Read) / 수정(Update) / 삭제(Delete)하는 간단한 CRUD를 두 가지 방식으로 구현했습니다.

| 파일 | 방식 |
| --- | --- |
| `crud_python.py` | 순수 Python CLI (`input()` 기반 메뉴) |
| `crud_fastapi.py` | FastAPI 기반 REST API |

두 구현 모두 DB 없이 메모리의 `dict`(`memo: {id: content}`)에 저장하며, ID는 `memo_id` 카운터로 1부터 증가합니다. 서버/프로그램을 종료하면 데이터는 사라집니다.

## 1. 순수 Python (`crud_python.py`)

### 실행

```bash
python week1/crud_python.py
```

### 메뉴

```
1. Create | 2. Read | 3. Update | 4. Delete | 0. Quit
```

| 입력 | 동작 |
| --- | --- |
| `1` | 메모 내용을 입력받아 저장하고 부여된 index를 출력 |
| `2` | index를 입력받아 해당 메모 출력 |
| `3` | index와 새 내용을 입력받아 메모 수정 |
| `4` | index를 입력받아 메모 삭제 |
| `0` | 프로그램 종료 |

- `actions` 딕셔너리에 `{"1": create_memo, ...}` 형태로 함수를 매핑해 `if/elif` 없이 메뉴를 분기합니다.
- 존재하지 않는 index는 `KeyError`를 잡아 안내 메시지를 출력합니다.

## 2. FastAPI (`crud_fastapi.py`)

### 실행

```bash
pip install fastapi uvicorn
uvicorn week1.crud_fastapi:app --reload
```

실행 후 http://127.0.0.1:8000/docs 에서 Swagger UI로 바로 테스트할 수 있습니다.

### 엔드포인트

| Method | Path | 설명 | 성공 응답 |
| --- | --- | --- | --- |
| POST | `/memos` | 메모 생성 | `201` + `{id, content}` |
| GET | `/memos` | 전체 메모 조회 | `200` + `[{id, content}, ...]` |
| GET | `/memos/{memo_index}` | 단건 조회 | `200` + `{id, content}` |
| PUT | `/memos/{memo_index}` | 메모 수정 | `200` + `{id, content}` |
| DELETE | `/memos/{memo_index}` | 메모 삭제 | `200` + `{"message": "삭제 완료", "id": ...}` |

### 요청/응답 모델

- `MemoRequest`: `content`(문자열). `StrictStr`로 문자열이 아닌 값을 거부하고, `field_validator`로 공백만 있는 내용을 거부합니다. (검증 실패 시 `422`)
- `MemoResponse`: `id`(int), `content`(str).

### 에러 처리

- 존재하지 않는 index로 조회/수정/삭제하면 `get_memo_or_404`가 `404`와 `Memo index {n} doesn't exist.` 메시지를 반환합니다.

### 사용 예시

```bash
# 생성
curl -X POST http://127.0.0.1:8000/memos -H "Content-Type: application/json" -d '{"content": "hello"}'

# 전체 조회
curl http://127.0.0.1:8000/memos

# 수정
curl -X PUT http://127.0.0.1:8000/memos/1 -H "Content-Type: application/json" -d '{"content": "updated"}'

# 삭제
curl -X DELETE http://127.0.0.1:8000/memos/1
```

## 참고 / 알려진 한계

- 데이터가 메모리에만 저장되어 재시작 시 초기화됩니다.
- `crud_python.py`에서 숫자가 아닌 index를 입력하면 `int()`가 `ValueError`를 내지만 현재 `TypeError`만 잡고 있어 예외가 처리되지 않습니다.
- `crud_python.py`의 삭제(`del memo[memo_index]`)는 `int` 변환 없이 문자열 키를 사용해 항상 "doesn't exists"가 출력됩니다.
- `crud_python.py`의 Read는 전체 목록 조회 없이 단건 조회만 지원합니다 (FastAPI 버전은 둘 다 지원).

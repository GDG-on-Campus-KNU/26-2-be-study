# 게시글 API

기본 주소: `http://localhost:8080`

요청 본문이 있는 API는 `Content-Type: application/json`을 사용합니다. 게시글 ID는 등록 시 서버가 발급합니다.

## 게시글 등록

`POST /posts`

요청:

```json
{
  "title": "첫 게시글",
  "content": "게시글 내용"
}
```

응답 `201 Created`:

```json
{
  "id": 1,
  "title": "첫 게시글",
  "content": "게시글 내용"
}
```

## 게시글 전체 조회

`GET /posts`

응답 `200 OK`: 게시글 응답 객체의 배열을 반환합니다.

## 게시글 단건 조회

`GET /posts/{id}`

응답 `200 OK`: 해당 게시글을 반환합니다. 게시글이 없으면 `404 Not Found`를 반환합니다.

## 게시글 수정

`PUT /posts/{id}`

요청 본문은 등록과 같은 형식이며, 제목과 내용을 모두 전달합니다.

응답 `200 OK`: 수정된 게시글을 반환합니다. 게시글이 없으면 `404 Not Found`를 반환합니다.

## 게시글 삭제

`DELETE /posts/{id}`

성공하면 `204 No Content`를 반환합니다. 게시글이 없으면 `404 Not Found`를 반환합니다.

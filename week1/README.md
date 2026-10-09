# 1주차 과제: 게시글 CRUD REST API

## API 명세
| Method | URI | 설명 | 성공 | 실패 |
|---|---|---|---|---|
| POST | /posts | 게시글 등록 | 201 | - |
| GET | /posts | 전체 조회 | 200 | - |
| GET | /posts/{id} | 단건 조회 | 200 | 404 |
| PUT | /posts/{id} | 게시글 수정 | 200 | 404 |
| DELETE | /posts/{id} | 게시글 삭제 | 204 | 404 |
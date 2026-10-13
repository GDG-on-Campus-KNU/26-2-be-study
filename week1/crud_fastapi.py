from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, StrictStr, field_validator

app = FastAPI()

memo: dict[int, str] = {}
memo_id = 1


class MemoRequest(BaseModel):
    content: StrictStr

    @field_validator("content")
    @classmethod
    def content_not_blank(cls, value: str) -> str:
        if not value.strip():
            raise ValueError("content must not be blank")
        return value


class MemoResponse(BaseModel):
    id: int
    content: str


def get_memo_or_404(index: int) -> str:
    if index not in memo:
        raise HTTPException(status_code=404, detail=f"Memo index {index} doesn't exist.")
    return memo[index]


@app.post("/memos", response_model=MemoResponse, status_code=201)
def create_memo(body: MemoRequest) -> MemoResponse:
    global memo_id
    memo[memo_id] = body.content
    created = MemoResponse(id=memo_id, content=body.content)
    memo_id += 1
    return created


@app.get("/memos", response_model=list[MemoResponse])
def read_memos() -> list[MemoResponse]:
    return [MemoResponse(id=i, content=c) for i, c in memo.items()]


@app.get("/memos/{memo_index}", response_model=MemoResponse)
def read_memo(memo_index: int) -> MemoResponse:
    return MemoResponse(id=memo_index, content=get_memo_or_404(memo_index))


@app.put("/memos/{memo_index}", response_model=MemoResponse)
def update_memo(memo_index: int, body: MemoRequest) -> MemoResponse:
    get_memo_or_404(memo_index)
    memo[memo_index] = body.content
    return MemoResponse(id=memo_index, content=body.content)


@app.delete("/memos/{memo_index}")
def delete_memo(memo_index: int) -> dict:
    get_memo_or_404(memo_index)
    del memo[memo_index]
    return {"message": "삭제 완료", "id": memo_index}

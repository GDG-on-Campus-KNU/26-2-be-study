memos = {}
next_id = 1


def create_memo():
    global next_id
    content = input("새 메모 내용: ").strip()

    memos[next_id] = content
    print(f"생성 완료! ID: {next_id}")
    next_id += 1


def read_memos():
    for memo_id, content in memos.items():
        print(f"[{memo_id}] {content}")


def update_memo():
    memo_id = memo_id = int(input("메모 ID: "))
    if memo_id is None:
        return

    content = input("수정할 내용: ").strip()
    memos[memo_id] = content
    print("수정 완료!")


def delete_memo():
    memo_id = memo_id = int(input("메모 ID: "))
    if memo_id is None:
        return

    del memos[memo_id]
    print("삭제 완료!")


def main():
    actions = {
        "1": create_memo,
        "2": read_memos,
        "3": update_memo,
        "4": delete_memo,
    }
    while True:
        print("\n1. 생성(C) | 2. 조회(R) | 3. 수정(U) | 4. 삭제(D) | 0. 종료")
        choice = input("선택: ").strip()
        if choice == "0":
            print("프로그램을 종료합니다.")
            break

        action = actions.get(choice)
        if action is None:
            print("0~4 중 하나를 선택해주세요.")
        else:
            action()


if __name__ == "__main__":
    main()

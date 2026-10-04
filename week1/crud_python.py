
memo = {}
memo_id = 1

def create_memo() -> None:
    global memo_id
    print("Input: ", end='')
    memo_text = input().strip()
    memo[memo_id] = memo_text
    memo_id += 1

    print(f"Memo index {memo_id-1} created.")


def read_memos() -> None:
    print("Select memo index: ", end = '')
    memo_index = input().strip()

    try:
        print(f"Memo {memo_index}: {memo[int(memo_index)]}")
    except TypeError:
        print("Invalid Input.")
    except KeyError:
        print(f"Memo index {memo_index} doesn't exists.")

    

def update_memo() -> None:
    print("Select the index of number to update: ", end = '')
    memo_index = input().strip()
    print("Input: ",end = '')
    memo_text = input().strip()
    try:
        memo[int(memo_index)] = memo_text
        print("Memo updated succesfully.")
    except TypeError:
        print("Invalid Input.")
    except KeyError:
        print(f"Memo index {memo_index} doesn't exists.")

def delete_memo() -> None:
    print("Select memo index: ",end = '')
    memo_index = input().strip()

    try:
        del memo[memo_index]
        print("Memo deleted succesfully.")
    except TypeError:
        print("Invalid Input.")
    except KeyError:
        print(f"Memo index {memo_index} doesn't exists.")



def main() :
    actions = {
        "1": create_memo,
        "2": read_memos,
        "3": update_memo,
        "4": delete_memo
    }
    while True:
        print("\n1. Create | 2. Read | 3. Update | 4. Delete | 0. Quit")
        choice = input("Select: ").strip()
        if choice == "0":
            print("Closing Program.")
            break

        action = actions.get(choice)
        if action is None:
            print("Invalid Command")
        else :
            action()

if __name__ == "__main__":
    main()
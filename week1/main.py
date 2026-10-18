database = {}


def create(key, value):
    if key in database:
        return {
            "error": "key already exists",
            "response": None,
        }

    database[key] = value
    return {
        "error": None,
        "response": "created successfully",
    }


def read(key):
    if key not in database:
        return {
            "error": "key not exists",
            "response": None,
        }

    return {
        "error": None,
        "response": database[key],
    }


def update(key, value):
    if key not in database:
        return {
            "error": "key not exists",
            "response": None,
        }

    database[key] = value
    return {
        "error": None,
        "response": "updated successfully",
    }


def delete(key):
    if key not in database:
        return {
            "error": "key not exists",
            "response": None,
        }

    del database[key]
    return {
        "error": None,
        "response": "delete successfully",
    }


def main():
    while True:
        print("[CREATE|READ|UPDATE|DELETE] KEY [VALUE]: ", end="")
        user = input().split()

        command = user[0]
        key = user[1]

        response = {}
        if command == "CREATE":
            value = user[2]
            response = create(key, value)
        elif command == "READ":
            response = read(key)
        elif command == "UPDATE":
            value = user[2]
            response = update(key, value)
        elif command == "DELETE":
            response = delete(key)
        else:
            response = {
                "error": "unknown command",
                "response": None,
            }

        if response["error"] is not None:
            print("error:", response["error"])
            continue

        print("response:", response["response"])


if __name__ == "__main__":
    main()

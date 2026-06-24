# Exercise 02 — List of Users

**Turn-in directory:** `ex02/`

**Files to turn in:** `UsersList.java`, `UsersArrayList.java`, `User.java`, `Program.java`, (+ any supporting files)

**Allowed:** Everything from Exercise 01 + `throw`

---

## Context

The application needs to store users in memory while it runs. To keep business logic independent from storage details, you will define an interface first and then provide one concrete implementation.

---

## UsersList (interface)

Define a `UsersList` interface with the following methods:

| Method | Description |
|--------|-------------|
| `addUser(User)` | Add a user to the list |
| `getUserById(int id)` | Return the user with the given ID |
| `getUserByIndex(int index)` | Return the user at the given position |
| `getNumberOfUsers()` | Return the total count of stored users |

---

## UsersArrayList (class)

Implement `UsersList` using an internal array:

- **Default capacity:** 10
- **Growth rule:** when full, increase capacity by **half** (e.g. 10 → 15)
- `addUser` places the new `User` in the first empty cell
- `getUserById` throws **`UserNotFoundException`** (unchecked) if no user with that ID exists

---

## UserNotFoundException

- Extend `RuntimeException`.
- Throw it whenever a lookup by ID finds no match.

---

## Program.java

- Create a `UsersArrayList`, add several users, retrieve them by ID and by index, and print results to `System.out`.

---

## Reminders

- The interface decouples business logic from storage — future implementations (e.g. database-backed) can replace `UsersArrayList` without touching other code.
- This mirrors how `ArrayList<T>` works in the Java standard library.
- Only the files listed above (and any you explicitly need) may be in `ex02/`.
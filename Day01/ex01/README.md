# Exercise 01 — ID Generator

**Turn-in directory:** `ex01/`

**Files to turn in:** `UserIdsGenerator.java`, `User.java`, `Program.java`

**Allowed types:** `Integer`, `String`, `UUID`, enumerations (+ all their methods)

---

## Context

Different users can share the same name, so each `User` needs a guaranteed-unique integer ID. This exercise introduces a centralised ID generator using the Singleton pattern.

---

## UserIdsGenerator

Implement a `UserIdsGenerator` class that:

| Member | Detail |
|--------|--------|
| State | Stores the last generated ID (starts at 0) |
| `int generateId()` | Returns `lastId + 1` on every call |
| `static getInstance()` | Returns the single instance (Singleton) |

- **Only one instance** of this class may ever exist. Multiple instances would break ID uniqueness.

---

## User (updated)

- The `id` field must be **read-only** — provide a getter, no setter.
- Set the ID automatically inside the constructor by calling the generator:

```java
public User(...) {
    this.id = UserIdsGenerator.getInstance().generateId();
}
```

---

## Program.java

- Create several `User` objects and print their IDs to confirm each one is unique.
- Output via `System.out`.

---

## Reminders

- Singleton: one object, one source of truth for IDs.
- `id` is assigned once at construction and never changes.
- Only the files listed above may be in `ex01/`.
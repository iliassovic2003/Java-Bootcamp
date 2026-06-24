# Exercise 00 — Models

**Turn-in directory:** `ex00/`

**Files to turn in:** `User.java`, `Transaction.java`, `Program.java`

**Allowed types:** `Integer`, `String`, `UUID`, enumerations (+ all their methods)

---

## Context

You are building an internal money transfer system. This exercise lays the foundation by defining the two core data models the rest of the project will rely on.

---

## User

Implement a `User` class with the following fields:

| Field | Type | Constraint |
|-------|------|------------|
| `id` | `int` | Any integer, unique per user |
| `name` | `String` | — |
| `balance` | `int` | Cannot be negative |

- Use a **setter** for `balance` that rejects negative values.

---

## Transaction

Implement a `Transaction` class with the following fields:

| Field | Type | Constraint |
|-------|------|------------|
| `id` | `String` | UUID format |
| `recipient` | `User` | — |
| `sender` | `User` | — |
| `category` | `enum` | `INCOME` or `OUTCOME` |
| `amount` | `int` | `INCOME` → positive only; `OUTCOME` → negative only |

- Use a **setter** for `amount` that validates sign against the category.

---

## Program.java

- Hardcode all field values (no user input required).
- Create at least one `User` and one `Transaction`.
- Print all field values to `System.out`.

---

## Reminders

- Use `get`/`set` methods for fields that need validation.
- Output only via `System.out`.
- Follow Oracle Java coding standards.
- Only the files listed above may be in `ex00/`.
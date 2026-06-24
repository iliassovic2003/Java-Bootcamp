# Exercise 03 — List of Transactions

**Turn-in directory:** `ex03/`

**Files to turn in:** `TransactionsList.java`, `TransactionsLinkedList.
java`, `User.java`, `Program.java`, (+ any supporting files)

**Allowed:** Everything from Exercise 02

---

## Context

Transactions are created very frequently. An array-based list would be expensive to resize, so this exercise uses a **linked list** instead, which adds elements in constant time O(1).

---

## TransactionsList (interface)

Define a `TransactionsList` interface with the following methods:

| Method | Description |
|--------|-------------|
| `addTransaction(Transaction)` | Add a transaction |
| `removeTransactionById(String uuid)` | Remove the transaction with the given UUID |
| `Transaction[] toArray()` | Return all transactions as an array |

---

## TransactionsLinkedList (class)

Implement `TransactionsList` as a singly linked list:

- Each `Transaction` object holds a reference (`next`) to the next transaction in the list.
- `addTransaction` must run in **O(1)** — insert at the head (or maintain a tail pointer).
- `removeTransactionById` throws **`TransactionNotFoundException`** (unchecked) if the UUID does not exist.

---

## TransactionNotFoundException

- Extend `RuntimeException`.
- Throw it when removal is attempted with a non-existent UUID.

---

## User (updated)

Add a field to `User`:

```java
private TransactionsList transactions;
```

Each user now owns their own transaction list.

---

## Program.java

- Create users with transaction lists, add and remove transactions, convert to array, and print results to `System.out`.

---

## Reminders

- O(1) insertion is the key advantage of a linked list over an array list here.
- `LinkedList<T>` in the Java standard library follows the same principle (it is bidirectional).
- Only the files listed above (and any you explicitly need) may be in `ex03/`.
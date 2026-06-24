# Exercise 04 — Business Logic

**Turn-in directory:** `ex04/`

**Files to turn in:** `TransactionsService.java`, `Program.java`, (+ all previous files)

**Allowed:** Everything from Exercise 03

---

## Context

Business logic lives in service classes. `TransactionsService` acts as a **Facade** — it coordinates users and transactions behind a single, clean API.

---

## TransactionsService

The class holds a `UsersList` field and exposes the following methods:

| Method | Description |
|--------|-------------|
| `addUser(User)` | Register a new user |
| `getUserBalance(int userId)` | Return the balance of the given user |
| `performTransfer(int senderId, int recipientId, int amount)` | Execute a transfer (see rules below) |
| `getUserTransactions(int userId)` | Return a `Transaction[]` of all transfers for that user |
| `removeTransactionById(int userId, String transactionId)` | Delete a specific transaction from a user's list |
| `checkTransactionValidity()` | Return a `Transaction[]` of all unpaired (unacknowledged) transactions |

---

### Transfer Rules (`performTransfer`)

1. Create **two** transactions with the **same UUID**:
   - `OUTCOME` (negative amount) added to the sender's list
   - `INCOME` (positive amount) added to the recipient's list
2. Update both users' balances accordingly.
3. If the transfer amount **exceeds the sender's balance**, throw **`IllegalTransactionException`** (unchecked) — do not modify any state.

---

### Validity Check (`checkTransactionValidity`)

A transaction is "unpaired" if its UUID appears in only one user's list (the matching entry was removed or never recorded for the other user). Return all such transactions.

---

## IllegalTransactionException

- Extend `RuntimeException`.
- Thrown when a transfer would overdraw the sender's balance.

---

## Program.java

- Demonstrate all six service methods with hardcoded or interactive data.
- Print results to `System.out`.

---

## Reminders

- Both transactions in a transfer share the same UUID so they can be paired later.
- The Facade pattern means callers never interact with `UsersList` or `TransactionsList` directly.
- Only the files listed above (and all files carried over) may be in `ex04/`.
# Exercise 05 — Menu

**Turn-in directory:** `ex05/`

**Files to turn in:** `Menu.java`, `Program.java`, (+ all previous files)

**Allowed:** Everything from Exercise 04 + `try/catch`

---

## Context

Wire everything together into a runnable console application with an interactive menu and two operating modes.

---

## Menu (class)

- Holds a reference to `TransactionsService`.
- Displays a numbered list of options and reads the user's choice from `System.in`.
- Loops after each action, re-printing the menu, until the user selects **Finish execution**.
- Catches all exceptions and prints an error message, then lets the user try again — the program must never crash on bad input.

---

## Launch Modes

Start the program with a `--profile` argument:

| Command | Mode | Extra menu items accessibility |
|---------|------|--------------------------|
| `java Program --profile=production` | Production | Items 1–4 + 7 only |
| `java Program --profile=dev` | Dev | All items 1–7 |

---

## Menu Items

```
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV - remove a transfer by ID
6. DEV - check transfer validity
7. Finish execution
```

Items 5 and 6 are **only accessible in dev mode**.

---

## Expected Interaction (dev mode)

```
$> java Program --profile=dev

1. Add a user
...
7. Finish execution
-> 1
Enter a user name and a balance
-> John 777
User with id = 1 is added
---------------------------------------------------------
...
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 100
The transfer is completed
---------------------------------------------------------
...
-> 4
Enter a user ID
-> 1
To Mike(id = 2) -100 with id = cc128842-2e5c-4cca-a44c-7829f53fc31f
---------------------------------------------------------
...
-> 5
Enter a user ID and a transfer ID
-> 1 1fc852e7-914f-4bfd-913d-0313aab1ed99
Transfer To Mike(id = 2) 150 removed
---------------------------------------------------------
...
-> 6
Check results:
Mike(id = 2) has an unacknowledged transfer id = 1fc852e7-... from John(id = 1) for 150
---------------------------------------------------------
...
-> 7
$>
```

Follow this output format exactly — spacing, labels, and separators (`---...---`) must match.

---

## Reminders

- Use `try/catch` around every user action; display the exception message and loop back to the menu.
- Output only via `System.out`.
- The separator line between menu iterations is exactly 57 dashes: `---------------------------------------------------------`
- Only the files listed above (and all carried-over files) may be in `ex05/`.
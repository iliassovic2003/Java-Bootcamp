# Java Piscine Day 01

## OOP, Collections

An introduction to object-oriented design in Java — models, interfaces, custom data structures, service layers, and console applications. All exercises build on each other progressively toward a fully working money transfer system.

---

## 📋 Project Structure

```
Day01/
├── ex00
│   ├── Program.java
│   ├── README.md
│   ├── Transaction.java
│   └── User.java
├── ex01
│   ├── Program.java
│   ├── README.md
│   ├── UserIdsGenerator.java
│   └── User.java
├── ex02
│   ├── Program.java
│   ├── README.md
│   ├── UserIdsGenerator.java
│   ├── User.java
│   ├── UserNotFoundException.java
│   ├── UsersArrayList.java
│   └── UsersList.java
├── ex03
│   ├── Program.java
│   ├── README.md
│   ├── Transaction.java
│   ├── TransactionNotFoundException.java
│   ├── TransactionsLinkedList.java
│   ├── TransactionsList.java
│   └── User.java
├── ex04
│   ├── IllegalTransactionException.java
│   ├── Program.java
│   ├── README.md
│   ├── Transaction.java
│   ├── TransactionNotFoundException.java
│   ├── TransactionsLinkedList.java
│   ├── TransactionsList.java
│   ├── TransactionsService.java
│   ├── UserIdsGenerator.java
│   ├── User.java
│   ├── UserNotFoundException.java
│   ├── UsersArrayList.java
│   └── UsersList.java
├── ex05
│   ├── IllegalTransactionException.java
│   ├── Menu.java
│   ├── Program.java
│   ├── README.md
│   ├── Transaction.java
│   ├── TransactionNotFoundException.java
│   ├── TransactionsLinkedList.java
│   ├── TransactionsList.java
│   ├── TransactionsService.java
│   ├── UserIdsGenerator.java
│   ├── User.java
│   ├── UserNotFoundException.java
│   ├── UsersArrayList.java
│   └── UsersList.java
└── README.md

7 directories, 50 files
```

---

## Day 01 — OOP, Collections

A step-by-step implementation of an internal money transfer system. Each exercise introduces one layer of the architecture, from raw domain models to a fully interactive console application.

| Exercise | Key concept | Description |
|----------|-------------|-------------|
| [ex00](ex00/README.md) | Domain models | Define `User` and `Transaction` with validated fields |
| [ex01](ex01/README.md) | Singleton pattern | Auto-increment ID generator; read-only user IDs |
| [ex02](ex02/README.md) | Interface + array list | `UsersList` interface backed by a resizable array |
| [ex03](ex03/README.md) | Linked list | `TransactionsList` interface backed by a linked list (O(1) insert) |
| [ex04](ex04/README.md) | Facade / service layer | `TransactionsService` orchestrates users, transfers, and validity checks |
| [ex05](ex05/README.md) | Console application | Interactive menu with `production` and `dev` launch modes |

# Java Bootcamp
 
A 10-day intensive Java training covering core language fundamentals, algorithms, data structures, OOP, and more. Structured as daily progressive exercises with increasing complexity.
 
Each day focuses on a specific theme and contains a set of exercises to implement from scratch, following strict coding standards and constraints that mirror real-world disciplined development practices.
 
---
 
## Structure
 
```
java-bootcamp/
├── Day0x/
│   ├── ex00/
│   │   ├── ex00_subject.md
│   │   └── Program.java
│   ├── ex01/
│   │   ├── ex01_subject.md
│   │   └── Program.java
│   ├── ex02/
│   │   ├── ex02_subject.md
│   │   └── Program.java
│   ├── ex03/
│   │   ├── ex03_subject.md
│   │   └── Program.java
│   ├── ex04/
│   │   ├── ex04_subject.md
│   │   └── Program.java
│   └── ex05/
│       ├── ex05_subject.md
│       └── Program.java
└── ...

```
 
Each exercise folder contains:
- `exNN_subject.md` — the exercise brief and requirements
- `Program.java` — the implemented solution
---
 
## Days Overview
 
| Day | Theme |
|-----|-------|
| Day 01 | Primitive types, I/O, Strings, Arrays |
| Day 02 | *(coming soon)* |
| Day 03 | *(coming soon)* |
| Day 04 | *(coming soon)* |
| Day 05 | *(coming soon)* |
| Day 06 | *(coming soon)* |
| Day 07 | *(coming soon)* |
| Day 08 | *(coming soon)* |
| Day 09 | *(coming soon)* |
| Day 10 | *(coming soon)* |
 
---
 
## Day 01 — Primitive Types, I/O, Strings, Arrays
 
An introduction to Java's type system and fundamental building blocks. All exercises are constrained to primitive types, basic I/O, and standard operators — no user-defined classes or non-static methods allowed.
 
| Exercise | Description |
|----------|-------------|
| ex00 | Compute the digit sum of a hardcoded 6-digit integer |
| ex01 | Check primality and count iterations, with input validation |
| ex02 | Count how many numbers in a stream have a prime digit sum |
| ex03 | Visualize weekly student grades as a horizontal bar chart |
| ex04 | Frequency analysis of characters displayed as a vertical histogram |
| ex05 | MVP class timetable with student registration, scheduling, and attendance |
 
---
 
## General Rules
 
- Java **latest LTS** version required
- Code must run on both **JVM** and **GraalVM**
- Follow **Oracle Java coding standards**
- Output via `System.out` only (unless otherwise specified)
- No precalculated outputs — solutions must compute results correctly
- Each solution lives in its designated directory with only the required files
---
 
## Setup
 
```bash
# Compile
javac Program.java
 
# Run on JVM
java Program
 
# Run on GraalVM (native)
native-image Program
./program
```
 
---
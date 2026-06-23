# Exercise 03 – A Little Bit of Statistics

## Objective

Visualize students' weekly progress as a **console bar chart**. Each week's bar height represents the **minimum grade** among five test scores entered for that week.

## Constraints

- Language: Java (latest LTS)
- Types: primitive types, `String`
- Allowed methods: `String::equals`
- Input via `Scanner(System.in)`, output via `System.out` / `System.err`
- **No arrays** — store data without them
- **No string concatenation inside loops** — use `StringBuilder`
- Maximum **18 weeks**; exactly **5 grades per week** (values 1–9)
- Input ends with `42`
- No user-defined classes or non-static methods

## Behavior

- Weeks are entered in labeled blocks: `Week N` followed by 5 space-separated grades
- Input order is **not guaranteed** — if a week is entered out of sequence, print `IllegalArgument` and exit with code `-1`
- After all input, display a horizontal bar chart where bar length = minimum grade of that week (`=` per point, ending with `>`)

## Example

```
$> java Program
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
42

Week 1 ==>
Week 2 ======>
Week 3 ===>
Week 4 ====>

```

## Key Concepts

- Minimum of a set of values
- Data storage without arrays (variables or `StringBuilder` tricks)
- Ordered input validation
- Efficient string building with `StringBuilder`

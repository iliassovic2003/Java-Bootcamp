# Exercise 01 – Really Prime Number

## Objective

Determine whether a number entered by the user is **prime**, and report how many **iterations** (comparison operations) the check required.

## Constraints

- Language: Java (latest LTS)
- Only **primitive types** may be used
- Allowed constructs: conditions, loops, standard operators
- Input via `Scanner(System.in)`, output via `System.out` / `System.err`
- No arrays, no user-defined classes or non-static methods

## Behavior

- **Valid input (≥ 2):** print `true <steps>` or `false <steps>`
- **Invalid input (negative, 0, or 1):** print `IllegalArgument` to stderr and exit with code `-1`

## Examples

```
$> java Program
113
true 10

$> java Program
169
false 12

$> java Program
42
false 1

$> java Program
-100
IllegalArgument
```

## Key Concepts

- Primality checking via trial division
- Counting iterations (each comparison = 1 step)
- Early exit on finding a divisor
- Input validation and controlled exit (`System::exit`)

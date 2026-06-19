# Exercise 02 – Endless Sequence (or not?)

## Objective

Process a stream of natural numbers and **count how many** have a digit sum that is a **prime number**. The sequence ends when the value `42` is entered.

## Constraints

- Language: Java (latest LTS)
- Only **primitive types** may be used
- Allowed constructs: conditions, loops, standard operators
- Input via `Scanner(System.in)`, output via `System.out` / `System.err`
- Input data is guaranteed to be correct
- No arrays, no user-defined classes or non-static methods

## Behavior

- Read natural numbers one per line until `42` is encountered
- For each number, compute the sum of its digits
- Check whether that digit sum is prime
- Print the total count of "coffee-related" queries at the end

## Example

```
$> java Program
198131
12901212
11122
42
Count of coffee-request : 2

```

## Key Concepts

- Stream/flow processing without storing all values
- Digit sum extraction (modulo + division)
- Primality check reused from primitive operations
- Sentinel value (`42`) to terminate input

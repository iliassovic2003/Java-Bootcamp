# Exercise 04 – A Bit More of Statistics

## Objective

Perform **frequency analysis** on a string of text and display a **vertical histogram** of the 10 most frequently occurring characters, sorted by frequency (descending) and then lexicographically on ties.

## Constraints

- Language: Java (latest LTS)
- Types: primitive types, `String`, arrays
- Allowed methods: `String::equals`, `String::toCharArray`, `String::length`
- Input via `Scanner(System.in)`, output via `System.out`
- Input is a single long string ending with `\n`
- Characters fit in a `char` (Unicode BMP, max code point 65535)
- Maximum occurrences per character: **999**
- Chart max height: **10**, min height: **0**
- **No multiple passes** over the source text for sorting/deduplication — process in a single pass using a frequency array
- No user-defined classes or non-static methods

## Behavior

- Count occurrences of each character in the input string
- Select the top 10 most frequent (ties broken lexicographically)
- Scale the counts so the tallest bar = 10 rows
- Print the histogram top-down, with counts annotated on the right of the tallest bars
- Print character labels on the bottom row

## Example

```
$> java Program
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO

36
 #  35
 #   #
 #   #  27
 #   #   #
 #   #   #
 #   #   #
 #   #   #  14  12
 #   #   #   #   #   9
 #   #   #   #   #   #   7   4
 #   #   #   #   #   #   #   #   2   2
 D   A   S   W   L   K   O   T   E   R

```

## Key Concepts

- Frequency counting using a fixed-size array indexed by `char` value
- Single-pass processing (no sort on full text)
- Scaling values to fit a fixed chart height
- Top-N selection with lexicographic tie-breaking
- Column-major rendering of a vertical bar chart

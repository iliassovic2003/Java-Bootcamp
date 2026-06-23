# Exercise 05 – Schedule

## Objective

Implement an MVP **class timetable** for September 2020, supporting student registration, class scheduling, attendance recording, and tabular display.

## Constraints

- Language: Java (latest LTS)
- Types: primitive types, `String`, arrays
- Allowed methods: `String::equals`, `String::toCharArray`, `String::length`
- Input via `Scanner(System.in)`, output via `System.out` / `System.err`
- Max **10 students**, max name length **10** (no spaces)
- Classes held **Monday–Sunday**, between **1 pm and 6 pm**
- Max **10 classes per week**
- Data correctness guaranteed, **except** class ordering when populating the timetable
- No user-defined classes or non-static methods

## Lifecycle

The program runs in three sequential phases, each separated by `.` (a period on its own line):

1. **Student list** — one name per line, ended by `.`
2. **Timetable** — one class per line as `<hour> <DAY>` (e.g. `2 MO`), ended by `.`
3. **Attendance** — one record per line as `<name> <hour> <date> <HERE|NOT_HERE>`, ended by `.`
4. **Display** — print the full timetable grid

## Output Format

- Header row: all class slots as `<H>:00 <DAY> <date>|` ordered chronologically across September 2020
- One row per student: attendance status per slot (`1` = HERE, `-1` = NOT_HERE, empty if no record)

## Example

```
$> java Program
John
Mike
Alice
.
2 MO
.
John 2 7 HERE
Mike 2 7 NOT_HERE
Alice 2 7 HERE
John 2 14 NOT_HERE
Mike 2 14 HERE
Alice 2 21 HERE
John 2 28 HERE
.

           | 14:00 MO 7| 14:00 MO  | 14:00 MO  | 14:00 MO  |
-----------|-----------|-----------|-----------|-----------|
John       | 1         | -1        |           | 1         |
Mike       | -1        | 1         |           |           |
Alice      | 1         |           | 1         |           |

```

## Key Concepts

- Multi-phase input parsing with `.` as phase delimiter
- Calendar logic for September 2020 (day-of-week mapping)
- 2D array for attendance grid (students × class slots)
- Chronological ordering of class occurrences across the month
- Formatted tabular console output

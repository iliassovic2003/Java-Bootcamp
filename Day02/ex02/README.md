# Exercise 02 — File Manager

**Turn-in directory:** `ex02/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Java Collections API`, `Java IO`, `Files`, `Paths`, etc.

---

## Context

This exercise emulates the behavior of a Unix-like command-line file manager. The program starts in a directory specified at launch and lets the user navigate the filesystem, inspect contents, and move or rename files — all through a simple interactive shell loop.

---

## Launch

Pass the starting directory as a command-line argument:

```
$> java Program --current-folder=/Users/Admin
```

---

## Supported Commands

| Command | Description |
|---|---|
| `ls` | List contents of the current directory (names + sizes in KB) |
| `cd FOLDER_NAME` | Change the current directory (supports `..` for going up) |
| `mv WHAT WHERE` | Move a file to another directory, or rename it if `WHERE` is a bare filename with no path |
| `help` | *(personal addition)* Display available commands and their usage |
| `exit` | Quit the program |

---

## Example Interaction

Given this folder structure under `/Users/Admin`:

```
ADMIN/
├── folder1/
│   ├── image.jpg
│   └── animation.gif
└── folder2/
    ├── text.txt
    └── Program.java
```

```
$> java Program --current-folder=/Users/Admin

/Users/Admin
-> ls
folder1        60 KB
folder2        90 KB

-> cd folder1
/Users/Admin/folder1

-> ls
image.jpg      10 KB
animation.gif  50 KB

-> mv image.jpg image2.jpg

-> ls
image2.jpg     10 KB
animation.gif  50 KB

-> mv animation.gif ../folder2

-> ls
image2.jpg     10 KB

-> cd ../folder2
/Users/Admin/folder2

-> ls
text.txt       10 KB
Program.java   80 KB
animation.gif  50 KB

-> help
Available commands:
  ls              - List current directory contents
  cd FOLDER       - Change directory (use .. to go up)
  mv WHAT WHERE   - Move or rename a file
  help            - Show this help message
  exit            - Exit the program

-> exit
$>
```

---

## Personal Addition: `help` Command

Beyond the base requirements, I added a `help` command that prints a summary of all available commands and their syntax. This makes the program friendlier to use, especially when testing with unfamiliar directory structures, and mirrors the behavior of real Unix shells where `help` or `man` is always available.

---

## Notes

- The current path is printed after each `cd` so you always know where you are.
- Directory sizes shown by `ls` reflect the **total size of their contents** in KB.
- `mv` with a bare filename (no `/`) in `WHERE` **renames** the file in place; with a path it **moves** it.
- Use `java.nio.file.Files` and `java.nio.file.Paths` for path resolution and file operations — they handle cross-platform path edge cases more reliably than `java.io.File`.
- Test the program with your own local folder structure before submission.
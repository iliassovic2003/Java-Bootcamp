import java.util.Scanner;

public class Program {

    /* ---------------------------------------------------- */

    static int getDayIndex(String day, String[] dayAbbrevs) {
        for (int i = 0; i < dayAbbrevs.length; i++)
            if (dayAbbrevs[i].equals(day))
                return i;
        return -1;
    }

    static String padRight(String text, int width) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < width)
            sb.append(" ");
        return sb.toString();
    }

    static String buildLabel(int hour, String day, int date, int colWidth) {
        String h      = (hour + 12) + ":00";
        String full   = h + " " + day + " " + date;
        String noDate = h + " " + day;

        if (full.length()   <= colWidth) return full;
        if (noDate.length() <= colWidth) return noDate;
        if (h.length()      <= colWidth) return h;
        return h.substring(0, colWidth);
    }

    /* ---------------------------------------------------- */

    static void validateHour(int hour) throws Exception {
        if (hour < 1 || hour > 6)
            throw new Exception("class hour must be between 1 and 6 (pm), got: " + hour);
    }

    static void validateDay(String day, String[] dayAbbrevs) throws Exception {
        if (getDayIndex(day, dayAbbrevs) == -1)
            throw new Exception("invalid day abbreviation: " + day);
    }

    static void validateName(String name) throws Exception {
        if (name.length() > 10)
            throw new Exception("student name too long: " + name);
        if (name.contains(" "))
            throw new Exception("student name cannot contain spaces: " + name);
    }

    static void validateStatus(String status) throws Exception {
        if (!status.equals("HERE") && !status.equals("NOT_HERE"))
            throw new Exception("invalid attendance status: " + status);
    }

    static void validateDate(int date) throws Exception {
        if (date < 1 || date > 30)
            throw new Exception("invalid date for September: " + date);
    }

    /* ---------------------------------------------------- */

    static int readStudents(Scanner s, String[] students) throws Exception {
        int    count = 0;
        String input = s.nextLine();

        while (!input.equals(".")) {
            if (count >= 10)
                throw new Exception("maximum 10 students allowed");
            validateName(input);
            students[count++] = input;
            input = s.nextLine();
        }

        return count;
    }

    static int readClasses(Scanner s, int[] classHours, String[] classDays,
                           String[] dayAbbrevs) throws Exception {
        int    count = 0;
        String input = s.nextLine();

        while (!input.equals(".")) {
            if (count >= 10)
                throw new Exception("maximum 10 classes per week allowed");

            String[] parts = input.split(" ");
            if (parts.length != 2)
                throw new Exception("invalid class format: " + input);

            int hour = Integer.parseInt(parts[0]);
            validateHour(hour);
            validateDay(parts[1], dayAbbrevs);

            classHours[count] = hour;
            classDays[count]  = parts[1];
            count++;
            input = s.nextLine();
        }

        return count;
    }

    /* ---------------------------------------------------- */

    static int expandSlots(int[] classHours, String[] classDays, int classCount,
                           int[] allDates, int[] allHours, String[] allDayAbbr,
                           String[] dayAbbrevs) {
        int septStart  = 1;
        int totalSlots = 0;

        for (int c = 0; c < classCount; c++) {
            int targetDay = getDayIndex(classDays[c], dayAbbrevs);
            int firstDate = 1 + ((targetDay - septStart + 7) % 7);

            for (int date = firstDate; date <= 30; date += 7) {
                allDates[totalSlots]   = date;
                allHours[totalSlots]   = classHours[c];
                allDayAbbr[totalSlots] = classDays[c];
                totalSlots++;
            }
        }

        return totalSlots;
    }

    static void sortSlots(int[] allDates, int[] allHours, String[] allDayAbbr, int totalSlots) {
        for (int i = 0; i < totalSlots - 1; i++) {
            for (int j = 0; j < totalSlots - i - 1; j++) {
                if (allDates[j] > allDates[j + 1]) {
                    int    tempDate   = allDates[j];
                    allDates[j]       = allDates[j + 1];
                    allDates[j + 1]   = tempDate;

                    int    tempHour   = allHours[j];
                    allHours[j]       = allHours[j + 1];
                    allHours[j + 1]   = tempHour;

                    String tempDay    = allDayAbbr[j];
                    allDayAbbr[j]     = allDayAbbr[j + 1];
                    allDayAbbr[j + 1] = tempDay;
                }
            }
        }
    }

    /* ---------------------------------------------------- */

    static void readAttendance(Scanner s, int[][] attendance,
                               String[] students, int studentCount,
                               int[] allDates, int[] allHours,
                               int totalSlots) throws Exception {
        String input = s.nextLine();

        while (!input.equals(".")) {
            String[] parts = input.split(" ");
            if (parts.length != 4)
                throw new Exception("invalid attendance format: " + input);

            String name   = parts[0];
            int    hour   = Integer.parseInt(parts[1]);
            int    date   = Integer.parseInt(parts[2]);
            String status = parts[3];

            validateDate(date);
            validateStatus(status);

            int studentIdx = -1;
            for (int i = 0; i < studentCount; i++) {
                if (students[i].equals(name)) {
                    studentIdx = i;
                    break;
                }
            }
            if (studentIdx == -1)
                throw new Exception("unknown student: " + name);

            int slotIdx = -1;
            for (int i = 0; i < totalSlots; i++) {
                if (allDates[i] == date && allHours[i] == hour) {
                    slotIdx = i;
                    break;
                }
            }
            if (slotIdx == -1)
                throw new Exception("no class at hour " + hour + " on date " + date);

            attendance[studentIdx][slotIdx] = status.equals("HERE") ? 1 : -1;
            input = s.nextLine();
        }
    }

    /* ---------------------------------------------------- */

    static void printHeader(int[] allHours, String[] allDayAbbr, int[] allDates,
                            int totalSlots, int colWidth) {
        StringBuilder header = new StringBuilder();
        header.append(padRight("", 11)).append("|");

        for (int i = 0; i < totalSlots; i++) {
            String label = buildLabel(allHours[i], allDayAbbr[i], allDates[i], colWidth);
            header.append(" ").append(padRight(label, colWidth)).append("|");
        }

        System.out.println(header.toString());
    }

    static void printSeparator(int totalSlots, int colWidth) {
        StringBuilder separator = new StringBuilder();
        separator.append(padRight("", 11).replace(' ', '-')).append("|");

        for (int i = 0; i < totalSlots; i++)
            separator.append("-").append(padRight("", colWidth).replace(' ', '-')).append("|");

        System.out.println(separator.toString());
    }

    static void printStudentRows(String[] students, int studentCount,
                                 int[][] attendance, int totalSlots, int colWidth) {
        for (int i = 0; i < studentCount; i++) {
            StringBuilder row = new StringBuilder();
            row.append(padRight(students[i], 11)).append("|");

            for (int j = 0; j < totalSlots; j++) {
                String cell = "";
                if (attendance[i][j] == 1)
                    cell = "1";
                else if (attendance[i][j] == -1)
                    cell = "-1";
                row.append(" ").append(padRight(cell, colWidth)).append("|");
            }

            System.out.println(row.toString());
        }
    }

    /* ---------------------------------------------------- */

    public static void main(String[] args) {
        try {
            Scanner  s          = new Scanner(System.in);
            String[] dayAbbrevs = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

            String[] students     = new String[10];
            int      studentCount = readStudents(s, students);

            int[]    classHours = new int[10];
            String[] classDays  = new String[10];
            int      classCount = readClasses(s, classHours, classDays, dayAbbrevs);

            int[]    allDates   = new int[100];
            int[]    allHours   = new int[100];
            String[] allDayAbbr = new String[100];
            int      totalSlots = expandSlots(classHours, classDays, classCount,
                                              allDates, allHours, allDayAbbr, dayAbbrevs);
            sortSlots(allDates, allHours, allDayAbbr, totalSlots);

            int[][] attendance = new int[10][100];
            readAttendance(s, attendance, students, studentCount, allDates, allHours, totalSlots);

            int colWidth = 10;
            printHeader(allHours, allDayAbbr, allDates, totalSlots, colWidth);
            printSeparator(totalSlots, colWidth);
            printStudentRows(students, studentCount, attendance, totalSlots, colWidth);

            s.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
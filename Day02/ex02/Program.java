import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Program {
    private String currPath;
    private String previousPath;

    public void printCurrPath() {
        System.out.printf("%s%n%n", this.currPath);
    }

    /* -----------------------------MV----------------------------- */

    public void mv(String filename, String toPath) {

        if (filename == null || filename.trim().isEmpty()) {
            System.out.println("mv: Missing source file");
            return;
        }
        if (toPath == null || toPath.trim().isEmpty()) {
            System.out.println("mv: Missing destination");
            return;
        }
        
        filename = filename.trim();
        toPath = toPath.trim();
        
        File source = new File(this.currPath + File.separator + filename);
        
        if (!source.exists()) {
            System.out.println("mv: " + filename + ": No such file or directory");
            return;
        }
        
        File dest;
        File toPathFile = new File(toPath);
        
        if (toPathFile.isAbsolute()) {

            if (toPathFile.exists() && toPathFile.isDirectory())
                dest = new File(toPathFile, source.getName());
            else 
                dest = toPathFile;

        }
        else if (toPath.contains(File.separator) || toPath.contains("/") || toPath.contains("\\")) {
            dest = new File(this.currPath + File.separator + toPath);

            if (dest.exists() && dest.isDirectory())
                dest = new File(dest, source.getName());

        }
        else 
            dest = new File(this.currPath + File.separator + toPath);
        
        try {
            if (source.getCanonicalPath().equals(dest.getCanonicalPath())) {
                System.out.println("mv: '" + filename + "' and '" + toPath + "' are the same file");
                return;
            }
        } catch (IOException e) {}
        
        boolean success = source.renameTo(dest);
        if (!success) {
            try {
                Files.move(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Moved: " + filename + " -> " + dest.getName());
            } catch (IOException e) {
                System.out.println("mv: Error: " + e.getMessage());
            }
        } else {
            System.out.println("Moved: " + filename + " -> " + dest.getName());
        }
    }

    /* -----------------------------LS----------------------------- */

    private String getFileType(File file) {
        if (file.isDirectory())
            return "📁 DIR";

        if (file.isFile()) {
            try {
                if (Files.isSymbolicLink(file.toPath()))
                    return "🔗 LINK ";
            } catch (Exception e) {}
            return "📄 FILE ";
        }
        else 
            return "❓ OTHER";
    }

    private String getFileSize(File file) {
        if (file.isDirectory()) {
            String[]    contents = file.list();

            return contents == null ? "  0 items" : String.format("%3d items", contents.length);
        }
        
        long            bytes = file.length();
        
        if (bytes < 1024) 
            return String.format("%4d B", bytes);
        if (bytes < 1024 * 1024)
            return String.format("%4.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024)
            return String.format("%4.1f MB", bytes / (1024.0 * 1024));
        else
            return String.format("%4.1f GB", bytes / (1024.0 * 1024 * 1024));
    }

    private String getPermissions(File file) {
        String          perms = "";
        
        perms += file.canRead()     ? "r" : "-";
        perms += file.canWrite()    ? "w" : "-";
        perms += file.canExecute()  ? "x" : "-";
        perms += file.isHidden()    ? "h" : "-";
        
        return perms;
    }

    public void ls() {
        File            dir = new File(this.currPath);
        File[]          files = dir.listFiles();

        if (files == null) {
            System.out.println("Cannot read directory: " + this.currPath);
            return;
        }

        Arrays.sort(files);

        for (File file : files) {
            String      name = file.getName();
            if (name.isEmpty())
                name = file.getPath();
            
            String      type = getFileType(file);
            String      size = getFileSize(file);
            String      permissions = getPermissions(file);
            
            System.out.printf("%s  %s  %s  %s%n", 
                permissions, type, size, name);
        }
        System.out.printf("%n");

    }

    /* -----------------------------CD----------------------------- */

    public void cd(String toPath) {

        if (toPath == null || toPath.isEmpty())
            return;

        toPath = toPath.trim();

        String newPath = null;
        String oldPath = this.currPath;
        

        if (toPath.equals("~"))
            newPath = System.getProperty("user.home");
        else if (toPath.equals("..")) {
            File parent = new File(currPath).getParentFile();
            
            if (parent != null)
                newPath = parent.getAbsolutePath();
            else {
                System.out.println("cd: Already at root directory");
                return;
            }
        }
        else if (toPath.equals("."))
            return;
        else if (toPath.equals("-")) {
            if (previousPath != null)
                newPath = previousPath;
            else {
                System.out.println("cd: No previous directory");
                return;
            }
        }
        else if (toPath.startsWith("/") || toPath.matches("^[A-Za-z]:\\\\.*"))
            newPath = toPath;
        else
            newPath = currPath + File.separator + toPath;

        try {
            File tempFile = new File(newPath);
            newPath = tempFile.getCanonicalPath();
        } catch (IOException e) {
            System.out.println("cd: " + toPath + ": Error resolving path");
            return;
        }

        File targetDir = new File(newPath);
        
        if (targetDir.exists()) {
            if (targetDir.isDirectory()) {
                this.previousPath = oldPath;
                this.currPath = newPath;

                printCurrPath();
            }
            else
                System.out.println("cd: " + toPath + ": Not a directory");
        }
        else
            System.out.println("cd: " + toPath + ": No such file or directory");
        
    }

    /* ----------------------------HELP---------------------------- */

    public void help() {
        System.out.println("\nAvailable commands:");
        System.out.println("  cd <folder>   - Change directory");
        System.out.println("  cd ..         - Go to parent directory");
        System.out.println("  cd ~          - Go to home directory");
        System.out.println("  cd -          - Go to previous directory");
        System.out.println("  ls            - List current directory contents");
        System.out.println("  mv <filename> <toPath> - Move or rename file");
        System.out.println("  help          - Show this help");
        System.out.println("  exit          - Exit the program");
        System.out.println();
    }

    /* ----------------------------MAIN---------------------------- */

    public static void main(String[] args) {

        try {
            String          startPath = System.getProperty("user.dir");
            
            if (args.length == 1) {
                String[] parts = args[0].split("=");

                if (parts.length == 2 && parts[0].equals("--current-folder"))
                    startPath = parts[1];
            }

            File startDir = new File(startPath);
            
            if (!startDir.exists() || !startDir.isDirectory()) {
                System.err.println("Error: Invalid directory: " + startPath);
                System.exit(1);
            }

            Program program = new Program();
            program.currPath = startPath;
            program.printCurrPath();

            Scanner         s = new Scanner(System.in);
            while (true) {

                String      line = s.nextLine().trim();
                if (line.isEmpty())
                    continue;
                
                String[]    parts = line.split("\\s+");

                if (parts[0] == null)
                    continue;

                String command = parts[0];
                
                if (command.equals("exit")) {
                    System.out.println("Exiting...");
                    s.close();
                    System.exit(0);
                    break;
                }
                else if (command.equals("ls") && parts.length == 1)
                    program.ls();
                else if (command.equals("cd") && parts.length == 2)
                    program.cd(parts[1]);
                else if (command.equals("cd") && parts.length == 1)
                    program.cd("~");
                else if (command.equals("mv") && parts.length == 3)
                    program.mv(parts[1], parts[2]);
                else if (command.equals("pwd") && parts.length == 1)
                    program.printCurrPath();
                else if (command.equals("help") && parts.length == 1)
                    program.help();
                else
                    System.out.println("Unknown command or invalid arguments. Type 'help' for available commands.");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
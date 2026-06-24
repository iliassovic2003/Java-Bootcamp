public class Program {
    public static void main(String[] args) {
        boolean mode = true;

        for (String arg : args)
            if ("--profile=dev".equals(args[0]))
                mode = false;

        Menu menu = new Menu();
        menu.startMenu(mode);
    }
}
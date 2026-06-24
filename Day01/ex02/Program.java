public class Program {
    public static void main(String[] args) {
        try {
            UsersList users = new UsersArrayList();

            users.addUser(new User(1000, "Alice"));
            users.addUser(new User(2500, "Bob"));
            users.addUser(new User(0,    "Charlie"));
            users.addUser(new User(750,  "Diana"));
            users.addUser(new User(300,  "Eve"));

            System.out.println();

            System.out.println("Total users: " + users.countUsers());
            System.out.println();

            System.out.println("--- Retrieve by index ---");
            for (int i = 0; i < users.countUsers(); i++) {
                System.out.println("  [" + i + "] " + users.searchUserByIndex(i));
            }
            System.out.println();

            System.out.println("--- Retrieve by ID ---");
            System.out.println("  ID 1 -> " + users.searchUserById(1));
            System.out.println("  ID 3 -> " + users.searchUserById(3));
            System.out.println();

            System.out.println("--- Filling array to trigger resize ---");
            users.addUser(new User(100, "Frank"));
            users.addUser(new User(200, "Grace"));
            users.addUser(new User(300, "Hank"));
            users.addUser(new User(400, "Ivy"));
            users.addUser(new User(500, "Jack"));
            users.addUser(new User(600, "Karen"));
            System.out.println();
            System.out.println("Total users after resize: " + users.countUsers());
            System.out.println();

            // System.out.println("--- UserNotFoundException (bad ID) ---");
            // users.searchUserById(999);

            // System.out.println("--- UserNotFoundException (bad index) ---");
            // users.searchUserByIndex(-1);

            // System.out.println("--- IllegalArgumentException (negative balance) ---");
            // users.addUser(new User(-50, "BadUser"));

        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException: " + e.getMessage());
        } catch (UserNotFoundException e) {
            System.err.println("UserNotFoundException: " + e.getMessage());
        }
    }
}
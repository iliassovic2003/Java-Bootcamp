import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        try {
            User user1 = new User(50, "John");
            User user2 = new User(10, "Moe");
            User user3 = new User(1005, "Diana");
            User user4 = new User(65, "Eve");

            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user3);
            System.out.println(user4);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}
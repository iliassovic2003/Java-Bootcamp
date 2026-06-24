import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        try {
            User user1 = new User(1, 50, "John Smith");
            User user2 = new User(2, 10, "Moe Lester");

            System.out.println("=== BEFORE TRANSACTION ===");
            System.out.println(user1);
            System.out.println(user2);
            System.out.println("");

            Transaction newTransaction = new Transaction();

            newTransaction.createTransaction(
                UUID.randomUUID(),
                user1,
                user2,
                Transaction.TransferType.DEBIT,
                100
            );

            newTransaction.validateTransaction();

            System.out.println("\n=== AFTER TRANSACTION ===");
            System.out.println(user1);
            System.out.println(user2);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
}
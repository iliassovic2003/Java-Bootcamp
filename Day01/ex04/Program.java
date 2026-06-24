import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        try {
            TransactionsService service = new TransactionsService();

            System.out.println("=== 1. Adding Users ===");
            User alice   = new User(1000, "Alice");
            User bob     = new User(500,  "Bob");
            User charlie = new User(750,  "Charlie");

            service.addUser(alice);
            service.addUser(bob);
            service.addUser(charlie);

            System.out.println("\n=== 2. Balances ===");
            System.out.println("Alice:   " + service.getUserBalance(alice));
            System.out.println("Bob:     " + service.getUserBalance(bob));
            System.out.println("Charlie: " + service.getUserBalance(charlie));

            service.makeTransfer(1, 2, 200);
            service.makeTransfer(2, 3, 100);
            service.makeTransfer(3, 1, 50);

            System.out.println("\n=== 3. Balances After Transfers ===");
            System.out.println("Alice:   " + service.getUserBalance(alice));
            System.out.println("Bob:     " + service.getUserBalance(bob));
            System.out.println("Charlie: " + service.getUserBalance(charlie));

            System.out.println("\n=== 4. Alice's Transactions ===");
            Transaction[] aliceTxs = service.getTransfersByID(alice.getIdentifier());
            for (Transaction t : aliceTxs)
                System.out.println("  " + t);

            System.out.println("\n=== 5. Invalid Transactions (expect none) ===");
            Transaction[] invalid = service.getInvalidTransactions();
            if (invalid.length == 0)
                System.out.println("  All transactions are valid.");
            else
                for (Transaction t : invalid)
                    System.out.println("  INVALID: " + t);

            System.out.println("\n=== 6. Removing Alice's first transaction ===");
            UUID removedId = aliceTxs[0].getIdentifier();
            service.removeTransactionByID(removedId, 1);
            System.out.println("  Removed transaction: " + removedId);

            System.out.println("\n=== 7. Invalid Transactions (expect 1) ===");
            invalid = service.getInvalidTransactions();
            if (invalid.length == 0)
                System.out.println("  All transactions are valid.");
            else
                for (Transaction t : invalid)
                    System.out.println("  INVALID: " + t);

            // System.out.println("\n=== 8. Overdraft Attempt ===");
            // service.makeTransfer(2, 1, 99999);

            // System.out.println("\n=== 8. Non-existent User ===");
            // service.makeTransfer(99, 1, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
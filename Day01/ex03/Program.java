import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        try {            
            User alice  =   new User(1, 1000, "Alice");
            User bob    =   new User(2, 500, "Bob");
            User charlie =  new User(3, 750, "Charlie");
            
            TransactionsList list = new TransactionsLinkedList();

            /*------------------------------------------------------*/
            
            System.out.println("1. Adding transactions:");

            Transaction tx1 = new Transaction();
            tx1.createTransaction(UUID.randomUUID(),
                alice,
                bob,
                Transaction.TransferType.DEBIT,
                200
            );
            tx1.validateTransaction();
            
            Transaction tx2 = new Transaction();
            tx2.createTransaction(UUID.randomUUID(),
                bob,
                charlie,
                Transaction.TransferType.DEBIT,
                100
            );
            tx2.validateTransaction();
            
            Transaction tx3 = new Transaction();
            tx3.createTransaction(UUID.randomUUID(),
                bob,
                alice,
                Transaction.TransferType.CREDIT,
            150);
            tx3.validateTransaction();

            /*------------------------------------------------------*/
            
            list.addTransaction(tx1);
            list.addTransaction(tx2);
            list.addTransaction(tx3);
            
            System.out.println("\n  Total transactions: " + list.size());
            
            /*------------------------------------------------------*/

            System.out.println("\n2. Converting to array:");

            Transaction[] array = list.toArray();
            System.out.println("  Array length: " + array.length);

            for (int i = 0; i < array.length; i++)
                System.out.println("  [" + i + "] " + array[i]);

            /*------------------------------------------------------*/
            
            System.out.println("\n3. Removing transaction:");

            UUID removeId = tx2.getIdentifier();
            list.removeTransactionByID(removeId);

            System.out.println("  Removed! Size now: " + list.size());

            /*------------------------------------------------------*/
            
            System.out.println("\n4. Remaining transactions:");

            Transaction[] remaining = list.toArray();
            for (int i = 0; i < remaining.length; i++)
                System.out.println("  [" + i + "] " + remaining[i]);

            /*------------------------------------------------------*/
            
            // System.out.println("\n5. Removing non-existent transaction:");
            // list.removeTransactionByID(UUID.randomUUID());

            /*------------------------------------------------------*/
            
            System.out.println("\n5. Users transactions:");

            Transaction[] aliceTxs = alice.getTransactions();
            System.out.println("  Alice's transactions (" + aliceTxs.length + "):");
            for (Transaction t : aliceTxs)
                System.out.println("    " + t);

            Transaction[] bobTxs = bob.getTransactions();
            System.out.println("\n  Bob's transactions (" + bobTxs.length + "):");
            for (Transaction t : bobTxs)
                System.out.println("    " + t);

            Transaction[] charlieTxs = charlie.getTransactions();
            System.out.println("\n  Charlie's transactions (" + charlieTxs.length + "):");
            for (Transaction t : charlieTxs)
                System.out.println("    " + t);

            /*------------------------------------------------------*/
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    public TransactionsService service;

    public Menu() {
        this.service = new TransactionsService();
    }

    public void addUserPrompt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a user name and a balance");

        String  name = s.next();
        int     balance = s.nextInt();

        User    tmp = new User(balance, name);
        this.service.addUser(tmp);

        System.out.println("User with id = " + tmp.getIdentifier() + " is added");
    }

    public void balancePromptByUser() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a user ID");

        int     userID = s.nextInt();
        User    tmp = this.service.searchUserById(userID);
        
        System.out.println(tmp.getName() + "-" + this.service.getUserBalance(tmp));
    }

    public void tranferPrompt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        
        int senderID    = s.nextInt();
        int recipientID = s.nextInt();
        int amount      = s.nextInt();

        this.service.makeTransfer(senderID, recipientID, amount);
        System.out.println("The transfer is completed");
    }

    public void transactionPrompt() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a user ID");
        
        int userID = s.nextInt();

        Transaction[] tmpArray = this.service.getTransfersByID(userID);

        for (int i = 0; i < tmpArray.length; i++) {
            Transaction tmp = tmpArray[i];

            if (tmp.getCategory() == Transaction.TransferType.CREDIT)
                System.out.print("To " + tmp.getSender().getName()
                    + " (id = " + tmp.getSender().getIdentifier()
                    + ") -");
            else
                System.out.print("From " + tmp.getRecipient().getName()
                    + " (id = " + tmp.getRecipient().getIdentifier()
                    + ") +");

            System.out.println(tmp.getAmount() + " with id = " + tmp.getIdentifier());
        }
    }

    public void removePrompt(boolean mode) {
        if (mode)
            throw new IllegalArgumentException("User have no permission");

        Scanner s = new Scanner(System.in);
        System.out.println("Enter a user ID and a transfer ID");
        
        int userID          = s.nextInt();
        UUID transactionID  = UUID.fromString(s.next());

        Transaction[] txArray  = this.service.getTransfersByID(userID);
        Transaction   found    = null;

        for (Transaction tx : txArray)
            if (tx.getIdentifier().equals(transactionID)) {
                found = tx;
                break;
            }

        this.service.removeTransactionByID(transactionID, userID);

        System.out.print("Transfer ");
        if (found.getCategory() == Transaction.TransferType.CREDIT)
            System.out.print("To " + found.getSender().getName() + "(id = " + found.getSender().getIdentifier());
        else
            System.out.print("From " + found.getRecipient().getName() + "(id = " + found.getRecipient().getIdentifier());
        System.out.println(") " + found.getAmount() + " removed");
    }

    public void validityPrompt(boolean mode) {
        if (mode)
            throw new IllegalArgumentException("User have no permission");

        Transaction[] tmpArray = this.service.getInvalidTransactions();
        System.out.println("Check results :");
        
        for(int i = 0; i < tmpArray.length; i++) {
            Transaction tmp = tmpArray[i];

            if (tmp.getCategory() == Transaction.TransferType.CREDIT)
                System.out.println(tmp.getSender().getName()
                    + "(id = " + tmp.getSender().getIdentifier()
                    + ") has an unacknowledged transfer id = "
                    + tmp.getIdentifier() + " To "+ tmp.getRecipient().getName() + "(id = " 
                    + tmp.getRecipient().getIdentifier() + ") for "
                    + tmp.getAmount());
            else
                System.out.println(tmp.getRecipient().getName()
                    + "(id = " + tmp.getRecipient().getIdentifier()
                    + ") has an unacknowledged transfer id = "
                    + tmp.getIdentifier() + " From " + tmp.getSender().getName() + "(id = "
                    + tmp.getSender().getIdentifier() + ") for "
                    + tmp.getAmount());
        }

        if (tmpArray.length == 0)
            System.out.println("no data to be shown...");
    }

    public void menuPrompt() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
        
    }

    public void startMenu(boolean mode) {
        Scanner s = new Scanner(System.in);

        while (true) {
            this.menuPrompt();
            Integer index = s.nextInt();

            switch(index) {
                case 1:
                    try {
                        addUserPrompt();
                    } catch (Exception e) {
                        System.out.print("Invalid Name or Balance");
                        if (!mode)
                            System.out.print(e.getMessage());
                        System.out.println();
                    }
                    break;

                case 2:
                    try {
                        balancePromptByUser();
                    } catch (Exception e) {
                        System.out.print("Invalid ID");
                        if (!mode)
                            System.out.print(e.getMessage());
                        System.out.println();
                    }
                    break;

                case 3:
                    try {
                        tranferPrompt();
                    } catch (Exception e) {
                        System.out.print("Failed Transfer: ");
                        if (!mode)
                            System.out.print(e.getMessage());
                        System.out.println();
                    }
                    break;

                case 4:
                    try {
                        transactionPrompt();
                    } catch (Exception e) {
                        System.out.print("Failed History Check: ");
                        if (!mode)
                            System.out.print(e.getMessage());
                        System.out.println();
                    }
                    break;

                case 5:
                    try {
                        removePrompt(mode);
                    } catch (Exception e) {
                        System.out.println("Failed to Delete: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        validityPrompt(mode);
                    } catch (Exception e) {
                        System.out.println("Failed to check validity: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid option, try again.");
            }
            
            System.out.println("---------------------------------------------------------");
        }

    }
}
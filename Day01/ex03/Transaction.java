import java.util.UUID;

public class Transaction {
    private UUID            Identifier;
    private User            Sender;
    private User            Recipient;
    private TransferType    Category;
    private Integer         Amount;
    private Transaction     next;

    /* ------------------------ENUM-------------------------- */

    public enum TransferType {
        DEBIT,
        CREDIT
    }

    /* -----------------------GETTERS------------------------ */

    public UUID getIdentifier() {
        return this.Identifier;
    }
    
    public User getSender() {
        return this.Sender;
    }
    
    public User getRecipient() {
        return this.Recipient;
    }
    
    public TransferType getCategory() {
        return this.Category;
    }

    public Integer getAmount() {
        return this.Amount;
    }

    public Transaction getNext() {
        return next;
    }

    /* -------------------CONSTRUCTOR----------------------- */

    public Transaction() {}

    /* -----------------------SETTER------------------------ */

    public void createTransaction(UUID identifier, User sender,
                            User recipient, TransferType category, Integer amount) {
        if (!isValidAmount(amount))
            throw new IllegalArgumentException("Transaction Creation: Amount cannot be negative.");

        this.Identifier = identifier;
        this.Sender = sender;
        this.Recipient = recipient;
        this.Category = category;
        this.Amount = amount;
        this.next = null;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    /* ---------------------VALIDATOR----------------------- */

    public boolean isValidAmount(Integer amount) {
        return amount >= 0;
    }

    public boolean isValidUser(User user) {
        return user != null;
    }

    /* ------------------------UTILS------------------------ */

    public void validateTransaction() {

        User tempSender = this.getSender();
        if (!isValidUser(tempSender))
            throw new IllegalArgumentException("Transaction(): Sender Unkown");

        User tempRecipient = this.getRecipient();
        if (!isValidUser(tempRecipient))
            throw new IllegalArgumentException("Transaction(): Recipient Unkown");

        Integer tempAmount = this.getAmount();
        if (tempSender.getBalance() - tempAmount < 0)
            throw new IllegalArgumentException("Transaction(): Not Enough Balance");

        tempSender.setBalance(tempSender.getBalance() - tempAmount);
        tempRecipient.setBalance(tempRecipient.getBalance() + tempAmount);

        tempSender.addTransaction(this);
        tempRecipient.addTransaction(this);
        
        System.out.println("   Transaction Completed Successfully");
    }

    @Override
    public String toString() {
        return String.format("Transaction{id=%s, from=%s, to=%s, amount=%d, type=%s}", 
                            Identifier, Sender.getName(), Recipient.getName(), 
                            Amount, Category);
    }
}
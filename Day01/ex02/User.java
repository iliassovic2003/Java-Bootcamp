public class User {
    private Integer     Identifier;
    private Integer     Balance;
    private String      Name;

    /* -----------------------GETTERS------------------------ */

    public Integer getIdentifier() {
        return this.Identifier;
    }

    public Integer getBalance() {
        return this.Balance;
    }

    public String  getName() {
        return this.Name;
    }

    /* ----------------------SETTERS------------------------ */

    public void setBalance(Integer balance) {
        if (!isValidBalance(balance))
            throw new IllegalArgumentException("User(): Balance can't be negative");
        
        this.Balance = balance;
    }

    public void initUser(Integer balance, String name) {
        this.Identifier = UserIdsGenerator.getInstance().generateId();
        this.Name = name;
        this.setBalance(balance);
    }

    /* ---------------------VALIDATOR----------------------- */
    
    public boolean isValidBalance(Integer balance) {
        return balance >= 0;
    }

    /* -------------------CONSTRUCTORS---------------------- */

    public User(Integer balance, String name) {
        this.initUser(balance, name);
    }

    public User(User other) {
        this.initUser(other.getBalance(), other.getName());
    }

    /* ------------------------UTILS------------------------ */

    public void updateBalance(Integer Amount) {
        this.setBalance(this.getBalance() + Amount);
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name='%s', balance=%d}", 
                            Identifier, Name, Balance);
    }
}
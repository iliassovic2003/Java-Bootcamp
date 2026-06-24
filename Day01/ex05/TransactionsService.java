import java.util.UUID;

public class TransactionsService {
    private UsersList    userList;

    public TransactionsService() {
        this.userList = new UsersArrayList();
    }

    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("add_User(): User can't be null");

        this.userList.addUser(user);
    }

    public Integer getUserBalance(User user) {
        if (user == null)
            throw new IllegalArgumentException("getBalance(): User can't be null");

        return (user.getBalance());
    }

    public User searchUserById(Integer userID) {
        if (userID <= 0)
            throw new IllegalArgumentException("search_User(): ID can't be null");

        return this.userList.searchUserById(userID);
    }

    public void makeTransfer(Integer senderID, Integer recipientID, Integer amount) {
        
        User        tempSender = this.userList.searchUserById(senderID);
        User        tempRecipient = this.userList.searchUserById(recipientID);

        UUID        txID = UUID.randomUUID();

        Transaction txS = new Transaction();
        Transaction txR = new Transaction();

        txS.createTransaction(txID, tempSender, tempRecipient, Transaction.TransferType.DEBIT, amount);
        txR.createTransaction(txID, tempSender, tempRecipient, Transaction.TransferType.CREDIT, amount);
        
        tempSender.setBalance(tempSender.getBalance() - amount);
        tempRecipient.setBalance(tempRecipient.getBalance() + amount);

        tempSender.addTransaction(txS);
        tempRecipient.addTransaction(txR);
    }

    public Transaction[] getTransfersByID(Integer id) {
        if (id <= 0)
            throw new IllegalArgumentException("getTranfers(): invalid ID");

        User user = this.userList.searchUserById(id);

        return user.getTransactions();
    }

    public void removeTransactionByID(UUID transactionID, Integer userID) {
        
        User tempUser = this.userList.searchUserById(userID);
        tempUser.removeTransactionByID(transactionID);
    }

    public Transaction[] getInvalidTransactions() {
        User[] allUsers = this.userList.getAllUsers();

        Integer total = 0;
        for (User user : allUsers)
            total += user.getTransactions().length;

        if (total == 0)
            return new Transaction[0];

        Transaction[] allTransactions = new Transaction[total];
        int index = 0;

        for (User user : allUsers)
            for (Transaction tx : user.getTransactions())
                allTransactions[index++] = tx;

        UUID[]          transactionIds = new UUID[total];
        int[]           occurrenceCounts = new int[total];
        Transaction[]   firstTransaction = new Transaction[total];
        Integer         uniqueCount = 0;

        for (Transaction tx : allTransactions) {
            UUID    currentId = tx.getIdentifier();
            boolean found = false;
            
            for (int i = 0; i < uniqueCount; i++) {
                if (transactionIds[i].equals(currentId)) {
                    occurrenceCounts[i]++;
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                transactionIds[uniqueCount] = currentId;
                occurrenceCounts[uniqueCount] = 1;
                firstTransaction[uniqueCount] = tx;
                uniqueCount++;
            }
        }

        int unpaired = 0;
        for (int i = 0; i < uniqueCount; i++)
            if (occurrenceCounts[i] != 2)
                unpaired++;

        Transaction[] result = new Transaction[unpaired];
        int r = 0;
        for (int i = 0; i < uniqueCount; i++)
            if (occurrenceCounts[i] != 2)
                result[r++] = firstTransaction[i];

        return result;
    }
}
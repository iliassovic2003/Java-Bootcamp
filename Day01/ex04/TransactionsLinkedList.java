import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node    head;
    private Node    tail;

    private Integer size;

    /* -------------------STRUCT-WRAPPER--------------------- */

    class Node {
        Transaction data;
        Node next;
        
        Node(Transaction data) {
            this.data = data;
            this.next = null;
        }
    }

    /* ----------------------GETTER-------------------------- */

    @Override
    public Integer size() {
        return this.size;
    }

    /* -------------------CONSTRUCTOR----------------------- */

    public TransactionsLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /* ---------------------VALIDATOR----------------------- */

    public boolean isValidTransaction(Transaction transaction) {
        return transaction != null;
    }

    /* ------------------------UTILS------------------------ */

    @Override
    public void addTransaction(Transaction transaction) {
        if (!isValidTransaction(transaction))
            throw new IllegalArgumentException("Add_Transaction(): Transaction not found");

        Node node = new Node(transaction);
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        size++;
    }

    @Override
    public void removeTransactionByID(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("Del_Transaction(): ID cannot be null");
        if (this.head == null)
            throw new TransactionNotFoundException("Del_Transaction(): Empty List");

        if (this.head.data.getIdentifier().equals(id)) {
            this.head = this.head.next;
            if (this.head == null)
                this.tail = null;
            size--;
            return;
        }

        Node current  = head;
        Node previous = null;
        while (current != null) {
            if (current.data.getIdentifier().equals(id)) {
                previous.next = current.next;
                if (current == tail)
                    tail = previous;
                size--;
                return;
            }
            previous = current;
            current  = current.next;
        }
        throw new TransactionNotFoundException(
            "Del_Transaction(): Transaction with ID " + id + " not found");
    }
    
    @Override
    public Transaction[] toArray() {
        Transaction[] array   = new Transaction[size];
        Node          current = head;
        for (int i = 0; current != null; current = current.next)
            array[i++] = current.data;
        return array;
    }
}
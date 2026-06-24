public class UsersArrayList implements UsersList {
    private User[]   Array;
    private Integer  emptyCells;
    private Integer  ArraySize;

    /* -----------------------GETTERS------------------------ */

    public Integer getArraySize() {
        return this.ArraySize;
    }

    public Integer getEmptyCells() {
        return this.emptyCells;
    }

    /* ----------------------SETTERS------------------------ */

    public void setArraySize(int size) {
        this.ArraySize = size;
    }

    public void setEmptyCells(int number) {
        this.emptyCells = number;
    }

    public void updateArray(int size) {
        if (size < 0 || size < this.ArraySize)
            throw new IllegalArgumentException("Array Size(): Invalid Re-Allocation");

        int occupied = (this.Array != null) ? this.ArraySize - this.emptyCells : 0;

        User[] tmp = new User[size];

        for (int i = 0; i < this.ArraySize; i++)
            tmp[i] = this.Array[i];

        this.Array = tmp;
        this.setArraySize(size);
        this.setEmptyCells(size - occupied);
    }

    /* -------------------CONSTRUCTORS---------------------- */

    public UsersArrayList() {
        this.setArraySize(0);
        this.setEmptyCells(0);
        this.updateArray(10);
    }

    /* ----------------------METHODS------------------------ */

    @Override
    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("Add_User(): User Not Found");

        if (this.getEmptyCells() == 0) {
            int newSize = this.getArraySize() + this.getArraySize() / 2;
            if (newSize <= this.getArraySize()) 
                newSize = this.getArraySize() + 1;
            this.updateArray(newSize);
        }

        for (int i = 0; i < this.getArraySize(); i++)
            if (this.Array[i] == null) {
                this.Array[i] = user;
                this.setEmptyCells(this.getEmptyCells() - 1);
                break;
            }

        System.out.printf("Adding User %s Successfully.\n", user.getName());
    }

    @Override
    public User searchUserById(Integer id) {
        for (int i = 0; i < this.getArraySize(); i++) {
            if (this.Array[i] == null)
                continue;
            if (this.Array[i].getIdentifier() == id)
                return this.Array[i];
        }

        throw new UserNotFoundException("User_Search(): id has not been found");
    }

    @Override
    public User searchUserByIndex(Integer index) {
        if (index >= this.getArraySize() || index < 0)
            throw new UserNotFoundException("User_Search(): Invalid Index");

        return this.Array[index];
    }

    @Override
    public Integer countUsers() {
        return this.getArraySize() - this.getEmptyCells();
    }
}
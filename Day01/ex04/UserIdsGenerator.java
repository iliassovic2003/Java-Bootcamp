public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int lastId;

    /* -----------------------GETTER------------------------- */

    public int getLastId() {
        return this.lastId;
    }

    /* -------------------CONSTRUCTOR----------------------- */

    private UserIdsGenerator() {
        this.lastId = 0;
    }

    /* ------------------------UTILS------------------------ */

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public int generateId() {
        return ++this.lastId;
    }
}
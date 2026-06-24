public interface UsersList {
    public void addUser(User user);

    public User searchUserById(Integer id);
    public User searchUserByIndex(Integer index);
    
    public Integer countUsers();

    public User[] getAllUsers();
}
package objects.user;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> Users = new ArrayList<>();

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        this.Users = users;
    }
}

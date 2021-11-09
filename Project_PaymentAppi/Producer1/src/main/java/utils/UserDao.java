package utils;

import bean.User;

import java.util.List;

public interface UserDao  {

    List<User> getAll();
    User insertUser(User user);

}

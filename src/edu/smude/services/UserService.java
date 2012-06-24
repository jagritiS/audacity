package edu.smude.services;

import edu.smude.domain.User;

import java.util.List;

public class UserService extends BaseService {

    public void add(User user) {
        try {
            queryRunner.update("insert into user (username, password) values (?,?", user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(User user) {

    }

    public void delete(long userId) {

    }

    public List<User> list() {
        return null;
    }

    public User get(long userId) {
        return null;
    }

    public User findByUsername(String username) {
        return null;
    }
}

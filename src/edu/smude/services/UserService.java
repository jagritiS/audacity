package edu.smude.services;

import edu.smude.domain.Song;
import edu.smude.domain.User;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
        try {
            ResultSetHandler<User> h = new BeanHandler<User>(User.class);
            return queryRunner.query("select * from user where username = ? ", h, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new User();
        
    }
}

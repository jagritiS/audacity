package edu.smude.services;

import edu.smude.domain.Song;
import edu.smude.domain.User;
import edu.smude.utils.PasswordEncoder;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserService extends BaseService {

    public void add(User user) throws SQLException{
        try {
            user.setPassword(PasswordEncoder.getEncoded(user.getPassword()));

            queryRunner.update("insert into user (username, password, name, email, userType) values (?,?,?,?,?)",
                    user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getUserType());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(User user) throws Exception{
        throw new Exception("Not Implemented");
    }

    public void delete(long userId) {
        try{

            queryRunner.update("delete from user where id =?",userId);

        } catch(Exception e ){
            e.printStackTrace();
        }

    }

    public List<User> list() {
        try {
            ResultSetHandler<List<User>> h = new BeanListHandler<User>(User.class);

            return queryRunner.query("select * from user", h);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new LinkedList<User>();
    }

    public User get(long userId) {
        try{
            ResultSetHandler<User> h = new BeanHandler<User>(User.class);
            return queryRunner.query("select * from user where id = ? ", h, userId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new User();
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

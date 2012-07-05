package edu.smude.services;


import edu.smude.domain.Band;
import edu.smude.domain.Song;
import edu.smude.utils.PasswordEncoder;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.LinkedList;
import java.util.List;

public class BandService extends BaseService {
    public void add(Band band) {
        try {
            queryRunner.update("insert into band (name, details, userId) values (?,?,?)",
                    band.getName(), band.getDetails(), band.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Band band) {
        try {
            queryRunner.update("update band set name=?, details=?, featured=?, featuredDate=? where id =? ",
                    band.getName(), band.getDetails(), band.isFeatured(), band.getFeaturedDate(), band.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            queryRunner.update("delete from band where id = ?", id);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Band> list() {
        try {
            ResultSetHandler<List<Band>> h = new BeanListHandler<Band>(Band.class);

            return queryRunner.query("select * from band", h);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Band>();
    }

    public Band get(long id) {
        try {
            ResultSetHandler<Band> h = new BeanHandler<Band>(Band.class);
            return queryRunner.query("select * from band where id = ? ", h, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Band();
    }

    public Band findByUserId(long id){
        try {
            ResultSetHandler<Band> h = new BeanHandler<Band>(Band.class);
            return queryRunner.query("select * from band where userId = ? ", h, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Band();
    }

    public List<Band> featured(){
        try {
            ResultSetHandler<List<Band>> h = new BeanListHandler<Band>(Band.class);

            return queryRunner.query("select * from band where featured=true order by featuredDate desc limit 10", h);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Band>();
    }
}

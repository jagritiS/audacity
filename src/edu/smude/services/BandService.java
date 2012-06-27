package edu.smude.services;


import edu.smude.domain.Band;
import edu.smude.domain.Song;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.LinkedList;
import java.util.List;

public class BandService extends BaseService {
    public void add(Band artist) {


    }

    public void update(Band artist) {

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

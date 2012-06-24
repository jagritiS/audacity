package edu.smude.services;


import edu.smude.domain.Band;
import edu.smude.domain.Song;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.LinkedList;
import java.util.List;

public class BandService extends BaseService {
    public void add(Band artist) {


    }

    public void update(Band artist) {

    }

    public void delete(long artistId) {

    }

    public List<Band> list() {
        return null;
    }

    public Band get(long bandId) {
        return null;
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

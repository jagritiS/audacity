package edu.smude.services;

import edu.smude.domain.Album;
import edu.smude.utils.DataSourceFactory;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AlbumService extends BaseService {

    public void add(Album album) {
        try {
            queryRunner.update("insert into album (name, details, bandId) values (?,?,?)",
                    album.getName(), album.getDetails(), album.getBandId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Album album) {
        try {
            queryRunner.update("update album set name=?, details=?, bandId = ? where id = ?",
                    album.getName(), album.getDetails(), album.getBandId(), album.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            queryRunner.update("delete from album where id = ?", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Album> list() {
        try {
            ResultSetHandler<List<Album>> h = new BeanListHandler<Album>(Album.class);

            return queryRunner.query("select * from album", h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedList<Album>();
    }

    public List<Album> topList() {

        try {
            ResultSetHandler<List<Album>> h = new BeanListHandler<Album>(Album.class);

            return queryRunner.query("select a.* \n" +
                    "from album a \n" +
                    " join visit_log v \n" +
                    "on a.id = v.item_id \n" +
                    "and v.item='album'\n" +
                    "group by item_id \n" +
                    "order by count(1) desc limit 5 ; ", h);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new LinkedList<Album>();
    }

    public List<Album> findByUserId(long userId) {
        try {
            ResultSetHandler<List<Album>> h = new BeanListHandler<Album>(Album.class);

            return queryRunner.query("select a.* from album a join band b on a.bandId = b.id where b.userId = ?", h, userId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new LinkedList<Album>();
    }

    public Album get(long id) {
        try {
            ResultSetHandler<Album> h = new BeanHandler<Album>(Album.class);
            return queryRunner.query("select * from album where id = ? ", h, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Album();
    }
}

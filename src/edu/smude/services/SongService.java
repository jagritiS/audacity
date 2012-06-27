package edu.smude.services;

import edu.smude.domain.Album;
import edu.smude.domain.Song;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.LinkedList;
import java.util.List;

public class SongService extends BaseService {

    public void add(Song album) {

    }

    public void update(Song album) {

    }

    public void delete(long albumId) {
        try{

            queryRunner.update("delete from song where id =?",albumId);

        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    public List<Song> list() {
        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select * from song", h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedList<Song>();
    }




    public Song get(long id) {
        try {
            ResultSetHandler<Song> h = new BeanHandler<Song>(Song.class);
            return queryRunner.query("select * from song where id = ? ", h, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Song();
    }

    public List<Song> topList() {

        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select a.* \n" +
                    "from song a \n" +
                    " join visit_log v \n" +
                    "on a.id = v.item_id \n" +
                    "and v.item='song'\n" +
                    "group by item_id \n" +
                    "order by count(1) desc limit 10; ", h);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new LinkedList<Song>();
    }

    public List<Song> latest(){
        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select * from song order by createdDate desc limit 10", h);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Song>(); 
    }

    public List<Song> featured(){
        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select * from song where featured=true order by featuredDate desc limit 10", h);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Song>();
    }

    public Song random(){
        Song song = new Song();
        try {
            ResultSetHandler<Song> h = new BeanHandler<Song>(Song.class);
            song = queryRunner.query("select s.* from song s join (select ceil(max(id)*rand()) id from song) t on s.id = t.id ", h);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return song; 
    }

    public List<Song> findByAlbumId(long albumId){
        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select a.* from song a join album b on a.albumId = b.id where b.id = ?", h, albumId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Song>();
    }

    public List<Song> findByBandId(long bandId){
        try {
            ResultSetHandler<List<Song>> h = new BeanListHandler<Song>(Song.class);

            return queryRunner.query("select a.* from song a join album b on a.albumId = b.id " +
                    " join band c on b.bandId = c.id where b.id = ?", h, bandId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new LinkedList<Song>();
    }


}

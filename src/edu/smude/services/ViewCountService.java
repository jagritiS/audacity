package edu.smude.services;

import edu.smude.domain.Album;
import edu.smude.domain.Band;
import edu.smude.domain.Song;

public class ViewCountService extends BaseService{
	
	public void logSong(Song song){
		logItem(song.getId(), "song"); 
	}
	
	public void logBand(Band band){
		logItem(band.getId(), "band");
	}
	
	public void logAlbum(Album album){
		logItem(album.getId(), "album");
	}
	
	private void logItem(Long itemId, String item){
		try{
			queryRunner.update("insert into visit_log (item, item_id, visit_date) values (?,?,now())", 
					item, itemId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

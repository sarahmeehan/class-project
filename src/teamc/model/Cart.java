package teamc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.wrapper.spotify.models.SimpleAlbum;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<SimpleAlbum> albums;	
    
    public Cart() {
    	albums = new ArrayList<SimpleAlbum>();
    }
    
	public ArrayList<SimpleAlbum> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<SimpleAlbum> albums) {
		this.albums = albums;
	}    
	public void addToCart(SimpleAlbum album) {
		this.albums.add(album);		
	}
	public void removeFromCart(SimpleAlbum album) {
		this.albums.remove(album);	
	}	
	public void removeFromCartById(String idToRemove) {
		Iterator<SimpleAlbum> iter = this.albums.iterator();
		while (iter.hasNext()) {
			String id = iter.next().getId();
			if (id.equals(idToRemove))
				iter.remove();
		}
	}		
	public boolean isEmpty() {
		return this.albums.isEmpty();
	}		
}
package javamusic.services;

public class SpotifyCache {
	
	private static String[] GENRES = {"aaa", "bbb", "ccc"};
	private static String[] ARTISTS = {"aaa", "bbb", "ccc"};	
	
	public static String[] getGenres() {
		
		if (GENRES == null) {
			//GENRES = SpotifyService.getInstance().getGenres();
		}
		return GENRES;
	}
	
	public static String[] getArtists() {
		
		if (ARTISTS == null) {
			//ARTISTS = SpotifyService.getInstance().getArtists();
		}
		return ARTISTS;
	}	

}

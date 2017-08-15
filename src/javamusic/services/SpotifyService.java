package javamusic.services;

import java.util.List;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.AlbumSearchRequest;
import com.wrapper.spotify.methods.AlbumsForArtistRequest;
import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.methods.NewReleasesRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;

public class SpotifyService {
	
	private static SpotifyService spotifyService;
	
	private SpotifyService() {}
	
	public static synchronized SpotifyService getInstance(){
		if (spotifyService == null) {
			spotifyService = new SpotifyService();
		}
		return spotifyService;
	}
	
	final String clientId = "fe2d7ef199f649839f9a7d671a18eb9b";
	final String clientSecret = "0ce362923c2444f280f6ddf5c562ab38";

	final Api api = Api.builder()
	  .clientId(clientId)
	  .clientSecret(clientSecret)
	  .build();

	/* Create a request object. */
	final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

	/* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
	final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

	private void authorize(){
		/* Add callback to handle success and failure */
    	Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
    	  public void onSuccess(ClientCredentials clientCredentials) {
    	    /* The tokens were retrieved successfully! */
    	    System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
    	    System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");
    	    
    	    /* Set access token on the Api object so that it's used going forward */
    	    api.setAccessToken(clientCredentials.getAccessToken());
    	    
    	    /* Please note that this flow does not return a refresh token.
    	   * That's only for the Authorization code flow */
    	  }

    	  public void onFailure(Throwable throwable) {
    	    /* An error occurred while getting the access token. This is probably caused by the client id or
    	     * client secret is invalid. */
    		  System.out.println(throwable.getMessage());
    		  System.out.println("Could not authorized");
    	  }
    	});
	}
	
	public Album getAlbumById(String id){
		// Create a request object for the type of request you want to make
		AlbumRequest request = api.getAlbum(id).build();

		// Retrieve an album
		try {
		  Album album = request.get();
		  return album;
		  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		  if (e.getMessage().equals("401")){
			  authorize();
			  return getAlbumById(id);
		  }
		  System.out.println("Could not get albums.");
		}
		return null;
	}
	
	public List<SimpleAlbum> getAlbumByArtist(String id){
		// Create a request object for the type of request you want to make
		AlbumsForArtistRequest request = api.getAlbumsForArtist(id).build();

		// Retrieve an album
		try {
		  Page<SimpleAlbum> album = request.get();
		  return album.getItems();
		  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		  if (e.getMessage().equals("401")){
			  authorize();
			  return getAlbumByArtist(id);
		  }
		  System.out.println("Could not get albums.");
		}
		return null;
	}
	
	public List<SimpleAlbum> getNewReleases(){
		// Create a request object for the type of request you want to make
		NewReleasesRequest request = api.getNewReleases().build();

		// Retrieve an album
		try {
		  NewReleases albums = request.get();
		  return albums.getAlbums().getItems();
		  
		} catch (Exception e) {
		  System.out.println(e.getMessage());
		  if (e.getMessage().equals("401")){
			  authorize();
			  return getNewReleases();
		  }
		  System.out.println("Could not get albums.");
		}
		return null;
	}
	
	public List<Artist> searchArtist(String query){
		// Create a request object for the type of request you want to make
		 System.out.println(query);
		ArtistSearchRequest request = api.searchArtists(query).build();
		System.out.println("Searching");
		// Retrieve an album
		try {
		  Page<Artist> artists = request.get();
		  System.out.println("SUCCESS");
		  return artists.getItems();
		  
		} catch (Exception e) {
		  System.out.println(e.getMessage());
		  if (e.getMessage().equals("401")){
			  authorize();
			  return searchArtist(query);
		  }
		  System.out.println("Could not get artists.");
		}
		System.out.println("FAIL");
		return null;
	}
	
	public List<SimpleAlbum> searchAlbums(String query){
		// Create a request object for the type of request you want to make
		AlbumSearchRequest request = api.searchAlbums(query).build();

		// Retrieve an album
		try {
		  Page<SimpleAlbum> albums = request.get();
		  return albums.getItems();
		  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		  if (e.getMessage().equals("401")){
			  authorize();
			  return searchAlbums(query);
		  }
		  System.out.println("Could not get albums.");
		}
		return null;
	}
}

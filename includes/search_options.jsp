<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,javamusic.services.*" %>
<aside id="sidebarA">
    <nav>
	<form action="music" method="post">    
	      <ul>
	          <li>
	          	<button type="submit" name="action" class="submitLink" value="show_current">Show Current Releases</button>
	          </li>         	         
	          <li>
	          	<label style="float: left; margin-bottom: 0.5em" class="pad_top">Search by keyword: </label>
		    	<input type="text" name="keyword"><br>
		      </li>
	          <li>
	          	<label style="float: left; margin-bottom: 0.5em" class="pad_top">Search by genre: </label>
		    	<select name="genre_choice">
		    		<option value="none">none</option>
	            	<c:forEach var="genre" items="${SpotifyCache.getGenres()}">
		            	<option value="${genre}">${genre}
		            	</option>
	            	</c:forEach>   		    	
		    	</select>
		      </li>	
		      <li>
	          	<label style="float: left; margin-bottom: 0.5em" class="pad_top">Search by artist: </label>
		    	<select name="artist_choice">
		    		<option value="none">none</option>		    	
	            	<c:forEach var="artist" items="${SpotifyCache.getArtists()}">
		            	<option value="${artist}">${artist}
		            	</option>
	            	</c:forEach>   		    	
		    	</select>
		      </li>	
	          <li>
	          	<button type="submit" name="action" value="search">Search</button>	          
	          </li>
	      </ul>
      </form> 
    </nav>
</aside>
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.nio.charset.Charset.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by adarshr2 on 2/7/17.
 */
public class MovieURLParserTest {

    MovieURLParser movie;

    /**
     * parse with given URL
     * take page 1, 1st movie as example
     * @throws IOException: resolves URL exceptions
     */
    @org.junit.Before
    public void parseJSON(){
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + TheMovieDB.API_KEY + "&page=1");
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new Gson();
            MovieCollectionURL mcURL = gson.fromJson(jsonReader, MovieCollectionURL.class); //loads full 1st page of movies into collection of movies
            movie = mcURL.getResults()[0]; //gets first movie from results array (array of Movie objects
        }
        catch(IOException IOE){
            System.out.println("IO Exception found. -parseJson");
        }

    }

    /**
     * checks that manually inputted genre ids (taken from website) are equal to URL loaded values
     * @throws Exception
     */
    @org.junit.Test
    public void getGenre_ids() throws Exception {
        int [] genre_ids = {12, 16, 35, 10751};
        for (int i = 0; i < genre_ids.length; i++) {
            assertEquals(movie.getGenre_ids()[i], genre_ids[i]);
        }
    }

    /**
     * checks for proper title of given movie
     * @throws Exception
     */
    @org.junit.Test
    public void getTitle() throws Exception {
        assertEquals(movie.getTitle(),"The Secret Life of Pets");
    }

    /**
     * checks for voter average of given movie
     * @throws Exception
     */
    @org.junit.Test
    public void getVote_average() throws Exception {
        assertEquals(movie.getVote_average(), 5.8, 0);
    }

    /**
     * checks for popularity of given movie
     * @throws Exception
     */
    @org.junit.Test
    public void getPopularity() throws Exception {
        assertEquals(movie.getPopularity(), 130.924005, 0);
    }

}
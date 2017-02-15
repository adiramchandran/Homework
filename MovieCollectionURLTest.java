import com.google.gson.stream.JsonReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;
import java.net.URL;
import static java.nio.charset.Charset.forName;
import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarshr2 on 2/7/17.
 */
public class MovieCollectionURLTest {

    MovieCollectionURL mcURL;
    MovieURLParser movie;
    MovieURLParser [] allMovies;

    /**
     * parse with given URL
     * take page 1, 1st movie as example
     * @throws IOException: resolves URL exceptions
     */
    @Before
    public void parseJSON(){
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + TheMovieDB.API_KEY + "&page=1");
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new Gson();
            mcURL = gson.fromJson(jsonReader, MovieCollectionURL.class); //loads full 1st page of movies into collection of movies
            allMovies = mcURL.getResults(); //to be used in all test methods
            movie = mcURL.getResults()[0]; //gets first movie from results array (array of Movie objects
        }
        catch(IOException IOE){
            System.out.println("IO Exception found.");
        }
    }

    /**
     * This checks the title of a given movie with the title of the first movie in the results array
     * @throws Exception
     */
    @Test
    public void getResults() throws Exception {
       //exampleMovie is titled "Secret Life Of Pets" which is contained in 0th index of allMovies array
        assertEquals(allMovies[0].getTitle(), movie.getTitle());
    }

    /**
     * checks functionality of testing for printing of all movie titles on one page
     * @throws Exception
     */
    @Test
    public void allMovieTitles() throws Exception {
        List<String> allMovieTitles = mcURL.allMovieTitles();

        assertEquals(allMovies[9].getTitle(), allMovieTitles.get(9));
        assertEquals(allMovies[3].getTitle(), allMovieTitles.get(3));
        assertNotEquals(allMovies[2].getTitle(), allMovieTitles.get(1));
    }

    /**
     * checks functionality of testing for printing of all movie titles by genre
     * @throws Exception
     */
    @Test
    public void moviesByGenre_ids() throws Exception {
        List<String> allMovieTitles  = mcURL.moviesByGenre_ids(28);

        assertEquals(allMovies[1].getTitle(), allMovieTitles.get(0)); //2nd movie on page is of desired genre
        assertEquals(allMovies[3].getTitle(), allMovieTitles.get(1)); //4th movie on page is of desired genre

    }

    /**
     * checks functionality of testing for printing of all movie titles above a min voter average
     * @throws Exception
     */
    @Test
    public void moviesByMinVotAvg() throws Exception {
        List<String> allMovieTitles= mcURL.moviesByMinVotAvg(6.5);

        assertEquals(allMovies[2].getTitle(), allMovieTitles.get(0)); //3rd movie on page has 6.5+ vot avg
        assertEquals(allMovies[3].getTitle(), allMovieTitles.get(1)); //4th movie on page has 6.5+ vot avg

    }

    /**
     * checks functionality of testing for printing of all movie titles above a min popularity
     * @throws Exception
     */
    @Test
    public void moviesByMinPopularity() throws Exception {
        List<String> allMovieTitles = mcURL.moviesByMinPopularity(100.0);

        assertEquals(allMovies[0].getTitle(), allMovieTitles.get(0));
        assertEquals(allMovies[1].getTitle(), allMovieTitles.get(1));

    }

}
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adarshr2 on 2/7/17.
 *
 * JSON Representation of MovieCollections
 * results: array of Movie objects
 */
public class MovieCollectionURL {

    private MovieURLParser[] results;

    public MovieURLParser[] getResults() {
        return results;
    }

    /**
     * takes no parameters, runs through all movies in array
     *
     * @return allMovies: list of all movies in results array
     */
    public List<String> allMovieTitles() {
        String toBeSplit = ""; //contains all movie titles; will be added to return List

        //loop through movie array and add titles to allMovies cumulatively
        for (int movieCounter = 0; movieCounter < results.length; movieCounter++) {
            toBeSplit += results[movieCounter].getTitle() + "; ";
        }
        List<String> allMovies = Arrays.asList(toBeSplit.split("; "));
        return allMovies;
    }

    /**
     * @param genre_ids: id of specific genre
     * @return genMovies: list of all movies of a given genre
     */
    public List<String> moviesByGenre_ids(int genre_ids) {
        String toBeSplit = ""; //contains all movies with given genre id; will be added to return List

        //loop through all movies in result array
        //double loop for genre_ids array to check for correct genre_id
        for (int movieCounter = 0; movieCounter < results.length; movieCounter++) {

            for (int genreCount = 0; genreCount < results[movieCounter].getGenre_ids().length; genreCount++) {

                if (genre_ids == results[movieCounter].getGenre_ids()[genreCount]) {
                    toBeSplit += results[movieCounter].getTitle() + "; ";
                    continue;
                }
            }
        }

        List<String> genMovies = Arrays.asList(toBeSplit.split("; "));
        return genMovies;
    }

    /**
     * @param min_vote_avg minimum voter average of a film to be displayed in the return String
     * @return vaMovies: list of titles of movies with voter average greater than or equaly to min_vote_avg
     */
    public List<String> moviesByMinVotAvg(double min_vote_avg) {
        String toBeSplit = ""; //contains all movies greater than or equal to given voter average

        //loop through all movies in result array
        //check for minimum voter average with if statement
        for (int movieCounter = 0; movieCounter < results.length; movieCounter++) {

            if (results[movieCounter].getVote_average() >= min_vote_avg) {
                toBeSplit += results[movieCounter].getTitle() + "; ";
            }
        }

        List<String> mvaMovies = Arrays.asList(toBeSplit.split("; "));
        return mvaMovies;
    }

    /**
     * @param min_popularity: minimum popularity of a film to be displayed  in the return string
     * @return popMovies: list of titles of movies with popularity greater than or equal to min_popularity
     */
    public List<String> moviesByMinPopularity(double min_popularity) {
        String toBeSplit = ""; //contains all movies greater than or equal to given popularity

        //loop through all movies in result array
        //check for minimum popularity with if statement
        for (int movieCounter = 0; movieCounter < results.length; movieCounter++) {

            if (results[movieCounter].getPopularity() >= min_popularity) {
                toBeSplit += results[movieCounter].getTitle() + "; ";
            }
        }

        List<String> popMovies = Arrays.asList(toBeSplit.split("; "));
        return popMovies;
    }
}


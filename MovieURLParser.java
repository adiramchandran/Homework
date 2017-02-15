/**
 * Created by adarshr2 on 2/6/17.
 */

/**
 * Representation of the JSON returned from:
 *      https://api.themoviedb.org/3/movie/popular?api_key=971578a4bad04ce5e35414fd9adc542b
 *
 *
 */
public class MovieURLParser {

    private int [] genre_ids;
    private String title;
    private double popularity;
    private double vote_average;

    public int [] getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average(){ return vote_average; }

    public double getPopularity(){ return popularity; }
}

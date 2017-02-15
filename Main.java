/**
 * Created by adarshr2 on 2/7/17.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import static java.nio.charset.Charset.forName;

public class Main {
    public static int numOfPages;
    public static List<MovieCollectionURL> mcURL = new ArrayList<MovieCollectionURL>();

    public static MovieCollectionURL tooFewMcURL;



    /**
     * if number of movies only warrants 1 page, no array is needed; single object is enough
     *
     */
    public static void tooFewMovies(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1: Print all movies\n2: Choose by genre\n3: Chose by minimum average voter rating\n" +
                    "4: Choose by minimum popularity\n5: Exit program");
            int choices = sc.nextInt(); //holds user choice of how to progress through application

            //check if user input is valid
            //if not, redirect to choices again
            if (choices < 1 || choices > 5) {
                System.out.println("Please choose a number from 1-5.");
                continue;
            }

            //switch case for 5 cases detailed in user prompting statement
            switch (choices) {
                //print all movies
                case 1:
                    String movieTitles = tooFewMcURL.allMovieTitles().toString(); //only one page - calls all movie titles
                    System.out.println(movieTitles);

                    break;

                //prompt for genre id before printing
                case 2:
                    System.out.println("Enter the genre id: ");
                    int genre_id = sc.nextInt();

                    String genreIdMovies = tooFewMcURL.moviesByGenre_ids(genre_id).toString(); //all movie titles from a genre
                    System.out.println(genreIdMovies);

                    break;

                //prompt for min voter avg before printing
                case 3:
                    System.out.println("Enter the minimum voter average: ");
                    double minVotAvg = sc.nextDouble();

                    String mvaMovies = tooFewMcURL.moviesByMinVotAvg(minVotAvg).toString(); //all movies titles w min voter avg
                    System.out.println(mvaMovies);

                    break;

                //prompt for min popularity before printing
                case 4:
                    System.out.println("Enter the minimum popularity: ");
                    double minPopularity = sc.nextDouble();

                    String minPopMovies = tooFewMcURL.moviesByMinPopularity(minPopularity).toString(); //all movie titles w min popularity
                    System.out.println(minPopMovies);

                    break;

                //end program
                case 5:
                    System.exit(0);
            }
        }
    }


    /**
     * if number of movies warrants more than one page, an array is needed
     *
     */
    public static void normalNumMovies() throws IOException {

        Scanner sc = new Scanner(System.in);

        //if statement to check for numofPages too large
        if (numOfPages > TheMovieDB.NUM_PAGES){
            numOfPages = TheMovieDB.NUM_PAGES;
        }

        while (true) {
            System.out.println("1: Print all movies\n2: Choose by genre\n3: Chose by minimum average voter rating\n" +
                    "4: Choose by minimum popularity\n5: Exit program");
            int choices = sc.nextInt(); //holds user choice of how to progress through application

            //check if user input is valid
            //if not, redirect to choices again
            if (choices < 1 || choices > 5) {
                System.out.println("Please choose a number from 1-5.");
                continue;
            }

            //switch case for 5 cases detailed in user prompting statement
            switch (choices) {
                //print all movies
                case 1:
                    for (int pageCounter = 0; pageCounter < numOfPages; pageCounter++) {
                        String movieTitles = mcURL.get(pageCounter).allMovieTitles().toString(); //all movie titles
                        System.out.println(movieTitles);
                    }
                    break;

                //prompt for genre id before printing
                case 2:
                    System.out.println("Enter the genre id: ");
                    int genre_id = sc.nextInt();
                    for (int pageCounter = 0; pageCounter < numOfPages; pageCounter++) {
                        String genreIdMovies = mcURL.get(pageCounter).moviesByGenre_ids(genre_id).toString(); //all movie titles from a genre
                        System.out.println(genreIdMovies);
                    }
                    break;

                //prompt for min voter avg before printing
                case 3:
                    System.out.println("Enter the minimum voter average: ");
                    double minVotAvg = sc.nextDouble();
                    for (int pageCounter = 0; pageCounter < numOfPages; pageCounter++) {
                        String mvaMovies = mcURL.get(pageCounter).moviesByMinVotAvg(minVotAvg).toString(); //all movies titles w min voter avg
                        System.out.println(mvaMovies);
                    }
                    break;

                //prompt for min popularity before printing
                case 4:
                    System.out.println("Enter the minimum popularity: ");
                    double minPopularity = sc.nextDouble();
                    for (int pageCounter = 0; pageCounter < numOfPages; pageCounter++) {
                        String minPopMovies = mcURL.get(pageCounter).moviesByMinPopularity(minPopularity).toString(); //all movie titles w min popularity
                        System.out.println(minPopMovies);
                    }
                    break;

                //end program
                case 5:
                    System.exit(0);
            }
        }
    }

    /**
     * normal loading of movies via for loop
     * @throws IOException
     */
    public static void normalParseJson() throws IOException{
        try {
            //check for numOfPages too large
            if (numOfPages > TheMovieDB.NUM_PAGES) {
                numOfPages = TheMovieDB.NUM_PAGES;
            }

            for (int pageCounter = 1; pageCounter <= numOfPages; pageCounter++) {
                URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + TheMovieDB.API_KEY + "&page=" + pageCounter);
                InputStream inStream = url.openStream();
                InputStreamReader reader = new InputStreamReader(inStream, forName("UTF-8"));
                JsonReader jsonReader = new JsonReader(reader);
                Gson gson = new Gson();
                mcURL.add(gson.fromJson(jsonReader, MovieCollectionURL.class)); //loads full 1st page of movies into collection of movies
            }
        }
        catch(IOException IOE){
            System.out.println("IO Exception found. -normalParseJson");
            System.exit(0);
        }

    }

    /**
     * no need for more than 1 page if movies is less than MOVIES_PER_PAGE
     * @throws IOException
     */
    public static void tooFewParseJson() throws IOException{
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + TheMovieDB.API_KEY + "&page=1");
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            Gson gson = new Gson();
            tooFewMcURL = gson.fromJson(jsonReader, MovieCollectionURL.class); //loads full 1st page of movies into collection of movies
        }
        catch(IOException IOE){
            System.out.println("IO Exception Found. -tooFewParseJson");
            System.exit(0);
        }

    }

    public static void main(String [] args) throws IOException{
        try {
            Scanner sc = new Scanner(System.in);

            //welcome user to application
            System.out.println("Welcome to the Premium Movie Searching Application. We hope to satisfy your movie needs!");

            //prompt user for requested number of movies
            System.out.println("Enter the number of movies you wish to see.");
            int numOfMovies = sc.nextInt();

            numOfPages = numOfMovies / TheMovieDB.MOVIES_PER_PAGE + 1; //int values round down so add 1

            if (numOfMovies < 0) {
                System.out.println("You entered an invalid input. ERROR");
                System.exit(0);
            }

            //if statement to account for user value < MOVIES_PER_PAGE
            if (numOfMovies < TheMovieDB.MOVIES_PER_PAGE) {
                tooFewParseJson();
                tooFewMovies();
            } else {
                normalParseJson();
                normalNumMovies();
            }
        }
        catch(IOException IOE){
            System.out.println("IO Exception. -main");
            System.exit(0);
        }

    }

}

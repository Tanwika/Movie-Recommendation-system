
/**
 * Write a description of SecongRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile){
       FirstRatings fr = new FirstRatings();
       myMovies = fr.loadMovies(movieFile);
       myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
       return myMovies.size();
    }
    
    public int getRaterSize(){
       return myRaters.size();
    }
    
    public double getAverageById(String movieId, int minimalRaters){
       double avg =0.0;
       int count =0;
       for(int i=0;i<myRaters.size();i++){
           Rater r = myRaters.get(i);
           if(r.hasRating(movieId)){
               avg += r.getRating(movieId);
               count++;
            }
        }
       if(count<minimalRaters){
           return 0.0;
        }
       return avg/count;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
       ArrayList<Rating> movieAvgList = new ArrayList<Rating>();
       for(int i=0;i<myMovies.size();i++){
           String movieId = myMovies.get(i).getID();
           double avg = getAverageById(movieId,minimalRaters);
           if(! (avg == 0.0)){
              Rating r = new Rating(movieId,avg);
              movieAvgList.add(r);
           }
        }
       return movieAvgList;
    }
    
    public String getTitle(String id){
       for(int i=0;i<myMovies.size();i++){
          if(myMovies.get(i).getID().equals(id)){
            return myMovies.get(i).getTitle();
            }
        }
       return "ID was not found";
    }
    
    public String getID(String title){
      for(int i=0;i<myMovies.size();i++){
        if(myMovies.get(i).getTitle().equals(title)){
           return myMovies.get(i).getID();
        }
        }
      return "NO SUCH TITLE";
    }
}

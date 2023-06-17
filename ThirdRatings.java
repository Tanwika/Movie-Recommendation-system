
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class ThirdRatings {
   
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){
       FirstRatings fr = new FirstRatings();
       myRaters = fr.loadRaters(ratingsFile);
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
        ArrayList<String> movieIdList = MovieDatabase.filterBy(new TrueFilter());
       ArrayList<Rating> movieAvgList = new ArrayList<Rating>();
       for(int i=0;i<movieIdList.size();i++){
           String movieId = movieIdList.get(i);
           double avg = getAverageById(movieId,minimalRaters);
           if(! (avg == 0.0)){
              Rating r = new Rating(movieId,avg);
              movieAvgList.add(r);
           }
        }
       return movieAvgList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
       ArrayList<String> movieIdList = MovieDatabase.filterBy(filterCriteria);
       ArrayList<Rating> movieAvgList = new ArrayList<Rating>();
       for(String movieId : movieIdList){
          double avg = getAverageById(movieId,minimalRaters);
          if(! (avg == 0.0)){
             Rating r = new Rating(movieId,avg);
             movieAvgList.add(r);
            }
        }
       return movieAvgList;
    }
    
    
}

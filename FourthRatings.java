
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class FourthRatings {
   
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsFile){
       //FirstRatings fr = new FirstRatings();
       RaterDatabase.initialize(ratingsFile);
    }
    
    
    public int getRaterSize(){
       return RaterDatabase.size();
    }
    
    public double getAverageById(String movieId, int minimalRaters){
       double avg =0.0;
       int count =0;
       ArrayList<Rater> raters = RaterDatabase.getRaters();
       for(int i=0;i<getRaterSize();i++){
           Rater r = raters.get(i);
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
    
    private double dotProduct(Rater me,Rater r){
       double d1 = 0.0;
       double d2 = 0.0;
       double dotProduct = 0.0;
       ArrayList<String> moviesList1 = me.getItemsRated();
       ArrayList<String> moviesList2 = r.getItemsRated();
       for(String s : moviesList1){
           if(moviesList2.contains(s)){
              d1 = me.getRating(s) - 5;
              d2 = r.getRating(s) - 5;
              dotProduct += (d1*d2); 
            }
        }
       return dotProduct;
    }
    
    /*private ArrayList<Rating> getSimilarities(String id){
       ArrayList<Rating> similarities = new ArrayList<Rating>();
       ArrayList<String> raterIdList = RaterDatabase.filterBy(new TrueFilter());
       Rater me = RaterDatabase.getRater(id);
       for(String currId : raterIdList){
          if(! currId.equals(id)){
             Rater r = RaterDatabase.getRater(currId);
             double dotProduct = dotProduct(me,r);
             if(dotProduct>0){
                Rating rating = new Rating(currId,dotProduct);
                similarities.add(rating);
                }
            }
        }
       Collections.sort(similarities,Collections.reverseOrder());
       return similarities;
    }*/
    
    private ArrayList<Rating> getSimilarities(String id){
       ArrayList<Rating> similarities = new ArrayList<Rating>();
       Rater me = RaterDatabase.getRater(id);
       for(Rater r : RaterDatabase.getRaters()){
           if(! (r.getID().equals(id))){
                 double dotProduct = dotProduct(me,r);
               if(dotProduct>0){
                Rating rating = new Rating(r.getID(),dotProduct);
                similarities.add(rating);
                }
           }
        }
       Collections.sort(similarities,Collections.reverseOrder());
       return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId,int numSimilarRaters, int minimalRaters){
       ArrayList<Rating> similarRatingsList = new ArrayList<Rating>();
       ArrayList<Rating> similarities = getSimilarities(raterId);
       for(String movieId : MovieDatabase.filterBy(new TrueFilter())){
           double sum = 0.0;
           int count = 0;
          for(int i=0;i<numSimilarRaters;i++){
             Rating rating = similarities.get(i);
             Rater r = RaterDatabase.getRater(rating.getItem());
             if(r.hasRating(movieId)){
                 sum += (r.getRating(movieId)*rating.getValue()); 
                 count++;
                }
            }
          if(! (count < minimalRaters)){
              Rating rtng = new Rating(movieId, (sum/count));
              similarRatingsList.add(rtng);
            }
        }
       Collections.sort(similarRatingsList, Collections.reverseOrder());
       return similarRatingsList;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId,int numSimilarRaters, int minimalRaters,Filter filterCriteria){
       ArrayList<Rating> similarRatingsList = new ArrayList<Rating>();
       ArrayList<Rating> similarities = getSimilarities(raterId);
       for(String movieId : MovieDatabase.filterBy(filterCriteria)){
           double sum = 0.0;
           int count = 0;
           if(similarities.size()>=numSimilarRaters){
          for(int i=0;i<numSimilarRaters;i++){
             Rating rating = similarities.get(i);
             Rater r = RaterDatabase.getRater(rating.getItem());
             if(r.hasRating(movieId)){
                 sum += (r.getRating(movieId)*rating.getValue()); 
                 count++;
                }
            }
          if(! (count < minimalRaters)){
              Rating rtng = new Rating(movieId, (sum/count));
              similarRatingsList.add(rtng);
            }
        }
        }
       Collections.sort(similarRatingsList, Collections.reverseOrder());
       return similarRatingsList;
    }
    
}

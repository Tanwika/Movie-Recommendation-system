
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
   public void printAverageRatings(){
       SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
       System.out.println("Noof movies : "+"\t"+sr.getMovieSize()+"\n"+"Noof raters :"+"\t"+sr.getRaterSize() );
       ArrayList<Rating> r = sr.getAverageRatings(12);
       System.out.println("Ratings greater than 12 :"+r.size());
       /*Collections.sort(r, new AverageComparator());
       System.out.println(r.get(0).getValue()+"\t"+sr.getTitle(r.get(0).getItem()));
       /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+sr.getTitle(r.get(i).getItem()));
        }*/
    }
    
   public void getAerageRatingOneMovie(){
       SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
       String movieTitle = "Vacation";
       String movieId = sr.getID(movieTitle);
       System.out.println("Avg of "+movieTitle+ " is "+sr.getAverageById(movieId,0));
    }
}

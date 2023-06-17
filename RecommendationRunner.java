
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class RecommendationRunner implements Recommender{
   public ArrayList<String> getItemsToRate(){
      Filter f = new YearAfterFilter(2000); 
      ArrayList<String> movies = MovieDatabase.filterBy(f);
      ArrayList<String> shortList = new ArrayList<String>(20);
      for(int i=0;i<20;i++){
         shortList.add(movies.get(i));
        }
        return shortList;
    }
    
   public void printRecommendationsFor(String webRaterId){
      FourthRatings fr = new FourthRatings();
      ArrayList<Rating> r = fr.getSimilarRatingsByFilter(webRaterId,20,10, new MinutesFilter(80,200));
      if(r.isEmpty()){
          System.out.println("NO MOVIES!"+"\n"+"CHANGE FILTER");
        }
        else{
               System.out.println("YOUR MOVIE RECOMMENDATIONS"+"\n");
                for(int i=0;i<r.size();i++){
                  System.out.println(MovieDatabase.getTitle(r.get(i).getItem()));
         }  
      }
    }
}

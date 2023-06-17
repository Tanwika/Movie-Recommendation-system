
/**
 * Write a description of MOvieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class MovieRunnerSimilarRatings {
   public void printAverageRatings(){
       FourthRatings tr = new FourthRatings();
       MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
       ArrayList<Rating> r = tr.getAverageRatings(35);
       System.out.println("Ratings greater than 35 : "+r.size());
       /*Collections.sort(r, new AverageComparator());
       //System.out.println(r.get(0).getValue()+"\t"+sr.getTitle(r.get(0).getItem()));
       for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getTitle(r.get(i).getItem()));
        }*/
    }
    
    public void printAverageRatingsByAfterYearAndGenre(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       /*Filter yf = new YearAfterFilter(180);
       Filter gf = new GenreFilter("Romance");*/
       AllFilters myFilter = new AllFilters();
       myFilter.addFilter(new YearAfterFilter(1990));
       myFilter.addFilter(new GenreFilter("Drama"));
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(8,myFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getYear(r.get(i).getItem())+"\t"+MovieDatabase.getTitle(r.get(i).getItem())+"\n"+
              "\t"+MovieDatabase.getGenres(r.get(i).getItem()));
        }*/
    }
    
    public void printSimilarRatings(){
      FourthRatings fr = new FourthRatings();
      MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
      System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
      ArrayList<Rating> r = fr.getSimilarRatings("71",20,5);
      System.out.println(MovieDatabase.getTitle(r.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenre(){
      FourthRatings fr = new FourthRatings();
      MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
      System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
      ArrayList<Rating> r = fr.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));
      System.out.println(MovieDatabase.getTitle(r.get(0).getItem()));
    }
    
    public void printSimilarRatingsByDirector(){
      FourthRatings fr = new FourthRatings();
      MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
      System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
      ArrayList<Rating> r = fr.getSimilarRatingsByFilter("120",10,2, 
        new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
      System.out.println(MovieDatabase.getTitle(r.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
      FourthRatings fr = new FourthRatings();
      MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
      System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
      AllFilters f = new AllFilters();
      f.addFilter(new GenreFilter("Drama"));
      f.addFilter(new MinutesFilter(80,160));
      ArrayList<Rating> r = fr.getSimilarRatingsByFilter("168",10,3,f);
      System.out.println(MovieDatabase.getTitle(r.get(0).getItem()));
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
      FourthRatings fr = new FourthRatings();
      MovieDatabase.initialize("ratedmoviesfull.csv");
       RaterDatabase.initialize("ratings.csv");
      System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+RaterDatabase.size() );
      AllFilters f = new AllFilters();
      f.addFilter(new YearAfterFilter(1975));
      f.addFilter(new MinutesFilter(70,200));
      ArrayList<Rating> r = fr.getSimilarRatingsByFilter("314",10,5,f);
      System.out.println(MovieDatabase.getTitle(r.get(0).getItem()));
    }
}

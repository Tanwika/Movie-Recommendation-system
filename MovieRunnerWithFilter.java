
/**
 * Write a description of MovieRunnerWithFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class MovieRunnerWithFilter {
   public void printAverageRatings(){
       ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       ArrayList<Rating> r = tr.getAverageRatings(35);
       System.out.println("Ratings greater than 35 : "+r.size());
       /*Collections.sort(r, new AverageComparator());
       //System.out.println(r.get(0).getValue()+"\t"+sr.getTitle(r.get(0).getItem()));
       for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getTitle(r.get(i).getItem()));
        }*/
    }
    
   public void printAverageRatingsByYear(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       Filter yearFilter = new YearAfterFilter(2000);
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(20,yearFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getYear(r.get(i).getItem())+"\t"+MovieDatabase.getTitle(r.get(i).getItem()));
        }*/
    }
    
   public void printAverageRatingsByGenre(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       Filter myFilter = new GenreFilter("Comedy");
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(20,myFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getTitle(r.get(i).getItem())+"\n"+MovieDatabase.getGenres(r.get(i).getItem()));
        }*/
    }
    
   public void printAverageRatingsByMinutes(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       Filter myFilter = new MinutesFilter(105,135);
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(5,myFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getMinutes(r.get(i).getItem())+"\t"+MovieDatabase.getTitle(r.get(i).getItem()));
        }*/
    }
    
   public void printAverageRatingsByDirectors(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       Filter myFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(4,myFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getTitle(r.get(i).getItem())+"\n"+MovieDatabase.getDirector(r.get(i).getItem()));
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
    
   public void printAverageRatingsByDirectorsAndMinutes(){
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       System.out.println("Noof movies : "+"\t"+MovieDatabase.size()+"\n"+"Noof raters :"+"\t"+tr.getRaterSize() );
       /*Filter yf = new YearAfterFilter(180);
       Filter gf = new GenreFilter("Romance");*/
       AllFilters myFilter = new AllFilters();
       myFilter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
       myFilter.addFilter(new MinutesFilter(90,180));
       ArrayList<Rating> r = tr.getAverageRatingsByFilter(3,myFilter);
        Collections.sort(r, new AverageComparator());
      System.out.println("Found : "+r.size());
      /*for(int i=0;i<r.size();i++){
           System.out.println(r.get(i).getValue()+"\t"+MovieDatabase.getMinutes(r.get(i).getItem())+"\t"+MovieDatabase.getTitle(r.get(i).getItem())+"\n"+
              "\t"+MovieDatabase.getDirector(r.get(i).getItem()));
        }*/
    }

}

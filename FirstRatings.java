import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String fileName){
       ArrayList<Movie> movieList = new ArrayList<Movie>();
       //System.out.println("Select file : "+fileName);
       FileResource fr = new FileResource(fileName);
       CSVParser parser = fr.getCSVParser();
       for(CSVRecord currRow : parser){
           movieList.add(new Movie(currRow.get("id"),currRow.get("title"),currRow.get("year"),currRow.get("genre"),currRow.get("director"),
                                  currRow.get("country"),currRow.get("poster"),Integer.parseInt(currRow.get("minutes"))));
        }
       return movieList;
    }
    
    public void testLoadMovies(){
      ArrayList<Movie> listMovies = loadMovies("data/ratedmoviesfull.csv");
      //System.out.println(listMovies);
      System.out.println("Total movies : "+listMovies.size());
      
      int comedyMovies = HowManyInGenre("Comedy","data/ratedmoviesfull.csv");
      System.out.println("Total movies in Comedy genre : "+comedyMovies);
      int num = 150;
      System.out.println("Movies greater than " +num+" are "+moviesGreaterThan(num,"data/ratedmoviesfull.csv"));
    }
    
    public int HowManyInGenre(String genre, String fileName){
       int num =0;
       ArrayList<Movie> list = loadMovies(fileName);
       for(int i=0;i<list.size();i++){
           String currGenre = list.get(i).getGenres();
           int index = currGenre.indexOf(genre);
           
           if(index!=-1){
               num++;
            }
        }
       return num;
    }
    
    public int moviesGreaterThan(int min, String fileName){
       int count = 0;
       ArrayList<Movie> movieList = loadMovies(fileName);
       for(int i=0;i<movieList.size();i++){
          int currLength = movieList.get(i).getMinutes();
          if(currLength>min){
             count++;
            }
        }
       return count;
    }
    
    public int maxMoviesByDirectors(HashMap<String, Integer> directorsMap){
        int max =0;
        for(String director : directorsMap.keySet()){
           if(directorsMap.get(director) > max){
              max=directorsMap.get(director);
            }
        }
        return max;
    }
    
    public HashMap<String,Integer> DirectorsMap(ArrayList<Movie> movieList){
       HashMap<String,Integer> directorsMap = new HashMap<String,Integer>();
       for(int i=0;i<movieList.size();i++){
           int index =0;
           String director = movieList.get(i).getDirector();
          while(index<director.length()){
              int indexLast = director.indexOf(",",index);
              String d = "";
              if(indexLast == -1){
                d = director.substring(index).trim();
                if(directorsMap.containsKey(d)){
                   directorsMap.put(d,directorsMap.get(d)+1);
                }
                else{
                   directorsMap.put(d,1);
                }
                break;
                }
              else{
                d= director.substring(index,indexLast).trim();
                if(directorsMap.containsKey(d)){
                   directorsMap.put(d,directorsMap.get(d)+1);
                }
                else{
                   directorsMap.put(d,1);
                }
                }  
              index=indexLast+1;
            }
        }
       return directorsMap;
    }
    
    public int indexOf(String id, ArrayList<Rater> ratersList){
      for(int i=0;i<ratersList.size();i++){
          if(ratersList.get(i).getID().equals(id)){
            return i;
            }
        }
        return -1;
    }
    
    public ArrayList<Rater> loadRaters(String fileName){
       FileResource fr = new FileResource(fileName);
       CSVParser parser = fr.getCSVParser();
       ArrayList<Rater> ratersList = new ArrayList<Rater>();
       for(CSVRecord record : parser){
           String rater_id = record.get("rater_id");
           String movie_id = record.get("movie_id");
           double rating = Double.parseDouble(record.get("rating"));
           int index = indexOf(rater_id,ratersList);
           if(index == -1){
              Rater rater = new EfficientRater(rater_id);
              rater.addRating(movie_id,rating);
               ratersList.add(rater);
            }
            else{
              Rater available = ratersList.get(index);
              available.addRating(movie_id,rating);
            }
           
           
        }
       return ratersList;
    }
    
    public int numOfRatingsById(String id, ArrayList<Rater> ratersList){
       int index = indexOf(id,ratersList);
       if(index==-1){
         return 0;
        }
        else{
          return ratersList.get(index).numRatings();
        }
    }
    
    public int maxRating(ArrayList<Rater> ratersList){
       int max =0;
       for(int i=0;i<ratersList.size();i++){
          Rater r = ratersList.get(i);
          if(r.numRatings()>max){
            max=r.numRatings();
            }
        }
       return max;
    }
    
    public ArrayList<String> ratersWithMaxRatings(ArrayList<Rater> ratersList){
       ArrayList<String> raters = new ArrayList<String>();
       int max = maxRating(ratersList);
       
       for(int i=0;i<ratersList.size();i++){
           Rater r = ratersList.get(i);
           if(r.numRatings()==max){
              raters.add(r.getID());
            }
        }
       return raters;
    }
    
    public int numOfRatingsToMovie(String movieId,ArrayList<Rater> ratersList){
      int num =0;
      for(int i=0;i<ratersList.size();i++){
         Rater r = ratersList.get(i);
         
         if(r.hasRating(movieId)){
             num+=1;
            }
        }
      return num;
    }
    
    public int howManyMovies(ArrayList<Rater> ratersList){
       
       ArrayList<String> moviesList = new ArrayList<String>();
       for(int i=0;i<ratersList.size();i++){
          Rater r = ratersList.get(i);
         /*HashMap<String,Double> ratings = r.ratings();
         for(String id : ratings.keySet()){
            if(! moviesList.contains(id)){
               moviesList.add(id);
            }
            }*/
         ArrayList<String> items = r.getItemsRated();
         for(int j=0;j<items.size();j++){
               if(! moviesList.contains(items.get(j))){
                   moviesList.add(items.get(j));
                }
            }
        }
       return moviesList.size();
    }
    
    public void testerRaters(){
       ArrayList<Rater> ratersList = loadRaters("data/ratings_short.csv");
       /*for(int i=0;i<ratersList.size();i++){
           Rater r = ratersList.get(i);
           System.out.println(r.getID() + "\t" + r.numRatings()+"\n"+r.ratings());
        }*/
       System.out.println("Number Of ratings by id 193 = "+numOfRatingsById("193",ratersList));
       System.out.println("Maximum noof ratings = "+maxRating(ratersList));
       System.out.println("Number of raters with max ratings : "+ratersWithMaxRatings(ratersList).size());
       System.out.println(numOfRatingsToMovie("1798709",ratersList));
       System.out.println(howManyMovies(ratersList));
    }
    
    public void testerRaters1(){
       ArrayList<Rater> ratersList = loadRaters("data/ratings.csv");
       /*for(int i=0;i<ratersList.size();i++){
           Rater r = ratersList.get(i);*/
           System.out.println("The raters in the file are : "+ratersList.size());
        //}
       System.out.println("Number Of ratings by id 193 = "+numOfRatingsById("193",ratersList));
       System.out.println("Maximum noof ratings = "+maxRating(ratersList));
       System.out.println("Number of raters with max ratings : "+"\n"+ratersWithMaxRatings(ratersList));
       System.out.println(numOfRatingsToMovie("1798709",ratersList));
       System.out.println(howManyMovies(ratersList));
    }
    
    
    public void tester(){
       /*ArrayList<Movie> listMovies = loadMovies("data/ratedmovies_short.csv");
       System.out.println(DirectorsMap(listMovies));*/
       ArrayList<Movie> listMovies = loadMovies("data/ratedmoviesfull.csv");
       /*System.out.println("Movies from large file:");
       for(int i=0;i<50;i++){
          System.out.println(listMovies.get(i));
        }*/
        
       HashMap<String,Integer> directorsMap = DirectorsMap(listMovies);
       int max = maxMoviesByDirectors(directorsMap);
       System.out.println("Directors having max = "+max+ " movies :");
       for(String d : directorsMap.keySet()){
          if(max == directorsMap.get(d)){
             System.out.println(d);
            }
        }
    }
}



public class DirectorsFilter implements Filter {
   private String directors;
   
   public DirectorsFilter(String directorsString){
     directors = directorsString;
    }
    
   public boolean satisfies(String movieId){
      String movieDirectors = MovieDatabase.getDirector(movieId);
      int index = 0;
      while(index<directors.length()){
         int lastIndex = directors.indexOf(",",index);
         String d = "";
         if(lastIndex == -1){
             d = directors.substring(index).trim();
             if(movieDirectors.contains(d)){
                  return true;
               }
             break;
            }
         else{
             d = directors.substring(index,lastIndex).trim();
            }
         if(movieDirectors.contains(d)){
            return true;
            }
         index = lastIndex+1;
        }
      return false;
    }
}

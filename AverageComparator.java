
/**
 * Write a description of AverageCOmparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class AverageComparator implements Comparator<Rating> {
   public int compare(Rating r1,Rating r2){
      return Double.compare(r1.getValue(),r2.getValue());
    }
}

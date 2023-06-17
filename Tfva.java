
/**
 * Write a description of Tfva here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tfva {
    public void test(){
       int n =2;
       int[] arr = {3,5};
       int sum=0;
       boolean[] bool = new boolean[1000];
       for(int i=0;i<n;i++){
          bool[arr[i]]=true;
          sum+=arr[i];
        }
        int res =0;
       for(int j=0;j<sum;j++){
          if(bool[j]){
             continue;
            }
          if(j%2!=0){
            res = Math.max(res,j);
            }
          
        }
       System.out.println(res);
    }
}

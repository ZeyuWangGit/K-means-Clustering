

import java.util.HashMap;


public class TfIdf {
   
    public double tfCalculator(HashMap<String, Integer> totalterms, String termToCheck) {
        double count = 0;  //to count the overall occurrence of the term termToCheck
        if(totalterms.containsKey(termToCheck)){
        	count = totalterms.get(termToCheck);
        }
        double sum = 0;
        for (double f : totalterms.values()) {
            sum += f;
        }
        //System.out.print(count);
        //return count / sum;
        return count/sum;
        
    }
 
}

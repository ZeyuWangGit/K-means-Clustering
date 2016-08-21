
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import util.FileFinder;

public class DocumentAnalysis {

    
	public HashMap<String,Integer>  allTermIndex_d;
	public HashMap<String,Integer> DocTermCount_d;
	public HashMap<Integer, HashMap<String, Integer>> outHashMap = new HashMap<>();
	public HashMap<Integer,HashMap<Integer,Double>> tfDocsVector = new HashMap<>();
	public double consineSimilarity = 0.0;
	public HashMap<Integer,String> nameTransfer = new HashMap<>();

    public void analyzeFile(String directory) throws FileNotFoundException, IOException{
    	
    	DocHandle termIndex_all = new DocHandle(directory,100,true);
    	allTermIndex_d = termIndex_all.getTermIndex();
    	//TermIndex ready
    		
    	
    	
    	ArrayList<File> files = FileFinder.GetAllFiles(directory, "", true);
    	int index = 0;
    	for (File f : files) {
    		//System.out.println(f);
    		nameTransfer.put(index, f.toString());
    		SingleDocHandle termIndex_single = new SingleDocHandle(f,1000000,true);
    		DocTermCount_d = (HashMap<String, Integer>) termIndex_single.getDocTermCount();
    		outHashMap.put(index++, DocTermCount_d);
    		
    	}
    	
    	
    	
    }
    
 

    /**
     * Method to create termVector according to its tf score.
     */
    public void tfCalculator() {
        double tf; //term frequency
        
        //System.out.println("**********");
        //System.out.println(outHashMap.size());
        //System.out.println("**********");
             
                
        int count = 0;
        int count_1 =0; 
        
        for (HashMap<String, Integer> f : outHashMap.values()) {
        	HashMap<Integer,Double> tfDocsVector_single = new HashMap<>();
            
            for (String term : allTermIndex_d.keySet()){
            	tf = new TfIdf().tfCalculator(f, term);
            	
            	tfDocsVector_single.put(count_1, tf);
            	count_1++;
            }
            count_1 = 0;
            
           
      		
            tfDocsVector.put(count, tfDocsVector_single);
            count++;
             //storing document vectors;            
        }
       
        
    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
    public void getCosineSimilarity() {
    	int sum = 0;
        for (HashMap<Integer, Double> f : tfDocsVector.values()) {
            sum ++;
        }
        for (int i = 0; i < sum; i++) {
            for (int j = 0; j < sum; j++) {
            	
            	
            	
            	
            	consineSimilarity = new CosineSimilarity().cosineSimilarity (tfDocsVector.get(i), tfDocsVector.get(j));
                //System.out.println("between " + i + " and " + j + "  =  "+ new CosineSimilarity().cosineSimilarity (tfDocsVector.get(i), tfDocsVector.get(j)));
            }
        }
    }
}

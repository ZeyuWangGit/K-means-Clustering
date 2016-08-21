import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class kmeans{
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//1、获取文档输入
		DocumentAnalysis documentAnalysis = new DocumentAnalysis();
    	String directory = "two_newsgroups";
    	documentAnalysis.analyzeFile(directory);
    	
    	//2、初始化TFIDF测量器，用来生产每个文档的TFIDF权重
    	documentAnalysis.tfCalculator();
    	
    	//System.out.println("Check whether the vertor is right");
    	//System.out.println(documentAnalysis.tfDocsVector.size());
    	
    	 //System.out.println("Fot Test+++++++++++++++++++");
         for (Integer key :documentAnalysis.tfDocsVector.get(0).keySet()) {  	  
   		    //System.out.println("Key = " + key);  
   		} 
         //System.out.print("*********** ");
     	//int l =0;
   		//for (double value : tfDocsVector_single.values()) { 
   			//System.out.print(l++ +" ");
   		    //System.out.println("Value = " + value);  
   		//}
         //System.out.println("Fot Test+++++++++++++++++++");
    	 

    	
   
    	int K =6; //聚成3个聚类
    	
    	//if(K>=5){
    		//K=K+2;
    	//}else{
    		//K=K+1;
    	//}

    	//4、初始化k-means算法，第一个参数表示输入数据，第二个参数表示要聚成几个类
    	kmeansTask kmeansE = new kmeansTask(documentAnalysis.tfDocsVector, K);
    	//System.out.println("Point1");
    	//5、开始迭代
    	kmeansE.Start();
    	//System.out.println("End");

    	//6、获取聚类结果并输出
    	kmeansCluster[] clusters = kmeansE._clusters;
    	//System.out.println("The clusters have :");
    	//System.out.println("[1] " + clusters[0].CurrentMembership.size());
    	//System.out.println("[2] " + clusters[1].CurrentMembership.size());
    	//System.out.println("[3] " + clusters[2].CurrentMembership.size());
    	
    		System.out.println();
    
    	
    	
    	
    	int p =1;
    	System.out.println("===============================");
    	for(int i=0;i<clusters.length;i++){
    		
    		List<Integer> members = new ArrayList<Integer>();
    		members = clusters[i].CurrentMembership;
    		if(members.isEmpty()) continue;
    		
    		
    		System.out.println("The cluster [" + (int)(p++) + "] has members");
    		
    		for(int k = 0; k < members.size(); k++)
    		{
    			//System.out.print(members.get(k)+":");
    			System.out.println(documentAnalysis.nameTransfer.get(members.get(k))+ " ");
    		}
    		
    		System.out.println();
    	}
    	System.out.println("===============================");
    	System.out.println("The K-Means can be divided into " + (int)(p-1) + " clusters!");
    	
	}
}



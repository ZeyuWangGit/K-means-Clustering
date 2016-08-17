import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class kmeansCluster
{
    public kmeansCluster(int dataindex,HashMap<Integer,Double> in)
    {
        CurrentMembership.add(dataindex);
        center = in;
    	 
    }
    // 该聚类的数据成员索引
    List<Integer> CurrentMembership = new ArrayList<Integer>();
    
    // 该成员集
    public HashMap<Integer,HashMap<Integer,Double>> currentMember = new HashMap<>();
    
    // 该聚类的中心
    public HashMap<Integer,Double> center;
    
    // 该方法计算聚类对象的均值 
    public void UpdateMean(HashMap<Integer,HashMap<Integer,Double>> coordinates)
    {
        // 根据 mCurrentMembership 取得原始资料点对象 coord ，该对象是 coordinates 的一个子集；
        //然后取出该子集的均值；取均值的算法很简单，可以把 coordinates 想象成一个 m*n 的距阵 ,
        //每个均值就是每个纵向列的取和平均值 , //该值保存在 mCenter 中	
    	
    	
    	
    	for(int i=0;i<CurrentMembership.size();i++){
    		currentMember.put(CurrentMembership.get(i), coordinates.get(CurrentMembership.get(i)));
    	}
    	int count_membership = 0;
    	for (HashMap<Integer,Double> f : currentMember.values()) {
            count_membership ++;
        }
    	//System.out.println(count_membership);
    	HashMap<Integer,Double> swap = coordinates.get(0);
    	int count_inner = 0;
    	for (Double f1 : swap.values()) {
            count_inner ++;
        }
    	//System.out.println(count_inner+"%%%%%");
    	//Mean = coordinates.get(0);
        for (int i = 0; i < count_membership; i++)
        {
        	double add=0;
        	double swap1 = 0;
        	
        	for(int j=0;j<count_inner;j++){
        		if(center.containsKey(j)){
        			add = center.get(j);
        			//System.out.println(add);
        		}
        		if(coordinates.containsKey(j)){
        			swap1 = coordinates.get(i).get(j);
        		}
        		
        		center.put(j, add+swap1);
        	}         
        }
        
     
        
        for(int i=0;i<count_inner;i++){
        	center.put(i,center.get(i)/count_membership);
        }
        
      
    }
}

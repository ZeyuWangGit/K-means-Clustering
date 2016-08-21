import java.util.HashMap;
import java.util.Random;

public class kmeansTask
{
    // 数据的数量
    public int _coordCount;
    // 原始数据
    public HashMap<Integer,HashMap<Integer,Double>> _coordinates;
    // 聚类的数量
    public int _k;
    // 聚类
    public kmeansCluster[] _clusters;

    // 定义一个变量用于记录和跟踪每个资料点属于哪个群聚类
    // _clusterAssignments[j]=i;// 表示第 j 个资料点对象属于第 i 个群聚类
    int[] _clusterAssignments;
    
    // 定义一个变量用于记录和跟踪每个资料点离聚类最近
    int[] _nearestCluster;
    
    // 定义一个变量，来表示资料点到中心点的距离,
    // 其中—_distanceCache[i][j]表示第i个资料点到第j个群聚对象中心点的距离；
     double[][] _distanceCache;
   
    // 用来初始化的随机种子
    private static Random _rnd = new Random(1);

    public kmeansTask(HashMap<Integer,HashMap<Integer,Double>> data, int K)
    {
        _coordinates = data;
    
        int sum = 0;
        for (HashMap<Integer,Double> f : _coordinates.values()) {
            sum ++;
        }
        _coordCount = sum;
        _k = K;
        _clusters = new kmeansCluster[K];
        _clusterAssignments = new int[_coordCount];
        _nearestCluster = new int[_coordCount];
        _distanceCache = new double[_coordCount][_k];
        InitRandom();
        
      
        
    }

    public void Start()
    {
        int iter = 0;
        while (true)
        {
            //Console.WriteLine("Iteration " + (iter++) + "");
            //1、重新计算每个聚类的均值
            for (int i = 0; i < _k; i++)
            {
                _clusters[i].UpdateMean(_coordinates);
            }
            //System.out.println(_clusters[0].center.containsKey(100));
            //System.out.println(_clusters[1].center.containsKey(100));
            //System.out.println(_clusters[2].center.containsKey(100));
                       
            //2、计算每个数据和每个聚类中心的距离
            for (int i = 0; i < _coordCount; i++)
            {
                for (int j = 0; j < _k; j++)
                {
            
                	
                
                	
                    double dist = 1-new CosineSimilarity().cosineSimilarity(_coordinates.get(i), _clusters[j].center);
                    
                    _distanceCache[i][j] = dist;
                    
                }
                //System.out.println();
            }
            //System.out.println("Point3");
            //3、计算每个数据离哪个聚类最近
            for (int i = 0; i < _coordCount; i++)
            {
            	_nearestCluster[i] = _nearestCluster(i);
            	//System.out.println(_nearestCluster[i]);
            }
            //System.out.println("Point4");
            //4、比较每个数据最近的聚类是否就是它所属的聚类
            //如果全相等表示所有的点已经是最佳距离了，直接返回；
            int k = 0;
            for (int i = 0; i < _coordCount; i++)
            {
                if (_nearestCluster[i] == _clusterAssignments[i])
                    k++;

            }
            if (k == _coordCount)
                break;
            //System.out.println("Point5");
            //5、否则需要重新调整资料点和群聚类的关系，调整完毕后再重新开始循环；
            //需要修改每个聚类的成员和表示某个数据属于哪个聚类的变量
            for (int j = 0; j < _k; j++)
            {
                _clusters[j].CurrentMembership.clear();
            }
            
            
            for (int i = 0; i < _coordCount; i++)
            {
                _clusters[_nearestCluster[i]].CurrentMembership.add(i);
                _clusterAssignments[i] = _nearestCluster[i];
            }
            
        }

    }


    // 计算某个数据离哪个聚类最近
    int _nearestCluster(int ndx)
    {
        int nearest = -1;
        double min = Double.MAX_VALUE;
        for (int c = 0; c < _k; c++)
        {
            double d = _distanceCache[ndx][c];
            if (d < min)
            {
                min = d;
                nearest = c;
            }
      
        }
        
        return nearest;
    }
   
    
    // 随机初始化k个聚类
    private void InitRandom()
    {
        for (int i = 0; i < _k; i++)
        {
            int temp = _rnd.nextInt(_coordCount);
            _clusterAssignments[temp] = i; //记录第temp个资料属于第i个聚类
            _clusters[i] = new kmeansCluster(temp,_coordinates.get(temp));
        }
    }
}

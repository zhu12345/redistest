package cn.manualIntervention.utils;

import java.util.HashMap;
import java.util.Map;

import cn.manualIntervention.utils.bo.TotalCountVo;


public class WorkingStatusHelper {
    private static Map<String,Integer> mapper = new HashMap<String,Integer> ();
    private static Map<String,Integer> totalCount = new HashMap<String,Integer> ();

    public static synchronized void init(String key, Integer total){
        mapper.put (key, 0);
        totalCount.put (key, total);
    }

    public static synchronized void addOne(String key){
        Integer val = mapper.containsKey(key)?
                mapper.get(key)+1:0;
        mapper.put (key, val);
    }

    public static  TotalCountVo get(String key) {
        Integer current = mapper.containsKey(key)?
                mapper.get (key):0;
        Integer total =  totalCount.containsKey(key)?
                totalCount.get (key):0;
        return new TotalCountVo (current, total);
    }
}

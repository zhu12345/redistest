package cn.redis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stringutils {
	public static List<List<String>> split(List<String> l){
		List<List<String>> lls = new ArrayList<List<String>>();
			for(String s:l){
				List<String> lb =Arrays.asList(s.split(":"));
				lls.add(lb);
			}
			return lls;
	}
}

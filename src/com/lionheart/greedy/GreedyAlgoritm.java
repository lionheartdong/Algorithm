package com.lionheart.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgoritm {

	public static void main(String[] args) {
		//首先创建一个Hashmap，将广播电台与覆盖地区的映射关系存储起来
		HashMap<String, HashSet<String>> boardcasts = new HashMap<String, HashSet<String>>();
		//将每个电台对应覆盖的地区用一个hashset存储起来
		HashSet<String> set1 = new HashSet<String>();
		HashSet<String> set2 = new HashSet<String>();
		HashSet<String> set3 = new HashSet<String>();
		HashSet<String> set4 = new HashSet<String>();
		HashSet<String> set5 = new HashSet<String>();
		//添加地区
		set1.add("Beijing");
		set1.add("Shanghai");
		set1.add("Tianjin");
		
		set2.add("Guangzhou");
		set2.add("Beijing");
		set2.add("Shenzhou");
		
		set3.add("Chengdu");
		set3.add("Shanghai");
		set3.add("Hangzhou");
		
		set4.add("Shanghai");
		set4.add("Tianjin");
		
		set5.add("Hangzhou");
		set5.add("Dalian");
		//将对应电台与地区添加到hashmap中
		boardcasts.put("k1", set1);
		boardcasts.put("k2", set2);
		boardcasts.put("k3", set3);
		boardcasts.put("k4", set4);
		boardcasts.put("k5", set5);
		//再创建一个所有地区的hashset集合
		HashSet<String> allareas = new HashSet<String>();
		allareas.add("Beijing");
		allareas.add("Shanghai");
		allareas.add("Tianjin");
		allareas.add("Guangzhou");
		allareas.add("Shenzhou");
		allareas.add("Chengdu");
		allareas.add("Hangzhou");
		allareas.add("Dalian");
		//然后创建一个arraylist存放最终的选择
		ArrayList<String> select = new ArrayList<String>();
		//也需要个临时set作为循环时的中间变量
		HashSet<String> temp = new HashSet<String>();
		//在所有地区都存在集合中时开始循环
		//存放电台的变量
		String maxKey = null;
		while(allareas.size() > 0) {
			//还需要重置maxkey
			maxKey = null;
			//需要一个集合存放交集
			//遍历映射的键
			for(String key : boardcasts.keySet()) {
				//重要！！将临时集合清零
				temp.clear();
				//遍历的目的是为了这一步得到与allareas集合的交集最多的集合
				//用areas存放遍历得到的集合
				temp.addAll(boardcasts.get(key));
				//再与所有地区取交集
				temp.retainAll(allareas);
				//下面是贪心算法的关键:每一次循环需要跟上一次保存下来的maxkey对应的集合进行比较,每一步寻找最优解
				//需要重置MaxKey
				if(temp.size()>0 && (maxKey == null ||temp.size() > boardcasts.get(maxKey).size())) {
					maxKey = key;
				}
			}
			//上述循环进行一次，表示找出了第一个电台，加入到select中，并从所有地区中删除相应的地区
			if(maxKey != null) {
			select.add(maxKey);
			allareas.removeAll(boardcasts.get(maxKey));
			}
		}
		System.out.println(select);
	}

}

package com.lionheart.kmp;

import java.util.Arrays;

public class kmp {

	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";
		
		int[] next = kmpNext(str2);
		System.out.println("next: "+ Arrays.toString(next));
		
		//测试Kmp算法
		int index = kmpSearch(str1, str2, next);
		System.out.println("index: " + index);		//15
	}
	//kmp搜索算法
	/**
	 *              
	 * @param str1  被匹配的字符串
	 * @param str2 需要匹配的字符串
	 * @param next str2的部分匹配表
	 * @return 匹配成功 则返回匹配成功的开始的第一个位置，否则返回-1；
	 */
	public static int kmpSearch(String str1,String str2,int[] next) {
		for(int i =0,j=0;i < str1.length();i++) {
			while(j>0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j-1];//这一步是kmp算法的核心，这里直接给出，可以在文章中理解后推一推
			}
			//如果不能理解，就直接记住就行
			if(str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if(j == str2.length()) {
				return i-j+1;//找到了
			}
		}
		return -1;
	}
	//寻找部分匹配值数组
	public static int[] kmpNext(String str) {
		//存放匹配值数组
		int[] next = new int[str.length()];
		next[0] = 0;//数组第一位总为0
		for(int i =1,j = 0; i< next.length;i++) {
			while(j>0 && str.charAt(i) != str.charAt(j)) {
				j = next[j-1];//这一步是kmp算法的核心，这里直接给出，可以在文章中理解后推一推
			}
			if(str.charAt(i) == str.charAt(j)) {
				j++;//找到匹配后，增加1
			}
			next[i] = j;//将对应的数组为赋值为求得匹配值
		}
		return next;
	}

}

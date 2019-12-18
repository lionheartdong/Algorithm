package com.lionheart.kmp;

public class ViolenceMatch {

	public static void main(String[] args) {
		//测试暴力匹配
		String str1 = "123234 345567 90123";
		String str2 = "3455";
		int index = violenceMatch(str1, str2);
		System.out.println("index: " + index);
	}
	
	//暴力匹配算法
	//匹配成功，则返回匹配的第一个位置坐标；失败则返回-1
	//s1是被匹配的原字符串，s2是需要匹配的字符串
	public static int violenceMatch(String str1,String str2) {
		//先将字符串转换成字符数组
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		
		int s1len = s1.length;
		int s2len = s2.length;
		//初始在字符串中的坐标位置
		int i = 0;
		int j = 0;
		while(i < s1len && j< s2len) {
			//保证不越界
			if(s1[i] == s2[j]) {
				//匹配到了
				i++;
				j++;
			}else {
				//没有匹配到
				i = i - (j-1);
				j = 0;
			}
		}
		//循环结束
		if(j == s2len) {
			return i-j;//匹配成功
		}else {
			return -1;
		}
	}

}

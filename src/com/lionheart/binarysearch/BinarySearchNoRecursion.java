package com.lionheart.binarysearch;
/*
 *     不用递归方法的二分查找
 *     查找的前提是数组已经有序
 */
public class BinarySearchNoRecursion {

	public static void main(String[] args) {
			//测试二分查找
		int[] arr = {1,2,4,5,7,23,45,67,123};
		System.out.println(binarysearch(arr, 23));
	}
	/**
	 *  不用递归的二分查找
	 * @param arr 需要查找的数组
	 * @param target 查找的目标
	 * @return  找到了目标数值，则返回该数值的位置；没有返回-1
	 */
	public static int binarysearch(int[] arr, int target) {
		//我的做法，太过于复杂
//		int start = 0;  //定义初始坐标
//		int end = arr.length-1; //定义末尾坐标
//		int mid = (start+end)/2; //定义中间位置
//		int result = 0;  //定义结果
//		boolean flag = true; //定义继续查找的标志
//		while(flag) {
//			//开始查找
//			if(target < arr[start] || target > arr[end]) {
//				//此时目标未在此数组内,先将标志位置为false，跳出循环
//				flag = false;
//				result = -1;
//			}else {
//				if(target > arr[mid] && mid + 1 != end) {
//					start = mid+1;
//					mid = (start+end)/2;
//				}
//				if(target < arr[mid] && start != mid-1) {
//					end = mid - 1;
//					mid = (start+end)/2;
//				}
//				if(target == arr[mid]) {
//					//找到了
//					flag = false;
//					result = arr[mid];
//				}else {
//					flag = false;
//					result = -1;
//				}
//			}
//		}
//		return result;
		//老师的做法，更为简单
		int left = 0;
		int right = arr.length-1;
		while(left<=right) {
			int mid = (left+right)/2;
			if(target == arr[mid]) {
				return mid;
			}else if(target > arr[mid]) {
				left = mid+1;
			}else if (target < arr[mid]) {
				right = mid-1;
			}
		}
		return -1;
	}

}

package com.linheart.DivideAndConquer;
/*
 *    分而治之算法解决汉诺塔问题
 */
public class divideAndConquer {

	public static void main(String[] args) {
		HanoiTower(3, 'A','B', 'C');
	}
	//分而治之算法实践，汉诺塔
	public static void HanoiTower(int num,char a,char b,char c) {
		if(num == 1) {
			System.out.println("第1个盘"+a+"->"+c);
		}else {
			//当盘数大于等于2时，将盘数分为两部分，最下面的盘为一部分，剩余的上面盘为另一部分
			//第一步将上面的盘全部从A移动到B，而由于打出的移动命令总是"a->c"，因此需要将b和c交换位置，才能正确打出移动命令
			HanoiTower(num-1, a, c, b);
			//只有在syso语句中才进行移动盘
			//这步将A中最后一个盘移动到C
			System.out.println("第"+num+"个盘"+a+"->"+c);
			//最后把B中的盘移动到C中，而由于打出的移动命令总是"a->c"，因此需要将a和b交换位置，才能正确打出移动命令
			HanoiTower(num-1, b, a, c);
			
		}
	}
}

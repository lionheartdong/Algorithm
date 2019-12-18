package com.lionheart.dynamic;

public class KnapsackProblem {

	public static void main(String[] args) {
		//首先进行背包问题的初始化，总容量为4，求在此容量下的物品组合的最大值,同时为01背包，即每个物品最多放一个
		//总共有三种物品，重量数组
		int[] weight = {1,4,3};
		//价值数组
		int[] value = {1500,3000,1000};
		//背包总容量
		int capacity = 4;
		//物品的个数
		int count = value.length;
		//用动态规划解决问题时，需要一个二维数组，每步存放当前的最优解，即目前的最大价值
		//数组的大小，行数比物品个数多一，因为数组含0坐标作为起始；列数比容量多一，同理。
		int[][] nowResult = new int[count+1][capacity+1];
		//需要一个同上同样大小的二维数组来储存路径
		int[][] path = new int[count+1][capacity+1];
		//先将我们作为决策的二维数组的第一行和第一列置为0，因为此时并无价值
		for(int i = 0; i<nowResult.length;i++) {
			nowResult[i][0] = 0;
		}
		for(int j = 0; j<nowResult[0].length;j++) {
			nowResult[0][j] = 0;
		}
		//nowResul[i][j]表示前i个物品中，能够装入容量为j的背包中的最大价值
		//往背包里加入物品，并按照法则计算,此时二维数组坐标应该从行和列从1开始，因为第一行和第一列均已置为0
		for(int i = 1; i<nowResult.length;i++) {
			for(int j = 1; j<nowResult[0].length;j++) {
				if(weight[i-1]>j) {
					//取物品时，由于weight数组是从0开始，因此为i-1
					//此时加入的物品重量大于目前的容量，不能加入,最大价值仍为未加入之前
					nowResult[i][j] = nowResult[i-1][j];
				}else {
					//取物品价值时，由于value数组是从0开始，因此为i-1
//					nowResult[i][j] = Math.max(nowResult[i-1][j], nowResult[i-1][j-weight[i-1]]+value[i-1]);
					if(nowResult[i-1][j]<nowResult[i-1][j-weight[i-1]]+value[i-1]) {
						nowResult[i][j] = nowResult[i-1][j-weight[i-1]]+value[i-1];
						path[i][j] = 1;//为什么只在这里将path数组对应的值置为1，因为这个条件下，才向背包里加物品
					}else {
						nowResult[i][j] = nowResult[i-1][j];
					}
				}
			}
		}
		//看看目前二维数组的值
		for(int i = 0; i<nowResult.length;i++) {
			for(int j = 0; j<nowResult[i].length;j++) {
				System.out.print(nowResult[i][j]+" ");
			}
			System.out.println();
		}
		for(int i = 0; i<nowResult.length;i++) {
			for(int j = 0; j<nowResult[i].length;j++) {
				System.out.print(path[i][j]+" ");
			}
			System.out.println();
		}
		//打印路径
		int m = path.length-1;
		int n = path[0].length-1;
		while(m>0 && n>0) {
			if(path[m][n] == 1) {
				System.out.printf("第%d个物品放入背包中",m);
				System.out.println();
				n -= weight[m-1];
			}
			m--;
		}
	}

}

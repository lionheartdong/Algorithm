package com.lionheart.floyd;

import java.util.Arrays;

public class FloydDemo {

	public static void main(String[] args) {
		char[] vertex = {'A','B','C','D','E','F','G'};
		final int N = 65535;
		int[][] weight = {
				new int[] {0,5,7,N,N,N,2},
				new int[] {5,0,N,9,N,N,3},
				new int[] {7,N,0,N,8,N,N},
				new int[] {N,9,N,0,N,4,N},
				new int[] {N,N,8,N,0,5,4},
				new int[] {N,N,N,4,5,0,6},
				new int[] {2,3,N,N,4,6,0}
		};
		//测试图的创建
		Graph graph = new Graph(vertex, weight);
		graph.showGraph();
		//测试弗洛伊德算法
		graph.floyd();
		graph.showGraph();
		graph.show();
	}

}
class Graph{
	char[] vertex; //顶点字符数组
	int[][] distance; //距离数组
	int[][] pre;  //前驱节点数组
	
	//构造器
	public Graph(char[] vertex,int[][] weight) {
		//对变量进行初始化
		this.vertex = vertex;
		int len = vertex.length;
		this.distance = new int[len][len];
		this.pre = new int[len][len];
		//然后进行第一次赋值
		//对距离数组进行赋值
		for(int i = 0; i < len; i++) {
			for(int j = 0; j< len; j++) {
				distance[i][j] = weight[i][j];
			}
		}
		//对初始前驱节点进行赋值
		for(int i = 0; i< len; i++) 
			for(int j = 0; j< len; j++) {
				pre[i][j] = i;
		}
	}
	//显示图的构造情况
	public void showGraph() {
		System.out.println("距离数组：");
		for(int[] line : this.distance) {
			System.out.println(Arrays.toString(line));
		}
		System.out.println("前驱节点数组：");
		for(int[] line : this.pre) {
			System.out.println(Arrays.toString(line));
		}
	}
	//得到对应坐标的代表的字符
	public char getChar(int index) {
		return vertex[index];
	}
	//弗洛伊德算法
	public void floyd() {
		//中间节点循环
		for(int k = 0; k < this.vertex.length; k++) {
			//开始节点循环
			for(int i = 0; i < this.vertex.length; i++) {
				//终点节点循环
				for(int j = 0; j < this.vertex.length ; j++) {
					int len = 0;
					len = this.distance[i][k] + this.distance[k][j];
					if( len < this.distance[i][j]) {
						this.distance[i][j] = len;
						pre[i][j] = pre[k][j];//这里不能直接用k进行赋值，因为在后面的循环中，不一致
					}
				}
			}
		}
	}
	//弗洛伊德算法展示最短路径
	public void show() {
		System.out.println("两两节点的最短路径为：");
		for(int i = 0; i< this.vertex.length-1; i++) {
			for(int j = i+1; j< this.vertex.length;j++) {
				System.out.println(getChar(i) + " -> "+ getChar(j) + " Minimal length: "+ this.distance[i][j]);
			}
			System.out.println();
		}
	}
}

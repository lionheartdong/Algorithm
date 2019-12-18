package com.lionheart.DijstraMinimalRoad;

import java.util.Arrays;
/*
 *   dijskstra算法，求图中一点到其他各个点之间的最短距离
 *   未能理解整个算法，经过查网上的资料后理解了
 */
public class dijstra {

	public static void main(String[] args) {
		//已知节点数组
		char[] vertex = {'A','B','C','D','E','F','G'};
		//节点数目
		int ver = vertex.length;
		int[][] weight = new int[ver][ver];
		final int INF = 65535;
		weight[0] = new int[]{INF,5,7,INF,INF,INF,2};
		weight[1] = new int[]{5,INF,INF,9,INF,INF,3};
		weight[2] = new int[]{7,INF,INF,INF,8,INF,INF};
		weight[3] = new int[]{INF,9,INF,INF,INF,4,INF};
		weight[4] = new int[]{INF,INF,8,INF,INF,5,4};
		weight[5] = new int[]{INF,INF,INF,4,5,INF,6};
		weight[6] = new int[]{2,3,INF,INF,4,6,INF};
		
		Graph graph = new Graph(vertex, weight);
		graph.showGraph();
		
		//测试
		graph.dsj(6);
		graph.showDSJ();
	}

}
//已访问顶点集合类
class VisitedVertex{
	//记录每个顶点是否被访问过的标志数组，访问过为真，未访问为假
	public boolean[] VisitedFlag;
	//记录当前坐标下的前一个顶点的下标，比如pre[0] = 1;表示 编号为0 的顶点的前置顶点为1
	public int[] pre;
	//记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求得的最短距离会存放到此数组中
	public int[] distance;
	
	
	//构造器
	/**
	 * 
	 * @param length 表示顶点的个数
	 * @param index  出发顶点对应的下标
	 */
	public VisitedVertex(int length, int index) {
		this.VisitedFlag = new boolean[length];
		this.pre = new int[length];
		this.distance = new int[length];
		
		//并进行简单初始化
		Arrays.fill(distance, 65535);
		//设置出发顶点被访问过
		this.VisitedFlag[index] = true; 
		//设置出发顶点的访问距离为0
		this.distance[index] = 0;
	}
	//判断顶点是否被访问过
	public boolean isVisted(int index) {
		return VisitedFlag[index];
	}
	//更新出发顶点到index顶点的距离
	public void updateDistance(int index,int newdistance) {
		 distance[index] = newdistance;
	}
	//更新前置节点为传入index指向的节点
	public void updatePre(int prenode,int index) {
		pre[prenode] = index;
	}
	//返回出发顶点到index顶点的距离
	public int getDistance(int index) {
		return distance[index];
	}
	//主要是更新distance距离数组以及判断是否被访问的数组
	//并找出这次更新中的最小距离，并将此时的最小距离的终点坐标进行返回，以此坐标为下一次算到其他节点的起始点
	public int updateArr() {
		int min = 65535,index =0;
		for(int i = 0; i< VisitedFlag.length; i++) {
			if(VisitedFlag[i] == false && distance[i] < min) {
				min = distance[i];
				index = i;
			}
		}
		VisitedFlag[index] = true;
		return index;
	}
	public void show() {
		System.out.println("=============");
		for(int i = 0; i<VisitedFlag.length;i++) {
			System.out.print(VisitedFlag[i] + " ");
		}
		System.out.println();
		for(int i : pre) {
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i : distance) {
			System.out.print(i+" ");
		}
		System.out.println();
		char[] vertex = {'A','B','C','D','E','F','G'};
		int count = 0;
		for(int i : distance) {
			if(i != 65535) {
				System.out.print(vertex[count] + "(" +i+ ")");
			}else {
				System.out.println("N");
			}
			count++;
		}
		System.out.println();
	}
}
//创建图类
class Graph{
	private char[] vertex; //图中的节点存放数组，由于暂时为字符，因此用字符数组存放
	private int[][] weight; //邻接矩阵用二维数组表示图中的边的关系
	private VisitedVertex visitedVertex;//已经访问过的顶点集合
	
	public Graph(char[] vertex, int[][] weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	//显示图
	public void showGraph() {
		for(int[] line : weight) {
			System.out.println(Arrays.toString(line));
		}
	}
	public void showDSJ() {
		visitedVertex.show();
	}
	//核心算法
	public void dsj(int index) {
		visitedVertex = new VisitedVertex(vertex.length, index);
		updateDistance(index);
		//这个循环是为了更新从初始节点到所有节点（除了它自己）的最小距离，所以从1开始，循环次数减一
		for(int j = 1; j < vertex.length; j++) {
			index = visitedVertex.updateArr();
			updateDistance(index);
		}
		
	}
	//对指定坐标点的顶点进行更新距离,第一次传入的是跟起始节点一致的节点坐标，第二次及以后，是选择distance数组中对应距离最小的那一个
	public void updateDistance(int index) {
		int distance= 0 ;
		for(int i = 0; i < weight[index].length; i++) {
			distance = visitedVertex.getDistance(index) + weight[index][i]; 
			//这个比较就是类似于寻找A -> C距离时，先看上一次更新距离中的最短距离为A -> B，加上B->C的距离，和原始数组中 A->C距离进行比较，然后再更新距离情况
			if(!visitedVertex.isVisted(i) && distance < visitedVertex.getDistance(i)) {
				visitedVertex.updatePre(i,index);
				visitedVertex.updateDistance(i, distance);
			}
		}
	}
}
package com.lionheart.MinimalTree;

import java.util.Arrays;

/*
 *    生成最小树算法：普林姆算法和克鲁斯卡尔算法
 */
public class MinimalTreeDemo {

	public static void main(String[] args) {
		//已知节点数组
		char[] vertex = {'A','B','C','D','E','F','G'};
		//节点数目
		int ver = vertex.length;
		//权值邻接矩阵，10000表示权值过大，即为不连通
		int[][] weight = {
				{10000,5,7,10000,10000,10000,2},
				{5,10000,10000,9,10000,10000,3},
				{7,10000,10000,10000,8,10000,10000},
				{10000,9,10000,10000,10000,4,10000},
				{10000,10000,8,10000,10000,5,4},
				{10000,10000,10000,4,5,10000,6},
				{2,3,10000,10000,4,6,10000},
		};
		//新建图对象
		Graph graph = new Graph(ver);
		//新建最小树对象
		miniTree miTree = new miniTree();
		miTree.createGraph(graph, ver, vertex, weight);
		//准备工作完成
		miTree.showGraph(graph);
		//测试普里姆算法
		miTree.prim(graph, 0);
	}

}
//创建最小树类
class miniTree{
	//创建图的方法
	/**
	 * 
	 * @param graph 图对象
	 * @param ver 图中节点数目
	 * @param vertex 节点字符数组
	 * @param weight 节点的邻接矩阵
	 */
	public void createGraph(Graph graph,int ver,char[] vertex,int[][] weight) {
		int i,j = 0;
		for(i = 0;i<ver;i++) {
			graph.vertex[i] = vertex[i];
			for(j = 0; j<ver;j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	//显示创建好的图
	public void showGraph(Graph graph) {
		for(int[] line : graph.weight) {
			System.out.println(Arrays.toString(line));
		}
	}
	//普里姆算法求最小生成树
	/**
	 *  graph 传入的图对象
	 *  ver 代表最小树开始的坐标
	 */
	public void prim(Graph graph, int ver) {
		//需要一个booealn数组，来标记每个节点是否被包含在树中,初始值为false，表示未包含
		boolean[] visitedFlag = new boolean[graph.vertexNum];
		//将传入的开始坐标设为已访问
		visitedFlag[ver] = true;
		//需要两个辅助int变量存放顶点；
		int n1 = -1;
		int n2 = -1;
		//设置一个比较权值的中间值，初始为最大值
		int minWieght = 10000;
		//开始找边，关键是下面的循环
		for(int k = 1; k < graph.vertexNum; k++) {
			//边的数目为顶点数-1
			for(int i = 0; i < graph.vertexNum; i++) {
				//虽然看起来是循环所有节点，但由下面的判断可知，其实是循环已访问过的节点。此处感觉可以尝试优化，执行效率可以提升，减少判断语句的数量
				//优化可采用空间换时间的方式，新建数组，将已访问的节点放进去
				for(int j = 0; j< graph.vertexNum; j++) {
					//虽然看起来是循环所有节点，但由下面的判断可知，其实是循环未访问过的节点
					if(visitedFlag[i] == true && visitedFlag[j] == false && graph.weight[i][j] < minWieght) {
						minWieght = graph.weight[i][j];
						n1 = i;
						n2 = j;
					}
				}
			}
			//找到一条边，则输出一条边
			System.out.println("Edge: "+graph.vertex[n1]+">"+graph.vertex[n2]+" weight:"+minWieght);
			//再将中间值重置为最大值
			minWieght = 10000;
			//还得把找到的边的第二个节点标记为已访问
			visitedFlag[n2] = true;
		}
	}
}
//创建图类
class Graph{
	int vertexNum; //图中的节点数
	char[] vertex; //图中的节点存放数组，由于暂时为字符，因此用字符数组存放
	int[][] weight; //邻接矩阵用二维数组表示图中的边的关系
	//构造方法,传入节点数目进行初始化
	public Graph(int ver) {
		this.vertexNum = ver;
		this.vertex = new char[ver];
		this.weight = new int[ver][ver];
	}
}

package com.lionheart.kruskalMiniTree;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *   克鲁斯卡尔算法形成最小生成树
 */
public class kruskalDemo {
	
	private int edgeNum; //边数目
	private char[] vertex;  //顶点字符数组
	private int[][] weight; //权值邻接矩阵
	//用INF表示两个顶点不能连通
	private static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		char[] vertex = {'a','b','c','d','e','f','g'};
		int[][] weight = {
				{0,12,INF,INF,INF,16,14},
				{12,0,10,INF,INF,7,INF},
				{INF,10,0,3,5,6,INF},
				{INF,INF,3,0,4,INF,INF},
				{INF,INF,5,4,0,2,8},
				{16,7,6,INF,2,0,9},
				{14,INF,INF,INF,8,9,0}
		};
		kruskalDemo kruskal = new kruskalDemo(vertex, weight);
		kruskal.print();
		//测试得到边数组而且进行排序
		edge[] edges = kruskal.getEdges();
		System.out.println(Arrays.toString(edges));
		System.out.println("After sort：");
		edgeSort(edges);
		System.out.println(Arrays.toString(edges));
		//测试克鲁斯卡尔算法
		kruskal.kruskal(edges);
	}
	//构造器方法
	public kruskalDemo(char[] vertex, int[][] weight) {
		int num = vertex.length;
		
		this.vertex = new char[num];
		for(int i = 0 ; i< num; i++) {
			this.vertex[i] = vertex[i];
		}
		
		this.weight = new int[num][num];
		for(int i = 0; i< num; i++) {
			for(int j = 0 ; j< num; j++) {
				this.weight[i][j] = weight[i][j];
			}
		}
		
		for(int i = 0; i < num; i++) {
			//统计边数时，注意j应该从 i+1开始，这样不会重复统计边，而且边的起点与终点一只没有意义
			for(int j = i+1; j < num; j++) {
				if(this.weight[i][j] != INF) {
					edgeNum ++;
				}
			}
		}
	}
	//打印方法
	public void print() {
		System.out.println("Weight matrix:");
		for(int i = 0; i < this.weight.length; i++) {
			for(int j = 0; j < this.weight.length; j++) {
				System.out.printf("%12d",this.weight[i][j]);
			}
			System.out.println();
		}
	}
	//根据字符得到其在字符数组中的位置,有则返回数值，没有则返回-1
	public int getPosition(char ch) {
		for(int i = 0; i < vertex.length; i++) {
			if(ch == vertex[i]) {
				return i;
			}
		}
		return -1;
	}
	//对边排序的方法
	/**
	 *  由于只传入一个数组，而且数据量比较少，可使用简单的冒泡排序
	 * @param edges  需要进行排序的边数组
	 */
	public static void edgeSort(edge[] edges) {
		edge temp;//比较权值大小的临时变量
		for(int i = 0; i < edges.length; i++) {
			for(int j = 0; j< edges.length - 1-i; j++) {
				if(edges[j].weight > edges[j+1].weight) {
					temp = edges[j];
					edges[j] = edges[j+1];
					edges[j+1] = temp;
				}
			}
		}
	}
	//得到边数组
	public edge[] getEdges(){
		//新建一个边数组
		edge[] edges = new edge[edgeNum];
		int index = 0; //此变量来对添加的边进行计数
		//通过对邻接矩阵进行判断
		for(int i = 0; i< vertex.length;i++) {
			for(int j = i+1; j< vertex.length;j++) {
				if(weight[i][j] != INF) {
					//如果边存在
					edges[index] = new edge(vertex[i], vertex[j], weight[i][j]);
					index++;
				}
			}
		}
		return edges;
	}
	//得到每个节点的终点,为了后面判断是否形成回路
	/**
	 * 
	 * @param ends  传入已知的终点数组，坐标为零，对应的值是a的终点
	 * @param i 指定的节点坐标
	 * @return  i对应的终点坐标
	 */
	public int getEnds(int[] ends, int i) {
		//我觉得可以用判断，而不是循环
		while(ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}
	//克鲁斯卡尔核心算法
	/**
	 *  关键在于是否形成回路的判断，以及得到相应终点的方法
	 * @param edges 已经排序好的边集
	 */
	public void kruskal(edge[] edges) {
		int[] ends = new int[edgeNum]; //创建每个节点的终点位置数组,长度为边的长度，因为每条边都得有一个终点
		//新建一个列表存放已经选择好的边
		ArrayList<edge> kruskalEdge = new ArrayList<edge>();
		//进行循环添加边
		//添加边的条件是这条边与现有的边的start节点对应的终点不一样
		for(edge edge: edges) {
			int p1 = getPosition(edge.start);//得到边的初始位置坐标
			int p2 = getPosition(edge.end); //得到边的末尾位置坐标
			
			int p1End = getEnds(ends, p1);
			int p2End = getEnds(ends, p2);
			
			if(p1End != p2End) {
				//没有形成回路
				ends[p1End] = p2End;  //使得第一个终点确立,很重要
				kruskalEdge.add(edge);
			}
		}
		for(int i = 0 ; i< kruskalEdge.size();i++) {
			System.out.println(kruskalEdge.get(i));
		}
	}
}
//为了便于后面的存放边，创建一个专门的边类
class edge{
	char start; //边的开始端
	char end; //边的结束端
	int weight; //边的权重
	//构造器方法
	public edge(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	//重写tostring，方便后面打印
	@Override
	public String toString() {
		return "edge [start=" +  start + ", end=" + end + ", weight=" + weight + "]";
	}
	
	
}


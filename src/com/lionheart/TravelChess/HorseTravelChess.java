package com.lionheart.TravelChess;
/*
 *   骑士周游或者马踏棋盘问题
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HorseTravelChess {
	private static int X;//棋盘的行数
	private static int Y;//棋盘的列数
	
	private static boolean[] visited; //判断点是否被访问过的一维数组
	private static boolean finished;//判断是否算法完成
	
	public static void main(String[] args) {
		X = 10;
		Y = 10;// 当为8*8时，电脑已经运算速度不够快,需要很久时间
		int row = 1;
		int column = 1;
		visited = new boolean[X*Y];
		int[][] chessboard = new int[X][Y];
		
		//测试
		System.out.println("gogogo:");
		long start = System.currentTimeMillis();
		System.out.println(start);
		travelChess(chessboard, row-1, column-1, 1);
		
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println("共耗时：" + (end-start)+"毫秒");
		
		for(int[] line: chessboard) {
			System.out.println(Arrays.toString(line));
		}
	}
	//马踏棋盘的主要方法，用到迭代
	/**
	 * 
	 * @param chessboard 棋盘二维数组
 	 * @param row 方法的初始行位置
	 * @param column 方法的初始列位置
	 * @param step 方法已进行的步数
	 */
	public static void travelChess(int[][] chessboard,int row,int column,int step) {
		chessboard[row][column] = step; //将这个步数记录在棋盘中
//		System.out.println(step);
		visited[row * Y + column] = true;//标记这个位置被访问过，注意乘的是原棋盘的列数
		//根据获得的坐标，获取下一个能走的坐标列表
		ArrayList<Point> nextList = getNextList(new Point(column,row));
		//先进行一次排序，再遍历,排序后除了为7的情况，速率大大提高
		//贪心算法的思想，选节点时，选取节点的下一个节点列表中节点中少的那一个
		sort(nextList);
		//开始遍历此列表
		while(!nextList.isEmpty()) {
			Point p = nextList.remove(0);
			//这里需要判断，在列表中的点是否被访问过
			if(!visited[p.y * Y + p.x]) {
			//步数加一
			travelChess(chessboard, p.y, p.x, step + 1);
			}
		}
		//判断这个坐标能否完成算法
		if(step < X*Y && !finished) {
			//没完成
			chessboard[row][column] = 0;//将这个坐标复位为0，表示不行，并不是将这个棋盘复位
			visited[row * Y + column] = false;//同时标为没访问
		}else {
			finished = true;
		}
	}
	//由当前的位置获取下一个可以走的位置列表，可使用Point类
	public static ArrayList<Point> getNextList(Point current){
		//初始化列表
		ArrayList<Point> nextList = new ArrayList<Point>();
		//创建一个新的点
		Point newPoint = new Point();
		//开始判断马能否走“日”
		//下面的加入节点的方法错误，需要另外实例化一个Point类对象，否则其实在nextList中的对象始终为newPoint，而且是同一个值，并且由于下面的
		//的判断条件一直在进行，反而计算出不符合规则的点
		//0
		if((newPoint.x = current.x + 2) < Y && (newPoint.y = current.y -1)>=0) {
			nextList.add(new Point(newPoint));
		}
		//1
		if((newPoint.x = current.x + 2) < Y && (newPoint.y = current.y +1) < X) {
			nextList.add(new Point(newPoint));
		}
		//2
		if((newPoint.x = current.x + 1) <Y && (newPoint.y = current.y +2) <X) {
			nextList.add(new Point(newPoint));
		}
		//3
		if((newPoint.x = current.x - 1) >=0 && (newPoint.y = current.y +2) <X) {
			nextList.add(new Point(newPoint));
		}
		//4
		if((newPoint.x = current.x - 2) >=0 && (newPoint.y = current.y +1) < X) {
			nextList.add(new Point(newPoint));
		}
		//5
		if((newPoint.x = current.x - 2) >=0 && (newPoint.y = current.y -1) >=0) {
			nextList.add(new Point(newPoint));
		}
		//6
		if((newPoint.x = current.x - 1) >=0 && (newPoint.y = current.y - 2) >=0) {
			nextList.add(new Point(newPoint));
		}
		//7
		if((newPoint.x = current.x + 1) < Y && (newPoint.y = current.y -2) >= 0) {
			nextList.add(new Point(newPoint));
		}
		
		return nextList;
	}
	//对算法进行优化处理，对获取下一步可执行的点的数量进行非递减排序
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {
			//比较器的使用
			@Override
			public int compare(Point o1, Point o2) {
				int n1 = getNextList(o1).size();
				int n2 = getNextList(o2).size();
				if(n1 > n2) {
					return 1;
				}else if (n1 == n2) {
					return 0;
				}else {
					return -1;
				}
			}
		});
	}

}

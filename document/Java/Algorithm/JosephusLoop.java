package com.melody.lee;

import java.util.ArrayList;
import java.util.List;

public class JosephusLoop {
	public static void Josephus(int number,int start,int distance){
		List<Integer> list=new ArrayList<>(number);
		for(int i=0;i<number;i++){
			list.add(i);
		}
		System.out.println(list);
		int i=start;
		while(list.size()>0){
			i=(i+distance-1) % list.size();
			System.out.println("移除了"+list.remove(i)+" size="+list.size());
		}
	}
	public static void Josephus2(int number,int start,int distance){
		boolean[] arr = new boolean[number];// 一共8个人
		for (int i = 0; i < arr.length; i++) {
			arr[i] = true;
		}
		int leftCount = arr.length;
		int countNum = 0;
		int index = start - 1;// 从index开始数
		while (leftCount > 0) {//剩下的人数
			if (arr[index] == true) {
				countNum++;
				if (countNum == distance) {// 数到3的时候退出
					System.out.println("拿出第" + (index + 1) + "人");
					countNum = 0;
					arr[index] = false;
					leftCount--;
				}
			}
			index++;
			if (index == arr.length) {
				index = 0;
			}
		}
	}
	public static void main(String[] args) {
		Josephus(8, 5, 3);
	}
}

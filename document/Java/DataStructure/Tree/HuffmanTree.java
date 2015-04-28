package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree<E> {
	public static class Node<E>{
		E data;
		double weight;
		Node<E> leftChild;
		Node<E> rightChild;
		public Node(E data,double weight){
			this.data=data;
			this.weight=weight;
		}
		public String toString(){
			return "Node[data="+data+",weight="+weight+"]";
		}
	}
	private static <E> Node<E> createTree(List<Node<E>> nodes){
		while(nodes.size()>1){
			quickSort(nodes);
			Node<E> left=nodes.get(nodes.size()-1);
			Node<E> right=nodes.get(nodes.size()-2);
			Node<E> parent=new Node<>(null,left.weight+right.weight);
			parent.leftChild=left;
			parent.rightChild=right;
			nodes.remove(nodes.size()-1);
			nodes.remove(nodes.size()-1);
			nodes.add(parent);
		}
		return nodes.get(0);
	}
	
	private static <E> void swap(List<Node<E>> nodes,int i,int j){
		Node<E> tmp;
		tmp=nodes.get(i);

		nodes.set(j, tmp);
	}
	private static <E> void subSort(List<Node<E>> nodes,int start,int end){
		if(start<end){
			Node base=nodes.get(start);
			int i=start;
			int j=end+1;
			while(true){
				while(i<end && nodes.get(++i).weight>=base.weight);
				while(j>start && nodes.get(--j).weight<=base.weight);
				if(i<j){
					swap(nodes, i, j);
				}else{
					break;
				}
			}
			swap(nodes, start, j);
			subSort(nodes, start, j-1);
			subSort(nodes, j+1, end);
		}
	}
	public static <E> void quickSort(List<Node<E>> nodes){
		subSort(nodes, 0, nodes.size()-1);
	}
	public static List<Node> breadthFirst(Node root){
		Queue<Node> queue=new ArrayDeque<Node>();
		List<Node> list=new ArrayList<Node>();
		if(root!=null){
			queue.offer(root);
		}
		while(!queue.isEmpty()){
			list.add(queue.peek());
			Node p=queue.poll();
			if(p.leftChild!=null){
				queue.offer(p.leftChild);
			}
			if(p.rightChild!=null){
				queue.offer(p.rightChild);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<Node<String>> nodes=new ArrayList<>();
		nodes.add(new Node<String>("A",40.0));
		nodes.add(new Node<String>("B",7.0));
		nodes.add(new Node<String>("C",10.0));
		nodes.add(new Node<String>("D",30.0));
		nodes.add(new Node<String>("E",12.0));
		nodes.add(new Node<String>("F",2.0));
		Node<String> root=HuffmanTree.createTree(nodes);
		System.out.println(breadthFirst(root));
	}
}

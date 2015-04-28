package Tree;

import java.util.ArrayList;
import java.util.List;

//父节点表示法
public class TreeParent <E>{
	
	public static class Node<T>{
		T data;
		int parent;
		public Node(){}
		public Node(T data){
			this.data=data;
		}
		public Node(T data,int parent){
			this.data=data;
			this.parent=parent;
		}
		public String toString(){
			return "TreeParent$Node[data="+data+",parent="+parent+"]";
		}
	}
	
	private final int DEFAULT_TREE_SIZE=100;
	private int treeSize=0;
	//使用一个node[]节点来记录该树里面的所有节点
	private Node<E> [] nodes;
	private int nodeNums;
	
	@SuppressWarnings("unchecked")
	public TreeParent(E data){
		treeSize=DEFAULT_TREE_SIZE;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data,-1);
		nodeNums++;
	}
	
	@SuppressWarnings("unchecked")
	public TreeParent(E data,int treeSize){
		this.treeSize=treeSize;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data,-1);
		nodeNums++;
	}
	
	public int pos(Node node){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==node){
				return i;
			}
		}
		return -1;
	}
	
	public void addNode(E data,Node parent){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==null){
				nodes[i]=new Node<E>(data,pos(parent));
				nodeNums++;
				return;
			}
		}
		throw new RuntimeException("该数已满，无法添加新节点");
	}
	public boolean empty(){
		return nodes[0]==null;
	}
	public Node<E> root(){
		return nodes[0];
	}
	public Node<E> parent(Node node){
		return nodes[node.parent];
	}
	public List<Node<E>> children(Node parent){
		List<Node<E>> list=new ArrayList<Node<E>>();
		for(int i=0;i<treeSize;i++){
			if(nodes[i]!=null && nodes[i].parent==pos(parent)){
				list.add(nodes[i]);
			}
		}
		return list;
	}
	public int deep(){
		int max=0;
		for(int i=0;i<treeSize && nodes[i]!=null;i++){
			int def=1;
			int m=nodes[i].parent;
			while(m!=-1 && nodes[m]!=null){
				m=nodes[m].parent;
				def++;
			}
			if(max<def){
				max=def;
			}
		}
		return max;
	}
}

package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//红黑树
public class RedBlackTree<T extends Comparable> {
	private static final boolean RED=false;
	private static final boolean BLACK=true;
	static class Node{
		Object data;
		Node parent;
		Node left;
		Node right;
		boolean color=BLACK;
		public Node(Object data,Node parent,Node left,Node right){
			this.data=data;
			this.parent=parent;
			this.left=left;
			this.right=right;
		}
		public String toString(){
			return "[data="+data+",color="+color+"]";
		}
		public boolean equals(Object obj){
			if(this==obj){
				return true;
			}
			if(obj.getClass()==Node.class){
				Node target=(Node) obj;
				return data.equals(target.data) && color==target.color && right==target.right && parent==parent.parent;
			}
			return false;
		}
	}
	
	private Node root;
	public RedBlackTree(){
		root=null;
	}
	public RedBlackTree(T o){
		root=new Node(o,null,null,null);
	}
	public void add(T ele){
		if(root==null){
			root=new Node(ele,null,null,null);
		}else{
			Node current=root;
			Node parent=null;
			int cmp=0;
			
			do
			{
				parent=current;
				cmp=ele.compareTo(current.data);
				if(cmp>0)
				{
					current=current.right;
				}
				else
				{
					current=current.left;
				}
			}
			while(current!=null);
			Node newNode=new Node(ele, parent, null, null);
			if(cmp>0)
			{
				parent.right=newNode;
			}
			else
			{
				parent.left=newNode;
			}
			fixAfterInsertion(newNode);
		}
	}
	
	
	public void remove(T ele){
		Node target=getNode(ele);
		if(target.left!=null && target.right!=null)
		{
			Node s=target.left;
			while(s.right!=null)
			{
				s=s.right;
			}
			target.data=s.data;
			target=s;
		}
		Node replacement=(target.left !=null ? target.left:target.right);
		if(replacement!=null)
		{
			replacement.parent=target.parent;
		}
		else if(target==target.parent.left)
		{
			target.parent.left=replacement;
		}
		else
		{
			target.parent.right=replacement;
		}
		target.left=target.right=target.parent=null;
		if(target.color==BLACK)
		{
			fixAfterDeletion(replacement);
		}
		else if(target.parent==null)
		{
			root=null;
		}
		else
		{
			if(target.color==BLACK)
			{
				fixAfterDeletion(target);
			}
			if(target.parent!=null)
			{
				if(target==target.parent.left.left)
				{
					target.parent.left=null;
				}
				else if(target==target.parent.right)
				{
					target.parent.right=null;
				}
				target.parent=null;
			}
		}
	}
	
	public List<Node> breathFirst() {
		Queue<Node> queue = new ArrayDeque<Node>();
		List<Node> list = new ArrayList<Node>();
		if (root != null) {
			queue.offer(root);
		}
		while (!queue.isEmpty()) {
			list.add(queue.peek());
			Node p = queue.poll();
			if (p.left != null) {
				queue.offer(p.left);
			}
			if (p.right != null) {
				queue.offer(p.right);
			}
		}
		return list;
	}
	
	private Node getNode(T ele) {
		Node p=root;
		while(p!=null)
		{
			int cmp=ele.compareTo(p.data);
			if(cmp<0){
				p=p.left;
			}
			else if(cmp>0){
				p=p.right;
			}else{
				return p;
			}
		}
		return null;
	}
	
	
	private void fixAfterInsertion(Node x) {
		x.color=RED;
		while(x!=null && x!=root && x.parent.color==RED)
		{
			if(parentOf(x)==leftOf(parentOf(parentOf(x))))
			{
				Node y=rightOf(parentOf(parentOf(x)));
				if(colorOf(y)==RED)
				{
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x=parentOf(parentOf(x));
				}
				else
				{
					if(x==rightOf(parentOf(x)))
					{
						x=parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
			else
			{
				Node y=leftOf(parentOf(parentOf(x)));
				if(colorOf(y)==RED)
				{
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x=parentOf(parentOf(x));
				}
				else
				{
					if(x==leftOf(parentOf(x)))
					{
						x=parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color=BLACK;
	}
	
	
	
	private void fixAfterDeletion(Node x) {
		while(x!=null && colorOf(x)==BLACK)
		{
			if(x==leftOf(parentOf(x)))
			{
				Node sib=rightOf(parentOf(x));
				if(colorOf(sib)==RED)
				{
					setColor(parentOf(x), RED);
					setColor(sib, BLACK);
					rotateLeft(parentOf(x));
					sib=rightOf(parentOf(x));
				}
				if(colorOf(leftOf(sib))==BLACK && colorOf(rightOf(sib))==BLACK)
				{
					setColor(sib, RED);
					x=parentOf(x);
				}
				else
				{
					if(colorOf(rightOf(sib))==BLACK)
					{
						setColor(leftOf(sib), BLACK);
						setColor(leftOf(sib), RED);
						rotateRight(sib);
						sib=rightOf(parentOf(x));
					}
					setColor(sib,colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x=root;
				}
			}
			else
			{
				Node sib=leftOf(parentOf(x));
				if(colorOf(sib)==RED)
				{
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib=leftOf(parentOf(x));
				}
				if(colorOf(rightOf(sib))==BLACK  && colorOf(leftOf(sib))==BLACK)
				{
					setColor(sib, RED);
					x=parentOf(x);
				}
				else
				{
					if(colorOf(leftOf(sib))==BLACK)
					{
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib=leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x=root;
				}
			}
		}
		setColor(x, BLACK);
	}
	
	private boolean colorOf(Node p){
		return (p==null?BLACK:p.color);
	}
	private Node parentOf(Node p){
		return (p!=null?null:p.parent);
	}
	private void setColor(Node p,boolean c){
		if(p!=null){
			p.color=c;
		}
	}
	private Node leftOf(Node p){
		return (p==null?null:p.left);
	}
	private Node rightOf(Node p){
		return (p==null?null:p.right);
	}
	
	
	
	private void rotateRight(Node p) {
		if(p!=null)
		{
			Node r=p.right;
			Node q=r.left;
			p.right=q;
			if(q!=null)
			{
				q.parent=p;
			}
			r.parent=p.parent;
			if(p.parent==null)
			{
				root=r;
			}
			else if(p.parent.left==p)
			{
				p.parent.left=r;
			}
			else
			{
				p.parent.right=r;
			}
			r.left=p;
			p.parent=r;
		}
	}
	private void rotateLeft(Node p) {
		if(p!=null)
		{
			Node l=p.left;
			Node q=l.right;
			p.left=q;
			if(q!=null)
			{
				q.parent=p;
			}
			l.parent=p.parent;
			if(p.parent==null)
			{
				root=l;
			}
			else if(p.parent.right==p)
			{
				p.parent.right=l;
			}
			else
			{
				p.parent.left=l;
			}
			l.right=p;
			p.parent=l;
		}
	}
	public List<Node> inIterator(){
		return inIterator(root);
	}
	private List<Node> inIterator(Node node) {
		List<Node> list=new ArrayList<Node>();
		if(node.left!=null)
		{
			list.addAll(inIterator(node.left));
		}
		list.add(node);
		if(node.right!=null)
		{
			list.addAll(inIterator(node.right));
		}
		return list;
	}
}

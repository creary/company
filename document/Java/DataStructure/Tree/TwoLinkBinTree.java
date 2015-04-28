package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//二叉链表实现二叉树
public class TwoLinkBinTree<E> {

	public static class TreeNode {
		Object data;
		TreeNode left;
		TreeNode right;

		public TreeNode() {
		}

		public TreeNode(Object data) {
			this.data = data;
		}

		public TreeNode(Object data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private TreeNode root;

	public TwoLinkBinTree() {
		this.root = new TreeNode();
	}

	public TwoLinkBinTree(E data) {
		this.root = new TreeNode(data);
	}

	public TreeNode addNode(TreeNode parent, E data, boolean isLeft) {
		if (parent == null) {
			throw new RuntimeException("节点为null,无法添加子节点");
		}
		if (isLeft && parent.left != null) {
			throw new RuntimeException(parent + "节点已经有左子节点,无法添加子节点");
		}
		if (!isLeft && parent.right != null) {
			throw new RuntimeException(parent + "节点已经有右子节点,无法添加子节点");
		}
		TreeNode newNode = new TreeNode(data);
		if (isLeft) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		return newNode;
	}

	public boolean empty() {
		return root.data == null;
	}

	public TreeNode root() {
		if (empty()) {
			throw new RuntimeException("树为空,无法添加子节点");
		}
		return root;
	}

	public E parent(TreeNode node) {
		return null;
	}

	public E leftChild(TreeNode parent) {
		if (parent == null) {
			throw new RuntimeException(parent + "节点为null,无法添加子节点");
		}
		return parent.left == null ? null : (E) parent.left.data;
	}

	public E rightChild(TreeNode parent) {
		if (parent == null) {
			throw new RuntimeException(parent + "节点为null,无法添加子节点");
		}
		return parent.right == null ? null : (E) parent.right.data;
	}

	public int deep() {
		return deep(root);
	}

	private int deep(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		} else {
			int leftDeep = deep(node.left);
			int rightDeep = deep(node.right);
			int max = leftDeep > rightDeep ? leftDeep : rightDeep;
			return max + 1;
		}
	}

	// 实现先序遍历
	public List<TreeNode> preIterator() {
		return preIterator(root);
	}

	private List<TreeNode> preIterator(TreeNode node) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		// 处理根节点
		list.add(node);
		// 处理左子树
		if (node.left != null) {
			list.addAll(preIterator(node.left));
		}
		// 处理右子树
		if (node.right != null) {
			list.addAll(preIterator(node.right));
		}
		return list;
	}
	
	//实现中序遍历
	public List<TreeNode> inIterrator(){
		return inIterrator(root);
	}

	private List<TreeNode> inIterrator(TreeNode node) {
		List<TreeNode> list=new ArrayList<TreeNode>();
		if(node.left!=null){
			list.addAll(inIterrator(node.left));
		}
		list.add(node);
		if(node.right!=null){
			list.addAll(inIterrator(node.right));
		}
		return list;
	}
	
	//实现后序遍历
	public List<TreeNode> postIterrator(){
		return postIterrator(root);
	}

	private List<TreeNode> postIterrator(TreeNode node) {
		List<TreeNode> list=new ArrayList<TreeNode>();
		if(node.left!=null){
			list.addAll(postIterrator(node.left));
		}
		if(node.right!=null){
			list.addAll(postIterrator(node.right));
		}
		list.add(node);
		return list;
	}
	
	//广度优先遍历
	public List<TreeNode> breathFirst(){
		Queue<TreeNode> queue=new ArrayDeque<TreeNode>();
		List<TreeNode> list=new ArrayList<TreeNode>();
		//将根元素加入队列
		if(root!=null){
			queue.offer(root);
		}
		while(!queue.isEmpty()){
			//将该队列的头部元素添加到list中
			list.add(queue.peek());
			//将该队列的头部元素移除队列
			TreeNode p=queue.poll();
			//如果左子节点不是null,将它加入队列
			if(p.left!=null){
				queue.offer(p.left);
			}
			//如果右子节点部位null，将它加入队列
			if(p.right!=null){
				queue.offer(p.right);
			}
		}
		return list;
	}
}

package TreeTest;

import java.util.List;

import Tree.TreeChild;

public class TreeChildTest {
	public static void main(String[] args) {
		TreeChild<String> tp=new TreeChild<String>("root");
		TreeChild.Node root=tp.root();
		System.out.println("根节点:"+root);
		tp.addNode("节点1", root);
		tp.addNode("节点2", root);
		tp.addNode("节点3", root);
		System.out.println("添加子节点后的根节点："+root);
		System.out.println("次书的深度："+tp.deep());
		List<TreeChild.Node<String>> nodes=tp.children(root);
		System.out.println("根节点的第一个子节点："+nodes.get(0));
		tp.addNode("节点4", nodes.get(0));
		System.out.println("根节点的第一个子节点:"+nodes.get(0));
		System.out.println("次树的深度:"+tp.deep());
	}
}

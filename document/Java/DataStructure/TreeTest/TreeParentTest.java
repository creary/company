package TreeTest;

import java.util.List;

import Tree.TreeParent;

public class TreeParentTest {
	public static void main(String[] args) {
		TreeParent<String> tp=new TreeParent<String>("root");
		TreeParent.Node root=tp.root();
		System.out.println(root);
		tp.addNode("节点1", root);
		System.out.println("次树的深度:"+tp.deep());
		tp.addNode("节点2", root);
		List<TreeParent.Node<String>> nodes=tp.children(root);
		System.out.println("根节点的第一个子节点:"+nodes.get(0));
		tp.addNode("节点3", nodes.get(0));
		System.out.println("次树的深度:"+tp.deep());
	}
}

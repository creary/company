package TreeTest;

import Tree.SortedBinTree;

public class SortedBinTreeTest {
	public static void main(String[] args) {
		SortedBinTree<Integer> tree=new SortedBinTree<Integer>();
		tree.add(5);
		tree.add(13);
		tree.add(20);
		tree.add(10);
		tree.add(30);
		tree.add(8);
//		tree.add(15);
		System.out.println(tree.breathFirst());
		tree.remove(20);
		System.out.println(tree.breathFirst());
	}
}

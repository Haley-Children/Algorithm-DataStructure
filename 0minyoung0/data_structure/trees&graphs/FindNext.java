// 이진검색트리에서 다음노드 찾기 (inorder traverse)
// https://youtu.be/6DIxzakjewQ
// 이진검색트리에서 주어진 노드의 다음노드를 찾는 함수를 구현하시오.
// (단, 다음 노드의 순서는 inorder traverse에 입각함)

package treeAndGraphs.findNext;

class Tree {
	class Node {
		int data;
		Node left;
		Node right;
		Node parent;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	Tree (int size) {
		root = makeBST(0, size - 1, null);
	}
	Node makeBST(int start, int end, Node parent) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1, node);
		node.right = makeBST(mid + 1, end, node);
		node.parent = parent;
		return node;
	}
	void findNext(Node node) {
		if (node.right == null) {
			System.out.println(findAbove(node.parent, node).data + " is " + node.data + "'s next");
		} else {
			System.out.println(findBelow(node.right).data + " is " + node.data + "'s next");
		}
	}
	Node findAbove(Node root, Node child) {
		if (root == null) return null;
		if (root.left == child) return root;
		return findAbove(root.parent, root);
	}
	Node findBelow(Node root) {
		if (root.left == null) return root;
		return findBelow(root.left);
	}
}

public class FindNext {
	public static void main(String[] args) {
		Tree t = new Tree(10);
/*
	     (4)
	    /   \
	   /     \
	  /       \
	(1)       (7)
   /   \     /   \
 (0)   (2)	(5)  (8)
	     \    \    \
	     (3)  (6)  (9)
*/
		t.findNext(t.root.left.right.right); // 4 is 3's next
		t.findNext(t.root.left);			 // 2 is 1's next
		t.findNext(t.root);					 // 5 is 4's next
		t.findNext(t.root.left.left);		 // 1 is 0's next
		t.findNext(t.root.right.left.right); // 7 is 6's next
	}
}

// 서브트리인지 확인하기
// https://youtu.be/-oQaeT-JV0w
// 크기가 매우 큰 두개의 이진트리 T1과 T2가 있다.
// T1은 T2보다 큰데 T2가 T1의 서브트리인지를 확인하는 알고리즘을 구현하시오.
// T2의 루트노드의 값이 T1에 존재하고,
// 그 노드를 기준으로 줄기를 잘라냈을 때, T2의 값과 구조가 같으면 서브트리로 인정함.

package treeAndGraphs.containsTree;

class Tree {
	class Node {
		int data;
		Node left;
		Node right;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	Node makeBST(int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1);
		node.right = makeBST(mid + 1, end);
		return node;
	}
	boolean containsTree(Node t1, Node t2) {
		if (t2 == null) return true;
		return subTree(t1, t2);
	}
	boolean subTree(Node t1, Node t2) {
		if (t1 == null) {
			return false;
		}else if (t1.data == t2.data && matchTree(t1, t2)) {
			return true;
		}
		return subTree(t1.left, t2) || subTree(t1.right, t2);
	}
	boolean matchTree(Node t1, Node t2) {
		if (t1 == null && t2 == null) {
			return true;
		}else if (t1 == null || t2 == null) {
			return false;
		}else if (t1.data != t2.data) {
			return false;
		}else {
			return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right); 
		}
	}
}

public class ContainsTree {
	public static void main(String[] args) {
		Tree t1 = new Tree();
		Tree t2 = new Tree();
		boolean result;
		/*
			    (4)
			   /   \
			  /     \
			 /       \
		   (1)       (7)
		  /   \     /   \
		(0)   (2)  (5)  (8)
			     \    \    \
			     (3)  (6)  (9)
		*/
		t1.root = t1.makeBST(0, 9);
		t2.root = t2.makeBST(5, 9);
		result = t1.containsTree(t1.root, t2.root);
		System.out.println(result); // true
		
		/*
		 * (8)
		   / \
		 (7) (9)
		 */
		t2.root = t2.makeBST(7, 9);
		result = t1.containsTree(t1.root, t2.root);
		System.out.println(result); // false
	}
}

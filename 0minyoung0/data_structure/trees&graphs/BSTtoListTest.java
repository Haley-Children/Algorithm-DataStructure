// 이진트리를 레벨단위 리스트로 변형하기
// https://youtu.be/Y9Ar9eerxQU
// 이진트리의 노드들을 각 레벨별로 LinkedList에 담는 알고리즘을 구현하시오.
// (예를들어, 5개의 깊이를 가지는 트리라면 5개의 LinkedList를 만들어야 함)

package treeAndGraphs.binarySearchTreeToList;

import java.util.LinkedList;
import java.util.ArrayList;

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
	Tree(int size) {
		root = makeBST(0, size - 1);
	}
	Node makeBST(int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1);
		node.right = makeBST(mid + 1, end);
		return node;
	}
	// Solution 1 : 함수가 호출될때마다 현재 노드가 몇번째 레벨인지를 인자로 받는 방법
	// 시간복잡도 O(N), 공간복잡도 O(N) + O(logN) <- 스택에 보관하기 때문에 추가로 O(logN)이 필요
	ArrayList<LinkedList<Node>> BSTtoList() {
		ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
		BSTtoList(root, lists, 0);
		return lists;
	}
	void BSTtoList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
		if (root == null) return;
		LinkedList<Node> list = null;
		if (lists.size() == level) {
			list = new LinkedList<Node>();
			lists.add(list);
		}else {
			list = lists.get(level);
		}
		list.add(root);
		BSTtoList(root.left, lists, level + 1);
		BSTtoList(root.right, lists, level + 1);
	}
	// Solution 2 : BFS를 활용하여 루트부터 시작하여 탐색하는 방법
	// 시간복잡도 O(N), 공간복잡도 O(N)
	ArrayList<LinkedList<Node>> BSTtoList2() {
		ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> current = new LinkedList<Node>();
		if (root != null) {
			current.add(root);
		}
		while (current.size() > 0) {
			result.add(current);
			LinkedList<Node> parents = current;
			current = new LinkedList<Node>();
			for (Node parent : parents) {
				if (parent.left != null) current.add(parent.left);
				if (parent.right != null) current.add(parent.right);
			}
		}
		return result;
	}
	void printList(ArrayList<LinkedList<Node>> arr) {
		for (LinkedList<Node> list : arr) {
			for (Node node : list) {
				System.out.print(node.data + " "); 
			}
			System.out.println();
		}
	}
}
public class BSTtoListTest {
	public static void main(String[] args) {
		Tree t = new Tree(10);
		t.printList(t.BSTtoList());
		t.printList(t.BSTtoList2());
		/*
			4 
			1 7 
			0 2 5 8 
			3 6 9 
		 */
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
	}
}

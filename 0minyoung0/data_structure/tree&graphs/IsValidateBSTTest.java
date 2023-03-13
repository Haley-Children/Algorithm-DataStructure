// 주어진 트리가 이진검색트리인지 확인하기
// https://youtu.be/zhhxBrtaOO0
// 주어진 트리가 이진검색트리인지를 확인하는 함수를 구현하시오.

package treeAndGraphs.isValidateBST;

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
	int size;
	Tree(int size) {
		this.size = size;
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
	
	// Solution 1: using inorder
	// inorder 순회하며 배열에 저장해서 정렬되어 있는지 확인
	boolean isValidateBST1() {
		int[] array = new int[size];
		inorder(root, array);
		for (int i=1; i<array.length; i++) {
			if (array[i] < array[i-1]) {
				return false;
			}
		}
		return true;
	}
	int index = 0;
	void inorder(Node root, int[] array) {
		if (root != null) {
			inorder(root.left, array);
			array[index] = root.data;
			index++;
			inorder(root.right, array);
		}
	}
	
	// Solution 2: without array
	// 위와 같지만 배열에 순회 결과를 다 저장하지 않고 이전 값만 저장해서 비교
	Integer last_printed = null;
	boolean isValidateBST2() {
		return isValidateBST2(root);
	}
	boolean isValidateBST2(Node n) {
		if (n == null) return true;
		if (!isValidateBST2(n.left)) return false;
		if (last_printed != null && n.data < last_printed) {
			return false;
		}
		last_printed = n.data;
		if (!isValidateBST2(n.right)) return false;
		return true;
	}
	
	// Solution 3: min/max
	// 트리의 왼쪽으로 갈때는 현재 값을 max 범위로
	// 오른쪽으로 갈때는 현재 값을 min 범위로 설정하여 순회
	boolean isValidateBST3() {
		return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	boolean isValidateBST3(Node n, int min, int max) {
		if (n == null) {
			return true;
		}
		if (n.data < min || n.data > max) {
			return false;
		}
		if (!isValidateBST3(n.left, min, n.data) || !isValidateBST3(n.right, n.data, max)) {
			return false;
		}
		return true;
	}
}

public class IsValidateBSTTest {
	public static void main(String[] args) {
		Tree t = new Tree(10);
		System.out.println("Solution 1 - using inorder: " + t.isValidateBST1());
		System.out.println("Solution 2 - without array: " + t.isValidateBST2());
		System.out.println("Solution 3 - min/max: " + t.isValidateBST3());
		
	}
}

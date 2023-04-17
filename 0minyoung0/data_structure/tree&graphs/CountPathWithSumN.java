// 트리에서 주어진값을 합산으로가지는 경로의 개수찾기
// https://youtu.be/vJi7GrjbgeU
// 주어진 이진트리가 있다. 이 트리의 노드에는 정수 값이 저장되어있다. (음의 정수 포함)
// 연결된 노드의 합산이 특정 숫자가 되는 경로의 개수를 찾으시오.
// 경로의 시작과 끝점은 어디든 될 수 있지만 언제나 위에서 아래로만 합산이 되어야함.

package treeAndGraphs.countPathWithSumN;

import java.util.HashMap;

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
		this.root = makeBST(0, size - 1);
	}
	Node makeBST(int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1);
		node.right = makeBST(mid + 1, end);
		return node;
	}
	
//	// solution1 : 각 노드마다 하위 서브트리를 모두 탐색해서 계산하는 방법
//	// 시간 복잡도 : 트리의 balance가 잘 맞을때는 O(NlogN), 최악의 경우 O(N^2)
//	int countPathsWithSum(int targetSum) {
//		return countPathsWithSum(root, targetSum);
//	}
//	int countPathsWithSum(Node root, int targetSum) {
//		if (root == null) return 0;
//		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
//		int pathsOnLeft = countPathsWithSum(root.left, targetSum);
//		int pathsOnRight = countPathsWithSum(root.right, targetSum);
//		return pathsFromRoot + pathsOnLeft + pathsOnRight;
//	}
//	int countPathsWithSumFromNode(Node node, int targetSum, int currSum) {
//		if (node == null) return 0;
//		currSum += node.data;
//		int totalPaths = 0;
//		if (currSum == targetSum) {
//			totalPaths++;
//		}
//		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currSum);
//		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currSum);
//		return totalPaths;
//	}
	
//	// solution2 : 각 노드를 시작점으로 하는 연속 합을 리스트에 저장하는 방법
//	int countPathsWithSum(int targetSum) {
//		ArrayList<Integer> array = new ArrayList<>();
//		return countPathsWithSum(root, targetSum, array);
//	}
//	int countPathsWithSum(Node root, int targetSum, ArrayList<Integer> array) {
//		if (root == null) return 0;
//		int totalPaths = 0;
//		addValue(array, root.data);
//		totalPaths = countPaths(array, targetSum);
//		totalPaths += countPathsWithSum(root.left, targetSum, array);
//		totalPaths += countPathsWithSum(root.right, targetSum, array);
//		removeLast(array);
//		return totalPaths;
//	}
//	void addValue(ArrayList<Integer> array, int value) {
//		for (int i=0; i<array.size(); i++) {
//			array.set(i, array.get(i)+value);
//		}
//		array.add(value);
//	}
//	void removeLast(ArrayList<Integer> array) {
//		int value = array.remove(array.size() - 1);
//		for (int i=0; i<array.size(); i++) {
//			array.set(i, array.get(i) - value);
//		}
//	}
//	int countPaths(ArrayList<Integer> array, int targetSum) {
//		int totalPaths = 0;
//		for (int i=0; i<array.size(); i++) {
//			if (array.get(i) == targetSum) totalPaths++;
//		}
//		return totalPaths;
//	}
	
	// solution3 : 배열방의 값을 거리라고 생각해서 위치를 마킹하는 방법
	int countPathsWithSum(int targetSum) {
		HashMap<Integer, Integer> hashTable = new HashMap<>();
		hashTable.put(0, 1);
		return countPathsWithSum(root, targetSum, 0, hashTable);
	}
	int countPathsWithSum(Node node, int targetSum, int currSum, HashMap<Integer, Integer> hashTable) {
		if (node == null) return 0;
		
		currSum += node.data;
		int sum = currSum - targetSum;
		int totalPaths = hashTable.getOrDefault(sum, 0);
		incrementHashTable(hashTable, currSum, 1);
		totalPaths += countPathsWithSum(node.left, targetSum, currSum, hashTable);
		totalPaths += countPathsWithSum(node.right, targetSum, currSum, hashTable);
		incrementHashTable(hashTable, currSum, -1);
		return totalPaths;
	}
	void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int val) {
		int newCount = hashTable.getOrDefault(key, 0) + val;
		if (newCount == 0) {
			hashTable.remove(key);
		}else {
			hashTable.put(key, newCount);
		}
	}
}
public class CountPathWithSumN {
	public static void main(String[] args) {
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
		Tree tree = new Tree(10);
		System.out.println(tree.countPathsWithSum(3)); // 2
		System.out.println(tree.countPathsWithSum(5)); // 4
	}
}

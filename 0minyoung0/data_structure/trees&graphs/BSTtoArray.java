// 이진검색트리를 만드는 모든 배열 찾기
// https://youtu.be/r3iW552f-kk
// 배열의 값을 왼쪽부터 하나씩 넣으면서 이진검색트리를 만들었다.
// 주어진 이진검색트리를 만들 수 있는 모든 배열을 찾는 알고리즘을 구현하시오.
// (단, 트리에 중복 값 없음)

package treeAndGraphs.binarySearchTreeToArray;

import java.util.*;

class Tree {
	static class Node {
		int data;
		Node left;
		Node right;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	Tree (int size) {
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
}

public class BSTtoArray {
	public static void main(String[] args) {
		Tree t = new Tree(5);
		ArrayList<LinkedList<Integer>> result = allSequences(t.root);
		for (LinkedList<Integer> l : result) {
			for (int data : l) {
				System.out.print(data);
			}
			System.out.println();
		}
	}
	
	static ArrayList<LinkedList<Integer>> allSequences(Tree.Node node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		}
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);
		
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
		
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}
	static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, 
					ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		if (first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = new LinkedList<Integer>();
			for (int data : prefix) result.add(data);
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}
		
		int headFirst = first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);
		
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		second.addFirst(headSecond);
	}
}

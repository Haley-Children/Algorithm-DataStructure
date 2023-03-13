// 이진트리에서 랜덤노드가져오기
// https://youtu.be/fZP-MFKxdr8
// 이진검색 트리에서 임의의 노드를 가져오는 알고리즘을 구현하시오
// 단, 모든 노드에 동일한 확률로 임의의 노드를 가져와야하며
// 필요한 경우 노드에 추가 정보를 저장해도 됨

package treeAndGraphs.getRandomNode;

import java.util.Random;

class Tree{
	class Node{
		int data;
		Node left;
		Node right;
		int size = 0;
		Node (int data){
			this.data = data;
			this.size = 1;
		}
		void insert(int data) {
			if (data <= this.data) {
				if (left == null) {
					left = new Node(data);
				}else {
					left.insert(data);
				}
			}else {
				if (right == null) {
					right = new Node(data);
				}else {
					right.insert(data);
				}
			}
			size++;
		}
		int size() {return size;}
		Node find(int data) {
			if (data == this.data) {
				return this;
			}else if (data < this.data) {
				return left != null? left.find(data) : null;
			}else if (data > this.data) {
				return right != null? right.find(data) : null;
			}else {
				return null;
			}
		}
		Node getIthNode(int i) {
			int leftSize = left == null? 0 : left.size();
			if (i < leftSize) {
				return left.getIthNode(i);
			}else if (i == leftSize) {
				return this;
			}else {
				return right.getIthNode(i - (leftSize + 1));
			}
		}
	}
	
	Node root;
	
	int size() {
		return root == null? 0 : root.size();
	}
	
	void insert(int data) {
		if (root == null) root = new Node(data);
		else root.insert(data);
	}
	Node getRandomNode() {
		if (root == null) return null;
		Random random = new Random();
		int i = random.nextInt(size());
		return root.getIthNode(i);
	}
}

public class GetRandomNode {
	public static void main(String[] args) {
/*
 *     (4)
 *     / \
 *    /   \
 *   /     \
 * (0)     (5)
 *   \       \
 *   (1)     (7)
 *     \     / \
 *     (2) (6) (8)
 *       \       \
 *       (3)     (9)
 */
		Tree t = new Tree();
		t.insert(4);
		t.insert(0);
		t.insert(1);
		t.insert(2);
		t.insert(5);
		t.insert(7);
		t.insert(8);
		t.insert(3);
		t.insert(6);
		t.insert(9);
		System.out.println(t.getRandomNode().data);
	}
}

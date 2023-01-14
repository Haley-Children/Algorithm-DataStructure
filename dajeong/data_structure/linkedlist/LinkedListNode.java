package linkedlist;

/*
 * 단방향 링크드리스트 (Singly LinkedList) 간단히 java로 구현해보기
 */

class LinkedList {

	Node header;

	LinkedList() {
		header = new Node();
	}

	public Node getHeader() {
		return header;
	}

	static class Node {

		int data;
		Node next = null;
	}

	// 시간 복잡도: O(N)
	void append(int d) {
		Node end = new Node();
		end.data = d;
		Node n = header;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

	// 시간 복잡도: O(N)
	void delete(int d) {
		Node n = header;
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
			} else {
				n = n.next;
			}
		}
	}

	// 시간 복잡도: O(N)
	void retrieve() {
		Node n = header.next;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}

	// k번째 노드 반환
	Node get(int k) {
		Node n = header;
		for (int i = 1; i <= k; i++) {
			n = n.next;
		}
		return n;
	}

	// 공간복잡도: O(1) 시간복잡도: O(N^2)
	void removeDups() {
		System.out.println("RemoveDups - LinkedListA class에서 구현");
	}

	boolean deleteNode(Node n) {
		System.out.println("DeleteNode - LinkedListC class에서 구현");
		return true;
	}

	// 공간 복잡도: O(N) 시간 복잡도: O(2N) -> O(N)
	Node kthNodeFromLastRecur(Node first, int k, Reference r) {
		System.out.println("KthFromLast - LinkedListB class에서 구현. (재귀호출 이용, Node 반환을 위해 Reference 전달)");
		return null;
	}

	// 공간 복잡도: O(1), 시간 복잡도: O(N)
	public Node kthNodeToLastPointer(Node first, int k) {
		System.out.println("KthFromLast - LinkedListB class에서 구현. (별도의 버퍼 공간 사용하지 않는 풀이)");
		return null;
	}

	Node kthFromLast(Node first, int k) {
		System.out.println("KthFromLast - LinkedListB class에서 구현. (쉬운 풀이)");
		return null;
	}

	int kthFromLastRecur(Node first, int k) {
		System.out.println("KthFromLast - LinkedListB class에서 구현. (재귀호출 이용. 추천 풀이)");
		return 0;
	}

	Node partition(Node n, int x) {
		System.out.println("Partition - LinkedListD class에서 구현. (재귀호출 이용. 추천 풀이)");
		return null;
	}



}

// 기본 동작 확인
public class LinkedListNode {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		ll.delete(2);
		ll.retrieve();
	}
}

/* 결과
 * 1 -> 2 -> 3 -> 4
 * 1 -> 3 -> 4
 */

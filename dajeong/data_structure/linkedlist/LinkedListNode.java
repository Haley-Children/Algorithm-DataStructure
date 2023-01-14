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

	void append(int d) {
		Node end = new Node();
		end.data = d;
		Node n = header;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

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

	void retrieve() {
		Node n = header.next;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}

	void removeDups() {
		System.out.println("LinkedListA class 에서 구현");
	}

	Node kthFromLast(Node first, int k) {
		System.out.println("LinkedListB class 에서 구현. (쉬운 풀이)");
		return null;
	}

	int kthFromLastRecur(Node first, int k) {
		System.out.println("LinkedListB class 에서 구현. (재귀호출 이용. 추천 풀이)");
		return 0;
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

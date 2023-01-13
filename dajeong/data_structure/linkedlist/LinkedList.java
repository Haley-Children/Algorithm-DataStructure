/*
 * 단방향 링크드리스트 (Singly LinkedList) 간단히 java로 구현해보기
 */

class Node {

	int data;
	Node next = null;

	Node(int d) {
		this.data = d;
	}
}

	void append(int d) {
		Node end = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}

	void delete(int d) {
		Node n = this;
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
			} else {
				n = n.next;
			}
		}
	}

	void retrieve() {
		Node n = this;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}

public class SinglyLinkedList {

	public static void main(String[] args) {
		Node head = new Node(1);
		head.append(2);
		head.append(3);
		head.append(4);
		head.retrieve();
		head.delete(2);
		head.retrieve();
	}

	/* 결과
	 * 1 -> 2 -> 3 -> 4
	 * 1 -> 3 -> 4
	 */
}

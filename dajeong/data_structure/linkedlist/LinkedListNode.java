package linkedlist;

/*
 * 단방향 링크드리스트 (Singly LinkedList) 간단히 java로 구현해보기
 */

class LinkedList {

	Node header;

	LinkedList() {
		header = new Node();
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

	// 중복값 삭제 메서드
	void removeDups() {
		Node n = header;

		// 마지막 node가 중복된 LinkedList일 경우 nullpointException이 발생하게 되므로 n!=null 조건 추가
			// 마지막 node 삭제하고 null로 가리키게 된다면, n = n.next 실행 후 다시 while문으로 들어올 때 nullpointException이 발생하게 됨
		while (n != null && n.next != null) {
			Node r = n;
			while (r.next != null) {
				if (n.data == r.next.data) {
					r.next = r.next.next;
				} else {
					r = r.next;
				}
			}
			n = n.next;
		}
	}

	static class Node {

		int data;
		Node next = null;
	}

}

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

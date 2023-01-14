// Node class를 감싸는 LinkedList 클래스 구현
// https://www.youtube.com/watch?v=IrXYr7T8u_s
// header가 첫번째 값이면서 동시에 linked list의 대표이기 때문에 발생하는 문제를 방지
// linked list의 header를 linked list의 시작을 알리는 용도로만 사용
class LinkedList {
	Node header;
	
	static class Node {
		int data;
		Node next = null;
	}
	
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
}

public class LinkedListNode {
	public static void main (String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve(); // 1 -> 2 -> 3 -> 4
		ll.delete(1);
		ll.retrieve(); // 2 -> 3 -> 4
	}
}

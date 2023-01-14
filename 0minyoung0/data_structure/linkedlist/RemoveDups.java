// 정렬되어 있지 않은 linked list의 중복되는 값을 제거
// https://www.youtube.com/watch?v=Ce4baygLMz0
// LinkedList 클래스안에 removeDups() 내부함수로 선언
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
	
	void removeDups() {
		Node n = header;
		while (n != null && n.next != null) { // 마지막 두 노드가 중복인 경우를 고려하여 n != null 조건이 필요함
			Node r = n; // 러너의 시작 위치
			while (r.next != null) { // 러너는 마지막에서 두 번째 노드까지 감
				if (n.data == r.next.data) {
					r.next = r.next.next;
				} else {
					r = r.next;
				}
			}
			n = n.next;
		}
	}
}

public class RemoveDups {
	public static void main (String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(2);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(4);
		ll.append(2);
		ll.append(2);
		ll.retrieve(); // 2 -> 1 -> 2 -> 3 -> 4 -> 4 -> 2 -> 2
		ll.removeDups();
		ll.retrieve(); // 2 -> 1 -> 3 -> 4
	}
}

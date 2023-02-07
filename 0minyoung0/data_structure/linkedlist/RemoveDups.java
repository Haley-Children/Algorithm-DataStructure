// Linked List 중복값 삭제 in Java
// https://www.youtube.com/watch?v=Ce4baygLMz0
// 정렬되어 있지 않은 LinkedList의 중복되는 데이터를 제거하는 함수 (버퍼 사용 X)
class Node {
	int data;
	Node next = null;
}
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
	void retrieve() {
		Node n = header.next;
		while (n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
	void removeDups() { // 포인터 두 개를 사용하여 중복되는 노드 제거
		Node n = header;
		while (n != null && n.next != null) { // n이 가리키는 노드와 다음 노드가 null이 아닌 구간에서
			Node r = n; // n이 가리키는 노드부터 runner를 출발시킴
			while (r.next != null) { // r이 가리키는 다음 노드가 null이 아닌 구간에서
				if (n.data == r.next.data) { // n과 r의 다음 노드가 같다면
					r.next = r.next.next; // r의 다음 노드를 제거
				} else { // n과 r의 다음 노드가 다르다면
					r = r.next; // r을 다음 노드로 진행
				}
			}
			n = n.next; // r 포인터가 끝까지 도달했다면 n을 다음 노드로 진행
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

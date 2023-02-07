// 단방향 Linked List 중간노드 삭제 in Java
// https://www.youtube.com/watch?v=xI4iPEmkHlc
// 단방향 Linked List에서 중간에 있는 노드를 삭제하시오
// (단, 첫번째 노드가 어디 있는지 모르고, 오직 삭제할 노드만 갖고 있다)
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
	Node get(int k) {
		Node n = header;
		for (int i = 0; i < k; i++) {
			n = n.next;
		}
		return n;
	}
}

public class DeleteNode {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(5);
		ll.retrieve(); // 1 -> 2 -> 3 -> 4 -> 5
		deleteNode(ll.get(2));
		ll.retrieve(); // 1 -> 3 -> 4 -> 5
		deleteNode(ll.get(3));
		ll.retrieve(); // 1 -> 3 -> 5
	}
	// 삭제할 노드의 이전 노드에 접근할 수 없으므로 바로 삭제할 수 없다.
	// 삭제할 노드의 다음 노드를 삭제할 노드에 복사하고 삭제할 노드의 다음 노드를 제거하면 된다.
	// 이 경우 원래 삭제하려던 노드의 next 주소를 다다음 노드로 바꿔주면 된다.
	// 추가로 이 방법을 통해서는 헤더 노드와 마지막 노드를 지울 수 없다.
	private static boolean deleteNode(Node n) {
		if (n == null || n.next == null) {
			return false;
		}
		Node next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}
}

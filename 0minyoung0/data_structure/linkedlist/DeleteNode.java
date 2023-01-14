// linked list의 중간에 있는 노드를 제거 (처음과 마지막은 X)
// https://www.youtube.com/watch?v=xI4iPEmkHlc
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
	// 지워야하는 해당 노드에서는 이전 노드에 방문할 수 없으므로
	// 지워야할 노드의 다음 노드의 data와 next를 지워야 할 노드에 복사하여 처리한다
	private static boolean deleteNode(LinkedList.Node n) {
		if (n == null || n.next == null) {
			return false; // 제거 실패
		}
		LinkedList.Node next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true; // 제거 성공
	}
}

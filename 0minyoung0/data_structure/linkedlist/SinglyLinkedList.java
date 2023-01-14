// 단방향 Linked List 구현 in Java
// https://www.youtube.com/watch?v=C1SDkdPvQPA
class Node {
	int data;
	Node next = null;
	
	Node(int d) { // 노드를 생성할 때 data
		this.data = d;
	}
	
	void append(int d) { // boolean 타입으로 성공, 실패 확인하는게 더 일반적
		Node end = new Node(d); // 추가할 노드
		Node n = this;
		while (n.next != null) { // n = 마지막 노드가 될때까지
			n = n.next;
		}
		n.next = end; // 마지막 노드의 next에 새로운 노드(end)
	}
	
	void delete(int d) { // 현재 구조에서는 첫 번째 노드를 지울 수 없음
		Node n = this; // 삭제할 값(d)을 찾기 위한 임의의 포인터(n)
		while (n.next != null) { // 마지막에서 두번째 노드까지 돌면서
			if (n.next.data == d) {
				n.next = n.next.next; // 값이 d인 노드 삭제
			} else {
				n = n.next;
			}
		}
	}
	
	void retrieve() { // linked list를 순회하면서 데이터를 출력
		Node n = this;
		while (n.next != null) { // 마지막에서 두번째 노드까지 돌면서
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data); // 마지막 노드 출력
	}
}

public class SinglyLinkedList {
	public static void main(String[] args) {
		Node head = new Node(1);
		head.append(2);
		head.append(3);
		head.append(4);
		head.retrieve(); // 1 -> 2 -> 3 -> 4
		head.delete(2);
		head.retrieve(); // 1 -> 3 -> 4
		head.delete(3);
		head.retrieve(); // 1 -> 4
		// head.delete(1);은 작동하지 않음 
	}
}
// Linked List 값에 따라 나누기 in Java
// https://www.youtube.com/watch?v=xufv1LUy42E
// LinkedList에 있는 노드들을 입력 받은 값을 기준으로
// 작은 노드는 왼쪽으로, 크거나 같은 노드는 오른쪽으로 나누는 알고리즘을 설명하시오.
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
public class Partition {
	public static void main(String[] args) {
		LinkedList ll1 = new LinkedList();
		ll1.append(7);
		ll1.append(2);
		ll1.append(8);
		ll1.append(5);
		ll1.append(3);
		ll1.append(4);
		ll1.retrieve(); // 7 -> 2 -> 8 -> 5 -> 3 -> 4
		
		Node n1 = Partition1(ll1.get(1), 5);
		while (n1.next != null) {
			System.out.print(n1.data + " -> ");
			n1 = n1.next;
		}
		System.out.println(n1.data); // 2 -> 3 -> 4 -> 7 -> 8 -> 5
		
		LinkedList ll2 = new LinkedList();
		ll2.append(7);
		ll2.append(2);
		ll2.append(8);
		ll2.append(5);
		ll2.append(3);
		ll2.append(4);
		ll2.retrieve(); // 7 -> 2 -> 8 -> 5 -> 3 -> 4
		
		Node n2 = Partition2(ll2.get(1), 5);
		while (n2.next != null) {
			System.out.print(n2.data + " -> ");
			n2 = n2.next;
		}
		System.out.println(n2.data); // 4 -> 3 -> 2 -> 7 -> 8 -> 5
	}
	// solution1 : 포인터 4개를 사용한 방법
	// s1과 e1은 왼쪽 리스트, s2와 e2는 오른쪽 리스트의 처음과 끝을 나타낸다.
	private static Node Partition1(Node n, int x){
		Node s1 = null;
		Node e1 = null;
		Node s2 = null;
		Node e2 = null;
		
		while (n != null) {
			Node next = n.next;
			n.next = null;
			if (n.data < x) {
				if (s1 == null) {
					s1 = n;
					e1 = s1;
				}else {
					e1.next = n;
					e1 = n;
				}
			}else {
				if (s2 == null) {
					s2 = n;
					e2 = s2;
				}else {
					e2.next = n;
					e2 = n;
				}
			}
			n = next;
		}
		if (s1 == null) {
			return s2;
		}
		e1.next = s2;
		return s1;
	}
	// solution2 :  빈 리스트의 앞에 작은 값을 추가하면서 head 포인터를
	// 				큰값을 뒤에 추가하면서 tail 포인터를 위치시킨다.
	private static Node Partition2(Node n, int x){
		Node head = n;
		Node tail = n;
		
		while (n != null) {
			Node next = n.next;
			if (n.data < x) {
				n.next = head;
				head = n;
			}else {
				tail.next = n;
				tail = n;
			}
			n = next;
		}
		tail.next = null;
		
		return head;
	}
}

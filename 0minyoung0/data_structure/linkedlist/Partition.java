// linked list를 특정 값에 따라 나누어 배치하기
// https://www.youtube.com/watch?v=xufv1LUy42E
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
public class Partition {
	public static void main(String[] args) {
		LinkedList ll1 = new LinkedList();
		ll1.append(7);
		ll1.append(2);
		ll1.append(8);
		ll1.append(5);
		ll1.append(3);
		ll1.append(4);
		ll1.retrieve();
		
		LinkedList.Node n1 = Partition1(ll1.get(1), 5);
		while (n1.next != null) {
			System.out.print(n1.data + " -> ");
			n1 = n1.next;
		}
		System.out.println(n1.data);
		
		LinkedList ll2 = new LinkedList();
		ll2.append(7);
		ll2.append(2);
		ll2.append(8);
		ll2.append(5);
		ll2.append(3);
		ll2.append(4);
		ll2.retrieve();
		
		LinkedList.Node n2 = Partition2(ll2.get(1), 5);
		while (n2.next != null) {
			System.out.print(n2.data + " -> ");
			n2 = n2.next;
		}
		System.out.println(n2.data);
	}
	// solution1 : 왼쪽, 오른쪽의 시작과 끝을 가리키는 포인터 2개씩 총 4개를 사용하는 방법
	private static LinkedList.Node Partition1(LinkedList.Node n, int x){
		LinkedList.Node s1 = null;
		LinkedList.Node e1 = null;
		LinkedList.Node s2 = null;
		LinkedList.Node e2 = null;
		
		while (n != null) {
			LinkedList.Node next = n.next;
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
	// solution2 : head 앞과 tail 뒤에 노드를 붙이며 포인터를 2개 사용하는 방법
	private static LinkedList.Node Partition2(LinkedList.Node n, int x){
		LinkedList.Node head = n;
		LinkedList.Node tail = n;
		
		while (n != null) {
			LinkedList.Node next = n.next;
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

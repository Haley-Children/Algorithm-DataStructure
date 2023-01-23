// Linked List Digit 합산 알고리즘 in Java
// https://youtu.be/vuJk2JZd3fI
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
		for (int i = 0; i < k; i++) n = n.next;
		return n;
	}
}
// solution을 위한 객체
class Storage{
	int carry = 0;
	Node result = null;
}

public class SumLists {
	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append(9);
		l1.append(1);
		l1.append(4);
		l1.retrieve(); // 9 -> 1 -> 4
		
		LinkedList l2 = new LinkedList();
		l2.append(6);
		l2.append(4);
		l2.append(3);
		l2.retrieve(); // 6 -> 4 -> 3
		
		Node n1 = sumLists1(l1.get(1), l2.get(1), 0);
		while (n1.next != null) {
			System.out.print(n1.data + " -> ");
			n1 = n1.next;
		}
		System.out.println(n1.data); // 5 -> 6 -> 7
		
		LinkedList l3 = new LinkedList();
		l3.append(9);
		l3.append(1);
		l3.retrieve(); // 9 -> 1
		
		LinkedList l4 = new LinkedList();
		l4.append(1);
		l4.append(1);
		l4.retrieve(); // 1 -> 1
		
		Node n2 = sumLists2(l3.get(1), l4.get(1));
		while (n2.next != null) {
			System.out.print(n2.data + " -> ");
			n2 = n2.next;
		}
		System.out.println(n2.data); // 1 -> 0 -> 2
	}
	// solution
	private static Node sumLists1(Node l1, Node l2, int carry){
		if (l1 == null && l2 == null && carry == 0) return null;
		Node result = new Node();
		int value = carry;
		
		if (l1 != null) value += l1.data;
		if (l2 != null) value += l2.data;
		result.data = value % 10;
		
		if (l1 != null || l2 != null) {
			Node next = sumLists1(l1 == null? null : l1.next,
											 l2 == null? null : l2.next,
											 value >= 10? 1 : 0);
			result.next = next;
		}
		return result;
	}
	// 심화 solution
	private static Node sumLists2(Node l1, Node l2){
		int len1 = getListLength(l1);
		int len2 = getListLength(l2);
		
		if (len1 < len2) l1 = LPadList(l1, len2 - len1);
		else l2 = LPadList(l2, len1 - len2);
		
		Storage storage = addLists(l1, l2);
		if (storage.carry != 0) storage.result = insertBefore(storage.result, storage.carry);
		return storage.result;
	}
	private static Storage addLists(Node l1, Node l2) {
		if (l1 == null && l2 == null) { 
			Storage storage = new Storage();
			return storage;
		}
		Storage storage = addLists(l1.next, l2.next);
		int value = storage.carry + l1.data + l2.data; 
		int data = value % 10; 
		storage.result = insertBefore(storage.result, data); 
		storage.carry = value / 10; 
		return storage;
	}
	private static int getListLength(Node l) {
		int total = 0;
		while (l != null) {
			total++;
			l = l.next;
		}
		return total;
	}
	private static Node insertBefore(Node node, int data){
		Node before = new Node();
		before.data = data;
		if (node != null) before.next = node;
		return before;
	}
	private static Node LPadList(Node l, int length){
		Node head = l;
		for (int i = 0; i < length; i++) head = insertBefore(head, 0);
		return head;
	}
}
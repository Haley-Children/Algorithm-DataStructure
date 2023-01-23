// Linked List 교차점찾기 in Java
// https://youtu.be/dk4oFGJx3ps
import java.io.IOException;

class Node {
	int data;
	Node next = null;
	Node(int d) {
		this.data = d;
	}
	Node addNext(int d) {
		Node nx = new Node(d);
		Node n = this;
		n.next = nx;
		return nx;
	}
	Node addNext(Node nx) {
		Node n = this;
		n.next = nx;
		return nx;
	}
	void print() {
		Node n = this;
		while(n.next != null) {
			System.out.print(n.data + " -> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
	Node get(int a) {
		Node n = this;
		while (a != 0) {
			n = n.next;
			a--;
		}
		return n;
	}
}

public class GetIntersection {
	public static void main (String[] args) throws IOException {
		Node n1 = new Node(5);
		Node n2 = n1.addNext(7);
		Node n3 = n2.addNext(9);
		Node n4 = n3.addNext(10);
		Node n5 = n4.addNext(7);
		Node n6 = n5.addNext(6);
		
		Node m1 = new Node(6);
		Node m2 = m1.addNext(8);
		Node m3 = m2.addNext(n4);
		
		n1.print();
		m1.print();
		
		Node n = getIntersection(n1, m1);
		
		if(n!=null) System.out.println("Intersection: " + n.data);
		else System.out.println("Not found");
		
		Node l1 = new Node(1);
		Node l2 = l1.addNext(2);
		Node l3 = l2.addNext(3);
		Node l4 = l3.addNext(4);
		Node l5 = l4.addNext(5);
		Node l6 = l5.addNext(6);
		
		n1.print();
		l1.print();
		Node m = getIntersection(n1, l1);
		
		if(m!=null) System.out.println("Intersection: " + m.data);
		else System.out.println("Not found");
	}
	
	private static Node getIntersection(Node l1, Node l2) {
		int len1 = getListLength(l1);
		int len2 = getListLength(l2);
		if (len1 > len2) l1 = l1.get(len1 - len2);
		else l2 = l2.get(len2 - len1);
		while (l1 != null && l2 != null) {
			if (l1 == l2) return l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		return null;
	}
	
	private static int getListLength(Node l1) {
		int length = 1;
		while (l1.next != null) {
			length++;
			l1 = l1.next;
		}
		return length;
	}
}
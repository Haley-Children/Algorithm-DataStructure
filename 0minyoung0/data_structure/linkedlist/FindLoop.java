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
public class FindLoop {
	public static void main (String[] args) throws IOException {
		Node n1 = new Node(1);
		Node n2 = n1.addNext(2);
		Node n3 = n2.addNext(3);
		Node n4 = n3.addNext(4);
		Node n5 = n4.addNext(5);
		Node n6 = n5.addNext(6);
		Node n7 = n6.addNext(7);
		Node n8 = n7.addNext(8);
		
		Node n = findLoop(n1);
		if (n!=null) System.out.println("Start of loop: " + n.data);
		else System.out.println("Loop not found");
		
		n8.addNext(n4);
		
		n = findLoop(n1);
		if (n!=null) System.out.println("Start of loop: " + n.data);
		else System.out.println("Loop not found");
	}
	private static Node findLoop (Node head) {
		Node fast = head;
		Node slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast==slow) break;
		}
		if (fast == null || fast.next == null) return null;
		slow = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
}
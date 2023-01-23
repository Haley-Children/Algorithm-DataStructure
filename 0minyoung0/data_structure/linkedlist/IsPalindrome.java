// Linked List 회문찾기(Palindrome) in Java
// https://youtu.be/34zpNdrgnpA
import java.util.Stack;

class Node {
	char data;
	Node next = null;
}
class LinkedList {
	Node header;
	LinkedList() {
		header = new Node();
	}
	void append(char d) {
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
// solution3을 위한 객체
class Storage{
	public Node node;
	public boolean result;
	
	Storage (Node n, boolean r){
		node = n;
		result = r;
	}
}
public class IsPalindrome {
	public static void main (String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append('m');
		l1.append('a');
		l1.append('d');
		l1.append('a');
		l1.append('m');
		l1.retrieve(); // m -> a -> d -> a -> m
		System.out.println(isPalindrome1(l1.get(1))); // true
		System.out.println(isPalindrome2(l1.get(1))); // true
		System.out.println(isPalindrome3(l1.get(1))); // true
		l1.append('m');
		l1.retrieve(); // m -> a -> d -> a -> m -> m
		System.out.println(isPalindrome1(l1.get(1))); // false
		System.out.println(isPalindrome2(l1.get(1))); // false
		System.out.println(isPalindrome3(l1.get(1))); // false
	}
	// solution1
	private static boolean isPalindrome1(Node head) {
		Node reversed = reverseAndClone(head);
		return isEqual(head, reversed);
	}
	private static Node reverseAndClone(Node node){
		Node head = null;
		while (node != null) { 
			Node n = new Node();
			n.data = node.data;
			n.next = head;
			head = n; 
			node = node.next; 
		}
		return head;
	}
	private static boolean isEqual(Node one, Node two) {
		while (one != null && two != null) {
			if (one.data != two.data) return false;
			one = one.next;
			two = two.next;
		}
		return one == null && two == null;
	}
	// solution2 
	private static boolean isPalindrome2(Node head) {
		Node fast = head;
		Node slow = head;
		Stack<Character> stack = new Stack<Character>();
		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) slow = slow.next;
		while (slow != null) {
			char c = stack.pop();
			if (slow.data != c) return false;
			slow = slow.next;
		}
		return true;
	}
	// solution3
	private static boolean isPalindrome3(Node head) {
		int length = getListLength(head);
		Storage storage = isPalindromeRecursive(head, length);
		return storage.result;
	}
	private static int getListLength(Node l) {
		int total = 0;
		while (l != null) {
			total++;
			l = l.next;
		}
		return total;
	}
	private static Storage isPalindromeRecursive(Node head, int length) {
		if (head == null || length <= 0) return new Storage(head, true); 
		else if (length == 1) return new Storage(head.next, true);
		
		Storage storage = isPalindromeRecursive(head.next, length - 2);
		
		if (!storage.result || storage.node == null) return storage; 
		storage.result = (head.data == storage.node.data);
		storage.node = storage.node.next; 
		return storage;
	}
}
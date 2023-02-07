// Linked List 회문찾기(Palindrome) in Java
// https://youtu.be/34zpNdrgnpA
// Linked List의 노드들이 회문(Palindrome)인지 확인하는 코드를 구현하시오.
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
	// solution1 : 거꾸로 정렬한 리스트
	// 거꾸로 정렬된 새 리스트를 만들어서 기존의 리스트와 처음부터 비교한다.
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
	// solution2 : 포인터 2개 이용
	// 빠른 포인터 f는 2노드씩 느린 포인터 s는 1노드씩 이동하며 s포인터가 스택에 노드의 데이터를 담는다.
	// f가 마지막 노드나 null에 도착하면 s포인터를 이어서 이동시키며 스택에서 꺼낸 값과 비교한다.
	// (f가 마지막 노드에 도달한 경우 전체 리스트의 길이가 홀수이므로 이를 고려해서 처리한다.)
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
	// solution3 : 재귀호출 이용
	// 중간까지만 이동하며 재귀를 멈추기 위해 인자를 2씩 감소시키며 넘겨준다.
	// 회귀하여 돌아가며 함수를 호출할 때 받았던 값과 중간에서 반환해준 다음 값을 비교한다.
	// 다음 노드의 주소와 결과를 포함하는 객체를 반환시키며 전달한다.
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
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
// solution3에서 사용할 노드와 결과를 보관할 객체
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
	// solution1 : LinkedList를 거꾸로 정렬하여 원본과 비교하는 방식
	private static boolean isPalindrome1(Node head) {
		Node reversed = reverseAndClone(head); // 거꾸로 정렬하는 함수로 reversed 노드를 선언
		return isEqual(head, reversed); // 두 LinkedList가 같은지 확인하는 함수의 결과를 리턴
	}
	private static Node reverseAndClone(Node node){
		Node head = null; // 반환할 새로운 노드 생성
		while (node != null) { // 원본 리스트가 끝날때까지
			Node n = new Node();
			n.data = node.data; // 원본 데이터를 받아서
			n.next = head; // 헤드 노드의 앞에 붙이기
			head = n; // 그리고 헤드 포인터를 추가된 노드로 변경
			node = node.next; // 다음 노드로 이동
		}
		return head; // 거꾸로 정렬된 리스트를 반환
	}
	private static boolean isEqual(Node one, Node two) {
		while (one != null && two != null) {
			if (one.data != two.data) return false;
			one = one.next;
			two = two.next;
		}
		return one == null && two == null; // 두 Linked List가 동시에 끝난 경우에만 true이므로 길이 확인하여 리턴
	}
	// solution2 : 두 개의 포인터 '토끼'와 '거북이'를 사용하는 방식
	// 토끼는 한번에 두칸, 거북이는 한번에 한칸씩 진행하여 토끼가 리스트에 끝에 도달하면 거북이가 리스트의 중간에 있게 됨
	// 거북이는 스택에 데이터를 보관하며 진행하고 중간부터는 스택에서 데이터를 꺼내며 비교
	// 리스트 전체 길이 홀/짝 고려 필요
	private static boolean isPalindrome2(Node head) {
		Node fast = head;
		Node slow = head;
		Stack<Character> stack = new Stack<Character>();
		while (fast != null && fast.next != null) {
			stack.push(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) slow = slow.next; // 리스트의 길이가 홀수인 경우 slow 포인터를 1 증가시킴 (가운데 노드 무시)
		while (slow != null) {
			char c = stack.pop();
			if (slow.data != c) return false;
			slow = slow.next;
		}
		return true;
	}
	// solution3 : 재귀호출을 이용하는 방법
	private static boolean isPalindrome3(Node head) {
		int length = getListLength(head);
		Storage storage = isPalindromeRecursive(head, length);
		return storage.result;
	}
	// Linked List의 길이를 반환하는 함수
	private static int getListLength(Node l) {
		int total = 0;
		while (l != null) {
			total++;
			l = l.next;
		}
		return total;
	}
	// Linked List의 길이와 노드 주소를 인자로 받아서 storage 객체의 주소를 반환
	private static Storage isPalindromeRecursive(Node head, int length) {
		if (head == null || length <= 0) return new Storage(head, true); // 짝수개일때
		else if (length == 1) return new Storage(head.next, true); // 홀수개일때
		
		Storage storage = isPalindromeRecursive(head.next, length - 2);
		
		if (!storage.result || storage.node == null) return storage; // result가 하나라도 false이면 더 비교할 필요가 없으므로 반환
		storage.result = (head.data == storage.node.data);
		storage.node = storage.node.next; // storage 노드는 정방향으로 계속 진행
		return storage;
	}
}
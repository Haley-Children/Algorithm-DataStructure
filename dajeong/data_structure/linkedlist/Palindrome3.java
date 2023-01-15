package linkedlist;

import linkedlist.LinkedList.Node;

class Storage2 {
	public Node node;
	public boolean result;

	public Storage2(Node node, boolean result) {
		this.node = node;
		this.result = result;
	}
}

public class Palindrome3 {

	// 방법 3. 재귀호출 이용 - 링크드리스트 길이의 반만큼만 재귀호출을 한다.
	// -> 비교할 노드의 주소값과 비교 결과를 재귀호출 반환시 함께 전달하기 위해 Storage2 객체 사용.
	private static boolean isPalindrome(Node head) {
		int length = getListLength(head);
		Storage2 storage = isPalindromeRecursive(head, length);
		return storage.result;
	}

	// 노드 위치와 길이를 파라미터로 받아서 스토리지2 객체에 주소와 비교 결과 반환하는 재귀함수
	private static Storage2 isPalindromeRecursive(Node head, int length) {
		if (head == null || length <=0) { // 짝수 개 일 때
			return new Storage2(head, true);
		} else if (length == 1) { // 홀수 개 일 때
			return new Storage2(head.next, true);
		}

		Storage2 storage = isPalindromeRecursive(head.next, length - 2);

		if (!storage.result || storage.node == null) {
			return storage;
		}

		storage.result = (head.data == storage.node.data);
		storage.node = storage.node.next;

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

	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append('m');
		l1.append('a');
		l1.append('d');
		l1.append('a');
		l1.append('m');
		l1.retrieve();

		System.out.println(isPalindrome(l1.get(1)));
	}
}

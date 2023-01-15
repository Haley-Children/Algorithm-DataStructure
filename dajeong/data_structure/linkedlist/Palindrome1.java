package linkedlist;

/* LinkedList Algorithm 6 - 1
 *
 * 링크드리스트 노드들이 회문(Palindrome)인지 확인하는 코드를 구현하시오.
 *
 */

import linkedlist.LinkedList.Node;

public class Palindrome1 {

	// 방법 1. 거꾸로 정렬해서 비교하기 O(N)
	private static boolean isPalindrome(Node head) {
		Node reversed = reverseAndClone(head);
		return isEqual(head, reversed);
	}

	private static Node reverseAndClone(Node node) {
		Node head = null;
		while (node != null) {
			Node n = new Node(node.data);
			n.next = head;
			head = n;
			node = n.next;
		}
		return head;
	}

	private static boolean isEqual(Node one, Node two) {
		while (one != null && two != null) {
			if (one.data != two.data) {
				return false;
			}
			one = one.next;
			two = two.next;
		}
		// 마지막으로 두 개의 리스트의 길이가 같은지(마지막 노드인지) 확인하고 return
		return one == null && two == null;
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

package linkedlist;

import java.util.Stack;
import linkedlist.LinkedList.Node;

public class Palindrome2 {

	// 방법 2. 투 포인터, 스택 이용
	// 공간복잡도: O(N), 시간복잡도: O(N)
	private static boolean isPalindrome(Node head) {
		Node fast = head;
		Node slow = head;

		Stack<Character> stack = new Stack<>();

		while (fast != null && fast.next != null) {
			stack.push((char) slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}

		// 홀수일 때 slow는 정중앙이므로 한칸 더 이동하여 스택값 비교
		if (fast != null) {
			slow = slow.next;
		}

		while (slow != null) {
			char c = stack.pop();

			if (slow.data != c) {
				return false;
			}
			slow = slow.next;
		}
		return true;
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

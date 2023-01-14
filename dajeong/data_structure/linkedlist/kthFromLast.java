package linkedlist;

/*
 * LinkedList Algorithm 2
 * 단방향 LinkedList의 "뒤에서" k번째 "노드"를 찾는 알고리즘을 구현하시오
 *
 * 방법 1)
 * LinkedList를 처음부터 순회하여 전체 길이(len)를 구하게 된다면, k=n일 때 앞에서 (len - n + 1)번째 숫자임을 알 수 있음
 */

import linkedlist.LinkedList.Node;

class LinkedListB extends LinkedList {

	// 쉬운 풀이
	public Node kthFromLast(Node first, int k) {
		Node n = first;
		int total = 1;

		while (n.next != null) {
			total++;
			n = n.next;
		}
		n = first;
		for (int i = 1; i < total - k + 1; i++) {
			if (n != null) {
				n = n.next;
			}
		}
		return n;
	}

	// 재귀 호출 이용 (추천 풀이)
	// -> 재귀로 마지막 노드 바로 뒤까지 (null) 호출한 후, count++ 해나가며 k와 비교
	public int kthFromLastRecur(Node first, int k) {
		Node n = first;
		// base condition
		if (n == null) {
			return 0;
		}

		int count = kthFromLastRecur(n.next, k) + 1;
		if (count == k) {
			System.out.println(k + "th to last node is " + n.data);
		}
		return count;
	}
}


public class kthFromLast {

	public static void main(String[] args) {
		LinkedList ll = new LinkedListB();
		ll.append(1);
		ll.append(2);
		ll.append(2);
		ll.append(5);
		ll.append(4);
		ll.append(1);
		Node first = ll.getHeader();

		int k = 5;

		/* 첫번 째 풀이
		 * Node kth = ll.kthFromLast(first, k);
		 * System.out.println("Last k(" + k + ")th data is " + kth.data);
		 */

		ll.retrieve();
		// 재귀 이용 풀이
		ll.kthFromLastRecur(first, k);
	}
}

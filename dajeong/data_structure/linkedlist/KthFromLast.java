package linkedlist;

/*
 * LinkedList Algorithm 2
 * 단방향 LinkedList의 "뒤에서" k번째 "노드"를 찾는 알고리즘을 구현하시오
 *
 * 방법 1)
 * LinkedList를 처음부터 순회하여 전체 길이(len)를 구하게 된다면, k=n일 때 앞에서 (len - n + 1)번째 숫자임을 알 수 있음
 *
 * 방법 2)
 * 재귀 호출  - 공간 복잡도(스택 이용): O(N), 시간 복잡도: O(N)
 *
 * 방법 3)
 * 투 포인터 이용 - 공간 복잡도: O(1), 시간 복잡도: O(N)
 */

import linkedlist.LinkedList.Node;

class Reference {

	public int count = 0;
}


public class KthFromLast {
	// 쉬운 풀이
	private static Node kthFromLast(Node first, int k) {
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

	// 재귀 호출 이용
	// -> 재귀로 마지막 노드 바로 뒤까지 (null) 호출한 후, count++ 해나가며 k와 비교
	private static int kthFromLastRecur(Node first, int k) {
		// base condition
		if (first == null) {
			return 0;
		}

		int count = kthFromLastRecur(first.next, k) + 1;
		if (count == k) {
			System.out.println(k + "th to last node is " + first.data);
		}
		return count;
	}


	// java에서는 하나만 반환할 수 있다.
	// c++는 pass by reference 방식이므로 자동으로 객체의 주소를 알 수 있는데 java는 그렇지 않으므로 Node를 반환하기 위해 Reference (객체의 주소)를 직접 전달해야 함.
	// -> 주소 전달을 위해 count(포인터 역할)를 전달하여 구현함.

	// 공간 복잡도: O(N) 시간 복잡도: O(2N) -> O(N)
	private static Node kthNodeFromLastRecur(Node first, int k, Reference r) {
		// base condition
		if (first == null) {
			return null;
		}

		Node found = kthNodeFromLastRecur(first.next, k, r);
		r.count++;

		if (r.count == k) {
			return first;
		}
		return found;
	}

	// 포인터 2개 이용! (아이디어가 신기하다)
	// 별도의 버퍼를 사용하지 않음
	// 공간 복잡도: O(1), 시간 복잡도: O(N)
	private static Node kthNodeToLastPointer(Node first, int k) {
		Node p1 = first;
		Node p2 = first;

		for (int i = 0; i < k; i++) {
			if (p1 == null) return null; // 뒤에서부터 k번째가 없기 때문에 종료
			p1 = p1.next;
		}

		// 두 개의 포인터를 동시에 움직임
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(2);
		ll.append(5);
		ll.append(4);
		ll.append(1);
		Node first = ll.getHeader();

		int k = 2;

		/* 완전 탐색 풀이
		 * Node kth = kthFromLast(first, k);
		 * ll.retrieve();
		 * System.out.println("Last k(" + k + ")th data is " + kth.data);
		 */

		/* 재귀 이용 풀이
		 * ll.retrieve();
		 * kthFromLastRecur(first, k);
		 */

		/* 재귀 이용, Node 반환 풀이
		Reference r = new Reference();
		ll.retrieve();
		Node found = kthNodeFromLastRecur(first, k, r);
		System.out.println(found.data);

		 */

		// 포인터 이용 풀이
		ll.retrieve();
		Node found = kthNodeToLastPointer(first, k);
		System.out.println(found.data);
	}
}

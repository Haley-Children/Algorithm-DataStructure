package linkedlist;

/*
 * LinkedList Algorithm 4
 * LinkedList에 있는 노드들을 x값을 기준으로 값이 작은 것들은 왼쪽, 큰 것들은 오른쪽 - 두 파트로 나누시오
 * 단, x는 왼/오 파트 어디에 놔도 상관 없음.
 */

import linkedlist.LinkedList.Node;

public class Partition {

	private static Node partition(Node n, int x) {
		Node s1 = null;
		Node e1 = null;
		Node s2 = null;
		Node e2 = null;

		while (n != null) {
			Node next = n.next;
			n.next = null;
			if (n.data < x) {
				if (s1 == null) {
					s1 = n;
					e1 = s1;
				} else {
					e1.next = n;
					e1 = n;
				}
			} else {
				if (s2 == null) {
					s2 = n;
					e2 = s2;
				} else {
					e2.next = n;
					e2 = n;
				}
			}
			n = next;
		}
		if (s1 == null) {
			return s2;
		}
		e1.next = s2;
		return s1;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(7);
		ll.append(2);
		ll.append(8);
		ll.append(5);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		partition(ll.get(1), 5);
		Node n = ll.getHeader();
		while (n.next != null) {
			System.out.print(n.data + "-> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
}

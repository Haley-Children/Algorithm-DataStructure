package linkedlist;


import linkedlist.LinkedList.Node;

/*
 * LinkedList Algorithm 5 -1
 * 어떤 숫자를 자릿수별로 한 개씩 링크드리스트에 담았다. 그런데 1의 자리가 헤더에 오도록 "거꾸로" 담았다.
 * 이런식의 링크드리스트 두 개를 받아서 합산하고, 같은 방법으로 링크드리스트에 담아서 반환하시오.
 */

public class DigitSumFromLast {
	private static Node sumListsFromLast(Node l1, Node l2, int carry) {
		if (l1 == null && l2 == null && carry == 0) {
			return null;
		}

		Node result = new Node();

		int value = carry;
		if (l1 != null) {
			value += l1.data;
		}
		if (l2 != null) {
			value += l2.data;
		}

		result.data = value % 10;

		if (l1 != null || l2 != null) {
			Node next = sumListsFromLast(l1 == null ? null : l1.next, l2 == null ? null : l2.next,
					value >= 10 ? 1 : 0);
			result.next = next;
		}

		return result;
	}

	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append(9);
		l1.append(1);
		l1.append(4);
		l1.retrieve();

		LinkedList l2 = new LinkedList();
		l2.append(6);
		l2.append(4);
		l2.append(3);
		l2.retrieve();

		Node n = sumListsFromLast(l1.get(1), l2.get(1), 0);
		while (n.next != null) {
			System.out.print(n.data + "-> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
}
/*
 * 9 -> 1 -> 4
 * 6 -> 4 -> 3
 * 5-> 6-> 7
 */

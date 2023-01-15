package linkedlist;

import linkedlist.LinkedList.Node;
/*
 * LinkedList Algorithm 5 -2
 * 어떤 숫자를 자릿수별로 한 개씩 링크드리스트에 담았다. 1의 자리가 마지막에 오도록 순서대로 담았다.
 * 이런식의 링크드리스트 두 개를 받아서 합산하고, 같은 방법으로 링크드리스트에 담아서 반환하시오.
 */
class Storage {
	int carry = 0;
	Node result = null;
}

public class DigitSum {

	private static Node sumLists(Node l1, Node l2) {
		int len1 = getListLength(l1);
		int len2 = getListLength(l2);

		if (len1 < len2) {
			l1 = lPadList(l2, len2 - len1);
		} else {
			l2 = lPadList(l2, len1 - len2);
		}

		// 재귀함수 호출하여 자릿수 합 계산
		Storage storage = addLists(l1, l2);

		// 재귀함수 호출 후 carry 검증
		if (storage.carry == 0) {
			return storage.result;
		} else {
			storage.result = insertBefore(storage.result, storage.carry);
		}
		return storage.result;
	}

	private static Storage addLists(Node l1, Node l2) {
		if (l1 == null && l2 == null) {
			Storage storage = new Storage();
			return storage;
		}
		// 먼저 재귀로 마지막 노드까지 갔다가 계산을 해야함
		Storage storage = addLists(l1.next, l2.next);
		int value = storage.carry + l1.data + l2.data;
		int data = value % 10;
		// storage 객체를 이용하여 객체 주소와 carry를 전달한다.
		storage.result = insertBefore(storage.result, data);
		storage.carry = value / 10;
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

	// 노드의 바로 앞에 새 데이터 노드 추가
	private static Node insertBefore (Node node, int data) {
		Node before = new Node(data);
		if (node != null) {
			before.next = node;
		}
		return before;
	}

	// 자릿수가 같지 않아 더 적은 자릿수의 왼쪽에 0 추가하는 메서드
	private static Node lPadList(Node l, int length) {
		Node head = l;
		for (int i = 0; i < length; i++) {
			head = insertBefore(head, 0);
		}
		return head;
	}

	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append(9);
		l1.append(1);
		l1.retrieve();

		LinkedList l2 = new LinkedList();
		l2.append(1);
		l2.append(1);
		l2.retrieve();

		Node n = sumLists(l1.get(1), l2.get(1));
		while (n.next != null) {
			System.out.print(n.data + "-> ");
			n = n.next;
		}
		System.out.println(n.data);
	}
}

/*
 * 9 -> 1
 * 1 -> 1
 * 1-> 0-> 2
 */

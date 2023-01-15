// Linked List Digit 합산 알고리즘 in Java
// https://youtu.be/vuJk2JZd3fI
// 문제
// 어떤 숫자를 자리수 별로 한개씩 Linked List에 담았다
// 그런데 1의자리가 헤더에 오도록 거꾸로 담았다
// 이런식의 Linked List 두개를 받아서 합산하고, 같은 식으로 Linked List에 담아서 반환하라
// 예시) l1 : 9->1->4, l2 : 6->4->3, 결과 : 5->6->7
// 심화문제
// Linked List의 숫자가 거꾸로가 아니라면?
// 예시) l1 : 4->1->9, l2 : 3->4, 결과 : 4->5->3
class Node {
	int data;
	Node next = null;
}
class LinkedList {
	Node header;
	LinkedList() {
		header = new Node();
	}
	void append(int d) {
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
// 심화 solution을 위한 노드와 carry를 가지는 객체
class Storage{
	int carry = 0;
	Node result = null;
}

public class SumLists {
	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.append(9);
		l1.append(1);
		l1.append(4);
		l1.retrieve(); // 9 -> 1 -> 4
		
		LinkedList l2 = new LinkedList();
		l2.append(6);
		l2.append(4);
		l2.append(3);
		l2.retrieve(); // 6 -> 4 -> 3
		
		Node n1 = sumLists1(l1.get(1), l2.get(1), 0);
		while (n1.next != null) {
			System.out.print(n1.data + " -> ");
			n1 = n1.next;
		}
		System.out.println(n1.data); // 5 -> 6 -> 7
		
		LinkedList l3 = new LinkedList();
		l3.append(9);
		l3.append(1);
		l3.retrieve(); // 9 -> 1
		
		LinkedList l4 = new LinkedList();
		l4.append(1);
		l4.append(1);
		l4.retrieve(); // 1 -> 1
		
		Node n2 = sumLists2(l3.get(1), l4.get(1));
		while (n2.next != null) {
			System.out.print(n2.data + " -> ");
			n2 = n2.next;
		}
		System.out.println(n2.data); // 1 -> 0 -> 2
	}
	// solution : 재귀호출을 이용한 방법
	private static Node sumLists1(Node l1, Node l2, int carry){
		if (l1 == null && l2 == null && carry == 0) return null; // 더이상 계산할게 없으면 null 반환
		Node result = new Node(); // 결과 노드 선언
		int value = carry; // 올림하여 넘어온 값 carry를 value에 부여
		
		if (l1 != null) value += l1.data; // 1번 Linked List의 데이터가 있다면 value에 합산
		if (l2 != null) value += l2.data; // 2번 Linked List의 데이터가 있다면 value에 합산
		result.data = value % 10; // value의 1의 자리를 result의 데이터로 선언
		
		if (l1 != null || l2 != null) { // 적어도 하나의 Linked List의 데이터가 있다면 (다음자리 계산이 필요하다면)
			Node next = sumLists1(l1 == null? null : l1.next, // l1이 null이 아니면 l1을 다음 노드로
											 l2 == null? null : l2.next, // l2가 null이 아니면 l2를 다음 노드로
											 value >= 10? 1 : 0);		 // value가 10이상이라면 (자리수 올림이 있다면) carry를 1로
																		 // 하는 인자를 가지는 sumList1()를 호출하여 next 노드를 선언
			result.next = next; // result의 다음 노드를 next 노드로 선언
		}
		return result;
	}
	// 심화 solution : 길이를 우선적으로 비교한 후 짧은 리스트의 앞을 0으로 채운 뒤 뒤에서부터 계산
	// 뒤에서부터 계산해야하므로 결과를 가진 노드와 carry의 값을 가지는 객체를 반환하는 형태로 구현
	private static Node sumLists2(Node l1, Node l2){
		int len1 = getListLength(l1);
		int len2 = getListLength(l2);
		
		if (len1 < len2) l1 = LPadList(l1, len2 - len1);
		else l2 = LPadList(l2, len1 - len2);
		
		Storage storage = addLists(l1, l2);
		// 리스트의 길이를 넘어갔음에도 carry가 남은 경우를 예외처리 (예시 : 91 + 10 = 101)
		if (storage.carry != 0) storage.result = insertBefore(storage.result, storage.carry);
		return storage.result;
	}
	// 두 Linked List의 덧셈 후 storage를 반환하는 함수
	private static Storage addLists(Node l1, Node l2) {
		if (l1 == null && l2 == null) { // 종료조건
			Storage storage = new Storage();
			return storage;
		}
		Storage storage = addLists(l1.next, l2.next); // 종료조건 될때까지 쭉 호출해서 들어감
		int value = storage.carry + l1.data + l2.data; // 해당 함수 내에서의 value 계산
		int data = value % 10; // 해당 함수 내에서의 data 계산
		storage.result = insertBefore(storage.result, data); // 기존의 result 앞에 계산된 data를 가지는 노드 추가
		storage.carry = value / 10; // carry는 10으로 나눈 몫
		return storage;
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
	// Linked List 앞에 새로운 노드를 추가하는 함수
	private static Node insertBefore(Node node, int data){
		Node before = new Node();
		before.data = data;
		if (node != null) before.next = node;
		return before;
	}
	// 길이를 받아서 길이만큼 Linked List 앞에 0노드를 채워주는 함수
	private static Node LPadList(Node l, int length){
		Node head = l;
		for (int i = 0; i < length; i++) head = insertBefore(head, 0);
		return head;
	}
}
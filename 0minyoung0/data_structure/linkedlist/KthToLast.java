// 단방향 Linked List 뒤부터 세기 in Java
// https://www.youtube.com/watch?v=Vb24scNDAVg
// 단방향 LinkedList의 마지막에서 k번째 노드를 찾는 알고리즘을 설명하시오
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
}
class Reference{ // solution2의 카운터 저장을 위한 객체
	public int count = 0;
}

public class KthToLast {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		for (int k = 1; k <= 4; k++) {
			Node kth = KthToLast1(ll.header, k);
			System.out.println("Last k(" + k + ")th data is " + kth.data);
		}
		/*  Last k(1)th data is 4
			Last k(2)th data is 3
			Last k(3)th data is 2
			Last k(4)th data is 1  */
		for (int k = 1; k <= 4; k++) {
			Reference r = new Reference();
			Node found = KthToLast2(ll.header, k, r);
			System.out.print(found.data + " ");
		} System.out.println(); // 4 3 2 1
		for (int k = 1 ; k <= 4; k++) {
			Node found = KthToLast3(ll.header, k);
			System.out.print(found.data + " ");
		} System.out.println(); // 4 3 2 1
	}
	// solution1 : 처음부터 끝까지 세어서 전체 크기를 구하고 다시 처음부터 탐색
	// 공간복잡도 O(1), 시간복잡도 O(N)
	private static Node KthToLast1(Node first, int k){
		Node n = first;
		int total = 1; // 아래 while문에서 마지막 노드에 도달하지 않기 때문에 1에서 시작
		while (n.next != null) {
			total++; // 전체 노드 개수 세기
			n = n.next;
		}
		n = first;
		for (int i = 1; i < total - k + 1; i++) {
			n = n.next;
		}
		return n;
	}
	// solution2 : 재귀호출
	// 공간복잡도 O(N), 시간복잡도 O(N)
	private static Node KthToLast2(Node n, int k, Reference r){
		if (n == null) {
			return null;
		}
		Node found = KthToLast2(n.next, k, r); // null이 나올때까지 쭉 들어감
		r.count++; // 반환하고 나올때마다 레퍼런스 객체의 카운터를 증가시킴
		if (r.count == k) { // 카운터와 k가 일치하면
			return n; // 해당 노드를 반환
		}
		return found; // 카운터와 k가 일치하지 않으면 이미 찾아서 반환한 found를 반환 (찾기 전에는 null)
	}
	// solution3 : 포인터 사용
	// p1을 k만큼 먼저 보내고 p1과 p2를 동시에 이동하다가 p1이 null에 도달하면 p2의 위치를 반환
	// 공간복잡도 O(1), 시간복잡도 O(N)
	private static Node KthToLast3(Node first, int k){
		Node p1 = first;
		Node p2 = first;
		for (int i = 0 ; i < k; i++) {
			if (p1 == null) return null;
			p1 = p1.next;
		}
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}

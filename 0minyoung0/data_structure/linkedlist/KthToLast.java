// 단방향 linked list 뒤부터 세기
// https://www.youtube.com/watch?v=Vb24scNDAVg
// 단방향 linked list의 끝에서 k번째 노드를 찾는 알고리즘을 구현하시오
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
class Reference{ // solution2에서 count의 전달을 위한 객체 선언
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
	// solution1 : list를 끝까지 순회하여 전체 길이를 구한 뒤 다시 header로 돌아와 total - k번째 노드의 값을 반환하는 방식
	// 시간복잡도 O(N), 공간복잡도 O(1)
	private static Node KthToLast1(Node first, int k){
		Node n = first;
		int total = 1;
		while (n.next != null) {
			total++;
			n = n.next;
		}
		n = first;
		for (int i = 1; i < total - k + 1; i++) {
			n = n.next;
		}
		return n;
	}
	// solution2 : 재귀 호출을 사용하여 return 될때 count를 증가시키는 방식
	// Node를 반환하기 위해 count를 객체 안에 넣어서 객체의 주소를 전달
	// 시간복잡도 O(N), 공간복잡도 O(N)
	private static Node KthToLast2(Node n, int k, Reference r){
		if (n == null) {
			return null;
		}
		Node found = KthToLast2(n.next, k, r);
		r.count++;
		if (r.count == k) {
			return n;
		}
		return found;
	}
	// solution3 : 2개의 포인터를 활용하여 p1이 p2보다 k만큼 앞서 가도록 하는 방식
	// 시간복잡도 O(N), 공간복잡도 O(1)
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

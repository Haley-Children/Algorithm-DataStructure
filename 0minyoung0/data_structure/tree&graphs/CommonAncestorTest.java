// Tree에서 두노드의 첫번째 공통부모 찾기
// https://youtu.be/VozLMFJQt3Q
// 이진트리에서 주어진 두개의 노드의 첫번째 공통된 부모 노드를 찾으시오.
// (단, 다른 자료구조 사용금지)

package treeAndGraphs.commonAncestor;

import java.util.*;

class Tree {
	static class Node {
		int data;
		Node left;
		Node right;
		Node parent;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	HashMap<Integer, Node> rootMap;
	Tree (int size) {
		rootMap = new HashMap<Integer, Node>();
		root = makeBST(0, size - 1, null);
	}
	Node makeBST(int start, int end, Node parent) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1, node);
		node.right = makeBST(mid + 1, end, node);
		node.parent = parent;
		rootMap.put(mid, node);
		return node;
	}
	Node getNode(int data) {
		return rootMap.get(data);
	}
	
//	// Solution 1 : 길이 맞추기
//	Node commonAncestor(int d1, int d2) {
//		Node p = getNode(d1);
//		Node q = getNode(d2);
//		int diff = depth(p) - depth(q);
//		Node first = diff > 0? q : p;
//		Node second = diff > 0? p : q;
//		second = goUpBy(second, Math.abs(diff));
//		while (first != second && first != null && second != null) {
//			first = first.parent;
//			second = second.parent;
//		}
//		return first == null || second == null ? null : first;
//	}
//	Node goUpBy(Node node, int diff) {
//		while (diff > 0 && node != null) {
//			node = node.parent;
//			diff--;
//		}
//		return node;
//	}
//	int depth(Node node) {
//		int depth = 0;
//		while (node != null) {
//			node = node.parent;
//			depth++;
//		}
//		return depth;
//	}
	
//	// Solution 2 : 형제 검색
//	// 첫번째 공통부모의 서브트리의 개수를 t라고 할때 O(t)의 시간이 걸림
//	// 최악의 경우 O(n)
//	boolean covers (Node root, Node node) {
//		if (root == null) return false;
//		if (root == node) return true;
//		return covers(root.left, node) || covers(root.right, node);
//	}
//	Node commonAncestor(int d1, int d2) {
//		Node p = getNode(d1);
//		Node q = getNode(d2);
//		if (!covers(root, p) || !covers(root, q)) {
//			return null;
//		}else if (covers(p, q)) {
//			return p;
//		}else if (covers(q, p)) {
//			return q;
//		}
//		Node sibling = getSibling(p);
//		Node parent = p.parent;
//		while (!covers(sibling, q)) {
//			sibling = getSibling(parent);
//			parent = parent.parent;
//		}
//		return parent;
//	}
//	Node getSibling(Node node) {
//		if (node == null || node.parent == null) {
//			return null;
//		}
//		Node parent = node.parent;
//		return parent.left == node? parent.right : parent.left;
//	}
	
//	// Solution 3 : 부모 노드 주소가 없을 경우
//	// 왼쪽 서브트리와 오른쪽 서브트리를 탐색하면서 p나 q가 있는지 확인
//	// 최초로 양쪽에 하나씩 있으면 최초 공통 조상임
//	// binary search는 O(logn)인데 호출될때마다
//	// 검색했던 부분을 반복적으로 또 검색하기때문에 O((logn)^2)
//	// O((logn)^2)은 O(N)보다 작으므로 그냥 O(n)이라고 해도 됨
//	boolean covers (Node root, Node node) {
//		if (root == null) return false;
//		if (root == node) return true;
//		return covers(root.left, node) || covers(root.right, node);
//	}
//	Node commonAncestor(int d1, int d2) {
//		Node p = getNode(d1);
//		Node q = getNode(d2);
//		if (!covers(root, p) || !covers(root, q)) {
//			return null;
//		}
//		return ancestorHelper(root, p, q);
//	}
//	Node ancestorHelper(Node root, Node p, Node q) {
//		if (root == null || root == p || root == q) {
//			return root;
//		}
//		boolean pIsOnLeft = covers(root.left, p);
//		boolean qIsOnLeft = covers(root.left, q);
//		if (pIsOnLeft != qIsOnLeft) {
//			return root;
//		}
//		Node childSide = pIsOnLeft? root.left : root.right;
//		return ancestorHelper(childSide, p, q);
//	}
	
//	// Solution 4 : 최적화 시키기
//	// postorder로 모든 노드를 한번씩만 방문하는 방식
//	// 시간 복잡도 O(N)
//	Node commonAncestor(int d1, int d2) {
//		Node p = getNode(d1);
//		Node q = getNode(d2);
//		return commonAncestor(root, p, q);
//	}
//	Node commonAncestor(Node root, Node p, Node q) {
//		if (root == null) return null;
//		if (root == p && root == q) return root;
//		Node x = commonAncestor(root.left, p, q);
//		if (x != null && x != p && x != q) {
//			return x;
//		}
//		Node y = commonAncestor(root.right, p, q);
//		if (y != null && y != p && y != q) {
//			return y;
//		}
//		
//		if (x != null && y != null) {
//			return root;
//		} else if (root == p || root == q) {
//			return root;
//		} else {
//			return x == null ? y : x;
//		}
//	}
	
	// Solution 5 : 정확한 결과 가져오기
	// Solution 4는 p나 q 중 하나가 트리 내에 존재하지 않을 때를 구분할 수 없음
	// 임의의 저장공간에 노드의 주소와 찾았는지 여부를 저장하는 방법
	class Result {
		Node node;
		boolean isAncestor;
		Result (Node n, boolean isAnc){
			node = n;
			isAncestor = isAnc;
		}
	}
	Node commonAncestor(int d1, int d2) {
		Node p = getNode(d1);
		Node q = getNode(d2);
		Result r = commonAncestor(root, p, q);
		if (r.isAncestor) {
			return r.node;
		}
		return null;
	}
	Result commonAncestor(Node root, Node p, Node q) {
		if (root == null) return new Result(null, false);
		if (root == p && root == q) return new Result(root, true);
		Result rx = commonAncestor(root.left, p, q);
		if (rx.isAncestor) return rx;
		Result ry = commonAncestor(root.right, p, q);
		if (ry.isAncestor) return ry;
		
		if (rx.node != null && ry.node != null) {
			return new Result(root, true);
		} else if (root == p || root == q) {
			boolean isAncestor = rx.node != null || ry.node != null;
			return new Result(root, isAncestor);
		} else {
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	}
}


public class CommonAncestorTest {
	public static void main(String[] args) {
		Tree t = new Tree(10);
/*
	     (4)
	    /   \
	   /     \
	  /       \
	(1)       (7)
   /   \     /   \
 (0)   (2)	(5)  (8)
	     \    \    \
	     (3)  (6)  (9)
*/
		Tree.Node fa = t.commonAncestor(3, 5);
		System.out.println("The First common ancestor is " + fa.data); // 4
		fa = t.commonAncestor(6, 7);
		System.out.println("The First common ancestor is " + fa.data); // 7
		fa = t.commonAncestor(6, 9);
		System.out.println("The First common ancestor is " + fa.data); // 7
		fa = t.commonAncestor(0, 3);
		System.out.println("The First common ancestor is " + fa.data); // 1
		fa = t.commonAncestor(2, 8);
		System.out.println("The First common ancestor is " + fa.data); // 4
	}
}

// Tree의 Balance 확인하기
// https://youtu.be/-m154rqFQng
// 주어진 이진트리의 Balance가 맞는지 확인하는 함수를 구현하시오.
// (여기서 Balance가 맞다는 의미는 어떤 노드의 양쪽서브트리의 길이가 1이상 차이가 나지 않는 것을 뜻함)

package treeAndGraphs.isBalancedTest;

class Tree {
	class Node {
		int data;
		Node left;
		Node right;
		Node (int data) {
			this.data = data;
		}
	}
	Node root;
	Tree(int size) {
		root = makeBST(0, size - 1);
	}
	Node makeBST(int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		Node node = new Node(mid);
		node.left = makeBST(start, mid - 1);
		node.right = makeBST(mid + 1, end);
		return node;
	}
	// Solution 1, 2 : 가장 긴 서브트리끼리 비교한다고 생각하고 구현
	// Solution1 : 노드들을 하나씩 돌면서 자식 노드들을 재귀 호출
	// 양쪽 서브트리의 길이를 비교하다가 차이가 1을 넘으면 false를 반환
	// 노드가 호출될때마다 매번 다시 가서 길이를 재기 때문에 O(NlogN)의 시간이 걸림
	boolean isBalanced(Node root) {
		if (root == null) return true;
		int heightDiff = getHeight(root.left)- getHeight(root.right);
		if (Math.abs(heightDiff) > 1) {
			return false;
		}else {
			return isBalanced(root.left) && isBalanced(root.right); 
		}
	}
	int getHeight(Node root) {
		if (root == null) return -1;
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1; 
	}
	// Solution2 : 노드를 돌면서 동시에 길이를 재는 방식으로 구현
	// 함수에서 돌아올때 +1을 반환해서 길이를 반환
	// 정수형을 반환하기 때문에 false일때는 Ingeger.MIN_VALUE를 사용하기로 함
	// 노드를 한번씩만 방문하면 되므로 O(N)의 시간이 걸림
	int checkHeight(Node root) {
		if (root == null) return -1;
		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int heightDiff = leftHeight - rightHeight;
		if (Math.abs(heightDiff) > 1) {
			return Integer.MIN_VALUE;
		}else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
	boolean isBalanced2 (Node root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}
	// Solution3 : 모든 서브트리의 가장 긴 줄기와 가장 짧은 줄기가 1 이하로 차이 나도록 구현
	// 각 서브트리를 아래로 내려가며 호출하여 마지막에 도달하면 Object에 min과 max를 저장
	// 노드를 한번씩 방문하므로 O(N)의 시간이 걸림
	class Level {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
	}
	boolean isBalanced3 (Node root) {
		Level obj = new Level();
		checkBalanced(root, obj, 0);
		if (Math.abs(obj.min - obj.max) > 1) return false;
		else return true;
	}
	void checkBalanced(Node node, Level obj, int level) {
		if (node == null) {
			if (level < obj.min) obj.min = level;
			else if (level > obj.max) obj.max = level;
			return;
		}
		checkBalanced(node.left, obj, level + 1);
		checkBalanced(node.right, obj, level + 1);
	}
}

public class IsBalancedTest {
	public static void main(String[] args) {
		Tree t = new Tree(10);
		/*	     (4)
			    /   \
			   /     \
			  /       \
			(1)       (7)
		  /   \     /   \
		(0)   (2)	(5)  (8)
			     \    \    \
			     (3)  (6)  (9)
		*/
		System.out.println(t.isBalanced(t.root));
		System.out.println(t.isBalanced2(t.root));
		System.out.println(t.isBalanced3(t.root));
	}
}

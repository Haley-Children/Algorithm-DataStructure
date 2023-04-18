// BST insertion/deletion
// https://youtu.be/xxADG17SveY

package treeAndGraphs.binarySearchTreeInsertionDeletion;

class BinarySearchTree{
	class Node{
		int data;
		Node left, right;
		
		public Node(int data) {
			this.data = data;
		}
	}
	Node root;
	public Node search(Node root, int key) {
		if (root == null || root.data == key) return root;
		if (root.data > key) return search(root.left, key);
		return search(root.right, key);
	}
	public void insert(int data) {
		root = insert(root, data);
	}
	private Node insert(Node root, int data) {
		if (root == null) {
			root = new Node(data);
			return root;
		}
		if (data < root.data) {
			root.left = insert(root.left, data);
		}else if (data > root.data) {
			root.right = insert(root.right, data);
		}
		
		return root;
	}
	public void delete(int data) {
		root = delete(root, data);
	}
	private Node delete(Node root, int data) {
		if (root == null) return root;
		if (data < root.data) {
			root.left = delete(root.left, data);
		}else if (data > root.data) {
			root.right = delete(root.right, data);
		}else {
			if (root.left == null && root.right == null) return null;
			else if (root.left == null) return root.right;
			else if (root.right == null) return root.left;
			
			root.data = findMin(root.right);
			root.right = delete(root.right, root.data);
		}
		return root;
	}
	int findMin(Node root) {
		int min = root.data;
		while (root.left != null) {
			min = root.left.data;
			root = root.left;
		}
		return min;
	}
	public void inorder() {
		inorder(root);
		System.out.println("");
	}
	private void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}
}

public class BinarySearchTreeInsertionDeletion {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		/*
		 *     4
		 *    / \
		 *   2   6
		 *  / \ / \
		 * 1  3 5  7 */
		
		tree.insert(4);
		tree.insert(2);
		tree.insert(1);
		tree.insert(3);
		tree.insert(6);
		tree.insert(5);
		tree.insert(7);
		
		tree.inorder(); // 1 2 3 4 5 6 7
		tree.delete(5);
		tree.inorder(); // 1 2 3 4 6 7
		tree.delete(6);
		tree.inorder(); // 1 2 3 4 7
		tree.delete(2);
		tree.inorder(); // 1 3 4 7
	}
}

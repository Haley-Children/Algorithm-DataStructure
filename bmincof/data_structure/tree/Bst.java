package structure.tree;

public class Bst {
    static class BinarySearchTree {
        class Node {
            int data;
            Node left, right;
            Node(int data) {
                this.data = data;
            }
        }

        Node root;

        Node search(Node root, int key) {
            if(root == null || root.data == key) return root;
            if(root.data > key) return search(root.left, key);
            return search(root.right, key);
        }

        void insert(int data) {
            root = insert(root, data);
        }

        Node insert(Node root, int data) {
            if(root == null) {
                root = new Node(data);
                return root;
            }
            if(data < root.data) {
                root.left = insert(root.left, data);
            } else if (data > root.data) {
                root.right = insert(root.right, data);
            }

            return root;
        }

        void delete(int data) {
            root = delete(root, data);
        }

        Node delete(Node root, int data) {
            if(root == null) return root;
            if(data < root.data) {
                root.left = delete(root.left, data);
            } else if (data > root.data) {
                root.right = delete(root.right, data);
            } else {
                if(root.left == null && root.right == null) return null;
                else if(root.left == null) return root.right;
                else if(root.right == null) return root.left;

                // 자식이 둘 다 있는 경우
                root.data = findMin(root.right);
                root.right = delete(root.right, root.data);
            }
            return root;
        }

        int findMin(Node root) {
            int min = root.data;
            while(root.left != null) {
                min = root.left.data;
                root = root.left;
            }
            return min;
        }

        void inorder(Node root) {
            if(root == null) return;

            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(7);

        tree.inorder(tree.root);
        System.out.println();
        tree.delete(5);
        tree.delete(6);
        tree.delete(2);
        tree.inorder(tree.root);
    }
}

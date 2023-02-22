package structure;

public class Tree {

    class Node {
        int data;
        Node left;
        Node right;
    }
    public Node root;

    public void setRoot(Node node) {
        this.root = node;
    }

    public Node getRoot() {
        return root;
    }

    // 현재 노드에 data와 left, right 노드를 갖는 새로운 노드 추가
    public Node makeNode(Node left, int data, Node right) {
        Node node = new Node();
        node.data = data;
        node.left = left;
        node.right = right;
        return node;
    }

    public void inorder(Node node) {
        if(node != null) {
            inorder(node.left);
            System.out.println(node.data);
            inorder(node.right);
        }
    }

    public void preorder(Node node) {
        if(node != null) {
            System.out.println(node.data);
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder(Node node) {
        if(node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.data);
        }
    }

    /*
            (1)
           ↙   ↘
         (2)   (3)
        ↙   ↘
      (4)   (5)
      Inorder (Left , Root, Right) : 4 2 5 1 3
      Preorder (Left , Root, Right) : 1 2 4 5 3
      Postrder (Left , Root, Right) : 4 5 2 3 1
     */

    public static void main(String[] args) {
        Tree t = new Tree();
        Node n4 = t.makeNode(null, 4, null);
        Node n5 = t.makeNode(null, 5, null);
        Node n2 = t.makeNode(n4, 2, n5);
        Node n3 = t.makeNode(null, 3, null);
        Node n1 = t.makeNode(n2, 1, n3);
        t.setRoot(n1);

        t.inorder(t.getRoot());
        System.out.println();
        t.preorder(t.getRoot());
        System.out.println();
        t.postorder(t.getRoot());
    }

}

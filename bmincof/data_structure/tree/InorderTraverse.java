package structure.tree;

// 이진 검색트리에서 주어진 노드의 다음 노드를 찾는 함수를 구현
// 단, 다음 노드의 순서는 inorder traverse에 입각
public class InorderTraverse {
    static class Tree {
        class Node {
           int data;
           Node left;
           Node right;
           Node parent;
           Node(int data) {
               this.data = data;
           }
        }

        Node root;
        Tree(int size) {
            root = makeBST(0, size - 1, null);
        }

        // 위 아래로 모두 이동할 수 있는 트리
        Node makeBST(int start, int end, Node parent) {
            if(start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);

            node.left = makeBST(start, mid - 1, node);
            node.right = makeBST(mid + 1, end, node);
            node.parent = parent;

            return node;
        }

        // inorder 순서로 다음 노드를 찾기
        void findNext(Node node) {
            // 현재 노드보다 더 큰 수(right)가 없으면 부모노드로 올라감
            // 현재 노드가 부모노드의 오른쪽 자식이면 다시 올라가서 반복
            if(node.right == null) {
                System.out.println(findAbove(node.parent, node).data);
            } else {
                System.out.println(findBelow(node.right).data);
            }
        }

        // child 가 root의 왼쪽 자식이라면 root가 child의 바로 다음 순서
        // 아니라면 해당 순서가 될 때 까지 올라가기
        Node findAbove(Node root, Node child) {
            if(root == null) return null;
            if(root.left == child) return root;
            return findAbove(root.parent, root);
        }

        // bst이므로 바로 다음 숫자는 오른쪽 자식 노드의 가장 왼쪽 자식 노드
        Node findBelow(Node root) {
            if(root.left == null) return root;
            return findBelow(root.left);
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree(10);
        t.findNext(t.root.right.left.right);
    }
}

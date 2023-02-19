package structure.tree;

// 크기가 매우 큰 두개의 이진트리 T1, T2 (T1 > T2)가 있을 때,
// T2가 T1의 서브트리인지 확인하는 알고리즘을 구현
// T2의 루트 노드의 값이 T1에 존재하고, 그 노드를 기준으로 T2의 값과 구조가 같으면 서브트리
public class CheckIsSubtree {
    // T2의 루트 노드를 찾으면 그 때부터 비교하는 것이 좋다.
    // 방문한 노드부터 순회하는 탐색방법 : preorder
    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            Node(int data) {
                this.data = data;
            }
        }

        Node root;

        Node makeBST(int start, int end) {
            if(start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1);
            node.right = makeBST(mid + 1, end);
            return node;
        }

        boolean containsTree(Node t1, Node t2) {
            if(t2 == null) return true;
            return isSubTree(t1, t2);
        }

        boolean isSubTree(Node t1, Node t2) {
            if(t1 == null) {
                return false;
            } else if (t1.data == t2.data && matchTree(t1, t2)) {
                return true;
            }
            // 데이터가 같지 않으면 t1의 좌우 서브트리에서 일치하는 값이 있는지 추가로 탐색
            return isSubTree(t1.left, t2) || isSubTree(t1.right, t2);
        }

        boolean matchTree(Node t1, Node t2) {
            if(t1 == null && t2 == null) {
                return true;
            } else if(t1 == null || t2 == null) {
                return false;
            } else if(t1.data != t2.data) {
                return false;
            } else {
                return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
            }
        }

        public static void main(String[] args) {
            Tree t1 = new Tree();
            Tree t2 = new Tree();

            boolean result;
            t1.root = t1.makeBST(0, 9);
            t2.root = t2.makeBST(7, 9);
            result = t1.containsTree(t1.root, t2.root);
            System.out.println(result);
        }
    }
}

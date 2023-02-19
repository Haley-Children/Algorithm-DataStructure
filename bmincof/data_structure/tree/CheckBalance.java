package structure.tree;

public class CheckBalance {

    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            Node(int data) {
                this.data = data;
            }
        }

        // 트리의 루트
        Node root;

        Tree(int size) {
            root = makeBST(0, size - 1);
        }

        // start부터 end까지의 수로 BST를 만드는 메서드
        Node makeBST(int start, int end) {
            if (start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);

            node.left = makeBST(start, mid - 1);
            node.right = makeBST(mid + 1, end);

            return node;
        }

        // isBalanced를 호출할 때마다 순회하면서 높이체크 -> O(NlogN)
        boolean isBalanced(Node root) {
            if(root == null) return true;

            int heightDiff = getHeight(root.left) - getHeight(root.right);
            // 서브트리의 높이 차이가 1보다 크면 balance가 맞지 않는다.
            if(Math.abs(heightDiff) > 1) {
                return false;
            // 양 쪽 서브트리가 모두 balanced이면 balanced tree
            } else {
                return isBalanced(root.left) && isBalanced(root.right);
            }
        }

        // root의 서브트리의 높이를 세는 메서드
        int getHeight(Node root) {
            if(root == null) return -1;
            // 양 옆이 모두 null(==leaf)이면 높이 0을 반환
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }

        int checkHeight(Node root) {
            if(root == null) return -1;
            // 왼쪽 높이 체크
            int leftHeight = checkHeight(root.left);
            // unbalance이면
            if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

            // 오른쪽 높이 체크
            int rightHeight = checkHeight(root.right);
            if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

            int heightDiff = leftHeight - rightHeight;
            // 높이 차이가 1보다 크면 unbalanced
            if(Math.abs(heightDiff) > 1) {
                return Integer.MIN_VALUE;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }

        // 한 번에 높이와 balance 체크 -> O(N)
        boolean isBalanced2 (Node root) {
            return checkHeight(root) != Integer.MIN_VALUE;
        }

        // 그 어떤 서브트리도 길이 차이가 1보다 크면 안된다고 제한
        class Level {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
        }

        boolean isBalanced3(Node root) {
            Level obj = new Level();
            checkBalanced(root, obj, 0);
            if(Math.abs(obj.min - obj.max) > 1) return false;
            else return true;
        }

        // 각 서브트리의 마지막에 도착했을 때 높이를 obj에 갱신
        void checkBalanced(Node node, Level obj, int level) {
            if(node == null) {
                if(level < obj.min) obj.min = level;
                else if(level > obj.max) obj.max = level;
                return;
            }
            checkBalanced(node.left, obj, level + 1);
            checkBalanced(node.right, obj, level + 1);
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree(11);
        System.out.println(t.isBalanced(t.root));
        System.out.println(t.isBalanced2(t.root));
        System.out.println(t.isBalanced3(t.root));
    }
}

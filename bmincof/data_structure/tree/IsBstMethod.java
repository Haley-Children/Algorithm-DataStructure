package structure.tree;

// 이진검색트리인지 확인하는 메서드 구현 문제
// inorder 순회 했을 때 값이 증가하는 순서가 아니면 BST가 아님
public class IsBstMethod {
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
        int size;
        Tree(int size) {
            this.size = size;
            root = makeBST(0, size - 1);
            // 반례 체크용
//            root.right.right.right.left = new Node(10);
//            this.size++;
        }

        Node makeBST(int start, int end) {
            if(start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);

            node.left = makeBST(start, mid - 1);
            node.right = makeBST(mid + 1, end);
            return node;
        }

        // 이 방법은 size 크기만큼의 배열이 추가로 필요하다.
        boolean isValidateBST1() {
            int[] array = new int[size];
            // inorder 순회하면서 array 값 저장
            inorder(root, array);
            // inorder 순으로 정렬된 게 아니면 false
            for (int i = 1; i < array.length; i++) {
                if(array[i] < array[i - 1]) {
                    return false;
                }
            }
            return true;
        }

        int index = 0;
        void inorder(Node root, int[] array) {
            if(root != null) {
                inorder(root.left, array);
                array[index] = root.data;
                index++;
                inorder(root.right, array);
            }
        }

        Integer last_printed = null;
        boolean isValidateBST2() {
            return isValidateBST2(root);
        }

        boolean isValidateBST2(Node n) {
            if(n == null) return true;
            if(!isValidateBST2(n.left)) return false;
            if(last_printed != null && n.data < last_printed) {
                return false;
            }
            last_printed = n.data;
            if(!isValidateBST2(n.right)) return false;
            return true;
        }

        boolean isValidateBST3() {
            return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean isValidateBST3(Node n, int min, int max) {
            if(n == null) {
                return true;
            }
            // 현재 노드가 정렬되어 있지 않을 경우 false
            if(n.data < min || n.data > max) {
                return false;
            }
            // 왼쪽 오른쪽 서브트리 중 하나라도 정렬되어 있지 않으면 false
            if(!isValidateBST3(n.left, min, n.data) || !isValidateBST3(n.right, n.data, max)) {
                return false;
            }
            // 모두 통과하면 true
            return true;
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree(10);
        System.out.println("Solution 1 - using inorder : " + t.isValidateBST1());
        System.out.println("Solution 2 - without array : " + t.isValidateBST2());
        System.out.println("Solution 3 - min/max : " + t.isValidateBST3());
    }
}

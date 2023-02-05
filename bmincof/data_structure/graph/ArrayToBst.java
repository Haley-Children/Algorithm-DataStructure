public class ArrayToBst {

    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            Node (int data) {
                this.data = data;
            }
        }

        Node root;
        // 배열을 이진검색트리로 변환하는 메서드
        public void makeTree(int[] arr) {
            root = makeTreeR(arr, 0, arr.length-1);
        }

        // 배열의 시작점과 끝 점을 받아 이진 검색트리의 리프에 추가
        public Node makeTreeR(int[] arr, int start, int end) {
            if (start > end) return null;
            // 이진탐색과 같음
            int mid = (start + end) / 2;
            Node node = new Node(arr[mid]);
            node.left = makeTreeR(arr, start, mid - 1);
            node.right = makeTreeR(arr, mid+1, end);

            return node;
        }

        // 이진탐색트리으로 이진탐색하기
        public void searchBTree (Node n, int toFind) {
            if(toFind < n.data) {
                System.out.println("Data is smaller than " + n.data);
                searchBTree(n.left, toFind);
            } else if (toFind > n.data) {
                System.out.println("Data is bigger than " + n.data);
                searchBTree(n.right, toFind);
            } else {
                System.out.println("Data found!");
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        // 정렬된 배열
        for(int i =0; i < a.length; i++) {
            a[i] = i;
        }

        Tree t = new Tree();
        t.makeTree(a);
        /*
                    (4)
                   /   \
                  /     \
                (1)     (7)
               /   \    /  \
             (0)   (2) (5) (8)
                     \   \   \
                     (3) (6) (9)
         */

        t.searchBTree(t.root, 2);
    }
}


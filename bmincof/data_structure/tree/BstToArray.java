package structure.tree;

import java.util.ArrayList;
import java.util.LinkedList;

// 해당 이진 검색트리를 만들 수 있는 모든 배열을 찾기
public class BstToArray {
    // 같은 BST를 만드려면 입력 순서가 중요하다
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

        static ArrayList<LinkedList<Integer>> allSequences(Tree.Node node) {
            ArrayList<LinkedList<Integer>> result = new ArrayList<>();

            if(node == null) {
                result.add(new LinkedList<>());
                return result;
            }
            // 맨 앞에 고정으로 들어갈 prefix - 처음은 루트
            LinkedList<Integer> prefix = new LinkedList<>();
            prefix.add(node.data);

            // 노드의 순서가 담긴 리스트
            ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
            ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

            // 노드의 순서로 만들 수 있는 모든 경우의 수 생성
            for (LinkedList<Integer> left : leftSeq) {
                for (LinkedList<Integer> right : rightSeq) {
                    ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                    weaveLists(left, right, weaved, prefix);
                    result.addAll(weaved);
                }
            }

            return result;
        }

        // 백트래킹
        static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                               ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
            if(first.isEmpty() || second.isEmpty()) {
                LinkedList<Integer> result = new LinkedList<>();
                // 현재 단계 이전까지 선택했던 것들을 다시 복사
                for(int data : prefix) result.add(data);
                result.addAll(first);
                result.addAll(second);
                results.add(result);
                return;
            }

            int headFirst = first.removeFirst();
            prefix.addLast(headFirst);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            first.addFirst(headFirst);

            int headSecond = second.removeFirst();
            prefix.addLast(headSecond);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            second.addFirst(headSecond);
        }

        public static void main(String[] args) {
            Tree t = new Tree(7);
            ArrayList<LinkedList<Integer>> result = allSequences(t.root);
            for(LinkedList<Integer> l : result) {
                for(int data : l) {
                    System.out.print(data);
                }
                System.out.println();
            }
        }
    }
}

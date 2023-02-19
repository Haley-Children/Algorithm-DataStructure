package structure.tree;

import java.util.ArrayList;
import java.util.HashMap;

// 정수값이 저장된 이진트리가 있을 때 연결된 노드의 합산이 특정 숫자가 되는 경로의 개수를 찾는 문제
// 경로의 시작과 끝점은 어디든 될 수 있지만 언제나 위에서 아래로만 합산이 되어야한다.
public class FindRoutes {
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
            this.root = makeBST(0, size - 1);
        }

        Node makeBST(int start, int end) {
            if(start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1);
            node.right = makeBST(mid + 1, end);

            return node;
        }

        int countPathsWithSum(int targetSum) {
            return countPathsWithSum(root, targetSum);
        }

        int countPathsWithSum(Node root, int targetSum) {
            if(root == null) return 0;

            int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
            int pathsOnLeft = countPathsWithSum(root.left, targetSum);
            int pathsOnRight = countPathsWithSum(root.right, targetSum);
            return pathsFromRoot + pathsOnLeft + pathsOnRight;
        }

        int countPathsWithSumFromNode(Node node, int targetSum, int currSum) {
            if(node == null) return 0;
            currSum += node.data;
            int totalPaths = 0;
            if(currSum == targetSum) {
                totalPaths++;
            }
            totalPaths += countPathsWithSumFromNode(node.left, targetSum, currSum);
            totalPaths += countPathsWithSumFromNode(node.right, targetSum, currSum);
            return totalPaths;
        }

        // 들렀던 경로 재탐색 대신 메모리 이용해서 재방문하지 않기
        int countPathsWithSum2(int targetSum) {
            ArrayList<Integer> array = new ArrayList<>();
            return countPathsWithSum2(root, targetSum, array);
        }

        int countPathsWithSum2(Node root, int targetSum, ArrayList<Integer> array) {
            if(root == null) return 0;

            int totalPaths = 0;
            addValue(array, root.data);

            totalPaths = countPaths(array, targetSum);
            totalPaths += countPathsWithSum2(root.left, targetSum, array);
            totalPaths += countPathsWithSum2(root.right, targetSum, array);

            removeLast(array);

            return totalPaths;
        }

        // 기존의 값들에 새로 방문한 노드의 값 더해주기
        void addValue(ArrayList<Integer> array, int value) {
            for (int i = 0; i < array.size(); i++) {
                array.set(i, array.get(i) + value);
            }
            array.add(value);
        }

        // 배열에 목표값과 같은 값들을 센다.
        int countPaths(ArrayList<Integer> array, int targetSum) {
            int totalPaths = 0;
            for (int i = 0; i < array.size(); i++) {
                if(array.get(i) == targetSum) totalPaths++;
            }
            return totalPaths;
        }

        void removeLast(ArrayList<Integer> array) {
            int value = array.remove(array.size() - 1);
            for (int i = 0; i < array.size(); i++) {
                array.set(i, array.get(i) - value);
            }
        }

        // 해시맵과 좌표축 개념을 이용한 탐색
        int countPathsWithSum3(int targetSum) {
            HashMap<Integer, Integer> hashTable = new HashMap<>();
            hashTable.put(0, 1);
            return countPathsWithSum3(root, targetSum, 0, hashTable);
        }

        int countPathsWithSum3(Node node, int targetSum,
                               int currSum, HashMap<Integer, Integer> hashTable) {
            if(node == null) return 0;

            currSum += node.data;
            int sum = currSum - targetSum;
            int totalPaths = hashTable.getOrDefault(sum, 0);

            incrementHashTable(hashTable, currSum, 1);
            totalPaths += countPathsWithSum3(node.left, targetSum, currSum, hashTable);
            totalPaths += countPathsWithSum3(node.right, targetSum, currSum, hashTable);
            incrementHashTable(hashTable, currSum, -1);

            return totalPaths;
        }

        void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int val) {
            int newCount = hashTable.getOrDefault(key, 0) + val;
            hashTable.put(key, newCount);
        }

        public static void main(String[] args) {
            Tree tree = new Tree(10);
            System.out.println(tree.countPathsWithSum(5));
            System.out.println(tree.countPathsWithSum2(5));
            System.out.println(tree.countPathsWithSum3(3));
        }
    }
}

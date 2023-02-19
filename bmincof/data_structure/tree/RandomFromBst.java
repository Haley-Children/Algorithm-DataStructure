package structure.tree;

import java.util.Random;

// 이진검색트리에서 랜덤 노드 가져오기
// 단, 모든 노드에 동일한 확률로 임의의 노드를 가져와야 하며,
// 필요한 경우 노드에 추가 정보를 저장해도 됨.
public class RandomFromBst {
    static class Tree {
        class Node {
            int data;
            Node left;
            Node right;
            // 현재 노드의 크기
            int size = 0;
            Node(int data) {
                this.data = data;
                this.size = 1;
            }

            void insert(int data) {
                if(data <= this.data) {
                    if(left == null) {
                        left = new Node(data);
                    } else {
                        left.insert(data);
                    }
                } else {
                    if(right == null) {
                        right = new Node(data);
                    } else {
                        right.insert(data);
                    }
                }
                // 거쳐간 모든 상위 노드에 개수 + 1
                size++;
            }

            int size() {
                return size;
            }

            Node find(int data) {
                if(data == this.data) {
                    return this;
                } else if (data < this.data) {
                    return left != null ? left.find(data) : null;
                } else if (data > this.data) {
                    return right != null ? right.find(data) : null;
                } else {
                    return null;
                }
            }

            Node getIthNode(int i) {
                int leftSize = left == null ? 0 : left.size();
                if(i < leftSize) {
                    return left.getIthNode(i);
                } else if(i == leftSize) {
                    return this;
                } else {
                    // 지나온 노드 개수만큼 빼주어야함.
                    return right.getIthNode(i - (leftSize + 1));
                }
            }
        }

        Node root;

        int size() {
            return root == null ? 0 : root.size();
        }

        void insert(int data) {
            if(root == null) root = new Node(data);
            else root.insert(data);
        }

        Node getRandomNode() {
            if(root == null) return null;
            Random random = new Random();
            int i = random.nextInt(size());
            return root.getIthNode(i);
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(4);
        t.insert(0);
        t.insert(1);
        t.insert(2);
        t.insert(5);
        t.insert(7);
        t.insert(8);
        t.insert(3);
        t.insert(6);
        t.insert(9);

        System.out.println(t.getRandomNode().data);
    }
}

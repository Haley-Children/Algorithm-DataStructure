package structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DfsBfs {

    static class Graph {
        class Node {
            int data;
            LinkedList<Node> adjacent;
            boolean marked;

            Node(int data) {
                this.data = data;
                this.marked = false;
                // Edges
                adjacent = new LinkedList<>();
            }
        }

        // Graph Vertex
        Node[] nodes;

        Graph(int size) {
            nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(i);
            }
        }

        // vertex l1과 l2 사이에 edge 추가
        void addEdge(int i1, int i2) {
            Node n1 = nodes[i1];
            Node n2 = nodes[i2];
            if(!n1.adjacent.contains(n2)) {
                n1.adjacent.add(n2);
            }
            if(!n2.adjacent.contains(n1)) {
                n2.adjacent.add(n1);
            }
        }

        // dfs 오버로드
        void dfs() {
            dfs(0);
        }

        // 스택을 이용한 dfs
        void dfs(int index) {
            Node root = nodes[index];
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            root.marked = true;
            while (!stack.isEmpty()) {
                Node r = stack.pop();
                for (Node n : r.adjacent) {
                    if(!n.marked) {
                        n.marked = true;
                        stack.push(n);
                    }
                }
                visit(r);
            }
        }

        // bfs 오버로드
        void bfs() {
            bfs(0);
        }

        // 큐를 이용한 bfs
        void bfs(int index) {
            Node root = nodes[index];
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            root.marked = true;
            while (!queue.isEmpty()) {
                Node r = queue.poll();
                for (Node n : r.adjacent) {
                    if(!n.marked) {
                        n.marked = true;
                        queue.offer(n);
                    }
                }
                visit(r);
            }
        }

        // 재귀를 이용한 dfs
        void dfsR(Node r) {
            if(r == null) return;
            r.marked = true;
            visit(r);
            for (Node n : r.adjacent) {
                if(!n.marked) {
                    dfsR(n);
                }
            }
        }

        // dfsR 오버로드
        void dfsR(int index) {
            Node r = nodes[index];
            dfsR(r);
        }

        // dfsR 오버로드
        void dfsR() {
            dfsR(0);
        }

        // 현재 방문한 노드 출력
        void visit(Node n) {
            System.out.print(n.data + " ");
        }

        // 마크 초기화
        void initMarks() {
            for(Node n : nodes) {
                n.marked = false;
            }
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(3,5);
        g.addEdge(5,6);
        g.addEdge(5,7);
        g.addEdge(6,8);
        /*
            0
           /
          1 -- 3    7
          |  / | \ /
          | /  |  5
          2 -- 4   \
                    6 -- 8
         */

        //g.dfs();
        // 0 1 3 5 7 6 8 4 2

        //g.dfsR();
        // 0 1 2 4 3 5 6 8 7

        g.bfs();
        // 0 1 2 3 4 5 6 7 8
    }

}

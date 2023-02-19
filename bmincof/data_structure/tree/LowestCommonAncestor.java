package structure.tree;

import java.util.HashMap;

// 이진 트리에서 주어진 두 개의 노드의 첫 번째 공통된 부모 노드를 찾으시오
// 최소 공통 조상 문제(대표 유형)
public class LowestCommonAncestor {
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
        HashMap<Integer, Node> rootMap;
        Tree(int size) {
            // 값으로 노드를 찾아올 수 있도록 하는 해시맵
            rootMap = new HashMap<>();
            root = makeBST(0, size - 1, null);
        }

        Node makeBST(int start, int end, Node parent) {
            if (start > end) return null;

            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1, node);
            node.right = makeBST(mid + 1, end, node);
            node.parent = parent;

            rootMap.put(mid, node);
            return node;
        }

        Node getNode(int data) {
            return rootMap.get(data);
        }

        // 방법 1 :
        // 1. 루트부터 노드까지의 거리를 잰다.
        // 2. 더 깊이 있는 노드를 차이만큼 위로 올려서 두 노드의 거리를 맞춘다.
        // 3. 하나씩 올라오면서 서로 같은 노드인지 비교한다.(같으면 공통 부모)
        // 트리의 깊이만큼만 가면 되므로 O(d)
        Node getCommonAncestor(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);
            int diff = getDepth(p) - getDepth(q);

            Node first = diff > 0 ? q : p;
            Node second = diff > 0 ? p : q;
            // 더 아래에 있는 노드를 두 노드의 depth 차이만큼 노드를 위로 이동
            second = goUpBy(second, Math.abs(diff));

            // 두 노드가 null이 아니면서 같은 노드를 참조할 때까지 위로 이동
            while(first != second && first != null && second != null) {
                first = first.parent;
                second = second.parent;
            }

            return first == null || second == null ? null : first;
        }

        Node goUpBy(Node node, int diff) {
            while (diff > 0 && node != null) {
                node = node.parent;
                diff--;
            }
            return node;
        }

        int getDepth(Node node) {
            int depth = 0;
            while(node != null) {
                node = node.parent;
                depth++;
            }
            return depth;
        }

        // 방법 2 :
        // 1. 하나의 노드에서부터 root로 올라간다.
        // 2. 하나 올라갈 때마다 반대쪽 서브트리에 찾으려는 다른 노드가 있는지 확인한다.
        // 3. 서브트리에 찾으려는 노드가 있다면 현재 노드가 두 노드의 공통조상이므로 반환
        // 서브트리에 있는지 확인 O(t), t : 서브트리의 크기 (최대 N)

        // node가 root의 자손인지 확인하는 노드 서브트리에 node가 있는지 모두 돌며 체크
        boolean covers(Node root, Node node) {
            if(root == null) return false;
            if(root == node) return true;
            return covers(root.left, node) || covers(root.right, node);
        }

        Node getCommonAncestor2(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);

            if(!covers(root,p) || !covers(root, q)) {
                return null;
            } else if (covers(p, q)) {
                return p;
            } else if (covers(q, p)) {
                return q;
            }
            // 형제 노드 가져오기
            // 현재 노드가 왼쪽이면 오른쪽 자식을 가져온다. 반대도 마찬가지
            Node sibling = getSibling(p);
            Node parent = p.parent;
            // node가 있던 서브트리의 형제 서브트리가 다른 노드를 포함할 때까지 이동
            while(!covers(sibling, q)) {
                sibling = getSibling(parent);
                parent = parent.parent;
            }
            return parent;
        }

        Node getSibling(Node node) {
            if(node == null || node.parent == null) {
                return null;
            }
            Node parent = node.parent;
            return parent.left == node ? parent.right : parent.left;
        }

        // 방법 3 :
        // 만약 노드의 부모 주소를 알 수 없을 때
        // 1. 루트부터 시작해서 각 노드가 어느 쪽 서브트리에 있는지 확인
        // 2. 같은 쪽에 있다면 해당 서브트리로 이동해서 다시 반복
        // 3. 서로 다른 서브트리에 있다면 현재 있는 노드가 두 노드의 공통 조상
        // 양쪽 서브트리를 모두 조사하는데 2N -> 2N/2 -> 2N/4 ... = O(logN)
        // 호출될 때마다 반복하므로 O(logN)^2 ~= O(N)

        Node getCommonAncestor3(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);

            if(!covers(root, p) || !covers(root, q)) {
                return null;
            }
            return ancestorHelper(root, p, q);
        }

        Node ancestorHelper(Node root, Node p, Node q) {
            if(root == null || root == p || root == q) {
                return root;
            }
            boolean pIsOnLeft = covers(root.left, p);
            boolean qIsOnLeft = covers(root.left, q);
            if(pIsOnLeft != qIsOnLeft) {
                return root;
            }
            Node childSide = pIsOnLeft ? root.left : root.right;
            return ancestorHelper(childSide, p, q);
        }

        // 방법 4 :
        // 방법 3은 매번 covers를 반복하므로 비효율적
        // postorder 순회를 통해 딱 한 번 씩만 돌면서 공통 부모 확인
        // 순회할 때 해당 노드를 발견했으면 해당 노드 반환, 아니면 null
        Node getCommonAncestor4(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);
            return getCommonAncestor4(root, p, q);
        }

        Node getCommonAncestor4(Node root, Node p, Node q) {
            if(root == null) return null;
            if(root == p && root == q) return root;
            // postorder traverse
            Node x = getCommonAncestor4(root.left, p, q);
            if(x != null && x != p && x != q) {
                return x;
            }
            Node y = getCommonAncestor4(root.right, p, q);
            if(y != null && y != p && y != q) {
                return y;
            }
            if(x != null && y != null) {
                return root;
            } else if (root == p || root == q) {
                return root;
            } else {
                return x == null ? y : x;
            }
        }

        // 방법 5 :
        // 방법 4의 경우에 둘 중 하나의 노드가 트리에 없다면 틀린 결과를 반환한다.
        class Result {
            Node node;
            boolean isAncestor;
            Result(Node n, boolean isAnc) {
                node = n;
                isAncestor = isAnc;
            }
        }

        Node getCommonAncestor5(int d1, int d2) {
            Node p = getNode(d1);
            Node q = getNode(d2);
            Result r = getCommonAncestor5(root, p, q);
            if(r.isAncestor) {
                return r.node;
            }
            return null;
        }

        Result getCommonAncestor5(Node root, Node p, Node q) {
            if(root == null) return new Result(null, false);
            if(root == p && root == q) return new Result(root, true);
            // postorder traverse
            Result rx = getCommonAncestor5(root.left, p, q);
            if(rx.isAncestor) {
                return rx;
            }
            Result ry = getCommonAncestor5(root.right, p, q);
            if(ry.isAncestor) {
                return ry;
            }
            // 공통 노드를 찾은 순간
            if(rx.node != null && ry.node != null) {
                return new Result(root, true);
            // 이미 결과에 null이 아닌 노드가 있을 경우
            } else if (root == p || root == q) {
                boolean isAncestor = rx.node != null || ry.node != null;
                return new Result(root, isAncestor);
            } else {
                return new Result(rx.node == null ? rx.node : ry.node, false);
            }
        }

    }


    public static void main(String[] args) {
        Tree t = new Tree(10);
        Tree.Node fa = t.getCommonAncestor(3, 5);
        Tree.Node fb = t.getCommonAncestor2(6, 3);
        Tree.Node fc = t.getCommonAncestor3(2, 8);
        Tree.Node fd = t.getCommonAncestor4(6, 7);
        Tree.Node fe = t.getCommonAncestor4(6, 8);
        System.out.println("The Least Common Ancestor By fa : " + fa.data);
        System.out.println("The Least Common Ancestor By fb : " + fb.data);
        System.out.println("The Least Common Ancestor By fc : " + fc.data);
        System.out.println("The Least Common Ancestor By fd : " + fd.data);
        System.out.println("The Least Common Ancestor By fe : " + fe.data);
    }
}

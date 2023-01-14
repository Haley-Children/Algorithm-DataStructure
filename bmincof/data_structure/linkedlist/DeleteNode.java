public class DeleteNode extends LinkedList {

    // i 번째 노드 반환하는 메서드
    // 가장 처음 노드 인덱스 = 1
    Node get(int k) {
        Node n = head;
        for (int i = 0; i < k; i++) {
            n = n.next;
        }

        return n;
    }

    // 맨 처음과 맨 마지막 노드를 제외한 중간지점 노드 삭제하는 메서드
    // O(N)
    boolean deleteNode(Node n) {
        if(n == null || n.next == null) {
            return false;
        }
        n.value = n.next.value;
        n.next = n.next.next;
        return true;
    }

    public static void main(String[] args) {
        DeleteNode l = new DeleteNode();

        l.insert(1);
        l.insert(2);
        l.insert(3);
        l.insert(4);
        l.retrieve();
        // 1 -> 2 -> 3 -> 4

        l.deleteNode(l.get(2));
        l.retrieve();
        // 1 -> 3 -> 4
    }

}


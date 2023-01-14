public class Partition extends LinkedList {

    // x 값을 기준으로 노드를 재정렬하는 메서드
    // O(N)
    Node partition(Node n, int x) {
        Node head = n;
        Node tail = n;

        while(n != null) {
            Node next = n.next;
            if(n.value < x) {
                n.next = head;
                head = n;
            } else {
                tail.next = n;
                tail = n;
            }
            n = next;
        }
        tail.next = null;

        return head;
    }

    public static void main(String[] args) {
        Partition l = new Partition();
        l.insert(8);
        l.insert(5);
        l.insert(2);
        l.insert(7);
        l.insert(3);
        l.retrieve();

        Node n = l.partition(l.head.next,5);
        while(n.next != null){
            System.out.print(n.value + " -> ");
            n = n.next;
        }
        System.out.println(n.value);
    }
}


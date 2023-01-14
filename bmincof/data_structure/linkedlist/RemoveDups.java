import java.util.HashSet;
import java.util.Set;

public class RemoveDups {

    public static void main(String[] args) {
        LinkedListA myList = new LinkedListA();

        myList.insert(1);
        myList.insert(4);
        myList.insert(4);
        myList.insert(3);
        myList.insert(3);
        myList.retrieve();
        // 1 -> 4 -> 4 -> 3 -> 3

        myList.removeDupsWithBuffer();
//        myList.removeDupsWithoutBuffer();
        myList.retrieve();
        // 1 -> 4 -> 3

    }
    
}

/*
 * LinkedList 에 removeDups 메서드 추가
 * */
class LinkedListA {

    Node head;

    public LinkedListA() {
        this.head = new Node();
    }

    static class Node {
        int value;
        Node next;

    }

    // O(N)
    void insert(int value) {

        Node toAdd = new Node();
        toAdd.value = value;

        Node cur = head;

        // 마지막 node 까지 이동
        while(cur.next != null) {
            cur = cur.next;
        }

        cur.next = toAdd;
    }

    // O(N)
    // 해당 로직으로는 맨 앞의 노드를 삭제할 수 없음 (head node 를 만들어서 해결)
    void remove(int value) {

        Node cur = head;

        // 마지막 node 까지 이동하면서 삭제할 값 찾기
        while(cur.next != null) {
            if(cur.next.value == value) {
                // 지우려는 값이 있는 노드 삭제 (가비지컬렉터가 반환)
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    // O(N)
    void retrieve() {

        Node cur = head.next;

        while(cur.next != null) {
            System.out.print(cur.value + " -> ");
            cur = cur.next;
        }
        System.out.println(cur.value);

    }

    // space O(N)
    // time O(N)
    void removeDupsWithBuffer() {
        Set<Integer> buf = new HashSet<>();
        Node cur = head;

        while(cur.next != null) {

            if(buf.contains(cur.next.value)) {
                cur.next = cur.next.next;
            } else {
                buf.add(cur.next.value);
                cur = cur.next;
            }

        }

    }

    // space O(1)
    // time O(N^2)
    void removeDupsWithoutBuffer() {
        Node ref = head.next;

        while(ref != null && ref.next != null) {
            Node cur = ref;

            while(cur.next != null) {
                if(ref.value == cur.next.value) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            ref = ref.next;
        }


    }

}

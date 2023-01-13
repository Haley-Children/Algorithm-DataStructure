/*
 * 단방향 연결리스트의 간단한 구현 (삽입, 삭제, 탐색)
 * */

public class LinkedList {

    Node head;

    public LinkedList() {
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

}

class TestSinglyLinkedList {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList();

        myList.insert(1);
        myList.insert(4);
        myList.insert(2);
        myList.insert(3);
        myList.retrieve();
        // 1 -> 4 -> 2 -> 3

        myList.remove(1);
        myList.remove(2);
        myList.remove(3);
        myList.retrieve();
        // 4
    }
}

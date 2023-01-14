public class KthNodeFromTail {

    public static void main(String[] args) {
        LinkedListB l = new LinkedListB();

        l.insert(1);
        l.insert(2);
        l.insert(3);
        l.insert(4);

        System.out.println(l.KthNodeFromTail1(1));
        // 4
        Reference r = new Reference();
        System.out.println(l.KthNodeFromTail2(l.head,3, r).value);
        // 2
        System.out.println(l.KthNodeFromTail3(3));
        // 2

    }
}

/*
 * LinkedList 에 KthNodeFromTail 추가
 * 리스트의 맨 뒤에서 k번 째에 있는 값 출력하는 메서드 (맨 뒤 == k : 1)
 * */
class LinkedListB extends LinkedList{

    // O(N)
    int KthNodeFromTail1(int k) {
        Node cur = head;
        int total = 0;

        while(cur.next != null) {
            cur = cur.next;
            total++;
        }

        cur = head;
        for (int i = 0; i < total - k; i++) {
            cur = cur.next;
        }

        return cur.next.value;
    }

    // O(N)
    Node KthNodeFromTail2(Node n, int k, Reference count) {
        if(n == null) {
            return null;
        }

        Node found = KthNodeFromTail2(n.next, k, count);
        count.count++;
        if(count.count == k) return n;

        return found;
    }

    // two pointer
    // O(N)
    int KthNodeFromTail3(int k) {
        Node kth = head;
        Node tail = head;

        for (int i = 0; i < k; i++) {
            tail = tail.next;
        }

        while(tail != null) {
            tail = tail.next;
            kth = kth.next;
        }

        return kth.value;
    }

}

class Reference {
    int count;
}


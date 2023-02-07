public class FindLoop extends Palindrome {

    // 리스트의 루프 시작점을 반환하는 메서드
    // O(루프 크기?)
    private static Node findLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) break;
        }

        if(fast == null || fast.next == null) return null;

        // 핵심 부분, 증명 필요
        slow = head;

        while(fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = n1.addNext(2);
        Node n3 = n2.addNext(3);
        Node n4 = n3.addNext(4);
        Node n5 = n4.addNext(5);
        Node n6 = n5.addNext(6);
        Node n7 = n6.addNext(7);
        Node n8 = n7.addNext(8);
        n8.addNext(n6);
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        //                          ^       |
        //                          | ------

        Node n = findLoop(n1);

        if(n != null) {
            System.out.println("Start of the Loop : "+n.value);
        } else {
            System.out.println("Not Found");
        }

        Node nn1 = new Node(1);
        Node nn2 = nn1.addNext(2);
        Node nn3 = nn2.addNext(3);
        Node nn4 = nn3.addNext(4);
        Node nn5 = nn4.addNext(5);
        Node nn6 = nn5.addNext(6);
        Node nn7 = nn6.addNext(7);
        Node nn8 = nn7.addNext(8);
        nn8.addNext(nn3);
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        //           ^                      |
        //           |----------------------


        Node nn = findLoop(nn1);

        if(nn != null) {
            System.out.println("Start of the Loop : "+nn.value);
        } else {
            System.out.println("Not Found");
        }

    }

}


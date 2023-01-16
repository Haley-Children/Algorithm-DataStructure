import java.util.Stack;

public class Palindrome extends Intersection {

    // 역순으로 배열된 복사본을 만들어 회문인지 검사하는 메서드
    // O(N)
    private static boolean isPalidrome(Node head) {
        Node reversed = getReversed(head);
        return isEqaul(head, reversed);
    }

    // 역순으로 배열된 복사본을 얻는 메서드
    // O(N)
    private static Node getReversed(Node head) {
        Node cur = head;
        Node result = null;

        while(cur != null) {
            Node n = new Node(cur.value);
            n.next = result;
            result = n;

            cur = cur.next;
        }

        return result;

    }

    // 두 리스트가 같은지 검사하는 메서드
    // O(N)
    private static boolean isEqaul(Node l1, Node l2) {
        while (l1 != null && l2 != null) {
            if(l1.value != l2.value) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        // 길이가 같은지
        return l1 == null && l2 == null;
    }

    // 투 포인터를 사용하여 회문인지 검사하는 메서드
    // O(N)
    private static boolean isPalindromeTwoPointer(Node head) {
        Node fast = head;
        Node slow = head;

        Stack<Integer> stack = new Stack<>();
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            stack.push(slow.value);
            slow = slow.next;
        }

        // == 리스트가 홀수이면
        if(fast != null) slow = slow.next;

        while(slow != null) {
            if(slow.value != stack.pop()) return false;
            slow = slow.next;
        }
        return true;
    }

    // 재귀 방식으로 회문인지 반환하는 메서드
    // O(N)
    private static boolean isPalindromeRecursive(Node head) {
        int length = getLength(head);
        Storage storage = checkPalindrome(head, length);
        return storage.result;
    }

    // 재귀 방식으로 리스트가 회문인지 검사하는 메서드
    // O(N)
    private static Storage checkPalindrome(Node head, int length) {
        // 절반 왔을 떄
        if (length <= 0) {
            return new Storage(head, true);
        } else if (length == 1) {
            // 홀수일 때
            return new Storage(head.next, true);
        }

        Storage storage = checkPalindrome(head.next, length - 2);

        if (!storage.result || storage.node == null) {
            return storage;
        }
        // storage 는 앞으로 head 는 뒤로 되돌아가면서 비교
        storage.result = (head.value == storage.node.value);
        storage.node = storage.node.next;

        return storage;
    }



    static class Storage {
        public Node node;
        public boolean result;

        public Storage(Node n, boolean r) {
            this.node = n;
            this.result = r;
        }
    }

    public static void main(String[] args) {
        Palindrome l = new Palindrome();
        l.insert('m');
        l.insert('a');
        l.insert('d');
        l.insert('a');
        l.insert('m');
//        l.insert('m');
        l.retrieve();
        // 109 -> 97 -> 100 -> 97 -> 109 (-> 109)

        System.out.println(isPalidrome(l.get(1)));
        // true(false)

        System.out.println(isPalindromeTwoPointer(l.get(1)));
        // true(false)

        System.out.println(isPalindromeRecursive(l.get(1)));
        // true(false)
    }

}


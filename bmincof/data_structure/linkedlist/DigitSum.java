public class DigitSum extends DeleteNode {

    // 자연수를 역순으로 저장한 두 개의 리스트를 더한 값을 출력해주는 메서드
    // O(N)
    static Node reverseSumLists(Node l1, Node l2, int carry) {
        if(l1 == null && l2 == null && carry == 0) {
            return null;
        }

        Node result = new Node();
        int value = carry;
        if (l1 != null) value += l1.value;
        if (l2 != null) value += l2.value;

        result.value = value % 10;
        if (l1 != null || l2 != null) {
            result.next = reverseSumLists(l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next,
                    value / 10);
        }

        return result;

    }

    // 리스트로 표현된 두 자연수를 더한 결과를 출력하는 메서드
    // O(N)
    static Node sumLists(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 < len2) {
            l1 = padBeforeWithZero(l1, len2 - len1);
        } else {
            l2 = padBeforeWithZero(l2, len1 - len2);
        }

        Carriage carriage = addLists(l1, l2);
        if (carriage.carry != 0) {
            carriage.result = insertBefore(carriage.result, carriage.carry);
        }

        return carriage.result;
    }

    // 리스트로 표현된 두 자연수의 더한 값을 반환하는 메서드
    private static Carriage addLists(Node l1, Node l2) {
        if(l1 == null && l2 == null) {
            return new Carriage();
        }
        Carriage carriage = addLists(l1.next, l2.next);
        int sum = carriage.carry + l1.value + l2.value;

        carriage.result = insertBefore(carriage.result, sum % 10);
        carriage.carry = sum / 10;

        return carriage;
    }

    // 리스트의 길이를 반환하는 메서드
    // O(N)
    static int getLength(Node l) {
        int len = 0;
        while(l != null) {
            len++;
            l = l.next;
        }
        return len;
    }

    // node 의 앞에 value를 가진 노드를 추가하는 메서드
    // O(1)
    private static Node insertBefore(Node node, int value) {
        Node before = new Node();
        before.value = value;

        if(node != null) {
            before.next = node;
        }
        return before;
    }

    // length 만큼 l 앞에 값이 0 인 노드를 채워주는 메서드
    // O(length)
    private static Node padBeforeWithZero(Node l, int length) {
        Node head = l;
        for (int i = 0; i < length; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    private static class Carriage {
        int carry;
        Node result;
    }

    public static void main(String[] args) {
        DigitSum l1 = new DigitSum();
        l1.insert(2);
        l1.insert(5);
        l1.insert(8);
        l1.retrieve();
        // 2 -> 5 -> 8 == 852

        DigitSum l2 = new DigitSum();
        l2.insert(5);
        l2.insert(9);
        l2.insert(3);
        l2.retrieve();
        // 5 -> 9 -> 3 == 395

        Node n = reverseSumLists(l1.get(1), l2.get(1), 0);
        while (n.next != null) {
            System.out.print(n.value + " -> ");
            n = n.next;
        }
        System.out.println(n.value);
        // 7 -> 4 -> 2 -> 1 == 1247 == 852 + 395

        n = sumLists(l1.get(1), l2.get(1));
        while (n.next != null) {
            System.out.print(n.value + " -> ");
            n = n.next;
        }
        System.out.println(n.value);
        // 8 -> 5 -> 1 == 851 == 258 + 593

    }

}


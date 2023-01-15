public class Intersection extends DigitSum {

    // 두 개의 리스트가 공통으로 갖는 교차점을 반환하는 메서드
    // O(N)
    private static Node getIntersection(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 > len2) {
            l1 = l1.get(len1 - len2);
        } else {
            l2 = l2.get(len2 - len1);
        }

        while (l1 != null && l2 != null) {
            if (l1 == l2) {
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        return null;
    }

    public static void main(String[] args) {
        Node n1 = new Node(5);
        Node n2 = n1.addNext(7);
        Node n3 = n2.addNext(9);
        Node n4 = n3.addNext(10);
        Node n5 = n4.addNext(7);
        Node n6 = n5.addNext(6);

        Node m1 = new Node(6);
        Node m2 = m1.addNext(8);
        Node m3 = m2.addNext(n4);

        // 5 -> 7 -> 9 -> 10 -> 7 -> 6
        //                ^
        //      6 -> 8 -> |

        Node n = getIntersection(n1, m1);
        System.out.println(n.value);
        // 10
    }


}


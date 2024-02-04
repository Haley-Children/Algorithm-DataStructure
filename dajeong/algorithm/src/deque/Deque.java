package deque;

/**
 * 덱 구현해보기 (선형덱)
 */
public class Deque {

    public static int MX = 1000005; // 선형덱이므로 MX 크게 잡기
    public static int[] dat = new int[2 * MX + 1];
    public static int head = MX, tail = MX;

    private static void test() {
        push_back(30); // 30
        System.out.println(front()); //* 30
        System.out.println(back()); //* 30
        push_front(25); // 25 30
        push_back(12); // 25 30 12
        System.out.println(back()); //* 12
        push_back(62); // 25 30 12 62
        pop_front(); // 30 12 62
        System.out.println(front()); //* 30
        pop_front(); // 12 62
        System.out.println(back()); //* 62
    }


    // 앞 원소 삽입
    private static void push_front(int x) {
        if (head == 0) {
            System.out.println("원소 추가 불가 (선형덱/크기초과)");
        } else {
            dat[--head] = x;
        }
    }

    // 뒤 원소 삽입
    private static void push_back(int x) {
        if (tail == 2 * MX ) {
            System.out.println("원소 추가 불가 (선형덱/크기초과)");
        } else {
            dat[tail++] = x;
        }
    }

    // 빈 상태인지 확인
    private static boolean isEmpty() {
        return head == tail;
    }

    // 앞 원소 삭제
    private static void pop_front() {
        if (isEmpty()) {
            System.out.println("빈 덱이므로 원소 삭제 불가");
        } else {
            head++;
        }
    }

    // 뒤 원소 삭제
    private static void pop_back() {
        if (isEmpty()) {
            System.out.println("빈 덱이므로 원소 삭제 불가");
        } else {
            tail--;
        }
    }

    // 앞 원소 조회
    private static int front() {
        return !isEmpty() ? dat[head] : -1;
    }

    // 뒤 원소 조회
    private static int back() {
        return !isEmpty() ? dat[tail-1] : -1;
    }


    public static void main(String[] args) {
        test();
    }

}

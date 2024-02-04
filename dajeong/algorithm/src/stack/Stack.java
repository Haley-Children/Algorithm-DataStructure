package stack;

/**
 * 스택을 배열로 구현해보기
 */
public class Stack {

    public static int MX = 1000005;
    public static int[] dat = new int[MX]; // 스택 (배열)
    public static int pos = 0; // 스택의 길이 == 다음 원소가 들어올 인덱스 위치


    // 스택의 꼭대기에 위치한 원소의 값 리턴
    private static int top() {
        return dat[pos - 1];
    }

    // 스택 원소 제거
    private static void pop() {
        pos--;
    }

    // 스택에 원소 추가
    private static void push(int x) {
        dat[pos++] = x;
    }


    private static void test() {
        push(5);
        push(4);
        push(3);
        System.out.println(top());// 3
        pop();
        pop();
        System.out.println(top());// 5
        push(10);
        push(12);
        System.out.println(top());// 12
        pop();
        System.out.println(top());// 10
    }

    public static void main(String[] args) {
        test();
    }
}

package queue;

/**
 * 큐를 배열로 구현해보자
 */
public class Queue {

    public static int MX = 5;
    public static int[] dat = new int[MX];
    public static int front = 0, rear = 0;

    // 원소 추가
    private static void enQueue(int x) {
        if (isFull()) {
            System.out.println("큐가 가득차서 원소 추가 불가");
        } else {
            rear = (rear + 1) % MX;
            dat[rear] = x;
        }
    }

    // 원소 삭제
    private static void deQueue() {
        if (isEmpty()) {
            System.out.println("큐 원소가 없어서 삭제 불가");
        } else {
            front = (front + 1) % MX;
        }
    }

    // 앞 원소 확인
    private static int front() {
        return dat[front+1];
    }

    // 뒤 원소 확인
    private static int back() {
        return dat[rear];
    }

    // 공백 상태 확인
    private static boolean isEmpty() {
        return front == rear;
    }

    // 포화 상태 확인
    private static boolean isFull() {
        return (rear + 1) % MX == front;
    }


    private static void test() {
        enQueue(10);
        enQueue(20);
        enQueue(30);
        System.out.println(front());// 10
        System.out.println(back());// 30
        deQueue();
        deQueue();
        deQueue();
        deQueue();
        enQueue(15);
        enQueue(25);
        enQueue(45);
        System.out.println(front());// 15
        System.out.println(back());// 45
    }

    public static void main(String[] args) {
        test();
    }

}

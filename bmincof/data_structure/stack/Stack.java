import java.util.EmptyStackException;

// LIFO (Last-In-First-Out)
// 4 가지 연산 (pop, push, peek, isEmpty) 구현
public class Stack<T> {

    private Node<T> top;

    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // 가장 위의 원소를 빼내고 해당 원소의 값을 반환하는 메서드
    // O(1)
    public T pop() {
        if(top == null) {
            throw new EmptyStackException();
        }

        T item = top.data;
        top = top.next;
        return item;
    }

    // 스택의 가장 위에 새로운 원소를 추가하는 메서드
    // O(1)
    public void push(T item) {
        Node<T> t = new Node<T>(item);
        t.next = top;
        top = t;
    }

    // 스택의 가장 위 원소의 값을 출력하는 메서드
    // O(1)
    public T peek() {
        if(top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // 스택이 비어있는지 확인
    // O(1)
    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        // 1 -> 2 -> 3 -> 4 (top)
        System.out.println(s.pop());
        // 4
        System.out.println(s.pop());
        // 3
        System.out.println(s.peek());
        // 2
        System.out.println(s.pop());
        // 2
        System.out.println(s.isEmpty());
        // false
        System.out.println(s.pop());
        // 1
        System.out.println(s.isEmpty());
        // true
    }

}


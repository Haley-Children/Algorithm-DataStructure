import java.util.NoSuchElementException;

// FIFO (First-In-First-Out)
// 4 가지 연산 (add, remove, peek, isEmpty) 구현
public class Queue<T> {
    private Node<T> first;
    private Node<T> last;

    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // 큐의 가장 뒤에 새로운 원소를 추가하는 메서드
    // O(1)

    public void add(T item) {
        Node<T> t = new Node<>(item);

        // 마지막 노드가 있다면
        if(last != null) {
            last.next = t;
        }
        last = t;

        // 데이터가 없었다면
        if (first == null) {
            first = last;
        }
    }

    // 큐의 가장 앞에 있는 원소를 빼내고 해당 원소의 값을 반환하는 메서드
    // O(1)
    public T remove() {
        if(first == null) {
            throw new NoSuchElementException();
        }

        T data = first.data;
        first = first.next;

        if(first == null) {
            last = null;
        }

        return data;
    }

    // 큐의 가장 앞에 있는 원소를 반환하는 메서드
    // O(1)
    public T peek() {
        if(first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    // 큐가 비어있는지 확인
    // O(1)
    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        // (first) 1 <- 2 <- 3 <- 4 (last)
        System.out.println(q.remove());
        // 1
        System.out.println(q.remove());
        // 2
        System.out.println(q.peek());
        // 3
        System.out.println(q.remove());
        // 3
        System.out.println(q.isEmpty());
        // false
        System.out.println(q.remove());
        // 4
        System.out.println(q.isEmpty());
        // true
    }

}


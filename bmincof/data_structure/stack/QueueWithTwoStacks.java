import java.util.Stack;

// 스택 2개로 큐를 구현하는 문제
// 새로운 값들을 저장할 new 스택과 peek이나 pop 처럼 출력을 요구할때 사용할 old 스택 두 가지를 이용
// 출력이 필요할 때 old가 비었다면 new의 모든 값을 pop해서 old로 push
public class QueueWithTwoStacks {
    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();

        q.add(1);
        q.add(2);
        q.add(3);
        // (head) 1 <- 2 <- 3
        System.out.println(q.pop());
        // 1
        System.out.println(q.pop());
        // 2
        // (head) 3
        q.add(4);
        // (head) 3 <- 4
        System.out.println(q.pop());
        // 3
        System.out.println(q.pop());
        // 4
    }

}

class MyQueue<T> {
    Stack<T> stackNewest, stackOldest;

    MyQueue() {
        stackNewest = new Stack<T>();
        stackOldest = new Stack<T>();
    }

    // 출력을 위해 값들을 push해 둔 oldest stack과 값을 입력받은 newest stack의 사이즈를 더해야 함
    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    // 데이터를 push할 때는 newest에 더하면 됨
    public void add(T value) {
        stackNewest.push(value);
    }

    // pop할 때는 oldest가 비었을 때만 옮겨줘야 데이터가 안 섞임
    // O(k), k : newest의 size
    private void shiftStacks() {
        if(stackOldest.isEmpty()) {
            while(!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    // 최악의 경우 O(k)
    public T peek() {
        shiftStacks();
        return stackOldest.peek();
    }

    // 최악의 경우 O(k)
    public T pop() {
        shiftStacks();
        return stackOldest.pop();
    }

}


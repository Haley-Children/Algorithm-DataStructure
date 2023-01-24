// 스택의 최소값을 O(1)에 구하는 min()구현
public class StackWithMin {

    public static void main(String[] args) {
        StackWithMinNode stack = new StackWithMinNode();
        stack.push(3);
        stack.push(4);
        stack.push(4);
        stack.push(1);
        stack.push(2);
        // 3 4 4 1 2 (top)
        // 3 3 3 1 1 (min)

        System.out.println(stack.min());
        // 1

        StackWithMinStack stack2 = new StackWithMinStack();
        stack2.push(3);
        stack2.push(4);
        stack2.push(4);
        stack2.push(1);
        stack2.push(2);
        // 3 4 4 1 2 (top)
        // 3     1   (min)

        System.out.println(stack2.min());
        // 1
    }

}

// 해당 노드가 스택에 추가될 때의 최소값을 같이 저장하는 노드
class NodeWithMin {
    int value;
    int minimum;

    NodeWithMin(int value, int minimum) {
        this.value = value;
        this.minimum = minimum;
    }
}

// NodeWithMin을 이용해 Stack에서의 최소값을 O(1)에 찾을 수 있도록 한 Stack
class StackWithMinNode extends Stack<NodeWithMin> {

    // NodeWithMin에 최소값 정보가 있기 때문에 O(1)에 최소값을 구할 수 있다
    public int min() {
        if(this.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return this.peek().minimum;
    }

    // push할 때 현재 추가하려는 값이 새로운 최소인지 비교해서 노드에 저장해야 함.
    // O(1)
    public void push(int value) {
        int minimum = Math.min(value, min());
        super.push(new NodeWithMin(value, minimum));
    }

}

// 내부에 최소값 정보를 담고 있는 새로운 스택을 추가하여 최소값을 O(1)에 찾을 수 있도록 한 스택
class StackWithMinStack extends Stack<Integer> {
    Stack<Integer> minStack;

    public StackWithMinStack() {
        minStack = new Stack<>();
    }

    // 최소값을 저장한 스택의 top을 반환
    // O(1)
    public int min() {
        if(minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }

    // 새로운 값을 저장할 때 현재 최소값보다 더 작거나 같은 값이면 최소값 스택에 저장
    // O(1)
    public void push(int value) {
        if(value <= min()) {
            minStack.push(value);
        }
        super.push(value);
    }

    // pop하는 값이 현재의 최소값과 같다면 최소값 스택에서 pop
    // O(1)
    @Override
    public Integer pop() {
        int value = super.pop();
        if(value == min()) {
            minStack.pop();
        }
        return value;
    }

}


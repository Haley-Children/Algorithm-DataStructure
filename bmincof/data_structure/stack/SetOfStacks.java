import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;


// Stack에 용량 한계가 정해져있어서 데이터의 수가 스택의 용량을 초과할 경우
// 추가적인 스택을 생성하여 이어 담는 형태를 구현해보는 문제
public class SetOfStacks {

    int capacity;
    ArrayList<Stack<Integer>> stacks = new ArrayList<>();

    SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    // 이어 붙인 스택들 중 마지막 스택을 불러오는 메서드
    public Stack<Integer> getLastStack() {
        if(stacks.isEmpty()) return null;
        return stacks.get(stacks.size() - 1);
    }

    // 새로운 스택을 추가하는 메서드
    public void addStack() {
        stacks.add(new Stack<>());
    }

    // 마지막 스택을 제거하는 메서드
    public void removeLastStack() {
        stacks.remove(stacks.size() - 1);
    }

    public void push(int data) {
        Stack<Integer> last = getLastStack();
        // 마지막 스택의 용량이 다 찼다면 새로운 스택을 추가해야 함
        if(last == null || last.size() == capacity) {
            addStack();
            last = getLastStack();
        }
        last.push(data);
    }

    public int pop() {
        Stack<Integer> last = getLastStack();
        if (last == null || last.isEmpty()) {
            throw new EmptyStackException();
        }

        int data = last.pop();
        // pop 한 뒤 마지막 스택이 비면 마지막 스택을 비워야 함
        if(last.isEmpty()) {
            removeLastStack();
        }
        return data;
    }

}

// 임의의 스택에서 pop 하는 popAt 메서드 구현
// top, bottom 를 갖는 stack 과 below, above 포인터를 가지는 노드를 통해 O(1)으로 구현
// 해당 노드를 사용하지 않으면 시간복잡도가 증가함 O(kN)
class StackWithBottomPointer<E> {

    int capacity;   // 각 스택의 최대 용량
    int size;       // 총 스택의 개수
    Node top;
    Node bottom;

    class Node {
        E data;
        Node below;
        Node above;
        Node (E data) {
            this.data = data;
        }
    }

    StackWithBottomPointer(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void push(E d) throws FullStackException {
        if(isFull()) throw new FullStackException();

        Node n = new Node(d);
        push(n);
    }

    public void push(Node n) throws FullStackException {
        if(isFull()) throw new FullStackException();

        // 새로운 노드이면 자기 자신이 top 이면서 bottom
        if(isEmpty()) {
            top = n;
            bottom = n;
        } else {
            top.above = n;
            n.below = top;
            top = n;
        }
        size++;
    }

    public E pop() {
        if(isEmpty()) throw new EmptyStackException();
        E data = top.data;
        top = top.below;
        if(top == null) {
            bottom = null;
        } else {
            top.above = null;
        }
        size--;
        return data;
    }

    // 스택 밑에서 빼오는 메서드
    public Node popBottom() {
        if(isEmpty()) throw new EmptyStackException();
        Node n = bottom;
        bottom = bottom.above;
        if(bottom == null) {    // 스택에 데이터가 없는 경우
            top = null;
        } else {
            bottom.below = null;
        }
        size--;
        return n;
    }

}

class SetOfStacks2 {
    int capacity;
    ArrayList<StackWithBottomPointer<Integer>> stacks = new ArrayList<>();

    SetOfStacks2(int capacity) {
        this.capacity = capacity;
    }

    public void addStack() {
        stacks.add(new StackWithBottomPointer<>(capacity));
    }

    public void removeLastStack() {
        stacks.remove(stacks.size() - 1);
    }

    public StackWithBottomPointer<Integer> getLastStack() {
        if(stacks.isEmpty()) return null;
        return stacks.get(stacks.size() - 1);
    }

    public void push(int data) {
        StackWithBottomPointer<Integer> last = getLastStack();
        if(last == null || last.isFull()) {
            addStack();
            last = getLastStack();
        }
        try {
            last.push(data);
        } catch (FullStackException e) {

        }
    }

    public int pop() {
        StackWithBottomPointer<Integer> last = getLastStack();
        if(last == null || last.isEmpty()) throw new EmptyStackException();
        int data = last.pop();
        if(last.isEmpty()) removeLastStack();
        return data;
    }

    // index 번 째 스택의 top을 pop하는 메서드
    public int popAt(int index) {
        if (stacks.size() <= 0) {
            throw new EmptyStackException();
        }
        if(index < 0 || index >= stacks.size()) {
            throw new IndexOutOfBoundsException();
        }
        StackWithBottomPointer<Integer> stack = stacks.get(index);
        if(stack == null || stack.isEmpty()) throw new EmptyStackException();
        int data = stack.pop();
        shiftLeft(index);
        return data;
    }

    public void shiftLeft(int index) {
        if (index < stacks.size() - 1) {
            StackWithBottomPointer<Integer> right = stacks.get(index + 1);
            StackWithBottomPointer<Integer> left = stacks.get(index);
            try {
                left.push(right.popBottom());
            } catch (FullStackException e) {
                stacks.remove(index + 1);
            }
            shiftLeft(index + 1);
        }
    }

}

class Test {
    public static void main(String[] args) {
        SetOfStacks stack = new SetOfStacks(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        SetOfStacks2 s = new SetOfStacks2(3);

        s.push(1);
        s.push(2);
        s.push(3);

        s.push(4);
        s.push(5);
        s.push(6);

        s.push(7);

        System.out.println(s.popAt(0));
        System.out.println(s.popAt(1));
    }
}

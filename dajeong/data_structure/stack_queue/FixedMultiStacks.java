package stack_queue;

import java.util.EmptyStackException;

/*
 * 하나의 배열로 3개의 스택을 구현하시오.
 * 1) 쉬운 방법 : 고정 길이 스택으로 구현
 *  - 스택의 수와 각 스택의 데이터 수(사이즈)로 고정 길이 스택을 만든다.
 * 2) 어려운 방법: 유동 길이 스택으로 구현
 */
class FullStackException extends Exception {

    public FullStackException() {
        super();
    }

    public FullStackException(String message) {
        super(message);
    }
}
public class FixedMultiStacks {

    private int numOfStacks = 3;
    private int stackSize;
    private int[] values;
    private int[] sizes;

    public FixedMultiStacks(int stackSize) {
        this.stackSize = stackSize;
        this.sizes = new int[numOfStacks];
        this.values = new int[numOfStacks * stackSize];
    }

    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackSize;
    }

    public int getTopIndex(int stackNum) {
        int offset = stackSize * stackNum;
        int size = sizes[stackNum];
        return offset + size - 1; // 0부터 시작해서 -1
    }

    public void push(int stackNum, int data) throws FullStackException {
        if (isFull(stackNum)) {
            throw new FullStackException();
        }
        values[getTopIndex(stackNum) + 1] = data;
        sizes[stackNum]++;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        int top = getTopIndex(stackNum);
        int data = values[top];
        values[top] = 0;
        sizes[stackNum]--;
        return data;
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[getTopIndex(stackNum)];
    }

}
class Test3 {

    public static void main(String[] args) {
        FixedMultiStacks ms = new FixedMultiStacks(5);
        try {
            ms.push(0,1);
            ms.push(0,2);
            ms.push(0,3);
            ms.push(0,4);
            ms.push(0,5);
            //ms.push(0,6); - FullStackException()이 일어나는데 스택 마다 남은 공간을 유동적으로 사용할 수 있는 유동 길이 스택을 사용하는 것이 더 좋다.

            ms.push(1,11);
            ms.push(1,12);
            ms.push(1,13);
            ms.push(1,14);
            ms.push(1,15);

        }catch (FullStackException e) {
            System.out.println("It's full");
        }

        try {
            System.out.println("Stack #0: " + ms.pop(0));
            System.out.println("Stack #0: " + ms.pop(0));
            System.out.println("Stack #0: " + ms.peek(0));
            System.out.println("Stack #0: " + ms.pop(0));
            System.out.println("Stack #0: " + ms.isEmpty(0));
            System.out.println("Stack #0: " + ms.pop(0));
            System.out.println("Stack #0: " + ms.pop(0));
            System.out.println("Stack #0: " + ms.isEmpty(0));

            System.out.println("Stack #1: " + ms.pop(1));
            System.out.println("Stack #1: " + ms.pop(1));
            System.out.println("Stack #1: " + ms.peek(1));
            System.out.println("Stack #1: " + ms.pop(1));
            System.out.println("Stack #1: " + ms.isEmpty(1));
            System.out.println("Stack #1: " + ms.pop(1));
            System.out.println("Stack #1: " + ms.pop(1));
            System.out.println("Stack #1: " + ms.isEmpty(1));

        } catch (EmptyStackException e) {
            System.out.println("It's empty");
        }
    }
}

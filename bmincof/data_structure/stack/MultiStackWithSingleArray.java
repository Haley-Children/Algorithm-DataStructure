import java.util.EmptyStackException;

// 하나의 배열로 3개의 스택을 구현하기
public class MultiStackWithSingleArray {
    public static void main(String[] args) {
        checkFMS();
    }

    static void checkFMS() {
        FixedSizeMultiStack fms = new FixedSizeMultiStack(5);
        try {
            fms.push(0, 1);
            fms.push(0, 2);
            fms.push(0, 3);
            fms.push(0, 4);
            fms.push(0,5);

            fms.push(2, 4);
            fms.push(2, 6);
            fms.push(2, 8);
            fms.push(2, 10);
            fms.push(2, 12);
        } catch (FullStackException e) {
            System.out.println("It's full");
        }

        try {
            System.out.println("Stack #0: " + fms.pop(0));
            System.out.println("Stack #0: " + fms.pop(0));
            System.out.println("Stack #0: " + fms.pop(0));
            System.out.println("Stack #0: " + fms.isEmpty(0));
            System.out.println("Stack #0: " + fms.pop(0));
            System.out.println("Stack #0: " + fms.pop(0));

            System.out.println("Stack #1: " + fms.isEmpty(1));

            System.out.println("Stack #2: " + fms.pop(2));
            System.out.println("Stack #2: " + fms.pop(2));
            System.out.println("Stack #2: " + fms.pop(2));
            System.out.println("Stack #2: " + fms.pop(2));
            System.out.println("Stack #2: " + fms.pop(2));
        } catch (EmptyStackException e) {
            System.out.println("It's empty");
        }
    }
}

// stackSize 크기의 stack 3개를 고정된 크기로 생성
// 각 내부 스택에는 stackNum 으로 접근 가능
// 모든 연산은 O(1)
// Stack의 사이즈를 유동적으로 할 수 없다는 단점
class FixedSizeMultiStack {
    private int numOfStacks = 3;
    private int stackSize;          // 각 스택의 최대 크기
    private int[] values;           // 스택에 push 한 값을 저장하는 곳
    private int[] sizes;            // 각 스택마다 가지고 있는 데이터 개수

    public FixedSizeMultiStack(int stackSize) {
        this.stackSize = stackSize;
        this.sizes = new int[numOfStacks];
        this.values = new int[numOfStacks * stackSize];
    }

    // stackNum 번 스택이 비었는지 확인
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    // stackNum 번 스택이 모두 찼는지 확인
    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackSize;
    }

    // stackNum 번 스택의 top 인덱스를 반환
    public int getTopIndex(int stackNum) {
        int offset = stackSize * stackNum;      // stackNum 번 스택의 시작인덱스
        int size = sizes[stackNum];             // stackNum 번 스택의 데이터 개수
        return offset + size - 1;               // 스택 번호는 0 부터 시작
    }

    // stackNum 번 스택에 값을 push
    public void push(int stackNum, int data) throws FullStackException {
        if (isFull(stackNum)) {
            throw new FullStackException();
        }
        values[getTopIndex(stackNum) + 1] = data;
        sizes[stackNum]++;
    }

    // stackNum 번 스택의 값을 pop
    public int pop(int stackNum) {
        if(isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        int top = getTopIndex(stackNum);
        int data = values[top];
        values[top] = 0;
        sizes[stackNum]--;
        return data;
    }

    // stackNum 번 스택의 top을 반환
    public int peek(int stackNum) {
        if(isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[getTopIndex(stackNum)];
    }

}

class FullStackException extends Exception {
    public FullStackException() {
    }

    public FullStackException(String message) {
        super(message);
    }
}

// 배열을 원형 리스트와 같은 방식으로 이용하여 MultiStack 구현
// stackNum 번의 스택에 더 큰 공간이 필요하면 양 옆으로 공간을 추가 확보
class MultiStack {

    // 스택 시작위치나 데이터 크기 등 저장
    private class StackInfo {
        public int start, dataSize, stackSize;
        public StackInfo(int start, int stackSize) {
            this.start = start;
            this.stackSize = stackSize;
            this.dataSize = 0;
        }

        public boolean isWithinStack(int index) {
            if (index < 0 || index >= values.length) {
                return false;
            }
            int virtualIndex = index < start ? index + values.length : index;
            int end = start + stackSize;
            return start <= virtualIndex && virtualIndex < end;
        }

        public int getLastStackIndex() {
            return adjustIndex(start + stackSize - 1);
        }

        public int getLastDataIndex() {
            return adjustIndex(start + dataSize - 1);
        }

        public int getNewDataIndex() {
            return adjustIndex(getLastDataIndex() + 1);
        }

        public boolean isFull() {
            return dataSize == stackSize;
        }

        public boolean isEmpty() {
            return dataSize == 0;
        }
    }

    private StackInfo[] info;
    private int[] values;

    public MultiStack(int numOfStacks, int defaultSize) {
        info = new StackInfo[numOfStacks];
        for (int i = 0; i < numOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numOfStacks * defaultSize];
    }

    private void expand(int stackNum) {
        int nextStack = (stackNum + 1) % info.length;
        shift(nextStack);
        info[stackNum].stackSize++;
    }

    private void shift(int stackNum) {
        StackInfo stack = info[stackNum];
        if(stack.dataSize >= stack.stackSize) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.stackSize++;
        }
        int index = stack.getLastStackIndex();
        while(stack.isWithinStack(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }
        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        stack.stackSize--;
    }

    public int numberOfElements() {
        int totalDataSize = 0;
        for (StackInfo sd : info) {
            totalDataSize += sd.dataSize;
        }
        return totalDataSize;
    }

    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    // 입력받은 인덱스를 올바른 위치로 재조정 해주는 메서드
    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }

    // 입력받은 인덱스를 다음 인덱스로 변경해주는 메서드
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    // 입력받은 인덱스를 이전 인덱스로 변경해주는 메서드
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

}


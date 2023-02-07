// 배열로 Multi Stack 구현하기 in Java
// https://youtu.be/lnVhHd0hheU
import java.util.EmptyStackException;
import java.lang.Exception;

class FullStackException extends Exception{
	public FullStackException() {
		super();
	}
	public FullStackException (String msg) {
		super(msg);
	}
}

// easy solution 고정길이스택
class FixedMultiStacks{
	private int numOfStacks = 3; // 스택 개수
	private int stackSize; // 한 스택의 사이즈
	private int[] values; // 스택이 저장 될 배열
	private int[] sizes; // 각 스택의 크기를 저장할 배열
	
	public FixedMultiStacks (int stackSize) { // 생성자
		this.stackSize = stackSize; // 각 스택의 사이즈
		this.sizes = new int[numOfStacks]; // 각 스택의 크기
		this.values = new int[numOfStacks * stackSize]; // 전체 스택
	}
	
	public boolean isEmpty(int stackNum) {
		return sizes[stackNum] == 0; // 특정 스택의 크기가 0인지에 따라 반환
	}
	
	public boolean isFull(int stackNum) {
		return sizes[stackNum] == stackSize; // 특정 스택의 크기가 최대 크기인지에 따라 반환
	}
	
	public int getTopIndex(int stackNum) {
		int offset = stackSize * stackNum; // 특정 스택의 맨 앞 인덱스
		int size = sizes[stackNum]; // 특정 스택에 들어있는 데이터 개수
		return offset + size - 1; // 특정 스택 top의 인덱스
	}
	
	public void push(int stackNum, int data) throws FullStackException {
		if (isFull(stackNum)) {
			throw new FullStackException();
		}
		values[getTopIndex(stackNum) + 1] = data;
		sizes[stackNum]++;
	}
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
	public int peek(int stackNum) {
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		return values[getTopIndex(stackNum)];
	}
}

// hard solution 유동길이스택
class FlexibleMultiStacks{
	private class StackInfo { // 각 스택의 정보를 개별적으로 다루기 위한 클래스
		public int start, dataSize, stackSize; // 스택 시작 위치, 데이터 크기, 스택 총 크기
		public StackInfo(int start, int stackSize) {
			this.start = start;
			this.stackSize = stackSize;
			this.dataSize = 0;
		}
		public boolean isWithinStack(int index) { // 특정 배열 방 번호가 스택 내부에 있는지 확인
			if (index < 0 || index >= values.length) { // 멀티 스택 전체의 인덱스를 벗어나는 경우
				return false;
			}
			int virtualIndex = index < start? index + values.length : index;
			int end = start + stackSize;
			return start <= virtualIndex && virtualIndex < end;
		}
		public int getLastStackIndex() { // 스택의 마지막 방 번호를 가져오는 메서드 (데이터 유무와 무관)
			return adjustIndex(start + stackSize - 1);
		}
		public int getLastDataIndex() { // 스택에 들어가있는 데이터의 마지막 방 번호를 가져오는 메서드
			return adjustIndex(start + dataSize - 1);
		}
		public int getNewDataIndex() { // 새로운 데이터를 추가하고 싶을 때 데이터를 추가할 방 번호를 가져오는 메서드
			return adjustIndex(getLastDataIndex() + 1);
		}
		public boolean isFull() {
			return dataSize == stackSize;
		}
		public boolean isEmpty() {
			return dataSize == 0;
		}
	}
	
	private StackInfo[] info; // 스택 정보 저장할 배열
	private int[] values; // 실제 데이터가 들어갈 전체 배열
	
	public FlexibleMultiStacks(int numOfStacks, int defaultSize) { // 생성자 (스택 개수, 스택 크기)
		info = new StackInfo[numOfStacks]; // 스택 개수만큼 스택 정보 배열의 크기 설정
		for (int i=0; i<numOfStacks; i++) {
			info[i] = new StackInfo(defaultSize * i, defaultSize); // 인자: 스택시작점, 스택크기
		}
		values = new int[numOfStacks * defaultSize]; // 전체 데이터 배열 크기 선언
	}
	private void expand(int stackNum) { // 자리가 없을 때 shift를 호출 하고 마무리 작업하는 메서드
		int nextStack = (stackNum + 1) % info.length; // 다음 스택 번호
		shift(nextStack); // shift로 다음 스택에서 자리 만들기
		info[stackNum].stackSize++; // 현재 스택의 사이즈 증가
	}
	private void shift(int stackNum) {
		StackInfo stack = info[stackNum]; // 스택 정보
		if (stack.dataSize >= stack.stackSize) { // 만약에 다 찼으면 재귀적으로 다음 스택 shift 호출
			int nextStack = (stackNum + 1) % info.length;
			shift(nextStack);
			stack.stackSize++;
		} // 자리가 남는 스택을 만나면 위의 재귀에서 탈출
		int index = stack.getLastStackIndex(); // 자리가 남는 스택의 마지막 인덱스를 가져옴
		while (stack.isWithinStack(index)) { // 인덱스가 스택 영역에 있는 동안 while문 반복
			values[index] = values[previousIndex(index)]; // 인덱스 앞의 데이터를 인덱스 위치로 이동
			index = previousIndex(index); // 인덱스를 앞으로 한칸 이동
		}
		values[stack.start] = 0; // 맨 앞의 데이터가 옮기고 나서도 남으므로 초기화
		stack.start = nextIndex(stack.start); // 시작점을 뒤로 한칸 미뤄서 앞 스택에 자리를 넘겨줌
		stack.stackSize--; // 시작점을 뒤로 당겼으니 사이즈도 1 감소
	}
	public int numberOfElements() { // 전체 배열의 데이터가 몇개인지 확인하는 메서드
		int totalDataSize = 0;
		for (StackInfo sd : info) { // 스택 인포를 전부 돌면서
			totalDataSize += sd.dataSize; // 데이터 사이즈를 합하여 계산
		}
		return totalDataSize;
	}
	public boolean allStacksAreFull() {
		return numberOfElements() == values.length; // 전체 데이터 개수와 전체 데이터 배열 크기를 비교
	}
	private int adjustIndex(int index) { // 가상 인덱스를 실제 인덱스로 조정하는 메서드
		int max = values.length;
		return ((index % max) + max) % max; // index가 음수일때를 고려해 (index%max)에 max를 더함
	}										// index가 양수일때를 고려해 이후 %max를 한 번 더 진행
	private int nextIndex(int index) {
		return adjustIndex(index + 1);
	}
	private int previousIndex(int index) {
		return adjustIndex(index - 1);
	}
	public void push(int stackNum, int value) throws FullStackException{
		if (allStacksAreFull()) { // 모든 스택이 찬 경우
			throw new FullStackException();
		}
		StackInfo stack = info[stackNum]; // 데이터를 넣으려는 스택의 정보
		if (stack.isFull()) { // 데이터를 넣으려는 스택이 찬 경우
			expand(stackNum); // 자리 만드는 메서드 호출
		}
		values[stack.getNewDataIndex()] = value; // 새로운 데이터를 저장
		stack.dataSize++; // 스택의 데이터 사이즈 증가
	}
	public int pop (int stackNum) {
		StackInfo stack = info[stackNum]; // 데이터를 뽑을 스택의 정보
		if (stack.isEmpty()) { // 해당 스택이 빈 경우
			throw new EmptyStackException();
		}
		int last = stack.getLastDataIndex();
		int value = values[last]; // 스택의 마지막 데이터 추출
		values[last] = 0; // 마지막 데이터 초기화
		stack.dataSize--; // 스택의 데이터 사이즈 감소
		return value;
	}
	public int peek (int stackNum) {
		StackInfo stack = info[stackNum]; // 데이터를 뽑을 스택의 정보
		if (stack.isEmpty()) { // 해당 스택이 빈 경우
			throw new EmptyStackException();
		}
		return values[stack.getLastDataIndex()];
	}
}

public class MultiStacks {
	public static void main(String[] args) {
		FixedMultiStacks ms1 = new FixedMultiStacks(5);
		try {
			ms1.push(0, 1);
			ms1.push(0, 2);
			ms1.push(0, 3);
			ms1.push(0, 4);
			ms1.push(0, 5);
//			ms1.push(0, 6);										// It's full
			
			ms1.push(1, 11);
			ms1.push(1, 12);
			ms1.push(1, 13);
			ms1.push(1, 14);
			ms1.push(1, 15);
		} catch(FullStackException e) {
			System.out.println("It's full");
		}
		
		try {
			System.out.println("Stack #0: " + ms1.pop(0));		// 5
			System.out.println("Stack #0: " + ms1.pop(0));		// 4
			System.out.println("Stack #0: " + ms1.peek(0));		// 3
			System.out.println("Stack #0: " + ms1.pop(0));		// 3
			System.out.println("Stack #0: " + ms1.isEmpty(0));	// false
			System.out.println("Stack #0: " + ms1.pop(0));		// 2
			System.out.println("Stack #0: " + ms1.pop(0));		// 1
			System.out.println("Stack #0: " + ms1.isEmpty(0));	// true
			
			System.out.println("Stack #1: " + ms1.pop(1));		// 15
			System.out.println("Stack #1: " + ms1.pop(1));		// 14
			System.out.println("Stack #1: " + ms1.peek(1));		// 13
			System.out.println("Stack #1: " + ms1.pop(1));		// 13
			System.out.println("Stack #1: " + ms1.isEmpty(1));	// false
			System.out.println("Stack #1: " + ms1.pop(1));		// 12
			System.out.println("Stack #1: " + ms1.pop(1));		// 11
			System.out.println("Stack #1: " + ms1.isEmpty(1));	// true
		} catch(EmptyStackException e) {
			System.out.println("It's empty");
		}
		
		FlexibleMultiStacks ms2 = new FlexibleMultiStacks(3, 5);
		try {
			ms2.push(0, 1);
			ms2.push(0, 2);
			ms2.push(0, 3);
			ms2.push(0, 4);
			ms2.push(0, 5);
			ms2.push(0, 6);
			ms2.push(0, 7);
			ms2.push(0, 8);
			ms2.push(0, 9);
			
			ms2.push(1, 11);
			ms2.push(1, 12);
			ms2.push(1, 13);
			ms2.push(1, 14);
			ms2.push(1, 15);
		} catch(FullStackException e) {
			System.out.println("It's full");
		}
		
		try {
			System.out.println("Stack #0: " + ms2.pop(0));		// 9
			System.out.println("Stack #0: " + ms2.pop(0));		// 8
			System.out.println("Stack #0: " + ms2.pop(0));		// 7
			System.out.println("Stack #0: " + ms2.pop(0));		// 6
			System.out.println("Stack #0: " + ms2.pop(0));		// 5
			System.out.println("Stack #0: " + ms2.pop(0));		// 4
			System.out.println("Stack #0: " + ms2.pop(0));		// 3
			System.out.println("Stack #0: " + ms2.pop(0));		// 2
			System.out.println("Stack #0: " + ms2.pop(0));		// 1
			
			System.out.println("Stack #1: " + ms2.pop(1));		// 15
			System.out.println("Stack #1: " + ms2.pop(1));		// 14
			System.out.println("Stack #1: " + ms2.pop(1));		// 13
			System.out.println("Stack #1: " + ms2.pop(1));		// 12
			System.out.println("Stack #1: " + ms2.pop(1));		// 11
		} catch(EmptyStackException e) {
			System.out.println("It's empty");
		}
	}
}

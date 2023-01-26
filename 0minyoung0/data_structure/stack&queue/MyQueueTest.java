// 두개의 Stack으로 Queue 만들기
// https://youtu.be/t45d7CgDaDM
// 두개의 Stack으로 하나의 Queue를 구현하시오
import java.util.Stack;
// 새 데이터를 받을 스택과 오래된 데이터를 저장할 스택으로 나눔
// add를 할 때는 new스택에 저장
// peek이나 remove할때는 old스택에서 처리한다
// peek이나 remove할때 old 스택에 데이터가 없으면
// new 스택의 데이터를 pop해서 old 스택에 전부 부어준다
class MyQueue<T>{
	Stack<T> stackNewest, stackOldest;
	MyQueue(){
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();		
	}
	public int size() {
		return stackNewest.size() + stackOldest.size();
	}
	public void add(T value) {
		stackNewest.push(value);
	}
	private void shiftStacks() { // old 스택이 비었으면 new 스택에서 old 스택으로 데이터 붓기
		if (stackOldest.isEmpty()) { // old 스택이 비어있을때 시행해야만 데이터 순서가 꼬이지 않음
			while(!stackNewest.isEmpty()) { // new 스택이 빌때까지 데이터를 old 스택에 부어주기
				stackOldest.push(stackNewest.pop());
			}
		}
	}
	public T peek() {
		shiftStacks();
		return stackOldest.peek();
	}
	public T remove() {
		shiftStacks();
		return stackOldest.pop();
	}
}

public class MyQueueTest {
	public static void main(String[] args) {
		MyQueue<Integer> q = new MyQueue<>();
		q.add(1);
		q.add(2);
		q.add(3);
		System.out.println(q.remove());	// 1
		System.out.println(q.remove()); // 2
		System.out.println(q.remove()); // 3
	}
}

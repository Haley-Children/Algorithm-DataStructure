// Set of Stacks
// https://youtu.be/P_c_W5cZWwU
// Stack에 데이터를 쌓다가 어느 지점에 이르면 새로운 스택에
// 저장하는 식으로 SetOfStacks을 구현하시오
// 내부적으로는 여러 개 스택으로 나뉘지만, push와 pop은
// 여전히 하나의 Stack인것처럼 동작해야함
// 추가적으로, 세트 중 하나의 Stack을 지정해서
// 데이터를 꺼내올 수 있는 popAt() 함수를 만드시오
import java.util.EmptyStackException;
import java.util.ArrayList;
import java.lang.Exception;

class FullStackException extends Exception{
	FullStackException(){
		super();
	}
}
class EmptyStackSetException extends Exception{
	EmptyStackSetException(){
		super();
	}
}

class Stack<E>{
	class Node{ // 스택 안에 노드를 만들어서 위 아래 노드를 저장하도록 함
		E data;
		Node below;
		Node above;
		Node(E data){ // 생성자에서 데이터를 받음
			this.data = data;
		}
	}
	int capacity; // 쌓을 수 있는 한계
	int size; // 현재 얼마나 쌓았는지
	Node top; // 탑 포인터
	Node bottom; // 바텀 포인터
	
	Stack(int capacity){ // 생성자에서 쌓을 수 있는 한계를 받음
		this.capacity = capacity;
	}
	
	public boolean isEmpty() {return size == 0;} // 스택이 비었는지
	public boolean isFull() {return size == capacity;} // 스택이 찼는지
	public int size() {return size;} // 데이터가 얼만큼 쌓였는지
	
	public void push(E d) throws FullStackException{ // 데이터 push
		if (isFull()) throw new FullStackException(); // 꽉 찼는데 데이터 넣으려고 하면 예외 던짐
		
		Node n = new Node(d);
		push(n);
	}
	public void push(Node n) throws FullStackException{ // Node push
		if (isFull()) throw new FullStackException(); // 꽉 찼는데 데이터 넣으려고 하면 예외 던짐 
		
		if (isEmpty()){ // 비어있는 상태에서 처음 데이터를 받을 때
			top = n;
			bottom = n;
		}else { // 기존에 데이터가 있는 경우
			top.above = n; // 새 노드가 기존노드의 위로 가므로
			n.below = top;
			top = n;
		}
		size++;
	}
	public E pop() {
		if (isEmpty()) throw new EmptyStackException(); // 비었는지 확인하고 예외 던짐
		E data = top.data; // 맨 위의 데이터를 가져오고
		top = top.below; // top을 그 아래의 데이터로 변경
		if (top == null) { // 가져온 데이터가 스택의 마지막 데이터였을때
			bottom = null;
		}else { // 그 외에 스택에 데이터가 남아 있을 때
			top.above = null; // 새로운 top의 above를 삭제
		}
		size--;
		return data;
	}
	public Node popBottom() {
		if (isEmpty()) throw new EmptyStackException(); // 비었는지 확인하고 예외 던짐
		Node n = bottom; // 맨 아래의 데이터를 가져오고
		bottom = bottom.above; // bottom을 그 위의 데이터로 변경
		if (bottom == null) { // 가져온 데이터가 스택의 마지막 데이터였을때
			top = null;
		}else { // 그 외에 스택에 데이터가 남아 있을 때
			bottom.below = null; // 새로운 bottom의 below를 삭제
		}
		size--;
		return n;
	}
}
class SetOfStacks{
	int capacity; // 스택이 가질 수 있는 노드의 개수
	ArrayList<Stack<Integer>> stacks = new ArrayList<Stack<Integer>>();
	SetOfStacks(int capacity){
		this.capacity = capacity;
	}
	public void addStack() { // ArrayList에 스택 추가
		stacks.add(new Stack<Integer>(capacity));
	}
	public void removeLastStack() { // ArrayList의 마지막 스택 삭제
		stacks.remove(stacks.size() - 1);
	}
	public Stack<Integer> getLastStack() {
		if (stacks.size() == 0) return null;
		return stacks.get(stacks.size() - 1);
	}
	public void push(int data) {
		Stack<Integer> last = getLastStack();
		if (last == null || last.isFull()) {
			addStack();
			last = getLastStack();
		}
		try{last.push(data);} catch (FullStackException e) {}
	}
	public int pop() {
		Stack<Integer> last = getLastStack();
		if (last == null || last.isEmpty()) throw new EmptyStackException();
		int data = last.pop();
		if (last.isEmpty()) removeLastStack();
		return data;
	}
	public int popAt(int index) {
		if (stacks.size() <= 0) {
			throw new EmptyStackException();
		}
		if (index < 0 || index >= stacks.size()) {
			throw new IndexOutOfBoundsException();
		}
		Stack<Integer> stack = stacks.get(index);
		if (stack == null || stack.isEmpty()) throw new EmptyStackException();
		int data = stack.pop();
		shiftLeft(index);
		return data;
	}
	public void shiftLeft(int index) {
		if (index < stacks.size() - 1) {
			Stack<Integer> right = stacks.get(index + 1);
			Stack<Integer> left = stacks.get(index);
			try {
				left.push(right.popBottom());
			} catch(FullStackException e) {}
			if (right.isEmpty()) {
				stacks.remove(index + 1);
			}
			shiftLeft(index + 1);
		}
	}
}

public class SetOfStacksTest {
	public static void main (String[] args) {
		SetOfStacks sos = new SetOfStacks(3);
		sos.push(1);
		sos.push(2);
		sos.push(3);
		
		sos.push(4);
		sos.push(5);
		sos.push(6);
		
		sos.push(7);
		sos.push(8);
		
		System.out.println(sos.popAt(0)); // 3	|1 2 4|5 6 7|8
		System.out.println(sos.popAt(1)); // 7	|1 2 4|5 6 8|
	}
}

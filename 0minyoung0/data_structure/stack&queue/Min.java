// Stack의 작은값 찾기
// https://youtu.be/atPGriLDP9E
// Stack을 구현하는데 Push, pop과 더불어
// 가장 작은 값을 반환하는 min 함수를 같이 구현하시오.
// (단, 모든 함수들은 O(1)의 시간만 걸려야 함)
// (주의) push할때마다 변수 하나에 가장 작은 값을 갱신하는 방식은 안됨. 해당 값이 pop 되었을때 문제가 생김 

import java.util.Stack;

// solution1 : 각 노드에 현재까지의 가장 작은 값을 같이 저장하는 방식
class NodeWithMin{ // 최솟값을 포함하는 노드
	int value;
	int min;
	
	NodeWithMin(int v, int min){
		value = v;
		this.min = min;
	}
}

class StackWithMin1 extends Stack<NodeWithMin>{ // 각 노드가 최솟값을 포함하는 스택
	public int min() {
		if(this.isEmpty()) { // 스택이 비었을때 
			return Integer.MAX_VALUE; // int 최댓값 반환
		}else { // 스택이 비지 않았다면
			return peek().min; // 최상단노드의 min을 반환
		}
	}
	public void push(int value) {
		int newMin = Math.min(value, min()); // 새 노드에 저장할 지금까지의 최솟값
		super.push(new NodeWithMin(value, newMin)); // 새 노드를 push
	}
}

// solution2 : 최솟값이 push될때마다 스택에 저장하는 방식
// 스택안에 최솟값을 저장하는 스택을 가지고 있고 최솟값이 push될때마다 스택에 저장하고
// pop될때 최솟값 스택의 top과 일치하면 제거하는 방식
// (주의) 같은 최솟값이 여러번 들어오는 경우를 고려하여 최솟값보다 작거나 같을 때 저장하도록 구현
class StackWithMin2 extends Stack<Integer>{
	Stack<Integer> s2;
	public StackWithMin2() {
		s2 = new Stack<Integer>();
	}
	public int min() {
		if (s2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}
	public void push(int value) {
		if (value <= min()) { // 
			s2.push(value);
		}
		super.push(value);
	}
	public Integer pop() {
		int value = super.pop();
		if (value == min()) {
			s2.pop();
		}
		return value;
	}
}

public class Min {
	public static void main(String[] args) {
		System.out.println("___stack1___");
		StackWithMin1 stack1 = new StackWithMin1();
		stack1.push(3);
		stack1.push(5);
		stack1.push(1);
		stack1.push(2);
		System.out.println("min: " + stack1.min());
		System.out.println(stack1.pop().value);
		System.out.println(stack1.pop().value);
		System.out.println("min: " + stack1.min());
		
		System.out.println("___stack2___");
		StackWithMin2 stack2 = new StackWithMin2();
		stack2.push(3);
		stack2.push(5);
		stack2.push(1);
		stack2.push(1);
		System.out.println("min: " + stack2.min());
		System.out.println("pop: " + stack2.pop());
		System.out.println("min: " + stack2.min());
		System.out.println("pop: " + stack2.pop());
		System.out.println("min: " + stack2.min());
		System.out.println("pop: " + stack2.pop());
		System.out.println("min: " + stack2.min());
	}
}

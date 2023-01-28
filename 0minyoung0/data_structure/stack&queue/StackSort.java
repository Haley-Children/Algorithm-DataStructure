// Stack 정렬하기
// https://youtu.be/6-tsS9aBfzY
// Stack을 정렬하는 함수를 만드시오.
// 단, 하나의 Stack을 추가로 사요할 수 있고 Array 등 다른 데이터 구조는 사용할 수 없음

import java.util.Stack;

public class StackSort {
	public static void main(String[] args) {
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(3);
		s1.push(5);
		s1.push(1);
		s1.push(6);
		sort(s1);
		System.out.println(s1.pop()); // 1
		System.out.println(s1.pop()); // 3
		System.out.println(s1.pop()); // 5
		System.out.println(s1.pop()); // 6
	}
	
	// s1의 정렬되지 않은 데이터를 s2에 정렬하고 s1에 다시 부어주는 함수
	// s1의 데이터를 s2에 하나씩 옮기며
	// s2의 peek이 s1에서 온 새 데이터보다 크다면 s1에 다시 넣는 방식
	private static void sort (Stack<Integer> s1) {
		Stack<Integer> s2 = new Stack<Integer>();
		while(!s1.isEmpty()) {
			int tmp = s1.pop();
			while(!s2.isEmpty() && s2.peek() > tmp) {
				s1.push(s2.pop());
			}
			s2.push(tmp);
		}
		while (!s2.empty()) {
			s1.push(s2.pop());
		}
	}
}

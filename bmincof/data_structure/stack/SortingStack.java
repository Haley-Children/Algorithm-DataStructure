import java.util.Stack;
/*
    Stack을 정렬하는 메서드 구현하기
    조건
    1. 하나의 스택을 추가로 사용할 수 있다.
    2. 배열 등 다른 자료구조를 사용할 수 없다.
 */
public class SortingStack {

    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        s1.push(3);
        s1.push(5);
        s1.push(1);
        s1.push(6);
        // 3 -> 5 -> 1 -> 6 (top)
        sort(s1);
        // 1 -> 3 -> 5 -> 6 (top)
        System.out.println(s1.pop());
        // 6
        System.out.println(s1.pop());
        // 5
        System.out.println(s1.pop());
        // 3
        System.out.println(s1.pop());
        // 1
    }

    /*
    전략
    정렬된 값을 담을 스택 sorted와 임의의 순서로 값이 담겨있는 스택 tmp
    tmp에서 pop한 데이터 data를 sorted에 push
    이 때 data가 sorted의 top보다 작거나 빈 스택이 될 때까지 sorted를 pop해서 tmp에 push
    tmp가 빈 스택이 될 때까지 반복
     */
    private static void sort(Stack<Integer> sorted) {
        Stack<Integer> tmp = new Stack<>();

        // 기존의 스택에 정렬된 값들을 반환하기 위해 tmp로 값을 이동
        while(!sorted.isEmpty()) {
            tmp.push(sorted.pop());
        }

        // 이동시킨 값들을 정렬하며 기존의 스택에 하나씩 저장
        while(!tmp.isEmpty()) {
            int data = tmp.pop();
            while(!sorted.isEmpty() && sorted.peek() > data) {
                tmp.push(sorted.pop());
            }

            sorted.push(data);
        }


    }

}


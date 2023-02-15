package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_1406 {
    // 커서의 왼쪽에 있는 문자들을 표현할 스택과
    // 오른쪽에 있는 문자들을 표현할 스택 2개를 이용
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String init = br.readLine();

        Deque<Character> prev = new ArrayDeque<>();
        Deque<Character> rear = new ArrayDeque<>();

        // 초기 문자를 커서 앞에 두기
        for (int i = 0; i < init.length(); i++) {
            prev.offerLast(init.charAt(i));
        }

        // 명령의 개수
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] cmd = br.readLine().split(" ");

            switch (cmd[0]) {
                // 커서를 왼쪽으로 옮기면 커서 앞에 있던 가장 뒤의 글자를 커서 뒤로 이동
                case "L":
                    if(!prev.isEmpty()) {
                        rear.offerFirst(prev.pollLast());
                    }
                    break;
                // 커서를 오른쪽으로 옮기면 커서 뒤에 있던 가장 앞의 글자를 커서 앞으로 이동
                case "D":
                    if(!rear.isEmpty()) {
                        prev.offerLast(rear.pollFirst());
                    }
                    break;
                // 커서 앞의 문자를 삭제
                case "B":
                    if(!prev.isEmpty()) {
                        prev.pollLast();
                    }
                    break;
                // 문자를 커서 앞에 입력
                default:
                    prev.offerLast(cmd[1].charAt(0));

            }
        }

        // 출력 단계
        // 커서 앞의 문자를 모두 출력한 후 커서 뒤의 문자를 모두 출력
        // 큐와 같은 구조로 사용
        StringBuilder sb = new StringBuilder(prev.size() + rear.size());

        // 커서 앞 문자 출력
        while(!prev.isEmpty()) {
            sb.append(prev.pollFirst());
        }
        // 커서 뒤 문자 출력
        while(!rear.isEmpty()) {
            sb.append(rear.pollFirst());
        }

        System.out.println(sb);

    }

}


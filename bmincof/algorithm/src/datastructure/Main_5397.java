package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_5397 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        // 커서 앞 뒤의 문자
        Deque<Character> prev = new ArrayDeque<>();
        Deque<Character> rear = new ArrayDeque<>();

        for (int test = 0; test < t; test++) {
            // 명령의 개수
            String line = br.readLine();
            for (int i = 0; i < line.length(); i++) {
                char cmd = line.charAt(i);

                switch (cmd) {
                    // 커서를 왼쪽으로 옮기면 커서 앞에 있던 가장 뒤의 글자를 커서 뒤로 이동
                    case '<':
                        if (!prev.isEmpty()) {
                            rear.offerFirst(prev.pollLast());
                        }
                        break;
                    // 커서를 오른쪽으로 옮기면 커서 뒤에 있던 가장 앞의 글자를 커서 앞으로 이동
                    case '>':
                        if (!rear.isEmpty()) {
                            prev.offerLast(rear.pollFirst());
                        }
                        break;
                    // 커서 앞의 문자를 삭제
                    case '-':
                        if (!prev.isEmpty()) {
                            prev.pollLast();
                        }
                        break;
                    // 문자를 커서 앞에 입력
                    default:
                        prev.offerLast(cmd);

                }
            }

            // 출력 단계
            // 커서 앞의 문자를 모두 출력한 후 커서 뒤의 문자를 모두 출력
            // 큐와 같은 구조로 사용
            StringBuilder sb = new StringBuilder(prev.size() + rear.size());

            // 커서 앞 문자 출력
            while (!prev.isEmpty()) {
                sb.append(prev.pollFirst());
            }
            // 커서 뒤 문자 출력
            while (!rear.isEmpty()) {
                sb.append(rear.pollFirst());
            }

            System.out.println(sb);
        }
    }

}


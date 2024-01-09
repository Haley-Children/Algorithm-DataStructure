package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_5430_AC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        loop:
        for (int t = 0; t < T; t++) {
            String command = br.readLine();
            int cnt = Integer.parseInt(br.readLine());
            // StringTokenizer는 정규식 검사가 아니기 때문에 구분하려는 문자들을 하나의 문자열로 인자를 넘겨주면 된다.
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            ArrayDeque<String> deque = new ArrayDeque<>(cnt);
            for (int i = 0; i < cnt; i++) {
                deque.offerLast(st.nextToken());
            }

            boolean direction = true; // front
            for (char c : command.toCharArray()) {
                if (c == 'R') {
                    direction = !direction;
                } else if (c == 'D') {
                    if (deque.isEmpty()) {
                        sb.append("error").append("\n");
                        continue loop;
                    }
                    if (direction) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }

            // 출력 오류 주의) poll() 함수는 덱이 비어있으면 null을 반환하므로, 결과값에 "null"이 찍혔다.
            sb.append("[");
            while (deque.size() > 1) {
                if (direction) {
                    sb.append(deque.pollFirst());
                } else {
                    sb.append(deque.pollLast());
                }
                sb.append(",");
            }
            if (deque.size()==1) {
                sb.append(deque.getFirst());
            }
            sb.append("]\n");

        }
        System.out.println(sb);
    }

}

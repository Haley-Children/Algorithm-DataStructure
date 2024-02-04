package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_5430_AC {

    // 배열을 직접 다 뒤집어줄 필요 없이, 덱을 사용하여 포인터를 이용해 앞 뒤를 볼 수 있다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tk = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        label:
        for (int testCase = 0; testCase < tk; testCase++) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine()); // 배열의 길이
            ArrayDeque<Integer> dq = new ArrayDeque<>(n);
            String arrString = br.readLine();
            arrString = arrString.substring(1, arrString.length() - 1);
            StringTokenizer st = new StringTokenizer(arrString, ",");
            for (int i = 0; i < n; i++) {
                dq.offer(Integer.parseInt(st.nextToken()));
            }
            boolean flag = true; //똑바로
            // 명령어 수행
            for (char c : command.toCharArray()) {
                if (c == 'R') { // 뒤집기
                    flag = !flag;
                } else if (c == 'D') { // 첫 번째 수 삭제
                    if (dq.isEmpty()) {
                        sb.append("error").append("\n");
                        continue label;
                    } else {
                        if (flag) {
                            dq.pollFirst();
                        } else {
                            dq.pollLast();
                        }
                    }
                }
            }

            sb.append("[");
            while (!dq.isEmpty()) {
                if (flag) {
                    sb.append(dq.pollFirst());
                } else {
                    sb.append(dq.pollLast());
                }
                if (dq.size() != 0) {
                    sb.append(",");
                }

            }
            sb.append("]");
            sb.append("\n");
        }
        System.out.println(sb);
    }

}

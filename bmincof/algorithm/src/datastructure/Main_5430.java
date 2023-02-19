package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_5430 {
    // 1. 값들을 덱에 저장한다.
    // 2. R을 입력받으면 reverse 모드로 생각
    // 3. reverse이면 덱의 뒤에서 작업하고, 아니면 앞에서 작업
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // n개의 테스트 케이스
        test :
        for(int i = 0; i < n; i++) {
            // 명령어 입력
            String cmd = br.readLine();
            boolean isReverse = false;

            // 배열 크기 입력
            int size = Integer.parseInt(br.readLine());
            // 배열 입력
            String line = br.readLine();

            // [ / ] / , 제거
            StringTokenizer st = new StringTokenizer(line.substring(1, line.length() - 1),",");
            Deque<String> dq = new LinkedList<>();

            // 배열 입력받기
            for(int j = 0; j < size; j++) {
                dq.offerLast(st.nextToken());
            }

            // 명령어 처리
            for(int j = 0; j < cmd.length(); j++) {
                // reverse 모드 변경
                if(cmd.charAt(j) == 'R') {
                    isReverse = !isReverse;
                }
                // 삭제 명령
                if(cmd.charAt(j) == 'D') {
                    // 값이 없는데 삭제하려 하면 error
                    if(dq.isEmpty()) {
                        sb.append("error\n");
                        continue test;
                    } else {
                        // reverse 상태면 뒤에서 삭제
                        if(isReverse) {
                            dq.pollLast();
                            // 정상 상태면 앞에서 삭제
                        } else {
                            dq.pollFirst();
                        }
                    }
                }
            } // end of for

            // 출력하기
            sb.append('[');
            while(!dq.isEmpty()) {
                // reverse면 뒤에서 부터 출력
                if(isReverse) {
                    sb.append(dq.pollLast()).append(',');
                    // 정상 상태면 앞에서 부터 출력
                } else {
                    sb.append(dq.pollFirst()).append(',');
                }
            }
            if(sb.charAt(sb.length()-1) == ',') {
                sb.deleteCharAt(sb.length()-1).append("]\n");
            } else {
                sb.append("]\n");
            }
        }
        // 최종 결과 출력
        System.out.println(sb);
    }
}

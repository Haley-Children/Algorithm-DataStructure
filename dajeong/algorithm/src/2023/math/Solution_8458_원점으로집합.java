package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458_원점으로집합 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tk = 1; tk <= T; tk++) {
            int N = Integer.parseInt(br.readLine());

            int mod = -1;
            long maxGap = 0;
            boolean hasAnswer = true;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                long gap = Math.abs(Long.parseLong(st.nextToken())) + Math.abs(Long.parseLong(st.nextToken()));
                if(i==0) { // 첫번째 수를 기준으로 짝홀 여부(나머지), maxGap 초기화
                    mod = (int) (gap%2);
                    maxGap = gap;
                } else { // 그 다음값부터 확인하면서 첫번째 값과 짝홀 종류가 같은지 확인.
                    if(gap%2 != mod) { // 모두 짝수거나 모두 홀수가 아니면 동시에 원점 도달 불가능
                        hasAnswer = false;
                        for (int j = i+1; j <N; j++) {
                            br.readLine();
                        }
                        break;
                    } else {
                        maxGap = Math.max(gap, maxGap); // 원점 도달 가능한 경우 사용할 최대 거리
                    }
                }

            }

            long ans = 0;
            if(hasAnswer) { // 원점 도달 가능한 케이스
                while(maxGap>0) {
                    ans++;
                    maxGap -= ans;
                }
                maxGap *= -1; // 양수로 변경

                if(maxGap%2 ==1) { // 거리가 홀수면 (짝수인 경우 와리가리 가능해서 바로 출력 (0도 포함))
                    if(ans%2==0) { // 현재 짝수면 (다음이 홀수) 와리가리 가능
                        ans++;
                    } else ans+=2; // 현재 홀수라 나중에 짝수면 와리가리 불가
                }
            } else { // 원점 도달 불가능
                ans = -1;
            }
            sb.append(String.format("#%d %d\n", tk, ans));

        }
        System.out.println(sb);
    }

}

package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 값 하나가 원하는 합(M)이 될 수 있음
public class Main_2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int e = 0;
        int sum = nums[0];
        for (int s = 0; s < N; s++) {
            if (sum == M) {
                cnt++;
                sum -= nums[s];
                continue;
            }
            while(e<N) {
                // e + 1 하기 전에 sum 확인해야 함. 로직 순서 주의!
                if (sum == M) {
                    cnt++;
                    break; // 빼먹음..
                } else if (sum > M) {
                  break;
                }
                e++;
                if (e!=N) sum+=nums[e];
            }
            sum -= nums[s];
        }
        System.out.println(cnt);
    }
}

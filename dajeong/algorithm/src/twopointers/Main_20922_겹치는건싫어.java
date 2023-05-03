package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20922_겹치는건싫어 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int[] cnt = new int[100_001];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 1;
        int rt = 0;
        int ans = 0;

        while(lt<=N) {
            while(rt+1<=N && cnt[nums[rt+1]]<K) {
                rt++;
                cnt[nums[rt]]++;
            }

            if (ans < rt-lt+1) {
                ans = rt-lt+1;
            }
            cnt[nums[lt]]--;
            lt++;
        }
        System.out.println(ans);
    }

}

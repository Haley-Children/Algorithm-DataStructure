package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22862_가장긴짝수연속한부분수열_large {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] nums = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i]= Integer.parseInt(st.nextToken());
        }

        int lt = 1;
        int rt = 0;
        int ans = 0;
        int oddCnt = 0;
        while(lt<=n) {
            while (oddCnt <= k && rt+1<=n) {
                rt++;
                if ((nums[rt] & 1) == 1) oddCnt++;
            }
            ans = Math.max(ans,rt-lt+1-oddCnt);
            if ((nums[lt++]&1)==1) oddCnt--;


        }
        System.out.println(ans);
    }
}

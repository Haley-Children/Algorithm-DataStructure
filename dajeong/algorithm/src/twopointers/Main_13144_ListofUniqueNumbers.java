package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13144_ListofUniqueNumbers {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N+1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 1;
        int rt = 0;
        long ans = 0; //***
        int[] cnt = new int[100001];
        while (lt <= N) {
            while (rt + 1 <= N && cnt[nums[rt + 1]] == 0) {
                rt++;
                cnt[nums[rt]]++;
            }
            ans += rt - lt + 1;
            cnt[nums[lt++]]--;
        }
        System.out.println(ans);

    }
}

package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1920_수찾기 {
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] searchingNums = new int[M];
        for (int i = 0; i < M; i++) {
            searchingNums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.parallelSort(nums);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            /* 
            int ans = Arrays.binarySearch(nums, searchingNums[i]);
            if (ans >= 0) {
               sb.append(1); 
            } else {
                sb.append(0);
            }
            sb.append("\n");
            */

            sb.append(binarysearch(nums, searchingNums[i])).append("\n");
        }
        System.out.println(sb);

    }
    private static int binarysearch(int[] a, int target) {
        int st = 0;
        int en = N-1;
        while (st <= en) {
            int mid = (st+en) >>> 1;
            if (a[mid] < target) {
                st = mid + 1;
            } else if (a[mid] > target) {
                 en = mid - 1;
            } else { // 찾은 경우
                return 1;
            }
        }
        return 0; // 못찾은 경우
    }
}

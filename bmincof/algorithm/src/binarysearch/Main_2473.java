package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 수
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 특성값을 저장
        long[] solutions = new long[n];

        for(int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.parallelSort(solutions);

        long minValue = Long.MAX_VALUE;
        long sol1 = 0;
        long sol2 = 0;
        long sol3 = 0;

        // left 와 right 특성값이
        for(int i = 0; i < n-2; i++) {
            for(int j = i+1; j < n-1; j++) {
                int left = j + 1;
                int right = n - 1;

                long sum = solutions[i] + solutions[j];
                while (left != right) {
                    int mid = (left + right) / 2;

                    long curr = Math.abs(solutions[mid] + sum);

                    if (curr > Math.abs(solutions[mid+1] + sum)) {
                        left = mid + 1;
                    } else if (curr > Math.abs(solutions[mid-1] + sum)) {
                        right = mid;
                    } else {
                        left = mid;
                        break;
                    }
                }

                // 이번에 만든 0에 가장 가까운 합
                long comp = Math.abs(sum + solutions[left]);
                if (comp < minValue) {
                    minValue = comp;
                    sol1 = solutions[i];
                    sol2 = solutions[j];
                    sol3 = solutions[left];
                }
            }
        }

        System.out.println(sol1 + " " + sol2 + " " + sol3);
    }
}

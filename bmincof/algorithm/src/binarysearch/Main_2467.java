package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 수
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 특성값을 저장
        int[] solutions = new int[n];

        for(int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        int minValue = Integer.MAX_VALUE;
        int sol1 = 0;
        int sol2 = 0;

        // left 와 right 특성값이
        for(int i = 0; i < n-1; i++) {
            int left = i+1;
            int right = n-1;

            while(left != right) {
                int mid = (left + right) / 2;

                if(Math.abs(solutions[mid] + solutions[i]) > Math.abs(solutions[mid+1] + solutions[i])) {
                    left = mid+1;
                } else if(Math.abs(solutions[mid] + solutions[i]) > Math.abs(solutions[mid-1] + solutions[i])) {
                    right = mid;
                } else {
                    left = mid;
                    break;
                }
            }

            // 이번에 만든 0에 가장 가까운 합
            int comp = Math.abs(solutions[i] + solutions[left]);
            if(comp < minValue) {
                minValue = comp;
                sol1 = solutions[i];
                sol2 = solutions[left];
            }
        }

        System.out.println(sol1 + " " + sol2);
    }
}

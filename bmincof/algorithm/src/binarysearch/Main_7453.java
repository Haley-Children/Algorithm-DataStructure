package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    a, b, c, d, ab 배열은 이분 탐색할 대상이 아닌데도 정렬을 해주면 시간이 매우 크게 감소함
    캐시 히트 때문이라는 이야기가 있다.
 */
public class Main_7453 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 배열의 크기
        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        Arrays.sort(d);

        // a와 b, c와 d로 합들을 만들기
        int[] ab = new int[n*n];
        int[] cd = new int[n*n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ab[i*n+j] = a[i] + b[j];
                cd[i*n+j] = c[i] + d[j];
            }
        }

        // cd를 정렬해서 이분탐색으로 0이 되는 값을 찾기
        Arrays.sort(ab);
        Arrays.sort(cd);

        long cnt = 0;
        for(int i = 0; i < n*n; i++) {
            int target = -ab[i];

            int start = 0;
            int end = n*n;

            while(start < end) {
                int mid = (start + end) / 2;

                if(cd[mid] <= target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            int upper = start;

            start = 0;
            end = n*n;

            while(start < end) {
                int mid = (start + end) / 2;

                if(cd[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            int lower = start;

            cnt += upper - lower;
        }

        System.out.println(cnt);
    }
}

package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11652 {

    public static void main(String[] args) throws IOException {
        // cnt: 현재 보고 있는 수가 몇번 등장했는가 (초기값 0)
        // mxval: 현재까지 가장 많이 등장한 수의 값 (초기값 -2^62 -1)
        // mxcnt: 현재까지 가장 많이 등장한 수의 등장 횟수 (초기값 0)

        // 1 1 1 2 2

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n]; // int 범위: -2^31 ~ (2^31 - 1)!!!!
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        int cnt = 1;
        int mxIdx = 0;
        int mxcnt = 1;
        for (int i = 1; i < n; i++) {
            cnt++;
            if (arr[i] == arr[i-1]) cnt++;
            else cnt = 1;
            if (cnt > mxcnt) {
                mxcnt = cnt;
                mxIdx = i;
            }
        }
        System.out.println(arr[mxIdx]);
    }
}

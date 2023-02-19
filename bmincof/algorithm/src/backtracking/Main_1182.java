package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182 {

    static int[] arr;
    static int n;
    static int s;
    static int cnt;

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        find(0, 0);

        System.out.println(cnt);
    }

    static void find(int k, int sum) {
        if(k == n) {
            return;
        }

        for (int i = k; i < n; i++) {
            int nsum = sum + arr[i];
            if(nsum == s) cnt++;
            find(i+1, nsum);
        }

    }
}


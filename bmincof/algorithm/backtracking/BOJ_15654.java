import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {

    static StringBuilder sb = new StringBuilder(1000);
    static int n, m;
    static int[] buffer = new int[10];
    static int[] arr;
    static boolean[] isUsed = new boolean[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        func(0);
        System.out.println(sb);
    }

    static void func(int k) {
        if(k == m) {
            for (int i = 0; i < k; i++) sb.append(buffer[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!isUsed[arr[i]]) {
                buffer[k] = arr[i];
                isUsed[arr[i]] = true;
                func(k+1);
                isUsed[arr[i]] = false;
            }
        }
    }
}


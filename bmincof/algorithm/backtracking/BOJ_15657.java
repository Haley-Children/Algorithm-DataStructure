import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15657 {

    static StringBuilder sb = new StringBuilder(1000);
    static int n, m;
    static int[] input, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        input = new int[n];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(input);
        bt(0, 0);
        System.out.println(sb);
    }

    static void bt(int k, int idx) {
        if(k == m) {
            for(int i = 0; i < k; i++) sb.append(arr[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = idx; i < n; i++) {
            arr[k] = input[i];
            bt(k + 1, i);
        }
    }
}


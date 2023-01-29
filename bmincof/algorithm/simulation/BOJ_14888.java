import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {

    static int[] numbers = new int[110];
    static int[] calc = new int[110];

    static int[] ops = new int[4];

    static int n;
    static int minimum = Integer.MAX_VALUE;
    static int maximum = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        calc[0] = numbers[0];

        st = new StringTokenizer(br.readLine());

        for (int op = 0; op < 4; op++) {
            int m = Integer.parseInt(st.nextToken());
            ops[op] = m;
        }

        bt(0);
        System.out.println(maximum);
        System.out.println(minimum);
    }

    static void bt(int k) {
        if(k == n - 1) {
            minimum = Math.min(calc[k], minimum);
            maximum = Math.max(calc[k], maximum);
            return;
        }

        if (ops[0] > 0) {
            calc[k + 1] = calc[k] + numbers[k + 1];
            ops[0]--;
            bt(k+1);
            ops[0]++;
        }
        if (ops[1] > 0) {
            calc[k + 1] = calc[k] - numbers[k + 1];
            ops[1]--;
            bt(k+1);
            ops[1]++;
        }
        if (ops[2] > 0) {
            calc[k + 1] = calc[k] * numbers[k + 1];
            ops[2]--;
            bt(k+1);
            ops[2]++;
        }
        if (ops[3] > 0) {
            calc[k + 1] = calc[k] / numbers[k + 1];
            ops[3]--;
            bt(k+1);
            ops[3]++;
        }


    }

}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        // i번째 집의 rgb 비용
        int[] r = new int[n];
        int[] g = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 집이 r,g,b 일 때 최소비용
        int[] mR = new int[n];
        int[] mG = new int[n];
        int[] mB = new int[n];

        mR[0] = r[0];
        mG[0] = g[0];
        mB[0] = b[0];

        // i번째 집이 r이면 i-1번째는 g 또는 b 나머지도 유사
        for (int i = 1; i < n; i++) {
            mR[i] = Math.min(mG[i-1], mB[i-1]) + r[i];
            mG[i] = Math.min(mR[i-1], mB[i-1]) + g[i];
            mB[i] = Math.min(mR[i-1], mG[i-1]) + b[i];
        }

        System.out.println(Math.min(Math.min(mR[n-1], mG[n-1]),mB[n-1]));

    }

}


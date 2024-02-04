package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10807_개수세기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(br.readLine());
        int[] cntArr = new int[201];
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(st.nextToken());
            cntArr[t + 100]++;
        }
        System.out.println(cntArr[v + 100]);

    }

}

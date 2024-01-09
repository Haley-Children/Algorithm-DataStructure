package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3273_두수의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());

        // ** array index out of bounds 주의
        // 1) 수열의 범위만 고려하면 안됨.
        // x의 범위가 최대 2백만이므로 인덱스 x-t를 조회하는 부분에서 x가 최대, t가 최소일 때 해당 에러 생길 수 있으므로 array 크기를 2백만 이상으로 잡아야 함.
        int[] cntArr = new int[2000005];
        int answer = 0;

        // 2) x-t가 음수인 경우 에러 발생 가능. 따라서 미리 cntArr에 값을 넣어두고 진행하거나 음수 예외 처리 필요
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (x-t >=0 && cntArr[x-t]>0) {
                answer++;
            }
            cntArr[t]++;
        }
        System.out.println(answer);
    }

}

package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1193_분수찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int idx = 1;
        while (X > idx) { // 대각선 몇번째 줄인지 찾기
            X -= idx;
            idx++;
        }
        if (idx % 2 == 0) { // 짝수번째
            System.out.println(String.format("%d/%d", X, idx + 1 - X));
        } else { // 홀수번째
            System.out.println(String.format("%d/%d", idx + 1 - X, X));
        }

    }

}

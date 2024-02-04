package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1992 {

    // 한 가지 수로만 이루어져 있으면 출력 (return)
    // 그렇지 않으면 n -> n/2로 나누면서 1, 2, 3, 4분면 동일한 작업 반복 (재귀)
    // base condition: n=1
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        recur(0, 0, n);
        System.out.println(sb);

    }

    private static void recur(int x, int y, int size) {
        if (check(x,y, size)) {
            sb.append(arr[x][y]);
            return;
        }

        int half = size / 2;
        sb.append('(');
        recur(x, y, half); // 1
        recur(x, y + half, half); // 2
        recur(x + half, y, half); // 3
        recur(x + half, y + half, half); // 4
        sb.append(')');
    }

    // ***********
    private static boolean check(int x, int y, int size) {
        int value = arr[x][y];
        for (int i = x; i < x + size; i++) { // *** x,y가 될 수 있는 위치를 생각해보기 (재귀 호출하는 함수 참고)
            for (int j = y; j < y + size; j++) {
                if (value != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}

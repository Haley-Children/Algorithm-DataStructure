package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9657 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // i번째에 돌을 가져가는 사람이 이기는지 저장한 배열
        boolean[] isWin = new boolean[n+4];
        isWin[1] = isWin[3] = isWin[4] = true;

        for (int i = 5; i <= n; i++) {
            // 1개, 3개, 4개를 가져갔을 때 모두 다음 플레이어가 이길 수 있으면
            if(isWin[i-1] && isWin[i-3] && isWin[i-4]) {
                isWin[i] = false;
            } else {
                isWin[i] = true;
            }
        }

        if(isWin[n]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}

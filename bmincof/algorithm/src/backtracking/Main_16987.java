package backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class Main_16987 {
    // 계란의 무게를 저장할 1-indexed 배열
    static int[] weights;
    // 모든 계란으로 쳐봤을 때 가장 많이 깨진 계란 개수
    static int maxBroken = Integer.MIN_VALUE;
    // 계란의 개수
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        // k번째 까지 진행했을 때 계란의 내구도를 저장할 1-indexed 배열
        int[] eggs = new int[n + 1];
        weights = new int[n + 1];

        // 각 계란 정보 입력받기
        for(int i = 1; i <= n; i++) {
            eggs[i] = sc.nextInt();
            weights[i] = sc.nextInt();
        }

        bt(eggs, 1);
        System.out.println(maxBroken);
    }

    static void bt(int[] eggs, int k) {
        if(k > n) {
            // 이번 경우에 깬 계란의 수
            int count = 0;
            // 깨진 계란의 개수를 +
            for(int i = 1; i <= n; i++) {
                if(eggs[i] == 0) count++;
            }
            // 최대 갱신
            maxBroken = Math.max(maxBroken, count);
            return;
        }
        
        // k번째 계란이 깨졌으면 그대로 순서 넘기기
        if(eggs[k] == 0) {
            bt(eggs, k + 1);
        // 다른 계란을 칠 수 있으면 쳐보기
        } else {
            // k번째 계란으로 자신을 제외한 다른 계란을 깨본다.
            for (int i = 1; i <= n; i++) {
                // 자기 자신이면 건너뛰기
                if (i == k) continue;
                // 이미 i번째 계란이 깨졌으면 건너뛰기
                if (eggs[i] == 0) continue;

                // i번째 계란을 깬 새로운 상태를 저장할 배열
                int[] newState = Arrays.copyOf(eggs, n + 1);

                // i번째 계란 깬 상태를 다음 단계로 넘겨주기
                newState[i] -= weights[k];
                newState[k] -= weights[i];

                if (newState[i] < 0) newState[i] = 0;
                if (newState[k] < 0) newState[k] = 0;

                bt(newState, k + 1);
            }
        }
    }
}

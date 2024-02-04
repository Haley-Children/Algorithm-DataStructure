package simulation.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13335_트럭 {

    static int[] trucks;
    static int[] bridge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭의 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

        trucks = new int[n];
        int p = 0;
        bridge = new int[w + 1];
        int sum = 0;
        int endOfBridge = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        while (p < n) {
            time++;
            int t = trucks[p];
            if (sum + t - bridge[w-1] > L) {
                // 오른쪽 쉬프트
                for (int i = w; i >= 1; i--) {
                    bridge[i] = bridge[i - 1];
                }
                bridge[0] = 0;
                // 기존 sum - bridge[w]
                sum -= bridge[w];
                // 첫 트럭이 있는 위치 + 1
                endOfBridge++;
            } else {
                // 오른쪽 쉬프트
                for (int i = w; i >= 1; i--) {
                    bridge[i] = bridge[i - 1];
                }
                // 첫번째 원소에 트럭 넣고 포인터 이동
                bridge[0] = t;
                p++;
                // 기존 sum - bridge[w] + bridge[0]
                sum = sum - bridge[w] + bridge[0];
                // 첫 트럭이 있는 위치 갱신
                endOfBridge = 0;
            }

        }

        // 모든 트럭이 다리를 다 건너야 함
        if (endOfBridge < w) {
            time += (w - endOfBridge);
        }

        System.out.println(time);


    }

}

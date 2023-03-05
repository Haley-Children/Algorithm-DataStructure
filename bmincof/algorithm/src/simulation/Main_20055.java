package simulation;

import java.util.Scanner;

public class Main_20055 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 위쪽에 있는 벨트의 개수
        int n = sc.nextInt();
        // 벨트의 총 길이
        int len = 2 * n;
        // 종료 조건
        int k = sc.nextInt();

        // i번째 벨트의 내구도를 저장하는 배열
        int[] belts = new int[len];
        // i번째 벨트에 로봇이 있는지 저장하는 배열
        boolean[] hasRobot = new boolean[len];

        for (int i = 0; i < len; i++) {
            belts[i] = sc.nextInt();
        }

        sc.close();

        // 로봇을 올리는 위치
        // 로봇을 내리는 위치 == inPos + n - 1
        int inPos = 0;
        int outPos = n - 1;

        // 몇 번째 단계가 진행중인지 나타내는 변수
        int level = 0;

        while (true) {
            level++;

            // 차례대로 동작
            // 1. 벨트가 로봇과 함께 한 칸 회전한다.
            inPos = (inPos - 1 + len) % (len);
            outPos = (outPos - 1 + len) % (len);

            // 1-1. 벨트가 회전하고 내리는 위치에 로봇이 있으면 로봇을 내린다
            hasRobot[outPos] = false;

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 회전하는 방향(내리는 위치 방향)으로 이동할 수 있다면
            // 한 칸 이동한다.
            for (int i = 1; i <= n - 1; i++) {
                int checkPos = (outPos - i + len) % len;
                int front = (checkPos + 1) % len;
                // 가장 먼저 벨트에 올라간 로봇은 outPos쪽에 있을 것
                // 로봇이 있고, 회전하는 방향으로 바로 앞 칸에 로봇이 없고 벨트의 내구도가 0이 아니면 로봇을 이동
                if (hasRobot[checkPos] && !hasRobot[front] && belts[front] > 0) {
                    // 기존 칸의 hasRobot을 false로
                    hasRobot[checkPos] = false;
                    // 앞 칸의 hasRobot을 true로
                    hasRobot[front] = true;

                    // 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다.
                    if(front == outPos) hasRobot[front] = false;
                    // 앞 칸의 벨트 내구도를 -1
                    belts[front]--;
                }
            }

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올리기
            if (belts[inPos] > 0) {
                // 올리는 위치에 로봇을 올린다.
                hasRobot[inPos] = true;
                // 올리는 위치 벨트의 내구도를 1 감소
                belts[inPos]--;
            }

            // 내구도가 0인 벨트의 개수를 센다.
            int count = 0;
            for (int belt : belts) {
                if (belt == 0) count++;
            }

            // 내구도가 0인 칸의 개수가 k개 이상이라면 과정 종료
            if (count >= k) {
                break;
            }
        }

        System.out.println(level);
    }
}

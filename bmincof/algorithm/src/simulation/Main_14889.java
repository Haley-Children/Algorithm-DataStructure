package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    M = N / 2
    1. N 까지 자연수 중에서 M 개의 서로 다른 수를 뽑는 경우의 수 -> 백트래킹
    2. M개를 모두 뽑았으면 양 팀의 Sij Sji 모두 더하고 차이 계산
    3. 차이가 가장 적은 값 리턴

    메서드
    1. M 명을 뽑는 메서드
    2. 팀의 능력합 구하는 메서드
 */

public class Main_14889 {

    static int n, m;
    // i 번째 선수가 스타트팀인지
    static boolean[] isTeamStart;
    static int[][] syn;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = n / 2;
        isTeamStart = new boolean[n];
        syn = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                syn[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bt(0, 0);
        System.out.println(minDiff);
    }

    static void bt(int k, int idx) {
        if(k == m) {
            minDiff = Math.min(calcSynDiff(), minDiff);
            return;
        }

        for (int i = idx; i < n; i++) {
            isTeamStart[i] = true;
            bt(k + 1, i + 1);
            isTeamStart[i] = false;
        }
    }

    static int calcSynDiff() {
        int synStart = 0;
        int synLink = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(isTeamStart[i] && isTeamStart[j]) {
                    synStart += syn[i][j];
                } else if(!isTeamStart[i] && !isTeamStart[j]) {
                    synLink += syn[i][j];
                }
            }
        }

        return Math.abs(synLink - synStart);
    }

}


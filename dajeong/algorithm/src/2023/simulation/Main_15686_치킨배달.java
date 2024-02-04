package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main_15686_치킨배달 {

    static int N;
    static int M;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행, 열
        M = Integer.parseInt(st.nextToken()); // 최대 치킨집 수
        map = new int[N][N];

        // 0: 빈칸 1: 집 2: 치킨집 입력
        int chickenCnt = 0;
        List<Point> chickenList = new ArrayList<>();
        List<Point> houseList = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) {
                    chickenCnt++;
                    chickenList.add(new Point(r, c)); // 치킨 집 저장
                } else if (map[r][c] == 1) {
                    houseList.add(new Point(r, c)); // 집 저장
                }
            }
        }

        ans = Integer.MAX_VALUE; // 도시의 치킨거리 최솟값 (정답)
        for (int i = 0; i < (1 << chickenCnt); i++) { // 가능한 치킨집 조합 경우의 수 탐색
            if (Integer.bitCount(i) >= 1 && Integer.bitCount(i) <= M) {
                int sum = 0; // 각 조합 당 가능한 도시의 치킨거리
                for (int h = 0; h < houseList.size(); h++) {
                    // 집을 하나씩 순회하면서 모든 치킨집간의 거리를 계산하고, 각 집들의 최소 거리를 sum에 더해준다.
                    Point house = houseList.get(h);

                    int minDis = Integer.MAX_VALUE; // 한 집의 최소 치킨거리
                    for (int k = 0; k < chickenCnt;
                        k++) { // **** 여기서 실수 있었다. M까지 보는 것이 아니라, chickenCnt까지 봐야함
                        if ((i & (1 << k)) != 0) {
                            Point chicken = chickenList.get(k);
                            minDis = Math.min(minDis, calDistance(house, chicken));
                        }
                    }
                    sum += minDis; // 한 집의 치킨 거리를 도시의 치킨거리 합에 더해주기
                }

                ans = Math.min(ans, sum); // 도시의 치킨 거리 중 최솟값 구하기
            }

        }
        System.out.println(ans);

    }

    private static int calDistance(Point house, Point chicken) {
        return Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
    }

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
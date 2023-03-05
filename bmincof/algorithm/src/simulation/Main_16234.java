package simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();

        int[][] map = new int[n][n];

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        // 초기 인구 수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 인구 이동이 발생하는 기간
        int day = 0;
        // 종료 조건 = 모든 연합의 크기가 1일 때 (아무와도 연합하지 않을 때)
        int maxUnion = -1;
        while(maxUnion != 1) {
            // bfs를 위한 큐
            Queue<Point> bfsQ = new LinkedList<>();
            // 연합 정보를 저장하기 위한 큐
            Queue<Point> unionQ = new LinkedList<>();

            // 종료 조건을 확인하기 위해 초기화
            maxUnion = 0;

            // 이번 이동에서 사용할 방문 정보 배열
            boolean[][] vis = new boolean[n][n];
            // 새로운 인구수를 기록할 지도
            int[][] newMap = new int[n][n];

            // 모든 땅을 돌면서 국경 열기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 방문한 적 없는 나라면 bfs 시작
                    if(!vis[i][j]) {

                        Point start = new Point(i, j);
                        vis[i][j] = true;
                        bfsQ.offer(start);
                        unionQ.offer(start);

                        // 연합을 이룰 국가들 모두 방문하기 (국경 열기)
                        // 연합의 땅 크기
                        int area = 1;
                        // 연합 인구수의 총합
                        int total = map[i][j];

                        // 연합 찾기 (국경 열기)
                        while(!bfsQ.isEmpty()) {
                            Point cur = bfsQ.poll();

                            for (int dir = 0; dir < 4; dir++) {
                                int nx = cur.x + dx[dir];
                                int ny = cur.y + dy[dir];

                                // 맵 밖이면 스킵
                                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                                // 지금 칸과의 인구수 차이 계산
                                int diff = Math.abs(map[nx][ny] - map[cur.x][cur.y]);
                                // 방문했거나 국경을 열 수 없으면 스킵
                                if(vis[nx][ny] || diff < l || diff > r) continue;

                                // 방문 표시
                                vis[nx][ny] = true;
                                // 연합 땅 + 1
                                area++;
                                // 연합 인구수 +
                                total += map[nx][ny];

                                Point nPoint = new Point(nx, ny);
                                bfsQ.offer(nPoint);
                                unionQ.offer(nPoint);
                            }
                        }
                        // 새로운 인구수 = 연합 땅의 평균 인구수
                        int population = total / area;
                        // 최대 연합 땅 크기 갱신
                        maxUnion = Math.max(maxUnion, area);

//                        System.out.println(unionQ);
                        // 연합 땅의 인구수를 모두 새로운 인구수로 교체
                        while(!unionQ.isEmpty()) {
                            Point cur = unionQ.poll();

                            // 새로운 지도에 저장, 기존 맵을 바꾸면 비교 조건이 흐트러짐
                            newMap[cur.x][cur.y] = population;
                        }
                    }
                }
            }

            // 맵 교체
            map = newMap;
            day++;
        }

        System.out.println(day - 1);
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

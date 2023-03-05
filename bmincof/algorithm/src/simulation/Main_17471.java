package boj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/*
    1. 각 구역을 A선거구에 넣거나 넣지 않거나를 true false로 결정 (최대 1024개의 경우)
    2. 모든 선거구를 선택했으면 A선거구와 B선거구 bfs
    3. 모두 연결되어있으면 인구수 차이 갱신
 */
public class Main_17471 {
    static int n, sizeA;
    static int minDiff = Integer.MAX_VALUE;
    static int[] population;
    static boolean[] isA;
    static boolean[][] isConnected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        // 각 구역의 인구수
        population = new int[n];
        // A선거구 인지
        isA = new boolean[n];
        // i에서 j로 연결되어있는지
        isConnected = new boolean[n][n];

        // 인구수 입력
        for (int i = 0; i < n; i++) {
            population[i] = sc.nextInt();
        }

        // 그래프 생성
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                isConnected[i][sc.nextInt() - 1] = true;
            }
        }

        // 비트마스킹으로 n개의 선거구 뽑기
        for (int i = 0; i < (1<<n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1<<j)) != 0) {
                    isA[j] = true;
                    sizeA++;
                }
            }
            // 모두 뽑았으면 bfs
            bfs();

            // bfs하고 초기화
            Arrays.fill(isA, false);
            sizeA = 0;
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static void bfs() {
        // 구역을 하나의 선거구에만 할당한 경우
        if(sizeA == 0 || sizeA == n) {
            return;
        }
        // 방문 배열
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        // A선거구에 포함된 구역들 중 연결된 곳의 수
        int count = 0;
        // A선거구의 인구수
        int popA = 0;
        for (int i = 0; i < n; i++) {
            if(isA[i]) {
                vis[i] = true;
                q.offer(i);
                count++;
                popA += population[i];

                while (!q.isEmpty()) {
                    int idx = q.poll();

                    for (int j = 0; j < n; j++) {
                        // 연결되어있는 곳이 아니면 생략
                        if(!isConnected[idx][j]) continue;
                        // A선거구가 아니거나 이미 방문한 곳이면 생략
                        if(vis[j] || !isA[j]) continue;

                        vis[j] = true;
                        q.offer(j);
                        count++;
                        popA += population[j];
                    }
                }

                // 연결된 곳을 모두 살펴봤으면 빠져나오기
                break;
            }
        }

        // 선거구끼리 모두 연결된게 아니라면 생략
        if(count != sizeA) return;

        count = 0;
        int popB = 0;
        for (int i = 0; i < n; i++) {
            if(!isA[i]) {
                vis[i] = true;
                q.offer(i);
                count++;
                popB += population[i];

                while (!q.isEmpty()) {
                    int idx = q.poll();

                    for (int j = 0; j < n; j++) {
                        // 연결되어있는 곳이 아니면 생략
                        if(!isConnected[idx][j]) continue;
                        // A선거구거나 이미 방문한 곳이면 생략
                        if(vis[j] || isA[j]) continue;

                        vis[j] = true;
                        q.offer(j);
                        count++;
                        popB += population[j];
                    }
                }

                // 연결된 곳을 모두 살펴봤으면 빠져나오기
                break;
            }
        }

        if(count != n - sizeA) return;

        int diff = Math.abs(popA - popB);
        minDiff = Math.min(minDiff, diff);
    }
}


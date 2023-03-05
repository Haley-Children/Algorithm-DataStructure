package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_20056 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dr = {-1,-1,0,1,1,1,0,-1};
        int[] dc = {0,1,1,1,0,-1,-1,-1};

        // 지도 크기
        int n = sc.nextInt();
        // 파이어볼의 개수
        int m = sc.nextInt();
        // 이동 명령 횟수
        int k = sc.nextInt();

        // 파이어볼의 정보 저장
        // r, c, 질량, 방향, 속력
        Queue<Fireball> fireballs = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int mass = sc.nextInt();
            int speed = sc.nextInt();
            int dir = sc.nextInt();

            fireballs.offer(new Fireball(r, c, mass, dir, speed));
        }

        // 파이어볼 이동
        for (int i = 0; i < k; i++) {
            Fireball[][] newMap = new Fireball[n][n];

            while (!fireballs.isEmpty()) {
                Fireball fireball = fireballs.poll();

                int nr = fireball.r + fireball.speed * dr[fireball.dir];
                int nc = fireball.c + fireball.speed * dc[fireball.dir];

                // 맵 밖으로 나가면 반대편 위치로
                if(nr < 0 || nc < 0) {
                    nr = (nr + 1000 * n) % n;
                    nc = (nc + 1000 * n) % n;
                }
                if (nr >= n || nc >= n) {
                    nr = nr % n;
                    nc = nc % n;
                }
//                System.out.println(fireball);
//                System.out.println("nr : " + nr + ", nc : " + nc);

                // 이동 완료
                fireball.r = nr;
                fireball.c = nc;

                if(newMap[nr][nc] == null) {
                    newMap[nr][nc] = fireball;
                // 파이어볼이 2개 이상이되면 합치기
                } else {
                    Fireball f = newMap[nr][nc];
                    f.mass += fireball.mass;
                    f.speed += fireball.speed;
                    if((f.dir + fireball.dir) % 2 == 1) {
                        f.dirMixed = true;
                    }
                    f.count++;
                }
            }

            // 파이어볼 분리하고 큐에 담기
            Queue<Fireball> newFireballs = new LinkedList<>();
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if(newMap[r][c] == null) continue;

                    Fireball fireball = newMap[r][c];

                    // 합쳐진 파이어볼이면
                    if(fireball.count > 1) {
                        int newMass = fireball.mass / 5;
                        if(newMass == 0) continue;

                        int newSpeed = fireball.speed / fireball.count;

                        // 파이어볼 나누기
                        for (int dir = 0; dir < 8; dir+=2) {
                            newFireballs.offer(new Fireball(r, c, newMass, fireball.dirMixed ? dir + 1 : dir, newSpeed));
                        }
                    } else {
                        newFireballs.offer(fireball);
                    }
                }
            }

            fireballs = newFireballs;
        }

        int totalMass = 0;
        while (!fireballs.isEmpty()) {
            totalMass += fireballs.poll().mass;
        }
        System.out.println(totalMass);
    }

    static class Fireball {
        int r, c, mass, dir, speed;
        int count = 1;
        boolean dirMixed = false;

        public Fireball(int r, int c, int mass, int dir, int speed) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.dir = dir;
            this.speed = speed;
        }
    }
}


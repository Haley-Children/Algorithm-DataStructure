package boj;

import java.util.*;

public class Main_21608 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        int n = sc.nextInt();
        int[][] wish = new int[n*n][5];
        // i번 학생이 몇 번째로 자리를 골랐는지
        int[] nums = new int[n*n+1];

        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < 5; j++) {
                wish[i][j] = sc.nextInt();
                if (j == 0) {
                    nums[wish[i][j]] = i;
                }
            }
        }

        int[][] map = new int[n][n];

        for (int person = 0; person < n*n; person++) {
            List<Seat> seats = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] == 0) {
                        // 좋아하는 학생의 수
                        int like = 0;
                        // 빈 칸의 수
                        int empty = 0;

                        // 인접방향 탐색
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];

                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                            // 비었으면 빈 칸의 수 +
                            if(map[nx][ny] == 0) {
                                empty++;
                            // 학생이 이미 있으면 좋아하는 사람인지 비교
                            } else {
                                for (int k = 1; k < 5; k++) {
                                    if(map[nx][ny] == wish[person][k]) {
                                        like++;
                                        break;
                                    }
                                }
                            }
                        }

                        seats.add(new Seat(i, j, like, empty));
                    }
                }
            }

            Collections.sort(seats);

            Seat seat = seats.get(0);
            map[seat.r][seat.c] = wish[person][0];
        }

        int totalScore = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    for (int k = 1; k < 5; k++) {
                        if(map[nx][ny] == wish[nums[map[i][j]]][k]) count++;
                    }
                }

                if (count == 0) {
                    totalScore += 0;
                } else if(count == 1) {
                    totalScore += 1;
                } else if (count == 2) {
                    totalScore += 10;
                } else if (count == 3) {
                    totalScore += 100;
                } else {
                    totalScore += 1000;
                }
            }
        }

        System.out.println(totalScore);

    }

    static class Seat implements Comparable<Seat> {
        int r, c;
        int like;
        int empty;

        public Seat(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Seat o) {
            if(this.like == o.like) {
                if(this.empty == o.empty) {
                    if(this.r == o.r) {
                        return this.c - o.c;
                    }
                    return this.r - o.r;
                }
                return o.empty - this.empty;
            }
            return o.like - this.like;
        }
    }
}


package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18869 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 우주의 개수
        int m = Integer.parseInt(st.nextToken());
        // 우주 내의 행성 개수
        int n = Integer.parseInt(st.nextToken());

        // [i]번 우주의 [j]번 행성의 좌표
        int[][] universes = new int[m][n];

        // 행성 입력받기
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int[] planets = new int[n];
            for(int j = 0; j < n; j++) {
                int planet = Integer.parseInt(st.nextToken());
                universes[i][j] = planets[j] = planet;
            }

            Arrays.parallelSort(planets);

            // lowerbound로 좌표 변경
            for(int j = 0; j < n; j++) {
                int start = 0;
                int end = n-1;
                while(start < end) {
                    int mid = (start + end + 1) / 2;

                    // mid의 행성이 j번째 행성보다 작으면 더 큰 영역에서도 찾아보기 (정답후보)
                    if(universes[i][j] >= planets[mid]) {
                        start = mid;
                    } else {
                        end = mid - 1;
                    }
                }
                // j번째 행성보다 작은 행성의 개수
                universes[i][j] = start;
            }
        }

        int pair = 0;
        // 가능한 우주 조합마다 비교
        for(int i = 0; i < m-1; i++) {
            for(int j = i+1; j < m; j++) {
                boolean isMultiverse = true;
                // 하나라도 순서가 다르면 멀티버스가 아님
                for(int k = 0; k < n; k++) {
                    if(universes[i][k] != universes[j][k]) {
                        isMultiverse = false;
                        break;
                    }
                }

                if(isMultiverse) pair++;
            }
        }

        System.out.println(pair);
    }

}
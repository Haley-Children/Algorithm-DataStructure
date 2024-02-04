package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_1차원폭발게임 {

    static int N, M, endOfArr;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        endOfArr = N;

        // 폭발하지 않았으면 시뮬레이션 종료 / 폭발했으면, 더 폭발 가능한지 체크
        boolean didExplode;
        int curIdx;
        do {
            didExplode = false;
            curIdx = 0;
            while (curIdx < endOfArr) {
                int endIdx = getEndIdxOfExplosion(curIdx, arr[curIdx]);

                if (endIdx - curIdx + 1 >= M) {
                    // 연속한 숫자의 개수가 m개 이상이면 폭탄이 터질 수 있는 경우 해당 부분의 수열을 잘라내기
                    cutArr(curIdx, endIdx);
                    // 폭탄 터졌음을 기록
                    didExplode = true;
                } else {
                    // 폭탄이 터질 수 없는 경우 순회할 필요가 없는 원소에 대한 탐색 생략
                    curIdx = endIdx + 1;
                }
            }
        }
        while (didExplode);

        System.out.println(endOfArr);
        for (int i = 0; i < endOfArr; i++) {
            System.out.println(arr[i]);
        }



    }

    private static void cutArr(int curIdx, int endIdx) {
        int cutLen = endIdx - curIdx + 1;
        for (int i = endIdx+1; i < endOfArr; i++) {
            arr[i - cutLen] = arr[i];
        }
        endOfArr -= cutLen;
    }

    private static int getEndIdxOfExplosion(int curIdx, int num) {
        int end = curIdx + 1;
        while (end < endOfArr) {
            if (arr[end] == num) {
                end++;
            } else {
                break;
            }
        }
        return end - 1;
    }

}

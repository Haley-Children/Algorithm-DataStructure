package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_1차원젠가 {

    static int[] arr; // 원본 배열
    static int arrLen; // 원본 배열의 길이
    static int[] tmpArr; // tmp 배열
    static int tmpArrLen; // tmp 배열의 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 처음 블럭의 수
        arr = new int[n];
        arrLen = n;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringTokenizer st;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken()) - 1;
            int endIdx = Integer.parseInt(st.nextToken()) - 1;
            tmpArr = new int[arrLen];
            tmpArrLen = 0;

            for (int j = 0; j < arrLen; j++) {
                if (startIdx <= j && j <= endIdx) {
                    continue;
                }
                tmpArr[tmpArrLen++] = arr[j];
            }

            arr = tmpArr;
            arrLen = tmpArrLen;
        }

        System.out.println(arrLen);
        for (int i = 0; i < arrLen; i++) {
            System.out.println(arr[i]);
        }
    }

}

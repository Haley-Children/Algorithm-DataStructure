package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1822_차집합 {
    static int A, B;
    static int[] arr, brr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[A];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        brr = new int[B];
        for (int i = 0; i < B; i++) {
            brr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.parallelSort(brr);

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> ansList = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < A; i++) {
            int t = arr[i];
            if (Arrays.binarySearch(brr, t) < 0) {
                cnt++;
                ansList.add(t);
            }
        }

        Collections.sort(ansList);

        System.out.println(cnt);
        for (int i = 0; i < ansList.size(); i++) {
            sb.append(ansList.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
}

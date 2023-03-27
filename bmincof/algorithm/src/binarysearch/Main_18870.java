package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_18870 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        HashSet<Integer> set = new HashSet<>();
        List<Integer> sorted = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(set.contains(arr[i])) continue;
            set.add(arr[i]);
            sorted.add(arr[i]);
        }

        Collections.sort(sorted);

        for (int i = 0; i < n; i++) {
            sb.append(Collections.binarySearch(sorted, arr[i])).append(" ");
        }

        System.out.println(sb);
    }
}

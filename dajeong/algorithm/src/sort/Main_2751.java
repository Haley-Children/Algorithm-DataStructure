package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2751 {
    static int n;
    static int[] tmp,arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0, n);
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }




    private static void mergeSort(int st, int en) {
        // base condition
        if (st +1 == en) return;

        int middle = (st+en)/2;
        mergeSort(st, middle);
        mergeSort(middle, en);
        merge(st, en);
    }

    private static void merge(int st, int en) {
        int lt = st;
        int mid = (st+en)/2;
        int rt = mid;

        for (int i = st; i < en; i++) {
            if (rt == en) tmp[i] = arr[lt++];
            else if (lt == mid) {
                tmp[i] = arr[rt++];
            } else if (arr[lt] <= arr[rt]) {
                tmp[i] =arr[lt++];
            } else {
                tmp[i] = arr[rt++];
            }
        }

        for (int i = st; i < en; i++) {
            arr[i] = tmp[i];
        }
    }
}

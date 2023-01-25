package sort;

import java.util.Arrays;

// 버블정렬 (시간복잡도: O(N^2))
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {-2, 2, 4, 6, 13};
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("버블정렬 = " + Arrays.toString(arr));
    }

}

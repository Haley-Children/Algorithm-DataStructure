package sort;

import java.util.Arrays;

// 선택정렬 구현 (시간복잡도: O(N^2))
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {3,2,7,116,62,235,1,23,55,77};
        int n = 10;

        for (int i = n-1; i > 0; i--) {
            int mxidx = 0;
            for (int j = 1; j <= i; j++) {
                if (arr[j] > arr[mxidx]) mxidx = j;
            }
            int temp = arr[i];
            arr[i] = arr[mxidx];
            arr[mxidx] = temp;
        }

        System.out.println("선택정렬 결과 = " + Arrays.toString(arr));
    }
}

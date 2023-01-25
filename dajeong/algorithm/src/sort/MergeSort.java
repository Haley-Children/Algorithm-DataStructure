package sort;

import java.util.Arrays;

// 머지 소트 (시간복잡도: O(N*logN))
public class MergeSort {

    static int n = 10;
    static int[] arr = {15, 25, 22, 357, 16, 23, -53, 12, 46, 3};
    static int[] tmp = new int[arr.length];

    // arr[st:en]을 정렬하고 싶다.
    private static void mergeSort(int st, int en) {
        if (st+1 == en) return; // 길이가 1인 경우
        int mid = (st+en)/2;
        // 분할
        mergeSort(st, mid);
        mergeSort(mid, en);
        // 정렬 및 합치기
        merge(st, en);
    }
    // mid = (st+en)/2라고 할 때 arr[st:mid], arr[mid:en]은 이미 정렬이 되어있는 상태일 때 arr[st:mid]와 arr[mid:en]을 합친다.
    private static void merge(int st, int en) {
        int mid = (st+en)/2;
        int lidx = st;
        int ridx = mid;

        for (int i = st; i < en; i++) {
            if (ridx == en) tmp[i] = arr[lidx++];
            else if (lidx == mid) {
                tmp[i] = arr[ridx++];
            } else if (arr[lidx] <= arr[ridx]) { // stable sort
                tmp[i] = arr[lidx++];
            } else {
                tmp[i] = arr[ridx++];
            }
        }

        for (int i = st; i < en; i++) {
            arr[i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        mergeSort(0, n);
        System.out.println(Arrays.toString(arr));
    }
}

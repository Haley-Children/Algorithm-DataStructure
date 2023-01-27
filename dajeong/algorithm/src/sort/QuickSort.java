package sort;

// 머지 소트
// 시간복잡도: O(N*logN), 최악의 경우: O(N^2). 단, 평균적으로 Merge Sort보다 빠름.
// 추가적으로 필요한 공간(Overhead): O(1)
// Stable Sort 여부: X
    // ** 최악의 경우: O(N^2)가 나오는 경우
    // 1 2 3 4 5 6 7 8 을 퀵소트로 정렬하면 pivot은 항상 맨 왼쪽에 위치하게 되고 그로 인해 logN이 아닌 n만큼 연산하게 된다.
    // 따라서 리스트가 오름차순이거나 내림차순일 때 시간복잡도를 계산하면 최악의 경우 O(N^2)이 될 수 있다.
    // => 라이브러리가 아닌 직접 정렬을 구현해야할 때 "절대" 퀵소트를 쓰지 말자!

public class QuickSort {
    static int n;
    static int[] arr;

    public static void main(String[] args) {
        arr = new int[]{15, 25, 22, 357, 16, 23, -53, 12, 46, 3};
        n = arr.length;
        StringBuilder sb = new StringBuilder();
        quickSort(0, n);
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);

    }

    private static void quickSort(int st, int en) {
        // 수열의 길이가 1 이하이면 함수 종료.(base condition)
        if (en - st <= 1) return;

        // 제일 앞의 것을 pivot으로 잡는다. 임의의 값을 잡고 arr[st]와 swap해도 상관없음.
        int pivot = arr[st];
        int l = st + 1;
        int r = en - 1;

        while (true) {
            // l, r 등호 주의!! l == r 일 때 진행이 되어야 한다.
            while (l <= r && arr[l] <= pivot) l++;
            while (l <= r && arr[r] >= pivot) r--;
            if (l > r) break; // l과 r이 역전되는 그 즉시 탈출
            // arr[l]과 arr[r] swap
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        int tmp = arr[st];
        arr[st] = arr[r];
        arr[r] = tmp;

        // 재귀를 이용해서 제자리를 찾은 arr[r] 기준으로 왼쪽(작은 수들)과 오른쪽(큰 수들)도 똑같이 퀵소트 진행
        quickSort(st, r);
        quickSort(r+1, en);
    }
}

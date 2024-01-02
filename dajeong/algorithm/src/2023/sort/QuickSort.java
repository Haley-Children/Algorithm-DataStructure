package sort;

import java.util.Arrays;

// 퀵 소트 (시간복잡도: O(N*logN)/ 최악의 경우 O(N^2))
// 직접 구현해서 써야할 때는 퀵소트 쓰지 말것!!
// In-place Sort: 추가적인 공간을 쓰지 않는다 (포인터 이용)
public class QuickSort {

	static int n = 10;
	static int[] arr = {15, 25, 22, 357, 16, 23, -53, 12, 46, 3};
	private static void quickSort(int st, int en) {
		if (en <= st+1) return;
		int pivot = arr[st]; // 첫번째 원소를 피봇으로 설정
		int l = st + 1;
		int r = en - 1;

		while (true) {
			while (l <= r && arr[l] <= pivot) l++;
			while (l <= r && arr[r] >= pivot) r++;
			if (l > r) break; // l > r일 때 종료
			int tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
		}

		// l > r일 때 피봇과 arr[r] swap
		int tmp = arr[st];
		arr[r] = arr[st];
		arr[st] = tmp;

		// 재귀적으로 피봇 이전과 이후를 동일하게 퀵소트 시행
		quickSort(st, r);
		quickSort(r+1, en);

	}
	public static void main(String[] args) {
		quickSort(0, n);
		System.out.println(Arrays.toString(arr));
	}
}

// LeetCode 1. Two Sum 설명 & 자바로 구현
// https://youtu.be/FHphOv2mmIA
// 정수 값이 담긴 array가 하나 있다.
// 배열 안의 두 숫자를 더해서 어떤 특정한 값이 나오면
// 그 두 숫자의 array index를 리턴하라.
// 배열 안에는 정답이 하나 뿐임이 보장된다.
// 답으로 같은 값을 두 번 쓸 수 없다. (두 개의 인덱스를 리턴해야함)

package arraysAndStrings.twoSum;

import java.util.*;

class Solution {
	// Solution1) Brute Force : 이중 포문으로 돌면서 찾기
	// 시간복잡도 : O(n^2)
//	public int[] twoSum(int[] nums, int target) {
//		for (int i=0; i<nums.length; i++) {
//			for (int j=i+1; j<nums.length; j++) {
//				if (target == nums[i] + nums[j]) {
//					return new int[] {i, j};
//				}
//			}
//		}
//		throw new IllegalArgumentException("No two sum solution");
//	}
	
	// Solution2) Hash Table을 사용하는 방법
	// 한번 돌면서 저장 + 다시 돌면서 (target - 현재 확인 중인 값)이 키로 있는지 확인
	// 시간복잡도 : O(2n) = O(n)
//	public int[] twoSum(int[] nums, int target) {
//		HashMap<Integer, Integer> map = new HashMap<>();
//		for (int i=0; i<nums.length; i++) map.put(nums[i], i);
//		for (int i1=0; i1<nums.length; i1++) {
//			Integer i2 = map.get(target - nums[i1]);
//			if (i2 != null && i1 != i2) return new int[] {i1, i2};
//		}
//		throw new IllegalArgumentException("No two sum solution");
//	}
	
	// Solution3) Hash Table을 사용하되, 돌면서 바로 찾기
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] {map.get(target - nums[i]), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
}

public class TwoSum {
	public static void main(String[] args) {
		int[] nums = {6,4,3,8,7,5,2};
		Solution sol = new Solution();
		int[] result = sol.twoSum(nums, 5);
		System.out.println(result[0] + ", " + result[1]); // 2, 6
	}
}

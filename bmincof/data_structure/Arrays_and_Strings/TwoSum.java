import java.util.Arrays;
import java.util.HashMap;

// Leetcode의 TwoSum 문제
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {6, 4, 3, 8, 7, 5, 2};
        Solution sol = new Solution();

        // 합이 5가 되는 값들의 인덱스 2개 출력
        int[] result = sol.twoSum(nums, 5);
        System.out.println(Arrays.toString(result));

        int[] result2 = sol.twoSum2(nums, 5);
        System.out.println(Arrays.toString(result2));

        int[] result3 = sol.twoSum3(nums, 5);
        System.out.println(Arrays.toString(result3));


    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(target == nums[i] + nums[j]) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalStateException("No two sum solution");
    }

    // 미리 등록 후 재탐색 => 2N번
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 해시 맵에 각 숫자를 미리 등록하기
        for(int i = 0; i < nums.length; i++) map.put(nums[i], i);
        for(int i1 = 0; i1 < nums.length; i1++) {
            Integer i2 = map.get(target - nums[i1]);
            if(i2 != null && i1 != i2) return new int[] {i1, i2};
        }
        throw new IllegalStateException("No two sum solution");
    }

    // N번 만에 확인
    public int[] twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 탐색하면서 등록
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i]))
                return new int[] {map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        throw new IllegalStateException("No two sum solution");
    }

}

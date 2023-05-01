// 문자열 안의 문자들이 고유한지 확인하기
// https://youtu.be/xnGyjBptpZ4
// 주어진 문자열의 문자들이 모두 고유한지를 확인하는 함수를 구현하시오
// 만약 별도의 저장공간을 사용하지 못하는 경우에는 어떻게 해결할지도 추가로 설명하시오

package arraysAndStrings.checkUnique;

import java.util.HashMap;

public class CheckUnique {
	
	private static boolean isUnique(String str) {
		
		// 1. 문자열이 128개의 ASCII문자로만 이루어진 경우
		// boolean 배열 사용
//		if (str.length() > 128) return false;
//		boolean[] char_set = new boolean[128];
//		for (int i=0; i<str.length(); i++) {
//			int val = str.charAt(i);
//			if (char_set[val]) {
//				return false;
//			}
//			char_set[val] = true;
//		}
//		return true;
		
		// 2. Unicode 문자로 이루어져있다면 2^20 + 2^16 = 1114112가지의 문자
		// HashMap 사용
		HashMap<Integer, Boolean> hashmap = new HashMap<>();
		for (int i=0; i<str.length(); i++) {
			int c = str.charAt(i);
			if (hashmap.containsKey(c)) {
				return false;
			}
			hashmap.put(c, true);
		}
		return true;
		
		// 3. 문자열에 들어가는 문자가 알파벳 소문자만 있다는 가정하에 별도의 배열 사용 없이
		// 비트연산자 사용
//		int checker = 0;
//		for (int i=0; i<str.length(); i++) {
//			int val = str.charAt(i) - 'a';
//			if ((checker & (1 << val)) > 0) {
//				return false;
//			}
//			checker |= (1 << val);
//		}
//		return true;
		
		// 4. 추가 저장공간을 사용할 수 없는 경우
		
		// 4-1. 이중포문 돌리는 방식
		// O(n^2)
		
		// 4-2. 입력데이터를 변형해도 된다면 정렬한뒤에 인접한 값끼리 비교
		// O(nlogn)
	}
	
	public static void main(String[] args) {
		System.out.println(isUnique("abcdefgghijk"));	// false
		System.out.println(isUnique("abcdefghijk"));	// true
	}
}

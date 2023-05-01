// 두개의 문자열이 서로 치환(Permutation)인지 알아내기
// https://youtu.be/6I1L8oRl3FA
// 공백도 문자로 인정하고, 대소문자를 구별한다

package arraysAndStrings.checkPermutation;

public class CheckPermutation {

	// Solution1. 두개의 문자열을 정렬하고 앞에서부터 한자씩 비교
//	private static String sort(String s) {
//		char[] content = s.toCharArray();
//		java.util.Arrays.sort(content);
//		return new String(content);
//	}
//	private static boolean permutataion(String s, String t) {
//		if (s.length() != t.length()) return false;
//		return sort(s).equals(sort(t));
//	}
	
	// Solution2. 기본 아스키문자 128개로 구성되었다고 가정하고 배열방에 저장
	private static boolean permutataion(String s, String t) {
		if (s.length() != t.length()) return false;
		int[] letters = new int[128];
		for (int i=0; i<s.length(); i++) {
			letters[s.charAt(i)]++;
		}
		for (int i=0; i<t.length(); i++) {
			letters[t.charAt(i)]--;
			if (letters[t.charAt(i)] < 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(permutataion("ABC", "BCA"));	// true
		System.out.println(permutataion("ABC", "BDA"));	// false
	}
}

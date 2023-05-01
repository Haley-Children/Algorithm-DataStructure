// 단한번만 편집된 문자열인지 알아내기
// https://youtu.be/H3zeBMt_fmE
// 세가지 문자열 편집기능이 있다.
// 문자를 한개 삽입하는 기능, 문자를 한개 삭제하는 기능,
// 그리고 하나의 문자를 교체하는 기능이 있다.
// 주어진 두개의 문자열이 편집기능을 단 한번만 이용한 경우
// (또는 한번도 이용하지 않은 경우)인지를 알아내는 함수를 구현하시오.

package arraysAndStrings.checkOneAway;

public class CheckOneAway {
	public static void main(String[] args) {
		System.out.println(isOneAway("pal", "pale")); // true
		System.out.println(isOneAway("pale", "pal")); // true
		System.out.println(isOneAway("pale", "bale")); // true

		System.out.println(isOneAway("pal", "pales")); // false
		System.out.println(isOneAway("pale", "pel")); // false
		System.out.println(isOneAway("pale", "bake")); // false
	}
	private static boolean isOneAway(String s1, String s2) {
		String ss, ls;
		if (s1.length() < s2.length()) {
			ss = s1;
			ls = s2;
		}else {
			ss = s2;
			ls = s1;
		}
		if (ls.length() - ss.length() > 1) return false;
		boolean flag = false;
		for (int i=0, j=0; i<ss.length(); i++, j++) {
			if (ss.charAt(i) != ls.charAt(j)) {
				if (flag) {
					return false;
				}
				flag = true;
				if (ss.length() != ls.length()) {
					j++;
				}
			}
		}
		return true;
	}
}

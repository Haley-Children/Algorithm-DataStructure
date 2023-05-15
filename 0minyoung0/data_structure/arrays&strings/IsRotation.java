// isSubstring()으로 문자열 회전여부 알아내기
// https://youtu.be/mK1nPP413SA
// isSubstring()을 단 한번만 호출하여
// 두 문자열 s1, s2가 회전 관계인지 판별하는 알고리즘을 구현하시오.
// 이때 회전 관계란 아래와 같이 문자열 앞의 일부를 뒤로 보내서 같은 문자열이 되는 관계를 말함.
// string, trings, ringst, ingstr, ngstri, gstrin

package arraysAndStrings.isRotation;

public class IsRotation {
	public static void main(String[] args) {
		System.out.println(isRotation("string", "ringst")); // true
		System.out.println(isRotation("string", "ingstr")); // true
		System.out.println(isRotation("string", "ingstn")); // false
		System.out.println(isRotation("string", "ringstr")); // false
	}
	private static boolean isRotation(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		return isSubstring(s1 + s1, s2);
	}
	private static boolean isSubstring(String s1, String s2) {
		return s1.contains(s2);
	}
}

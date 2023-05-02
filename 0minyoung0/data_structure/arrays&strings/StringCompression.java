// 문자열 압축(Compression)하기
// https://youtu.be/BPEeI4uCMwg
// 문자열을 압축하고, 압축한 결과가 더 크면 원본을 반환

package arraysAndStrings.stringCompression;

public class StringCompression {
	public static void main(String[] args) {
		System.out.println(compressString("aabbbbbccccdd")); // a2b5c4d2
		System.out.println(compressString("abcd")); // abcd
	}

	// 압축 문자열과 원본 문자열을 비교해서 더 짧은 문자열 반환
	private static String compressString(String str) {
		String newStr = compress(str);
		return str.length() < newStr.length() ? str : newStr;
	}

	// 문자열 압축하기
	private static String compress(String str) {
		int count = 0;
		StringBuilder newString = new StringBuilder(getTotal(str));
		for (int i = 0; i < str.length(); i++) {
			count++;
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				newString.append(str.charAt(i));
				newString.append(count);
				count = 0;
			}
		}
		return newString.toString();
	}

	// 압축 문자열의 문자 개수를 미리 세기 (스트링빌더 사이즈 미리 할당)
	private static int getTotal(String str) {
		int count = 0;
		int total = 0;
		for (int i = 0; i < str.length(); i++) {
			count++;
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				total += 1 + String.valueOf(count).length();
				count = 0;
			}
		}
		return total;
	}
}

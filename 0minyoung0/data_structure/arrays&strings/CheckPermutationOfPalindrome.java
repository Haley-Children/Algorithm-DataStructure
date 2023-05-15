// 문자열이 회문(palindrome)의 치환(permutation)인지 확인하기
// https://youtu.be/_3_580GhBYc
// 주어진 문자열이 회문(palindrome)을 만들수 있는 문자열의
// 치환(permutation)인지를 확인하는 함수를 만드시오.

package arraysAndStrings.checkPermutationOfPalindrome;

public class CheckPermutationOfPalindrome {
	public static void main(String[] args) {
		System.out.println(isPermutationOfPalindrome("aa bb cc dd")); // true
		System.out.println(isPermutationOfPalindrome("aa bb cc dd e")); // true
		System.out.println(isPermutationOfPalindrome("aa bb cc dd e fff")); // false
	}

	private static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if (a <= val && val <= z) {
			return val - a;
		}
		return -1;
	}

	// Solution1 : 문자의 개수를 각각 배열방에 저장해서 홀수개인 문자가 1개 이하면 true
//	private static boolean isPermutationOfPalindrome(String s) {
//		int[] table = buildCharFrequencyTable(s);
//		return checkMaxOneOdd(table);
//	}
//	
//	private static int[] buildCharFrequencyTable(String s) {
//		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
//		for (char c : s.toCharArray()) {
//			int x = getCharNumber(c);
//			if (x != -1) {
//				table[x]++;
//			}
//		}
//		return table;
//	}
//	
//	private static boolean checkMaxOneOdd(int[] table) {
//		boolean foundOdd = false;
//		for (int count : table) {
//			if (count % 2 == 1) {
//				if (!foundOdd) {
//					foundOdd = true;
//				}else {
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	// solution2 : 문자열을 돌면서 홀수 개수를 바로 카운팅
//	private static boolean isPermutationOfPalindrome(String s) {
//		int countOdd = 0;
//		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
//		for (char c : s.toCharArray()) {
//			int x = getCharNumber(c);
//			if (x != -1) {
//				table[x]++;
//				if (table[x] % 2 == 1) {
//					countOdd++;
//				} else {
//					countOdd--;
//				}
//			}
//		}
//		return countOdd <= 1;
//	}

	// solution3 : 비트 연산자 사용
	private static boolean isPermutationOfPalindrome(String s) {
		int bitVector = createBitVector(s);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}

	private static int createBitVector(String s) {
		int bitVector = 0;
		for (char c : s.toCharArray()) {
			int x = getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}

	private static int toggle(int bitVector, int index) {
		if (index < 0)
			return bitVector;
		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			bitVector |= mask;
		} else {
			bitVector &= ~mask;
		}
		return bitVector;
	}

	private static boolean checkExactlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}
}

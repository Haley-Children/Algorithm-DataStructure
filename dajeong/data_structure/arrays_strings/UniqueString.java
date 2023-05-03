package arrays_strings;

import java.util.HashMap;

/**
 * 문제) 주어진 문자열의 문자들이 모두 고유한지를 확인하는 함수를 구현하시오.
 * 만약, 별도의 저장 공간을 사용하지 못하는 경우에는 어떻게 해결할지도 추가로 설명하시오.
 */
public class UniqueString {

    public static void main(String[] args) {
        System.out.println("** 아스키코드 방식 **");
        System.out.println(isUniqueAscii("abcdefgghijk"));
        System.out.println(isUniqueAscii("abcdefghijk"));
        System.out.println();
        System.out.println("** 유니코드 방식 **");
        System.out.println(isUniqueUnicode("abcdefgghijk"));
        System.out.println(isUniqueUnicode("abcdefghijk"));
        System.out.println();
        System.out.println("** 비트 연산 방식**");
        System.out.println(isUniqueBit("abcdefgghijk"));
        System.out.println(isUniqueBit("abcdefghijk"));
    }

    /**
     * 해당 문자열이 ASCII로만 이루어진 문자라는 가정)
     * 128개의 boolean 배열로 문자열 내 문자들이 유니크한지 체크하는 메서드
     *
     * @param str : 확인할 문자열
     * @return : 유니크 여부
     */
    private static boolean isUniqueAscii(String str) {
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * 해당 문자열이 unicode라는 가정)
     * unicode -> 2^20 + 2^16 = 1,114,112
     * HashMap으로 중복 여부 확인
     *
     * @param str : 확인할 문자열
     * @return : 유니크 여부
     */
    public static boolean isUniqueUnicode(String str) {
        HashMap<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (hashMap.containsKey(c)) {
                return false;
            }
            hashMap.put(c, true);
        }
        return true;
    }


    /**
     * 해당 문자열의 문자들이 소문자로만 이루어졌을 경우 (26개) Bit Operation 방식 사용해서 중복 여부 확인
     * Bit Operation: int의 bit 수만큼 확인 가능 (32개)
     *
     * @param str : 확인할 문자열
     * @return : 유니크 여부
     */

    public static boolean isUniqueBit(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1<<val);
        }
        return true;
    }

    /**
     * No DataStructure 방식
     * 1) O(n^2)
     *  - 투 포인터
     * 2) O(nlogn)
     *  - 정렬 후 옆 자리 확인
     */

}

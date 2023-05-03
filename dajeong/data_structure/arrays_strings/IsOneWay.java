package arrays_strings;

/**
 * 세 가지 문자열 편집 기능이 있다.
 * 1. 문자를 한 개 삽입하는 기능
 * 2. 문자를 한 개 삭제하는 기능
 * 3. 하나의 문자를 교체할 수 있는 기능
 *
 * 주어진 두 개의 문자열이 편집 기능을 단 한번만 이용한 경우 (또는 한번도 이용하지 않은 경우)인지를 알아내는 함수를 구현하시오.
 */
public class IsOneWay {

    public static void main(String[] args) {
        System.out.println(isOneWay("pal", "pale"));
        System.out.println(isOneWay("pale", "pal"));
        System.out.println(isOneWay("pale", "bale"));
        System.out.println(isOneWay("pal", "pales"));
        System.out.println(isOneWay("pale", "pel"));
        System.out.println(isOneWay("pale", "bake"));
    }

    private static boolean isOneWay(String s1, String s2) {
        String ss, ls;
        if (s1.length() < s2.length()) {
            ss = s1;
            ls = s2;
        } else {
            ss = s2;
            ls = s1;
        }
        if (ls.length() - ss.length() > 1) {
            return false;
        }
        boolean flag = false;
        // return false에 걸리지 않는다면 다른 값이 나오지 않거나, 하나만 다른 경우이므로 true 반환
        for (int i = 0, j = 0; i < ss.length(); i++, j++) {
            if (ss.charAt(i) != ls.charAt(j)) {
                if (flag) return false;
                flag = true;
                if (ss.length() != ls.length()) {
                    j++;
                }
            }
        }
        return true;
    }

}

import java.util.HashMap;

// 문자열의 문자들이 모두 고유한지 확인
public class IsUniqueImpl {
    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));

        System.out.println(isUnique2("abcdefgghijk"));
        System.out.println(isUnique2("abcdefghijk"));

        System.out.println(isUnique3("abcdefgghijk"));
        System.out.println(isUnique3("abcdefghijk"));
    }

    // 아스키
    private static boolean isUnique(String str) {
        if(str.length() > 128) return false;
        boolean[] char_set = new boolean[128];

        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if(char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }

        return true;
    }

    // 확장 아스키
    // 값의 범위가 크므로 해시맵으로 저장
    // 배열 또는 해시셋을 이용해도 큰 문제는 없어보임
    private static boolean isUnique2(String str) {
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if(hashMap.containsKey(c)) {
                return false;
            }
            hashMap.put(c, true);
        }
        return true;
    }

    // 알파벳 소문자로만 이루어져있을 경우
    // 정수 값 하나에 모든 상태를 저장 (비트마스킹)
    private static boolean isUnique3(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if((checker & (1<<val)) > 0) {
                return false;
            }
            checker |= (1<<val);
        }
        return true;
    }

    // 자료구조를 이용하지 않고 진행하는 방법
    // 문자 하나 고르고 남은 문자열에서 같은 문자 찾기 = O(n^2)
    // 문자열의 문자를 정렬하고 인접한 문자만 체크하기 = O(n)
}

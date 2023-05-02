import java.util.Arrays;

public class IsPermutationImpl {
    public static void main(String[] args) {
        System.out.println(permutation("ABC", "BCA"));
        System.out.println(permutation("ABC", "BDA"));

        System.out.println(permutation2("ABC", "BCA"));
        System.out.println(permutation2("ABC", "BDA"));
    }

    private static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
    // 두 문자의 길이가 다르면 다른 치환한 것이 아님
    // 두 문자열을 사전순 정렬했을 때 결과가 같으면 같은 수의 구성문자
    // 즉, 치환한 문자
    private static boolean permutation(String s, String t) {
        if(s.length() != t.length()) return false;

        return sort(s).equals(sort(t));
    }

    private static boolean permutation2(String s, String t) {
        if(s.length() != t.length()) return false;

        // ASCII 가정
        int[] letters = new int[128];

        for(int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }

        // 길이가 같고 치환 문자열이라면 구성 문자의 개수가 같아야 한다.
        for(int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            // 즉 개수가 더 많은 것이 있다면 치환문자열이 아님
            // 글자수가 같으므로 치환문자열이 아니라면 마이너스가 반드시 하나는 존재
            if(letters[t.charAt(i)] < 0) return false;
        }

        return true;
    }
}

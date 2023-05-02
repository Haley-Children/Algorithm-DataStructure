// 한 글자 수정, 삭제, 추가의 편집 기능이 있을 때
// 단 한 번만 편집된 문자열인지 판단하는 메서드를 구현
public class IsModifiedOnce {
    public static void main(String[] args) {
        System.out.println(isOneAway("pal", "pale"));
        System.out.println(isOneAway("pale", "pal"));
        System.out.println(isOneAway("pale", "bale"));

        System.out.println(isOneAway("pal", "pales"));
        System.out.println(isOneAway("pale", "pel"));
        System.out.println(isOneAway("pale", "bake"));
    }

    private static boolean isOneAway(String s1, String s2) {
        // 짧은 문자열, 긴 문자열
        String ss, ls;
        if(s1.length() < s2.length()) {
            ss = s1;
            ls = s2;
        } else {
            ss = s2;
            ls = s1;
        }
        // 한 글자보다 크게 차이나면 한 번보다 많이 편집된 것
        if(ls.length() - ss.length() > 1) return false;

        // 글자수가 다른 것이 발견되었다면 true
        boolean flag = false;

        // 짧은 문자열만큼 비교
        for(int i = 0, j = 0; i < ss.length(); i++, j++) {
            // 문자가 다르다면
            if(ss.charAt(i) != ls.charAt(j)) {
                // 두 번째이면 실격
                if(flag) {
                   return false;
                }
                // 처음이면 flag를 true로 설정하고 재탐색
                flag = true;
                if(ss.length() != ls.length()) {
                    j++;
                }
            }
        }

        return true;
    }
}

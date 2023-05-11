// 원본의 문자열을 회전시킨 문자열인지 isSubstring 한번으로 확인하기
public class IsRotatedImpl {
    public static void main(String[] args) {
        System.out.println(isRotation("string", "ringst"));
        System.out.println(isRotation("string", "ingstr"));
        System.out.println(isRotation("string", "ingstn"));
        System.out.println(isRotation("string", "ringstr"));
    }

    private static boolean isRotation(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        // ex) s1 : ingstr, s2 : string
        // => s1 + s1 = ing(string)str
        return isSubstring(s1 + s1, s2);
    }

    private static boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }
}

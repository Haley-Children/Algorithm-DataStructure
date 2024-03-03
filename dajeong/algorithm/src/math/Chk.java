package math;

// 연립합동방정식
public class Chk {

    public static void main(String[] args) {
        int n = 30;
        int ans = chk(n);
        System.out.println(ans);
    }

    private static int chk(int n) {
        for (int i = 3; i < n; i += 6) {
            if (i % 5 == 2) {
                return i;
            }
        }
        return -1; // 불가능할 경우 -1 리턴
    }

}

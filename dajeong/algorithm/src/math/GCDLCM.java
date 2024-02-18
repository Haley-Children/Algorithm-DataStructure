package math;

// 최대공약수: 유클리드 호제법
public class GCDLCM {

    public static void main(String[] args) {
        int a = 20;
        int b = 12;

        System.out.println("**** 최대 공약수");
        int gcd = gcd(a, b);
        System.out.println(gcd);

        System.out.println("**** 최소 공배수");
        int lcm = lcm(a, b);
        System.out.println(lcm);
    }

    // 최소공배수 구하기
    // ** (a * b) 먼저 계산한 후 gcd를 나누지 않은 이유: int overflow
    private static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }


    // 최대공약수 구하기
    // O(log(max(a,b)))
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b,a % b);
    }

}

// 문자열의 길이가 충분하게 주어졌을 때
// 문자열을 URL로 인코딩하기 (공백을 %20으로)
public class EncodeUrlImpl {
    public static void main(String[] args) {
        System.out.println(URLify("Mr John Smith        ", 13));
    }
    private static String URLify(String str, int len) {
        return URLify(str.toCharArray(), len);
    }

    private static String URLify(char[] str, int len) {
        int spaces = 0;
        // 공백의 수를 세기
        for(int i = 0; i < len; i++) {
            if(str[i] == ' ') spaces++;
        }
        // 인코딩시 공백의 수마다 2칸이 추가로 필요
        int index = len + spaces * 2 - 1;

        // 문자열의 뒤부터 시작해서 투포인터로 공백을 치환해가기
        for(int p = len - 1; p >= 0; p--) {
            if(str[p] == ' ') {
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            } else {
                str[index--] = str[p];
            }
        }

        return new String(str);
    }
}

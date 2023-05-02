public class CompressionImpl {
    public static void main(String[] args) {
        System.out.println(compressString("aabbbbbccccdd"));
        System.out.println(compressString("abcd"));
    }

    private static String compressString(String str) {
        // 문자열 압축하기
        String newStr = compress(str);
        // 압축한 결과가 기존보다 더 길어진다면 기존 문자열을 반환
        return str.length() < newStr.length() ? str : newStr;
    }

    // 문자마다 연속으로 몇 번 등장하는지를 문자 옆에 붙이기
    // ex) aaabb = a3b2
    private static String compress(String str) {
        int count = 0;
        StringBuilder newString = new StringBuilder(getTotal(str));
        for(int i = 0; i < str.length(); i++) {
            count++;
            if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                newString.append(str.charAt(i));
                newString.append(count);
                count = 0;
            }
        }

        return newString.toString();
    }

    // 문자열 압축을 진행할 때 필요한 칸의 수를 계산하기
    private static int getTotal(String str) {
        int count = 0;
        int total = 0;
        for(int i = 0; i < str.length(); i++) {
            count++;
            if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                total += 1 + String.valueOf(count).length();
                count = 0;
            }
        }
        return total;
    }
}

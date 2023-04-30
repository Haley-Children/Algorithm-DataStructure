// 주어진 문자열을 치환해서 회문으로 만들 수 있는지 체크하는 메서드 구현
public class IsPermutationOfPalindromeImp {
    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrome("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e fff"));

        System.out.println(isPermutationOfPalindrome2("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome2("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome2("aa bb cc dd e fff"));

        System.out.println(isPermutationOfPalindrome3("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd e fff"));
    }

    // 메서드 분리하여 구현
    private static boolean isPermutationOfPalindrome(String s) {
        // 각 문자의 빈도수 체크
        int[] table = buildCharFrequencyTable(s);
        // 문자의 개수가 홀수인 문자가 0개 또는 1개이면 true
        return checkMaxOneOdd(table);
    }

    // 코드 양 줄이기
    private static boolean isPermutationOfPalindrome2(String s) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
            if(x != -1) {
                table[x]++;
                if(table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    // 비트 연산으로 구현하기
    // 문자열의 모든 문자마다 xor연산을 한 뒤
    // 1인 비트가 2개 이상이면 false
    private static boolean isPermutationOfPalindrome3(String s) {
        int bitVector = createBitVector(s);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    ////////////////////////////////////////////////////////////
    // isPermutationOfPalindrome 에 필요한 메서드
    private static int[] buildCharFrequencyTable(String s) {
//        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a')];
        int[] table = new int['z' - 'a' + 1];

        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
//            int x = c - 'a';
            if(x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    // 문자가 알파벳 소문자이면 몇 번째 알파벳인지 반환, 아니면 -1
    private static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);

        if(a <= val && val <= z) return val - a;
        return -1;
    }

    // 문자열에서 홀수 개 존재하는 문자가 2개 이상이면 false
    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if(count % 2 == 1) {
                if (!foundOdd) {
                    foundOdd = true;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /////////////////////////////////////////////////////////////
    // method 3에 필요한 private 메서드
    private static int createBitVector(String s) {
        int bitVector = 0;
        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    private static int toggle(int bitVector, int index) {
        if(index < 0) return bitVector;
        int mask = 1 << index;
        // XOR 연산과 같음
        // bitVector ^= mask 과 같다.
        if(((bitVector & mask)) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

}

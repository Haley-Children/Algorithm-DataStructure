package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9012_괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // 괄호가 한 종류라 스택 자료구조 이용 없이 포인터로 스택 개념 활용 가능
            int pos = 0;
            boolean flag = true;
            for(char c : br.readLine().toCharArray()) {
                if (c == '(') {
                    pos++;
                } else {
                    if (pos <= 0) {
                        flag = false;
                        break;
                    }
                    pos--;
                }
            }
            if (!flag) {
                ans.append("NO\n");
            } else {
                if (pos != 0) {
                    ans.append("NO\n");
                } else {
                    ans.append("YES\n");
                }
            }
        }
        System.out.println(ans);
    }

}

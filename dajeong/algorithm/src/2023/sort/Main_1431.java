package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_1431 {
    // 1. 길이가 다르면 짧은 것
    // 2. 길이가 같다면 숫자끼리 더해서 비교
    // 3. 1,2번으로 비교 불가능할 경우 사전순 비교 (숫자 < 알파벳)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(br.readLine());
        }
        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) { // 길이가 같을 경우
                    int sum1 = 0;
                    int sum2 = 0;
                    for (int i = 0; i < o1.length(); i++) {
                        if (Character.isDigit(o1.charAt(i))) {
                            sum1 += o1.charAt(i) - '0';
                        }
                    }
                    for (int i = 0; i < o2.length(); i++) {
                        if (Character.isDigit(o2.charAt(i))) {
                            sum2 += o2.charAt(i) - '0';
                        }
                    }

                    if (sum1 != sum2) return sum1 - sum2;
                    return o1.compareTo(o2);
                } else { // 길이가 다를 경우
                    return o1.length() - o2.length();
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            sb.append(arr.get(i)).append("\n");
        }
        System.out.println(sb);
    }

}

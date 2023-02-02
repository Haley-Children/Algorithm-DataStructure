package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        List<String> arr = new ArrayList<>(n+1);
        while (n-- > 0) {
            arr.add(br.readLine());
        }
        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

        // 중복처리 + 출력
        System.out.println(arr.get(0)); // **************
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).equals(arr.get(i-1))) continue;
            sb.append(arr.get(i)).append("\n");
        }
        System.out.println(sb);
    }

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2751 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> arr = new ArrayList<>(n);
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        //최악의 경우 O(N^2)을 피하기 위해
        Collections.sort(arr);
        for (int i: arr) {
            sb.append(i).append('\n');
        }
        System.out.println(sb);
    }
}


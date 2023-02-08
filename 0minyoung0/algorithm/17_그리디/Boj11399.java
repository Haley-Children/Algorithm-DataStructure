// 그리디.boj11399;

import java.io.*;
import java.util.*;

public class Boj11399 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        int ans = 0;
        int sum = 0;
        for (int i=0; i<n; i++) {
        	sum += list.get(i);
        	ans += sum;
        }
        System.out.println(ans);
    }
}
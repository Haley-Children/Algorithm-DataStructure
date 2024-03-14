package hash.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_7785_회사에있는사람 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String note = st.nextToken();
            if (note.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }

        ArrayList<String> enterList = new ArrayList<>(set);
        Collections.sort(enterList, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < enterList.size(); i++) {
            sb.append((enterList.get(i))).append("\n");
        }
        System.out.println(sb);
    }
}

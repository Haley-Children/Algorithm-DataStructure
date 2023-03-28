package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7785 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Set<String> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String status = st.nextToken();

            if(status.equals("enter")) {
                set.add(name);
            } else {
                if(set.contains(name)) {
                    set.remove(name);
                }
            }
        }

        List<String> names = new ArrayList<>(set);
        Collections.sort(names, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name).append("\n");
        }
        System.out.println(sb);
    }

}

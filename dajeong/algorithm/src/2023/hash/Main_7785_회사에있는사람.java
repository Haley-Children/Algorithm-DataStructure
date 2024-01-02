package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_7785_회사에있는사람 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();
            if (status.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }
        List<String> list= new ArrayList<>();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            list.add(it.next());
        }
        Collections.sort(list, Collections.reverseOrder());
        for (String name : list) {
            System.out.println(name);
        }
    }

}

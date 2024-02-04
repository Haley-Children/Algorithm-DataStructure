package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_13414_수강신청 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 수강 가능 인원
        int L = Integer.parseInt(st.nextToken()); // 버튼 클릭 순서
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < L; i++) {
            String student = br.readLine();
            if (!set.contains(student)) {
                set.add(student);
            } else {
                set.remove(student);
                set.add(student);
            }
        }
        Iterator<String> it = set.iterator();
        while(K-->0 && it.hasNext()) {
            System.out.println(it.next());
        }

    }

}

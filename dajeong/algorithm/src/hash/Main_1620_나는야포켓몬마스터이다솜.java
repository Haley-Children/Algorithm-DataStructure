package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1620_나는야포켓몬마스터이다솜 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken());
        int find = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> s2i = new HashMap<>(); // unorderedMap
        String[] i2s = new String[total+1];
        for (int i = 1; i <= total; i++) {
            String poketmon = br.readLine();
            s2i.put(poketmon, i);
            i2s[i] = poketmon;
        }
        for (int i = 0; i < find; i++) {
            String str = br.readLine();
            if (IsInteger(str)) {
                System.out.println(i2s[Integer.parseInt(str)]);
            } else {
                System.out.println(s2i.get(str));
            }
        }
    }

    private static boolean IsInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

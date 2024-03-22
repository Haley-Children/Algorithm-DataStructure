package hash.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_1620_나는야포켓몬마스터이다솜 {
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> nameMap = new HashMap<>();
        HashMap<Integer, String> numMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameMap.put(name, i);
            numMap.put(i, name);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String comm = br.readLine();
            if (isText(comm)) {
                sb.append(nameMap.get(comm));
            } else {
                sb.append(numMap.get(Integer.parseInt(comm)));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    private static boolean isText(String input) {
        if (input.matches("[A-Za-z]+")) {
            return true;
        } else {
            return false;
        }
    }
}

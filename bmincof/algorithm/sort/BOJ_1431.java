import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1431 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    int s1 = 0;
                    int s2 = 0;
                    for(int i = 0; i < o1.length(); i++) {
                        if(o1.charAt(i) < 'A') s1 += o1.charAt(i) - '0';
                        if(o2.charAt(i) < 'A') s2 += o2.charAt(i) - '0';
                    }
                    if(s1 == s2) {
                        for (int i = 0; i < o1.length(); i++) {
                            if (o1.charAt(i) != o2.charAt(i)) {
                                return o1.charAt(i) < o2.charAt(i) ? -1 : 1;
                            }
                        }
                    } else {
                        return s1 < s2 ? -1 : 1;
                    }
                } else {
                    return o1.length() < o2.length() ? -1 : 1;
                }

                return 0;
            }
        });
        
	StringBuilder sb = new StringBuilder();
        for (String s : arr) sb.append(s).append('\n');
        System.out.println(sb);
    }

}


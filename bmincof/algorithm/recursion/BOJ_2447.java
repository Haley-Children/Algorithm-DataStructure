import java.util.Scanner;

public class BOJ_2447 {
    static StringBuilder sb = new StringBuilder();
    static char[][] stars;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        stars = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stars[i][j] = '*';
            }
        }

        func(0, 0, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(stars[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    static void func(int r, int c, int size) {
        if(size == 0) {
            return;
        }

        int newSize = size / 3;
        for (int i = r + newSize; i < r + 2 * newSize; i++) {
            for (int j = c + newSize; j < c + 2 * newSize; j++) {
                stars[i][j] = ' ';
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) continue;
                func(r + i * newSize, c + j * newSize, newSize);
            }
        }
    }
}


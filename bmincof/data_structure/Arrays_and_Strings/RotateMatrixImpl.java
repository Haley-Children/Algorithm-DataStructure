public class RotateMatrixImpl {
    public static void main(String[] args) {
        // 360도 회전시키면서 확인하기
        int[][] image = {
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        printImage(image);

        rotateImage(image);
        printImage(image);
        rotateImage(image);
        printImage(image);
        rotateImage(image);
        printImage(image);
    }

    // 가장 바깥쪽 테두리 요소부터 반시계 방향으로 회전시키기
    private static void rotateImage(int[][] image) {
        int tmp;
        for(int s = 0, e = image.length - 1; s < e; s++, e--) {
            for(int i = s, j = e; i < e; i++, j--) {
                tmp = image[s][i];
                image[s][i] = image[i][e];
                image[i][e] = image[e][j];
                image[e][j] = image[j][s];
                image[j][s] = tmp;
            }
        }
    }

    private static void printImage(int[][] image) {
        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

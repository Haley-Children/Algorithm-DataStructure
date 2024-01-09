package array;

/**
 * 배열 삽입, 삭제 기능 구현해보기
 */
public class Array {

    public static int size; // 배열의 크기 (메모리 할당된 크기)
    public static int len; // 마지막 값이 있는 idx == 배열의 길이

    public static void main(String[] args) {
        insertTest();
        eraseTest();
    }

    private static void eraseTest() {
        System.out.println("Impl.eraseTest");
        int[] arr = {10, 50, 40, 30, 70, 20};
        size = 6;
        len = 5;
        erase(4, arr); // 10 50 40 30 20
        printArr(arr);
        erase(1, arr); // 10 40 30 20
        printArr(arr);
        erase(3, arr); // 10 40 30
        printArr(arr);
    }


    private static void insertTest() {
        System.out.println("Impl.insertTest");
        int[] arr = {10, 20, 30};
        size = 3;
        len = 2;
        arr = insert(3, 40, arr); // 10 20 30 40
        printArr(arr);
        arr = insert(1, 50, arr); // 10 50 20 30 40
        printArr(arr);
        arr = insert(0, 15, arr); // 15 10 50 20 30 40
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i <= len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void erase(int idx, int[] arr) {
        for (int i = idx; i < len; i++) {
            arr[i] = arr[i + 1];
        }
        len--;
    }

    private static int[] insert(int idx, int num, int[] arr) {
        // 배열의 크기가 정해진 크기보다 커졌을 경우 일정한 배수(2배)로 재설정, 복사
        if (len + 1 >= size) {
            int[] newArr = new int[2 * size];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            size *= 2;
            arr = newArr;
        }

        // 맨 오른쪽부터 역순으로 값을 shift
        for (int i = len + 1; i > idx; i--) {
            arr[i] = arr[i - 1];
        }
        arr[idx] = num;
        len++;

        return arr;
    }

}

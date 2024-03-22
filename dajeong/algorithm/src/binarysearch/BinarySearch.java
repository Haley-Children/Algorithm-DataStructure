package binarysearch;

public class BinarySearch {
    static int[] a;
    public static void main(String[] args) {
        a = new int[]{2,4,6,10,10,16,16,16,30,32};
        int targetIdx = binarySearch(a, 30);
        int targetIdx2 = binarySearch(a, 3);
        System.out.println("target idx: "+ targetIdx);
        System.out.println("target idx2: "+ targetIdx2);
    }

    private static int binarySearch(int[] a, int target) {
        int st = 0;
        int en = a.length -1;
        
        while (st <= en) { // ** 등호 주의
            int mid = (st + en) >>> 1;
            if (a[mid] < target) {
                st = mid + 1;
            } else if (a[mid] > target) {
                en = mid - 1;
            } else { // 타겟을 찾았을 때
                return mid;
            }
        }
        return -(st+1); // 값이 존재하지 않으면 들어갈 수 있는 음수 인덱스 반환
    }
}

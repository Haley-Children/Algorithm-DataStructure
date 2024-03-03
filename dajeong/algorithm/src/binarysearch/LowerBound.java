package binarysearch;

public class LowerBound {
    static int[] a;
    public static void main(String[] args) {
        a = new int[]{2,4,6,10,10,16,16,16,30,32};
        int len = a.length;
        int lowerIdx = lowerBound(16, len);
        System.out.println("lower idx: "+ lowerIdx);
    }

    private static int lowerBound(int target, int len) {
        int st = 0;
        int en = len; // ** len-1이 아님을 주의 

        while (st < en) { // ** 등호 없음 주의
            int mid = (st+en) >>> 1;
            if (a[mid] >= target) { // **
                en = mid;
            } else {
                st = mid+1;
            }
        }
        return st;
    }
}

package arrays_strings;

public class StringBuilder {
    private char[] value;
    private int size; // 배열 방 크기
    private int index; // 새로 추가할 value의 방 번호를 저장할 index

    public StringBuilder() {
        size = 1;
        value = new char[size];
        index = 0;
    }

    /**
     * string 추가
     * @param str : 추가할 string
     */
    public void append(String str) {
        if (str == null) str = "null";
        int len = str.length();
        ensureCapacity(len);
        for (int i = 0; i < str.length(); i++) {
            value[index] = str.charAt(i);
            index++;
        }
        System.out.println(size + ", " + index);
    }

    /**
     * 배열 방이 충분하지 않으면 배열 방을 늘려주는 메서드
     *
     * @param len : 추가할 str 길이
     */
    private void ensureCapacity(int len) {
        if (index + len > size) {
            size = (size+len)*2;
            char[] newValue = new char[size];
            for (int i = 0; i < value.length; i++) {
                newValue[i] = value[i];
            }
            value = newValue;
        }
        System.out.println("*** "+size+", "+index);
    }

    /**
     * @return : append한 string 붙여서 출력
     */
    public String toString() {
        return new String(value, 0, index);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("sung");
        sb.append(" is");
        sb.append(" pretty");
        System.out.println(sb.toString());
    }
}

package arrays_strings;

public class ArrayList {
    private Object[] data;
    private int size;
    private int index;

    public ArrayList() {
        this.size = 1;
        this.data = new Object[this.size];
        this.index = 0;
    }

    /**
     * 데이터 (Object) 추가하는 메서드
     * @param obj
     */
    public void add(Object obj) {
        System.out.println("index: "+this.index + ", size: "+this.size + ", data size: "+this.data.length);
        if (this.index == this.size - 1) {
            doubling();
        }
        data[this.index] = obj;
        this.index++;
    }

    /**
     * 배열이 다 차면 현재 사이즈의 2배만큼 늘리는 메서드
     * 기존 사이즈 * 2 만큼의 사이즈의 배열을 새로 생성 후 기존 데이터 복사
     */
    private void doubling() {
        this.size = this.size * 2;
        Object[] newData = new Object[this.size];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
        System.out.println("*** index: "+this.index+", size: "+this.size+", data size: "+this.data.length);
    }

    /**
     * 인덱스로 value 반환하는 메서드
     * 인덱스가 데이터 범위 내에 있는지 확인
     * @param i
     * @return
     * @throws Exception
     */
    public Object get(int i) throws Exception {
        if (i > this.index - 1) {
            throw new Exception("ArrayIndexOutOfBound");
        } else if (i < 0) {
            throw new Exception("Negative Value");
        }
        return this.data[i];
    }

    /**
     * 해당 인덱스의 값을 삭제하는 메서드
     * 삭제한 인덱스를 기준으로 한칸씩 shift해서 빈 공간을 없애준다
     * @param i
     * @throws Exception
     */
    public void remove(int i) throws Exception {

        if (i > this.index - 1) {
            throw new Exception("ArrayIndexOutOfBound");
        } else if (i < 0) {
            throw new Exception("Negative Value");
        }
        System.out.println("data removed: "+this.data[i]);
        for (int x = 0; x < this.data.length - 1; x++) {
            data[x] = data[x+1];
        }
        this.index--;
    }

    public static void main(String[] args) throws Exception {
        ArrayList al = new ArrayList();
        al.add("0");
        al.add("1");
        al.add("2");
        al.add("3");
        al.add("4");
        al.add("5");
        al.add("6");
        al.add("7");
        al.add("8");
        al.add("9");
        System.out.println(al.get(5));
        al.remove(5);
        System.out.println(al.get(5));
    }


}

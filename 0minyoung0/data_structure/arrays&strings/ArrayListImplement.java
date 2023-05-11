// 자바의 ArrayList에 대해 알아보고 구현하기
// https://youtu.be/I4_uFyjWZn4

package arraysAndStrings.arrayListImplement;

class ArrayList {
	private Object[] data;
	private int size;
	private int index;
	
	public ArrayList() {
		this.size = 1;
		this.data = new Object[this.size];
		this.index = 0;
	}
	// add : O(1)
	// 단, doubling시 추가적인 시간 소요
	public void add(Object obj) {
		System.out.println("index: " + this.index + ", size: " + this.size +
							", data size: " + this.data.length);
		
		if(this.index == this.size - 1) {
			doubling();
		}
		data[this.index] = obj;
		this.index++;
	}
	// doubling : O(n)
	private void doubling() {
		this.size = this.size * 2;
		Object[] newData = new Object[this.size];
		for(int i=0; i<data.length; i++) {
			newData[i] = data[i];
		}
		this.data = newData;
		System.out.println("*** index: " + this.index + ", size: " + this.size +
							", data size: " + this.data.length);
	}
	// get : O(1)
	public Object get(int i) throws Exception{
		if(i > this.index-1) {
			throw new Exception("ArrayIndexOutOfBound");
		}else if (i < 0) {
			throw new Exception("Negative Value");
		}
		return this.data[i];
	}
	public void remove(int i) throws Exception{
		if(i > this.index-1) {
			throw new Exception("ArrayIndexOutOfBound");
		}else if (i < 0) {
			throw new Exception("Negative Value");
		}
		System.out.println("data removed: " + this.data[i]);
		
		for (int x=i; x<this.data.length - 1; x++) {
			data[x] = data[x + 1];
		}
		this.index--;
	}
}

public class ArrayListImplement {
	public static void main(String[] args) throws Exception {
		ArrayList al = new ArrayList();
		al.add("0");	// index: 0, size: 1, data size: 1
						// *** index: 0, size: 2, data size: 2
		al.add("1");	// index: 1, size: 2, data size: 2
						// *** index: 1, size: 4, data size: 4
		al.add("2");	// index: 2, size: 4, data size: 4
		al.add("3");	// index: 3, size: 4, data size: 4
						// *** index: 3, size: 8, data size: 8
		al.add("4");	// index: 4, size: 8, data size: 8
		al.add("5");	// index: 5, size: 8, data size: 8
		al.add("6");	// index: 6, size: 8, data size: 8
		al.add("7");	// index: 7, size: 8, data size: 8
						// *** index: 7, size: 16, data size: 16
		al.add("8");	// index: 8, size: 16, data size: 16
		al.add("9");	// index: 9, size: 16, data size: 16
		System.out.println(al.get(5));	// 5
		al.remove(5);	// data removed: 5
		System.out.println(al.get(5));	// 6
	}
}

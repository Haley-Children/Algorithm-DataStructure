// 자바의 StringBuilder에 대해 알아보고 구현하기
// https://youtu.be/gc7bo5_bxdA
// StringBuilder : asynchronized
// StringBuffer : synchronized (thread safe)

package arraysAndStrings.stringBuilderImplement;

class StringBuilder{
	private char[] value;
	private int size;
	private int index;
	
	StringBuilder() {
		size = 1;
		value = new char[size];
		index = 0;
	}
	
	public void append(String str) {
		if (str == null) str = "null";
		int len = str.length();
		ensureCapacity(len);
		for (int i=0; i<str.length(); i++) {
			value[index] = str.charAt(i);
			index++;
		}
		System.out.println(size + ", " + index);
	}
	
	private void ensureCapacity(int len) {
		if (index + len > size) {
			size = (size + len) * 2;
			char[] newValue = new char[size];
			for (int i=0; i<value.length; i++) {
				newValue[i] = value[i];
			}
			value = newValue;
			System.out.println("*** " + size + ", " + index);
		}
	}
	
	public String toString() {
		return new String(value, 0, index);
	}
}

public class StringBuilderImplement {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Jeong");		// *** 12, 0
								// 12, 5
		sb.append(" Min");		// 12, 9
		sb.append(" Young");	// *** 36, 9
								// 36, 15
		System.out.println(sb.toString());	// Jeong Min Young
	}
}

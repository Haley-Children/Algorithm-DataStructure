package arrays_strings;

import java.util.LinkedList;

public class HashTable {
    class Node {
        String key;
        String value;
        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
        String value() {
            return value;
        }

        void value(String value) {
            this.value = value;
        }
    }
    LinkedList<Node>[] data;

    public HashTable(int size) {
        this.data = new LinkedList[size];
    }

    /**
     * 각 자리 문자의 아스키코드 값을 더해서 hashcode를 만드는 hash 함수
     * @param key
     * @return
     */
    int getHashCode(String key) {
        int hashcode = 0;
        for (char c : key.toCharArray()) {
            hashcode += c;
        }
        return hashcode;
    }

    /**
     * index: hashcode를 배열의 크기로 나눈 나머지
     * @param hashcode
     * @return
     */
    int convertToIndex(int hashcode) {
        return hashcode % data.length;
    }

    /**
     * Hash Collision -> 해당 인덱스에 저장된 Node가 여러개일 때, key로 찾고자하는 Node 찾는 메서드
     * @param list
     * @param key
     * @return
     */
    Node searchKey(LinkedList<Node> list, String key) {
        if (list == null) return null;
        for (Node node : list) {
            if(node.key.equals(key)) return node;
        }
        return null;
    }

    /**
     * 해시테이블에 값을 저장하는 메서드
     * 기존에 해당 키로 저장한 Node가 있는지 확인 후, 있으면 대체(중복값 처리) 없으면 저장
     * @param key
     * @param value
     */
    void put(String key, String value) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        System.out.println(key + ", hashcode(" + hashcode + "), index(" + index + ")");
        LinkedList<Node> list = data[index];
        if (list == null) {
            list = new LinkedList<>();
            data[index] = list;
        }
        Node node = searchKey(list, key);
        if (node == null) {
            list.addLast(new Node(key, value));
        } else {
            node.value(value);
        }
    }

    /**
     * key값으로 hashtable에 저장된 value값 찾는 메서드
     * @param key
     * @return
     */
    String get(String key) {
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null ? "Not found" : node.value();
    }

    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("sung", "She is pretty");
        h.put("jin", "She is a model");
        h.put("hee", "She is an angel");
        h.put("min", "She is cute");
        h.put("sung", "She is beautiful");
        System.out.println(h.get("sung")); // 중복 값 대체
        System.out.println(h.get("jin"));
        System.out.println(h.get("hee"));
        System.out.println(h.get("min"));
        System.out.println(h.get("jae")); // 없는 key Not found 반환
    }

}

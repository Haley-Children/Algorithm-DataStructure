import java.util.LinkedList;

// 간단한 해시 함수를 통해 해시테이블 구현해보기
public class Hashtable {
    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("sung", "She is pretty");
        h.put("jin", "She is a model");
        h.put("hee", "She is an angel");
        h.put("min", "She is cute");

        System.out.println(h.get("sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("hee"));
        System.out.println(h.get("min"));
    }

    static class HashTable {
        class Node {
            String key;
            String value;

            Node(String key, String value) {
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
        HashTable(int size) {
            this.data = new LinkedList[size];
        }

        // 각 문자를 모두 더한 값을 해시코드로 이용
        // 간단한 형태의 해시함수
        int getHashCode(String key) {
            int hashcode = 0;
            for(char c : key.toCharArray()) {
                hashcode += c;
            }

            return hashcode;
        }

        int convertToIndex(int hashcode) {
            return hashcode % data.length;
        }

        Node searchKey(LinkedList<Node> list, String key) {
            if(list == null) return null;
            for(Node node : list) {
                if(node.key.equals(key)) {
                    return node;
                }
            }

            return null;
        }

        void put(String key, String value) {
            int hashcode = getHashCode(key);
            int index = convertToIndex(hashcode);

            LinkedList<Node> list = data[index];
            if(list == null) {
                list = new LinkedList<>();
                data[index] = list;
            }
            Node node = searchKey(list, key);
            if(node == null) {
                list.addLast(new Node(key, value));
            } else {
                node.value(value);
            }
        }

        String get(String key) {
            int hashcode = getHashCode(key);
            int index = convertToIndex(hashcode);
            LinkedList<Node> list = data[index];
            Node node = searchKey(list, key);

            return node == null ? "Not Found" : node.value();
        }
    }
}

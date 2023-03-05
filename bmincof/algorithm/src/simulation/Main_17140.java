package boj;

import java.util.*;

public class Main_17140 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int k = sc.nextInt();

        int n = 100;
        int[][] arr = new int[n][n];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int times = 0;
        
        int maxRow = 3;
        int maxCol = 3;
        
        while(times <= n) {
            if(arr[r][c] == k) {
                break;
            }

            // R연산
            if(maxRow >= maxCol) {
                for (int i = 0; i < maxRow; i++) {
                    // 카운트하기
                    Map<Integer, Node> hMap = new HashMap<>();

                    for (int j = 0; j < maxCol; j++) {
                        if(arr[i][j] == 0) continue;

                        if (hMap.containsKey(arr[i][j])) {
                            hMap.get(arr[i][j]).count++;
                        } else {
                            hMap.put(arr[i][j], new Node(arr[i][j]));
                        }
                    }

                    List<Node> nodes = new ArrayList<>(hMap.values());
                    Collections.sort(nodes);

                    int colIdx = 0;
                    for (int j = 0; j < nodes.size(); j++) {
                        Node node = nodes.get(j);
                        arr[i][colIdx++] = node.value;
                        arr[i][colIdx++] = node.count;

                        if(colIdx == n) break;
                    }

                    for (int j = colIdx; j < maxCol; j++) {
                        arr[i][j] = 0;
                    }

                    maxCol = Math.max(maxCol, colIdx);
                }
            // C연산
            } else {
                for (int j = 0; j < maxCol; j++) {
                    // 카운트하기
                    Map<Integer, Node> hMap = new HashMap<>();

                    for (int i = 0; i < maxRow; i++) {
                        if (arr[i][j] == 0) continue;

                        if (hMap.containsKey(arr[i][j])) {
                            hMap.get(arr[i][j]).count++;
                        } else {
                            hMap.put(arr[i][j], new Node(arr[i][j]));
                        }
                    }

                    List<Node> nodes = new ArrayList<>(hMap.values());
                    Collections.sort(nodes);

                    int rowIdx = 0;
                    for (int i = 0; i < nodes.size(); i++) {
                        Node node = nodes.get(i);
                        arr[rowIdx++][j] = node.value;
                        arr[rowIdx++][j] = node.count;

                        if (rowIdx == n) break;
                    }

                    for (int i = rowIdx; i < maxRow; i++) {
                        arr[i][j] = 0;
                    }

                    maxRow = Math.max(maxRow, rowIdx);
                }
            }

            times++;
        }

        System.out.println(times == n + 1 ? -1 : times);
    }
    
    static class Node implements Comparable<Node> {
        int value;
        int count;

        public Node(int value) {
            this.value = value;
            this.count = 1;
        }

        @Override
        public int compareTo(Node o) {
            if(this.count == o.count) {
                return this.value - o.value;
            } else {
                return this.count - o.count;
            }
        }
    }
}


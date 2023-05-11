package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1202 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 보석의 개수
        int n = Integer.parseInt(st.nextToken());
        // 가방의 개수
        int k = Integer.parseInt(st.nextToken());

        // 보석 정보들을 저장할 배열
        List<Jewel> jewels = new ArrayList<>();

        for(int i = 0; i < n; i++) {
           st = new StringTokenizer(br.readLine());
           int weight = Integer.parseInt(st.nextToken());
           int cost = Integer.parseInt(st.nextToken());

           jewels.add(new Jewel(cost, weight));
        }

        // 보석을 비용이 크고, 무게가 작은 순으로 정렬
        Collections.sort(jewels);

        // 가방을 크기 순으로 저장
        TreeMap<Integer, Integer> bags = new TreeMap<>();

        for(int i = 0; i < k; i++) {
            int canStore = Integer.parseInt(br.readLine());

            if(bags.containsKey(canStore)) {
                bags.put(canStore, bags.get(canStore) + 1);
            } else {
                bags.put(canStore, 1);
            }
        }

        // 훔친 보석의 가격 합을 저장할 변수
        long total = 0;
        for(int i = 0; i < n; i++) {
            Jewel curJewel = jewels.get(i);

            Integer bag = bags.ceilingKey(curJewel.weight);
            if(bag == null) continue;

            total += curJewel.cost;
            if(bags.get(bag) == 1) {
                bags.remove(bag);
            } else {
                bags.put(bag, bags.get(bag) - 1);
            }

            if(bags.isEmpty()) break;
        }

        System.out.println(total);
    }

    static class Jewel implements Comparable<Jewel> {
        int cost, weight;

        public Jewel(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public int compareTo(Jewel o) {
            if(this.cost == o.cost) {
                return this.weight - o.weight;
            }
            return o.cost - this.cost;
        }
    }
}

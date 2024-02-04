package bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_19700_수업 {

    private static class Group {

        int pk;
        int size;

        public Group(int pk, int size) {
            this.pk = pk;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<int[]> personList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            personList.add(new int[]{h, k});
        }
        // 그리디) 키가 큰 순서대로 그룹 짓기 위한 정렬
        Collections.sort(personList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        // 그룹별 사람 수를 저장할 트리셋 (그룹 간 사람 수가 같을 경우 구분을 위해 pk 식별자를 둔 Group 객체 사용)
        // 그룹 사이즈를 오름차순으로 저장
        TreeSet<Group> groupTreeSet = new TreeSet<>(new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                if (o1.size == o2.size) return o1.pk-o2.pk;
                return o1.size-o2.size;
            }
        });

        int pkidx = 1; // 식별자
        for (int i = 0; i < personList.size(); i++) {
            int[] person = personList.get(i);
            int K = person[1];
            if (groupTreeSet.isEmpty()) {
                groupTreeSet.add(new Group(pkidx++, 1));
            } else {
                Group lower = groupTreeSet.lower(new Group(0, K));
                if (lower == null) {
                    groupTreeSet.add(new Group(pkidx++, 1));
                } else {
                    lower.size++; // set에서 빼고, 다시 그룹 넣지 않고 객체의 필드값만 더해주면 된다! (시간상 더 유리함)
                }
            }
        }

        System.out.println(groupTreeSet.size());
    }

}

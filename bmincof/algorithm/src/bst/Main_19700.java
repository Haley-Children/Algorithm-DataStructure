package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_19700 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 학생의 수
        int n = Integer.parseInt(br.readLine());

        // 그룹들을 정렬하기 위한 bst
        TreeSet<Group> groups = new TreeSet<>();
        // 학생들을 저장하기 위한 리스트
        List<int[]> students = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int height = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());

            students.add(new int[] {height, limit});
        }

        // 학생들을 키 내림차순으로 정렬하기
        Collections.sort(students, (s1, s2) -> s2[0] - s1[0]);

        for(int i = 0; i < n; i++) {
            Group curr = new Group(students.get(i)[1], students.get(i)[0]);
            // 학생이 들어갈 수 있는 팀을 찾아보기
            Group toEnter = groups.lower(curr);

            // 들어갈 수 있는 그룹이 없다면 (= 현재 학생보다 키가 큰 학생이 limit보다 많다면)
            if(toEnter == null) {
            	// 새로운 그룹 만들기
                curr.size = 1;
                groups.add(curr);
            // 들어갈 수 있는 그룹이 있으면 해당 그룹에 들어가기
            } else {
                toEnter.size++;
            }
        }

        System.out.println(groups.size());
    }

    static class Group implements Comparable<Group> {
        int size, rep;

        Group(int size, int rep) {
            this.size = size;
            this.rep = rep;
        }

        // 그룹 크기 오름차순, 대표자 키 오름차순(대표자는 사실상 상관없음)
        @Override
        public int compareTo(Group o) {
            if(this.size == o.size) return this.rep - o.rep;
            return this.size - o.size;
        }

    }
}

package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Egg implements Cloneable{

    int s; // 내구도
    int w; // 무게

    public Egg(int s, int w) {
        this.s = s;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Egg{" +
            "s=" + s +
            ", w=" + w +
            '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Main_16987_계란으로계란치기 {

    // 중복 순열 문제
    // 1. 가능한 깨뜨릴 계란을 선택하는 중복순열의 경우의 수 구하기
    // 2. 계란을 돌면서 해당 경우의 수대로 계란깨기 시행. (조건에 맞지 않을 경우 끝내거나 백트래킹)
    static int N;
    static Egg[] eggList;
    static int[] order;
    static int ans;
    static Egg[] copyEggList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 계란의 수
        eggList = new Egg[N];
        order = new int[N];
        copyEggList = new Egg[N];
        ans = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggList[i] = new Egg(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));
        }

        makeOrder(0);
        System.out.println(ans);

    }

    private static void makeOrder(int cnt) throws CloneNotSupportedException {
        if (cnt == N) {
            int sum = 0; // 각 경우의 수별로 깬 계란 수

            // ******* 참조자료형 배열을 복사할 때는 clone!! Arrays.copyOf도 안됨..
            for (int i = 0; i < N; i++) {
                copyEggList[i] = (Egg) eggList[i].clone();
            }

            for (int e = 0; e < N; e++) { // 손에 든 계란
                Egg egg = copyEggList[e]; // **** 배열 안에 있는 값이 객체!! 주소값 참조해서 객체가 변한다.

                Egg toCrushEgg = copyEggList[order[e]]; // 깰 대상인 계란
                if (e == order[e]) {
                    return; // 자기 자신을 깨야하는 경우엔 종료 후 다른 경우의 수 탐색
                }
                if (toCrushEgg.s <= 0) {
                    continue;
                    // **** 깨뜨릴 계란이 이미 깨졌을 경우 continue!!
                    // (원래는 종료 후 다른 경우의 수로 넘어갔으나, 이럴 경우 특정 시점부터 남아있는 모든 계란이 다 깨져있을 경우 처리가 힘들었음.. 그래서 그냥 continue로 함)
                }
                if (egg.s <= 0) {
                    continue; // 손에 든 계란이 깨졌을 경우 손에 든 계란을 다음 순서로 이동
                }
                sum += crush(egg, toCrushEgg);
            }
            ans = Math.max(ans, sum); // 계란을 끝까지 쳤을 때
            return;
        }
        for (int i = 0; i < N; i++) {
            if (cnt == i) continue; // *** 자기자신일 경우 continue 조건 추가 (시간 줄이려고..)
            order[cnt] = i;
            makeOrder(cnt + 1);
        }
    }

    private static int crush(Egg egg, Egg toCrushEgg) {
        int cnt = 0;
        egg.s -= toCrushEgg.w;
        toCrushEgg.s -= egg.w;
        if (egg.s <= 0) {
            cnt++;
        }
        if (toCrushEgg.s <= 0) {
            cnt++;
        }
        return cnt;
    }
}

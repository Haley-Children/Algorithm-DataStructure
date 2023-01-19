import java.util.Scanner;

public class BOJ_17478 {

    // 1. void func(int n)
    // 2. base condition
    // if n == 0 , out >> 재귀함수는 자기 자신 ...
    // 3. 재귀식 : f(n) = f(n-1)
    static final String q = "\"재귀함수가 뭔가요?\"";
    static final String a01 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static final String a02 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static final String a03 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
    static final String a = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static final String end = "라고 답변하였지.";
    static final String pre = "____";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        question(n, 0);
        sc.close();
    }

    static void question(int n, int count) {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for(int i = 0; i < count; i++) {
            prefix += pre;
        }
        System.out.println(prefix + q);

        if(n == 0) {
            sb.append(prefix).append(a);
            sb.append(prefix).append(end);
            System.out.println(sb);
            return;
        }
        sb.append(prefix).append(a01);
        sb.append(prefix).append(a02);
        sb.append(prefix).append(a03);
        System.out.println(sb);
        question(n-1, count+1);
        System.out.println(prefix + end);
    }
}


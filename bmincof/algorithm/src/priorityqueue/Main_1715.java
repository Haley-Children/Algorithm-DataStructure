package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1715 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		long totalComp = 0;

		PriorityQueue<Long> deckSize = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			deckSize.offer(Long.parseLong(br.readLine()));
		}

		while(deckSize.size() > 1) {
			long newDeck = deckSize.poll() + deckSize.poll();
			totalComp += newDeck;
			deckSize.offer(newDeck);
		}

		System.out.println(totalComp);
	}
}

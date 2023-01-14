package linkedlist;

/*
 * LinkedList Algorithm 1
 * : 정렬되어 있지 않은 LinkdedList의 중복된 값을 삭제하시오. (단, 별도의 버퍼 사용 금지)

 * 버퍼 사용하여 구현할 시)
 * -> 버퍼를 hashset으로 선언. key값으로 값을 찾는 시간이 O(1)이기 때문.
 * -> LinkedList를 돌면서 hashset에 없으면 hashset에 값을 추가하고 next data 조회. hashset에 있으면 data 삭제
 * 공간복잡도: O(N) - 버퍼 공간
 * 시간복잡도: O(N)

 * 버퍼 없이 구현할 시) LinkedList 내 removeDups()로 구현함
 * -> 포인터를 두어서 확인 가능.
 * -> 기준점이 되는 포인터(n)와 기준점부터 마지막 data까지 중복된 값을 찾는 포인터(r) 이용
 * 공간복잡도: O(1)
 * 시간복잡도: O(N^2)
 */

public class RemoveDuplicatedValue {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(2);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(3);
		ll.append(2);
		ll.append(1);
		ll.append(1);
		ll.retrieve();
		ll.removeDups();
		ll.retrieve();
	}
}

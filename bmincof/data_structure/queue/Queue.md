# 큐 (Queue)

# 1. 큐 (Queue)
## 1-1. 정의
- 먼저 들어온 데이터가 먼저 나가는 형태를 표현할 수 있는 자료구조
- 선입선출 방식(FIFO)
- 크기를 동적으로 사용하기 위해 일반적으로 연결리스트를 사용
- 컴퓨터 버퍼에서 주로 사용된다.

# 2. 주요 기능 및 시간복잡도
<table style="text-align : center">
    <tr>
        <th>기능</th>
        <th>시간복잡도</th>
    </tr>
    <tr>
        <th>PUSH</th>
        <td>O(1)</td>
    </tr>
    <tr>
        <th>POP</th>
        <td>O(1)</td>
    </tr>
    <tr>
        <th>PEEK</th>
        <td>O(1)</td>
    </tr>
</table>
양방향 연결 리스트 기준

# 3. 자바에서의 구현
## 3-1. Queue 인터페이스의 대표적인 메서드
`add` `offer` : 추가(push)

`poll` `remove` `clear` : 삭제(pop)

`peek` : 출력(peek)

`contains` : 탐색

## 3-2. LinkedList 클래스로 구현한 Queue

- 추가, 삭제, 조회가 모두 O(1)으로 앞서 의논한 시간복잡도와 같다.
- Size() 연산도 O(1)에 해결이 가능하다.
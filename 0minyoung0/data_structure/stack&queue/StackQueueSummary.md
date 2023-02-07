# Stack & Queue 요약

## Restricted Structure
특정 위치에서만 자료를 넣거나 뺄 수 있는 자료구조 (스택, 큐, 덱)

## Stack이란
Stack (스택): 한 쪽 끝에서만 원소를 넣거나 뺄 수 있는 자료구조  
먼저 들어간 원소가 나중에 나오는 FILO 자료구조

## Queue란
Queue (큐): 한 쪽 끝에서 원소를 넣고 반대쪽 끝에서 원소를 뺄 수 있는 자료구조  
먼저 들어간 먼저 나오게 되는 FIFO 자료구조

## deque이란
Deque (Double Ended Queue, 덱): 양쪽 끝에서 삽입과 삭제가 가능한 자료구조

## Stack의 성질
1. 원소의 추가가 O(1)
2. 원소의 제거가 O(1)
3. 제일 상단의 원소 확인이 O(1)
4. 제일 상단이 아닌 나머지 원소들의 확인/변경이 원칙적으로 불가능

## Queue의 성질
1. 원소의 추가가 O(1)
2. 원소의 제거가 O(1)
3. 제일 앞의 원소 확인이 O(1)
4. 제일 앞이 아닌 나머지 원소들의 확인/변경이 원칙적으로 불가능

## Deque의 성질
1. 원소의 추가가 O(1)
2. 원소의 제거가 O(1)
3. 제일 앞/뒤의 원소 확인이 O(1)
4. 제일 앞/뒤가 아닌 나머지 원소들의 확인/변경이 원칙적으로 불가능

## Stack & Queue의 구현 in Java

[Stack 구현하기 in Java](https://youtu.be/whVUYv0Leg0) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/StackTest.java)

[Queue 구현하기 in Java](https://youtu.be/W3jNbNGyjMs) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/QueueTest.java)

## Stack & Queue의 구현 (응용) in Java

[배열로 Multi Stack 구현하기 in Java](https://youtu.be/lnVhHd0hheU) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/MultiStacks.java)

[Stack의 작은값 찾기](https://youtu.be/atPGriLDP9E) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/Min.java)

[Set of Stacks](https://youtu.be/P_c_W5cZWwU) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/SetOfStacksTest.java)

[두개의 Stack으로 Queue만들기](https://youtu.be/t45d7CgDaDM) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/MyQueueTest.java)

[Stack 정렬하기](https://youtu.be/6-tsS9aBfzY) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/StackSort.java)

[LinkedList로 Queue구현하기](https://youtu.be/PkTKe_wUfUI) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/stack&queue/LinkedListQueue.java)

## Deque 인터페이스의 메소드

### 데이터 삽입

#### addFirst(), push() : deque의 앞쪽에 데이터를 삽입, 용량 초과시 Exception
#### offerFirst() : deque의 앞쪽에 데이터를 삽입 후 true, 용량 초과시 false

#### addLast(), add() : deque의 뒤쪽에 데이터를 삽입, 용량 초과시 Exception
#### offerLast(), offer() : deque의 뒤쪽에 데이터를 삽입 후 true, 용량 초과시 false

### 데이터 제거

#### removeFirst(), remove(), pop() : deque의 앞에서 제거, 비어있으면 Exception
#### poll(), pollFirst() : deque의 앞에서 제거, 비어있으면 null 리턴

#### removeLast() : deque의 뒤에서 제거, 비어있으면 Exception
#### pollLast() : deque의 뒤에서 제거, 비어있으면 null 리턴

#### removeFirstOccurrence(Object o), remove(Object o) : deque의 앞에서부터 찾아서 첫 번째 데이터를 삭제
#### removeLastOccurrence(Object o) : deque의 뒤에서부터 찾아서 첫 번째 데이터를 삭제

### 데이터 확인

#### getFirst() : 첫 번째 엘리먼트를 확인, 비어있으면 Exception
#### peekFirst(), peek() : 첫 번째 엘리먼트를 확인, 비어있으면 null 리턴

#### getLast() : 마지막 엘리먼트를 확인, 비어있으면 Exception
#### peekLast() : 마지막 엘리먼트를 확인, 비어있으면 null 리턴

#### contain(Object o) : Object 인자와 동일한 엘리먼트가 포함되어 있는지 확인
#### size() : deque에 들어있는 엘리먼트 개수

### iterator

#### iterator() : 이터레이터
#### descendingIterator() : 역방향 이터레이터


## Deque의 구현체 in Java
### ArrayDeque
Array에 의해 지원되고 Array는 LinkedList보다 Cache Locality (캐시 지역성) 친화적  
다음 노드에 추가적인 reference를 유지할 필요가 없기 때문에 메모리 효율적  
일반적인 stack, queue, deque 구현시 사용
### LinkedList
리스트를 순회할 때 삽입, 삭제가 필요한 경우에 사용

## 자료 출처
[[엔지니어대한민국] Stack & Queue 재생목록](https://www.youtube.com/playlist?list=PLjSkJdbr_gFZL2BNnGLvTgMYXptKGIyum)  
# 연결리스트(Linked List) 요약

## Linked List란
Linked List(연결 리스트) :  
원소들을 저장할 때 그 다음 원소가 있는 위치를 포함시키는 방식으로 저장하는 자료구조  
## Linked List의 성질
1. k번째 원소를 확인/변경하기 위해 O(k)가 필요함
2. 임의의 위치 원소를 추가/임의 위치의 원소 제거는 O(1)
3. 원소들이 메모리 상에 연속해있지 않아 Cache hit rate가 낮지만 할당이 다소 쉬움
   
## Linked List의 종류
### 단일 연결 리스트(Singly Linked List)
각 원소가 자신의 다음 원소의 주소를 가지고 있음
### 이중 연결 리스트(Doubly Linked List)
각 원소가 자신의 이전 원소와 다음 원소의 주소를 둘 다 가지고 있음
### 원형 연결 리스트(Circular Linked List)
마지막 원소가 처음 원소와 연결되어 있음 (단일 연결 리스트, 이중 연결 리스트 모두 가능)

## 배열과 연결 리스트의 차이
||배열|연결리스트|
|:---:|:---:|:---:|
|k번째 원소의 접근|O(1)|O(k)|
|임의 위치에 원소 추가/제거|O(N)|O(1)*|
|메모리 상의 배치|연속|불연속|
|추가적으로 필요한 공간(Overhead)|-|O(N)**|

\* 원소 추가/제거 자체는 O(1)이지만 해당 위치를 찾아가는 시간이 추가적으로 필요  
** 각 원소가 다음 원소, 혹은 이전과 다음 원소의 주소값을 가져야하므로  
32비트 컴퓨터의 경우 주소값이 32비트(=4바이트)이므로 4N 바이트가 추가로 필요하고  
64비트 컴퓨터의 경우 주소값이 64비트(=8바이트)이므로 8N 바이트가 추가로 필요함

## Linked List의 기능
### 임의의 위치에 있는 원소를 확인/변경
시간 복잡도 : O(N)  
원하는 위치까지 이동 O(k) = O(N)  
임의의 위치의 원소를 변경 O(1)
### 임의의 위치에 원소를 추가
시간 복잡도 : O(1)*  
임의의 위치에 원소를 추가 O(1)
### 임의의 위치의 원소를 제거
시간 복잡도 : O(1)**  
이전 원소가 가지는 다음 주소 값을 다음 원소로 변경

\* ** 원소의 주소를 알고 있을 때 O(1) 성립  

```임의의 위치에서 원소를 추가하거나 임의 위치의 원소를 제거하는 연산을 많이 해야 할 경우에 연결리스트의 사용을 고려```

## Linked List의 구현 in Java

[단방향 Linked List 구현 in Java](https://youtu.be/C1SDkdPvQPA) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/SinglyLinkedList.java)

[LinkedListNode의 구현 in Java](https://youtu.be/IrXYr7T8u_s) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/LinkedListNode.java)

## Linked List의 구현 (응용) in Java

[Linked List 중복값 삭제 in Java](https://youtu.be/Ce4baygLMz0) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/RemoveDups.java)

[단방향 Linked List 뒤부터 세기 in Java](https://youtu.be/Vb24scNDAVg) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/KthToLast.java)

[단방향 Linked List 중간노드 삭제 in Java](https://youtu.be/xI4iPEmkHlc) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/DeleteNode.java)

[Linked List 값에 따라 나누기 in Java](https://youtu.be/xufv1LUy42E) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/Partition.java)

[Linked List Digit 합산 알고리즘 in Java](https://youtu.be/vuJk2JZd3fI) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/Sumlists.java)

[Linked List 회문찾기(Palindrome) in Java](https://youtu.be/34zpNdrgnpA) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/IsPalindrome.java)

[Linked List 교차점찾기 in Java](https://youtu.be/dk4oFGJx3ps) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/GetIntersection.java)

[Linked List 루프찾기](https://youtu.be/AWWxMl9-8CY) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/linkedlist/FindLoop.java)

## LinkedList in collection framework
### 컬렉션 프레임워크의 구성도
![|List|Set|Map|
|:---:|:---:|:---:|
|ArrayList|HashSet|HashMap|
|Vector|TreeSet|HashTable|
|***LinkedList***||TreeMap|](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbrQjiX%2FbtqAnwpKMOP%2FtayUV6l35MsZj4vgsTiLP1%2Fimg.png)

### 생성자
* new LinkedList<제네릭>() : 비어있는 LinkedList Class 인스턴스 1개 생성

### add(), get(), set()
* 데이터 추가, 데이터 확인, 데이터 변경
* add(index) 또는 add(index, value)
* list.get(index)
* list.set(index, value)

### addFirst(), push(), addLast()
* head와 tail 위치에 데이터 추가
* offer(), offerFirst() / offerLast()와 같으나 return 타입이 다름
* add 메서드는 void, offer 메서드는 boolean 타입 반환
* 데이터 추가 시 발생할 수 있는 에러 처리를 해야할 때는 boolean 타입을 사용하는 것이 좋음
* addFirst()는 add(0,value)와 같음
* list.addFirst(value);
* list.addLast(value);

### element() / getFirst() / peek() / peekFirst()
* 첫 번째 값(head) 추출
* get(0)과 동일
* list.element();
* list.getFirst();

### getLast() / peekLast()
* 마지막 값(tail) 추출
* list.getLast();

### poll() / pollFirst() / pop()
* 첫 번째 값(head) 추출 후 삭제
* list.poll();

### pollLast()
* 마지막 값(tail) 추출 후 삭제
* list.pollLast();

### removeFirst() / removeLast()
* 첫 번째 값(head), 마지막 값(tail) 삭제
* removeFirst()는 remove(0)과 같음
* list.removeFirst();
* list.removeLast();

### removeFirstOccurrence() / removeLastOccurrence()
* 첫 번째(head) / 마지막(tail)부터 검색해서 삭제
* list.removeFirstOccurrence("a");
* list.removeLastOccurrence("b");


## 자료 출처
[[바킹독의 실전 알고리즘] 연결리스트](https://youtu.be/C6MX5u7r72E)  
[[엔지니어대한민국] LinkedList 재생목록](https://www.youtube.com/playlist?list=PLjSkJdbr_gFZQp0KEoo0Y4KkCI5YqxtjZ)  
[[절차대로 생각하고 객체로 코딩하기] 컬렉션 프레임워크](https://codevang.tistory.com/131)
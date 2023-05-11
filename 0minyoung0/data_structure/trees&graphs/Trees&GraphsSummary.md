# 트리, 그래프 요약

## 그래프란 
정점과 간선으로 이루어진 자료구조

## 그래프의 종류
### 방향성 유무
1. 무방향 그래프(Undirected Graph)
2. 방향 그래프(Directed Graph)
### 사이클 유무
1. 순환 그래프(Cyclic Graph)
2. 비순환 그래프(Acyclic Graph)
### 완전 그래프(Complete Graph)
모든 서로 다른 두 정점 쌍이 간선으로 연결된 그래프
### 연결 그래프(Connected Graph)
임의의 두 정점 사이에 경로가 항상 존재하는 그래프
### 단순 그래프(Simple Graph)
두 정점 사이의 간선이 1개 이하이고 루프가 존재하지 않는 그래프  
(루프 : 한 정점에서 시작해 같은 정점으로 돌아오는 간선)
   
## 트리란
무방향이면서 사이클이 없는 연결 그래프(Undirected Acyclic Connected Graph)  
= 연결 그래프이면서 임의의 간선을 제거하면 연결 그래프가 아니게 되는 그래프  
= 임의의 두 점을 연결하는 simple path(정점이 중복해서 나오지 않는 경로)가 유일한 그래프  
= V개의 정점을 가지고 V-1개의 간선을 가지는 연결 그래프  
= 사이클이 없는 연결 그래프이면서 임의의 간선을 추가하면 사이클이 생기는 그래프  
= V개의 정점을 가지고 V-1개의 간선을 가지는 Acyclic(=사이클이 없는) 그래프

## 트리의 종류
### Binary Tree (이진트리)
자식 노드가 최대 두 개인 트리
### Binary Search Tree (이진 검색트리)
왼쪽 노드와 그 이하 자식노드는 현재 값보다 작고, 오른쪽 노드와 그 이하 자식 노드는 현재 값보다 큰 성질을 만족하는 이진트리
### Balanced Bianry Tree (균형 이진트리)
왼쪽과 오른쪽 노드의 높이 차이가 1 이하인 이진 트리를 의미합니다. map, set을 구성하는 레드 블랙 트리는 균형 이진 트리 중 하나입니다.
### Complete Binary Tree (완전 이진트리)
모든 노드들이 레벨별로 왼쪽부터 채워진 이진트리
### Full Bianry Tree (정이진 트리)
자식 노드가 0 또는 두 개인 이진트리
### Perpect Binary Tree (포화 이진 트리)
모든 노드가 꽉 차있는 이진 트리

## 그래프의 표현법 (인접 행렬, 인접 리스트)
||인접 행렬|인접 리스트|
|:---:|:---:|:---:|
|공간 복잡도|O(V^2)|O(V+E)|
|정점 u,v의 연결 여부 확인|O(1)|O(min(deg(u), deg(v)))|
|정점 v와 연결된 모드 정점을 확인|O(v)|O(deg(V))|
|효율적인 상황 1|두 점의 연결 여부를 자주 확인|특정 정점에 연결된 모든 정점 확인|
|효율적인 상황 2|E가 V^2에 가까운 경우|E가 V^2보다 훨씬 작을 때|

## Graph 관련 구현

[Graph 검색 DFS, BFS 구현 in Java](https://youtu.be/_hxFgg7TLZQ) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/DfsBfsTest.java)

[Graph에서 두지점의 경로찾기](https://youtu.be/VHNOQZBXS0o) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/searchPath.java)

[Graph에 명시된 관계에 따라 데이타 뽑아오기](https://youtu.be/Lavp0cwnteA) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/ProjectManagerTest.java)

## Tree 관련 구현

[Binary Tree의 3가지 순회방법 구현하기](https://youtu.be/QN1rZYX6QaA) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/BinaryTreeTraversal.java)

[배열을 이진검색트리로 만들기 in Java](https://youtu.be/9ZZbA2iPjtM) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/ArrayToBinarySearchTree.java)

[이진트리를 레벨단위 리스트로 변형하기](https://youtu.be/Y9Ar9eerxQU) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/BSTtoListTest.java)

[Tree의 Balance 확인하기](https://youtu.be/-m154rqFQng) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/IsBalancedTest.java)

[주어진 트리가 이진검색트리인지 확인하기](https://youtu.be/zhhxBrtaOO0) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/IsValidateBSTTest.java)

[이진검색트리에서 다음노드 찾기 (inorder traverse)](https://youtu.be/6DIxzakjewQ) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/FindNext.java)

[Tree에서 두노드의 첫번째 공통부모 찾기](https://youtu.be/VozLMFJQt3Q) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/CommonAncestorTest.java)

[이진검색트리를 만드는 모든 배열 찾기](https://youtu.be/r3iW552f-kk) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/BSTtoArray.java)

[서브트리인지 확인하기](https://youtu.be/-oQaeT-JV0w) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/ContainsTree.java)

[이진트리에서 랜덤노드가져오기](https://youtu.be/fZP-MFKxdr8) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/GetRandomNode.java)

[트리에서 주어진값을 합산으로가지는 경로의 개수찾기](https://youtu.be/vJi7GrjbgeU) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/CountPathWithSumN.java)

[순회결과로 원본트리 재현하기](https://youtu.be/kGdnFfi2uz8) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/BuildingTreeFromTraversalResults.java)

[BST insertion/deletion](https://youtu.be/xxADG17SveY) [(코드)](https://github.com/DJ-archive/Algorithm-DataStructure/blob/main/0minyoung0/data_structure/tree&graphs/BinarySearchTreeInsertionDeletion.java)

## 자료 출처
[[바킹독의 실전 알고리즘] 0x18강 그래프](https://blog.encrypted.gg/1016)  
[[바킹독의 실전 알고리즘] 0x19강 트리](https://blog.encrypted.gg/1019)  
[[엔지니어대한민국] Trees & Graphs 재생목록](https://www.youtube.com/playlist?list=PLjSkJdbr_gFY8VgactUs6_Jc9Ke8cPzZP)  
[면접을 위한 CS 전공지식 노트] Chapter 5 자료구조
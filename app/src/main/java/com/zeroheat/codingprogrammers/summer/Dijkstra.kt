package com.zeroheat.codingprogrammers.summer

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*


//N개의 마을로 이루어진 나라가 있습니다. 이 나라의 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있습니다. 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데, 서로 다른 마을 간에 이동할 때는 이 도로를 지나야 합니다. 도로를 지날 때 걸리는 시간은 도로별로 다릅니다. 현재 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 합니다. 각 마을로부터 음식 주문을 받으려고 하는데, N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 합니다. 다음은 N = 5, K = 3인 경우의 예시입니다.
//
//위 그림에서 1번 마을에 있는 음식점은 [1, 2, 4, 5] 번 마을까지는 3 이하의 시간에 배달할 수 있습니다. 그러나 3번 마을까지는 3시간 이내로 배달할 수 있는 경로가 없으므로 3번 마을에서는 주문을 받지 않습니다. 따라서 1번 마을에 있는 음식점이 배달 주문을 받을 수 있는 마을은 4개가 됩니다.
//마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개변수로 주어질 때, 음식 주문을 받을 수 있는 마을의 개수를 return 하도록 solution 함수를 완성해주세요.


// 노드의 연결된 노드와 거리를 가짐
internal class Edge(var to: Int, var distance: Int)

// 시작 노드에서 특정 노드에 도착하기 까지 가능한 거리
internal class Info(var node: Int, var distance: Int)


internal class Dijkstra {
    @RequiresApi(Build.VERSION_CODES.N)
    fun solution(N: Int, road: Array<IntArray>, K: Int): Int {
        var answer = 0

        // 해당 노드까지의 최단 거리를 담을 배열 생성
        val dist = IntArray(N + 1)
        Arrays.fill(dist, Int.MAX_VALUE)

        // 각 노드의 인접 노드 리스트를 넣어줄 배열 생성
        val edges: Array<ArrayList<Edge>?> = arrayOfNulls(N + 1)
        for (i in 0..N) {
            edges[i] = ArrayList()
        }

        // 거리 정보를 넣어줄 힙 생성
        val pq = PriorityQueue(object : Comparator<Info> {
            override fun compare(i1: Info, i2: Info): Int {
                return i1.distance - i2.distance
            }
        })

        // 데이터를 담는다.
        for (i in road.indices) {
            for (j in 0..2) {
                edges[road[i][0]]?.add(Edge(road[i][1], road[i][2]))
                edges[road[i][1]]?.add(Edge(road[i][0], road[i][2]))
            }
        }

        // 1번 Node부터 시작
        dist[1] = 0
        pq.offer(Info(1, dist[1]))
        while (!pq.isEmpty()) {
            val info = pq.poll()
            if (info.distance > dist[info.node]) continue
            for (adj in edges[info.node]!!) {
                if (info.distance + adj.distance < dist[adj.to]) {
                    dist[adj.to] = info.distance + adj.distance
                    pq.offer(Info(adj.to, dist[adj.to]))
                }
            }
        }
        for (i in 1 until dist.size) {
            if (dist[i] <= K) answer++
        }
        return answer
    }
}


//자바코드
//import java.util.*;
//
//// 노드의 연결된 노드와 거리를 가짐
//class Edge {
//    int to;
//    int distance;
//
//    public Edge(int to, int distance){
//        this.to = to;
//        this.distance = distance;
//    }
//}
//
//// 시작 노드에서 특정 노드에 도착하기 까지 가능한 거리
//class Info {
//    int node;
//    int distance;
//
//    public Info(int node, int distance){
//        this.node = node;
//        this.distance = distance;
//    }
//}
//
//
//class Solution {
//    public int solution(int N, int[][] road, int K) {
//        int answer = 0;
//
//        // 해당 노드까지의 최단 거리를 담을 배열 생성
//        int[] dist = new int[N+1];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//
//        // 각 노드의 인접 노드 리스트를 넣어줄 배열 생성
//        ArrayList<Edge>[] edges = new ArrayList[N+1];
//        for(int i = 0; i <= N; i++){
//            edges[i] = new ArrayList<Edge>();
//        }
//
//        // 거리 정보를 넣어줄 힙 생성
//        PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>(){
//            @Override
//            public int compare(Info i1, Info i2){
//                return i1.distance - i2.distance;
//            }
//        });
//
//        // 데이터를 담는다.
//        for(int i = 0; i < road.length; i++){
//            for(int j = 0; j < 3; j++){
//                edges[road[i][0]].add(new Edge(road[i][1], road[i][2]));
//                edges[road[i][1]].add(new Edge(road[i][0], road[i][2]));
//            }
//        }
//
//        // 1번 Node부터 시작
//        dist[1] = 0;
//        pq.offer(new Info(1, dist[1]));
//
//        while(!pq.isEmpty()) {
//            Info info = pq.poll();
//            if(info.distance > dist[info.node]) continue;
//            for(Edge adj : edges[info.node]){
//                 if(info.distance + adj.distance < dist[adj.to]){
//                     dist[adj.to] = info.distance + adj.distance;
//                     pq.offer(new Info(adj.to, dist[adj.to]));
//                 }
//            }
//        }
//
//        for(int i = 1; i < dist.length; i++){
//            if(dist[i] <= K) answer++;
//        }
//
//        return answer;
//    }
//}
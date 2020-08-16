package QuizGen;

import java.util.ArrayList;

public class Graph<T> extends DataStructures<T>{
    protected class Node{
        T data;
        ArrayList<Edge> weights;
        Node(T d){data = d;}
        void addLink(Edge cost){weights.add(cost);}
    }
    protected class Edge{
        Node neighbour;
        int weight;
        Edge(Node near, int cost){
            neighbour = near;
            weight = cost;
        }
    }

    @Override
    public void insert(T data) {

    }

    @Override
    public void delete(T data) {

    }

    @Override
    public void find(T data) {

    }

    //public dijkstra()
}

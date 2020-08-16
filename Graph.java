package QuizGen;

import java.util.ArrayList;

public class Graph<T> extends DataStructures<T>{
    protected class Node{
        T data;
        ArrayList<Link> adj;
        Node(T d){data = d;}
        void addLink(Link edge){adj.add(edge);}
    }
    protected class Link{
        Node neighbour;
        int weight;
        Edge(Node vertex, int cost){
            neighbour = vertex;
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

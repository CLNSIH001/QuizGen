package QuizGen;

import java.util.List;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Graph extends RuntimeException{
    private Map<String, Node> vertexMap = new HashMap<String, Node>();
    public static final double infinity = Double.MAX_VALUE;

    protected void insert(String src, String dest, double l){
        Node s = getNode(src);
        Node d = getNode(dest);
        s.addLink(new Link(d, l));
    }
    private Node getNode(String vertex){
        Node v = vertexMap.get(vertex);
        if(v == null){
            v = new Node(vertex);
            vertexMap.put(vertex, v);}
        return v;
    }

    private void clearAll(){
        for (Node n : vertexMap.values())
            n.reset();
    }

    public void printPath(String node){
        Node w = vertexMap.get(node);
        if(w == null)
            throw new NoSuchElementException();
        else if(w.val == infinity)
            System.out.println(node + "can't be reached");
        else{
            System.out.println("Cost is: " + w.val);
            printPath(w);
            System.out.println();}
    }
    private void printPath(Node dest){
        if(dest.prev != null){
            printPath(dest.prev);
            System.out.print(" --> ");
        }
        System.out.print(dest.data);
    }

    public void bfs(String startName){
        clearAll();
        Node start = vertexMap.get(startName);
        if (start == null)
            throw  new NoSuchElementException("No such a Node!");
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(start);
        start.val = 0;
        while(!queue.isEmpty()){
            Node vertex = queue.remove();
            for (Link edge: vertex.adj){
                Node w = edge.neighbour;
                if (w.val == infinity){
                    w.val = vertex.val + 1;
                    w.prev = vertex;
                    queue.add(w);
                }
            }
        }
    }
    public void dijkstra(String Start){
        PriorityQueue<Path> pq = new PriorityQueue<Path>();
        Node start = vertexMap.get(Start);
        if (start == null)
            throw  new NoSuchElementException("No such a Node!");
        clearAll();
        pq.add(new Path(start, 0));
        start.val = 0;
        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()){
            Path vrec = pq.remove( );
            Node n = vrec.v;
            if( n.scratch != 0 ) // already processed n
                continue;
            n.scratch = 1;
            nodesSeen++;
            for (Link l : n.adj){
                Node w = l.neighbour;
                double cvw = l.weight;
                if( cvw < 0 )
                    throw new RuntimeException( "Graph has negative edges" );
                if (w.val > n.val + cvw){
                    w.val = n.val + cvw;
                    w.prev = n;
                    pq.add(new Path(w, w.val));
                }
            }
        }
    }
    public void bellmanFord(String startName){
        clearAll();
        Node start = vertexMap.get(startName);
        if (start == null)
            throw  new NoSuchElementException("No such a Node!");
        Queue<Node> q = new LinkedList<Node>();
        q.add(start);
        start.val = 0;
        start.scratch++;
        while(!q.isEmpty()){
            Node v = q.remove();
            if (v.scratch++ > 2 * vertexMap.size())
                throw new RuntimeException( "Graph has negative cycles" );
            for (Link e : v.adj){
                Node w = e.neighbour;
                double cvw = e.weight;
                if (w.val > v.val+cvw){
                    w.val = v.val +cvw;
                    w.prev = v;
                    if (w.scratch++ %2 == 0)
                        q.add(w);
                    else
                        w.scratch--;
                }
            }
        }
    }
    public void topological(String startName){
        Node start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );
        clearAll( );
        Queue<Node> q = new LinkedList<Node>( );
        start.val = 0;
        // Compute the indegrees
        Collection<Node> vertexSet = vertexMap.values( );
        for( Node v : vertexSet )
            for( Link e : v.adj )
            e.neighbour.scratch++;
        // Enqueue vertices of indegree zero
        for( Node v : vertexSet )
            if( v.scratch == 0 )
            q.add( v );
        int iterations;
        for( iterations = 0; !q.isEmpty( ); iterations++ )
        {
            Node v = q.remove( );
            for( Link e : v.adj )
                {
                Node w = e.neighbour;
                double cvw = e.weight;
                if( --w.scratch == 0 )
                    q.add( w );
                if( v.val == infinity )
                    continue;
                if( w.val > v.val + cvw )
                {
                    w.val = v.val + cvw;
                    w.prev = v;
                }
            }
        }
        if( iterations != vertexMap.size( ) )
            throw new RuntimeException( "Graph has negative cycles" );
    }

    //Content of main will be used in driver to implement the Graph data structure
    /*public static void main(String[] args){
        Graph g = new Graph();
        g.insert("A", "B", -5);
        g.insert("A", "D", 7);
        g.insert("A", "E", 2);
        g.insert("B", "C", 5);
        g.insert("C", "A", 1);
        g.insert("C", "D", -4);
        g.insert("D", "E", 3);
        g.insert("E", "C", 1);
        g.insert("E", "B", 6);
        g.bellmanFord("A");
        g.printPath("E");
        Graph dg = new Graph();
        dg.insert("A", "B", 15);
        dg.insert("A", "D", 80);
        dg.insert("B", "E", 10);
        dg.insert("C", "A", 11);
        dg.insert("D", "B", 25);
        dg.insert("D", "C", 24);
        dg.insert("E", "D", 47);
        dg.dijkstra("A");
        dg.printPath("B");
        dg.dijkstra("B");
        dg.printPath("C");
    }*/
}

class Path implements Comparable<Path>{
    Node v;
    double cost;
    Path(Node vertex, double dist){
        v = vertex;
        cost = dist;
    }
    @Override
    public int compareTo(Path other) {
        double otherCost = other.cost;
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}
class Node{
    String data;
    List<Link> adj;
    double val;
    Node prev;
    int scratch;
    Node(String d){
        data = d;
        adj = new LinkedList<Link>();
    }
    void addLink(Link edge){adj.add(edge);}
    void reset(){
        val = Graph.infinity;
        prev = null;
        scratch = 0;
    }
}
class Link{
    Node neighbour;
    double weight;
    Link(Node vertex, double cost){
        neighbour = vertex;
        weight = cost;
    }
}

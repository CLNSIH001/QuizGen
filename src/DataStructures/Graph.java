package DataStructures;

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
    private String type = "";

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

    public void emptyGraphMap(){
        type = "";
        clearAll();
        vertexMap.clear();
    }

    public String printPath(String node){
        String path = "";
        Node w = vertexMap.get(node);
        if(w == null)
            throw new NoSuchElementException();
        else if(w.val == infinity)
            System.out.println(node + "can't be reached");
        else{
            /*if (type.equals("unweighted")) System.out.println("Path Length is: " + w.val);
            else if (type.equals("dijk")) System.out.println("Distance is: " + w.val +"km");
            else if (type.equals("negative")) System.out.println("Profit/Loss is: R" + w.val);
            else if (type.equals("acyclic")) System.out.println("Vertex Magnitude is: " + w.val);
            else {
                System.out.println("No algorithm was chosen");
                return;
            }*/
            printPath(w, path);}
        return path+"\n";
    }
    private void printPath(Node dest, String p){
        if(dest.prev != null){
            printPath(dest.prev, p);
            p += " --> ";
        }
        p += dest.data;
    }

    public double getNodeValue(String node){
        Node w = vertexMap.get(node);
        if(w == null)
            throw new NoSuchElementException();
        else if(w.val == infinity)
            System.out.println(node + "can't be reached");
        else
            return w.val;
        return infinity;
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
        type = "unweighted";
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
        type = "dijk";
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
        type = "negative";
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
        if( iterations != vertexMap.size( ) ) {
            System.out.println("iterations is " + iterations + " and vMap size is " + vertexMap.size());
            throw new RuntimeException("Graph has negative cycles");
        }
        type = "acyclic";
    }

    //Content of main will be used in driver to implement the Graph data structure
    public static void main(String[] args){
        Graph digraph = new Graph();
        digraph.insert("Jan", "Feb", -5.00);
        digraph.insert("Jan", "Apr", 7.00);
        digraph.insert("Jan", "May", 2.00);
        digraph.insert("Feb", "Mar", 5.00);
        digraph.insert("Mar", "Jan", 1.00);
        digraph.insert("Mar", "Apr", -4.00);
        digraph.insert("Apr", "May", 3.00);
        digraph.insert("May", "Mar", 1.00);
        digraph.insert("May", "Feb", 6.00);
        digraph.bellmanFord("Jan");
        //digraph.printPath("");     //node cost and path
        digraph.emptyGraphMap();
        digraph.insert("Algeria", "Belgium", 7.0);
        digraph.insert("Algeria", "Chile", 3.0);
        digraph.insert("Algeria", "Denmark", 4.0);
        digraph.insert("Belgium", "Greece", 3.0);
        digraph.insert("Chile", "France", 4.0);
        digraph.insert("Denmark", "France", 2.0);
        digraph.insert("Denmark", "Ethiopia", 7.0);
        digraph.insert("France", "Greece", 5.0);
        digraph.insert("Ethiopia", "Greece", 2.0);
        digraph.dijkstra("Algeria");
        //digraph.printPath("");    //node cost and path
        digraph.emptyGraphMap();
        digraph.insert("v0","v1", infinity);
        digraph.insert("v0","v3", infinity);
        digraph.insert("v1","v3", infinity);
        digraph.insert("v1","v4", infinity);
        digraph.insert("v2","v0", infinity);
        digraph.insert("v2","v5", infinity);
        digraph.insert("v3", "v2", infinity);
        digraph.insert("v3", "v4", infinity);
        digraph.insert("v3","v5", infinity);
        digraph.insert("v3","v6", infinity);
        digraph.insert("v4","v6", infinity);
        digraph.insert("v6","v5", infinity);
        digraph.bfs("v2");
        digraph.printPath("v6");
        digraph.emptyGraphMap();
        digraph.insert("A", "B", 4);
        digraph.insert("A", "C", 8);
        digraph.insert("B", "C", 9);
        digraph.insert("B", "D", 10);
        digraph.insert("C", "D", 7);
        digraph.topological("A");
        digraph.printPath("D");
    }
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

/**
 * P2_my_Money_My_Shares
 */
import java.util.*;

class DS {
    String name;
    double weight;
    DS( String n, double w ) {
        this.name = n;
        this.weight = w;
    }
}

public class P2_my_Money_My_Shares {

    static List<Integer> lt ;
    static boolean vis [] ;
    public static void main(String[] args) {
                       
        int price [] = { 50 , 30 , 20 };

        int t1 = 0;
        for ( int i : price ) {
            t1 += i;
        }

        double pram = (double)price[0]/t1;
        double psham = (double)price[1]/t1;
        double prahim = (double)price[2]/t1;
        
        int w [] = { 400 , 100 , 400 , 300 , 200 , 300 ,100 , 200 };
        int sz = w.length;

        Arrays.sort( w );

        int t2 = 0;
        for ( int i : w ) {
            t2 += i;
        }

        double wram = t2 * pram;
        double wsham = t2 * psham;
        double wrahim = t2 * prahim ;

        PriorityQueue< DS > pq = new PriorityQueue<>( (a,b) -> Double.compare( b.weight, a.weight ) );

        pq.add( new DS( "Ram", wram ) );
        pq.add( new DS( "Sham", wsham ) );
        pq.add( new DS( "Rahim", wrahim ) );

        lt = new ArrayList<>();
        vis = new boolean [ sz ];

        while ( ! pq.isEmpty() ) {
            String name = pq.peek().name;
            double wt = pq.poll().weight;
            System.out.print( name + ": " );
            distribute( sz-1, 0, wt, w);
        }

    }

    static void distribute( int cur, int sum, double required_wt, int [] w ) {
        if ( sum == required_wt ) {
            
            printList( w, lt );

            for ( int j : lt ) {
                vis[j] = true;
            }
            return ;
        }
        if ( cur < 0 ) {            
            return ;
        }

        if ( !vis[cur] && (sum + w[cur] <= required_wt) ) {
            lt.add( cur );
            distribute( cur-1, sum + w[cur], required_wt, w );
            lt.remove( lt.size() - 1 );
        }
        else {
            distribute( cur-1, sum, required_wt, w );
        } 
    }

    static void printList( int [] w, List<Integer> lt ) {
        for ( int i : lt ) {
            System.out.print( w[i] + " " );
        }
        System.out.println();
    }
}
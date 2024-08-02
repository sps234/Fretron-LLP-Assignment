/**
 * P3_kill_All_And_Return_Home
 */
import java.util.*;
public class P3_kill_All_And_Return_Home {

    static List<String> path ;
    public static void main(String[] args) {        
        int chessBoard[][] = new int[10][10];
        int startColCastle = 1;
        int startRowCastle = 2;
        chessBoard[startRowCastle][startColCastle] = 2;
        int dirX = 0;
        int dirY = 1;

        int soldierRow [] = { 1, 1, 2, 2, 6, 6, 8, 8, 9, 9, 9 };
        int soldierCol [] = { 1, 4, 4, 8, 2, 5, 2, 4, 1, 5, 8 };

        for (int i = 0; i < soldierRow.length; i++) {
            chessBoard[ soldierRow[i] ][ soldierCol[i] ] = 1;
        }

        path = new ArrayList<>();
        // for ( int [] i : chessBoard ) {           
        //     System.out.println( Arrays.toString( i ) );
        // }
        path.add( "Start: ("+String.valueOf( startColCastle )+","+String.valueOf( startRowCastle )+") " );
        findPath( startRowCastle+dirY, startColCastle+dirX, dirX, dirY, chessBoard );
    }

    static void findPath(  int row, int col, int dirX, int dirY, int chessBoard[][] ){
        
        if( row < 1 || col < 1 || row >= 10 || col >= 10  ){
            return ;
        }
        
        if(  chessBoard[row][col] == 2) {
            path.add( "Arrive ("+String.valueOf( col )+","+String.valueOf( row )+") " );
            System.out.println( path);
            path.remove( path.size()-1 );
            return ;
        }

        if ( chessBoard[row][col] != 1 ) {
            findPath( row+dirY, col+dirX, dirX, dirY, chessBoard );
        }
        else if ( chessBoard[row][col] == 1 ) {

            path.add( "Jump (" +String.valueOf( col )+","+String.valueOf( row )+") " );
            findPath( row+dirY, col+dirX, dirX, dirY, chessBoard );
            path.remove( path.size()-1 );
            
            if ( dirX == 0 && dirY == 1 ) {
                dirX = 1;
                dirY = 0;
            }
            else if ( dirX == 1 && dirY == 0 ) {
                dirX = 0;
                dirY = -1;
            }
            else if ( dirX == 0 && dirY == -1 ) {
                dirX = -1;
                dirY = 0;
            }
            else if ( dirX == -1 && dirY == 0 ) {
                dirX = 0;
                dirY = 1;
            }
            chessBoard[row][col] = 0;
            path.add( "Kill (" +String.valueOf( col )+","+String.valueOf( row ) + "). Turn Left "  );
            findPath( row+dirY, col+dirX, dirX, dirY, chessBoard );
            path.remove( path.size()-1 );
            chessBoard[row][col] = 1;
        }
    }
}
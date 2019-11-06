
/**
 * MapReader helper class reads and sets up the game map from the file
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.FileReader;
public class MapReader
{
    /**
     * reads and sets up the map from a file
     * @param m the map that has to be set up
     */
    public static void readMap(Map m) {
        try
        {
            Scanner scanner = new Scanner(new FileReader("Map.txt"));
            for(int i=0;i<m.length;i++)
            {
                String s =  scanner.next(); 
                for(int j=0;j<m.length;j++)
                {
                     Pos pos = new Pos();
                     pos.x=i;
                     pos.y=j;
                    
                     if(s.charAt(j)=='P') {m.setPit(pos); continue;}
                     
                     if(s.charAt(j)=='O') {m.setObstacle(pos); continue;}
                     
                     if(s.charAt(j)=='G') {m.setGold(pos); continue;}
                     
                }
                
            }
            } catch(Exception e) { System.out.println(e);}
    }
}

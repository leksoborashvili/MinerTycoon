
/**
 * Map class containes the map of the game that is 2D ArrayList with Tile as an element.
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.List;
public class Map
{
    private List<List<Tile>> map = new ArrayList<List<Tile>>();
    /**
     * dimension of the map
     */
    public int length;
    
    private static int goldOnMap = 0;
    /**
     * gold is the total amount of gold that the player has
     */
    public static  int gold = 1000;
    /**
     * Reduces the number of gold tiles on the map
     */
    public void foundGoldOnMap()
    {
        goldOnMap--;
    }
    /**
     * returns the number of gold tiles on the map
     */
    public int checkGoldOnMap()
    {
        return goldOnMap;
    }
    
    /**
     * initiates the 2D arrayList of length l. That is the map.
     * @param l the length of the map
     */
    public Map(int l)
    {
        length =l;
        
        for(int i=0;i<l;i++)
        {
            List<Tile> list = new ArrayList<Tile>();
            for(int j=0;j<l;j++)
            {
                Tile t = new Tile();
                list.add(t);
            }
            map.add(list);
        }
    }
    /**
     * inserts that the dwarf has been to specified position
     * @param pos position of the tile
     * @param d dwarf 
     */
    public void insert(Pos pos, Dwarf d)
    {
        int ID = d.getID();
        Tile t = map.get(pos.x).get(pos.y);
        t.bst.put(d.getID(),d.getID());
        map.get(pos.x).set(pos.y,t);
    }
    /**
     * returns the tile at the position (x,y);
     * @return the tile at (x,y);
     */
    public Tile get(int x, int y)
    {
        return map.get(x).get(y);
    }
    /**
     * prints number of elements in the BST of the whole map in each tile
     */
    public void printMapNumbers()
    {
        for(int i =0;i<length;i++)
        {
            for(int j =0;j<length;j++)
            {
                if(get(i,j).type=="pit") {System.out.printf("%-4s","P"); continue;}
                if(get(i,j).type=="obstacle") {System.out.printf("%-4s", "X"); continue;}
                if(get(i,j).type=="gold") {System.out.printf("%-4s","G"); continue;}
                System.out.printf("%-4d",map.get(i).get(j).bst.size());
            }
            System.out.println();
        }
    }
    
    /**
     * sets obstacle at given position
     * @param pos position of the obstacle
     */
    public void setObstacle(Pos pos)
    {
        List<Tile> l = map.get(pos.x);
        Tile t = l.get(pos.y);
        t.type = "obstacle";
        l.set(pos.y,t);
        map.set(pos.x,l);
    }
    /**
     * sets gold tile at given position
     * @param pos position of the gold
     */
    public void setGold(Pos pos)
    {
        goldOnMap++;
        List<Tile> l = map.get(pos.x);
        Tile t = l.get(pos.y);
        t.type = "gold";
        l.set(pos.y,t);
        map.set(pos.x,l);
    }
    
    /**
     * sets the pit at given position
     * @param pos position of the pit
     */
    public void setPit(Pos pos)
    {
        List<Tile> l = map.get(pos.x);
        Tile t = l.get(pos.y);
        t.type = "pit";
        l.set(pos.y,t);
        map.set(pos.x,l);
    }
}

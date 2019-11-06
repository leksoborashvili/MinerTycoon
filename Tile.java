
/**
 * Tile is the unit of map on each square
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.TreeMap;
public class Tile
{
    /**
     * RedBlack tree that keeps track of the visited dwarves on this tile
     * 
     */
    TreeMap<Integer,Integer> bst = new TreeMap<Integer,Integer>();
    
    /**
     * type of the tile 
     */
    String type;
    
    /**
     * Constructor for tile setting default type to rock
     */
    public Tile()
    {
        type = "rock";
    }
}

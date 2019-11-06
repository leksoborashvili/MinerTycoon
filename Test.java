
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.PriorityQueue;
public class Test implements Comparable<Test>
{
    Integer numberofDwarves;
    String dir;
    
    public int compareTo(Test t)
    {
        return this.numberofDwarves.compareTo(t.numberofDwarves);
    }
}

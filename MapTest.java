

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileWriter;
/**
 * The test class MapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MapTest
{
    /**
     * Default constructor for test class MapTest
     */
    public MapTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void map1() throws Exception
    {
        Map map1 = new Map(5);
        
        Pos pos1 = new Pos();
        pos1.x =1;
        pos1.y =1;
        map1.setGold(pos1);
        
        
        MainClass mc = new MainClass();
        
        assertEquals(map1.get(1,1).type,"gold");
        
    }
    @Test
    public void map2() throws Exception
    {
        Map map1 = new Map(5);
        Pos pos1 = new Pos();
        map1.setGold(pos1);
        pos1.x=3;
        pos1.y=4;
        map1.setObstacle(pos1);
       
        
        MainClass mc = new MainClass();
        
        assertEquals(map1.get(3,4).type,"obstacle");
        
    }
    @Test
    public void map3() throws Exception
    {
        Map map1 = new Map(5);
        Pos pos1 = new Pos();
        map1.setGold(pos1);
        pos1.x=2;
        pos1.y=3;
        map1.setPit(pos1);
       
        
        MainClass mc = new MainClass();
        
        assertEquals(map1.get(2,3).type,"pit");
        
    }
    @Test
    public void map4() throws Exception
    {
        Map map1 = new Map(5);
        Pos pos1 = new Pos();
        map1.setGold(pos1);
        pos1.x=3;
        pos1.y=4;
        map1.setObstacle(pos1);
       
        
        MainClass mc = new MainClass();
        
        assertEquals(map1.get(3,4).type,"obstacle");
        
    }
    @Test
    public void map5() throws Exception
    {
        Map map1 = new Map(5);
        Pos pos1 = new Pos();
        
        pos1.x=3;
        pos1.y=4;
        Dwarf d = new Dwarf(1);
        
       
        
        MainClass mc = new MainClass();
        
        assertEquals(map1.get(3,4).type,"rock");
        
    }
}


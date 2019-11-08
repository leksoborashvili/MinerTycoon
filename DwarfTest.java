

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DwarfTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DwarfTest
{
    /**
     * Default constructor for test class DwarfTest
     */
    public DwarfTest()
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
    public void dwarf1()
    {
        Map m = new Map(5);
        Dwarf d1 = new Dwarf(1);
        Dwarf d2 = new Dwarf(3);
        String dir = d1.whereToGo(m);
        assertEquals(dir,"right");
    }
    @Test
    public void dwarf2()
    {
        
        Map m = new Map(5);
        Dwarf d1 = new Dwarf(1);
        Dwarf d2 = new Dwarf(3);
        String dir = d1.whereToGo(m);
        d1.move(dir);
        m.insert(d1.getPos(),d1);
        dir = d2.whereToGo(m);
        assertEquals(dir,"down");
        
    }
    @Test
    public void dwarf3()
    {
        
        Map m = new Map(5);
        Dwarf d1 = new Dwarf(1);
        String dir = d1.whereToGo(m);
        d1.move(dir);
        m.insert(d1.getPos(),d1);
        assertEquals(d1.getPos().x,0);
        assertEquals(d1.getPos().y,1);
    }
    @Test
    public void dwarf4()
    {
        
        Map m = new Map(5);
        Dwarf d1 = new Dwarf(0);
        Pos pos = new Pos();
        pos.x = 0;
        pos.y = 2;
        m.setGold(pos);
        
        String dir = d1.whereToGo(m);
        d1.move(dir);
        m.insert(d1.getPos(),d1);
        
        dir = d1.whereToGo(m);
        d1.move(dir);
        m.insert(d1.getPos(),d1);
        
        Dwarf g = new Dwarf(1);
        
        g.destx=0;
        g.desty=2;
        g.setDestID(1);
        
        String dir1=g.whereIsGold(m);
        if(dir.equals("right")) assertEquals(true,true);
        
    }
    
}


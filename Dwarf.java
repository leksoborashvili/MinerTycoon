

/**
 * Dwarf class has information about every dwarf. These dwarves have different levels and unique ID.
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.*;

public class Dwarf implements Comparable<Dwarf>
{
    
    private Integer ID;
    private static int objCounter;
    //pos is the position of each dwarf on the map
    private Pos pos = new Pos();
    //path keeps track of previously visited tiles. Necessary for the recursion
    private Stack<String> path = new Stack<String>();
    
    //level defines the abylities of the dwarf
    private int level;
    
    private int destID=-1;
    //this is used by collector dwarves
    /**
     * destination x coordinate
     */
    public int destx;
    /**
     * destination y coordinate
     */
    public int desty;
    //this is used to keep track of each level dwarves
    /**
     * number of lvl1 dwarves
     */
    public static int lvl1=0;
    /**
     * number of lvl2 dwarves
     */
    public static int lvl2=0;
    /**
     * number of lvl3 dwarves
     */
    public static int lvl3=0;
    /**
     * initiates new dwarf object. Placing it on the (0,0) tile on the map.
     * giving different access level.
     * @param lvl the access level that given dwarf has.
     */
    public Dwarf(int lvl)
    {
        objCounter++;
        ID=objCounter;
        level = lvl;
        pos.x=0;
        pos.y=0;
        if(lvl==1) lvl1++;
        if(lvl==2) lvl2++;
        if(lvl==3) lvl3++;
    }
    
    /**
     * returns the ID of the dwarf
     * @return the ID of the dwarf
     */
    public Integer getID()
    {
        return ID;
    }
    /**
     * returns the number of dwarves
     * @return number of dwarves
     */
    public static Integer getNumberofDwarves()
    {
        return objCounter;
    }
    /**
     * sets destination ID
     * @param dest destination
     */
    public void setDestID(int dest)
    {
        destID=dest;
    }
    /**
     * gets destination ID
     * @return destination ID
     */
    public int getDestID()
    {
        return destID;
    }
    
    /**
     * returns the level of the dwarf
     * @return level of the dwarf
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * returns the direction dwarf went before to come to this position
     * @return the direction dwarf moved
     */
    public String getFromStack()
    {
        return path.pop();
    }
    
    /**
     * returns position for the dwarf
     * @return the position of the dwarf
     */
    public Pos getPos()
    {
        return pos;
    }
    /**
     * Shuffles the order dwarves perform actions
     * @param dwarves list of the available dwarves
     * @param orderDwarves shuffled deque of the dwarves
     */
    public static void shuffleDwarves(List<Dwarf> dwarves,Deque<Dwarf> orderDwarves)
    {
        Random r = new Random();
        for(int i=dwarves.size()-1;i>0;i--)
        {
            int j = r.nextInt(i);
            Dwarf temp = dwarves.get(i);
            dwarves.set(i,dwarves.get(j));
            dwarves.set(j,temp);
        }
        for(int i=0;i<dwarves.size();i++)
        {
            orderDwarves.addLast(dwarves.get(i));
        }
    }
    /**
     * adjustes the position of the dwarf according to the direction.
     * @param direction direction of the dwarf movement
     * @return true if the movement is successful
     */
    public void move(String direction)
    {
      
        if(direction == "left"){
            if(this.pos.y==0) return ;
            this.pos.y--;
            path.add("left");
            return ;
            
        }   else if(direction =="right"){
            this.pos.y++;
            path.add("right");
            return ;
        }   else if(direction == "up"){
            if(this.pos.x==0) return ;
            this.pos.x--;
            path.add("up");
            return ;
        }   else if(direction == "down"){
            this.pos.x++;
            path.add("down");
            return ;
        } else if(direction == "return" && path.size()>0)
            {
                String dir = path.pop();
                if(dir=="right") {this.pos.y--; return ;}
                if(dir=="left") {this.pos.y++; return ;}
                if(dir=="up") {this.pos.x++; return ;}
                if(dir=="down") {this.pos.x--; return ;}
            }
        
        
    }
    
    /**
     * returns where the direction the dwarf should go.
     * @param m the current situation of the map.
     * @return direction for the dwarf to go.
     */
    public String whereToGo(Map m)
    {
        PriorityQueue<Direction> directions = new PriorityQueue<>();
        
        if(pos.y<m.length-1 && m.get(pos.x,pos.y+1).type!="obstacle") 
        {   
            Direction dir1 = new Direction();
            dir1.numberofDwarves=m.get(pos.x,pos.y+1).bst.size();
            dir1.dir="right";
            if(m.get(pos.x,pos.y+1).type.equals("pit"))
            {
                if(this.level==3) directions.add(dir1);
            } else directions.add(dir1);
        }
        if(pos.x<m.length-1 && m.get(pos.x+1,pos.y).type!="obstacle") 
        {
            Direction dir2 = new Direction();
            dir2.numberofDwarves=m.get(pos.x+1,pos.y).bst.size();
            dir2.dir="down";
            if(m.get(pos.x+1,pos.y).type.equals("pit"))
            {
                if(this.level==3) directions.add(dir2);
            } else directions.add(dir2);
            
        }
        
        if(pos.y>0 && m.get(pos.x,pos.y-1).type!="obstacle") 
        {
            Direction dir3 = new Direction();
            dir3.numberofDwarves=m.get(pos.x,pos.y-1).bst.size();
            dir3.dir="left";
            if(m.get(pos.x,pos.y-1).type.equals("pit"))
            {
                if(this.level==3) directions.add(dir3);
            } else directions.add(dir3);
            
        }
        
        if(pos.x>0 && m.get(pos.x-1,pos.y).type!="obstacle") 
        {
            Direction dir4 = new Direction();
            dir4.numberofDwarves=m.get(pos.x-1,pos.y).bst.size();
            dir4.dir="up";
            if(m.get(pos.x-1,pos.y).type.equals("pit"))
            {
                if(this.level==3) directions.add(dir4);
            } else directions.add(dir4);
            
        }
        
        for(int i=0;i<directions.size();i++)
        {
            Direction direction = directions.poll();
            String dir = direction.dir;
            if(dir=="right")
                {
                    Tile t = m.get(pos.x,pos.y+1);
                    if(t.bst.get(ID)==null) return "right"; 
                }
            if(dir=="down")
                {
                    Tile t = m.get(pos.x+1,pos.y);
                    if(t.bst.get(ID)==null) return "down"; 
                }
            if(dir=="left")
                {
                    Tile t = m.get(pos.x,pos.y-1);
                    if(t.bst.get(ID)==null) return "left"; 
                }
            if(dir=="up")
                {
                    Tile t = m.get(pos.x-1,pos.y);
                    if(t.bst.get(ID)==null) return "up"; 
                }
        }
        /*
        //the case where the dwarf will try to go unmined position
        for(int i=0;i<directions.size();i++)
        {
            String dir = directions.get(i);
            if(dir=="right")
                {
                    Tile t = m.get(pos.x,pos.y+1);
                    if(t.bst.size()==0) return "right"; 
                }
            if(dir=="down")
                {
                    Tile t = m.get(pos.x+1,pos.y);
                    if(t.bst.size()==0) return "down"; 
                }
            if(dir=="left")
                {
                    Tile t = m.get(pos.x,pos.y-1);
                    if(t.bst.size()==0) return "left";
                }
            if(dir=="up")
                {
                    Tile t = m.get(pos.x-1,pos.y);
                    if(t.bst.size()==0) return "up"; 
                }
        }
        for(int i=0;i<directions.size();i++)
        {
            String dir = directions.get(i);
            if(dir=="right")
                {
                    Tile t = m.get(pos.x,pos.y+1);
                    if(t.bst.get(ID)==null) return "right"; 
                }
            if(dir=="down")
                {
                    Tile t = m.get(pos.x+1,pos.y);
                    if(t.bst.get(ID)==null) return "down"; 
                }
            if(dir=="left")
                {
                    Tile t = m.get(pos.x,pos.y-1);
                    if(t.bst.get(ID)==null) return "left"; 
                }
            if(dir=="up")
                {
                    Tile t = m.get(pos.x-1,pos.y);
                    if(t.bst.get(ID)==null) return "up"; 
                }
        }
        */
        //if the miner has been in all the directions he will just go back 
        if(path.size()==0) return "stay";
        
        return "return";
    }
    /**
     * returns where the gold miner should go according to the dwarf ID that found the gold
     * @param m the map
     * @return direction to go
     */
    public String whereIsGold(Map m)
    {
        if(destID==(-1)) 
        {   
            if(pos.x==0 && pos.y==0) return "stay";
                else return "return";
            }
        ArrayList<String> directions = new ArrayList<>();
        if(pos.y<m.length-1 && m.get(pos.x,pos.y+1).type!="obstacle") directions.add("right");
        if(pos.x<m.length-1 && m.get(pos.x+1,pos.y).type!="obstacle") directions.add("down");
        if(pos.y>0 && m.get(pos.x,pos.y-1).type!="obstacle") directions.add("left");
        if(pos.x>0 && m.get(pos.x-1,pos.y).type!="obstacle") directions.add("up");
        for(int i=0;i<directions.size();i++)
        {
            String dir = directions.get(i);
            if(dir=="right")
                {
                    Tile t = m.get(pos.x,pos.y+1);
                    if(t.bst.get(ID)==null && t.bst.get(destID)!=null) return "right"; 
                }
            if(dir=="down")
                {
                    Tile t = m.get(pos.x+1,pos.y);
                    if(t.bst.get(ID)==null && t.bst.get(destID)!=null) return "down"; 
                }
            if(dir=="left")
                {
                    Tile t = m.get(pos.x,pos.y-1);
                    if(t.bst.get(ID)==null && t.bst.get(destID)!=null) return "left"; 
                }
            if(dir=="up")
                {
                    Tile t = m.get(pos.x-1,pos.y);
                    if(t.bst.get(ID)==null && t.bst.get(destID)!=null) return "up"; 
                }
        }
        return "return";
    }
    /**
     * sets new ID for the dwarf
     */
    public void setNewID()
    {
        objCounter++;
        ID=objCounter;
    }
    /**
     * resets all the static values
     * 
     */
    public static void nullify()
    {
        lvl1=0;
        lvl2=0;
        lvl3=0;
        objCounter=0;
    }
    /**
     * comparable that compares dwarves with their ID
     * @param d dwarf that is compared to
     * @return integer value that gives us the value of compared IDs
     */
    @Override
    public int compareTo(Dwarf d)
    {
        return this.ID.compareTo(d.getID());
    }
}

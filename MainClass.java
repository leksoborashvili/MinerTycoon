//
/**
 * The game runs from this class
 * args - "Diggers lvl 1","Collectors lvl 2","Builder+Digger lvl 3"
 * "50","50","20"
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainClass
{
    public static void main(String[] args) throws Exception {
            
            
            //setting up a map everything is rock at the start
            Map m = new Map(30);
            MapReader.readMap(m);
            Pos position = new Pos();
            
            /*position.x=0;
            position.y=3;
            m.setObstacle(position);
            
            position.x=4;
            position.y=2;
            m.setPit(position);
            position.x=0;
            position.y=1;
            m.setPit(position);
            position.x= 1;
            position.y= 4;
            m.setGold(position);
            position.x =2;
            position.y =2;
            m.setGold(position);
            position.x = 2;
            position.y= 3;
            m.setGold(position);*/
            Scanner s = new Scanner(System.in);
            
            Deque<Dwarf> dwarvesQueue = new ArrayDeque<Dwarf>();
            List<Dwarf> minerDwarves = new ArrayList<Dwarf>();
            
            List<Dwarf> goldCollectors = new ArrayList<Dwarf>();
            Deque<Integer> foundGold = new ArrayDeque<Integer>();
            
            int b = 1;
            //creating miners
            
            
            //lvl 1 dwarves
            for(int i=0;i<Integer.parseInt(args[0]);i++)
            {
                Dwarf d = new Dwarf(1);
                minerDwarves.add(d);
                m.insert(d.getPos(),d);
            }
            //lvl 3 dwarves
            for(int i=0;i<Integer.parseInt(args[2]);i++)
            {
                Dwarf d = new Dwarf(3);
                minerDwarves.add(d);
                m.insert(d.getPos(),d);
            }
            //lvl 2 dwarves
            for(int i=0;i<Integer.parseInt(args[1]);i++)
            {
                Dwarf g = new Dwarf(2);
                goldCollectors.add(g);
                m.insert(g.getPos(),g);
            }
            
            Dwarf.shuffleDwarves(minerDwarves,dwarvesQueue);
            
            for(int i=0;i<minerDwarves.size();i++)
            {
               // System.out.println(dwarvesQueue.pollFirst().getID());
            }
             
           
            //System.out.println("Gold on map " + m.checkGoldOnMap());
            int t=1;
            while(b==1)
            {
                Dwarf.shuffleDwarves(minerDwarves,dwarvesQueue);
                for(int i=0;i<minerDwarves.size();i++)
                {
                 Dwarf d = dwarvesQueue.pollFirst();
                 String dir = d.whereToGo(m);
                 //System.out.println(d.getID());
                 //System.out.println(dir);
                 d.move(dir);
                 m.insert(d.getPos(),d);
                 if(m.get(d.getPos().x,d.getPos().y).type=="gold" && m.get(d.getPos().x,d.getPos().y).bst.size()==1 && dir!="return") 
                    {foundGold.addLast(d.getID());}
                 if(m.get(d.getPos().x,d.getPos().y).type=="pit" && d.getLevel()==3) 
                    {m.get(d.getPos().x,d.getPos().y).type="rock";}
                 //m.printMapNumbers();
                 
                }
                //System.out.println("gold queue  " + foundGold.size());
                
                for(int i=0;i<goldCollectors.size();i++)
                {
                    Dwarf g = goldCollectors.get(i);
                    String dir = g.whereIsGold(m);
                    //System.out.println(dir + " " + g.getDestID());
                    g.move(dir);
                    m.insert(g.getPos(),g);
                    if(m.get(g.getPos().x,g.getPos().y).type=="gold") 
                        {
                            m.foundGoldOnMap();
                            m.get(g.getPos().x,g.getPos().y).type="rock";
                            Map.gold+=50;
                            goldCollectors.get(i).setDestID(-1);
                        } 
                    //m.printMapNumbers();
                    if(dir=="stay" && foundGold.size()>0)  
                        {goldCollectors.get(i).setNewID(); goldCollectors.get(i).setDestID(foundGold.pollFirst());}
                }
                if(m.checkGoldOnMap()==0) {System.out.println("Game Over"); break;}
                
                t++;
                System.out.print('\u000C');
                //TimeUnit.MILLISECONDS.sleep(500);
                m.printMapNumbers();
                //System.out.print('\u000C');

            }
            
            System.out.println(Map.gold +" "+ t);
            System.out.println("gold per min : " + Map.gold/t);   
    }
}

import static java.lang.Math.abs;
import java.util.*;
import javax.swing.*;

public class Grasshopper extends Thread { 
    //status
    private int Speed = 9;
    private int MaxHungry = 10000;
    private int Hungry = 3000;
    private int dropLate = 5000;
    private boolean Alive = true;
    private int TargetFood;
    private int Distance       , Direction      ,Destination;
    //components
    private JLabel This_Label;
    private Thread AnimationThread;
    private int Num_Animation = 1;
    //components data
    private int Width          , Height;
    private int CurX           , CurY;
    //pictures
    private ArrayList<MyImageIcon> Image = new ArrayList<MyImageIcon>();
    //sound
    private ArrayList<MySoundEffect> Sound = new ArrayList<MySoundEffect>();
    //linked data
    private JLabel gamepane;
    private Silo Silo ;
    private ArrayList<Food> FOOD;
    private ArrayList<Grasshopper> GRASSHOPPER;
    
    public Grasshopper(JLabel g,ArrayList<Food> F,ArrayList<Grasshopper> A,ArrayList<MyImageIcon> M,Silo S,ArrayList<MySoundEffect> s){
        gamepane = g;
        FOOD = F;
        GRASSHOPPER = A;
        Image = M;
        Silo = S;
        Sound = s;
        Width = M.get(0).getIconWidth();
        Height = M.get(0).getIconHeight();
        start();     
    }
    public void run(){
        Random rand = new Random();
        CurX = rand.nextInt(gamepane.getWidth()-400)+200;
        CurY = rand.nextInt(gamepane.getHeight()-400)+200;
        This_Label = new JLabel(Image.get(0));
        This_Label.setBounds(CurX, CurY, Width, Height);
        gamepane.add(This_Label);
        Animation();
        while(Alive){
            walk();
            if(Hungry<0)death();
        }
        gamepane.validate();
        gamepane.repaint();
    }   
    public void walk(){
        Random rand = new Random();
        Distance    = rand.nextInt(300);
        Direction   = rand.nextInt(4); 
        //move left
        if(Direction%4 == 0){
            Destination = CurX - Distance;
            if(Destination <= 170)Destination = 170;
            //animation
            Num_Animation = 1;
            while(Destination <= CurX){
                CurX = CurX - 1;
                Hungry--;
                drop();
                This_Label.setLocation(CurX, CurY);
                try { this.sleep(Speed); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                if(Hungry<1000&&!FOOD.isEmpty())break;
            } 
        }
        //move right
        if(Direction%4 == 1){
            Destination = CurX + Distance;
            if(Destination >= 1096)Destination = 1096;
            //animation
            Num_Animation = 2;
            while(Destination >= CurX){
                CurX = CurX + 1;
                Hungry--;
                drop();
                This_Label.setLocation(CurX, CurY);
                try { this.sleep(Speed); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                if(Hungry<1000&&!FOOD.isEmpty())break;
            }
        }
        //move down
        if(Direction%4 == 2){
            Destination = CurY + Distance; 
            if(Destination >= 500)Destination = 500;
            //animation
            Num_Animation = 1;
            while(Destination >= CurY){
                CurY = CurY + 1;
                Hungry--;
                drop();
                This_Label.setLocation(CurX, CurY);
                try { this.sleep(Speed); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                if(Hungry<1000&&!FOOD.isEmpty())break;
            }
        }
        //move up
        if(Direction%4 == 3){
            Destination = CurY - Distance; 
            if(Destination <= 150)Destination = 150;
            //animation
            Num_Animation = 2;
            while(Destination <= CurY){
                CurY = CurY - 1;
                Hungry--;
                drop();
                This_Label.setLocation(CurX, CurY);
                try { this.sleep(Speed); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                if(Hungry<1000&&!FOOD.isEmpty())break;
            }
        }
        if(Hungry<1000&&!FOOD.isEmpty()){
            System.out.println("Hungry");
            runtoFood();
        }
    }
    public void runtoFood(){
        int d = 1000000,difx = 1,dify = 1,d1,move;
        try{while(!FOOD.isEmpty()){
            //find nearest food
            for(int i = 0;i<FOOD.size();i++){
                int difx1 = (FOOD.get(i).getX()-CurX);
                int dify1 = (FOOD.get(i).getY()-CurY);
                if(difx==0)difx=1;
                if(dify==0)dify=1;
                d1 = difx1*difx1+dify1*dify1;
                if(d1<d){
                    d = d1;
                    difx = difx1;
                    dify = dify1;
                    TargetFood = i;
                }
            }
            //run to food
            difx = FOOD.get(TargetFood).getX()-CurX;
            dify = FOOD.get(TargetFood).getY()-CurY;
            if(difx==0)difx=1;
            if(dify==0)dify=1;
            if(difx<0)Num_Animation = 1;
            else Num_Animation = 2;
            if(abs(difx)>abs(dify)){
                move = difx/abs(difx);
                CurX = CurX+move;
            }
            else {
                move = dify/abs(dify);
                CurY = CurY+move;
            }
            Hungry--;
            drop();
            This_Label.setLocation(CurX, CurY);
            if(Hungry<0)death();
            try { this.sleep(Speed); } 
            catch (InterruptedException e) { e.printStackTrace(); }
            //reached food
            if(This_Label.getBounds().intersects(FOOD.get(TargetFood).getFood_Label().getBounds())){
                eat(FOOD.get(TargetFood),difx);
                break;
            }
        }}catch(IndexOutOfBoundsException e){}
    }
    public void Animation(){
        AnimationThread = new Thread(){
            public void run(){
                int i = 0;
                while(Alive){
                while(Num_Animation==1){
                    if(i%6==0)This_Label.setIcon(Image.get(0));
                    if(i%6==1)This_Label.setIcon(Image.get(1));
                    if(i%6==2)This_Label.setIcon(Image.get(2));
                    if(i%6==3)This_Label.setIcon(Image.get(3));
                    if(i%6==4)This_Label.setIcon(Image.get(4));
                    if(i%6==5)This_Label.setIcon(Image.get(0));
                    i++;
                    try { Thread.sleep(75); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                while(Num_Animation==2){
                    if(i%6==0)This_Label.setIcon(Image.get(5));
                    if(i%6==1)This_Label.setIcon(Image.get(6));
                    if(i%6==2)This_Label.setIcon(Image.get(7));
                    if(i%6==3)This_Label.setIcon(Image.get(8));
                    if(i%6==4)This_Label.setIcon(Image.get(9));
                    if(i%6==5)This_Label.setIcon(Image.get(5));
                    i++;
                    try { Thread.sleep(75); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                while(Num_Animation==3){//eat left
                    if(i%2==0)This_Label.setIcon(Image.get(0));
                    if(i%2==1)This_Label.setIcon(Image.get(10));                    
                    i++;
                    try { Thread.sleep(150); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                while(Num_Animation==4){//eat right
                    if(i%2==0)This_Label.setIcon(Image.get(5));
                    if(i%2==1)This_Label.setIcon(Image.get(11));                    
                    i++;
                    try { Thread.sleep(150); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                }
            }
        };
        AnimationThread.start();
    }
    public void eat(Food food,int x){
        Sound.get(2).playLoop();
        while(food.getCurFood()>0){
            if(x<0)Num_Animation = 3;
            else Num_Animation = 4;
            Hungry = Hungry + food.eaten();
            if(Hungry>=MaxHungry)break;
            try { this.sleep(1); } 
            catch (InterruptedException e) { e.printStackTrace(); }
        } 
        Sound.get(2).stop();
        if(Hungry<=MaxHungry*0.5)runtoFood();
    }
    public void drop(){      
        dropLate--;
        if(dropLate==0){
            new Vstring(gamepane,CurX+50,CurY+50,Image.get(13),Silo,Sound); 
            Sound.get(4).playOnce();
            dropLate = new Random().nextInt(2000)+3000;
        }
    }
    public void forceremove(){
        Num_Animation = 0;
        gamepane.remove(This_Label);
        gamepane.validate();
    }
    public void death(){
        Alive = false;
        Num_Animation = 0;
        This_Label.setIcon(Image.get(12));
        Sound.get(3).playOnce();
        try { this.sleep(1000); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        gamepane.remove(This_Label);
        GRASSHOPPER.remove(this);
        gamepane.validate();
    }   
}


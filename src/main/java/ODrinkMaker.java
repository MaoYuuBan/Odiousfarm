import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ODrinkMaker extends StuffMaker{
    private int CurX = 1190,CurY = 465;
    private int time = 10000;
    private Thread Process = new Thread();
    private JLabel gamepane;
    private Silo Silo;
    private MyImageIcon Made_Image;
    private ArrayList<MySoundEffect> Made_Sound = new ArrayList<MySoundEffect>();
    private Random rand = new Random(); 
    private State state;
    
    public ODrinkMaker(JLabel g,ArrayList<MyImageIcon> M,MyImageIcon h,Silo S,State s,ArrayList<MySoundEffect> m){
        super(M);
        state = s;
        gamepane = g;
        Silo = S;
        Made_Image = h;
        Made_Sound = m;
        setPosition(CurX,CurY);
    }
    public void Make(){
        if(Silo.getNum_Candy()>0&&Silo.getNum_Honey()>0&&Silo.getNum_Violin()>0&&Silo.getNum_Mayonnaise()>0&&!Process.isAlive()){
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            Silo.remove(Candy.class);
            Silo.remove(Honey.class);
            Silo.remove(Violin.class);
            Silo.remove(Mayonnaise.class);
            Process = new Thread() {
                public void run()
                {
                    JProgressBar ProgressBar = new JProgressBar(0, 100);
                    ProgressBar.setBounds(CurX,CurY+150,150,10);
                    ProgressBar.setStringPainted(true);
                    ProgressBar.setValue(0);
                    Animation(time);
                    gamepane.add(ProgressBar);                   
                    System.out.println("Make");
                    Made_Sound.get(6).playLoop();
                    for (int i = 0; i <= 100; i++) {
                        try { Thread.sleep(time/100); } 
                        catch (InterruptedException e) { e.printStackTrace(); }
                        ProgressBar.setValue(i);
                    }               
                    gamepane.add(new ODrink(CurX-50-rand.nextInt(10),CurY+rand.nextInt(50),Made_Image,state));
                    gamepane.remove(ProgressBar);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    gamepane.validate();
                    gamepane.repaint();
                    System.out.println("Success");
                }
            };
	Process.start();
        }
        else {
            System.out.println("No resorce");
            Made_Sound.get(0).playOnce();
        }
    }
}
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class CandyMaker extends StuffMaker{
    private int CurX = 0,CurY = 100;
    private int time = 3000;
    private Thread Process = new Thread();
    private JLabel gamepane;
    private Silo Silo;
    private MyImageIcon Made_Image;
    private ArrayList<MySoundEffect> Made_Sound = new ArrayList<MySoundEffect>();
    private Random rand = new Random(); 
    
    public CandyMaker(JLabel g,ArrayList<MyImageIcon> M,MyImageIcon h,Silo S,ArrayList<MySoundEffect> s){
        super(M);
        gamepane = g;
        Silo = S;
        Made_Image = h;
        Made_Sound = s;
        setPosition(CurX,CurY);
    }
    public void Make(){
        if(Silo.getNum_Sugar()>0&&!Process.isAlive()){
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            Silo.remove(Sugar.class);
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
                    Made_Sound.get(2).playLoop();
                    for (int i = 0; i <= 100; i++) {
                        try { Thread.sleep(time/100); } 
                        catch (InterruptedException e) { e.printStackTrace(); }
                        ProgressBar.setValue(i);
                    }
                    new Candy(gamepane,CurX+170+rand.nextInt(10),CurY+rand.nextInt(50),Made_Image,Silo,Made_Sound);
                    gamepane.remove(ProgressBar);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    gamepane.validate();
                    gamepane.repaint();
                    Made_Sound.get(2).stop();
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

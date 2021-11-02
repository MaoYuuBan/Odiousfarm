import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;

public abstract class StuffMaker extends JLabel implements MouseListener{
    //linked data
    private ArrayList<MyImageIcon> This_Image;
    
    public StuffMaker(ArrayList<MyImageIcon> M){
        super();
        This_Image = M;
    }
    public void setPosition(int x,int y){
        setBounds(x, y, This_Image.get(0).getIconWidth(), This_Image.get(0).getIconHeight());
        setIcon(This_Image.get(0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(this);
    }
    public void Animation(int t){
        Thread AnimationThread = new Thread(){
            public void run(){
                int i = 0;
                int time = 0;
                while(time<=t){
                    if(i%4==0)setIcon(This_Image.get(0));
                    if(i%4==1)setIcon(This_Image.get(1));
                    if(i%4==2)setIcon(This_Image.get(2));
                    if(i%4==3)setIcon(This_Image.get(1));
                    i++;
                    try { Thread.sleep(100); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                    time+=100;
                }
                setIcon(This_Image.get(0));
        }};
        AnimationThread.start();
    }
    public abstract void Make();
    public void mouseReleased(MouseEvent e) { 
        Make();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}  
}

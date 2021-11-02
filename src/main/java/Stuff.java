import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;

public abstract class Stuff extends JLabel implements MouseListener{
    //linked data
    private JLabel gamepane;
    private Silo Silo ;
    private MyImageIcon This_Image;
    private ArrayList<MySoundEffect> This_Sound;
    
    public Stuff(JLabel g,Silo S,MyImageIcon i,ArrayList<MySoundEffect> s){
        super();
        gamepane = g;
        Silo = S;
        This_Image = i;
        This_Sound = s;
    } 
    public void setPosition(int x,int y){
        setBounds(x, y, This_Image.getIconWidth(), This_Image.getIconHeight());
        setIcon(This_Image);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        gamepane.add(this);
        addMouseListener(this);
        drain(this);
    }
    public void drain(Stuff x){
        Thread drain = new Thread(){
            public void run(){
                try { Thread.sleep(60000); } 
                catch (InterruptedException e) { e.printStackTrace(); }
                gamepane.remove(x);
                gamepane.validate();
                gamepane.repaint();
        }};
        drain.start();
    }
    public void mouseReleased(MouseEvent e) {
        if(Silo.getCurCapacity()+this.getCapacity()<=Silo.getMaxCapacity()){
            This_Sound.get(1).playOnce();
            gamepane.remove(this);
            gamepane.validate();
            gamepane.repaint();
            Silo.add(this);
            System.out.println("Silo "+Silo.getCurCapacity()+" Max "+ Silo.getMaxCapacity());
        }
        else This_Sound.get(0).playOnce();    
    }
    public void mouseClicked(MouseEvent e) {    
    }
    public abstract int getCapacity();
    public abstract int getPrice();
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        /*if(Silo.getCurCapacity()+this.getCapacity()<=Silo.getMaxCapacity()){
            gamepane.remove(this);
            gamepane.validate();
            gamepane.repaint();
            Silo.add(this);
            System.out.println("Silo "+Silo.getCurCapacity()+" Max "+ Silo.getMaxCapacity());
        }*/
    }
    public void mouseExited(MouseEvent e) {}  
}

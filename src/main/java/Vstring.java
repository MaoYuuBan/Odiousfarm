import java.util.ArrayList;
import javax.swing.JLabel;

public class Vstring extends Stuff{
    //status
    private int Price = 700;
    private int Capacity = 10;
    
    public Vstring(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
        super(g,S,i,s);
        setPosition( x, y);
    }
    public int getCapacity(){
        return Capacity;
    }
    public int getPrice(){
        return Price;
    }
}

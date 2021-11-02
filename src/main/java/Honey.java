import java.util.ArrayList;
import javax.swing.JLabel;

public class Honey extends Stuff {
    //status
    private int Price = 500;
    private int Capacity = 4;
    
    public Honey(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

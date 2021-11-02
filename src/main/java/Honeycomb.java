import java.util.ArrayList;
import javax.swing.JLabel;

public class Honeycomb extends Stuff{
    //status
    private int Price = 450;
    private int Capacity = 6;
    
    public Honeycomb(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

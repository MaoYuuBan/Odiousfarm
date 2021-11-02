import java.util.ArrayList;
import javax.swing.JLabel;

public class Violin extends Stuff {
    //status
    private int Price = 2500;
    private int Capacity = 7;
    
    public Violin(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

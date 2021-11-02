import java.util.ArrayList;
import javax.swing.JLabel;

public class Egg extends Stuff{
    //status
    private int Price = 4375;
    private int Capacity = 20;
    
    public Egg(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

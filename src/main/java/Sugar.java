import java.util.ArrayList;
import javax.swing.JLabel;

public class Sugar extends Stuff{
    //status
    private int Price = 20;
    private int Capacity = 2;
    
    public Sugar(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

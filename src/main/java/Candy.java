import java.util.ArrayList;
import javax.swing.JLabel;

public class Candy extends Stuff{
    //status
    private int Price = 80;
    private int Capacity = 1;
    
    public Candy(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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
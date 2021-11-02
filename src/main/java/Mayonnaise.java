import java.util.ArrayList;
import javax.swing.JLabel;

public class Mayonnaise extends Stuff {
    //status
    private int Price = 20000;
    private int Capacity = 10;
    public Mayonnaise(JLabel g,int x,int y,MyImageIcon i,Silo S,ArrayList<MySoundEffect> s){
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

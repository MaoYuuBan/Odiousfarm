import java.util.ArrayList;
import javax.swing.*;

public class Food {
    //status
    private int CurFood = 3000;
    private int MaxFood = 5000;
    private int HalfFood = MaxFood/2;
    private int MinFood = HalfFood/2;
    //components
    private JLabel Food_Label;
    //pictures
    private ArrayList<MyImageIcon> Food_Image = new ArrayList<MyImageIcon>();
    //components data
    private int Width    = 50 , Height = 50;
    private int CurX          , CurY;
    //linked data
    private JLabel gamepane;
    private ArrayList<Food> FOOD;
    
    
    public Food(int x,int y,JLabel g,ArrayList<MyImageIcon> I,ArrayList<Food> F){
        CurX = x - 25; CurY = y - 25;
        gamepane = g;
        Food_Image = I;
        FOOD = F;
        Food_Label = new JLabel(Food_Image.get(0));
        Food_Label.setBounds(CurX, CurY, Width, Height);
        gamepane.add(Food_Label);  
        gamepane.validate();
        gamepane.repaint();
    }
    synchronized public int eaten(){
        CurFood--;
        if(CurFood==HalfFood)Food_Label.setIcon(Food_Image.get(1));
        if(CurFood==MinFood)Food_Label.setIcon(Food_Image.get(2));
        if(CurFood==0){
            gamepane.remove(Food_Label);
            FOOD.remove(this);
        }
        if(CurFood<0)return 0;
        gamepane.validate();
        gamepane.repaint();
        return 1;
    }
    public JLabel getFood_Label(){
        return Food_Label;
    }
    public int getCurFood(){
        return CurFood;
    }
    public int getX(){
        return CurX;
    }
    public int getY(){
        return CurY;
    }
}

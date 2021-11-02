import java.util.ArrayList;
import javax.swing.JLabel;

public class Silo extends JLabel{
    private int MaxCapacity;
    private int CurX = 700,CurY = 530;
    private ArrayList<MyImageIcon> This_Image;
    //stuff
    private ArrayList<Sugar> SUGAR = new ArrayList<Sugar>();
    private ArrayList<Honeycomb> HONEYCOMB = new ArrayList<Honeycomb>();
    private ArrayList<Vstring> STRING = new ArrayList<Vstring>();
    private ArrayList<Egg> EGG = new ArrayList<Egg>();
    private ArrayList<Candy> CANDY = new ArrayList<Candy>();
    private ArrayList<Honey> HONEY = new ArrayList<Honey>();
    private ArrayList<Violin> VIOLIN = new ArrayList<Violin>();
    private ArrayList<Mayonnaise> MAYONNAISE = new ArrayList<Mayonnaise>();
   
    public Silo(int c,ArrayList<MyImageIcon> M){
        super();
        MaxCapacity = c;
        This_Image = M;
        
        setBounds(CurX, CurY, M.get(0).getIconWidth(), M.get(0).getIconHeight());
        setIcon(This_Image.get(0));
    }
    public void setCurIcon(){
        if(getCurCapacity()<MaxCapacity*20/100)setIcon(This_Image.get(0));
        else if(getCurCapacity()>MaxCapacity*0.9)setIcon(This_Image.get(3));
        else if(getCurCapacity()>MaxCapacity*0.5)setIcon(This_Image.get(2));
        else if(getCurCapacity()>MaxCapacity*0.1)setIcon(This_Image.get(1));
    }
    public void add(Stuff s){
        if(s.getClass().equals(Sugar.class))SUGAR.add((Sugar)s);
        else if(s.getClass().equals(Honeycomb.class))HONEYCOMB.add((Honeycomb)s);
        else if(s.getClass().equals(Vstring.class))STRING.add((Vstring)s);
        else if(s.getClass().equals(Egg.class))EGG.add((Egg)s);
        else if(s.getClass().equals(Candy.class))CANDY.add((Candy)s);
        else if(s.getClass().equals(Honey.class))HONEY.add((Honey)s);
        else if(s.getClass().equals(Violin.class))VIOLIN.add((Violin)s);
        else if(s.getClass().equals(Mayonnaise.class))MAYONNAISE.add((Mayonnaise)s);
        setCurIcon();
    }
    public void remove(Class c){
        try{if(c.equals(Sugar.class))SUGAR.remove(0);
        else if(c.equals(Honeycomb.class))HONEYCOMB.remove(0);
        else if(c.equals(Vstring.class))STRING.remove(0);
        else if(c.equals(Egg.class))EGG.remove(0);
        else if(c.equals(Candy.class))CANDY.remove(0);
        else if(c.equals(Honey.class))HONEY.remove(0);
        else if(c.equals(Violin.class))VIOLIN.remove(0);
        else if(c.equals(Mayonnaise.class))MAYONNAISE.remove(0);
        }catch(Exception e){}
        setCurIcon();
    }
    public int getCurCapacity(){
        int totalcapacity = 0;
        if(SUGAR.size()!=0)totalcapacity = totalcapacity + SUGAR.size()*SUGAR.get(0).getCapacity();
        if(HONEYCOMB.size()!=0)totalcapacity = totalcapacity + HONEYCOMB.size()*HONEYCOMB.get(0).getCapacity();
        if(STRING.size()!=0)totalcapacity = totalcapacity + STRING.size()*STRING.get(0).getCapacity();
        if(EGG.size()!=0)totalcapacity = totalcapacity + EGG.size()*EGG.get(0).getCapacity();
        if(CANDY.size()!=0)totalcapacity = totalcapacity + CANDY.size()*CANDY.get(0).getCapacity();
        if(HONEY.size()!=0)totalcapacity = totalcapacity + HONEY.size()*HONEY.get(0).getCapacity();
        if(VIOLIN.size()!=0)totalcapacity = totalcapacity + VIOLIN.size()*VIOLIN.get(0).getCapacity();
        if(MAYONNAISE.size()!=0)totalcapacity = totalcapacity + MAYONNAISE.size()*MAYONNAISE.get(0).getCapacity();
        return totalcapacity;
    }
    public int getMaxCapacity(){return MaxCapacity;}
    public int getNum_Sugar(){return SUGAR.size();}
    public int getNum_Honeycomb(){return HONEYCOMB.size();}
    public int getNum_String(){return STRING.size();}
    public int getNum_Egg(){return EGG.size();}
    public int getNum_Candy(){return CANDY.size();}
    public int getNum_Honey(){return HONEY.size();}
    public int getNum_Violin(){return VIOLIN.size();}
    public int getNum_Mayonnaise(){return MAYONNAISE.size();}
    public int getPrice_Sugar(){try{return SUGAR.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Honeycomb(){try{return HONEYCOMB.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_String(){try{return STRING.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Egg(){try{return EGG.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Candy(){try{return CANDY.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Honey(){try{return HONEY.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Violin(){try{return VIOLIN.get(0).getPrice();}catch(Exception e){return 0;}}
    public int getPrice_Mayonnaise(){try{return MAYONNAISE.get(0).getPrice();}catch(Exception e){return 0;}}
}

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Shop extends JLabel implements MouseListener,ActionListener{
    //status
    private int         totalsale = 0;
    private int         time = 0;
    private int         Selected_Sugar = 0;
    private int         Selected_Honeycomb = 0;
    private int         Selected_String = 0;
    private int         Selected_Egg = 0;
    private int         Selected_Candy = 0;
    private int         Selected_Honey = 0;
    private int         Selected_Violin = 0;
    private int         Selected_Mayonnaise = 0;
    private Thread      Process;
    //pictures
    private ArrayList<MyImageIcon> This_Image;
    //components  
    private JDialog     Shop_Dialog;
    private JButton     Confirm_Button;
    private JPanel      Shop_Panel;
    private JLabel      Shop_Label;
    private JLabel      Sugar_Label;
    private JLabel      Honeycomb_Label;
    private JLabel      String_Label;
    private JLabel      Egg_Label;
    private JLabel      Candy_Label;
    private JLabel      Honey_Label;
    private JLabel      Violin_Label;
    private JLabel      Mayonnaise_Label;
    private JSlider     Sugar_Slider;
    private JSlider     Honeycomb_Slider;
    private JSlider     String_Slider;
    private JSlider     Egg_Slider;
    private JSlider     Candy_Slider;
    private JSlider     Honey_Slider;
    private JSlider     Violin_Slider;
    private JSlider     Mayonnaise_Slider;
    //linked data
    private State       state;
    private Silo        Silo;
    private MySoundEffect shop_Sound;
    
    public Shop(State s,Silo S,ArrayList<MyImageIcon> M,MySoundEffect m){
        Silo = S;
        This_Image = M;
        state = s;
        shop_Sound = m;
        setBounds(400, 560, This_Image.get(0).getIconWidth(), This_Image.get(0).getIconHeight());
        setIcon(This_Image.get(0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(this);
    }
    public void mousePressed(MouseEvent e) {
        Selected_Sugar = 0;
        Selected_Honeycomb = 0;
        Selected_String = 0;
        Selected_Egg = 0;
        Selected_Candy = 0;
        Selected_Honey = 0;
        Selected_Violin = 0;
        Selected_Mayonnaise = 0;
        
        shop_Sound.playOnce();
        state.getContentPane().setVisible(false);
        
        Shop_Label = new JLabel();
        Shop_Label.setIcon(This_Image.get(9));
        Shop_Label.setBounds(0, 0, 1000, 600);
        Shop_Label.setLayout(null);
        
        Shop_Dialog = new JDialog(state,"Shop");
        Shop_Dialog.setSize(1000, 600);
        Shop_Dialog.setVisible(true);
        Shop_Dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Shop_Dialog.setResizable(false);
        Shop_Dialog.setAlwaysOnTop(true);
        Shop_Dialog.setLocationRelativeTo(state);
        Shop_Dialog.setContentPane(Shop_Label);
        Shop_Dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        addDialogComponent();
        Shop_Dialog.getContentPane().validate();
        Shop_Dialog.getContentPane().repaint();
    }
    public void addDialogComponent(){
        Shop_Panel = new JPanel(new BorderLayout()); 
        Shop_Panel.setBounds(200, 0, 600, 450);
        Shop_Panel.setOpaque(false);
        Shop_Panel.setLayout(new FlowLayout(1,25,25));
        Shop_Label.add(Shop_Panel);  
        
        //confirm button
        Confirm_Button = new JButton();
        Confirm_Button.setBounds(100, 450, 200, 100);
        Confirm_Button.setIcon(This_Image.get(10));
        Confirm_Button.addActionListener(this);
        Confirm_Button.setText("0");
        Confirm_Button.setFont(new Font("Handy",Font.BOLD,16));
        Confirm_Button.setHorizontalTextPosition(JLabel.CENTER);
        Confirm_Button.setVerticalTextPosition(JLabel.BOTTOM); 
        Shop_Label.add(Confirm_Button);
        
        //sugar label
        Sugar_Label = new JLabel();
        Sugar_Label.setIcon(This_Image.get(1));
        Sugar_Label.setSize(50,50);
        
        //honeycomb label
        Honeycomb_Label = new JLabel();
        Honeycomb_Label.setIcon(This_Image.get(2));
        Honeycomb_Label.setSize(50,50);
        
        //string label
        String_Label = new JLabel();
        String_Label.setIcon(This_Image.get(3));
        String_Label.setSize(50,50);
        
        //egg label
        Egg_Label = new JLabel();
        Egg_Label.setIcon(This_Image.get(4));
        Egg_Label.setSize(50,50);
        
        //candy label
        Candy_Label = new JLabel();
        Candy_Label.setIcon(This_Image.get(5));
        Candy_Label.setSize(50,50);
        
        //honey label
        Honey_Label = new JLabel();
        Honey_Label.setIcon(This_Image.get(6));
        Honey_Label.setSize(50,50);
        
        //violin label
        Violin_Label = new JLabel();
        Violin_Label.setIcon(This_Image.get(7));
        Violin_Label.setSize(50,50);
        
        //mayonnaise label
        Mayonnaise_Label = new JLabel();
        Mayonnaise_Label.setIcon(This_Image.get(8));
        Mayonnaise_Label.setSize(50,50);
        
        //sugar slider
        Sugar_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Sugar(),0);
        Sugar_Slider.setMajorTickSpacing(1);
        Sugar_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Sugar_Slider.setPaintTicks(true);
        Sugar_Slider.setPaintLabels(true);
        Sugar_Slider.setOpaque(false);
        Sugar_Slider.addChangeListener(e->{
            Selected_Sugar = Sugar_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Sugar);
        });
        
        //honeycomb slider
        Honeycomb_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Honeycomb(),0);
        Honeycomb_Slider.setMajorTickSpacing(1);
        Honeycomb_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Honeycomb_Slider.setPaintTicks(true);
        Honeycomb_Slider.setPaintLabels(true);
        Honeycomb_Slider.setOpaque(false);
        Honeycomb_Slider.addChangeListener(e->{
            Selected_Honeycomb = Honeycomb_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Honeycomb);
        });
        
        //string slider
        String_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_String(),0);
        String_Slider.setMajorTickSpacing(1);
        String_Slider.setFont(new Font("Handy",Font.BOLD,16));
        String_Slider.setPaintTicks(true);
        String_Slider.setPaintLabels(true);
        String_Slider.setOpaque(false);
        String_Slider.addChangeListener(e->{
            Selected_String = String_Slider.getValue();
            settotalsale();
            System.out.println(Selected_String);
        });
        
        //egg slider
        Egg_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Egg(),0);
        Egg_Slider.setMajorTickSpacing(1);
        Egg_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Egg_Slider.setPaintTicks(true);
        Egg_Slider.setPaintLabels(true);
        Egg_Slider.setOpaque(false);
        Egg_Slider.addChangeListener(e->{
            Selected_Egg = Egg_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Egg);
        });
        
        //candy slider
        Candy_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Candy(),0);
        Candy_Slider.setMajorTickSpacing(1);
        Candy_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Candy_Slider.setPaintTicks(true);
        Candy_Slider.setPaintLabels(true);
        Candy_Slider.setOpaque(false);
        Candy_Slider.addChangeListener(e->{
            Selected_Candy = Candy_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Candy);
        });
        
        //honey slider
        Honey_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Honey(),0);
        Honey_Slider.setMajorTickSpacing(1);
        Honey_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Honey_Slider.setPaintTicks(true);
        Honey_Slider.setPaintLabels(true);
        Honey_Slider.setOpaque(false);
        Honey_Slider.addChangeListener(e->{
            Selected_Honey = Honey_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Honey);
        });
        
        //violin slider
        Violin_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Violin(),0);
        Violin_Slider.setMajorTickSpacing(1);
        Violin_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Violin_Slider.setPaintTicks(true);
        Violin_Slider.setPaintLabels(true);
        Violin_Slider.setOpaque(false);
        Violin_Slider.addChangeListener(e->{
            Selected_Violin = Violin_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Violin);
        });
        
        //mayonnaise slider
        Mayonnaise_Slider = new JSlider(JSlider.VERTICAL,0,Silo.getNum_Mayonnaise(),0);
        Mayonnaise_Slider.setMajorTickSpacing(1);
        Mayonnaise_Slider.setFont(new Font("Handy",Font.BOLD,16));
        Mayonnaise_Slider.setPaintTicks(true);
        Mayonnaise_Slider.setPaintLabels(true);
        Mayonnaise_Slider.setOpaque(false);
        Mayonnaise_Slider.addChangeListener(e->{
            Selected_Mayonnaise = Mayonnaise_Slider.getValue();
            settotalsale();
            System.out.println(Selected_Mayonnaise);
        });
        
        Shop_Panel.add(Sugar_Label);
        Shop_Panel.add(Sugar_Slider);
        
        Shop_Panel.add(Honeycomb_Label);
        Shop_Panel.add(Honeycomb_Slider);
        
        Shop_Panel.add(String_Label);
        Shop_Panel.add(String_Slider);
        
        Shop_Panel.add(Egg_Label);
        Shop_Panel.add(Egg_Slider);
        
        Shop_Panel.add(Candy_Label);
        Shop_Panel.add(Candy_Slider);
        
        Shop_Panel.add(Honey_Label);
        Shop_Panel.add(Honey_Slider);
        
        Shop_Panel.add(Violin_Label);
        Shop_Panel.add(Violin_Slider);
        
        Shop_Panel.add(Mayonnaise_Label);
        Shop_Panel.add(Mayonnaise_Slider);
    }
    public void settotalsale(){
        totalsale = Selected_Sugar*Silo.getPrice_Sugar()
                    +Selected_Honeycomb*Silo.getPrice_Honeycomb()
                    +Selected_String*Silo.getPrice_String()
                    +Selected_Egg*Silo.getPrice_Egg()
                    +Selected_Candy*Silo.getPrice_Candy()
                    +Selected_Honey*Silo.getPrice_Honey()
                    +Selected_Violin*Silo.getPrice_Violin()
                    +Selected_Mayonnaise*Silo.getPrice_Mayonnaise(); 
        Confirm_Button.setText(String.valueOf(totalsale));
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void actionPerformed(ActionEvent e) {
        int i;
        for(i=0;i<Selected_Sugar;i++)Silo.remove(Sugar.class);
        for(i=0;i<Selected_Honeycomb;i++)Silo.remove(Honeycomb.class);
        for(i=0;i<Selected_String;i++)Silo.remove(Vstring.class);
        for(i=0;i<Selected_Egg;i++)Silo.remove(Egg.class);
        for(i=0;i<Selected_Candy;i++)Silo.remove(Candy.class);
        for(i=0;i<Selected_Honey;i++)Silo.remove(Honey.class);
        for(i=0;i<Selected_Violin;i++)Silo.remove(Violin.class);
        for(i=0;i<Selected_Mayonnaise;i++)Silo.remove(Mayonnaise.class);
     
        Shop_Dialog.setVisible(false);
        state.getContentPane().setVisible(true);
        Process = new Thread() {
                public void run()
                {
                    time = (Selected_Sugar
                            +Selected_Honeycomb
                            +Selected_String
                            +Selected_Egg
                            +Selected_Candy
                            +Selected_Honey
                            +Selected_Violin
                            +Selected_Mayonnaise)*500;
                    state.saleProcess(time);
                    try { Thread.sleep(time);} 
                    catch (InterruptedException x) { x.printStackTrace(); }
                    state.gainMoney(totalsale);
                    System.out.println(totalsale);
                    time = 0;
                    totalsale = 0;
                    Shop_Dialog.dispose();
                }
            };
	Process.start();
    }
}

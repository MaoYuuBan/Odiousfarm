import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;         

public class State extends JFrame implements KeyListener,MouseListener{
    //status
    private Timer Timer = null;     
    private int time_sec = 0;           
    private int time_min = 0;
    private int Money;
    private int Level;
    private int MaxFood;
    private int Cur_Food;
    private int FoodPrice;
    private int Ant_Price            = 100;
    private int Bee_Price            = 800;
    private int Grasshopper_Price    = 5400;
    private int Cockroach_Price      = 35000;
    private int CandyMaker_Price     = 300;
    private int HoneyMaker_Price     = 2160;
    private int ViolinMaker_Price    = 14700;
    private int MayonnaiseMaker_Price= 105000;
    private int ODrinkMaker_Price    = 500000;
    private Thread Process;
    private Silo Silo;
    private Shop Shop;
    private ArrayList<Food> FOOD = new ArrayList<Food>();
    private ArrayList<Ant> ANT = new ArrayList<Ant>();
    private ArrayList<Bee> BEE = new ArrayList<Bee>();
    private ArrayList<Grasshopper> GRASSHOPPER = new ArrayList<Grasshopper>();
    private ArrayList<Cockroach> COCKROACH = new ArrayList<Cockroach>();
    //components   
    private JLabel                  gamepane;
    private JPanel                  buy_Insect_Panel;
    private JProgressBar            Sale_ProgressBar;
    private JLabel                  Time_Label;
    private JLabel                  Money_Label;
    private JLabel                  buy_Food_Label;
    private JButton                 buy_Ant_Button;
    private JButton                 buy_Bee_Button;
    private JButton                 buy_Grasshopper_Button;
    private JButton                 buy_Cockroach_Button;   
    private JButton                 buy_CandyMaker_Button;
    private JButton                 buy_HoneyMaker_Button;
    private JButton                 buy_ViolinMaker_Button;
    private JButton                 buy_Mayonnaise_Button;
    private JButton                 buy_ODrinkMaker_Button;
    //pictures
    private MyImageIcon Map_Image;
    private MyImageIcon Money_Image;
    private MyImageIcon Time_Image;
    private ArrayList<MyImageIcon> Silo_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Shop_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> FoodBox_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Food_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Ant_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Bee_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Grasshopper_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> Cockroach_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> CandyMaker_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> HoneyMaker_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> ViolinMaker_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> MayonnaiseMaker_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> ODrinkMaker_Image = new ArrayList<MyImageIcon>();
    private ArrayList<MyImageIcon> buy_Button_Image = new ArrayList<MyImageIcon>();
    //sounds
    private ArrayList<MySoundEffect> insect_Sound = new ArrayList<MySoundEffect>();
    private ArrayList<MySoundEffect> maker_Sound = new ArrayList<MySoundEffect>();
    private MySoundEffect           buy_Sound;
    private MySoundEffect           ant_Sound;
    private MySoundEffect           bee_Sound ;      
    private MySoundEffect           grasshopper_Sound;
    private MySoundEffect           cockroach_Sound;    
    private MySoundEffect           alert_Sound;
    private MySoundEffect           feed_Sound;
    private MySoundEffect           game_BGM;
    private MySoundEffect           win_Sound;
    private MySoundEffect           shop_Sound;
    private MySoundEffect           sell_Sound;
    //components data
    private int ButtonWidth     = 50  , ButtonHight    = 50;//*Change Size
    private int FrameWidth      = 1366, FrameHeight    = 768;
    //linked data
    private Player Player;
    //cheat
    private String cheat = "";
    
    public State(Player p){ 
        setTitle("Odious Farm");
        setBounds(0, 0, FrameWidth, FrameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE);
        addKeyListener(this);
        addWindowListener( new WindowAdapter () {
            public void windowClosing( WindowEvent e ){
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null, 
                                    "Are you sure you want to exit?", "Exit", 
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                                    ObjButtons,ObjButtons[1]);
                if(PromptResult==0)
                {
                    System.exit(0);          
                }
            }	
        });
               
        loadImage();
        loadFXSound();
        
        setContentPane(gamepane = new JLabel());
        gamepane.setIcon(Map_Image);
        gamepane.setLayout(null);
        game_BGM.playLoop();
        Player = p; 
        Level = p.getLevel();
        setDefault();
        addLabels();
        addbuyButtons();        
        addTimer();
        
        gamepane.validate();
        gamepane.repaint();
    }
    
    public void setDefault(){
        if(Level==0){//Elite
            Money = 100;
            MaxFood = 5;
            Cur_Food = MaxFood;
            FoodPrice = 60;
            Silo = new Silo(100,Silo_Image);
        }
        else if(Level==1){//Hard
            Money = 200;
            MaxFood = 7;
            Cur_Food = MaxFood;
            FoodPrice = 50;
            Silo = new Silo(150,Silo_Image);
        }
        else if(Level==2){//Normal
            Money = 200;
            MaxFood = 10;
            Cur_Food = MaxFood;
            FoodPrice = 40;
            Silo = new Silo(200,Silo_Image);
        }
        else if(Level==3){//Easy
            Money = 500;
            MaxFood = 15;
            Cur_Food = MaxFood;
            FoodPrice = 30;
            Silo = new Silo(300,Silo_Image);
        }
        else if(Level==4){//Noob
            Money = 1000;
            MaxFood = 20;
            Cur_Food = MaxFood;
            FoodPrice = 20;
            Silo = new Silo(400,Silo_Image);
        }
        gamepane.add(Silo);
    }
    public void loadImage(){
        Map_Image = new MyImageIcon("pictures/map.png").resize(FrameWidth,FrameHeight);
        Money_Image = new MyImageIcon("pictures/money.png");
        Time_Image = new MyImageIcon("pictures/time.png");
        
        int i;
        for(i=0;i<13;i++){
            if(i<3){
                Food_Image.add(new MyImageIcon("pictures/food"+i+".png").resize(50, 50));
                CandyMaker_Image.add(new MyImageIcon("pictures/candymaker"+i+".png").resize(150, 150));
                HoneyMaker_Image.add(new MyImageIcon("pictures/honeymaker"+i+".png").resize(150, 150));
                ViolinMaker_Image.add(new MyImageIcon("pictures/violinmaker"+i+".png").resize(150, 150));
                MayonnaiseMaker_Image.add(new MyImageIcon("pictures/mayonnaisemaker"+i+".png").resize(150, 150));
                ODrinkMaker_Image.add(new MyImageIcon("pictures/odrinkmaker"+i+".png").resize(150, 150));
            }
            if(i<4){
                FoodBox_Image.add(new MyImageIcon("pictures/foodbox"+i+".png").resize(150, 150));
                Silo_Image.add(new MyImageIcon("pictures/silo"+i+".png").resize(200, 200));
            }
            if(i<9)buy_Button_Image.add(new MyImageIcon("pictures/icon"+i+".png").resize(ButtonWidth, ButtonHight));
            Ant_Image.add(new MyImageIcon("pictures/ant"+i+".png").resize(100, 100));
            Bee_Image.add(new MyImageIcon("pictures/bee"+i+".png").resize(100, 100));           
            Grasshopper_Image.add(new MyImageIcon("pictures/grasshopper"+i+".png").resize(100, 100));
            Cockroach_Image.add(new MyImageIcon("pictures/cockroach"+i+".png").resize(100, 100));
        }
        Ant_Image.add(new MyImageIcon("pictures/sugar.png").resize(50, 50));
        Bee_Image.add(new MyImageIcon("pictures/honeycomb.png").resize(50, 50));
        Grasshopper_Image.add(new MyImageIcon("pictures/string.png").resize(50, 50));
        Cockroach_Image.add(new MyImageIcon("pictures/egg.png").resize(50, 50));
        buy_Button_Image.add(new MyImageIcon("pictures/quiz.png").resize(50, 50));
        
        Shop_Image.add(new MyImageIcon("pictures/cashier.png").resize(200,200));
        Shop_Image.add(Ant_Image.get(13));
        Shop_Image.add(Bee_Image.get(13));
        Shop_Image.add(Grasshopper_Image.get(13));
        Shop_Image.add(Cockroach_Image.get(13));
        Shop_Image.add(buy_Button_Image.get(4));
        Shop_Image.add(buy_Button_Image.get(5));
        Shop_Image.add(buy_Button_Image.get(6));
        Shop_Image.add(buy_Button_Image.get(7));
        Shop_Image.add(new MyImageIcon("pictures/menuclear.png").resize(1000, 600));
        Shop_Image.add(new MyImageIcon("pictures/coin.png").resize(50, 50));
        
    }
    public void loadFXSound(){
        ant_Sound = new MySoundEffect("resourses/ant.wav");
        bee_Sound = new MySoundEffect("resourses/bee.wav");      
        grasshopper_Sound = new MySoundEffect("resourses/grasshopper.wav");
        cockroach_Sound = new MySoundEffect("resourses/cockroach.wav");
        alert_Sound = new MySoundEffect("resourses/fail_click.wav");
        insect_Sound.add(alert_Sound);
        insect_Sound.add(new MySoundEffect("resourses/item_pickup.wav"));
        insect_Sound.add(new MySoundEffect("resourses/eat.wav"));
        insect_Sound.add(new MySoundEffect("resourses/death.wav"));
        insect_Sound.add(new MySoundEffect("resourses/item_drop.wav"));
        
        //makersound
        maker_Sound.add(alert_Sound);
        maker_Sound.add(insect_Sound.get(1));
        maker_Sound.add(new MySoundEffect("resourses/candy_process.wav"));
        maker_Sound.add(new MySoundEffect("resourses/honey_process.wav"));
        maker_Sound.add(new MySoundEffect("resourses/violin_process.wav"));
        maker_Sound.add(new MySoundEffect("resourses/mayongnese_process.wav"));
        maker_Sound.add(new MySoundEffect("resourses/odrink_process.wav"));
        feed_Sound = new MySoundEffect("resourses/feed.wav");
        buy_Sound = new MySoundEffect("resourses/buy_process.wav");
        game_BGM = new MySoundEffect("resourses/play_bgm.wav");
        win_Sound = new MySoundEffect("resourses/win.wav");
        shop_Sound = new MySoundEffect("resourses/shop.wav");
        sell_Sound = new MySoundEffect("resourses/sell.wav");
                            
    }
    public void addLabels(){
        //Money Label
        Money_Label =  new JLabel();
        Money_Label.setBounds(1090, 5, 260, 60);
        Money_Label.setIcon(Money_Image);
        Money_Label.setText(Integer.toString(Money));
        Money_Label.setFont(new Font("Handy",Font.BOLD,16));
        Money_Label.setHorizontalTextPosition(JLabel.CENTER);
        Money_Label.setVerticalTextPosition(JLabel.CENTER);
        gamepane.add(Money_Label);
        //Timer Label
        Time_Label =  new JLabel();
        Time_Label.setBounds(1240, 660, 100, 60);
        Time_Label.setIcon(Time_Image);
        Time_Label.setFont(new Font("Handy",Font.BOLD,16));
        Time_Label.setHorizontalTextPosition(JLabel.CENTER);
        Time_Label.setVerticalTextPosition(JLabel.CENTER);
        gamepane.add(Time_Label);
        //Shop
        Shop = new Shop(this,Silo,Shop_Image,shop_Sound);
        gamepane.add(Shop);
        //ProcessBar
        Sale_ProgressBar = new JProgressBar();
        Sale_ProgressBar.setBounds(50, 650, 300, 25);
        Sale_ProgressBar.setStringPainted(true);
        Sale_ProgressBar.setValue(0);
        gamepane.add(Sale_ProgressBar);
        //buy Food Label
        buy_Food_Label = new JLabel();
        buy_Food_Label.setBounds(900, 0, 200, 200);
        buy_Food_Label.setIcon(FoodBox_Image.get(0));
        buy_Food_Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Food_Label.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
            if(Money>=FoodPrice&&Cur_Food!=MaxFood){
                Cur_Food = MaxFood;
                buy_Food_Label.setIcon(FoodBox_Image.get(0));
                lostMoney(FoodPrice);
                buy_Sound.playOnce();
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
            System.out.printf("Food         : %d\n",FOOD.size());
            System.out.printf("Ant          : %d\n",ANT.size());
            System.out.printf("Bee          : %d\n",BEE.size()); 
            System.out.printf("Grasshopper  : %d\n",GRASSHOPPER.size());
            System.out.printf("Cockroach    : %d\n",COCKROACH.size());    
            System.out.printf("Sugar     : %d\n",Silo.getNum_Sugar());
            System.out.printf("Honeycomb : %d\n",Silo.getNum_Honeycomb());
            System.out.printf("String    : %d\n",Silo.getNum_String());
            System.out.printf("Egg       : %d\n",Silo.getNum_Egg());
            System.out.printf("Candy     : %d\n",Silo.getNum_Candy());
            System.out.printf("Honey     : %d\n",Silo.getNum_Honey());           
            System.out.printf("Violin    : %d\n",Silo.getNum_Violin());           
            System.out.printf("Mayonnaise: %d\n",Silo.getNum_Mayonnaise());
            }
        });
        gamepane.add(buy_Food_Label);
    }   
    public void addbuyButtons(){
        buy_Insect_Panel = new JPanel();
        buy_Insect_Panel.setBounds(0,0,1000,ButtonHight+30);
        buy_Insect_Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buy_Insect_Panel.setOpaque(false);
        
        //buy Ant Button
        buy_Ant_Button = new JButton();
        buy_Ant_Button.setSize(ButtonWidth, ButtonHight);
        buy_Ant_Button.setIcon(buy_Button_Image.get(0));
        buy_Ant_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Ant_Button.setFocusable(false);
        buy_Ant_Button.setText(String.valueOf(Ant_Price));
        buy_Ant_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_Ant_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_Ant_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_Ant_Button.addActionListener(e ->{addAnt();});
        buy_Insect_Panel.add(buy_Ant_Button); 
        
        //buy Bee Button
        buy_Bee_Button = new JButton();
        buy_Bee_Button.setSize(ButtonWidth, ButtonHight);
        buy_Bee_Button.setIcon(buy_Button_Image.get(1));
        buy_Bee_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Bee_Button.setFocusable(false);
        buy_Bee_Button.setText(String.valueOf(Bee_Price));
        buy_Bee_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_Bee_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_Bee_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_Bee_Button.addActionListener(e ->{addBee();});
        buy_Insect_Panel.add(buy_Bee_Button); 
        
        //buy Grasshopper Button
        buy_Grasshopper_Button = new JButton();
        buy_Grasshopper_Button.setSize(ButtonWidth, ButtonHight);
        buy_Grasshopper_Button.setIcon(buy_Button_Image.get(2));
        buy_Grasshopper_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Grasshopper_Button.setFocusable(false);
        buy_Grasshopper_Button.setText(String.valueOf(Grasshopper_Price));
        buy_Grasshopper_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_Grasshopper_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_Grasshopper_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_Grasshopper_Button.addActionListener(e ->{addGrasshopper();});
        buy_Insect_Panel.add(buy_Grasshopper_Button); 
        
        //buy Cockroach Button
        buy_Cockroach_Button = new JButton();
        buy_Cockroach_Button.setSize(ButtonWidth, ButtonHight);
        buy_Cockroach_Button.setIcon(buy_Button_Image.get(3));
        buy_Cockroach_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Cockroach_Button.setFocusable(false);
        buy_Cockroach_Button.setText(String.valueOf(Cockroach_Price));
        buy_Cockroach_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_Cockroach_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_Cockroach_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_Cockroach_Button.addActionListener(e ->{addCockroach();});
        buy_Insect_Panel.add(buy_Cockroach_Button); 
              
        //buy CandyMaker Button
        buy_CandyMaker_Button = new JButton();
        buy_CandyMaker_Button.setSize(ButtonWidth, ButtonHight);
        buy_CandyMaker_Button.setIcon(buy_Button_Image.get(4));
        buy_CandyMaker_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_CandyMaker_Button.setFocusable(false);
        buy_CandyMaker_Button.setText(String.valueOf(CandyMaker_Price));
        buy_CandyMaker_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_CandyMaker_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_CandyMaker_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_CandyMaker_Button.addActionListener(e ->{
        if(Money>=CandyMaker_Price){
                lostMoney(CandyMaker_Price);
                buy_Sound.playOnce();
                gamepane.add(new CandyMaker(gamepane,CandyMaker_Image,buy_Button_Image.get(4),Silo,maker_Sound));
                repaint();
                buy_CandyMaker_Button.setVisible(false);
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
        });
        buy_Insect_Panel.add(buy_CandyMaker_Button); 
        
        //buy HoneyMaker Button
        buy_HoneyMaker_Button = new JButton();
        buy_HoneyMaker_Button.setSize(ButtonWidth, ButtonHight);
        buy_HoneyMaker_Button.setIcon(buy_Button_Image.get(5));
        buy_HoneyMaker_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_HoneyMaker_Button.setFocusable(false);
        buy_HoneyMaker_Button.setText(String.valueOf(HoneyMaker_Price));
        buy_HoneyMaker_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_HoneyMaker_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_HoneyMaker_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_HoneyMaker_Button.addActionListener(e ->{
            if(Money>=HoneyMaker_Price){
                lostMoney(HoneyMaker_Price);
                buy_Sound.playOnce();
                gamepane.add(new HoneyMaker(gamepane,HoneyMaker_Image,buy_Button_Image.get(5),Silo,maker_Sound));
                repaint();
                buy_HoneyMaker_Button.setVisible(false);
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
        });
        buy_Insect_Panel.add(buy_HoneyMaker_Button);
        
        //buy ViolinMaker Button
        buy_ViolinMaker_Button = new JButton();
        buy_ViolinMaker_Button.setSize(ButtonWidth, ButtonHight);
        buy_ViolinMaker_Button.setIcon(buy_Button_Image.get(6));
        buy_ViolinMaker_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_ViolinMaker_Button.setFocusable(false);
        buy_ViolinMaker_Button.setText(String.valueOf(ViolinMaker_Price));
        buy_ViolinMaker_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_ViolinMaker_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_ViolinMaker_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_ViolinMaker_Button.addActionListener(e ->{
        if(Money>=ViolinMaker_Price){
                lostMoney(ViolinMaker_Price);
                buy_Sound.playOnce();
                gamepane.add(new ViolinMaker(gamepane,ViolinMaker_Image,buy_Button_Image.get(6),Silo,maker_Sound));
                repaint();
                buy_ViolinMaker_Button.setVisible(false);
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
        });
        buy_Insect_Panel.add(buy_ViolinMaker_Button);
        
        //buy MayonnaiseMaker Button
        buy_Mayonnaise_Button = new JButton();
        buy_Mayonnaise_Button.setSize(ButtonWidth, ButtonHight);
        buy_Mayonnaise_Button.setIcon(buy_Button_Image.get(7));
        buy_Mayonnaise_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_Mayonnaise_Button.setFocusable(false);
        buy_Mayonnaise_Button.setText(String.valueOf(MayonnaiseMaker_Price));
        buy_Mayonnaise_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_Mayonnaise_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_Mayonnaise_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_Mayonnaise_Button.addActionListener(e ->{
        if(Money>=MayonnaiseMaker_Price){
                lostMoney(MayonnaiseMaker_Price);
                buy_Sound.playOnce();
                gamepane.add(new MayonnaiseMaker(gamepane,MayonnaiseMaker_Image,buy_Button_Image.get(7),Silo,maker_Sound));
                repaint();
                buy_Mayonnaise_Button.setVisible(false);
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
        });
        buy_Insect_Panel.add(buy_Mayonnaise_Button);
        
        //buy ODrinkMaker Button
        buy_ODrinkMaker_Button = new JButton();
        buy_ODrinkMaker_Button.setSize(ButtonWidth, ButtonHight);
        buy_ODrinkMaker_Button.setIcon(buy_Button_Image.get(9));
        buy_ODrinkMaker_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buy_ODrinkMaker_Button.setFocusable(false);
        buy_ODrinkMaker_Button.setText(String.valueOf(ODrinkMaker_Price));
        buy_ODrinkMaker_Button.setFont(new Font("Handy",Font.BOLD,16));
        buy_ODrinkMaker_Button.setHorizontalTextPosition(JLabel.CENTER);
        buy_ODrinkMaker_Button.setVerticalTextPosition(JLabel.BOTTOM);
        buy_ODrinkMaker_Button.addActionListener(e ->{
        if(Money>=ODrinkMaker_Price){
                lostMoney(ODrinkMaker_Price);
                buy_Sound.playOnce();
                gamepane.add(new ODrinkMaker(gamepane,ODrinkMaker_Image,buy_Button_Image.get(8),Silo,this,maker_Sound));
                repaint();
                buy_ODrinkMaker_Button.setVisible(false);
            }
            else{
                alert_Sound.playOnce();//sound
                System.out.println("Not Enough Money");
            }
        });
        buy_Insect_Panel.add(buy_ODrinkMaker_Button);
        
        
        //place food
        gamepane.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                int CurX = e.getX(), CurY = e.getY();
                //run out of food
                if(Cur_Food <= 0){
                    alert_Sound.playOnce();//warning sound             
                    System.out.println("No more food");
                }
                //check can't put food out of Map
                else if((200 > CurX) || (CurX > 1166) || (150 > CurY) || (CurY > 550)){
                    alert_Sound.playOnce();
                    System.out.println("X" + CurX + " Y" + CurY);
                    System.out.println("Can't put food here");
                }
                //put the food in area
                else{
                    feed_Sound.playOnce();//sound
                    Food f = new Food(CurX,CurY,gamepane,Food_Image,FOOD);
                    FOOD.add(f);
                    Cur_Food--;
                    if(Cur_Food==MaxFood/2)buy_Food_Label.setIcon(FoodBox_Image.get(1));
                    if(Cur_Food==MaxFood/4)buy_Food_Label.setIcon(FoodBox_Image.get(2));
                    if(Cur_Food==0)buy_Food_Label.setIcon(FoodBox_Image.get(3));
                    System.out.println("Food+"+FOOD.size());
                }
            }
        });
        gamepane.add(buy_Insect_Panel);
    }
    public void addTimer() {        
        Timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time_sec++;
                if(time_sec>=60){
                    time_min++;
                    time_sec = 0;
                }
                if (true){
                    if(time_sec<10&&time_min<10)Time_Label.setText("0"+Integer.toString(time_min)+":0"+Integer.toString(time_sec));
                    else if(time_min<10)Time_Label.setText("0"+Integer.toString(time_min)+":"+Integer.toString(time_sec));
                    else if(time_sec<10)Time_Label.setText(Integer.toString(time_min)+":0"+Integer.toString(time_sec));
                    else Time_Label.setText(Integer.toString(time_min)+":"+Integer.toString(time_sec));
                }
            }
        });
        Timer.start();        
    }
    public void lostMoney(int p){
        Money = Money - p;
        Money_Label.setText(Integer.toString(Money));
    }
    public void gainMoney(int p){
        Money = Money + p;
        Money_Label.setText(Integer.toString(Money));
    }
    public void saleProcess(int t){       
        Process = new Thread(){
            public void run(){
                Shop.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                Shop.removeMouseListener(Shop);
                sell_Sound.playOnce();
                for (int i = 0; i <= 100; i++) {
                    try { Thread.sleep(t/100); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                    Sale_ProgressBar.setValue(i);
                }
                Sale_ProgressBar.setValue(0);
                Shop.addMouseListener(Shop);
                Shop.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };
        Process.start();
    }
    public void addAnt(){
        if(Money>=Ant_Price){
            lostMoney(Ant_Price);
            ANT.add(new Ant(gamepane,FOOD,ANT,Ant_Image,Silo,insect_Sound));
            ant_Sound.playOnce();//sound488
        }
        else{
            alert_Sound.playOnce();//warning sound
        }
    }
    public void addBee(){
        if(Money>=Bee_Price){
            lostMoney(Bee_Price);
            BEE.add(new Bee(gamepane,FOOD,BEE,Bee_Image,Silo,insect_Sound));
            bee_Sound.playOnce();//sound498
        }
        else{
            alert_Sound.playOnce();//warning sound
        }
    }  
    public void addGrasshopper(){
        if(Money>=Grasshopper_Price){
            lostMoney(Grasshopper_Price);
            GRASSHOPPER.add(new Grasshopper(gamepane,FOOD,GRASSHOPPER,Grasshopper_Image,Silo,insect_Sound));
            grasshopper_Sound.playOnce();//sound508
        }
        else{
            alert_Sound.playOnce();//warning sound
        }
    }
    public void addCockroach(){
        if(Money>=Cockroach_Price){
            lostMoney(Cockroach_Price);
            COCKROACH.add(new Cockroach(gamepane,FOOD,COCKROACH,Cockroach_Image,Silo,insect_Sound));
            cockroach_Sound.playOnce();//sound518
        }
        else{
            alert_Sound.playOnce();//warning sound
        }
    }
    public int getTimeMin(){
        return time_min;
    }
    public int getTimeSec(){
        return time_sec;
    }
    //mouse listener
    public void mouseReleased(MouseEvent e) {
        Player.setTime(this.getTimeMin(), this.getTimeSec());
        Player.save();
        game_BGM.stop();
        win_Sound.playOnce();
        JOptionPane.showMessageDialog(new JFrame(),"!!! You win !!! \nTime Finished "+Player.getScore(),"VICTORY" ,JOptionPane.UNDEFINED_CONDITION );
        System.exit(0);
    }
    public void mouseClicked(MouseEvent e)  {}
    public void mousePressed(MouseEvent e)  {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    
    //cheating code key listener	
    public void keyTyped( KeyEvent e ){
	cheat = cheat + e.getKeyChar();
        System.out.println(cheat);
        if(cheat.equalsIgnoreCase("I'mSultan")){
            Money = 99999999;
            Money_Label.setText(Integer.toString(Money));
            cheat = "";
        }
        if(cheat.equalsIgnoreCase("MagnateCP")){
            MaxFood = 99999999;
            Cur_Food = MaxFood;
            cheat = "";
        }
    }
    public void keyPressed( KeyEvent e ){
        if(e.getKeyCode()== VK_SHIFT ){
            cheat = "";
            System.out.println(cheat);
        }
    }
    public void keyReleased( KeyEvent e ){}
}

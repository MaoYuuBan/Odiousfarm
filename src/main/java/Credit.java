import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Credit extends JFrame{
    private boolean             aviable = true;
    private JLabel              contentpane;
    private JButton             Back_Button;
    private Thread              AnimationThread;
    private Thread              Background_Sound;
    
    private ArrayList<MyImageIcon> Credit_IMG = new ArrayList<MyImageIcon>();
    private MyImageIcon         Back_IMG;
    
    private ArrayList<MySoundEffect> Credit_Sound = new ArrayList<MySoundEffect>();
    private MySoundEffect       Click_Sound;
    
    private int FrameWidth      = 1366 , FrameHeight    = 768;
    private int ButtonWidth     = 200  , ButtonHight    = 50;//*Change Size
            
    public Credit(){
        setTitle("Credit");
        setBounds(0, 0, FrameWidth, FrameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );      
        load();
        Background_Sound();
        Back_IMG = new MyImageIcon("pictures/button_back.png").resize(ButtonWidth, ButtonHight);        
        Click_Sound= new MySoundEffect("resourses/click.wav");
        
        setContentPane(contentpane = new JLabel());
        
	Animation();
	contentpane.setLayout( null );
        
        Back_Button = new JButton();
        Back_Button.setBounds(FrameWidth/2-ButtonWidth/2, FrameHeight/2+225, ButtonWidth, ButtonHight);//*Change Position
        Back_Button.setIcon(Back_IMG);
        Back_Button.setFocusable(false);
        contentpane.add(Back_Button);
        Back_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
               Click_Sound.playOnce();
               for(int i=0;i<3;i++)Credit_Sound.get(i).stop();
               aviable=false;
               dispose();
            }
        });
        addWindowListener( new WindowAdapter () {
            public void windowClosing( WindowEvent e ){
                for(int i=0;i<3;i++)Credit_Sound.get(i).stop();
                aviable=false;
            }	
        });
    }
    public boolean getaviable(boolean t){return t;}
    public void load(){
        for(int i=0;i<4;i++)
            Credit_IMG.add(new MyImageIcon("pictures/credit_"+i+".png").resize(FrameWidth, FrameHeight));
            Credit_IMG.add(new MyImageIcon("pictures/credit_all.png").resize(FrameWidth, FrameHeight));
        for(int i=0;i<3;i++)
            Credit_Sound.add(new MySoundEffect("resourses/credit_sound"+i+".wav"));
    }
    public void Background_Sound(){
        Background_Sound = new Thread(){
            public void run(){
                {
                    Credit_Sound.get(0).playOnce();
                    try { Background_Sound.sleep(23237); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                    if(getaviable(aviable))Credit_Sound.get(1).playOnce();
                    try { Background_Sound.sleep(20053); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
                    if(getaviable(aviable))Credit_Sound.get(2).playOnce();
                    try { Background_Sound.sleep(24362); } 
                    catch (InterruptedException e) { e.printStackTrace(); } 
                }
            }          
        };
        Background_Sound.start();
    }
    
    public void Animation(){
        AnimationThread = new Thread(){
            public void run(){
                for(int i=0;i<4;i++){
                    contentpane.setIcon(Credit_IMG.get(i));
                    try { Thread.sleep(6000); } 
                    catch (InterruptedException e) { e.printStackTrace(); } 
            }
                    contentpane.setIcon(Credit_IMG.get(4));
            }
        };
        AnimationThread.start();
    }
}

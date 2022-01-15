import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class HowToPlay extends JFrame{
    private JLabel              contentpane;
    private JButton             Back_Button;
    private JButton             Next_Button;
    
    private MyImageIcon         BackGround_IMG;
    private MyImageIcon         Back_IMG;
    private MyImageIcon         Next_IMG;
    private MySoundEffect       Click_Sound;
    private MySoundEffect       BackGround_Sound;
    private ArrayList<MyImageIcon> Background = new ArrayList<MyImageIcon>();
    
    private int FrameWidth      = 1366 , FrameHeight    = 768;
    private int ButtonWidth     = 200  , ButtonHight    = 50; 
    
    public HowToPlay(){
        setTitle("How to Play");
        setBounds(0, 0, FrameWidth, FrameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        
        Next_IMG = new MyImageIcon("pictures/button_next.png").resize(ButtonWidth, ButtonHight);
        Back_IMG = new MyImageIcon("pictures/button_back.png").resize(ButtonWidth, ButtonHight);
        Background.add(new MyImageIcon("pictures/howtoplay0.png").resize(FrameWidth, FrameHeight));
        Background.add(new MyImageIcon("pictures/howtoplay1.png").resize(FrameWidth, FrameHeight));
        
        BackGround_Sound = new MySoundEffect("resourses/start_bgm.wav");        
        Click_Sound= new MySoundEffect("resourses/click.wav");
        
        addWindowFocusListener(new WindowAdapter(){
            public void windowGainedFocus(WindowEvent e){
                BackGround_Sound.playLoop();
            }
            public void windowLostFocus(WindowEvent e){
                BackGround_Sound.stop();
            }
        });
        
        setContentPane(contentpane = new JLabel());
	contentpane.setIcon(Background.get(0));
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
               CloseFrame();
            }
        });
        Next_Button = new JButton();
        Next_Button.setBounds(FrameWidth/2-ButtonWidth/2+225, FrameHeight/2+225, ButtonWidth, ButtonHight);//*Change Position
        Next_Button.setIcon(Next_IMG);
        Next_Button.setFocusable(false);
        contentpane.add(Next_Button);
        Next_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
               Click_Sound.playOnce();
               contentpane.setIcon(Background.get(1));
               Next_Button.setVisible(false);
            }
        });
    }
    public void CloseFrame(){
        this.dispose();
    }
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainApplication extends JFrame{
    //components
    private JLabel              contentpane;
    private JButton             StartGame_Button;
    private JButton             HowToPlay_Button;
    private JButton             Credit_Button;
    private JButton             Quit_Button;
    //pictures and sounds
    private MyImageIcon         BackGround_Image;
    private MyImageIcon         StartGame_Image;
    private MyImageIcon         HowToPlay_Image;
    private MyImageIcon         Credit_Image;
    private MyImageIcon         Quit_Image;
    private MySoundEffect       BackGround_Sound;
    private MySoundEffect       Click_Sound;
    //components data
    private int MainFrameWidth = 1366 , MainFrameHeight = 768;
    private int ButtonWidth    = 200  , ButtonHight     = 50;//*Change Size
    //next frames
    private Game game;
    private HowToPlay htp;
    private Credit cd;
    
    
    public static void main(String[] args) {
        new MainApplication();
    }
    public MainApplication(){
        //MainFrame
        setTitle("OdiousFarm");
        setBounds(0, 0, MainFrameWidth, MainFrameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
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
        addWindowFocusListener(new WindowAdapter(){
            public void windowGainedFocus(WindowEvent e){
                BackGround_Sound.playLoop();
            }
            public void windowLostFocus(WindowEvent e){
                BackGround_Sound.stop();
            }
        });

        
        addComponents();      
    }
    public void addComponents(){
        //pictures
        BackGround_Image = new MyImageIcon("pictures/menu.png").resize(MainFrameWidth, MainFrameHeight);
        StartGame_Image = new MyImageIcon("pictures/button_start.png").resize(ButtonWidth, ButtonHight);
        HowToPlay_Image = new MyImageIcon("pictures/button_howtoplay.png").resize(ButtonWidth, ButtonHight);
        Credit_Image = new MyImageIcon("pictures/button_credit.png").resize(ButtonWidth, ButtonHight);
        Quit_Image = new MyImageIcon("pictures/button_quit.png").resize(ButtonWidth, ButtonHight);
        
        //sounds
        BackGround_Sound = new MySoundEffect("resourses/start_bgm.wav");
        Click_Sound= new MySoundEffect("resourses/click.wav");
        BackGround_Sound.playLoop();

        setContentPane(contentpane = new JLabel());
	contentpane.setIcon(BackGround_Image);
	contentpane.setLayout( null );
        
        //StartGame Button
        StartGame_Button = new JButton();
        StartGame_Button.setBounds(MainFrameWidth/2-ButtonWidth/2, MainFrameHeight/2, ButtonWidth, ButtonHight);//*Change Position
        StartGame_Button.setIcon(StartGame_Image);
        StartGame_Button.setFocusable(false);
        contentpane.add(StartGame_Button);
        StartGame_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {     
                Click_Sound.playOnce();
                BackGround_Sound.stop();
                if (game == null) game = new Game();
		else {
                    game.dispose();
                    game = new Game();
                }
            }
	});
        
        //HowToPlay Button
        HowToPlay_Button = new JButton();
        HowToPlay_Button.setBounds(MainFrameWidth/2-ButtonWidth/2, MainFrameHeight/2+75, ButtonWidth, ButtonHight);//*Change Position
        HowToPlay_Button.setIcon(HowToPlay_Image);
        HowToPlay_Button.setFocusable(true);
        contentpane.add(HowToPlay_Button);
        HowToPlay_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                Click_Sound.playOnce();
                if (htp == null) htp = new HowToPlay();
		else {
                    htp.dispose();
                    htp = new HowToPlay();
                }
                
            }
        });
        //Credit Button
        Credit_Button = new JButton();
        Credit_Button.setBounds(MainFrameWidth/2-ButtonWidth/2, MainFrameHeight/2+150, ButtonWidth, ButtonHight);//*Change Position
        Credit_Button.setIcon(Credit_Image);
        Credit_Button.setFocusable(false);
        contentpane.add(Credit_Button);
        Credit_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                Click_Sound.playOnce();
                BackGround_Sound.stop();
                if (cd == null) cd = new Credit();
		else {
                    cd.dispose();
                    cd = new Credit();
                }
                
            }
        });
        //Quit Button
        Quit_Button = new JButton();
        Quit_Button.setBounds(MainFrameWidth/2-ButtonWidth/2, MainFrameHeight/2+225, ButtonWidth, ButtonHight);//*Change Position
        Quit_Button.setIcon(Quit_Image);
        Quit_Button.setFocusable(false);
        contentpane.add(Quit_Button);
        Quit_Button.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                Click_Sound.playOnce();
                BackGround_Sound.stop();
                System.exit(0);
            }
        });
        
                repaint();
    }
}
// Auxiliary class to resize image
class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	return new MyImageIcon(newimg);
    }
}
// Auxiliary class to play sound effect (support .wav or .mid file)
class MySoundEffect
{
    private java.applet.AudioClip audio;

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { audio.play(); }
    public void playLoop()   { audio.loop(); }
    public void stop()       { audio.stop(); }
}  


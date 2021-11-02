import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game extends JFrame {
    //file
    private String              filename = "save.txt";
    private DefaultListModel	playerlist; 
    //components
    private JLabel              contentpane;
    private JLabel              PlayerName_label;
    private JLabel              PlayerList_label;
    private JLabel              BestTime_label;
    private JButton             Back_Button;
    private JButton             Confirm_Button;
    private JTextArea           PlayerName_Text;
    private JList               Player_List;
    private JPanel              Level_Panel;
    private JRadioButton []     Level_RadioButton;
    private ButtonGroup         Level_Group;
    private String []           Level_String = {"Elite","Hard","Normal","Easy","Noob"};
    private int                 Level = 4;
    //pictures
    private MyImageIcon         BackGround_Image;
    private MyImageIcon         Play_Image;
    private MyImageIcon         Back_IMG;
    private MyImageIcon         PlayerName_IMG;
    private MyImageIcon         PlayerList_IMG;
    private MyImageIcon         BestTime_IMG;
    //sounds
    private MySoundEffect       Click_Sound;
    private MySoundEffect       FailClick_Sound;
    //components data
    private int FrameWidth      = 1366 , FrameHeight    = 768;
    private int ButtonWidth     = 200  , ButtonHight    = 50;//*Change Size
    private int PanelBoxWidth   = 500  , PanelBoxHight  = 50;//*Change Size
    //next frame
    private State state;
    
    public Game(){
        setTitle("Game");
        setBounds(0, 0, FrameWidth, FrameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE);
        
        loadImage();
        loadSound();
        loadFile();
        
        setContentPane(contentpane = new JLabel());
	contentpane.setIcon(BackGround_Image);
	contentpane.setLayout(null);
        
        addComponents();
    }
    public void loadImage(){
        BackGround_Image = new MyImageIcon("pictures/menuclear.png").resize(FrameWidth, FrameHeight);//***Change Picture
        Play_Image = new MyImageIcon("pictures/button_play.png").resize(ButtonWidth, ButtonHight);
        Back_IMG = new MyImageIcon("pictures/button_back.png").resize(ButtonWidth, ButtonHight);
        PlayerName_IMG  = new MyImageIcon("pictures/frame_name.png").resize(ButtonWidth, ButtonHight);
        PlayerList_IMG  = new MyImageIcon("pictures/frame_list.png").resize(260, 300);
        BestTime_IMG  = new MyImageIcon("pictures/button_time.png").resize(260, ButtonHight);
    }
    public void loadSound(){
        Click_Sound= new MySoundEffect("resourses/click.wav");
        FailClick_Sound= new MySoundEffect("resourses/fail_click.wav");
    }
    public void loadFile(){
        boolean opensuccess = false;
        playerlist = new DefaultListModel();
        while (!opensuccess) {
            try ( Scanner fileScan = new Scanner(new File(filename));) {
                opensuccess = true;
                while (fileScan.hasNext()) {
                    String[] buf = fileScan.nextLine().split(",");
                    String slevel = "";
                    if(Integer.parseInt(buf[1].trim())<10)buf[1]="0"+buf[1];
                    if(Integer.parseInt(buf[2].trim())<10)buf[2]="0"+buf[2];
                    if("0".equals(buf[3].trim()))slevel="Elite     :";
                    if("1".equals(buf[3].trim()))slevel="Hard    :";
                    if("2".equals(buf[3].trim()))slevel="Normal:";
                    if("3".equals(buf[3].trim()))slevel="Easy     :";
                    if("4".equals(buf[3].trim()))slevel="Noob    :";
                    String list = slevel+buf[1]+":"+buf[2]+" "+buf[0];
                    playerlist.addElement((Object)list);
                }
            } catch (FileNotFoundException e) {}
        }
    }
    public void addComponents(){
        //Name TextArea
        PlayerName_label =  new JLabel();
        PlayerName_label.setBounds(FrameWidth/2-ButtonWidth, FrameHeight/4, ButtonWidth, ButtonHight);
        PlayerName_label.setIcon(PlayerName_IMG);
        
        PlayerName_Text = new JTextArea();
        PlayerName_Text.setBounds(FrameWidth/2-ButtonWidth+5, FrameHeight/4+10, ButtonWidth-5, ButtonHight-10);
        PlayerName_Text.setText("Name");
        PlayerName_Text.setOpaque(false);
        PlayerName_Text.setFont(new Font("Handy",Font.BOLD,25));
        PlayerName_Text.addKeyListener(new KeyAdapter(){
            public void keyPressed( KeyEvent e ){
                if(e.getKeyCode()== VK_ENTER){e.consume();}
            }
            public void keyTyped(KeyEvent e) {
                if (PlayerName_Text.getText().length() >= 10 )e.consume();
                if(e.getKeyChar() == ' '){e.consume();}
            }
        });
        contentpane.add(PlayerName_Text);
        contentpane.add(PlayerName_label);
        
        //Player List 
        BestTime_label =  new JLabel();
        BestTime_label.setBounds(FrameWidth/2-260/2 ,FrameHeight/5-30, 260, 300);
        BestTime_label.setIcon(BestTime_IMG);
        
        PlayerList_label =  new JLabel();
        PlayerList_label.setBounds(FrameWidth/2 - 260/2 ,FrameHeight/3+45, 260, 300);
        PlayerList_label.setIcon(PlayerList_IMG);
        
        Player_List = new JList(playerlist);
        Player_List.setBounds(FrameWidth/2 - 250/2 +6,FrameHeight/3+5+45, 240, 300-10);
        Player_List.setOpaque(false);
        Player_List.setFont(new Font("Handy",Font.BOLD,16));
        Player_List.setVisibleRowCount(0);
        Player_List.setFocusable(false);
        contentpane.add(BestTime_label);
        contentpane.add(Player_List);
        contentpane.add(PlayerList_label);
        
        //Level RadioButtons
        Level_Panel = new JPanel();
        Level_Panel.setBounds(FrameWidth/2 - PanelBoxWidth/2, FrameHeight/6, PanelBoxWidth+10, PanelBoxHight);
        Level_Panel.setLayout(new FlowLayout());
        Level_Panel.setOpaque(false);
        Level_RadioButton = new JRadioButton[Level_String.length];
        Level_Group = new ButtonGroup();
        for (int i=0; i < Level_String.length; i++)
        {
            Level_RadioButton[i] = new JRadioButton( Level_String[i] );
            Level_Group.add(Level_RadioButton[i]);
            Level_Panel.add(Level_RadioButton[i]);
        }
        Level_RadioButton[4].setSelected(true);
        Level_RadioButton[0].addItemListener(e->{Level = 0;});
        Level_RadioButton[0].setFont(new Font("Handy",Font.BOLD,16));
        Level_RadioButton[0].setOpaque(false);
        Level_RadioButton[1].addItemListener(e->{Level = 1;});
        Level_RadioButton[1].setFont(new Font("Handy",Font.BOLD,16));
        Level_RadioButton[1].setOpaque(false);
        Level_RadioButton[2].addItemListener(e->{Level = 2;});
        Level_RadioButton[2].setFont(new Font("Handy",Font.BOLD,16));
        Level_RadioButton[2].setOpaque(false);
        Level_RadioButton[3].addItemListener(e->{Level = 3;});
        Level_RadioButton[3].setFont(new Font("Handy",Font.BOLD,16));
        Level_RadioButton[3].setOpaque(false);
        Level_RadioButton[4].addItemListener(e->{Level = 4;});
        Level_RadioButton[4].setFont(new Font("Handy",Font.BOLD,16));
        Level_RadioButton[4].setOpaque(false);
        contentpane.add(Level_Panel);
         
        //Confirm Button
        Confirm_Button = new JButton();
        Confirm_Button.setBounds(FrameWidth/2 +10, FrameHeight/4, ButtonWidth, ButtonHight);
        Confirm_Button.setIcon(Play_Image);
        Confirm_Button.setFocusable(false);
        Confirm_Button.addActionListener(e ->{            
            if(PlayerName_Text.getText().equalsIgnoreCase("")){
                FailClick_Sound.playOnce();
                JOptionPane.showMessageDialog(new JFrame(), "Please Enter Name", "Warning",JOptionPane.WARNING_MESSAGE );
            }
            else{
                Click_Sound.playOnce();
                Player P = new Player(PlayerName_Text.getText().trim(),Level);
                dispose();
                state = new State(P);
            }
        });
        contentpane.add(Confirm_Button);
        
        //Back Button
        Back_Button = new JButton();
        Back_Button.setBounds(FrameWidth/2-ButtonWidth/2, FrameHeight/2+225, ButtonWidth, ButtonHight);
        Back_Button.setIcon(Back_IMG);
        Back_Button.setFocusable(false);
        Back_Button.addActionListener(e ->{
            Click_Sound.playOnce();
            dispose();
        });
        contentpane.add(Back_Button);
                   
        validate();
        repaint();
    }
}
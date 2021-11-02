
import java.awt.Cursor;
import javax.swing.JLabel;

public class ODrink extends JLabel {
    private State state;
    
    public ODrink(int x,int y,MyImageIcon m,State s){
        super();
        state = s;
        setBounds(x, y, 50, 50);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setIcon(m);
        addMouseListener(state);
    }
}

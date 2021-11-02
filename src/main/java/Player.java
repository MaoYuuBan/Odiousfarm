import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player implements Comparable<Player>{
    private String Name;
    private int Level;
    private int Time_Min = 99;
    private int Time_Sec = 99;
    private String Time = "99 minutes 99 seconds";
    
    public Player(String n,int l){
        Name = n;
        Level = l;
    }
    public void setTime(int m,int s){
        Time_Min = m;
        Time_Sec = s;
        Time = Time_Min+" minutes "+Time_Sec+" seconds";
        System.out.println(Time);
    }
    public String getName(){return Name;}
    public int getLevel(){return Level;}
    public int getTimemin(){return Time_Min;}
    public int getTimesec(){return Time_Sec;}
    public String getScore(){return Time;}
    public void save(){
        boolean opensuccess = false;
        ArrayList<Player> P = new ArrayList<Player>();
        while (!opensuccess) {
            try ( Scanner fileScan = new Scanner(new File("save.txt"));) {
                opensuccess = true;
                while (fileScan.hasNext()) {
                    String [] buf = fileScan.nextLine().split(",");
                    Player p = new Player(buf[0].trim(),Integer.parseInt(buf[3].trim()));
                    p.setTime(Integer.parseInt(buf[1].trim()),Integer.parseInt(buf[2].trim()));
                    P.add(p);
                }
                P.add(this);
            } catch (FileNotFoundException e) {}
        }
        Collections.sort(P);
        try 
        {
            PrintWriter write = new PrintWriter("save.txt");
            for(int i=0;i<P.size();i++){	
                System.out.printf("%s,%d,%d,%d\r\n",P.get(i).getName(), P.get(i).getTimemin(), P.get(i).getTimesec(),P.get(i).getLevel());
                write.printf("%s,%d,%d,%d\r\n",P.get(i).getName(), P.get(i).getTimemin(), P.get(i).getTimesec(),P.get(i).getLevel());
            } 
            write.close();
	}
	catch(FileNotFoundException e) {}
    }
    public int compareTo(Player other) {
        if(this.Level<other.Level)return -1;
        else if(this.Level>other.Level)return 1;
        else if(this.Time_Min<other.Time_Min)return -1;
        else if (this.Time_Min>other.Time_Min)return 1;
        else if (this.Time_Sec<other.Time_Sec)return -1;
        else if (this.Time_Sec>other.Time_Sec)return 1;
        else return 0;
    }
}
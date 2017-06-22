import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Representation of Battle Royale
 * Created by Smruthi Gadenkanahalli on 4/30/2017.
 */
public class WoolieBattleRoyale {
    public static void main(String[] args) {
        BufferedReader br=null;
        FileReader fr=null;
        Troll troll;
        ArrayList<Woolie> woolList=new ArrayList<Woolie>();

        int arenas;
        try{
            fr=new FileReader(args[0]);
            br=new BufferedReader(fr);

            arenas=Integer.parseInt(br.readLine());
            SportsComplex sportsComplex=new SportsComplex(arenas);
            String curr=br.readLine();
            System.out.println("The contestants are : \n");
            while(curr!=null){
                String[] wl=curr.split(",");
                Woolie W=new Woolie(wl[0],Integer.parseInt(wl[1]),Integer.parseInt(wl[2]),Integer.parseInt(wl[3]),Integer.parseInt(wl[4]));
                woolList.add(W);

                System.out.println("\t"+W);
                curr=br.readLine();


            }
            troll=new Troll(woolList,sportsComplex);
            troll.beginBattleRoyale();




        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }
}

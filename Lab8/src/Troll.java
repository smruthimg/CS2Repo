import java.util.ArrayList;
import java.util.Random;

/**
 * Representation of Troll
 * Created by Smruthi Gadenkanahalli on 4/30/2017.
 */
public class Troll {
    public static int seed=1;
    ArrayList<Woolie> woolies;
    SportsComplex sportsComplex;

    /**
     * Constructor to initialize the instance variables
     * @param woolies
     * @param sportsComplex
     */
    public Troll(ArrayList<Woolie> woolies,SportsComplex sportsComplex){
        this.woolies=new ArrayList<Woolie>( woolies);
        this.sportsComplex=sportsComplex;
    }

    /**
     * Method to start the battle
     */
    public void beginBattleRoyale(){

        // winners=oneround(woolies)
        // ooneround()=
        int round=1;
        ArrayList<Woolie> winners=new ArrayList<Woolie>();
        winners=oneRound(woolies);
        System.out.println("Round one has ended \n");
        if(winners.size()==1){
            System.out.println("The winner is " + winners.get(0));
        }
        else {
            round++;
            winners=oneRound(winners);
            System.out.println("Round " + round+" has ended\n ");
        }
//        Random rand=new Random();
//        rand.setSeed(seed);
//        System.out.println("\n");
//        int arenaCount=0;
//        ArrayList<WoolieBattleThread> woolieThread=new ArrayList<WoolieBattleThread>();
//        while(!woolies.isEmpty()){
//
//            WoolieBattleThread b1=new WoolieBattleThread(woolies.remove(rand.nextInt(woolies.size())),woolies.remove(rand.nextInt(woolies.size())),this.sportsComplex);
//            System.out.println("Woolies: "+b1.getFighter1().getName()+ " and " + b1.getFighter2().getName() +" enter Arena line to battle");
//            woolieThread.add(b1);
//
//
//
//
//
//
//        }
//        while(!woolieThread.isEmpty()){
//
//
//            System.out.println();
//
//
//                WoolieBattleThread b1=woolieThread.remove(0);
//                b1.enterArena();
//                arenaCount++;
//                b1.start();
//                try {
//                    b1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if(b1.winner!=null){
////                    System.out.println(b1.winner);
//                    woolies.add(b1.winner);
//                }
//
//
//
//        }


    }

    /**
     * Method to run a battle
     * @param woolies
     * @return list of winners
     */
    public ArrayList<Woolie> oneRound(ArrayList<Woolie> woolies){
        ArrayList<Woolie> winners=new ArrayList<Woolie>();
        ArrayList<WoolieBattleThread> battles=new ArrayList<WoolieBattleThread>();

        Random rand=new Random();
        rand.setSeed(seed);

        while (woolies.size()>0){
            if(woolies.size()==1){
                winners.add(woolies.get(0));
            }
            WoolieBattleThread b1=new WoolieBattleThread(woolies.remove(rand.nextInt(woolies.size())),woolies.remove(rand.nextInt(woolies.size())),this.sportsComplex);
            System.out.println("Woolies: "+b1.getFighter1().getName()+ " and " + b1.getFighter2().getName() +" enter Arena line to battle");
            battles.add(b1);
            b1.start();
//            w1,w2;//random woolies
//            battle b= new battle(w1,w2,sportsComplex);
//            b.start();
//            battles.add(b);
        }
        //awit untill all batles are done. jooin
        for (WoolieBattleThread b: battles){
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //select winner
        for(WoolieBattleThread b:battles){
            Woolie winner=b.getWinner();
            winner.reset();
            winners.add(winner);

        }
        //odd number of woolies ,out woole to winners
        return winners;
    }

}


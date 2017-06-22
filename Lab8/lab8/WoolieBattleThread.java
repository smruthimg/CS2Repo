import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Representation of Battle
 * Created by Smruthi Gadenkanahalli on 4/27/2017.
 */

public class WoolieBattleThread extends Thread {
    Woolie winner;
    Woolie fighter1;
    Woolie fighter2;
    SportsComplex spCplx;

    /**
     * Constructor to initialize the instance variables
     * @param fighter1
     * @param fighter2
     * @param sportsComplex
     */
    public WoolieBattleThread(Woolie fighter1,Woolie fighter2,SportsComplex sportsComplex){
        this.fighter1=fighter1;
        this.fighter2=fighter2;
        this.spCplx=sportsComplex;

    }

    /**
     * Method to enter the arena
     */
    public  void enterArena(){

        spCplx.enterArena(this);



    }

    /**
     * method to exit the arena
     */
    public void exitArena(){
        spCplx.leaveArena();
//        notifyAll();

    }

    /**
     * Accessor to get fighter 1
     * @return Woolie 1
     */
    public Woolie getFighter1(){
        return fighter1;
    }

    /**
     * Method to get the fighter 2
     * @returnWoolie 2
     */
    public Woolie getFighter2(){
        return fighter2;
    }

    /**
     * Method to get Winner of the battle
     * @return
     */
    public Woolie getWinner(){
        return winner;
    }

    /**
     * Method to implement the battle between 2 woolies
     */
    public void run(){

        int t=0;
        System.out.println("Woolie: "+ fighter1.getName() + " and " + fighter2.getName()+ " enter Arena to battle");
            while(getFighter2().currentHP>0 && getFighter1().currentHP>0) {

                if(fighter1.isOK() && t%getFighter1().hitTime==0 && t!=0) {

                    if(!fighter2.isOK()){
                        winner=fighter1;
                        break;
                    }
                    int attack=getFighter1().getAttackAmount();
                    getFighter2().takeDamage(attack);
                    System.out.println(getFighter1().getName() + " does " + attack + " damage to " + getFighter2().getName());
                    System.out.println(getFighter2().getName() + " has " + getFighter2().getCurrentHP() + " left!");
//                    this.join(this.fighter1.getHitTime());
//                    t=t+getFighter1().hitTime;
                }
                if(fighter2.isOK() && t%getFighter2().hitTime==0 && t!=0) {

                    int attack = getFighter2().getAttackAmount();
                    getFighter1().takeDamage(attack);//maxHP = getFighter1().maxHP - getFighter2().getAttackAmount();
                    System.out.println(getFighter2().getName() + " does " + attack + " damage to " + getFighter1().getName());
                    System.out.println(getFighter1().getName() + "  has " + getFighter1().getCurrentHP() + " left!");
//                    this.join(this.fighter2.getHitTime());
//                    t=t+getFighter2().hitTime;
                }
                t=t+1;
//                this.join(this.fighter2.getHitTime());

            }
            if(fighter1.isOK()){
                winner=fighter1;
                System.out.println("WOOLIE: "+fighter1.getName()+" leaves arena victorious!");

            }
            else{
                winner=fighter2;
                System.out.println("WOOLIE: "+fighter2.getName()+" leaves arena victorious!");
            }
//            System.out.println("Exiting arena");
            this.exitArena();
            t=0;


    }


}

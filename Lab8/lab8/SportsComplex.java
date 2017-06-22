/**
 * Representation of the sport complex
 * Created by Smruthi Gadenkanahalli on 4/27/2017.
 */
public class SportsComplex {
    int maxInUse;
    int currentInUse;

    /**
     * Constructor to initialize the max arenas in use
     * @param maxInUse
     */
    public SportsComplex(int maxInUse){
        this.maxInUse=maxInUse;
    }

    /**
     * Mehtod to enter teh woolies into a battle arena
     * @param t
     */
    public synchronized void	enterArena(WoolieBattleThread t) {
//        if(maxInUse>currentInUse) {
//            System.out.println(t.getFighter1().getName() + " and " + t.getFighter2().getName() + " enter Arena to battle");
//            currentInUse++;
//
//
//        }
//        else{
//        System.out.println(currentInUse+ " current in use");
        while (currentInUse == maxInUse) {
            try {

                wait();
            }


         catch(InterruptedException e){
            e.printStackTrace();
        }
    }
        currentInUse++;


    }

    /**
     * Method to exit the arena
     */
    public synchronized void leaveArena(){
        currentInUse--;
        notifyAll();

    }
}

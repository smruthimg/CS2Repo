import java.util.Random;

/**
 * Representation of Woolie
 * Created by Smruthi Gadenkanahalli on 4/27/2017.
 */
public class Woolie {
    static int seed=1;
    String name;
    int minAtk;
    int maxAtk;
    int hitTime;
    int maxHP;
    int currentHP;
    Random rand=new Random();

    public Woolie(String name,int minAtk,int maxAtk,int hitTime,int maxHP){
        this.name=name;
        this.minAtk=minAtk;
        this.maxAtk=maxAtk;
        this.maxHP=maxHP;
        this.hitTime=hitTime;
        currentHP=maxHP;

        rand.setSeed(seed);


    }

    /**
     * Constructor to initialize from the string array
     * @param params
     */
    public Woolie(String[] params){
        this.name=params[0];
        this.minAtk=Integer.parseInt(params[1]);
        this.maxAtk=Integer.parseInt(params[2]);
        this.maxHP=Integer.parseInt(params[3]);
        this.hitTime=Integer.parseInt(params[4]);
        this.currentHP=maxHP;

    }

    /**
     * a method to get attack points
     * @return
     */
    public int getAttackAmount(){

        return rand.nextInt((this.maxAtk-this.minAtk))+this.minAtk;
    }

    /**
     * method to get the current hit points
     * @return
     */
    public int getCurrentHP(){
        return this.currentHP;
    }

    /**
     * method to get teh Hit time
     * @return
     */
    public int getHitTime(){
        return hitTime;
    }

    /**
     * Method to get eh Woolie name
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method to get if the woolie is alive
     * @return
     */
    public boolean isOK(){
        return (this.currentHP>0);
    }

    /**
     * Method to reset the hit points
     */
    public  void reset(){
        currentHP=maxHP;
    }

    /**
     * Method to make damage tot he Woolie hit points
     * @param damage
     */
    public void takeDamage(int damage){
        this.currentHP= this.currentHP-damage;

    }

    /**
     * Mehtod to represent in the Woolie in a string format
     * @return
     */
    public String toString(){
        //Foo: Max HP 100, Min Attack 20, Max Attack 40, Hit Time 3
        return  getName() + ": Max HP "+ this.maxHP + ", Min Attack " + this.minAtk + ", Max Attack "+ this.maxAtk+ ", Hit Time "+ this.hitTime;
    }
}

package calendar.model;

import java.util.Comparator;

/**
 * Class to represent Appointment
 * Created by Smruthi Gadenkanahalli on 4/13/2017.
 */
public class Appointment implements Comparable<Appointment>{
    //Member variables
    private int date;
    private String text;
    private Time time;

    public Appointment(){

    }
//Accessor for date
    public int getDate(){
        return this.date;

    }

    //Contructor to initialize the member fields
    public Appointment(int date,Time time,String text){

        this.date = date;
        this.time = time;
        this.text = text;
    }
    /*
    Mehtod to read from string to create the appointment
     */
    public static Appointment fromString(String S){

//        System.out.println(S);
        String [] app=S.split(",");

        return new Appointment(Integer.parseInt(app[0]),Time.fromString(app[1]),app[2]);
    }

    /**
     * Mehtod to convert the appoint to csv string format
     * @return string format
     */
    public String csvFormat(){
        return this.getDate()+","+this.time+","+text;

    }

    /**
     * String representations of the appointment
     * @return
     */
    public String toString(){
        return "on " +this.date +" at " +this.time + " -- " + this.text;

    }

//    public int getDate(){
//        return this.date;
//    }

    /**
     * Accessor for Time
     * @return
     */
    public Time getTime(){
        return this.time;
    }


    /**
     * Method to determine if object is equal to current instance
     * @param ob
     * @return
     */
    public boolean equals(Object ob){

        if(ob.getClass().isInstance(Appointment.class)) {
            Appointment ap=(Appointment)ob;
            if(ap.getDate()==(this.getDate()) && (ap.getTime().toString().equals(this.getTime().toString())))            return true;
        }

        return false;
    }

    /**
     * Mehtod to compare the appointments
     * @param o Input object to compare
     * @return -1 if appointment s earlier,0 if same,1 if appointment is later
     */
    @Override
    public int compareTo(Appointment o) {
        Appointment app=(Appointment)o;
        if( this.date<app.date || this.getTime().compareTo(app.getTime())<0){
            return -1;

        }
        else if(this.getDate()==app.date && this.getTime().compareTo(app.getTime())==0){
            return 0;
        }
        else


            return 1;
    }

//    @Override
//    public int compareTo(Appointment o) {
//        return 0;
//    }
}

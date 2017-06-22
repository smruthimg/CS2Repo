package calendar.model;

import sun.security.krb5.internal.APOptions;

import java.io.*;
import java.util.*;

/**
 * Class to represent Calendar
 * Created by Smruthi Gadenkanahalli on 4/13/2017.
 */
public class Calendar extends Observable {
    private   int monthSize;
    private static String saveFile;

    private List<List<Appointment>> appointments;
    public Calendar(){


    }

    /**
     * COnstructor for Calendar
     * @param i
     */
    public Calendar(int i){
        this.monthSize=i;
        appointments=new ArrayList<>(monthSize);
        for (int j = 0; j <monthSize ; j++) {
            ArrayList<Appointment> appt=new ArrayList<Appointment>();
            appointments.add(new ArrayList<>());
//            dateChanged(i);
        }


    }

    /*
    Mehtod to read from a file
     */
    public static Calendar fromFile(String s) throws IOException{
//        System.out.println(s);
        BufferedReader br=new BufferedReader( new FileReader(s));
        String line=br.readLine();
//        System.out.println(line);
        int date=Integer.parseInt(line);
        Calendar cal=new Calendar(Integer.parseInt(line));
//        System.out.println(cal.appointments.size());
        line=br.readLine();
        while(line!=null){


//            System.out.println(line);
            String [] app=line.split(",");
//            System.out.println(app[0]);
//            cal.appointments=new ArrayList<>();
            cal.appointments.get(Integer.parseInt(app[0])-1).add(new Appointment(Integer.parseInt(app[0]),Time.fromString(app[1]),app[2]));
//            System.out.println(cal.appointments.get(Integer.parseInt(app[0])));
            line=br.readLine();
        }
        saveFile=s;

        return cal;
    }

    /*
    Method to save to a file
     */
    public void toFile() throws IOException{
        if ( this.saveFile == null ) {
            throw new IOException( "Calendar not loaded from a file." );
        }
        try ( PrintWriter calFile = new PrintWriter( this.saveFile ) ) {
            //...
            calFile.println(monthSize);
//            this.appointments.sort(new Comparator<List<Appointment>>() {
//            });
//            Collections.sort(appointments.);
            for (int i = 1; i <=numDays() ; i++) {
//                Collections.sort(appointments.get(i));
                for (Appointment a:this.appointmentsOn(i)
                     ) {
                    calFile.println(a.csvFormat());
                }


            }

        }
        finally {

        }

    }

    /**
     * Method to get the monthsize
     * @return
     */
    public int numDays(){
        return monthSize;
    }

    /**
     * Mehtod to add the appointment to list
     * @param appt
     */
    public void add(Appointment appt){
        int date=appt.getDate();
//        System.out.println(appt);
        appointments.get(date-1).add(appt);
        dateChanged(date);

    }

    /**
     * Method to remove an appointment
     * @param appt
     */
    public void remove(Appointment appt){
//        System.out.println(appt);
        int date=appt.getDate();

        for (int i = 0; i <appointmentsOn(appt.getDate()).size() ; i++) {
            if(((appointmentsOn(appt.getDate()).get(i).getTime().toString()).equals(appt.getTime().toString()))){

                appointmentsOn(appt.getDate()).remove(i);

                dateChanged(date);

            }

        }


    }

    /** method to add an appointment
     *
     * @param date input date
     * @param time time of appointment
     * @param what description
     */
    public void add(int date,Time time, String what){
        Appointment appt=new Appointment(date,time,what);
        appointments.get(date).add(appt);
        dateChanged(date);


    }

    /**
     * Method to get the appointments on the selected date
     * @param date
     * @return
     */
    public List<Appointment> appointmentsOn(int date){
//        System.out.println(date-1);
//        appointments;
        ArrayList appts=new ArrayList();
        appts.addAll(appointments.get(date-1));
        Collections.sort(appts);

        return appts;
    }

    /**
     * Method to notify the change to calendar
     * @param date
     */
    private void dateChanged(int date ){
        super.setChanged();
//        System.out.println("notify");
        super.notifyObservers(date);

    }


}

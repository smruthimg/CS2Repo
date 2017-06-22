package calendar.view_controller.fx;

//import com.sun.org.apache.xpath.internal.operations.String;
import calendar.model.Appointment;
import calendar.model.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

import static javafx.scene.paint.Color.*;

/**
 * Representation of the Calendar UI
 * Created by Smruthi Gadenkanahalli on 4/21/2017.
 */
public class KalGUI extends Application implements Observer{
    private Calendar kal;
    private Appointment appointment;
    private List<Appointment> appList;

    /**
     * Method to initialize calendar
     * @throws IOException
     */
    @Override
    public void init() throws IOException {
        this.kal = new Calendar();
        this.appList=new ArrayList<>();
        Application.Parameters params=getParameters();
        int days=0;
        if(params.getRaw().size()==0){
            days=28;
            kal=new Calendar(days);
        }
        else{
//            System.out.println(params.getRaw());
            kal=kal.fromFile(params.getRaw().get(0).toString());
//            days=Integer.parseInt(params.getRaw().get(0));
        }


//        System.out.println("init");
        kal.addObserver( this );
//        System.out.println(this);
    }

    /**
     * The main is the application entry point to launch the JavaFX GUI.
     * @param args not used
     */
    public static void main( String[] args ) {
        launch(args);
    }

    /**
     * method to set up stage
     * @param stage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane border=new BorderPane();

        border.setCenter(makeGrid());
        HBox vbox=new HBox(new Label("Kalendar"));
        vbox.setAlignment(Pos.CENTER);

        Button save=new Button("Save");
        HBox hBox2=new HBox(save);
        border.setBottom(hBox2);
        hBox2.setFillHeight(true);
        hBox2.setAlignment(Pos.CENTER);

//        save.setAlignment(Pos.TOP_RIGHT);
//        vbox.getChildren().add(save);
        border.setTop(vbox);
        vbox.setSpacing(50);
        border.setRight(new VBox());
        save.setOnAction(e -> {
            try {
                kal.toFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Scene scene = new Scene( border );

        stage.setTitle( "Kalendar" );
        stage.setScene( scene );
        stage.show();

    }

    /**
     * Mehtod to update the UI after the Model is updated.
     * @param o Observable object
     * @param arg the oject
     */
    @Override
    public void update(Observable o, Object arg) {
        assert o == this.kal: "Unexpected subject of observation";

        appList=this.kal.appointmentsOn((Integer)arg);
    }

    /**
     * Class to create  Add Appointment popup
     */
    class PopupAddApp extends Stage{
        public PopupAddApp(String S){
            FlowPane fP=new FlowPane();
            Label string=new Label("Add Appointment : ");

            TextField app=new TextField("Add Appointment");
            fP.getChildren().addAll(string,app);
            this.setTitle( "Add Appointment" );
            this.setWidth( 300 );
            this.setHeight( 200 );

            this.setScene( new Scene(fP) );
            this.show();
        }
    }

    /**
     *Class to create the Calendar
     */
    class PopupStage extends Stage {
        public  PopupStage( String S, FlowPane pane) {
//            System.out.println(S);

            BorderPane bPane=new BorderPane();
            VBox vBox=new VBox();
            ToggleGroup group=new ToggleGroup();
//            Label labelHead=new Label(S);
//            vBox.getChildren().addAll(labelHead);
            bPane.setCenter(vBox);
            HBox hBox=new HBox();
            Button addApp=new Button("Add");
            addApp.setPrefWidth(100);
            Button removeApp=new Button("Remove");
            removeApp.setPrefWidth(100);
            Button close=new Button("Close");
            close.setPrefWidth(100);
            hBox.getChildren().addAll(addApp,removeApp,close);
            close.setOnAction(e->this.close());
            bPane.setBottom(hBox);
            appList=kal.appointmentsOn(Integer.parseInt(S));
            if(appList.size()!=0){
                for (Appointment a:appList
                     ) {

                    RadioButton r=new RadioButton(a.toString());
                    r.setUserData(a);

                    r.setToggleGroup(group);
                    vBox.getChildren().addAll(r);

                }

            }
            else{
                removeApp.setDisable(true);
            }
//            else{
//                Label noApp=new Label("No Appointments");
//                vBox.getChildren().add(noApp);
//            }
            //event handler for add button
            addApp.setOnMouseClicked(
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            TextInputDialog takeAppointment = new TextInputDialog("time,description");
                            takeAppointment.setTitle("Add Appointment");
                            takeAppointment.setHeaderText("Add Appointment");
                            takeAppointment.setContentText("Please enter your appointment details:");
                            Optional<String> result = takeAppointment.showAndWait();
                            if (result.isPresent()){
                                appointment=Appointment.fromString(S+"," +result.get());
//                                System.out.println(newApp);
                                kal.add(appointment);
                                RadioButton r=new RadioButton(appointment.toString());
                                r.setUserData(appointment);

                                r.setToggleGroup(group);
                                vBox.getChildren().addAll(r);
                                hBox.getChildren().get(1).setDisable(false);
//                                System.out.println();



                            }
                        }
                    });
//            System.out.println(getEventDispatcher());
            //Event handler for remove button
            removeApp.setOnMouseClicked(
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            Alert remAppointment = new Alert(Alert.AlertType.CONFIRMATION);
                            remAppointment.setTitle("Remove Appointment");
                            remAppointment.setHeaderText("Remove Appointment? " );
                            Optional<ButtonType> result = remAppointment.showAndWait();
                            if (result.get()==ButtonType.OK){
                                if((group.getSelectedToggle()!=null)){
                                    appointment=(Appointment)group.getSelectedToggle().getUserData();
//                                    System.out.println(appointment+" " + kal.appointmentsOn(appointment.getDate()));
                                    kal.remove(appointment);
                                    vBox.getChildren().remove(group.getSelectedToggle());
                                }


//                                System.out.println(kal.appointmentsOn(Integer.parseInt(S)));




                            }
                        }
                    });
//            addApp.setOnAction(event -> new PopupAddApp("Appointment"));

            this.setTitle( "Appointments" );
            this.setWidth( 300 );
            this.setHeight( 200 );

            this.setScene( new Scene(bPane) );
            this.show();

        }
    }

    /**
     * Mehtod to create the dates in the Calendar
     * @return
     */
    private FlowPane makeGrid(){
        FlowPane pane=new FlowPane();
        pane.setPrefWidth(300);
        pane.setPrefHeight(200);
        int date = 1;
        for (int i = 0; i <kal.numDays(); i++) {//(Integer.parseInt(params.getRaw().get(0)) )
            final int x=i+1;
            Button btn = new Button(""+date);
            btn.setTextAlignment(TextAlignment.RIGHT);
            btn.setPrefHeight(50);
            btn.setPrefWidth(50);
            btn.setId(Integer.toString(i));
//            if( kal.appointmentsOn(date).size()>0  ){
//
//                btn.setStyle("-fx-base: #b6e7c9;");
//            }

            btn.setOnAction((ActionEvent event) -> {
                btn.setStyle("-fx-base: #b6e7c9;");
                new PopupStage(Integer.toString(x),pane);

            });//(Integer.toString(date)));



            pane.getChildren().add(btn);
            date++;
        }
        for (int i = 0; i <pane.getChildren().size() ; i++) {
//            System.out.println(pane.getChildren().get(i));
            if(kal.appointmentsOn(i+1).size()>0){
                pane.getChildren().get(i).setStyle("-fx-base: #b6e7c9;");
            }
            else{
            }
        }



        return pane;
    }

//private void renderAppointments(String S){
//
//}


}

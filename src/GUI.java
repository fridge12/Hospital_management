import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


//I am mainly going to use static methods since there will only be one frame
public class GUI {

    //the frame
    public static JFrame GUI;
    //these will be used to differenciate between patients and doctors
    private static final String PATIENT = "Patient";
    private static final String DOCTOR = "Doctor";
    //the container in which the modifications to the screen will happen
    private static Container ContentPane;

    //a plane black border
    private static final LineBorder BLACK_BORDER= new LineBorder(Color.BLACK);

    public static void main(String args[]){

    createFrame();

    }

    private static void createFrame(){
        GUI = new JFrame();
        GUI.setVisible(false);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting screen size to half the size of the computer screen so if frame is unmaximized it is still usable
        GUI.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.5),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.5));

        ContentPane = GUI.getContentPane();
        GUI.setVisible(true);
        createRecetption("");






    }

    //takes the contentpane of the frame and modifies it
    private static void createRecetption(String message){

        //setting the layout of the container
        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/5),(ContentPane.getHeight()/16)));


        //creating label with the word reception
        JLabel receptionLabel = new JLabel("Reception",JLabel.CENTER);
        //border is mainly used for testing, to see if it is positioned properly
        //receptionLabel.setBorder(BLACK_BORDER);
        //setting size
        receptionLabel.setPreferredSize(new Dimension(ContentPane.getWidth(), (int) (ContentPane.getSize().getHeight()/5)));
        //adding label to container
        ContentPane.add(receptionLabel);


        //creating button to add patient
        JButton newPatient = new JButton("New Patient");
        //setting size of button
        newPatient.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        newPatient.setBorder(BLACK_BORDER);

        //adding functionality to the button
        newPatient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               createNewPatientDoctor(PATIENT);
            }
        });

        newPatient.setVisible(true);
        //adding it to the container
        ContentPane.add(newPatient);

        //creating button to add patient
        JButton newDoctor = new JButton("New Doctor");
        //setting size of button
        newDoctor.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        newDoctor.setBorder(BLACK_BORDER);

        //adding functionality to the button
        newDoctor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                createNewPatientDoctor(DOCTOR);
            }
        });

        newDoctor.setVisible(true);
        //adding it to the container
        ContentPane.add(newDoctor);




        //creating button to add patient
        JButton loginPatient = new JButton("Patient login");
        //setting size of button
        loginPatient.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        loginPatient.setBorder(BLACK_BORDER);
        loginPatient.setVisible(true);
        //adding it to the container
        ContentPane.add(loginPatient);

        //creating button to add patient
        JButton loginDoctor= new JButton("Doctor login");
        //setting size of button
        loginDoctor.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        loginDoctor.setBorder(BLACK_BORDER);
        loginDoctor.setVisible(true);
        //adding it to the container
        ContentPane.add(loginDoctor);

        //creating button to add patient
        JButton emergency= new JButton("emergency");
        //setting size of button
        emergency.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        emergency.setBorder(BLACK_BORDER);
        //setting background color to a slightly transparent red
        emergency.setBackground(new Color(255,0,0,180));
        emergency.setVisible(true);
        //adding it to the container
        ContentPane.add(emergency);

        //creating a label that displays a message
        JLabel messageLabel = new JLabel(message,JLabel.CENTER);
        //setting size
        messageLabel.setPreferredSize(new Dimension(ContentPane.getWidth(), 40));
        messageLabel.setVisible(true);
        //adding it to the container
        ContentPane.add(messageLabel);

        ContentPane.setVisible(true);
    }


    private static void createNewPatientDoctor(String patientDoctor){




        //setting the layout of the container
        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/2),(ContentPane.getHeight()/16)));

        JLabel newPatientDoctorLabel = new JLabel("New "+patientDoctor,JLabel.CENTER);
        //border is mainly used for testing, to see if it is positioned properly
        //newPatientDoctorLabel.setBorder(BLACK_BORDER);
        //setting size
        newPatientDoctorLabel.setPreferredSize(new Dimension(ContentPane.getWidth(), (int) (ContentPane.getSize().getHeight()/5)));
        //adding label to container
        ContentPane.add(newPatientDoctorLabel);

        //textfield to enter name of patient/doctor
        JTextField newPatientDoctorName = new JTextField("Name");
        //setting size of textfield
        newPatientDoctorName.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //adding border
        newPatientDoctorName.setBorder(BLACK_BORDER);

        //adding focus listener to set hint in the textfield
        newPatientDoctorName.addFocusListener( new FocusListener(){
            public void focusGained(FocusEvent e){
                if(newPatientDoctorName.getText().equals("Name")) {
                    newPatientDoctorName.setText("");
                }
            }


            public void focusLost(FocusEvent e) {
                if(newPatientDoctorName.getText().equals("")) {
                    newPatientDoctorName.setText("Name");
                }
            }

        });

        newPatientDoctorName.setVisible(true);
        //adding to container
        ContentPane.add(newPatientDoctorName);


        //label to enter name of patient/doctor
        JLabel newPatientDoctorID = new JLabel("");
        //setting size
        newPatientDoctorID.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //adding border
        newPatientDoctorID.setBorder(BLACK_BORDER);

        if(patientDoctor.equals(PATIENT)){
            //id of patient
            newPatientDoctorID.setText("ID = " + Patient.totalPatients.size());
        }
        else{
            //id of doctor
            newPatientDoctorID.setText("ID = " + Doctor.totalDoctors.size());

        }
        newPatientDoctorID.setVisible(true);
        //adding to container
        ContentPane.add(newPatientDoctorID);

        //button to submit
        JButton submitPatientDoctor = new JButton("Submit");
        //setting size of button
        submitPatientDoctor.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        submitPatientDoctor.setBorder(BLACK_BORDER);

        //creating a new doctor or patient when the button is pressed
        submitPatientDoctor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //adds a new doctor or patient and then calls the next method
                if(patientDoctor.equals(DOCTOR)) {
                    Doctor doc = new Doctor(newPatientDoctorName.getText());
                    //calls the opening screen again because there is no more functionality for the doctor
                    createRecetption("doctor added");
                }
                //if there are no doctors you can't add a patient
                else if(Doctor.totalDoctors.size()==0&& patientDoctor.equals(PATIENT)){
                    //informing the user that no doctors are available
                    createRecetption("Sorry, there are no doctors");

                    }
                    else {
                    //adding a new patient
                    Patient patient = new Patient(newPatientDoctorName.getText());
                    System.out.println("button pressed");
                    createSymptoms(patient);

                }
             }
        });
        submitPatientDoctor.setVisible(true);
        //adding it to the container
        ContentPane.add(submitPatientDoctor);

        //creating back button
        ContentPane.add(createBackButton("cancelled creating "+ patientDoctor));



        //setting the container visible after all the components have been added
        ContentPane.setVisible(true);
    }

    private static void createSymptoms(Patient patient){


        //clearning container and adding a layout
        ContentPane.setLayout(clearContentPaneAddLayout(40,40));

        //adding a label informing a user what to do
        JLabel symptomsLabel= new JLabel("Please select the symptoms you are experiencing and Dr."+patient.doctor.name+" will give you a diagnosis",JLabel.CENTER);
        //setting size
        symptomsLabel.setPreferredSize(new Dimension(ContentPane.getWidth(),ContentPane.getHeight()/5));
        //this is used mainly for testing
        //symptomsLabel.setBorder(BLACK_BORDER);
        //setting visible
        symptomsLabel.setVisible(true);
        //adding it to the container
        ContentPane.add(symptomsLabel);


        //creating a radio button for each symptom, so the user can select which ever symptoms they have
        for(String symptom:Diagnosis.symptomsList){
            //creating radio button and replacing all the underscores with spaces, since symptoms names have underscores in them
            JRadioButton symptomButton = new JRadioButton(symptom.replaceAll("_"," "));
            //setting a border on them
            symptomButton.setBorder(BLACK_BORDER);
            //setting them visible
            symptomButton.setVisible(true);

            //if the button is selected then the symptom is added to the symptomsShown string, if it is unselected then the symptoms is removed from the symptomsShown string
            symptomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(symptomButton.isSelected()){
                        patient.symptomsShown=patient.symptomsShown+symptom;
                    }
                    else {
                        patient.symptomsShown = patient.symptomsShown.replaceAll(symptom, "");
                    }
                }


            });

            ContentPane.add(symptomButton);

        }

        JButton proceedButton = new JButton("Proceed");
        proceedButton.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        proceedButton.setBorder(BLACK_BORDER);
        proceedButton.setVisible(true);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (patient.symptomsShown != "") {
                    foundDiagnosis(patient, patient.symptomsShown, false);
                    System.out.println(patient.symptomsShown);
                } else {
                    String warning = " Please select atleast one symtpom";
                    if(!(symptomsLabel.getText().contains(warning))){
                        symptomsLabel.setText(warning);
                    }
                }
            }
        });



        ContentPane.add(proceedButton);

        ContentPane.add(createBackButton("patient did not visit the doctor"));

        ContentPane.setVisible(true);
    }

    private static void foundDiagnosis(Patient patient,String patientSymptoms, Boolean isEmergency){


        ContentPane.setLayout(clearContentPaneAddLayout(40,40));

        JLabel diagnosis = new JLabel("",JLabel.CENTER);
        diagnosis.setPreferredSize(new Dimension(ContentPane.getWidth(),(ContentPane.getHeight()/4)));
        diagnosis.setBorder(BLACK_BORDER);
        if(isEmergency){
            diagnosis.setText("After taking a look at your symptom(s) Dr."+patient.doctor.name+" said that you most likely have severe " +patient.findDiagnosis(patientSymptoms));
        }
        else{
            diagnosis.setText("After taking a look at your symptom(s) Dr."+patient.doctor.name+" said that you most likely have " +patient.findDiagnosis(patientSymptoms));
        }
        diagnosis.setVisible(true);
        ContentPane.add(diagnosis);

        ContentPane.add(createBackButton("diagnosis given"));

        ContentPane.setVisible(true);
    }

    //creating a method for this as it is used at the beginning of almost every method.
    private static FlowLayout clearContentPaneAddLayout(int hGap, int vGap){

        //making current container invisible
        ContentPane.setVisible(false);
        //clearing frame's content pane
        ContentPane.removeAll();
        //setting the size of the container
        ContentPane.setSize(GUI.getSize());

        //creating new layout
        FlowLayout flowlayout  = new FlowLayout();
        //setting horizontal and vertical gap between components
        flowlayout.setHgap(hGap);
        flowlayout.setVgap(vGap);

        return flowlayout;
    }

    //method for creating backbutton as it is used for almost every frame
    private static JButton createBackButton (String message){

        //button to go back
        JButton backButton = new JButton("Back");
        //setting size
        backButton.setPreferredSize(new Dimension(40,40));
        //setting border
        backButton.setBorder(BLACK_BORDER);
        //adding functionality to the button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRecetption(message);
            }
        });
        backButton.setVisible(true);

        return backButton;
    }
}

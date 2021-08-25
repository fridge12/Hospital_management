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

    public static void createFrame(){
        GUI = new JFrame();
        GUI.setVisible(false);
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting screen size to half the size of the computer screen so if frame is unmaximized it is still usable
        GUI.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.5),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.5));

        ContentPane = GUI.getContentPane();
        createRecetption("");




        GUI.setVisible(true);

    }

    //takes the contentpane of the frame and modifies it
    public static void createRecetption(String message){

        //I don't use a method to do the modifications to the content panes as all the frames have slightly different needs
        //making current container invisible
        ContentPane.setVisible(false);
        //clearing frame's content pane
        ContentPane.removeAll();
        //setting the size of the container
        ContentPane.setSize(GUI.getSize());

        FlowLayout receptionLayout = new FlowLayout();
        //setting horizontal gap between components
        receptionLayout.setHgap(ContentPane.getWidth()/5);
        //setting vertical gap between components
        receptionLayout.setVgap(ContentPane.getHeight()/16);


        //setting the layout of the container
        ContentPane.setLayout(receptionLayout);


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


    public static void createNewPatientDoctor(String patientDoctor){

        //making current container invisible
        ContentPane.setVisible(false);
        //clearing frame's content pane
        ContentPane.removeAll();
        //setting the size of the container
        ContentPane.setSize(GUI.getSize());

        FlowLayout newPatientDoctorLayout = new FlowLayout();
        //setting horizontal gap between components
        newPatientDoctorLayout.setHgap(ContentPane.getWidth()/2);
        //setting vertical gap between components
        newPatientDoctorLayout.setVgap(ContentPane.getHeight()/16);


        //setting the layout of the container
        ContentPane.setLayout(newPatientDoctorLayout);

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

        if(patientDoctor==PATIENT){
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

        //adding  functionality to the button
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


        //button to go back
        JButton backButton = new JButton("Back");
        //setting size
        backButton.setPreferredSize(new Dimension(newPatientDoctorLayout.getVgap(),newPatientDoctorLayout.getVgap()));
        //setting border
        backButton.setBorder(BLACK_BORDER);
        //adding functionality to the button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRecetption("");
            }
        });
        backButton.setVisible(true);
        //adding it to the container
        ContentPane.add(backButton);




        ContentPane.setVisible(true);
    }

    public static void createSymptoms(Patient patient){
        //making current container invisible
        ContentPane.setVisible(false);
        //clearing frame's content pane
        ContentPane.removeAll();
        //setting the size of the container
        ContentPane.setSize(GUI.getSize());

        //creating new layout
        FlowLayout symptomsLayout = new FlowLayout();
        //setting horizontal and vertical gap between components
        symptomsLayout.setHgap(40);
        symptomsLayout.setVgap(40);

        ContentPane.setLayout(symptomsLayout);


        JLabel symptomsLabel= new JLabel("Please select the symptoms you are experiencing and Dr."+patient.doctor.name+" will give you a diagnosis",JLabel.CENTER);
        symptomsLabel.setPreferredSize(new Dimension(ContentPane.getWidth(),ContentPane.getHeight()/5));
        symptomsLabel.setBorder(BLACK_BORDER);
        symptomsLabel.setVisible(true);
        ContentPane.add(symptomsLabel);



        for(String symptom:Diagnosis.symptomsList){

            JRadioButton symptomButton = new JRadioButton(symptom);
            //symptomButton.setSize();
            symptomButton.setBorder(BLACK_BORDER);
            //symptomButton.setSize(40,40);
            symptomButton.setVisible(true);
            ContentPane.add(symptomButton);

        }

        JButton proceedButton = new JButton("Proceed");
        proceedButton.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        proceedButton.setBorder(BLACK_BORDER);
        proceedButton.setVisible(true);

        //TODO: add button functoinality

        ContentPane.add(proceedButton);

        System.out.println("symptoms list");

        ContentPane.setVisible(true);
    }

}

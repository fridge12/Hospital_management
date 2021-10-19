import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.format.DateTimeFormatter;


//I am mainly going to use static methods since there will only be one frame which is static
public class GUI {

    //the frame
    public static JFrame GUI;
    //these will be used to differenciate between patients and doctors when calling certain methods.
    private static final String PATIENT = "Patient";
    private static final String DOCTOR = "Doctor";

    //the container in which the modifications to the screen will happen
    private static Container ContentPane;

    //a plane black border which is mainly used to see if the components are aligned correctly
    private static final LineBorder BLACK_BORDER= new LineBorder(Color.BLACK);

    public static void main(String args[]){
    //calling method to create frame
    createFrame();

    }

    private static void createFrame(){
        //creating new frame
        GUI = new JFrame();
        GUI.setVisible(false);
        //making it so that when frame is closed the program exits.
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting frame size to slightly more than half the size of the computer screen so if frame is unmaximized it is still usable
        GUI.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.5),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.5));

        //setting the container equal to the container of the frame
        ContentPane = GUI.getContentPane();
        //setting frame to visible
        GUI.setVisible(true);

        //calling method to create the reception
        createRecetption("");

    }

    //method to create reception, where user can decide between new patient/doctor , patient/doctor login or emergency
    private static void createRecetption(String message){

        removeEmptyPatients();

        //setting the layout of the container
        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/5),(ContentPane.getHeight()/16)));

        System.out.println(ContentPane.getSize());

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



        //creating button to add doctor
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




        //creating button for patient login
        JButton loginPatient = new JButton("Patient login");
        //setting size of button
        loginPatient.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        loginPatient.setBorder(BLACK_BORDER);

        loginPatient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                createPatientDoctorLogin(PATIENT);
            }
        });
        loginPatient.setVisible(true);
        //adding it to the container
        ContentPane.add(loginPatient);



        //creating button for doctor login
        JButton loginDoctor= new JButton("Doctor login");
        //setting size of button
        loginDoctor.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        loginDoctor.setBorder(BLACK_BORDER);
        loginDoctor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                createPatientDoctorLogin(DOCTOR);
            }
        });
        loginDoctor.setVisible(true);
        //adding it to the container
        ContentPane.add(loginDoctor);



        //creating button for emergency
        JButton emergency= new JButton("emergency");
        //setting size of button
        emergency.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        emergency.setBorder(BLACK_BORDER);
        //setting background color to a slightly transparent red
        emergency.setBackground(new Color(255,0,0));
        //adding fucntionality to the button
        emergency.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("creating emergency");
                createEmergency();
            }
        });
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

    //used to create new doctors and patients depending on the given string
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
            //when the textfield has gained focus and text is equal to Name it will set text to empty
            public void focusGained(FocusEvent e){
                if(newPatientDoctorName.getText().equals("Name")) {
                    newPatientDoctorName.setText("");
                }
            }

            //when the textfield loses focus, if the text is empty it sets the text to Name
            public void focusLost(FocusEvent e) {
                if(newPatientDoctorName.getText().equals("")) {
                    newPatientDoctorName.setText("Name");
                }
            }

        });

        newPatientDoctorName.setVisible(true);
        //adding to container
        ContentPane.add(newPatientDoctorName);


        //label to inform user about the id of the patient/doctor
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



        //button to submit which will create a new patient/doctor
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
                //adding a patient since we have already checked if doctors are available
                else {
                    //adding a new patient
                    Patient patient = new Patient(newPatientDoctorName.getText());
                    System.out.println("button pressed");
                    //creating symptoms for doctor visit
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

    //creating symptom selection screen
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

        //creating the symptom buttons the user can select
        createSymptomsList(patient);

        //button that will take the user to the screen which will give then the diagnosis
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        proceedButton.setBorder(BLACK_BORDER);
        proceedButton.setVisible(true);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //it will only proceed if symptoms shown is not empty
                if (!(patient.symptomsShown.isBlank())) {
                    System.out.println(patient.symptomsShown+"symptom");
                    //calls method to go to next screen
                    createFoundDiagnosis(patient, patient.symptomsShown, false);

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

    private static void createFoundDiagnosis(Patient patient,String patientSymptoms, Boolean isEmergency){

        //clearing container and adding layout
        ContentPane.setLayout(clearContentPaneAddLayout(ContentPane.getWidth()/2,40));

        //adding textpane which will tell the user what diagnosis they have
        JTextPane diagnosis = new JTextPane();
        //setting editable to false so that the user can not edit the textpane
        diagnosis.setEditable(false);

        //it is easier to set the text in a string and then put in a jtextpane
        String diagnosis_text= "";

        //this is mainly used for testing
        //diagnosis.setBorder(BLACK_BORDER);


        if(isEmergency){
            diagnosis_text= diagnosis_text.concat("Visit with Dr."+patient.doctor.name+" \n\nDiagnosis: severe " +patient.findDiagnosis(patientSymptoms)+"\n\nCure:"+ patient.doctorVisits.getLast().confirmedDiagnosis.severeCure);
        }
        else{
            diagnosis_text= diagnosis_text.concat("Visit with Dr."+patient.doctor.name+" \n\nDiagnosis:" +patient.findDiagnosis(patientSymptoms)+"\n\nCure:"+ patient.doctorVisits.getLast().confirmedDiagnosis.cure);
        }

        //the next three lines are to set the text alignment to center in the textpane
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center,StyleConstants.ALIGN_CENTER);
        diagnosis.getStyledDocument().setParagraphAttributes(0,diagnosis.getText().length(), center , false);

        System.out.println(diagnosis_text);

        //setting text
        diagnosis.setText(diagnosis_text);

        //setting size at the end to wrap contents
        diagnosis.setPreferredSize(diagnosis.getPreferredSize());

        diagnosis.setVisible(true);
        ContentPane.add(diagnosis);

        ContentPane.add(createBackButton("diagnosis given"));

        ContentPane.setVisible(true);
    }

    //method to create screen if there is an emergency, it has patient creation and symptom selection in one screen
    private static void createEmergency() {

        if (Doctor.totalDoctors.size() == 0) {
            createRecetption("Sorry, there are no doctors");
        }
        else {
        //I need to create a patient without a name as createSymptomsList needs a patient as an input
        Patient patient = new Patient("");

        ContentPane.setLayout(clearContentPaneAddLayout(40, 40));


        JLabel emergencyLabel = new JLabel("please select your symptoms",JLabel.CENTER);
            emergencyLabel.setPreferredSize(new Dimension(ContentPane.getWidth(), ContentPane.getHeight() / 5));
            //emergencyLabel.setBorder(BLACK_BORDER);
            emergencyLabel.setVisible(true);
        ContentPane.add(emergencyLabel);

        //calling method to create the symptoms
        createSymptomsList(patient);

        //textfield to enter name of patient
        JTextField newPatientName = new JTextField("Name");
        //setting size of textfield
        newPatientName.setPreferredSize(new Dimension((ContentPane.getWidth() / 4), (ContentPane.getWidth() / 20)));
        //adding border
        newPatientName.setBorder(BLACK_BORDER);

        //adding focus listener to set hint in the textfield
        newPatientName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (newPatientName.getText().equals("Name")) {
                    newPatientName.setText("");
                }
            }


            public void focusLost(FocusEvent e) {
                if (newPatientName.getText().equals("")) {
                    newPatientName.setText("Name");
                }
            }

        });

        newPatientName.setVisible(true);
        //adding to container
        ContentPane.add(newPatientName);


        //label display the id of patient
        JLabel newPatientID = new JLabel("");
        //setting size
        newPatientID.setPreferredSize(new Dimension((ContentPane.getWidth() / 4), (ContentPane.getWidth() / 20)));
        //adding border
        newPatientID.setBorder(BLACK_BORDER);
        //displaying id
        newPatientID.setText("ID = " + (Patient.totalPatients.size()-1));

        newPatientID.setVisible(true);
        //adding to container
        ContentPane.add(newPatientID);

        //button to proceed to the diagnosis screen
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setPreferredSize(new Dimension((ContentPane.getWidth() / 4), (ContentPane.getWidth() / 20)));
        proceedButton.setBorder(BLACK_BORDER);

            //adding functionality to the button
            proceedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(patient.name);
                    //setting patient name so that it is not left blank
                    patient.name= newPatientName.getText();
                    System.out.println(patient.name);
                    //it will only proceed if the symptomsShown is not blank
                    if (!(patient.symptomsShown.isBlank())) {
                        //calling method to display diagnosis
                        createFoundDiagnosis(patient, patient.symptomsShown, true);
                        System.out.println(patient.symptomsShown);
                    }
                    else {
                        String warning = " Please select atleast one symtpom";
                        if(!(emergencyLabel.getText().contains(warning))){
                            emergencyLabel.setText(warning);
                        }
                    }
                }
            });
            proceedButton.setVisible(true);
            ContentPane.add(proceedButton);

        ContentPane.add(createBackButton("cancelled emergency"));
        ContentPane.setVisible(true);
    }
    }

    //method to create the login screen for doctors and patients
    private static void createPatientDoctorLogin(String patientDoctor){

        //setting the layout of the container
        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/2),(ContentPane.getHeight()/16)));

        JLabel patientDoctorLoginLabel = new JLabel("New "+patientDoctor,JLabel.CENTER);
        //border is mainly used for testing, to see if it is positioned properly
        //newPatientDoctorLabel.setBorder(BLACK_BORDER);
        //setting size
        patientDoctorLoginLabel.setPreferredSize(new Dimension(ContentPane.getWidth(), (int) (ContentPane.getSize().getHeight()/5)));
        //adding label to container
        ContentPane.add(patientDoctorLoginLabel);

        //textfield to enter name of patient/doctor
        JTextField patientDoctorName = new JTextField("Name");
        //setting size of textfield
        patientDoctorName.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //adding border
        patientDoctorName.setBorder(BLACK_BORDER);

        //adding focus listener to set hint in the textfield
        patientDoctorName.addFocusListener( new FocusListener(){
            public void focusGained(FocusEvent e){
                if(patientDoctorName.getText().equals("Name")) {
                    patientDoctorName.setText("");
                }
            }


            public void focusLost(FocusEvent e) {
                if(patientDoctorName.getText().equals("")) {
                    patientDoctorName.setText("Name");
                }
            }

        });

        patientDoctorName.setVisible(true);
        //adding to container
        ContentPane.add(patientDoctorName);


        //textfield to enter ID of patient/doctor
        JTextField patientDoctorID = new JTextField("ID Number");
        //setting size
        patientDoctorID.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //adding border
        //adding focus listener to set hint in the textfield
        patientDoctorID.addFocusListener( new FocusListener(){
            public void focusGained(FocusEvent e){
                if(patientDoctorID.getText().equals("ID Number")) {
                    patientDoctorID.setText("");
                }
            }


            public void focusLost(FocusEvent e) {
                if(patientDoctorID.getText().equals("")) {
                    patientDoctorID.setText("ID Number");
                }

            }

        });
        patientDoctorID.setVisible(true);
        //adding to container
        ContentPane.add(patientDoctorID);

        //button to submit
        JButton patientDoctorLogin = new JButton("Login");
        //setting size of button
        patientDoctorLogin.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        patientDoctorLogin.setBorder(BLACK_BORDER);

        //creating a new doctor or patient when the button is pressed
        patientDoctorLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                try{

                if (Patient.totalPatients.size() == 0 && patientDoctor == PATIENT) {
                    createRecetption("Sorry, there are no patients");
                }
                else if (Doctor.totalDoctors.size() == 0 && patientDoctor == DOCTOR) {
                    createRecetption("Sorry, there are no doctors");
                }
                else {
                    if (patientDoctor == PATIENT) {
                        //iterating through all the patients to see if any match
                        for (Patient p : Patient.totalPatients) {
                            if (p.name.equals(patientDoctorName.getText()) && p.ID == Integer.valueOf(patientDoctorID.getText())) {
                                System.out.println("patient found");
                                //calling method to go to the patients page
                                createPatientPage(p);
                            }
                        }
                        patientDoctorLoginLabel.setText("please enter valid patient details");

                    } else {
                        //iterating through all the doctors to see if any match
                        for (Doctor d : Doctor.totalDoctors) {
                            System.out.println(d.name + " " + patientDoctorName.getText() + d.ID + " " + Integer.valueOf(patientDoctorID.getText()));
                            if (d.name.equals(patientDoctorName.getText()) && d.ID == Integer.valueOf(patientDoctorID.getText())) {
                                System.out.println("doctor found");
                                //calling method to go to the doctors page
                                createDoctorPage(d);
                            }
                        }
                        patientDoctorLoginLabel.setText("please enter valid doctor details");
                    }
                }
            }
                //need to use try catch as patientDoctorID can have a string value which would throw an exception when using Integer.valueOf
            catch(Exception exception){
                patientDoctorLoginLabel.setText("Please enter valid ID");
                }
            }
        });
        patientDoctorLogin.setVisible(true);
        //adding it to the container
        ContentPane.add(patientDoctorLogin);

        //creating back button
        ContentPane.add(createBackButton("cancelled "+ patientDoctor+" login"));



        //setting the container visible after all the components have been added
        ContentPane.setVisible(true);
    }


    //to create a page where the patient can access all their information
    private static void createPatientPage(Patient patient){

        ContentPane.setLayout(clearContentPaneAddLayout(ContentPane.getWidth()/2,40));

        //setting symptomsshown to empty, as if it is not done the symptomsshown will have more symptoms than what have been selected.
        patient.symptomsShown="";

        //creating a label that will say hello to the patient
        JLabel greetingPatient = new JLabel("Hello "+patient.name+" these are all your visits",JLabel.CENTER);
        //setting size
        greetingPatient.setPreferredSize(new Dimension(ContentPane.getWidth(), (int) (ContentPane.getSize().getHeight()/5)));
        //setting border, this is mainly used for testing
        //greetingPatient.setBorder(BLACK_BORDER);
        greetingPatient.setVisible(true);
        ContentPane.add(greetingPatient);


        //button to visit the doctor
        JButton visitDoctor = new JButton("Visit Dr."+patient.doctor.name);
        //setting size of button
        visitDoctor.setPreferredSize(new Dimension((ContentPane.getWidth()/4),(ContentPane.getWidth()/20)));
        //setting border
        visitDoctor.setBorder(BLACK_BORDER);

        //adding functionality to the button
        visitDoctor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                createSymptoms(patient);
            }
        });

        visitDoctor.setVisible(true);
        //adding it to the container
        ContentPane.add(visitDoctor);


        //need to create container to hold buttons otherwise scrollpane won't work
        Container visitPane = new Container();
        //need to use setpreferredsize as otherwise it won't work in the scrollpane
        visitPane.setPreferredSize(new Dimension(ContentPane.getWidth()-(ContentPane.getWidth()/25),ContentPane.getHeight()));


        FlowLayout buttonLayout = new FlowLayout();
        buttonLayout.setVgap((ContentPane.getWidth()/25));
        buttonLayout.setHgap((ContentPane.getWidth()/25));
        //setting layout of the container
        visitPane.setLayout(buttonLayout);

        //iterating through all the visits of a patient and adding them to the container as buttons
        for(Visits v : patient.doctorVisits){
            //creating button
            JButton visit = new JButton("Visit: "+v.visitNumber);
            //setting size
            visit.setPreferredSize(new Dimension((ContentPane.getWidth()/8),(ContentPane.getWidth()/40)));
            visit.setBorder(BLACK_BORDER);
            //adding functionality
            visit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createVisitInformation(patient, v.visitNumber);
                }
            });

            visit.setVisible(true);
            visitPane.add(visit);
        }

        visitPane.setVisible(true);

        //creating scroll pane in which the container holding the visit buttons will go
        JScrollPane visitScrollPane = new JScrollPane(visitPane);
        //setting size
        visitScrollPane.setPreferredSize(new Dimension(ContentPane.getWidth()-(ContentPane.getWidth()/25),(ContentPane.getWidth()/6)));
        //setting the vertical scroll bar visible only when needed
        visitScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //setting the horizontal scroll bar to invisible
        visitScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        visitScrollPane.setVisible(true);

        ContentPane.add(visitScrollPane);

        ContentPane.add(createBackButton(""));
        ContentPane.setVisible(true);
    }

    //to create a page where the patient can see the information about their visit
    private static void createVisitInformation(Patient patient, int visitNumber){

        ContentPane.setLayout(clearContentPaneAddLayout(ContentPane.getWidth()/2,40));



        //creating a text pane as jlabel doesn't support multiple lines without using html
        JTextPane greetingPatient = new JTextPane();
        greetingPatient.setEditable(false);
        //setting border, this is mainly used for testing
        //greetingPatient.setBorder(BLACK_BORDER);

        //string that I am adding the information to. which i will add to the textpane later
        String otherInfo ="Hello, " +patient.name;

        //so that the visit number has the correct suffix (st,nd,rd,th)
        switch (visitNumber%10){
            case 1:otherInfo = otherInfo.concat(" this is your "+ (visitNumber)+"st visit");
                break;

            case 2:otherInfo = otherInfo.concat(" this is your "+ (visitNumber)+"nd visit");
                break;

            case 3: otherInfo = otherInfo.concat(" this is your "+ (visitNumber)+"rd visit");
                break;

            default: otherInfo = otherInfo.concat(" this is your "+ (visitNumber)+"th visit");
        }
        System.out.println(otherInfo+"visit number");


        //format of the entrance time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        //adding diagnosis, entrance time and name of the doctor
        otherInfo =otherInfo.concat("\n\nDiagnosis: "+patient.doctorVisits.get(visitNumber-1).confirmedDiagnosis.diagnosisName);
        otherInfo = otherInfo.concat("\n\nEntrance time: "+ patient.doctorVisits.get(visitNumber-1).enteranceTime.format(format));
        otherInfo = otherInfo.concat("\n\n Doctor: Dr."+patient.doctor.name);
        System.out.println(otherInfo);

        //these 3 lines are needed to align the text to the center in a textpane
        //since we need to pass an attributeset object in setParagraphattributes I cannot simply use StyleConstants.ALIGN_CENTER, that is why i am creating a new object.
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center,StyleConstants.ALIGN_CENTER);
        greetingPatient.getStyledDocument().setParagraphAttributes(0,greetingPatient.getText().length(), center , false);

        System.out.println(otherInfo);

        //setting text
        greetingPatient.setText(otherInfo);

        //setting size at the end to wrap contents
        greetingPatient.setPreferredSize(greetingPatient.getPreferredSize());
        greetingPatient.setVisible(true);
        //adding it to the container
        ContentPane.add(greetingPatient);


        //adding back button
        ContentPane.add(createBackButton(""));
        ContentPane.setVisible(true);


    }

    //to create a page where the doctor can access all their patients and the patients visits
    private static void createDoctorPage(Doctor doctor){
        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/2),40));

        //creating a label that will say hello to the patient
        JLabel greetingDoctor = new JLabel("Hello Dr."+doctor.name+" these are all your patients",JLabel.CENTER);
        //setting size
        greetingDoctor.setPreferredSize(new Dimension(ContentPane.getWidth(), (int) (ContentPane.getSize().getHeight()/5)));
        //setting border, this is mainly used for testing
        //greetingDoctor.setBorder(BLACK_BORDER);
        greetingDoctor.setVisible(true);
        ContentPane.add(greetingDoctor);


        //need to use container to hold the buttons otherwise it won't work with the scrollpane
        Container patientPane = new Container();
        //need to use setpreferredsize as otherwise it won't work in the scrollpane
        patientPane.setPreferredSize(new Dimension(ContentPane.getWidth()-40,ContentPane.getHeight()));


        FlowLayout buttonLayout = new FlowLayout();
        buttonLayout.setVgap((ContentPane.getWidth()/25));
        buttonLayout.setHgap((ContentPane.getWidth()/25));
        //setting layout of container
        patientPane.setLayout(buttonLayout);

        //iterating through each patient and making a button
        for(Patient p : doctor.patients){
            //creating button
            JButton patient = new JButton(p.name);
            patient.setPreferredSize(new Dimension((ContentPane.getWidth()/8),(ContentPane.getWidth()/40)));
            patient.setBorder(BLACK_BORDER);
            //adding functionality
            patient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    System.out.println("patient information");
                    //calling method to display the information
                    createDoctorPatientInformation(doctor, p);
                }
            });

            patient.setVisible(true);
            patientPane.add(patient);
        }

        patientPane.setVisible(true);

        JScrollPane patientScrollPane = new JScrollPane(patientPane);
        patientScrollPane.setPreferredSize(new Dimension(ContentPane.getWidth()-40,(ContentPane.getWidth()/6)));
        patientScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        patientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        patientScrollPane.setVisible(true);

        ContentPane.add(patientScrollPane);

        ContentPane.add(createBackButton(""));
        ContentPane.setVisible(true);
    }

    //method to display the patient information
    private static void createDoctorPatientInformation(Doctor doctor,Patient patient){

        ContentPane.setLayout(clearContentPaneAddLayout((ContentPane.getWidth()/2),40));


        JLabel greeting = new JLabel("Hello Dr."+doctor.name+" this is Information regarding "+patient.name,JLabel.CENTER);
        greeting.setVisible(true);
        ContentPane.add(greeting);

        //creating a text pane as jlabel doesn't support multiple lines without using html
        JTextPane Information = new JTextPane();
        //setting editable false so the user can't edit information
        Information.setEditable(false);
        //setting border, this is mainly used for testing
        //Information.setBorder(BLACK_BORDER);

        //it is easier to store everything in a string and then set it to the textpane
        String patientInfo ="";
        patientInfo = patientInfo.concat("Total visits: "+patient.doctorVisits.size());

        //iterating through all the visits and adding relevant information to the string
        for(Visits v : patient.doctorVisits) {
            patientInfo = patientInfo.concat("\n\nVisit "+v.visitNumber+" \n\tDiagnosis: "+v.confirmedDiagnosis.diagnosisName);
        }

        //these 3 lines are needed to align the text to the center in a textpane
        //since we need to pass an attributeset object in setParagraphattributes I cannot simply use StyleConstants.ALIGN_CENTER, that is why i am creating a new object.
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center,StyleConstants.ALIGN_CENTER);
        Information.getStyledDocument().setParagraphAttributes(0,Information.getText().length(), center , false);

        //setting text
        Information.setText(patientInfo);
        //setting preferred size after adding text as preferred size wraps the content within it
        Information.setPreferredSize(Information.getPreferredSize());
        Information.setVisible(true);

        //scrollpane to hold the textpane where the information is displayed
        JScrollPane ScrollInformation = new JScrollPane(Information);
        //setting size
        ScrollInformation.setPreferredSize(new Dimension((int) ScrollInformation.getPreferredSize().getWidth(),ContentPane.getHeight()/2));
        //setting vertical scrollbar to visible only when needed
        ScrollInformation.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //setting horizontal scrollbar to never be visible
        ScrollInformation.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ScrollInformation.setVisible(true);

        ContentPane.add(ScrollInformation);

        ContentPane.add(createBackButton(""));
        ContentPane.setVisible(true);
    }

    //creating a radio button for each symptom, so the user can select which ever symptoms they have
    private static void createSymptomsList(Patient patient){
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
                        patient.symptomsShown=patient.symptomsShown+" "+symptom;
                    }
                    else {
                        patient.symptomsShown = patient.symptomsShown.replaceAll(symptom, "");
                    }
                }


            });

            ContentPane.add(symptomButton);

        }
    }

    //creating a method for this as it is used at the beginning of almost every method.
    //it removes all previous components and returns a flowlayout which is used as the layout for the container
    private static FlowLayout clearContentPaneAddLayout(int hGap, int vGap){

        //making current container invisible
        ContentPane.setVisible(false);
        //clearing container
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

    private static void removeEmptyPatients(){

        //this is to remove the  patient that is created if you go back from an emergency.
        try{
            if(Patient.totalPatients.getLast().name==""){
                Patient.totalPatients.removeLast();
                System.out.println("removed last patient");
            }
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }


    }
}

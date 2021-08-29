import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

public class Diagnosis  {

    //making a list of symptoms so other classes can access them.
    public static final LinkedList<String> symptomsList= new LinkedList<String>();
    //making a list of diagnosis so other classes can access them.
    public static final LinkedList<Diagnosis> diagnosisList= new LinkedList<Diagnosis>();

    //list of symptoms, not directly putting them in an array as that would make it difficult to add and remove symptoms
    //and using separate variables as that makes it easier to edit their values without breaking the program
    //adding a space after each
    private static final String FEVER="FEVER ";
    private static final String RUNNY_NOSE = "RUNNY_NOSE ";
    private static final String HEADACHE = "HEADACHE ";
    private static final String COUGH = "COUGH ";
    private static final String SORE_THROAT = "SORE_THROAT";
    private static final String SNEEZING = "SNEEZING ";
    private static final String VOMITTING = "VOMITTING ";
    private static final String TIRED = "TIRED ";
    private static final String DISCOMFORT = "DISCOMFORT ";
    private static final String RED_EYES = "RED_EYES ";
    private static final String THIRST = "THIRST ";
    private static final String ARMS_PAINING = "ARMS_PAINING ";
    private static final String LEGS_PAINING = "LEGS_PAINING ";
    private static final String CHEST_PAINING = "CHEST_PAINING ";
    private static final String STOMACH_PAINING = "STOMACH_PAINING ";
    private static final String BACK_PAINING = "BACK_PAINING ";
    private static final String JOINTS_PAINING = "JOINTS_PAINING ";
    private static final String BODY_PAINING = "BODY_PAINING ";
    private static final String APPETITE_LOSS = "APPETITE_LOSS ";
    private static final String CONFUSION ="CONFUSION ";
    private static final String DIFFICULTY_BREATHING = "DIFFICULTY_BREATHING ";


    //constructor
    Diagnosis (String symptoms,String name){
        //setting the name and the symptoms of the diagnosis
        this.symptoms=symptoms;
        this.diagnosisName = name;
        //adding the diagnosis to the diagnosis list
        diagnosisList.addLast(this);
        }




        //This will run as soon as the class gets loaded.
        static{

        System.out.println("I got loaded");
            //iterating through the declared fields of this class (a field from my understanding is just a class variable)
            for (Field declaredField : Diagnosis.class.getDeclaredFields()) {
                //checking if the field is 'final' 'static'
                if(Modifier.isFinal(declaredField.getModifiers())&&Modifier.isStatic(declaredField.getModifiers())){
                    //if it is a string, if it is it is added into symptoms list
                    if(declaredField.getType().equals(String.class)) {
                        try {
                            symptomsList.add(String.valueOf(declaredField.get(new Object())));
                        } catch (Exception e) {
                            System.out.println(e.getStackTrace());
                        }
                    }
                    //I tried to add the objects from here using declared field but realized it was easier to add them from the constructor as it only requires one line of code.
                }
            }

        }
    //the symptoms of a disease
    String symptoms;
    String diagnosisName;

    //making a list of diseases and conditions
    //have to code these by hand as there are many types with different symptoms
    private static final Diagnosis coronavirus = new Diagnosis(RUNNY_NOSE+SORE_THROAT+HEADACHE+FEVER,"Coronavirus");

    private static final Diagnosis smallpox= new Diagnosis(FEVER+DISCOMFORT+HEADACHE+TIRED+BACK_PAINING+VOMITTING,"Small Pox");

    private static final Diagnosis commonCold= new Diagnosis(RUNNY_NOSE+SORE_THROAT+COUGH+FEVER+SNEEZING,"Common Cold");

    private static final Diagnosis whoopingCough= new Diagnosis(RUNNY_NOSE+RED_EYES+FEVER+COUGH,"Whooping Cough");

    private static final Diagnosis malaria= new Diagnosis(HEADACHE+BODY_PAINING+TIRED,"Malaria");

    private static final Diagnosis tuberculosis = new Diagnosis(FEVER+APPETITE_LOSS+TIRED,"Tuberculosis");

    private static final Diagnosis cholera= new Diagnosis(VOMITTING+THIRST+LEGS_PAINING,"Cholera");

    private static final Diagnosis rabies= new Diagnosis(VOMITTING+CONFUSION,"Rabies");

    private static final Diagnosis pneumonia= new Diagnosis(COUGH+FEVER+APPETITE_LOSS+TIRED+DIFFICULTY_BREATHING,"Pneumonia");

    private static final Diagnosis brokenArm= new Diagnosis(ARMS_PAINING,"fractures in your arm");

    private static final Diagnosis brokenLeg= new Diagnosis(LEGS_PAINING,"fractures in your leg");

    private static final Diagnosis arthritis = new Diagnosis(JOINTS_PAINING,"Arthritis");

    private static final Diagnosis asthma = new Diagnosis(DIFFICULTY_BREATHING,"Asthma");

    private static final Diagnosis stomachBug = new Diagnosis(STOMACH_PAINING,"Stomach Bug");

    private static final Diagnosis heartDisease = new Diagnosis(CHEST_PAINING, "Heart Disease");







}

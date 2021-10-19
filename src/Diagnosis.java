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
    //adding a space after each to make it easier to distinguish when
    private static final String FEVER="FEVER ";
    private static final String RUNNY_NOSE = "RUNNY_NOSE ";
    private static final String HEADACHE = "HEADACHE ";
    private static final String COUGH = "COUGH ";
    private static final String SORE_THROAT = "SORE_THROAT ";
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


    //constructor which takes symptoms, name, cure and cure which is used when there is an emergency
    Diagnosis (String symptoms,String name, String cure, String severe_cure){
        //setting the name and the symptoms of the diagnosis
        this.symptoms=symptoms;
        this.diagnosisName = name;
        this.cure=cure;
        this.severeCure=severe_cure;
        //adding the diagnosis to the diagnosis list
        diagnosisList.addLast(this);
        }




        //This will run as soon as the class gets loaded.
        static{
        //it will store all the symptoms in the symptomslist so that it is easier to iterate through them when comparing the symptoms.
        //it also makes it easier to add a symptom as I won't have to create it and add it to the list and worry about messing the order up
        System.out.println("I got loaded");
            //iterating through the declared fields of this class (a field from my understanding is just a class variable)
            for (Field declaredField : Diagnosis.class.getDeclaredFields()) {
                //checking if the field is 'final' and 'static'
                if(Modifier.isFinal(declaredField.getModifiers())&&Modifier.isStatic(declaredField.getModifiers())){
                    //checking if it is a string, if it is i add it into symptoms list
                    if(declaredField.getType().equals(String.class)) {
                        try {
                            symptomsList.add(String.valueOf(declaredField.get(new Object())));
                            System.out.println(symptomsList.getLast());
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
    String cure="";
    String severeCure="";

    //making a list of diseases and conditions
    //have to code these by hand as there are many types with different symptoms
    private static final Diagnosis coronavirus = new Diagnosis(RUNNY_NOSE+SORE_THROAT+HEADACHE+FEVER,"Coronavirus","stay at home and monitor symptoms", "hospitalization");

    private static final Diagnosis smallpox= new Diagnosis(FEVER+DISCOMFORT+HEADACHE+TIRED+BACK_PAINING+VOMITTING,"Small Pox","smallpox vaccine","tecovirimat, cidofovir and brincidofovir");

    private static final Diagnosis commonCold= new Diagnosis(RUNNY_NOSE+SORE_THROAT+COUGH+FEVER+SNEEZING,"Common Cold","wait until you get better", "treat the symptoms ");

    private static final Diagnosis whoopingCough= new Diagnosis(RUNNY_NOSE+RED_EYES+FEVER+COUGH,"Whooping Cough", "Clarithromycin","hospitalization");

    private static final Diagnosis malaria= new Diagnosis(HEADACHE+BODY_PAINING+TIRED,"Malaria","antimalrials to be taken orally","hospitalization");

    private static final Diagnosis tuberculosis = new Diagnosis(FEVER+APPETITE_LOSS+TIRED,"Tuberculosis","isoniazid (INH), rifampin (RIF), ethambutol (EMB)", "hospitalization");

    private static final Diagnosis cholera= new Diagnosis(VOMITTING+THIRST+LEGS_PAINING,"Cholera","Dehydration therapy", "hospitalization");

    private static final Diagnosis rabies= new Diagnosis(VOMITTING+CONFUSION,"Rabies","Rabies vaccine","Postexposure prophylaxis, given multiple times with small intervals in between");

    private static final Diagnosis pneumonia= new Diagnosis(COUGH+FEVER+APPETITE_LOSS+TIRED+DIFFICULTY_BREATHING,"Pneumonia","Pneumonia vaccine","Septic shock with need for vasopressors");

    private static final Diagnosis brokenArm= new Diagnosis(ARMS_PAINING,"fractures in your arm","cast on the affected arm","cast on affected arm, with physical therapy");

    private static final Diagnosis brokenLeg= new Diagnosis(LEGS_PAINING,"fractures in your leg","cast on the affected leg with crutches","cast on the affected leg with crutches, with physical therapy");

    private static final Diagnosis arthritis = new Diagnosis(JOINTS_PAINING,"Arthritis","avoid high impact activities","avoid high impact activities, manage weight and exercise");

    private static final Diagnosis asthma = new Diagnosis(DIFFICULTY_BREATHING,"Asthma","asthma can not be cured, so it is best to make an asthma management plan","asthma can not be cured, so it is best to make an asthma management plan");

    private static final Diagnosis stomachBug = new Diagnosis(STOMACH_PAINING,"Stomach Bug","it will resolve itself in a few days", "IV drip");

    private static final Diagnosis heartDisease = new Diagnosis(CHEST_PAINING, "Heart Disease","heart disease can not be cured but normalizing blood pressure and lowering cholesterol will help","heart disease can not be cured but normalizing blood pressure and lowering cholesterol will help");

}

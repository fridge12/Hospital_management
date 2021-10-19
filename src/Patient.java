import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Patient {
    //list that contains all the patients
    public static LinkedList<Patient> totalPatients= new LinkedList<Patient>();

    //each patient has a name, unique id number, doctor, symptoms and list of visits to the doctor
    String name="";
    int ID;
    Doctor doctor;
    String symptomsShown = "";
    LinkedList<Visits> doctorVisits = new LinkedList<Visits>();

    //constructor which takes name as an input
    Patient(String name){
        //setting name to the given name
        this.name= name;
        //setting ID to the number of patients
        this.ID = totalPatients.size();
        //adding it to the list which contains all patients
        totalPatients.addLast(this);
        //setting its doctor
        this.setDoctor();
    }


    //this method is used to assign a doctor to the patient.
    private void setDoctor(){
        //setting doctor to first in linked list
        this.doctor = Doctor.totalDoctors.getFirst();

        //checking which doctor has the least amount of patients and then assigning that doctor
        for(Doctor d: Doctor.totalDoctors){
            if(this.doctor.patients.size()>d.patients.size()){
                this.doctor = d;
            }
        }
        //adding patient in doctor
        this.doctor.addPatient(this);

        //printing doctors name
        System.out.println(this.doctor.name);
    }

    //to find all possible diagnosis and to return the name of the most likely diagnosis.
    public String findDiagnosis(String symptoms){
        //takes the names of the symptoms as the input
        Scanner in = new Scanner(symptoms);

        //need to use try catch as sometimes doctorvisits.getlast produces and exception
        try{
            //if the last doctor visit is null then we can use it as there is nothing in there, otherwise we need to create a new doctor visit which is null
            if(doctorVisits.getLast().confirmedDiagnosis!=null){
                //creates a new visit to the doctor
                doctorVisits.addLast(new Visits(doctorVisits.size()));
            }
        }
        catch(Exception e){
            //creates a new visit to the doctor
            doctorVisits.addLast(new Visits(doctorVisits.size()));
        }

        System.out.println(doctorVisits.getLast().visitNumber);
        // in this loop I take a symptom, check which all 'Diagnosis' have it in their symptom list
        //if a diagnosis does have it, I check if it is already in the hashmap of all the possible diagnosis
        //if it is not I add it to the hashmap and set its value to 1
        //if it is there I add 1 to its value

        while(in.hasNext()){
            //separating the symptoms
            String s =in.next();
            System.out.println(s);
            //iterating through every diagnosis
            for (Diagnosis diagnosis : Diagnosis.diagnosisList) {
                //checking if has the symptom
                if(diagnosis.symptoms.contains(s)) {
                    System.out.println(diagnosis.symptoms+" "+diagnosis.diagnosisName);
                    //adding 1 to the value if present, if not present then value is set to 1
                    if(doctorVisits.getLast().possibleDiagnosis.containsKey(diagnosis)) {
                        //adding 1 to the value
                        doctorVisits.getLast().possibleDiagnosis.computeIfPresent(diagnosis, (key, val) -> val + 1);
                    }
                    else{
                        //since the diagnosis was not there, it is added into the hashmap with a value of 1
                        doctorVisits.getLast().possibleDiagnosis.put(diagnosis, 1);
                    }
                }
            }


        }


        //this checks which diagnosis has the most matching symptoms to the symptoms the patients is showing
        int mostMatchingSymptoms=0;
        for(Map.Entry<Diagnosis, Integer> ent: doctorVisits.getLast().possibleDiagnosis.entrySet()){
            System.out.println(ent.getKey().diagnosisName);
        if(ent.getValue()>mostMatchingSymptoms){
            System.out.println(ent.getValue());
            //setting confirmed diagnosis to the diagnosis which has the most matching symptoms
            doctorVisits.getLast().confirmedDiagnosis=ent.getKey();
            mostMatchingSymptoms= ent.getValue();
        }

        //if the current diagnosis has the same number of matching symptoms, then to calculate the most correct diagnosis
        //i am taking the ratio of matching symptoms to total symptoms
        //and then multiplying the value by the total number of symptoms shown by the patient

        else if(ent.getValue()==mostMatchingSymptoms){
            double prevSymptoms=noOfSymptoms(symptomsShown.trim())*((double)mostMatchingSymptoms/noOfSymptoms(doctorVisits.getLast().confirmedDiagnosis.symptoms.trim()));
            double currentSymptoms =noOfSymptoms(symptomsShown.trim())*((double)mostMatchingSymptoms/noOfSymptoms(ent.getKey().symptoms.trim()));
            System.out.println(prevSymptoms+" "+currentSymptoms);

            if(currentSymptoms>prevSymptoms){
                doctorVisits.getLast().confirmedDiagnosis= ent.getKey();
            }
        }
        }

        System.out.println(doctorVisits.getLast().possibleDiagnosis.toString());
        System.out.println(doctorVisits.getLast().confirmedDiagnosis);
        //returning the name of the diagnosis
        return doctorVisits.getLast().confirmedDiagnosis.diagnosisName;
    }

    //this method returns the total number of symptoms in a disease
    private static double noOfSymptoms(String symptoms){
        Scanner sc = new Scanner(symptoms);
        int ctr=0;
        while(sc.hasNext()){
            ctr++;
            sc.next();
        }
        return ctr;
    }

}
